package model;

import static java.util.Objects.requireNonNull;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
