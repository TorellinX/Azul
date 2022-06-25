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

  private record SelectedTilesAndMaybePenaltyTile(List<ColorTile> colorTiles,
                                                  Optional<PenaltyTile> penaltyTile) {

  }

  public TableCenter() {
    penaltyTileOptional = Optional.of(new PenaltyTile());
  }

  public void addColorTiles(List<ColorTile> colorTiles) {
    this.colorTiles.addAll(colorTiles);
  }


  public void addPenaltyTile() {
    penaltyTileOptional = Optional.of(new PenaltyTile());
  }

  public List<Tile> getTiles() {
    List<Tile> list = new ArrayList<>(colorTiles);
    penaltyTileOptional.ifPresent(list::add);
    return list;
  }

  public SelectedTilesAndMaybePenaltyTile pickTiles(Color color) {
    SelectedTilesAndMaybePenaltyTile selectedTilesAndMaybePenaltyTile = new SelectedTilesAndMaybePenaltyTile(
        colorTiles.stream().filter(t -> t.getColor() == color).toList(),
        penaltyTileOptional
    );
    colorTiles.removeAll(colorTiles.stream().filter(t -> t.getColor() == color).toList());
    if (penaltyTileOptional.isPresent()) {
      penaltyTileOptional = Optional.empty();
    }
    return selectedTilesAndMaybePenaltyTile;
  }
}
