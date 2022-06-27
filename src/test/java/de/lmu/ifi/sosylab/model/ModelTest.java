package de.lmu.ifi.sosylab.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Tests for the implementation of the model.
 */
public class ModelTest {

  private static List<Player> testPlayers = new ArrayList<>();
  private static final int NUMBER_OF_PLAYERS = 4;

  @BeforeAll
  static void setUp() {

    for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
      testPlayers.add(new Player("Player" + i));
    }
  }

  GameModel newModel(List<Player> testPlayers) {
    return new GameModel(testPlayers);
  }

  @Test
  public void getPlayers_whenPlayersExist() {
    GameModel model = newModel(testPlayers);
    assertIterableEquals(model.getPlayers(), testPlayers);
  }


  @Test
  public void getAmountOfPlates_accordingToPlayers() {
    GameModel model = newModel(testPlayers);
    int amount = testPlayers.size() * 2 + 1;
    assertEquals(model.getPlates().size(), amount);
  }

}
