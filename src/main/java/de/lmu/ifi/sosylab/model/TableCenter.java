package de.lmu.ifi.sosylab.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Manages the Table Center.
 */
public class TableCenter {

  private List<ColorTile> colorTiles = new ArrayList<>();
  private Optional<PenaltyTile> penaltyTileOptional;

  record SelectedTilesAndMaybePenaltyTile(List<ColorTile> colorTiles,
                                          Optional<PenaltyTile> penaltyTile) {

  }

  public TableCenter() {
    penaltyTileOptional = Optional.of(new PenaltyTile());
  }

  public void addColorTiles(List<ColorTile> tiles) {
    this.colorTiles.addAll(tiles);
  }

  public void addPenaltyTile(PenaltyTile tile) {
    penaltyTileOptional = Optional.of(tile);
  }

  /**
   * Getter for the tiles on the table center.
   *
   * @return list of tiles including penalty tile if present
   */
  public List<Tile> getTiles() {
    List<Tile> list = new ArrayList<>(colorTiles);
    penaltyTileOptional.ifPresent(penaltyTile -> list.add(0, penaltyTile));
    return list;
  }

  public List<ColorTile> getColorTiles() {
    List<ColorTile> unmodifiableColorTilesList = Collections.unmodifiableList(colorTiles);
    return unmodifiableColorTilesList;
  }

  /**
   * Get the tiles from table center after a pick from table center. Includes the penalty tile if
   * present. Picked tiles (in case including penalty tile) are removed from table center.
   *
   * @param color selected color
   * @return record of picked tiles (in case with penalty tile)
   */
  public SelectedTilesAndMaybePenaltyTile pickTiles(Color color) {
    List<ColorTile> selectedColorTiles = this.colorTiles.stream().filter(t -> t.getColor() == color)
        .toList();
    SelectedTilesAndMaybePenaltyTile returnedTiles = new SelectedTilesAndMaybePenaltyTile(
        selectedColorTiles,
        penaltyTileOptional
    );
    colorTiles.removeAll(selectedColorTiles);
    if (penaltyTileOptional.isPresent()) {
      penaltyTileOptional = Optional.empty();
    }
    return returnedTiles;
  }
}
