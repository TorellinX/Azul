package de.lmu.ifi.sosylab.model;

import java.util.Objects;

/**
 * Player class holding information about a player.
 */
public class Player {

  private final String nickname;
  private PlayerState state;
  private int score = 0;
  //private boolean isHost;
  PlayerBoard playerBoard;

  /**
   * Builds a player with state defined by argument.
   *
   * @param nickname string of player nickname
   * @param state state the player is initiated with
   */
  public Player(String nickname, PlayerState state) {
    this.nickname = nickname;
    this.state = state;
    this.playerBoard = new PlayerBoard();
  }

  /**
   * Builds a player with predefined state "ready".
   *
   * @param nickname string of player nickname
   */
  public Player(String nickname) {
    this.nickname = nickname;
    this.state = PlayerState.READY;
    this.playerBoard = new PlayerBoard();
  }


  public String getNickname() {
    return nickname;
  }

  public PlayerBoard getPlayerBoard() {
    return playerBoard;
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
