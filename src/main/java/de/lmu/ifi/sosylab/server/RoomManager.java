package de.lmu.ifi.sosylab.server;

import java.util.ArrayList;
import java.util.List;

public class RoomManager {

  public List<Rooms> roomsList = new ArrayList<>();

  // create room and add it to the list
  public void createRoom(String name, String password) {
    Rooms room = new Rooms(name, password);
    roomsList.add(room);
  }

  public void createRoom(String name) {
    Rooms room = new Rooms(name);
    roomsList.add(room);
  }

  //get room by id
  public Rooms getRoomById(String id) {
    for (Rooms room : roomsList) {
      if (room.id.equals(id)) {
        return room;
      }
    }
    return null;
  }

  //get room by name
  public Rooms getRoomByName(String name) {
    for (Rooms room : roomsList) {
      if (room.name.equals(name)) {
        return room;
      }
    }
    return null;
  }

  //delete room by id
  public void deleteRoomById(String id) {
    for (Rooms room : roomsList) {
      if (room.id.equals(id)) {
        roomsList.remove(room);
      }
    }
  }

}
