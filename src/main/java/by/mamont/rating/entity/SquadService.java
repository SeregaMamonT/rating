package by.mamont.rating.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SquadService
{
  @Autowired
  private SquadRepository squadRepository;

  public Squad createSquad(Squad squad)
  {
    return squadRepository.save(squad);
  }
}
