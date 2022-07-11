package de.lmu.ifi.sosylab.model;

import static java.util.Objects.requireNonNull;

import de.lmu.ifi.sosylab.model.Plate.SelectedAndRemainingTiles;
import de.lmu.ifi.sosylab.model.TableCenter.SelectedTilesAndMaybePenaltyTile;
import java.beans.PropertyChangeListener;
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
  private static final String MODEL_CHANGED = "Model changed";
  private static final String MODEL_STATE_CHANGED = "Model state changed";
  private List<Player> players;
  private final PropertyChangeSupport support = new PropertyChangeSupport(this);
  private List<Plate> plates;
  private final TableCenter tableCenter;
  private final List<ColorTile> bag = Arrays.stream(Color.values())
      .flatMap(color -> IntStream.range(0, TILES_PER_COLOR).mapToObj(i -> new ColorTile(color)))
      .collect(Collectors.toList());
  private final Random random = new Random();
  List<ColorTile> box = new ArrayList<>();

  public State getState() {
    return state;
  }

  private State state;
  private RoundState roundState = RoundState.WAIT;
  private int startingPlayerIndex;
  private int playerToMoveIndex;

  /**
   * Getter for the player set as player to move.
   *
   * @return player instance
   */
  public Player getPlayerToMove() {
    List<Player> ptmList = new ArrayList<>();
    ptmList.add(playerToMove);
    List<Player> unmodPtmList = Collections.unmodifiableList(ptmList);
    return unmodPtmList.get(0);
  }
  private Player playerToMove;
  private int round = 1;
  List<ColorTile> selectedTiles = new ArrayList<>();
  Score score;

  /**
   * Creates a new table with game components.
   */
  public GameModel() {
    tableCenter = new TableCenter();
    shuffleBag(); //shuffle Bag on Game Construction
  }

  /**
   * Creates list of players from list of nicknames, assigns a player to start, creates plates
   * according to player number, links box to player, stets game stat to running and notifies
   * listeners about model state change.
   *
   * @param playerNames list of player nicknames
   */
  public void createPlayers(List<String> playerNames) {
    if (playerNames.size() < 2 || playerNames.size() > 4) {
      throw new IllegalArgumentException("Invalid number of players, needs to be from 2 to 4");
    }
    this.players = playerNames.stream().map(p -> new Player(p, PlayerState.READY))
        .collect(Collectors.toUnmodifiableList());
    plates = createAndFillPlates();
    chooseRandomStartingPlayer();
    linkBoxToPlayerBoard();
    this.state = State.RUNNING;
    score = new Score(players, box);
    notifyListeners(MODEL_STATE_CHANGED);
  }

  public void setState(State state) {
    this.state = state;
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
    playerToMove.setState(PlayerState.TO_MOVE);
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
   * Fills plates with color tiles from bag.
   */
  private void fillPlates() {
    for (Plate plate : plates) {
      plate.addTiles(getAndRemoveTilesFromBagForPlate());
    }
    notifyListeners(MODEL_CHANGED);
  }

  /**
   * Set tiles from selected tiles list to selected row.
   *
   * @param player  player to move
   * @param row     row to set
   * @return        true if success
   */
  // (patternLines (0-4) or floorLine (-1)
  public synchronized boolean setTiles(Player player, int row) {
    if (roundState != RoundState.PICKED) {
      return false;
    }
    if (player.getState() != PlayerState.TO_MOVE) {
      throw new IllegalArgumentException("\"set to row\" event from non-active player");
    }
    System.out.println("    Setting tiles to row " + row + "...");
    if (!setTilesToRow(player, row)) {
      return false;
    }
    setPlayerToMove(getNextPlayerIndex());
    if (!areThereMoreTiles()) {
      endRound();
      return true;
    }
    System.out.println(
        "Active Player: " + getPlayerToMoveIndex() + " State: " + playerToMove.getState());
    roundState = RoundState.WAIT;
    System.out.println("    roundState: " + roundState);
    return true;
  }

  private void setPlayerToMove(int newPlayerToMoveIndex) {
    playerToMove.setState(PlayerState.READY);
    playerToMoveIndex = newPlayerToMoveIndex;
    playerToMove = players.get(playerToMoveIndex);
    playerToMove.setState(PlayerState.TO_MOVE);
    notifyListeners(MODEL_CHANGED);
  }

  private boolean areThereMoreTiles() {
    return tableCenter.getTiles().size() != 0 || areThereMoreTilesOnPlates();
  }

  private boolean areThereMoreTilesOnPlates() {
    for (Plate plate : plates) {
      if (plate.getState() == PlateState.FULL) {
        return true;
      }
    }
    return false;
  }

  public void endRound() {
    // the tiles from the patternLines and the floorLine are being moved during the scoring.
    score.calculateRoundScore();
    if (hasCompleteWallRow()) {
      endGame();
      return;
    }
    round++;
    setPlayerToMove(startingPlayerIndex);
    fillPlates();
    tableCenter.addPenaltyTile(new PenaltyTile());
    roundState = RoundState.WAIT;
    notifyListeners(MODEL_CHANGED);
  }



  private void endGame() {

    score.calculateEndScore();
    roundState = RoundState.FINISHED;
    state = State.FINISHED;
    notifyListeners(MODEL_STATE_CHANGED);
  }



  /**
   * Checks if any of the players have the completed wall row.
   *
   * @return true if the specified player has a complete wall row
   */
  private boolean hasCompleteWallRow() {
    for (Player player : getPlayers()) {
      if (hasCompleteWallRow(player)) {
        System.out.println("WALL ROW COMPLETED");
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if the specified player has a complete wall row.
   *
   * @param player the specified player
   * @return true if the specified player has a complete wall row
   */
  private boolean hasCompleteWallRow(Player player) {
    requireNonNull(player);
    for (boolean[] row : player.playerBoard.wall) {
      int completeTiles = 0;
      for (boolean tile : row) {
        if (tile) {
          completeTiles++;
        }
      }
      if (completeTiles == PlayerBoard.WALL_SIZE) {
        return true;
      }
    }
    return false;
  }

  public int getPlayerToMoveIndex() {
    return playerToMoveIndex;
  }

  private int getNextPlayerIndex() {
    int nextPlayerIndex = playerToMoveIndex + 1;
    return nextPlayerIndex == players.size() ? 0 : nextPlayerIndex;
  }

  /**
   * Remove first TILES_PER_PLATE tiles from bag (which should already be shuffled).
   */
  private List<ColorTile> getAndRemoveTilesFromBagForPlate() {
    int numberOfRemainingTiles = bag.size();
    if (numberOfRemainingTiles < TILES_PER_PLATE) {
      List<ColorTile> tilesToAdd = new ArrayList<>();
      for (int i = numberOfRemainingTiles - 1; i >= 0; i--) {
        tilesToAdd.add(bag.remove(i));
      }
      moveBoxTilesToBagAndShuffle();
      for (int i = TILES_PER_PLATE - numberOfRemainingTiles - 1; i >= 0; i--) {
        tilesToAdd.add(bag.remove(i));
      }
      return tilesToAdd;
    } else {
      return IntStream.range(0, TILES_PER_PLATE).mapToObj(i -> bag.remove(0)).toList();
    }
  }

  /**
   * Takes a selected color tile from a plate and saves it as selected tiles.
   *
   * @param plate plate the tile was picked from
   * @param color color of the tile
   * @return true if tiles was successfully added to selected tiles
   */
  public boolean pickTilesFromPlate(Plate plate, Color color) {
    if (selectedTiles.size() != 0) {
      return false;
    }
    SelectedAndRemainingTiles tiles = plate.pickTiles(color);
    selectedTiles.addAll(tiles.selected());
    if (tiles.remaining().isPresent()) {
      tableCenter.addColorTiles(tiles.remaining().get());
    }
    roundState = RoundState.PICKED;
    System.out.println("    roundState: PICKED " + selectedTiles + " tiles");
    notifyListeners(MODEL_CHANGED);
    return true;
  }

  /**
   * Takes a selected color tile from table center and saves it as selected tiles. Awards the player
   * with the penalty tile and sets the next starting player if the penalty tile is still on the
   * table (first pick).
   *
   * @param color  color of the tile
   * @param player player to move
   */
  public boolean pickTilesFromTableCenter(Color color, Player player) {
    if (selectedTiles.size() != 0) { // if selectedTiles already has tiles
      return false;
    }
    SelectedTilesAndMaybePenaltyTile tiles = tableCenter.pickTiles(color);
    if (tiles.penaltyTile().isPresent()) {
      player.playerBoard.addTileToFloorLine(tiles.penaltyTile().get());
      startingPlayerIndex = players.indexOf(player);
    }
    selectedTiles.addAll(tiles.colorTiles());
    roundState = RoundState.PICKED;
    System.out.println("    roundState: PICKED " + selectedTiles + " tiles");
    notifyListeners(MODEL_CHANGED);
    return true;
  }

  /**
   * Sets picked tiles to the specified line (pattern line or floor line). Returns true if the
   * validation was successful and tiles were set.
   *
   * @param player player to move
   * @param row    the row (-1 for floor line or 0 - 4 for pattern lines)
   * @return true if the validation was successful and tiles were set.
   */
  public boolean setTilesToRow(Player player, int row) {
    requireNonNull(player);
    if (row < -1 || row > PlayerBoard.WALL_SIZE - 1) {
      throw new IllegalArgumentException(
          "    Invalid row number, needs to be from -1 to " + (PlayerBoard.WALL_SIZE - 1));
    }
    if (selectedTiles.size() == 0) {
      throw new IllegalArgumentException(
          "    Trying to add an empty list of tiles to the patternLine.");
    }
    if (!hasSameColor(selectedTiles)) {
      System.out.println(selectedTiles);
      throw new IllegalArgumentException("    Selected tiles have different color");
    }
    if (row == -1) {
      System.out.println("    addTileToFloorLine");
      for (ColorTile tile : selectedTiles) {
        player.playerBoard.addTileToFloorLine(tile);
      }
      selectedTiles.clear();
      return true;
    }
    if (player.playerBoard.countFreeFieldsInRow(row) == 0) {
      // the pattern line is full
      System.out.println("    the pattern line is full");
      return false;
    }
    if (player.playerBoard.isColorAlreadyOnWall(selectedTiles.get(0).getColor(), row)) {
      // the color is already on the wall
      System.out.println("    the color is already on the wall");
      return false;
    }
    if (player.getPlayerBoard().countFreeFieldsInRow(row) < player.getPlayerBoard()
        .getPatternLines()[row].length
        && selectedTiles.get(0).getColor() != player.playerBoard.getPatternLineColor(row)) {
      // the line already has tiles with another color
      System.out.println("    the line already has tiles with another color "
          + player.playerBoard.getPatternLineColor(row));
      return false;
    }
    player.playerBoard.addColorTilesToLine(selectedTiles, row);
    selectedTiles.clear();
    return true;
  }

  /**
   * Checks if the Color Tiles have the same color.
   *
   * @param tiles the list of Color Tiles to be checked
   * @return true when the Color Tiles have same color
   */
  static boolean hasSameColor(List<ColorTile> tiles) {
    Color color = tiles.get(0).getColor();
    for (ColorTile tile : tiles) {
      if (tile.getColor() != color) {
        return false;
      }
    }
    return true;
  }

  public List<Plate> getPlates() {
    List<Plate> unmodifiablePlateList = Collections.unmodifiableList(plates);
    return unmodifiablePlateList;
  }

  public List<Player> getPlayers() {
    List<Player> unmodifiablePlayersList = Collections.unmodifiableList(players);
    return unmodifiablePlayersList;
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


  private void shuffleBag() {
    Collections.shuffle(bag, random);
  }
  private void moveBoxTilesToBagAndShuffle() {
    bag.addAll(box);
    box.clear();
    shuffleBag();
    notifyListeners(MODEL_CHANGED);
  }


  public TableCenter getTableCenter() {
    List<TableCenter> tcList = new ArrayList<>();
    tcList.add(tableCenter);
    List<TableCenter> unmodTcList = Collections.unmodifiableList(tcList);
    return unmodTcList.get(0);
  }



  public RoundState getRoundState() {
    return roundState;
  }

  public int getRound() {
    return round;
  }

  /**
   * Add a {@link PropertyChangeListener} to the model to get notified about any changes that the
   * the model publishes.
   *
   * @param pcl the view that subscribes itself to the model.
   */
  public void addPropertyChangeListener(PropertyChangeListener pcl) {
    requireNonNull(pcl);
    support.addPropertyChangeListener(pcl);
  }


  /**
   * Invokes the model to fire a new event, such that any attached observer (i.e.,
   * {@link PropertyChangeListener}) gets notified about a change in this model.
   */
  private void notifyListeners(String state) {
    support.firePropertyChange(state, null, this);
  }

}
