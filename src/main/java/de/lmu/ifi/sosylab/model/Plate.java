package de.lmu.ifi.sosylab.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Factory class providing 4 tiles to pick.
 */
public class Plate {

  /**
   * Record holding the picked color tiles after a pick as well as the remaining tiles to be
   * transferred to the table center.
   *
   * @param selected  list of tiles of the selected color from this plate (factory)
   * @param remaining list of tiles of the other colors from this plate
   */
  public record SelectedAndRemainingTiles(List<ColorTile> selected,
                                          Optional<List<ColorTile>> remaining) {

    public SelectedAndRemainingTiles {
      selected = List.copyOf(selected);
    }

    public List<ColorTile> selected() {
      List<ColorTile> immutableColorTileList = Collections.unmodifiableList(selected);
      return immutableColorTileList;
    }

  }

  private PlateState state = PlateState.FULL;

  private final List<ColorTile> tiles = new ArrayList<>();

  public Plate(List<ColorTile> tiles) {
    this.addTiles(tiles);
  }

  /**
   * Adds color tiles to this plate. 4 tiles are expected, otherwise exception is thrown.
   *
   * @param tiles list of color tiles to add
   */
  public void addTiles(List<ColorTile> tiles) {
    if (tiles.size() != 4) {
      throw new RuntimeException("A plate has to be filled with exactly 4 tiles");
    }
    this.tiles.addAll(tiles);
    this.state = PlateState.FULL;
  }

  /**
   * Builds the record containing Lists of tiles of the selected color and the residual tiles.
   * Expects valid argument.
   *
   * @param color selected color from this plate
   * @return record with lists as described above
   */
  public SelectedAndRemainingTiles pickTiles(Color color) {
    if (state == PlateState.EMPTY) {
      throw new RuntimeException("Can not pick tiles from empty plate");
    }
    if (tiles.stream().noneMatch(t -> t.getColor() == color)) {
      throw new RuntimeException("No tiles of the selected color are on the plate");
    }
    SelectedAndRemainingTiles selectedAndRemainingTiles = new SelectedAndRemainingTiles(
        tiles.stream().filter(t -> t.getColor() == color).toList(),
        Optional.of(tiles.stream().filter(t -> t.getColor() != color).toList())
    );
    state = PlateState.EMPTY;
    tiles.clear();
    return selectedAndRemainingTiles;
  }

  public PlateState getState() {
    return state;
  }

  public List<ColorTile> getTiles() {
    List<ColorTile> unmodifiableTilesList = Collections.unmodifiableList(tiles);
    return unmodifiableTilesList;
  }

  public boolean containsColor(Color color) {
    return tiles.stream().anyMatch(t -> t.getColor() == color);
  }

}
