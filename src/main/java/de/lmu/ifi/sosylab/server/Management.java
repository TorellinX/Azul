package de.lmu.ifi.sosylab.server;

import de.lmu.ifi.sosylab.Authenticator;
import de.lmu.ifi.sosylab.InformationWrapper;

import java.util.ArrayList;
import java.util.List;


public class Management {

  List<Room> playrooms;


  public Management() {
    playrooms = new ArrayList<>();
  }

  public Boolean creatNewRoom(Authenticator authenticatorRoom, Authenticator authenticatorPlayer) {
    playrooms.add(new Room(authenticatorRoom));
    playrooms.get(getRoom(authenticatorRoom)).addPlayerToRoom(authenticatorPlayer);
    return true;
  }

  public Boolean addPlayerToServer(Authenticator authenticatorPlayer, Authenticator authenticatOfRoom) {
    if (playrooms.get(getRoom(authenticatOfRoom)).addPlayerToRoom(authenticatorPlayer)) {
      return true;
    } else {
      return false;
    }
  }

  public Boolean removePlayerFromServer(Authenticator authenticatorPlayer, Authenticator authenticatOfRoom) {
    if (playrooms.get(getRoom(authenticatOfRoom)).removePlayerFromRoom(authenticatorPlayer)) {
      return true;
    } else {
      return false;
    }
  }

  public int getRoom(Authenticator authenticator) {
    int toReturn = -1;
    for (int i = 0; i < playrooms.size(); i++) {
      if (playrooms.get(i).getAuthenticatorOfRoom().equals(authenticator)) {
        return i;
      }
    }
    return toReturn;
  }

  public List<Room> getAllRooms() {
    return playrooms;
  }

  public Boolean startGame(Authenticator authenticatorRoom, Authenticator authenticatorPlayer) {

    if (!(getRoom(authenticatorRoom) == -1)) {
      if (playrooms.get(getRoom(authenticatorRoom)).start(authenticatorPlayer)) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }

  }

  public Boolean removeRoom(Authenticator authenticatorRoom, Authenticator authenticatorPlayer) {
    if (!(getRoom(authenticatorRoom) == -1)) {
      if (playrooms.get(getRoom(authenticatorRoom)).isPlayerPartOfRoom(authenticatorPlayer)) {
        playrooms.remove(getRoom(authenticatorRoom));
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  public Boolean pickTileFromPlate(InformationWrapper informationWrapper) {
    return playrooms.get(getRoom(informationWrapper.getAuthenticatorRoom())).pickTileFromPlate(informationWrapper);
  }

  public Boolean pickTileFromTableCenter(InformationWrapper informationWrapper) {
    return playrooms.get(getRoom(informationWrapper.getAuthenticatorRoom())).pickTileFromTableCenter(informationWrapper);
  }

  public Boolean placeTile(InformationWrapper informationWrapper) {
    return playrooms.get(getRoom(informationWrapper.getAuthenticatorRoom())).placeTile(informationWrapper);
  }

}
