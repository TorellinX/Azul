package de.lmu.ifi.sosylab.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * Tests for the implementation of the model.
 */
public class ModelTest {

  private static ArrayList<String> testPlayers;
  private static final int numberOfPlayers = 4;

  @BeforeAll
  static void setUp() {
    testPlayers = new ArrayList<>();
    for (int i = 0; i < numberOfPlayers; i++) {
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

  @Test
  public void shiftFirstPlayer_whenFirstPlayerIsRandom() {
    AzulModel model = newModel(testPlayers);
    int firstPlayerBefore = model.getFirstPlayer();
    model.shiftFirstPlayer();
    if (firstPlayerBefore < model.getPlayers().size() - 1) {
      assertEquals(model.getFirstPlayer(), firstPlayerBefore + 1);
    } else if (firstPlayerBefore == model.getPlayers().size() - 1) {
      assertEquals(model.getFirstPlayer(), 0);
    } else {
      fail("First player index must be less than the number of players");
    }

  }


}
