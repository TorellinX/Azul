package de.lmu.ifi.sosylab.server;

import de.lmu.ifi.sosylab.Authenticator;
import de.lmu.ifi.sosylab.InformationWrapper;
import de.lmu.ifi.sosylab.controller.GameController;
import de.lmu.ifi.sosylab.model.GameModel;

import java.util.ArrayList;
import java.util.List;

public class Room {

  private Authenticator authenticatorOfRoom;
  private ArrayList<Authenticator> listOfPlayers;
  private final GameModel model;
  private final GameController controller;


  public Room(Authenticator authenticator) {
    listOfPlayers = new ArrayList<>();

    this.model = new GameModel();
    this.controller = new GameController(model);

    this.authenticatorOfRoom = new Authenticator();
  }

  public Boolean isPlayerPartOfRoom(Authenticator authenticator) {
    if (listOfPlayers.contains(authenticator)) {
      return true;
    } else {
      return false;
    }
  }

  public Boolean addPlayerToRoom(Authenticator authenticatorPlayer) {
    if (!listOfPlayers.contains(authenticatorPlayer)) {
      listOfPlayers.add(authenticatorPlayer);
      return true;
    } else {
      return false;
    }
  }

  public Boolean removePlayerFromRoom(Authenticator authenticatorPlayer) {
    if (listOfPlayers.contains(authenticatorPlayer)) {
      listOfPlayers.remove(listOfPlayers.indexOf(authenticatorPlayer));
      return true;
    } else {
      return false;
    }
  }

  public Authenticator getAuthenticatorOfRoom() {
    return authenticatorOfRoom;
  }


  public List<Authenticator> getPlayers() {
    return listOfPlayers;
  }

  public void terminateRoom() {

  }

  public Boolean start(Authenticator authenticator) {
    if (listOfPlayers.contains(authenticator)) {
      controller.start();
      return true;
    } else {
      return false;
    }
  }

  public Boolean pickTileFromPlate(InformationWrapper informationWrapper) {
    return controller.pickTilesFromPlate(informationWrapper.getColor(), informationWrapper.getPlayer(), informationWrapper.getPlate());
  }

  public Boolean pickTileFromTableCenter(InformationWrapper informationWrapper) {
    return controller.pickTilesFromTableCenter(informationWrapper.getColor(), informationWrapper.getPlayer());
  }

  public Boolean placeTile(InformationWrapper informationWrapper) {
    return controller.placeTiles(informationWrapper.getPlayer(), informationWrapper.getRow());
  }

}
