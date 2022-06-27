package de.lmu.ifi.sosylab.model;

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
 * Contais all game components on the table.
 */
public class GameModel {

  public static final int TILES_PER_COLOR = 20;
  public static final int TILES_PER_PLATE = 4;



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
    return IntStream.range(0, TILES_PER_PLATE).mapToObj(i -> bag.remove(0)).toList();
  }

  public void pickTilesFromPlate(Plate plate, Color color, Player player, int row) {
    SelectedAndRemainingTiles tiles = plate.pickTiles(color);
    player.playerBoard.addColorTilesToLine(tiles.selected(), row);
    if (tiles.remaining().isPresent()) {
      tableCenter.addColorTiles(tiles.remaining().get());
    }
  }

  public void pickTilesFromTableCenter(Color color, Player player, int row) {
   SelectedTilesAndMaybePenaltyTile tiles =  tableCenter.pickTiles(color);
   player.playerBoard.addColorTilesToLine(tiles.colorTiles(), row);
   if(tiles.penaltyTile().isPresent()){
     player.playerBoard.addTileToFloorLine(tiles.penaltyTile().get());
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
    for (Player player: players) {
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
    if(bag.isEmpty()){
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

  public TableCenter getTableCenter() {
    return tableCenter;
  }
}
