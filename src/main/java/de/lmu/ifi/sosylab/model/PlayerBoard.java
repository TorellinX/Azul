package de.lmu.ifi.sosylab.model;

import java.util.Arrays;
import java.util.List;

public class PlayerBoard {

  private final static int WALL_SIZE = 5;

  private int score;
  ColorTile[][] patternLines;
  boolean[][] wall;
  List<Tile> floorLine;
  private List<ColorTile> pickedTiles;


  public PlayerBoard() {
    patternLines = new ColorTile[5][];

  }

  public int countFreeFieldsInRow(int rowIndex) {
    ColorTile[] row = patternLines[rowIndex];
    for (int i = 0; i < row.length; i++) {
      if (row[i] == null) {
        return row.length - i;
      }
    }
    return 0;
  }

  public int patternLineIndex(int row) {
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


  public void addColorTilesToLine(List<ColorTile> tiles, int rowIndex) {
    int freeFields = countFreeFieldsInRow(rowIndex);
    if (freeFields == 0) {
      throw new IllegalArgumentException("Row is full");
    }
    if (isColorAlreadyOnWall(tiles.get(0).getColor(), rowIndex)) {
      throw new IllegalArgumentException("Color already on wall");
    }
    if (rowIndex == -1) {
      for (Tile tile : tiles) {
        addTileToFloorLine(tile);
      }
    }
    if (tiles.get(0).getColor() == getPatternLineColor(rowIndex) || patternLineIndex(rowIndex) == 0) {
      ColorTile[] row = patternLines[rowIndex];
      for (int i = 0; i < tiles.size(); i++) {
        if(freeFields >= tiles.size()) {
          row[row.length - freeFields + i] = tiles.get(i);
          freeFields--;
        }
        else {
          addTileToFloorLine(tiles.get(i));
        }
      }
    }
  }

  public void addTileToFloorLine(Tile tile) {
    floorLine.add(tile);
  }

}
