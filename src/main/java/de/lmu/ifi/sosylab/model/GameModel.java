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
 * Contais all game components on the table.
 */
public class GameModel {

  public static final int TILES_PER_COLOR = 20;
  public static final int TILES_PER_PLATE = 4;

  String GAME_STARTED = "Game started";
  String MODEL_STATE_CHANGED = "Model state changed";
  String NEW_DATA = "New data";
  String NOT_ALLOWED = "Not allowed";
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


  public void addPropertyChangeListener(PropertyChangeListener listener) {
    requireNonNull(listener);
    support.addPropertyChangeListener(listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {
    requireNonNull(listener);
    support.removePropertyChangeListener(listener);
  }

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

    // intended use: Game setup completed, paint board!
    notifyListeners(GAME_STARTED);
  }

  private void notifyListeners(String property) {
    support.firePropertyChange(property, null, this);
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

  // This method should be split into a pick method without argument "row" returning some
  // collection of picked tiles and a common place method to place the tiles with argument "lineIndex"
  public void pickTilesFromPlate(Plate plate, Color color, Player player, int row) {
    //TODO: Tiles present check
    SelectedAndRemainingTiles tiles = plate.pickTiles(color);
    player.playerBoard.addColorTilesToLine(tiles.selected(), row);
    if (tiles.remaining().isPresent()) {
      tableCenter.addColorTiles(tiles.remaining().get());
    }

    // intended use: notify GUI to allow for place event, as now tiles to place are available
    notifyListeners(NEW_DATA);
  }

  // This method should be split into a pick method without argument "row" returning some
  // collection of picked tiles and a common place method to place the tiles with argument "lineIndex"
  public void pickTilesFromTableCenter(Color color, Player player, int row) {
    //TODO: Tiles present check
    SelectedTilesAndMaybePenaltyTile tiles = tableCenter.pickTiles(color);
    if (tiles.penaltyTile().isPresent()) {
      player.playerBoard.addTileToFloorLine(tiles.penaltyTile().get());
    }
    player.playerBoard.addColorTilesToLine(tiles.colorTiles(), row);

    // intended use: notify GUI to allow for place event, as now tiles to place are available
    notifyListeners(NEW_DATA);
  }

  public void placeTiles (int lineIndex) {
    // TODO: method to place tiles and making all the required checks and actions:
    // line empty?
    // color allowed?
    // count free fields, fill tiles and push rest to floorline
    // ...?

    // intended use: ready for board update or placing error => repaint or notify user and repeat input
    if (/* the place action is ok -> */ true) {
      notifyListeners(MODEL_STATE_CHANGED);
    } else {  // if place not possible, notify "repeat"
      notifyListeners(NOT_ALLOWED);
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

    // intended use: this method to close one evaluation step during scoring => thereafter: repaint!
    notifyListeners(MODEL_STATE_CHANGED);
  }

  /**
   * Moves color tiles from floor line to box and penalty tile to the table center.
   *
   * @param playerBoard the spicified player board
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

    // intended use: this method to close one evaluation step during scoring => thereafter: repaint!
    notifyListeners(MODEL_STATE_CHANGED);
  }

  private void makeMove() {
    // move logic

    playerToMoveIndex = getNextPlayerIndex();
    playerToMove = players.get(playerToMoveIndex);
    // the following if does not seem correct, as a round ends when all tiles are picked.
    if (playerToMoveIndex == startingPlayerIndex) {
      endRound();
    }

    // no listener notification, as those are set with more detail in the move-methods.
    // this concept may be subject to discussion.
  }

  // This method needs reconsidering, see "makeMove()"
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
}
