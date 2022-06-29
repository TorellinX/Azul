package de.lmu.ifi.sosylab.model;

import java.util.Arrays;
import java.util.List;

/**
 * Player board representing the game Azul. It contains the pattern lines where tiles can be placed,
 * the wall, which is to be tiled and the floor line, which collects penalties.
 */
public class PlayerBoard {

  private static final int WALL_SIZE = 5;

  private int score;
  ColorTile[][] patternLines;
  boolean[][] wall;
  List<Tile> floorLine;
  private List<ColorTile> pickedTiles;


  public PlayerBoard() {
    patternLines = new ColorTile[5][];

  }

  /**
   * Calculates the number of fields in a pattern line row, which are not occupied, yet.
   *
   * @param rowIndex pattern line index
   * @return number of free fields
   */
  public int countFreeFieldsInRow(int rowIndex) {
    ColorTile[] row = patternLines[rowIndex];
    for (int i = 0; i < row.length; i++) {
      if (row[i] == null) {
        return row.length - i;
      }
    }
    return 0;
  }

  /**
   * Calculate the index for the pattern lines and the floor line. The pattern line indices are
   * standard array indices whereas the floor line is identified by an index value of -1.
   *
   * @param row row index considering the floor line as last row of a standard array
   * @return array row index for pattern lines and -1 for floor line
   */
  public int patternLineIndex(int row) {
    for (int i = 0; i < patternLines[row].length; i++) {
      if (patternLines[row][i] == null) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Getter for the color of some tile already placed in a pattern line.
   *
   * @param row row index of the addressed pattern line (>= 0)
   * @return color of already placed tiles. 'null' if line is empty
   */
  public Color getPatternLineColor(int row) {
    // TODO: validation (Line must be not empty, etc.)
    // TODO: tests
    Color color = patternLines[row][patternLines[row].length - 1].getColor();
    return color;
  }

  private void addTileToWall(Color color, int row) {
    //TODO: add validation
    // TODO: tests
    int column = (row + color.ordinal()) % WALL_SIZE;
    wall[row][column] = true;
  }


  private boolean isColorAlreadyOnWall(Color color, int row) {
    //TODO: add validation
    // TODO: tests
    int column = (row + color.ordinal()) % WALL_SIZE;
    return wall[row][column];
  }

  public int getScore() {
    int scoreCopy = score;
    return scoreCopy;
  }

  /**
   * Getter for the full pattern array (2D).
   *
   * @return pattern line array of ColorTile type
   */
  public ColorTile[][] getPatternLines() {
    ColorTile[][] patternLinesCopy = new ColorTile[patternLines.length][];
    for (int i = 0; i < patternLines.length; i++) {
      patternLinesCopy[i] = Arrays.copyOf(patternLines[i],
          patternLines[i].length); // Tiles are references!
    }
    return patternLinesCopy;
  }

  /**
   * Getter for the wall to be filled with with tiles. Indicates if an array field carries a tile.
   *
   * @return boolean array
   */
  public boolean[][] getWall() {
    boolean[][] wallCopy = new boolean[wall.length][];
    for (int i = 0; i < wall.length; i++) {
      wallCopy[i] = Arrays.copyOf(wall[i], wall[0].length);
    }
    return wall;
  }

  public List<Tile> getFloorLine() {
    return floorLine;
  }


  /**
   * Fills selected tiles into a pattern line. Surplus tiles are transferred to the floor line.
   * Not matching color tiles are transferred to floor line. If row is already full, or color is
   * already on the wall, an argument exception is thrown.
   * (please correct in case of to do's below...)
   *
   * @param tiles color tiles to be added to the pattern line
   * @param rowIndex of the addressed pattern line
   */
  public void addColorTilesToLine(List<ColorTile> tiles, int rowIndex) {
    int freeFields = countFreeFieldsInRow(rowIndex);
    if (freeFields == 0) {
      throw new IllegalArgumentException("Row is full");
      // TODO: If row is full, aren't tiles transferred to the floor line instead of exception?
    }
    if (isColorAlreadyOnWall(tiles.get(0).getColor(), rowIndex)) {
      throw new IllegalArgumentException("Color already on wall");
    }
    if (rowIndex == -1) {
      for (Tile tile : tiles) {
        addTileToFloorLine(tile);
      }
    }
    if (tiles.get(0).getColor() == getPatternLineColor(rowIndex)
        || patternLineIndex(rowIndex) == 0) {
      //TODO: shouldn't color mismatch be an illegal argument exception?
      ColorTile[] row = patternLines[rowIndex];
      for (int i = 0; i < tiles.size(); i++) {
        if (freeFields >= tiles.size()) {
          row[row.length - freeFields + i] = tiles.get(i);
          freeFields--;
        } else {
          addTileToFloorLine(tiles.get(i));
        }
      }
    }
  }

  public void addTileToFloorLine(Tile tile) {
    floorLine.add(tile);
  }

}
