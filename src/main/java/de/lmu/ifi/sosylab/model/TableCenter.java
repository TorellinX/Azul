package de.lmu.ifi.sosylab.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Manages the Table Center.
 */
public class TableCenter {

  private List<ColorTile> colorTiles;
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

  public List<Tile> getTiles() {
    List<Tile> list = new ArrayList<>(colorTiles);
    penaltyTileOptional.ifPresent(list::add);
    return list;
  }

  public List<ColorTile> getColorTiles() {
    return colorTiles;
  }

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
