package de.lmu.ifi.sosylab.model;

import java.util.ArrayList;
import java.util.List;

public class Plate {

  public record SelectedAndRemainingTiles(List<ColorTile> selected, List<ColorTile> remaining) {
  }

  private PlateState state = PlateState.FULL;

  private final List<ColorTile> tiles = new ArrayList<>();

  public Plate(List<ColorTile> tiles) {
    this.addTiles(tiles);
  }

  public void addTiles(List<ColorTile> tiles) {
    if (tiles.size() != 4) {
      throw new RuntimeException("A plate has to be filled with exactly 4 tiles");
    }
    this.tiles.addAll(tiles);
    this.state = PlateState.FULL;
  }

  public SelectedAndRemainingTiles pickTiles(Color color) {
    if (state == PlateState.EMPTY) {
      throw new RuntimeException("Can not pick tiles from empty plate");
    }
    if (tiles.stream().noneMatch(t -> t.getColor() == color)) {
      throw new RuntimeException("No tiles of the selected color are on the plate");
    }
    SelectedAndRemainingTiles selectedAndRemainingTiles = new SelectedAndRemainingTiles(
        tiles.stream().filter(t -> t.getColor() == color).toList(),
        tiles.stream().filter(t -> t.getColor() != color).toList()
    );
    state = PlateState.EMPTY;
    tiles.clear();
    return selectedAndRemainingTiles;
  }

  public PlateState getState() {
    return state;
  }

  public List<ColorTile> getTiles() {
    return tiles;
  }
}
