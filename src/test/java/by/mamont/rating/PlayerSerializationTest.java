package by.mamont.rating;

import by.mamont.rating.entity.Player;
import by.mamont.rating.entity.Squad;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerSerializationTest
{
  @Test
  public void givenBidirectionRelation_whenSerializing_thenException() throws JsonProcessingException
  {
    Player player = new Player();
    Squad squad = new Squad();
    squad.setPlayers(Arrays.asList(player));
    player.setSquads(Arrays.asList(squad));

    assertThat(new ObjectMapper().writeValueAsString(player), not(isEmptyString()));
    assertThat(new ObjectMapper().writeValueAsString(squad), not(isEmptyString()));
  }
}
