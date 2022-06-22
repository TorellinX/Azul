package model;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerBoard {
  private final static int WALL_SIZE = 5;

  private final String nickname;
  private final Table table;
  private int score;
  ColorTile[][] patternLines;
  boolean[][] wall;
  ArrayList<Tile> floorLine;


  public PlayerBoard(String nickname, Table table){
    this.nickname = nickname;
    this.table = table;
  }

  /**
   * Checks if the row of patternLines is full.
   *
   * @param row selected row
   * @return true, if the row is full
   */
  public boolean isFull(int row){
    // TODO: validation
    // TODO: check
    for (int i = 0; i < patternLines[row].length; i++) {
      if (patternLines[row][i] == null){
        return false;
      }
    }
    return true;
  }

  public Color getPatternLineColor(int row){
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

  public String getNickname() {
    String nicknameCopy = new StringBuffer(nickname).toString();
    return nicknameCopy;
  }

  public int getScore() {
    int scoreCopy = score;
    return scoreCopy;
  }

  public ColorTile[][] getPatternLines() {
    ColorTile[][] patternLinesCopy = new ColorTile[patternLines.length][];
    for(int i = 0; i < patternLines.length; i++) {
      patternLinesCopy[i] = Arrays.copyOf(patternLines[i], patternLines[i].length); // Tiles are references!
    }
    return patternLinesCopy;
  }

  public boolean[][] getWall() {
    boolean[][] wallCopy = new boolean[wall.length][];
    for(int i = 0; i < wall.length; i++) {
      wallCopy[i] = Arrays.copyOf(wall[i], wall[0].length);
    }
    return wall;
  }

  public ArrayList<Tile> getFloorLine() {
    return floorLine;
  }

}
