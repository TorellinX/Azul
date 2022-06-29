package de.lmu.ifi.sosylab.model;

import java.util.Objects;

/**
 * A penalty tile that gets the player who first takes the tiles from the center of the table. It
 * serves also as a marker for the starting player for the next round.
 */
public class PenaltyTile extends Tile {

  @Override
  public String toString() {
    return "(-1)";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PenaltyTile penaltyTile = (PenaltyTile) o;
    return this.toString().equals(penaltyTile.toString());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.toString());
  }
}
