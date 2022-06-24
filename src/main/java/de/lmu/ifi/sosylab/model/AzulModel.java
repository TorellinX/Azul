package de.lmu.ifi.sosylab.model;

import static java.util.Objects.requireNonNull;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;

public class AzulModel {

  private ArrayList<String> players;
  private State state = null;
  private final PropertyChangeSupport support;
  private Table table;

  // index of the active player for the factory offer phase
  private int activePlayer;
  // index of the first player for the round
  private int firstPlayer;

  Random random = new Random();

  public AzulModel(ArrayList<String> players) {
    support = new PropertyChangeSupport(this);
    this.table = new Table(players);
    state = State.RUNNING;
    this.players = players;
    firstPlayer = random.nextInt(players.size());
    activePlayer = firstPlayer;

  }

  public ArrayList<String> getPlayers() {
    // TODO: return copy of players
    return players;
  }

  public State getState() {
    //TODO: is State necessary?
    return state;
  }

  public Table getTable() {
    // TODO: return a copy of the table?????
    return table;
  }

  /**
   * Shifts the index of the active player to the next player during the factory offer phase.
   */
  void shiftActivePlayer() {
    activePlayer = (activePlayer + 1) % (players.size());
  }

  /**
   * Shifts the index of the first player to the next player for the next round.
   */
  void shiftFirstPlayer() {
    firstPlayer = (firstPlayer + 1) % (players.size());
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
  public void setActivePayerToFirstPlayer() {
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

}
