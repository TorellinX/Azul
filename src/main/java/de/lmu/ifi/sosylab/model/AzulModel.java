package de.lmu.ifi.sosylab.model;



import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// TODO: refine JavaDoc

/**
 * Manages the logic of the Azul Game.
 */
public class AzulModel {

  private final List<Player> players;
  private State state = State.RUNNING;

  private final PropertyChangeSupport support = new PropertyChangeSupport(this);
  private Game game;

  // index of the active player for the factory offer phase
  private int activePlayer;
  // index of the first player for the round
  private int firstPlayer;

  /**
   * Constructs a new game with the specified list of players.
   *
   * @param players list of players
   */
  public AzulModel(List<Player> players) {
    this.players = players;
    game = new Game(this.players);
    state = State.RUNNING;
  }

  public List<Player> getPlayers() {
    // TODO: return copy of players
    return players;
  }

  public State getState() {
    //TODO: is State necessary?
    return state;
  }

  public Game getTable() {
    // TODO: return a copy of the table?????
    return game;
  }

  /**
   * Shifts the index of the active player to the next player during the factory offer phase.
   */
  void shiftActivePlayer() {
    activePlayer = (activePlayer + 1) % (players.size());
  }

  /**
   * Get the index of the active player for the factory offer phase.
   *
   * @return the index of the active player
   */
  public int getActivePlayer() {
    int activePlayerCopy = activePlayer;
    return activePlayerCopy;
  }

  /**
   * Sets the active player index to the specified value.
   *
   * @param activePlayer new value of the active player index.
   * @throws IllegalArgumentException if the specified index is greater or equal than the number of
   *                                  players or negative
   */

  public void setActivePlayer(int activePlayer) {
    if (activePlayer >= players.size()) {
      throw new IllegalArgumentException(
          "The active player index must be less than the number of players.");
    }
    if (activePlayer < 0) {
      throw new IllegalArgumentException(
          "The active player index must be positive.");
    }
    this.activePlayer = activePlayer;
  }

  /**
   * Sets the index of the active player to the first player index value.
   */
  public void setActivePlayerToFirstPlayer() {
    setActivePlayer(firstPlayer);
  }

  /**
   * Get the index of the first player for the round.
   *
   * @return the index of the first player
   */
  public int getFirstPlayer() {
    int firstPlayerCopy = firstPlayer;
    return firstPlayerCopy;
  }

  /**
   * Sets the first player index to the specified value.
   *
   * @param firstPlayer new value of the first player index.
   * @throws IllegalArgumentException if the specified index is greater or equal than the number of
   *                                  players  or negative
   */
  void setFirstPlayer(int firstPlayer) {
    if (firstPlayer >= players.size()) {
      throw new IllegalArgumentException(
          "The first player index must be less than the number of players.");
    }
    if (firstPlayer < 0) {
      throw new IllegalArgumentException(
          "The first player index must be positive.");
    }
    this.firstPlayer = firstPlayer;
  }

}
