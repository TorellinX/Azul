package de.lmu.ifi.sosylab.server;

import lombok.Getter;
import lombok.Setter;

public class User {

  @Getter
  @Setter
  private String username;

  @Getter
  @Setter
  private String token;

  public User() {

  }

  // User constructor and auto generate token
  public User(String username) {
    this.username = username;
    this.token = generateToken();
  }

  // Generate random token
  private String generateToken() {
    String token = "";
    for (int i = 0; i < 10; i++) {
      token += (char) (Math.random() * 26 + 'a');
    }
    return token;
  }






}
