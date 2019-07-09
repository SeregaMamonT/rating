package by.mamont.rating.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SyncStatus
{
  @Id
  private int id = 1;
  private int currentPageId = 1;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public int getCurrentPageId()
  {
    return currentPageId;
  }

  public void setCurrentPageId(int currentPageId)
  {
    this.currentPageId = currentPageId;
  }
}
