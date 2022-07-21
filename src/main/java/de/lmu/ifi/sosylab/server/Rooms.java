package de.lmu.ifi.sosylab.server;

import de.lmu.ifi.sosylab.model.State;

public class Rooms {

  public String name;
  public String password;
  public String id;
  public State state;

  public Rooms(String name, String password) {
    this.name = name;
    this.password = password;
    this.id = generateToken();
    this.state = State.RUNNING;
  }

  public Rooms(String name) {
    this.name = name;
    this.id = generateToken();
    this.state = State.RUNNING;
  }

  //Generate a random Room token
  public static String generateToken() {
    String token = "";
    for (int i = 0; i < 6; i++) {
      token += (char) (Math.random() * 26 + 'a');
    }
    return token;
  }


}
