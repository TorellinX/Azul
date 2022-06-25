package de.lmu.ifi.sosylab.model;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerBoard {
  private final static int WALL_SIZE = 5;

  private final String nickname;
  private final Table table;
  private int score;
  PatternLine[] patternLines;
  boolean[][] wall;
  ArrayList<Tile> floorLine;


  public PlayerBoard(String nickname, Table table){
    this.nickname = nickname;
    this.table = table;
    // initialize boolean wall
    this.wall = new boolean[WALL_SIZE][WALL_SIZE];
    for (int i = 0; i < WALL_SIZE; i++) {
      for (int j = 0; j < WALL_SIZE; j++) {
        this.wall[i][j] = false;
      }
    }
    // create pattern lines array
    patternLines = new PatternLine[5];
    for (int i = 0; i < 5; i++) {
      patternLines[i] = new PatternLine(i + 1);
    }
  }

  /**
   * Checks if the row of patternLines is full.
   *
   * @param row selected row
   * @return true, if the row is full
   */
  public boolean isFull(int row){
    return patternLines[row].getCapacity() == patternLines[row].getOccupancy();
  }

  public Color getPatternLineColor(int row){
    // TODO: validation (Line must be not empty, etc.)
    // TODO: tests
    Color color = patternLines[row].getColorTile().getColor();
    return color;
  }

  private void addTileToWall(Color color, int row) {
    //TODO: add validation
    // TODO: tests
    int column = (row + color.ordinal()) % WALL_SIZE;
    wall[row][column] = true;
  }

  private boolean isTileFree(Color color, int row) {
    //TODO: add validation
    // TODO: tests
    int column = (row + color.ordinal()) % WALL_SIZE;
    return wall[row][column];
  }

  public void setPatternLine(int row, int count, ColorTile colorTile){
    patternLines[row].setColorTile(colorTile);
    patternLines[row].setOccupancy(count);
  }

  public String getNickname() {
    String nicknameCopy = new StringBuffer(nickname).toString();
    return nicknameCopy;
  }

  public int getScore() {
    int scoreCopy = score;
    return scoreCopy;
  }

  public PatternLine[] getPatternLines() {
    PatternLine[] patternLinesCopy = new PatternLine[patternLines.length];
    for(int i = 0; i < patternLines.length; i++) {
      patternLinesCopy[i] = patternLines[i];         // some deep copy??
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
