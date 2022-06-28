package de.lmu.ifi.sosylab.model;

import static java.util.Objects.requireNonNull;

import de.lmu.ifi.sosylab.model.Plate.SelectedAndRemainingTiles;
import de.lmu.ifi.sosylab.model.TableCenter.SelectedTilesAndMaybePenaltyTile;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Contains all game components on the table.
 */
public class GameModel {

  public static final int TILES_PER_COLOR = 20;
  public static final int TILES_PER_PLATE = 4;
  private static final int POINTS_PRO_ROW = 2;
  private static final int POINTS_PRO_COLUMN = 7;
  private static final int POINTS_PRO_COLOR = 10;
  private static final int[] PENALTY_POINTS = new int[]{0, -1, -2, -4, -6, -8, -11, -14};


  private final List<Player> players;

  private State state = State.RUNNING;

  private final PropertyChangeSupport support = new PropertyChangeSupport(this);

  private final List<Plate> plates;
  private final TableCenter tableCenter;
  private final List<ColorTile> bag = Arrays.stream(Color.values())
      .flatMap(color -> IntStream.range(0, TILES_PER_COLOR).mapToObj(i -> new ColorTile(color)))
      .collect(Collectors.toList());
  List<ColorTile> box = new ArrayList<>();

  private int startingPlayerIndex;
  private int playerToMoveIndex;
  private Player playerToMove;

  private int round = 1;

  private final Random random = new Random();

  /**
   * Creates a new table with game components.
   */
  public GameModel(List<Player> players) {
    if (players.size() < 2 || players.size() > 4) {
      throw new IllegalArgumentException("Invalid number of players, needs to be from 2 to 4");
    }
    tableCenter = new TableCenter();
    shuffleBag(); //shuffle Bag on Game Construction
    this.players = players;
    plates = createAndFillPlates();
    chooseRandomStartingPlayer();
    linkBoxToPlayerBoard();
  }

  private void linkBoxToPlayerBoard() {
    for (Player player : players) {
      player.playerBoard.box = this.box;
    }
  }

  /**
   * select random Player.
   */
  private void chooseRandomStartingPlayer() {
    startingPlayerIndex = playerToMoveIndex = random.nextInt(players.size());
    playerToMove = players.get(playerToMoveIndex);
  }

  /**
   * create Plates according to the amount of Players and fill them with Tiles.
   *
   * @return a List of filled Plates.
   */
  private List<Plate> createAndFillPlates() {
    int numberOfPlates = players.size() * 2 + 1;
    return IntStream.range(0, numberOfPlates)
        .mapToObj(i -> new Plate(getAndRemoveTilesFromBagForPlate()))
        .toList();
  }

  /**
   * Remove first TILES_PER_PLATE tiles from bag (which should already be shuffled).
   */
  private List<ColorTile> getAndRemoveTilesFromBagForPlate() {
    // TODO: check if enough tiles in Bag
    return IntStream.range(0, TILES_PER_PLATE).mapToObj(i -> bag.remove(0)).toList();
  }

  public void pickTilesFromPlate(Plate plate, Color color, Player player, int row) {
    SelectedAndRemainingTiles tiles = plate.pickTiles(color);
    if (!player.playerBoard.isColorAlreadyOnWall(color, row)) {
      player.playerBoard.addColorTilesToLine(tiles.selected(), row);
    }else {
      throw new IllegalArgumentException("Color tile is already on the wall!");
    }
    if (tiles.remaining().isPresent()) {
      tableCenter.addColorTiles(tiles.remaining().get());
    }
  }

  public void pickTilesFromTableCenter(Color color, Player player, int row) {
    SelectedTilesAndMaybePenaltyTile tiles = tableCenter.pickTiles(color);
    if (tiles.penaltyTile().isPresent()) {
      player.playerBoard.addTileToFloorLine(tiles.penaltyTile().get());
    }
    if (!player.playerBoard.isColorAlreadyOnWall(color, row)) {
      player.playerBoard.addColorTilesToLine(tiles.colorTiles(), row);
    }else {
      throw new IllegalArgumentException("Color tile is already on the wall!");
    }
  }


