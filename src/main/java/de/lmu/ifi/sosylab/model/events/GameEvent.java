package de.lmu.ifi.sosylab.model.events;

/**
 * Class for sub classing events in the game.
 */
public abstract class GameEvent {

  /**
   * The name of the event as string representation.
   *
   * @return a string describing the implementing event.
   */
  public abstract String getName();

}
