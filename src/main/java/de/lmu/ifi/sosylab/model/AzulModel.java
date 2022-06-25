package de.lmu.ifi.sosylab.model;

import static java.util.Objects.requireNonNull;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class AzulModel {
  private ArrayList<String> players;
  private State state = null;
  private final PropertyChangeSupport support;
  private Table table;

  public AzulModel(ArrayList<String> players){
    support = new PropertyChangeSupport(this);
    this.table = new Table(players);
    state = State.RUNNING;
    this.players = players;

  }

  public ArrayList<String> getPlayers() {
    // TODO: return copy of players
    return players;
  }

  public State getState() {
    //TODO: is State necessary?
    return state;
  }

  public Table getTable(){
    // TODO: return a copy of the table?????
    return table;
  }

  public boolean pickFromTableCenter(String player, Tile tile, int row) {
    return false;
  }

  public boolean pickFromPlate(String player, Tile tile, int plateId) {
    return false;
  }

  // end class
}
