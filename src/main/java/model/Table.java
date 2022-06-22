package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class Table {

  public static final int PLATES_2_PLAYERS = 5;
  public static final int PLATES_3_PLAYERS = 7;
  public static final int PLATES_4_PLAYERS = 9;
  public static final int TILES_PER_COLOR = 20;
  public static final int TILES_PER_PLATE = 4;

  private ArrayList<Tile>[] plates;
  ArrayList<Tile> tableCenter;
  ArrayList<ColorTile> bag;
  HashSet<ColorTile> box;
  PlayerBoard[] playerBoards;

  ArrayList<ColorTile> selectedTiles = new ArrayList<>();



  public Table(ArrayList<String> players){
    tableCenter = new ArrayList<>();
    tableCenter.add(new PenaltyTile());
    bag = createBag();
    box = new HashSet<>();
    playerBoards = createPlayerBoards(players);
    plates = createPlates(players.size());
  }


  private ArrayList<ColorTile> createBag() {
    ArrayList<ColorTile> bag = new ArrayList<>();
    for (int i = 0; i < TILES_PER_COLOR; i++) {
      bag.add(new ColorTile(Color.BLACK));
      bag.add(new ColorTile(Color.YELLOW));
      bag.add(new ColorTile(Color.RED));
      bag.add(new ColorTile(Color.BLUE));
      bag.add(new ColorTile(Color.WHITE));
    }
    return bag;
  }

  private PlayerBoard[] createPlayerBoards(ArrayList<String> players) {
    PlayerBoard[] playerBoards = new PlayerBoard[players.size()];
    for (int i = 0; i < players.size(); i++) {
      playerBoards[i] = new PlayerBoard(players.get(i), this);
    }
    return playerBoards;
  }


  private ArrayList<Tile>[] createPlates(int size) {
    int numberOfPlates;
    switch(size){
      case 2:
        numberOfPlates = PLATES_2_PLAYERS;
        break;
      case 3:
        numberOfPlates = PLATES_3_PLAYERS;
        break;
      case 4:
        numberOfPlates = PLATES_4_PLAYERS;
        break;
      default:
        throw new IllegalArgumentException("Wrong number of players.");
    }
    ArrayList<Tile>[] plates = new ArrayList[numberOfPlates]; // << !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // TODO: validation (if the bag has not enough tiles, ect.)
    for(int i = 0; i < plates.length; i++ ){
      plates[i] = new ArrayList<>();
      for (int j = 0; j < TILES_PER_PLATE; j++){
        plates[i].add(pickRandomTile(bag));
      }
    }
    return plates;
  }

  private Tile pickRandomTile(ArrayList<ColorTile> tiles){
    // TODO: validation (if the bag is empty, ect.)
    int size = tiles.size();
    int randomIndex = (int) (Math.random() * size);
    return tiles.remove(randomIndex);
  }

  public ArrayList<ColorTile> pickSameColorTiles(ArrayList<Tile> tiles, Color color){
    // TODO add validation
    ArrayList<ColorTile> sameColorTiles = new ArrayList<>();
    for(Tile tile : tiles) {
      if(tile instanceof PenaltyTile) {
        continue;
      }
      if(((ColorTile) tile).getColor() == color ) {
        sameColorTiles.add((ColorTile) tile);
      }
    }
    return sameColorTiles;
  }

  //temp test
  public ArrayList<Tile>[] getPlates(){ // Tile[][] ?
    // TODO: return copy of plates
    return plates;
  }

  private void moveFullPatternLineToBox(int row, PlayerBoard playerBoard) {
    // TODO: validation
    // TODO: tests
    ColorTile[] fullPatternLine = playerBoard.patternLines[row];
    ArrayList<ColorTile> tilesList = (ArrayList<ColorTile>) Arrays.asList(fullPatternLine);
    tilesList.remove(0); // One Tile remains in the wall
    box.addAll(tilesList);
    Arrays.fill(fullPatternLine, null);
  }

  /**
   * Moves color tiles from floor line to box and penalty tile to the table center.
   *
   * @param playerBoard
   */
  private void moveFloorLineToBox(PlayerBoard playerBoard){
    // TODO: validation
    // TODO: tests
    for (Tile tile : playerBoard.floorLine) {
      if(tile instanceof PenaltyTile) {
        tableCenter.add(tile);
        continue;
      }
      box.add((ColorTile) tile);
    }
    playerBoard.floorLine.clear();
  }

  private void moveBoxToBag() {
    // TODO: validation
    // TODO: tests
    bag.addAll(box);
    box.clear();
  }
}
