package de.lmu.ifi.sosylab.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Player class holding information about a player.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "nickname")
public class Player {


  public String nickname;


  public PlayerState playerState = PlayerState.READY;

  int score = 0;
  //private boolean isHost;

  PlayerBoard playerBoard;

  /**
  /**
   * Builds a player with state defined by argument.
   *
   * @param nickname string of player nickname
   * @param state    state the player is initiated with
   */
  public Player(String nickname, PlayerState state) {
    this.nickname = nickname;
    this.playerState = state;
    this.playerBoard = new PlayerBoard();
  }

  public Player() {

  }


  /**
   * Builds a player with predefined state "ready".
   *
   * @param nickname string of player nickname
   */
  public Player(String nickname) {
    this.nickname = nickname;
    //this.state = PlayerState.READY;
    this.playerBoard = new PlayerBoard();
  }


  public String getNickname() {
    return nickname;
  }



  /**
   * Get the player board of the current player instance.
   *
   * @return player board instance
   */
  public PlayerBoard getPlayerBoard() {
    List<PlayerBoard> boards = new ArrayList<>();
    boards.add(playerBoard);
    List<PlayerBoard> unmodifiableBoards = Collections.unmodifiableList(boards);
    return unmodifiableBoards.get(0);
  }

  public PlayerState getPlayerState() {
    return playerState;
  }

  public int getScore() {
    return score;
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

  public void setPlayerState(PlayerState playerState) {
    this.playerState = playerState;
  }


}
