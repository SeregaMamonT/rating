package by.mamont.rating.service;

import by.mamont.rating.entity.Player;
import by.mamont.rating.entity.PlayerRepository;
import by.mamont.rating.entity.SyncStatus;
import by.mamont.rating.entity.SyncStatusRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ImportService
{
  private static final int SYNC_STATUS_ID = 1;
  private static final String PLAYER_PAGE_TEMPLATE = "https://rating.chgk.info/api/players.json?page=%d";

  @Autowired
  private PlayerRepository playerRepository;
  @Autowired
  private SyncStatusRepository syncStatusRepository;


  public int importPlayers()
  {
    Optional<SyncStatus> syncStatus = syncStatusRepository.findById(SYNC_STATUS_ID);
    return syncStatus
      .map(this::importPlayers)
      .orElse(-1);
  }

  private int importPlayers(SyncStatus status)
  {
    int importedCount = 0;
    try {
      Iterator<List<Player>> it = getPageIterator(status);
      do {
        List<Player> players = it.next();
        playerRepository.saveAll(players);
        importedCount += players.size();
        randomSleep();
      } while (it.hasNext());
    }
    catch (Exception e) {
      return -1;
    }
    return importedCount;
  }

  private Iterator<List<Player>> getPageIterator(final SyncStatus syncStatus)
  {
    return new Iterator<>()
    {
      private SyncStatus status = syncStatus;
      private JSONObject currentPage;

      @Override
      public boolean hasNext()
      {
        if (currentPage == null) {
          initCurrentPage();
        }
        return currentPage != null && hasNextPage(currentPage);
      }

      @Override
      public List<Player> next()
      {
        if (currentPage == null) {
          initCurrentPage();
        }
        if (currentPage != null) {
          List<Player> players = getPlayersFromJson(currentPage);
          if (hasNextPage(currentPage)) {
            status.setCurrentPageId(status.getCurrentPageId() + 1);
            syncStatusRepository.save(status);
            initCurrentPage();
          }
          return players;
        }
        else {
          throw new IllegalStateException("Iterator has no next.");
        }
      }

      private void initCurrentPage()
      {
        currentPage = getPlayersPage(status.getCurrentPageId()).orElse(null);
      }
    };
  }

  private List<Player> getPlayersFromJson(JSONObject object)
  {
    JSONArray items = (JSONArray) object.get("items");
    return jsonArrayToListOfPlayers(items);
  }

  private List<Player> jsonArrayToListOfPlayers(JSONArray array)
  {
    List<Player> players = new ArrayList<>(1000);
    array.forEach(object -> players.add(jsonToPlayer((JSONObject) object)));
    return players;
  }

  private Player jsonToPlayer(JSONObject json)
  {
    Player player = new Player();
    player.setId(json.getInt("idplayer"));
    player.setName(json.getString("name"));
    player.setSurname(json.getString("surname"));
    player.setPatronymic(json.getString("patronymic"));
    return player;
  }

  private static Optional<JSONObject> getPlayersPage(int pageId)
  {
    try {
      URI uri = new URI(String.format(PLAYER_PAGE_TEMPLATE, pageId));
      HttpRequest request = HttpRequest.newBuilder(uri).build();
      String body = HttpClient.newHttpClient()
        .send(request, HttpResponse.BodyHandlers.ofString())
        .body();
      return Optional.of(new JSONObject(body));
    }
    catch (Exception e) {
      return Optional.empty();
    }
  }

  private boolean hasNextPage(JSONObject pageObject)
  {
    Pair<Integer, Integer> currentRange = parseRange(pageObject.getString("current_items"));
    int total = Integer.parseInt(pageObject.getString("total_items"));
    return total > currentRange.getSecond();
  }

  private Pair<Integer, Integer> parseRange(String rangeStr)
  {
    String[] tokens = rangeStr.split("-");
    return Pair.of(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
  }

  private void randomSleep()
  {
    long sleepPeriod = Math.round(Math.random() * 1000 * 5 + 1000);
    this.sleep(sleepPeriod);
  }

  private void sleep(long millis)
  {
    try {
      Thread.sleep(millis);
    }
    catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
