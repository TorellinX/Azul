package de.lmu.ifi.sosylab.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Tests for the implementation of the model.
 */
public class ModelTest {

  private static ArrayList<String> testPlayers;
  private static final int NUMBER_OF_PLAYERS = 4;

  @BeforeAll
  static void setUp() {
    testPlayers = new ArrayList<>();
    for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
      testPlayers.add("Player" + i);
    }
  }

  AzulModel newModel(ArrayList<String> testPlayers) {
    return new AzulModel(testPlayers);
  }

  @Test
  public void getPlayers_whenPlayersExist() {
    AzulModel model = newModel(testPlayers);
    assertIterableEquals(model.getPlayers(), testPlayers);
  }

  @Test
  public void shiftActivePlayer_whenActivePlayerIs0() {
    AzulModel model = newModel(testPlayers);
    model.setActivePlayer(0);
    model.shiftActivePlayer();
    assertEquals(model.getActivePlayer(), 1);
  }

  @Test
  public void shiftActivePlayer_whenActivePlayerIsMax() {
    AzulModel model = newModel(testPlayers);
    model.setActivePlayer(testPlayers.size() - 1);
    model.shiftActivePlayer();
    assertEquals(model.getActivePlayer(), 0);
  }


}
