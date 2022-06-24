package de.lmu.ifi.sosylab.model.events;

import de.lmu.ifi.sosylab.model.Color;

public class SelectTileEvent extends GameEvent {

  private final Color color;
  // place ?????????????????????????????????????????????????????????

  public SelectTileEvent(Color color) {  // , place) ????????????????
    this.color = color;
    // place ????????????????
  }

  public Color getColor() {
    return color;
  }

  @Override
  public String getName() {
    return "SelectTileEvent";
  }
}
