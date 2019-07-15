package by.mamont.rating.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Player
{
  @Id
  private int id;
  private String name;
  private String surname;
  private String patronymic;
  @ManyToMany(mappedBy = "players")
  private List<Squad> squads;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getSurname()
  {
    return surname;
  }

  public void setSurname(String surname)
  {
    this.surname = surname;
  }

  public String getPatronymic()
  {
    return patronymic;
  }

  public void setPatronymic(String patronymic)
  {
    this.patronymic = patronymic;
  }

  public List<Squad> getSquads()
  {
    return squads;
  }

  public void setSquads(List<Squad> squads)
  {
    this.squads = squads;
  }
}
