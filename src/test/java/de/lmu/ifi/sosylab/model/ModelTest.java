package de.lmu.ifi.sosylab.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the implementation of the model.
 */
public class ModelTest {

  private static final int NUMBER_OF_PLAYERS = 4;
  private static List<Player> testPlayers = new ArrayList<>();

  @BeforeAll
  static void setUp() {

    for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
      testPlayers.add(new Player("Player" + i));
    }
  }

  @AfterEach
  void clearPlayerBoards() {
    for (Player player : testPlayers) {
      player.playerBoard = new PlayerBoard();
    }
  }


  GameModel newModel(List<Player> testPlayers) {
    return new GameModel(testPlayers);
  }

  @Test
  public void getPlayers_whenPlayersExist() {
    GameModel model = newModel(testPlayers);
    assertIterableEquals(testPlayers, model.getPlayers());
  }

  @Test
  public void getAmountOfPlates_accordingToPlayers() {
    GameModel model = newModel(testPlayers);
    int expectedAmount = testPlayers.size() * 2 + 1;
    assertEquals(expectedAmount, model.getPlates().size());
  }

  @Test
  public void addColorTilesToPatternLine_whenFits() {
    //Arrange test
    GameModel model = newModel(testPlayers);
    model.getPlayers().get(0).playerBoard.patternLines[4] = new ColorTile[]{null, null, null, null,
        new ColorTile(Color.RED)};
    ArrayList<ColorTile> addedRedTiles = new ArrayList<>();
    for (int i = 0; i < model.getPlayers().get(0).playerBoard.patternLines[4].length - 1; i++) {
      addedRedTiles.add(new ColorTile(Color.RED));
    }
    ColorTile[] expectedLine = new ColorTile[model.getPlayers()
        .get(0).playerBoard.patternLines[4].length];
    for (int i = 0; i < expectedLine.length; i++) {
      expectedLine[i] = new ColorTile(Color.RED);
    }

    //Act test
    model.getPlayers().get(0).playerBoard.addColorTilesToLine(addedRedTiles, 4);

    //Assert test
    assertArrayEquals(expectedLine, model.getPlayers().get(0).playerBoard.patternLines[4]);
  }

  @Test
  public void addTileToFloorLine_floorLineWhenPenaltyTileIsBeingAddedToFullFloorLine() {
    //Arrange test
    GameModel model = newModel(testPlayers);
    Player testingPlayer = model.getPlayers().get(0);
    testingPlayer.playerBoard.floorLine = new ArrayList<>();
    for (int i = 0; i < PlayerBoard.FLOORLINE_SIZE; i++) {
      Color currentColor = Color.values()[i % (Color.values().length - 1)];
      testingPlayer.playerBoard.floorLine.add(new ColorTile(currentColor));
    }
    List<Tile> expectedFloorLine = new ArrayList<>(testingPlayer.playerBoard.floorLine);
    expectedFloorLine.remove(testingPlayer.playerBoard.floorLine.size() - 1);
    expectedFloorLine.add(0, new PenaltyTile());

    //Act test
    testingPlayer.playerBoard.addTileToFloorLine(new PenaltyTile());

    //Assert test
    assertEquals(expectedFloorLine, testingPlayer.playerBoard.floorLine);
  }

  @Test
  public void addTileToFloorLine_boxWhenPenaltyTileIsBeingAddedToFullFloorLine() {
    //Arrange test
    GameModel model = newModel(testPlayers);
    Player testingPlayer = model.getPlayers().get(0);
    testingPlayer.playerBoard.floorLine = new ArrayList<>();
    for (int i = 0; i < PlayerBoard.FLOORLINE_SIZE; i++) {
      Color currentColor = Color.values()[i % (Color.values().length - 1)];
      testingPlayer.playerBoard.floorLine.add(new ColorTile(currentColor));
    }
    List<Tile> expectedBox = new ArrayList<>();
    expectedBox.add(
        testingPlayer.playerBoard.floorLine.get(testingPlayer.playerBoard.floorLine.size() - 1));

    //Act test
    testingPlayer.playerBoard.addTileToFloorLine(new PenaltyTile());

    //Assert test
    assertEquals(expectedBox, model.box);
  }

  @Test
  public void addTileToFloorLine_floorLineWhenColorTileIsBeingAddedToFullFloorLine() {
    //Arrange test
    GameModel model = newModel(testPlayers);
    Player testingPlayer = model.getPlayers().get(0);
    testingPlayer.playerBoard.floorLine = new ArrayList<>();
    for (int i = 0; i < PlayerBoard.FLOORLINE_SIZE; i++) {
      Color currentColor = Color.values()[i % (Color.values().length - 1)];
      testingPlayer.playerBoard.floorLine.add(new ColorTile(currentColor));
    }
    List<Tile> expectedFloorLine = new ArrayList<>(testingPlayer.playerBoard.floorLine);

    //Act test
    testingPlayer.playerBoard.addTileToFloorLine(new ColorTile(Color.YELLOW));

    //Assert test
    assertEquals(expectedFloorLine, testingPlayer.playerBoard.floorLine);
  }

  @Test
  public void addTileToFloorLine_boxWhenColorTileIsBeingAddedToFullFloorLine() {
    //Arrange test
    GameModel model = newModel(testPlayers);
    Player testingPlayer = model.getPlayers().get(0);
    testingPlayer.playerBoard.floorLine = new ArrayList<>();
    for (int i = 0; i < PlayerBoard.FLOORLINE_SIZE; i++) {
      Color currentColor = Color.values()[i % (Color.values().length - 1)];
      testingPlayer.playerBoard.floorLine.add(new ColorTile(currentColor));
    }
    List<Tile> expectedBox = new ArrayList<>();
    ColorTile addedTile = new ColorTile(Color.YELLOW);
    expectedBox.add(addedTile);

    //Act test
    testingPlayer.playerBoard.addTileToFloorLine(addedTile);

    //Assert test
    assertEquals(expectedBox, model.box);
  }

  @Test
  public void moveFullPatternLineToBox_floorLineWhenPatternLineComplete() {
    //Arrange test
    GameModel model = newModel(testPlayers);
    Player testingPlayer = model.getPlayers().get(0);
    int rowIndex = 4;
    ColorTile[] completeRow = new ColorTile[rowIndex + 1];
    for (int i = 0; i < rowIndex + 1; i++) {
      completeRow[i] = new ColorTile(Color.YELLOW);
    }
    testingPlayer.playerBoard.patternLines[rowIndex] = completeRow;
    ColorTile[] expectedRow = new ColorTile[rowIndex + 1];

    //Act test
    model.moveFullPatternLineToBox(rowIndex, testingPlayer.playerBoard);

    //Assert test
    assertArrayEquals(expectedRow, testingPlayer.playerBoard.patternLines[rowIndex]);
  }

  @Test
  public void moveFullPatternLineToBox_boxWhenPatternLineComplete() {
    //Arrange test
    GameModel model = newModel(testPlayers);
    Player testingPlayer = model.getPlayers().get(0);
    int rowIndex = 4;
    ColorTile[] completeRow = new ColorTile[rowIndex + 1];
    for (int i = 0; i < rowIndex + 1; i++) {
      completeRow[i] = new ColorTile(Color.YELLOW);
    }
    testingPlayer.playerBoard.patternLines[rowIndex] = completeRow;
    List<Tile> expectedBox = new ArrayList<>();
    for (int i = 0; i < rowIndex; i++) {
      expectedBox.add(new ColorTile(Color.YELLOW));
    }

    //Act test
    model.moveFullPatternLineToBox(rowIndex, testingPlayer.playerBoard);

    //Assert test
    assertEquals(expectedBox, model.box);
  }


  @Test
  public void moveFullPatternLineToBox_whenPatternLineIncomplete() {
    //Arrange test
    GameModel model = newModel(testPlayers);
    Player testingPlayer = model.getPlayers().get(0);
    int rowIndex = 4;
    ColorTile[] incompleteRow = new ColorTile[rowIndex + 1];
    for (int i = incompleteRow.length - 1; i > 0; i--) {
      incompleteRow[i] = new ColorTile(Color.YELLOW);
    }
    testingPlayer.playerBoard.patternLines[rowIndex] = incompleteRow;

    //Act test
    try {
      model.moveFullPatternLineToBox(rowIndex, testingPlayer.playerBoard);
      fail("The patten line is not complete");
    } catch (RuntimeException e) {

      //Assert test
      assertEquals("The patten line must be complete", e.getMessage());
    }
  }

  @Test
  public void setPickedTiles_floorLineWhenToFloorLine() {
    //Arrange test
    GameModel model = newModel(testPlayers);
    List<ColorTile> testTiles = new ArrayList<>();
    testTiles.add(new ColorTile(Color.BLACK));
    testTiles.add(new ColorTile(Color.BLACK));
    testTiles.add(new ColorTile(Color.BLACK));
    model.selectedTiles = testTiles;
    Player testingPlayer = model.getPlayers().get(0);
    int rowIndex = -1;

    //Act test
    model.setPickedTiles(testingPlayer, rowIndex);

    //Assert test
    assertEquals(testTiles, testingPlayer.playerBoard.floorLine);
  }

  @Test
  public void setPickedTiles_floorLineWhenToPatternLineNotEnough() {
    //Arrange test
    GameModel model = newModel(testPlayers);
    int rowIndex = 1;
    int overage = 2;
    List<ColorTile> testTiles = new ArrayList<>();
    for (int i = 0; i < rowIndex + overage; i++) {
      testTiles.add(new ColorTile(Color.BLACK));
    }
    model.selectedTiles = testTiles;
    ColorTile[] testingPatternLine = new ColorTile[rowIndex + 1];
    for (int i = testingPatternLine.length - 1; i >= 1; i--) { //patternLine with just one free slot
      testingPatternLine[i] = new ColorTile(Color.BLACK);
    }
    Player testingPlayer = model.getPlayers().get(0);
    testingPlayer.playerBoard.patternLines[rowIndex] = testingPatternLine;
    List<ColorTile> expectedFloorLine = new ArrayList<>();
    for (int i = 0; i < overage; i++) {
      expectedFloorLine.add(new ColorTile(Color.BLACK));
    }

    //Act test
    model.setPickedTiles(testingPlayer, rowIndex);

    //Assert test
    assertEquals(expectedFloorLine, testingPlayer.playerBoard.floorLine);
  }

  @Test
  public void setPickedTiles_patternLineWhenToPatternLineNotEnough() {
    //Arrange test
    GameModel model = newModel(testPlayers);
    int rowIndex = 1;
    int overage = 2;
    List<ColorTile> testTiles = new ArrayList<>();
    for (int i = 0; i < rowIndex + overage; i++) {
      testTiles.add(new ColorTile(Color.BLACK));
    }
    model.selectedTiles = testTiles;
    ColorTile[] testingPatternLine = new ColorTile[rowIndex + 1];
    for (int i = testingPatternLine.length - 1; i >= 1; i--) { //patternLine with just one free slot
      testingPatternLine[i] = new ColorTile(Color.BLACK);
    }
    Player testingPlayer = model.getPlayers().get(0);
    testingPlayer.playerBoard.patternLines[rowIndex] = testingPatternLine;
    ColorTile[] expectedPatternLine = new ColorTile[rowIndex + 1];
    for (int i = expectedPatternLine.length - 1; i >= 0; i--) {
      expectedPatternLine[i] = new ColorTile(Color.BLACK);
    }

    //Act test
    model.setPickedTiles(testingPlayer, rowIndex);

    //Assert test
    assertArrayEquals(expectedPatternLine, testingPlayer.playerBoard.patternLines[rowIndex]);
  }

  @Test
  public void setPickedTiles_whenToPatternLineColorMismatch() {
    //Arrange test
    GameModel model = newModel(testPlayers);
    int rowIndex = 1;
    int overage = 2;
    List<ColorTile> testTiles = new ArrayList<>();
    for (int i = 0; i < rowIndex + overage; i++) {
      testTiles.add(new ColorTile(Color.BLACK));
    }
    model.selectedTiles = testTiles;
    ColorTile[] testingPatternLine = new ColorTile[rowIndex + 1];
    for (int i = testingPatternLine.length - 1; i >= 1; i--) { // patternLine with other color
      testingPatternLine[i] = new ColorTile(Color.YELLOW);
    }
    Player testingPlayer = model.getPlayers().get(0);
    testingPlayer.playerBoard.patternLines[rowIndex] = testingPatternLine;
    ColorTile[] expectedPatternLine = Arrays.copyOf(testingPatternLine, testingPatternLine.length);

    //Act test
    model.setPickedTiles(testingPlayer, rowIndex);

    //Assert test
    assertArrayEquals(expectedPatternLine, testingPlayer.playerBoard.patternLines[rowIndex]);
  }

  @Test
  public void setPickedTiles_whenToFullPatternLine() {
    //Arrange test
    GameModel model = newModel(testPlayers);
    int rowIndex = 1;
    int overage = 2;
    List<ColorTile> testTiles = new ArrayList<>();
    for (int i = 0; i < rowIndex + overage; i++) {
      testTiles.add(new ColorTile(Color.BLACK));
    }
    model.selectedTiles = testTiles;
    ColorTile[] testingPatternLine = new ColorTile[rowIndex + 1];
    for (int i = testingPatternLine.length - 1; i >= 0; i--) { // full patternLine
      testingPatternLine[i] = new ColorTile(Color.BLACK);
    }
    Player testingPlayer = model.getPlayers().get(0);
    testingPlayer.playerBoard.patternLines[rowIndex] = testingPatternLine;
    ColorTile[] expectedPatternLine = Arrays.copyOf(testingPatternLine, testingPatternLine.length);

    //Act test
    model.setPickedTiles(testingPlayer, rowIndex);

    //Assert test
    assertArrayEquals(expectedPatternLine, testingPlayer.playerBoard.patternLines[rowIndex]);
  }

  @Test
  public void setPickedTiles_whenColorAlreadyOnWall() {
    //Arrange test
    GameModel model = newModel(testPlayers);
    Player testingPlayer = model.getPlayers().get(0);
    int rowIndex = 1;
    int overage = 2;
    List<ColorTile> testTiles = new ArrayList<>();
    for (int i = 0; i < rowIndex + overage; i++) {
      testTiles.add(new ColorTile(Color.BLACK));
    }
    model.selectedTiles = testTiles;
    testingPlayer.playerBoard.wall[rowIndex][testingPlayer.playerBoard.getColumnOnWall(Color.BLACK,
        rowIndex)] = true;
    ColorTile[] expectedPatternLine = Arrays.copyOf(
        testingPlayer.playerBoard.patternLines[rowIndex], rowIndex + 1);

    //Act test
    model.setPickedTiles(testingPlayer, rowIndex);

    //Assert test
    assertArrayEquals(expectedPatternLine, testingPlayer.playerBoard.patternLines[rowIndex]);
  }

  @Test
  public void hasSameColor_whenColorIsSame() {
    //Arrange test
    List<ColorTile> testTiles = new ArrayList<>();
    testTiles.add(new ColorTile(Color.BLACK));
    testTiles.add(new ColorTile(Color.BLACK));
    testTiles.add(new ColorTile(Color.BLACK));

    //Act test
    boolean result = GameModel.hasSameColor(testTiles);

    //Assert test
    assertTrue(result);
  }

  @Test
  public void hasSameColor_whenColorIsNotSame() {
    //Arrange test
    List<ColorTile> testTiles = new ArrayList<>();
    testTiles.add(new ColorTile(Color.BLACK));
    testTiles.add(new ColorTile(Color.RED));
    testTiles.add(new ColorTile(Color.BLACK));

    //Act test
    boolean result = GameModel.hasSameColor(testTiles);

    //Assert test
    assertFalse(result);
  }

}
