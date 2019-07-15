package by.mamont.rating.controller;

import by.mamont.rating.entity.Squad;
import by.mamont.rating.entity.SquadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import by.mamont.rating.entity.Player;

@RestController()
public class SquadController
{
  @Autowired
  private SquadService squadService;

  @CrossOrigin(origins = "http://localhost:1234")
  @RequestMapping("api/squad/create")
  public void createSquad(@RequestBody() List<Player> players)
  {
    Squad squad = new Squad();
    squad.setName("Apocalypse");
    squad.setPlayers(players);
    squadService.createSquad(squad);
  }
}
