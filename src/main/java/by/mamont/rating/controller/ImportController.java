package by.mamont.rating.controller;

import by.mamont.rating.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImportController
{
  @Autowired
  private ImportService importService;

  @RequestMapping("api/import/new")
  public void importNewPlayers()
  {
    int integratedCount = importService.importPlayers();
    System.out.println(integratedCount);
  }
}
