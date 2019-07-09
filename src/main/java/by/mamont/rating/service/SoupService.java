package by.mamont.rating.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import by.mamont.rating.entity.Player;

@Service
public class SoupService
{
  private static final String TEAM_URL_TEMPLATE = "https://rating.chgk.info/team/%s";

  public List<Player> parseTeam(String teamId)
  {
    String teamUrl = String.format(TEAM_URL_TEMPLATE, teamId);
    Optional<Document> teamPage = getDocument(teamUrl);
    return teamPage
      .map(this::getTeamPlayers)
      .orElse(Collections.emptyList());
  }

  private List<Player> getTeamPlayers(Document teamPage)
  {
    Element seasons = teamPage.getElementById("seasons");
    Element seasonRow = seasons.child(1).child(1);
    Element season = seasonRow.child(1);
    Elements players = season.getElementsByTag("a");
    return players.stream()
      .map(this::parsePlayer)
      .collect(Collectors.toList());
  }

  private Player parsePlayer(Element element)
  {
    Player player = new Player();

    String text = element.text().trim();
    if (text.startsWith("#")) {
      text = text.substring(2);
    }

    int index = text.indexOf(" ");
    player.setId(Integer.parseInt(text.substring(0, index)));
    text = text.substring(index + 1);
    player.setName(text);
    return player;
  }

  private Optional<Document> getDocument(String url)
  {
    try {
      return Optional.of(Jsoup.connect(url).get());
    }
    catch (IOException e) {
      return Optional.empty();
    }
  }
}
