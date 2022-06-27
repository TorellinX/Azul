package de.lmu.ifi.sosylab;

import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.PlayerState;
import java.util.List;

// TODO: refine JavaDoc

/**
 * Starts the Azul Game.
 */
public class AzulMain {

  public static void main(String[] args) {
    List<Player> players = List.of(
        new Player("player1", PlayerState.READY),
        new Player("player2", PlayerState.READY)
    );
    GameModel model = new GameModel(players);

    System.out.println(model.getPlayerNames());
    // TODO: start view.

    model.test();


  }
}
