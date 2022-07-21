package de.lmu.ifi.sosylab.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Tiles with a color.
 */
public class ColorTile extends Tile {

  @JsonProperty("color")
  public Color color;

  public ColorTile(){

  }

  public ColorTile(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

  @Override
  public String toString() {
    return color.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ColorTile colorTile = (ColorTile) o;
    return color.equals(colorTile.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(color);
  }
}
