package de.lmu.ifi.sosylab.server;

import de.lmu.ifi.sosylab.InformationWrapper;
import de.lmu.ifi.sosylab.controller.GameController;
import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.model.State;
import de.lmu.ifi.sosylab.server.controller.APIController;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import lombok.Getter;

public class Room {

  @Getter
  public String name;
  //public String password;
  @Getter
  public String id;
  public State state;


  private List<User> users = new ArrayList<>(4);
  private GameModel model;
  private GameController controller;

  private APIController apiController;

  public Room(){
  }

  public Room(String name, APIController apiController) {
    this.name = name;
    this.id = generateToken();
    this.state = State.RUNNING;
    this.apiController = apiController;
  }

  //Generate a random Room token
  public static String generateToken() {
    String token = "";
    for (int i = 0; i < 6; i++) {
      token += (char) (Math.random() * 26 + 'a');
    }
    return token;
  }

  public List<String> getUsers() {
    //return username of all users in room
    List<String> res = new ArrayList<>();
    for (User user : users) {
      res.add(user.getUsername());
    }
    return res;
  }

  //add user to list
  public void addUser(User user) {
    System.out.println(users.size());
    users.add(user);
    System.out.println(users.size());
  }

  //start room
  public Boolean start() {
    this.model = new GameModel();
    this.controller = new GameController(model);
    if (users.size() >= 2 && users.size() <= 4) {
      this.model.createPlayers(users.stream().map(User::getUsername).collect(Collectors.toList()));
      this.state = State.RUNNING;
      sendModel();
      return true;
    } else {
      return false;
    }
  }

  //send GameModel to all users subscribing to this room
  public void sendModel() {
    if (state == State.RUNNING)
    apiController.sendGameModel(id, model);
  }

  public boolean terminateRoom() {
    //delete room
    return true;
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
