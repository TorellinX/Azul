package de.lmu.ifi.sosylab.model;

public class PatternLine {

  private final int capacity;
  private int occupancy;

  private ColorTile colorTile = null;

  public PatternLine(int capacity){
    this.capacity = capacity;
  }

  public void setOccupancy(int occupancy) {
    this.occupancy = occupancy;
  }

  public void setColorTile(ColorTile colorTile){
    this.colorTile = colorTile;
  }

  public int getCapacity() {
    return capacity;

  }
  public int getOccupancy(){
    return occupancy;
  }

  public ColorTile getColorTile() {
    return colorTile;
  }
}
