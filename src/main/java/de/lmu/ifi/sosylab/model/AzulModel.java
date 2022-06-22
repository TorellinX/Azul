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
    return (ArrayList<String>) players.clone();
  }

  public State getState() {
    //TODO: is State necessary?
    return state;
  }

  public Table getTable(){
    // TODO: return a copy of the table?????
    return table;
  }

}
