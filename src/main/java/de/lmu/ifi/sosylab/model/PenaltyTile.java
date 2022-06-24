package de.lmu.ifi.sosylab.model;

/**
 * A penalty tile that gets the player who first takes the tiles from the center of the table. It
 * serves also as a marker for the starting player for the next round.
 */
public class PenaltyTile extends Tile {

  @Override
  public String toString() {
    return "(-1)";
  }
}
