package de.lmu.ifi.sosylab.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.Arrays;
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

  @Test
  public void addColorTilesToLine_whenFits() {
    GameModel model = newModel(testPlayers);
    model.getPlayers().get(0).playerBoard.patternLines[4] = new ColorTile[]{null, null, null, null,
        new ColorTile(Color.RED)};
    ArrayList<ColorTile> redTiles = new ArrayList<>();
    for (int i = 0; i < model.getPlayers().get(0).playerBoard.patternLines[4].length - 1; i++) {
      redTiles.add(new ColorTile(Color.RED));
    }

    model.getPlayers().get(0).playerBoard.addColorTilesToLine(redTiles, 4);

    ColorTile[] redTilesInLine = new ColorTile[model.getPlayers()
        .get(0).playerBoard.patternLines[4].length];
    for (int i = 0; i < redTilesInLine.length; i++) {
      redTilesInLine[i] = new ColorTile(Color.RED);
    }

    assertArrayEquals(model.getPlayers().get(0).playerBoard.patternLines[4], redTilesInLine);
  }

}
