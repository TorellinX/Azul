package de.lmu.ifi.sosylab.model.events;

import de.lmu.ifi.sosylab.model.Color;

/**
 * Subclass for notifying about a select tile event.
 */
public class SelectTileEvent extends GameEvent {

  private final Color color;

  public SelectTileEvent(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

  @Override
  public String getName() {
    return "SelectTileEvent";
  }
}
