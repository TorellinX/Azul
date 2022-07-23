package de.lmu.ifi.sosylab;

import de.lmu.ifi.sosylab.model.Color;
import de.lmu.ifi.sosylab.model.Plate;
import de.lmu.ifi.sosylab.model.Player;
import java.util.List;

public class InformationWrapper {

  private List<Player> playersList;
  private Player player;
  private List<Plate> platesList;
  private Plate plate;
  private Color color;
  private int row;
  private Boolean wasInputSuccesful;


  public InformationWrapper() {

  }

  public void playersList(List<Player> playersList) {
    this.playersList = playersList;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public void setPlatesList(List<Plate> platesList) {
    this.platesList = platesList;
  }

  public void setPlate(Plate plate) {
    this.plate = plate;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public void setWasInputSuccesful(Boolean succesful) {
    this.wasInputSuccesful = succesful;
  }


  public int getRow() {
    return row;
  }

  public Player getPlayer() {
    return player;
  }

  public List<Player> getPlayersList() {
    return playersList;
  }

  public List<Plate> getPlatesList() {
    return platesList;
  }

  public Color getColor() {
    return color;
  }

  public Plate getPlate() {
    return plate;
  }

  public Boolean getWasInputSuccesful() {
    return wasInputSuccesful;
  }
}
