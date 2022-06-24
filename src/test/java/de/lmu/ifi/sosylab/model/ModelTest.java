package de.lmu.ifi.sosylab.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

// import de.lmu.ifi.sosylab.model.AzulModel;
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
    for(int i = 0; i < numberOfPlayers; i++) {
      testPlayers.add("Player" + i);
    }
  }

  /* // model classes are required
  AzulModel newModel(ArrayList<String> testPlayers) {return new AzulModel(testPlayers);}

  @Test
  public void modelPlayers() {
    AzulModel model = newModel(testPlayers);
    assertIterableEquals(model.getPlayers(), testPlayers);
  }

  @Test
  public void shiftFirstPlayer_whenActivePlayerIs0(){
    AzulModel model = newModel(testPlayers);
    model.setActivePlayer(0);
    model.s
    assertTrue();
  }

  @Test
  public void shiftFirstPlayer_whenActivePlayerIsMax(){
    AzulModel model = newModel(testPlayers);
    model.setActivePlayer(testPlayers.size());
  }

   */

}
