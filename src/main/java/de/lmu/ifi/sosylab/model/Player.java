package de.lmu.ifi.sosylab.model;

import java.util.ArrayList;
import java.util.Objects;

public class Player {

  private final String nickname;
  private PlayerState state;
  private int score = 0;
  //private boolean isHost;
  private PlayerBoard playerBoard;

  public Player(String nickname, PlayerState state) {
    this.nickname = nickname;
    this.state = state;
    this.playerBoard = new PlayerBoard();
  }

  public String getNickname() {
    return nickname;
  }

  public PlayerState getState() {
    return state;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Player player = (Player) o;
    return nickname.equals(player.nickname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nickname);
  }
}
