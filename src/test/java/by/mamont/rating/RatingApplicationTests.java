package by.mamont.rating;

import by.mamont.rating.entity.Player;
import by.mamont.rating.service.PlayerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingApplicationTests {

	@Autowired
	private PlayerService playerService;

	@Test
	public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
		List<Player> players = playerService.list();

		Assert.assertEquals(players.size(), 2);
	}

	@Test
	public void contextLoads() {
	}

}
