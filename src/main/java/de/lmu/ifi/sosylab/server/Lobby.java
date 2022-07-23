package de.lmu.ifi.sosylab.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.lmu.ifi.sosylab.server.controller.APIController;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Lobby {

  @Getter
  @Setter
  @JsonProperty("users")
  List<User> users = new ArrayList<>();

  @Getter
  @Setter
  List<Room> rooms = new ArrayList<>();


  public String createRoom(String name, APIController apiController) {
    Room room = new Room(name, apiController);
    rooms.add(room);
    return room.id;
  }

  // check if username is already in use
  public boolean checkUsername(String username) {
    for (User user : users) {
      if (user.getUsername().equals(username)) {
        return true;
      }
    }
    return false;
  }

  // Create User and add it to the list
  public User createUser(String name) {
    User user = new User(name);
    users.add(user);
    return user;
  }

  // User join a room
  public void joinRoom(String UserToken, String roomId) {
    // get room with id rooomId

    //remove user with token from list
    User user = users.stream().filter(u -> u.getToken().equals(UserToken)).findFirst().orElse(null);
    users.remove(user);

    // add user to room with id roomId
    rooms.stream().filter(r -> r.id.equals(roomId)).findFirst().orElse(null).addUser(user);
  }

  // return lobby by id
  public Room getRoom(String id) {
    return rooms.stream().filter(r -> r.id.equals(id)).findFirst().orElse(null);
  }

}
