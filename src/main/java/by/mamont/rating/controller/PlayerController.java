package by.mamont.rating.controller;

import by.mamont.rating.entity.Player;
import by.mamont.rating.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController
{
  private static final String CROSS_ORIGIN_HOST = "http://localhost:1234";

  @Autowired
  private PlayerService playerService;

  @CrossOrigin(origins = CROSS_ORIGIN_HOST)
  @RequestMapping("api/player/all")
  public List<Player> getAllPlayers()
  {
    return playerService.list();
  }

  @CrossOrigin(origins = CROSS_ORIGIN_HOST)
  @RequestMapping("api/player/{playerId}")
  public Player getPlayer(@PathVariable(name = "playerId") String playerId)
  {
    return playerService.findById(Integer.parseInt(playerId)).orElse(null);
  }

  @CrossOrigin(origins = CROSS_ORIGIN_HOST)
  @RequestMapping("api/player/search")
  public List<Player> findPlayers(
    @RequestParam(value = "surname", required = false) String surname,
    @RequestParam(value = "name", required = false, defaultValue = "") String name,
    @RequestParam(value = "patronymic", required = false, defaultValue = "") String patronymic
  )
  {
    return playerService.findByLettersContaining(surname, name, patronymic);
  }
}
