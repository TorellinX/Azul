package de.lmu.ifi.sosylab.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Player board representing the game Azul. It contains the pattern lines where tiles can be placed,
 * the wall, which is to be tiled and the floor line, which collects penalties.
 */
public class PlayerBoard {

  static final int WALL_SIZE = 5;
  static final int FLOORLINE_SIZE = 7;

  private int score;
  ColorTile[][] patternLines;
  boolean[][] wall = new boolean[WALL_SIZE][WALL_SIZE];
  List<Tile> floorLine = new ArrayList<>(7);  // 7 just optimizes, does not limit the maximum size.
  private List<ColorTile> pickedTiles;
  List<ColorTile> box;


  public PlayerBoard() {
    patternLines = createPatternLines();
  }

  private ColorTile[][] createPatternLines() {
    patternLines = new ColorTile[WALL_SIZE][];
    for (int i = 0; i < WALL_SIZE; i++) {
      patternLines[i] = new ColorTile[i + 1];
    }
    return patternLines;
  }

  /**
   * Calculates the number of fields in a pattern line row, which are not occupied, yet.
   *
   * @param rowIndex pattern line index
   * @return number of free fields
   */
  public int countFreeFieldsInRow(int rowIndex) {
    if (rowIndex > WALL_SIZE) {
      throw new IllegalArgumentException("Row index must be within the wall size.");
    }
    if (rowIndex < 0) {
      throw new IllegalArgumentException("Row index must be positive");
    }
    ColorTile[] row = patternLines[rowIndex];
    for (int i = row.length - 1; i >= 0; i--) {
      if (row[i] == null) {
        return i + 1;
      }
    }
    return 0;
  }

  /**
   * Returns the next index of a pattern line indicated by row. (?)
   *
   * @param row pattern line index
   * @return the next free index in pattern line or -1
   */
  public int getNextFreePatternLineIndex(int row) {
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
    if (patternLines[row][patternLines[row].length - 1] == null) {
      return null;
    }
    Color color = patternLines[row][patternLines[row].length - 1].getColor();
    return color;
  }

  /**
   * The specified tile is added to the space of the same color in the specified line of the wall.
   *
   * @param color the color of the specified tile
   * @param row   the specified line of the wall
   */
  void addTileToWall(Color color, int row) {
    //TODO: add validation
    // TODO: tests
    int column = (row + color.ordinal()) % WALL_SIZE;
    wall[row][column] = true;
  }


  boolean isColorAlreadyOnWall(Color color, int row) {
    //TODO: add validation
    // TODO: tests
    int column = getColumnOnWall(color, row);
    return wall[row][column];
  }

  int getColumnOnWall(Color color, int row) {
    return (row + color.ordinal()) % WALL_SIZE;
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
   * Adds selected ColorTiles of the same color to the specified row from right to left. If there
   * are more tiles than there are free spaces in a row, remaining tiles will be added to the
   * floorLine.
   *
   * @param tiles    tiles to be added
   * @param rowIndex row of the PatternLines
   */
  public void addColorTilesToLine(List<ColorTile> tiles, int rowIndex) {
    int freeFields = countFreeFieldsInRow(rowIndex);
    if (tiles.size() == 0) {
      throw new IllegalArgumentException(
          "Trying to add an empty list of tiles to the patternLine.");
      // TODO: If row is full, aren't tiles transferred to the floor line instead of exception?
    }
    if (freeFields == 0) {
      throw new IllegalArgumentException("Row is full");
    }
    if (isColorAlreadyOnWall(tiles.get(0).getColor(), rowIndex)) {
      throw new IllegalArgumentException("Color already on wall");
    }
    if (rowIndex == -1) {
      for (ColorTile tile : tiles) {
        addTileToFloorLine(tile);
      }
    }
    if (tiles.get(0).getColor() == getPatternLineColor(rowIndex)
        || getNextFreePatternLineIndex(rowIndex) == 0) {
      //TODO: shouldn't color mismatch be an illegal argument exception?
      ColorTile[] row = patternLines[rowIndex];
      for (int i = 0; i < tiles.size(); i++) {
        if (freeFields > 0) {
          row[freeFields - 1] = tiles.get(i);
          freeFields--;
        } else {
          addTileToFloorLine(tiles.get(i));
        }
      }
    }
  }

  /**
   * Adds the specified tile to the floor line. If the tile to be added is a penalty tile, it will
   * be placed at the beginning of the floor line (index 0). If the floor line was already full, the
   * color tile, that was previously at the end of the floor line, will be moved to the box. If the
   * tile to be added is a color tile, it will be added in the first free space of the floor line.
   * If the floor line is already full, the color tile will be added to the box.
   *
   * @param tile the tile to be added to the floor line
   */
  public void addTileToFloorLine(Tile tile) {
    if (floorLine.size() > FLOORLINE_SIZE) {
      throw new RuntimeException("Maximum floorLine size exceeded.");
    }
    if (tile instanceof PenaltyTile) {
      shiftTilesOfFloorLine();
      floorLine.set(0, tile);
      return;
    }
    if (floorLine.size() == FLOORLINE_SIZE) {
      addTileToBox((ColorTile) tile);
      return;
    }
    floorLine.add(tile);
  }

  /**
   * Shifts color tiles in the pattern line one slot to the right, the first slot at the beginning
   * of the beginning of the floor line becomes empty (null).
   */
  private void shiftTilesOfFloorLine() {
    if (floorLine.size() == FLOORLINE_SIZE) {
      addTileToBox((ColorTile) floorLine.get(floorLine.size() - 1));
      floorLine.set(floorLine.size() - 1, null);
    }
    for (int i = floorLine.size() - 1; i > 0; i--) {
      floorLine.set(i, floorLine.get(i - 1));
    }
    floorLine.set(0, null);
  }

  /**
   * Adds the specified tile to the box.
   *
   * @param tile the tile to be added to the box
   */
  void addTileToBox(ColorTile tile) {
    requireNonNull((tile));
    box.add(tile);
  }

}
