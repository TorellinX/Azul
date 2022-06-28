package de.lmu.ifi.sosylab.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerBoard {

  final static int WALL_SIZE = 5;

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

  public int countFreeFieldsInRow(int rowIndex) {
    if (rowIndex > WALL_SIZE) {
      throw new IllegalArgumentException("Row Index must be within the wall size.");
    }
    if (rowIndex < 0) {
      throw new IllegalArgumentException("Row Index must be positive");
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
   * @param row
   * @return the next free index or -1
   */
  public int getNextFreePatternLineIndex(int row) {
    for (int i = 0; i < patternLines[row].length; i++) {
      if (patternLines[row][i] == null) {
        return i;
      }
    }
    return -1;
  }

  public Color getPatternLineColor(int row) {
    // TODO: validation (Line must be not empty, etc.)
    // TODO: tests
    if (patternLines[row][patternLines[row].length - 1] == null) {
      return null;
    }
    Color color = patternLines[row][patternLines[row].length - 1].getColor();
    return color;
  }

  void addTileToWall(Color color, int row) {
    //TODO: add validation
    // TODO: tests
    int column = (row + color.ordinal()) % WALL_SIZE;
    wall[row][column] = true;
  }


  public boolean isColorAlreadyOnWall(Color color, int row) {
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

  public ColorTile[][] getPatternLines() {
    ColorTile[][] patternLinesCopy = new ColorTile[patternLines.length][];
    for (int i = 0; i < patternLines.length; i++) {
      patternLinesCopy[i] = Arrays.copyOf(patternLines[i],
          patternLines[i].length); // Tiles are references!
    }
    return patternLinesCopy;
  }

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
   * are more tiles than there are free spaces in a row, tiles will be added to the floorLine.
   *
   * @param tiles    tiles to be added
   * @param rowIndex row of the PatternLines
   */
  public void addColorTilesToLine(List<ColorTile> tiles, int rowIndex) {
    int freeFields = countFreeFieldsInRow(rowIndex);
    if (tiles.size() == 0) {
      throw new IllegalArgumentException(
          "Trying to add an empty list of tiles to the patternLine.");
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

  public void addTileToFloorLine(Tile tile) {
    if (floorLine.size() > 7) {
      throw new RuntimeException("Maximum floorLine size exceeded.");
    }
    if (tile instanceof PenaltyTile) {
      // TODO
      shiftTilesInPatternLine((PenaltyTile) tile);
      floorLine.add(0, tile);
    }
    if (floorLine.size() == 7) {
      addTileToBox((ColorTile) tile);
      return;
    }
    floorLine.add(tile);
  }

  private void shiftTilesInPatternLine(PenaltyTile penaltyTile) {
    if (floorLine.size() == 7) {
      addTileToBox((ColorTile) floorLine.get(floorLine.size() - 1));
      floorLine.add(floorLine.size() - 1, null);
    }
    for (int i = floorLine.size() - 1; i >= 0; i--) {
      floorLine.add(floorLine.get(i - 1));
    }
    // TODO
  }

  void addTileToBox(ColorTile tile) {
    requireNonNull((tile));
    box.add(tile);
  }

}
