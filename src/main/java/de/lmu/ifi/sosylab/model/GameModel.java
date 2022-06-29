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
  private final PropertyChangeSupport support = new PropertyChangeSupport(this);
  private final List<Plate> plates;
  private final TableCenter tableCenter;
  private final List<ColorTile> bag = Arrays.stream(Color.values())
      .flatMap(color -> IntStream.range(0, TILES_PER_COLOR).mapToObj(i -> new ColorTile(color)))
      .collect(Collectors.toList());
  private final Random random = new Random();
  List<ColorTile> box = new ArrayList<>();
  private State state = State.RUNNING;
  private int startingPlayerIndex;
  private int playerToMoveIndex;
  private Player playerToMove;
  private int round = 1;

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
   * Select random Player.
   */
  private void chooseRandomStartingPlayer() {
    startingPlayerIndex = playerToMoveIndex = random.nextInt(players.size());
    playerToMove = players.get(playerToMoveIndex);
  }

  /**
   * Create Plates according to the amount of Players and fill them with Tiles.
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

  /**
   * Takes a selected color tile from a plate and places it in the selected row.
   *
   * @param plate plate the tile was picked from
   * @param color color of the tile
   * @param player player to move
   * @param row row to place the tile
   */
  public void pickTilesFromPlate(Plate plate, Color color, Player player, int row) {
    //TODO: Tiles present check
    SelectedAndRemainingTiles tiles = plate.pickTiles(color);
    player.playerBoard.addColorTilesToLine(tiles.selected(), row);
    if (tiles.remaining().isPresent()) {
      tableCenter.addColorTiles(tiles.remaining().get());
    }
  }

  /**
   * Takes a selected color tile from table center and places it in the selected row.
   * Awards the player with the penalty tile if present (first pick).
   *
   * @param color color of the tile
   * @param player player to move
   * @param row row to place the tile
   */
  public void pickTilesFromTableCenter(Color color, Player player, int row) {
    //TODO: Tiles present check
    SelectedTilesAndMaybePenaltyTile tiles = tableCenter.pickTiles(color);
    if (tiles.penaltyTile().isPresent()) {
      player.playerBoard.addTileToFloorLine(tiles.penaltyTile().get());
    }
    player.playerBoard.addColorTilesToLine(tiles.colorTiles(), row);
  }

  public List<Plate> getPlates() {
    return plates;
  }

  public List<Player> getPlayers() {
    return players;
  }

  /**
   * Provides the nicknames of the players.
   *
   * @return list of strings
   */
  public ArrayList<String> getPlayerNames() {
    ArrayList<String> names = new ArrayList<>();
    for (Player player : players) {
      names.add(player.getNickname());
    }
    return names;
  }

  /**
   * Moves all tiles except one from the specified complete patternLine to the box. One tile remains
   * on the wall.
   *
   * @param row         the specified complete patternLine
   * @param playerBoard the player board with the complete patternLine
   */
  void moveFullPatternLineToBox(int row, PlayerBoard playerBoard) {
    requireNonNull(playerBoard);
    if (row > PlayerBoard.WALL_SIZE) {
      throw new IllegalArgumentException("Row Index must be within the wall size.");
    }
    if (row < 0) {
      throw new IllegalArgumentException("Row index must be positive");
    }
    List<ColorTile> tiles = new ArrayList<>(Arrays.asList(playerBoard.patternLines[row]));
    for (ColorTile tile : tiles) {
      if (tile == null) {
        throw new RuntimeException("The patten line must be complete");
      }
    }
    tiles.remove(tiles.size() - 1); // one tile remains on the wall
    box.addAll(tiles);
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
    // the tiles from the patternLines and the floorLine are being moved during the scoring.
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

  /**
   * Combines "Wall-tiling" and "Scoring" round phases for each player. For each complete line the
   * space of the same color in the corresponding line of the wall becomes taken, points are
   * immediately scored. The remaining tiles from the completed line are moved to the box. At the
   * end of the Wall-tiling phase, penalty points from the floor line are scored.
   */
  private void calculateRoundScore() {
    List<Player> players = getPlayers();
    for (Player player : players) {
      ColorTile[][] lines = player.playerBoard.patternLines;
      int scoreDifference = 0;
      for (int row = 0; row < lines.length; row++) {
        if (player.playerBoard.getNextFreePatternLineIndex(row) == -1) {
          Color color = player.playerBoard.getPatternLineColor(row);
          if (player.playerBoard.isColorAlreadyOnWall(color, row)) {
            throw new RuntimeException("The space on the wall is already taken. This row #"
                + row
                + " of patternLine should not have been filled with tiles of this color "
                + color.name());
          }
          player.playerBoard.addTileToWall(color, row);
          int column = player.playerBoard.getColumnOnWall(color, row);
          int scoreVerticalLinkedTiles = countVerticalLinkedTiles(row, column, player);
          int scoreHorizontalLinkedTiles = countHorizontalLinkedTiles(row, column, player);
          if (scoreVerticalLinkedTiles == 0 && scoreHorizontalLinkedTiles == 0) {
            scoreDifference += 1; // just the tile itself
          } else {
            scoreDifference += scoreVerticalLinkedTiles + scoreHorizontalLinkedTiles;
          }
          moveFullPatternLineToBox(row, player.playerBoard);
        }
      }
      scoreDifference += calculatePenaltyPoints(player);
      moveFloorLineToBox(player.playerBoard);
      player.score += scoreDifference;
      if (player.score < 0) {
        player.score = 0; // the score can never drop below 0 points.
      }
    }
  }

  /**
   * Calculates the number of vertically linked to the placed tile tiles for the specified player.
   *
   * @param row    the row in which the tile was placed
   * @param column the column in which the tile was placed
   * @param player the player who owns this player board
   * @return the number of vertically linked tiles
   * @throws IllegalArgumentException if the row or the column index is outside of wall dimensions.
   */
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

  /**
   * Calculates the number of horizontally linked to the placed tile tiles for the specified
   * player.
   *
   * @param row    the row in which the tile was placed
   * @param column the column in which the tile was placed
   * @param player the player who owns this player board
   * @return the number of horizontally linked tiles
   * @throws IllegalArgumentException if the row or the column index is outside of wall dimensions.
   */
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

  /**
   * Calculates penalty points from the floor line for the specified player.
   *
   * @param player the player who owns this player board
   * @return penalty points
   */
  private int calculatePenaltyPoints(Player player) {
    requireNonNull(player);
    int numberOfTilesInFloorLine = player.playerBoard.floorLine.size();
    return PENALTY_POINTS[numberOfTilesInFloorLine];
  }

  /**
   * Scores additional points for each player at the end of the game: - gain 2 points for each
   * complete horizontal line on the wall. - gain 7 points for each complete vertical line on the
   * wall. - gain 10 points for each each color of which the player has place 5 tiles an the wall.
   */
  private void calculateEndScore() {
    for (Player player : getPlayers()) {
      int comletedColumns = countCompleteColumns(player);
      int comletedRows = countCompleteRows(player);
      int completedColors = countCompleteColors(player);
      // 2 points / horizontal (row)
      // 7 points / vertical (column)
      // 10 points / color
      player.score += comletedRows * POINTS_PRO_ROW + comletedColumns * POINTS_PRO_COLUMN
          + completedColors * POINTS_PRO_COLOR;
    }
  }

  /**
   * Calculates the number of complete vertical lines for the specified player.
   *
   * @param player the player who owns this player board
   * @return the number of complete vertical lines
   */
  private int countCompleteColumns(Player player) {
    requireNonNull(player);
    int counterCompleteColumns = 0;
    for (int col = 0; col < PlayerBoard.WALL_SIZE; col++) {
      int completesTiles = 0;
      for (int row = 0; row < PlayerBoard.WALL_SIZE; row++) {
        if (player.playerBoard.wall[row][col]) {
          completesTiles++;
        }
      }
      if (completesTiles == PlayerBoard.WALL_SIZE) {
        counterCompleteColumns++;
      }
    }
    System.out.println("Completed Columns: " + counterCompleteColumns);
    return counterCompleteColumns;
  }

  /**
   * Calculates the number of complete horizontal lines for the specified player.
   *
   * @param player the player who owns this player board
   * @return the number of complete horizontal lines
   */
  private int countCompleteRows(Player player) {
    requireNonNull(player);
    int counterCompleteRows = 0;
    for (boolean[] row : player.playerBoard.wall) {
      int completesTiles = 0;
      for (boolean tile : row) {
        if (tile) {
          completesTiles++;
        }
      }
      if (completesTiles == PlayerBoard.WALL_SIZE) {
        counterCompleteRows++;
      }
    }
    return counterCompleteRows;
  }

  /**
   * Calculates the number of complete colors for the specified player.
   *
   * @param player the player who owns this player board
   * @return the number of complete colors
   */
  private int countCompleteColors(Player player) {
    requireNonNull(player);
    int counterCompleteColors = 0;
    for (Color color : Color.values()) {
      int tilesOfColor = 0;
      for (int row = 0; row < PlayerBoard.WALL_SIZE; row++) {
        int column = player.playerBoard.getColumnOnWall(color, row);
        if (player.playerBoard.wall[row][column]) {
          tilesOfColor++;
        }
      }
      if (tilesOfColor == PlayerBoard.WALL_SIZE) {
        counterCompleteColors++;
      }
    }
    return counterCompleteColors;
  }
}
