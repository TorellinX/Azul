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

  /**
   * Main method of the Azul game app.
   *
   * @param args unused
   */
  public static void main(String[] args) {
    List<Player> players = List.of(
        new Player("player1", PlayerState.READY),
        new Player("player2", PlayerState.READY)
    );
    GameModel model = new GameModel(players);

    System.out.println(model.getPlayerNames());
    // TODO: start view.
    /*AzulModel model = new AzulModel(testPlayers);
    System.out.println(model.getPlayers());
    System.out.println(model.getState());
    System.out.println((model.getTable().getPlates()));
    System.out.println(model.getTable().pickSameColorTiles(model.getTable().getPlates().get(0),
        ((ColorTile) model.getTable().getPlates().get(0).get(0)).getColor()));*/
  }
}
