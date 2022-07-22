package de.lmu.ifi.sosylab.server;

import com.fasterxml.jackson.databind.node.ObjectNode;
import de.lmu.ifi.sosylab.Authenticator;
import de.lmu.ifi.sosylab.InformationWrapper;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

/**
 * The class provides the interface for the API calls and processes them.
 */
@RestController
@RequestMapping("/api")
public class router {

  /**
   * Constructor for the class.
   */
  Lobby lobby = new Lobby();


  @GetMapping("/rooms")
  public List<Room> getRooms() {
    return lobby.rooms;
  }

  @PostMapping("/rooms/create")
  public String createRoom(@RequestBody String name) {
    return lobby.createRoom(name);
  }

  @PostMapping("/rooms/id/{id}/start")
  public Boolean startRoom(@PathVariable(value = "id") String id) {
    return lobby.getRoom(id).start();
  }

  @PostMapping("/rooms/id/{id}/terminate")
  public Boolean terminateRoom(@PathVariable(value = "id") String id) {
    return lobby.getRoom(id).terminateRoom();
  }

  @GetMapping("/users")
  public List<String> getUsers() {
    //System.out.println(lobby.getUsers().get(0).getUsername());
    return lobby.getUsers().stream().map(user -> user.getUsername()).toList();
  }

  @PostMapping("/user/register")
  public String register(@RequestBody String username, HttpServletResponse response) {
    System.out.println(username);
    if (lobby.checkUsername(username) || lobby.rooms.stream().anyMatch(r -> r.getUsers().stream().anyMatch(u -> u.getUsername().equals(username)))) {
      response.setStatus(409);
      return "Username already in use";
    } else {
      return lobby.createUser(username);
    }
  }

  @PostMapping("/rooms/join")
  public String joinRoom(@RequestBody ObjectNode json) {
    String userToken = json.get("userToken").asText();
    String roomId = json.get("roomId").asText();
    lobby.joinRoom(userToken, roomId);
    return "ok";
  }

  public router() {
    //management = new Management();
  }


  @GetMapping("/getInformationWrapper")
  public InformationWrapper getInforamtionWrapper() {
    InformationWrapper informationWrapper = new InformationWrapper();
    return informationWrapper;
  }

  @PostMapping("/rooms/id/{roomid}/pickTilePlate")
  public Boolean pickTilePlate(@RequestBody InformationWrapper informationWrapper) {
    //management.pickTileFromPlate(informationWrapper);
    return null;
  }

  @PostMapping("/pickTileFromTableCenter")
  public Boolean pickTileFromTableCenter(@RequestBody InformationWrapper informationWrapper) {
    //management.pickTileFromTableCenter(informationWrapper);
    return null;
  }

  @PostMapping("/placeTile")
  public Boolean placeTile(@RequestBody InformationWrapper informationWrapper) {
    //management.placeTile(informationWrapper);
    return null;
  }
}
