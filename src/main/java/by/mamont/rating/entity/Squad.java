package by.mamont.rating.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "id")
@Entity
public class Squad
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @ManyToMany()
  @JoinTable(name = "squad_player",
    joinColumns = @JoinColumn(name = "squad_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "player_id", referencedColumnName = "id"))
  private List<Player> players;
  private String name;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public List<Player> getPlayers()
  {
    return players;
  }

  public void setPlayers(List<Player> players)
  {
    this.players = players;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }
}
