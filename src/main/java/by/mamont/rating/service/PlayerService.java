package by.mamont.rating.service;

import by.mamont.rating.entity.PlayerRepository;
import by.mamont.rating.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService
{
  @Autowired
  private PlayerRepository playerRepository;


  public List<Player> list()
  {
    return playerRepository.findAll();
  }

  public Optional<Player> findById(int id)
  {
    return playerRepository.findById(id);
  }

  public List<Player> findByLettersContaining(String surname, String name, String patronymic)
  {
    Pageable pageable = PageRequest.of(0, 10, Sort.by("surname", "name", "patronymic"));
    return playerRepository.findByLettersContaining(surname, name, patronymic, pageable).getContent();
  }
}
