package by.mamont.rating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import by.mamont.rating.entity.Player;
import by.mamont.rating.service.PlayerService;

@RestController()
public class TeamController
{
  @Autowired
  private PlayerService playerService;

  @RequestMapping("api/team/{teamId}")
  public List<Player> getTeamPlayers(@PathVariable(value = "teamId") String teamId)
  {
    return playerService.list();
  }


  @CrossOrigin(origins = "http://localhost:1234")
  @RequestMapping("api/player")
  public List<Player> findPlayers(
    @RequestParam(value = "surname", required = false) String surname,
    @RequestParam(value = "name", required = false, defaultValue = "") String name,
    @RequestParam(value = "patronymic", required = false, defaultValue = "") String patronymic
  )
  {
    return playerService.findByLettersContaining(surname, name, patronymic);
  }
}