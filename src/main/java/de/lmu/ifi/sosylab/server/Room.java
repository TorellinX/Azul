package de.lmu.ifi.sosylab.server;

import de.lmu.ifi.sosylab.InformationWrapper;
import de.lmu.ifi.sosylab.controller.GameController;
import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.model.State;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class Room {

  public String name;
  //public String password;
  @Getter
  public String id;
  public State state;

  @Getter
  private List<User> users = new ArrayList<>(4);
  private final GameModel model;
  private final GameController controller;


  public Room(String name) {
    this.name = name;
    this.id = generateToken();
    this.state = State.RUNNING;

    this.model = new GameModel();
    this.controller = new GameController(model);
  }

  //Generate a random Room token
  public static String generateToken() {
    String token = "";
    for (int i = 0; i < 6; i++) {
      token += (char) (Math.random() * 26 + 'a');
    }
    return token;
  }

  //add user to list
  public void addUser(User user) {
    System.out.println(users.size());
    users.add(user);
    System.out.println(users.size());
  }

  //start room
  public Boolean start() {
    state = State.RUNNING;
    return true;
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