  public List<Plate> getPlates() {
    return plates;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public ArrayList<String> getPlayerNames() {
    ArrayList<String> names = new ArrayList<>();
    for (Player player : players) {
      names.add(player.getNickname());
    }
    return names;
  }

  private void moveFullPatternLineToBox(int row, PlayerBoard playerBoard) {
    // TODO: validation
    // TODO: tests
    box.addAll(List.of(playerBoard.patternLines[row]));
    Arrays.fill(playerBoard.patternLines[row], null);
  }

  /**
   * Moves color tiles from floor line to box and penalty tile to the table center.
   *
   * @param playerBoard the specified player board
   */
  private void moveFloorLineToBox(PlayerBoard playerBoard) {
    for (Tile tile : playerBoard.floorLine) {
      if (tile instanceof PenaltyTile) {
        tableCenter.addPenaltyTile((PenaltyTile) tile);
        continue;
      }
      box.add((ColorTile) tile);
    }
    playerBoard.floorLine.clear();
  }

  private void makeMove() {
    // move logic

    playerToMoveIndex = getNextPlayerIndex();
    playerToMove = players.get(playerToMoveIndex);
    if (playerToMoveIndex == startingPlayerIndex) {
      endRound();
    }
  }

  private void endRound() {
    // TODO: checks and table cleanup (move tiles to box, ...).
    round++;
    if (bag.isEmpty()) {
      moveBoxTilesToBagAndShuffle();
    }
  }

  private int getNextPlayerIndex() {
    int nextPlayerIndex = playerToMoveIndex + 1;
    return nextPlayerIndex == players.size() ? 0 : nextPlayerIndex;
  }

  private void moveBoxTilesToBagAndShuffle() {
    bag.addAll(box);
    box.clear();
    shuffleBag();
  }

  private void shuffleBag() {
    Collections.shuffle(bag, random);
  }

  private void calculateRoundScore() {
    List<Player> players = getPlayers();
    for (Player player : players) {
      ColorTile[][] lines = player.playerBoard.patternLines;
      int scoreDifference = 0;
      for (int row = 0; row < lines.length; row++) {
        if (player.playerBoard.getNextFreePatternLineIndex(row) == -1) {
          Color color = player.playerBoard.getPatternLineColor(row);
          player.playerBoard.addTileToWall(color, row);
          int column = player.playerBoard.getColumnOnWall(color, row);
          int scoreVerticalLinkedTiles = countVerticalLinkedTiles(row, column, player);
          int scoreHorizontalLinkedTiles = countHorizontalLinkedTiles(row, column, player);
          if (scoreVerticalLinkedTiles == 0 && scoreHorizontalLinkedTiles == 0) {
            scoreDifference += 1; // just tile itself
          } else {
            scoreDifference += scoreVerticalLinkedTiles + scoreHorizontalLinkedTiles;
          }
        }
      }
      scoreDifference += calculatePenaltyPoints(player);
      player.score += scoreDifference;
      if (player.score < 0) {
        player.score = 0;
      }
    }
  }

  private int countVerticalLinkedTiles(int row, int column, Player player) {
    // 1 point pro tile for linked tiles vertically
    if (row < 0 || row > PlayerBoard.WALL_SIZE || column < 0 || column > PlayerBoard.WALL_SIZE) {
      throw new IllegalArgumentException("Row and column must be within wall size.");
    }
    requireNonNull(player);

    int counter = 0;
    int currentRow = row - 1;
    while (currentRow >= 0 && player.playerBoard.wall[currentRow][column]) { //tiles from above
      counter++;
      currentRow--;
    }
    currentRow = row + 1;
    while (currentRow < PlayerBoard.WALL_SIZE
        && player.playerBoard.wall[currentRow][column]) { // tiles from below
      counter++;
      currentRow++;
    }
    if (counter > 0) {
      counter++; // tile itself is also linked
    }
    return counter;
  }

  private int countHorizontalLinkedTiles(int row, int column, Player player) {
    // 1 point pro tile for linked tiles horizontally
    if (row < 0 || row > PlayerBoard.WALL_SIZE || column < 0 || column > PlayerBoard.WALL_SIZE) {
      throw new IllegalArgumentException("Row and column must be within wall size.");
    }
    requireNonNull(player);

    int counter = 0;
    int currentCol = column - 1;
    while (currentCol >= 0 && player.playerBoard.wall[row][currentCol]) {
      counter++;
      currentCol--;
    }
    currentCol = column + 1;
    while (currentCol < PlayerBoard.WALL_SIZE && player.playerBoard.wall[row][currentCol]) {
      counter++;
      currentCol++;
    }
    if (counter > 0) {
      counter++; // tile itself is also linked
    }
    return counter;
  }

  private int calculatePenaltyPoints(Player player) {
    int numberOfTilesInFloorLine = player.playerBoard.floorLine.size();
    return PENALTY_POINTS[numberOfTilesInFloorLine];
  }


  private void calculateEndScore(Player player) {
    // TODO
    int comletedColumns = countCompletedColumns(player);
    int comletedRows = countCompletedRows(player);
    int completedColors = countCompletedColors(player);
    // 2 points / horizontal (row)
    // 7 points / vertical (column)
    // 10 points / color
    player.score += comletedRows * POINTS_PRO_ROW + comletedColumns * POINTS_PRO_COLUMN
        + completedColors * POINTS_PRO_COLOR;
  }

  private int countCompletedColumns(Player player) {
    // TODO
    return 0;
  }

  private int countCompletedRows(Player player) {
    // TODO
    return 0;
  }

  private int countCompletedColors(Player player) {
    // TODO
    return 0;
  }

  public void test() {
    ArrayList<ColorTile> redTile = new ArrayList<>();
    redTile.add(new ColorTile(Color.RED));
    redTile.add(new ColorTile(Color.RED));
    redTile.add(new ColorTile(Color.RED));
    redTile.add(new ColorTile(Color.RED));
    ArrayList<ColorTile> yellowTiles = new ArrayList<>();
    yellowTiles.add(new ColorTile(Color.YELLOW));
    yellowTiles.add(new ColorTile(Color.YELLOW));
    yellowTiles.add(new ColorTile(Color.YELLOW));
    yellowTiles.add(new ColorTile(Color.YELLOW));
    yellowTiles.add(new ColorTile(Color.YELLOW));
    ArrayList<ColorTile> blueTiles = new ArrayList<>();
    blueTiles.add(new ColorTile(Color.BLUE));
    blueTiles.add(new ColorTile(Color.BLUE));
    blueTiles.add(new ColorTile(Color.BLUE));
    blueTiles.add(new ColorTile(Color.BLUE));
    blueTiles.add(new ColorTile(Color.BLUE));

    getPlayers().get(0).playerBoard.wall[1][0] = true;
    getPlayers().get(0).playerBoard.wall[1][4] = true;
    getPlayers().get(0).playerBoard.wall[1][2] = true;
    getPlayers().get(0).playerBoard.wall[1][1] = true;
    getPlayers().get(0).playerBoard.addColorTilesToLine(redTile, 1);
    getPlayers().get(0).playerBoard.addColorTilesToLine(blueTiles, 4);

    calculateRoundScore();
  }

}
