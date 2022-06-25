package de.lmu.ifi.sosylab;

import de.lmu.ifi.sosylab.model.AzulModel;
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
    AzulModel model = new AzulModel(players);
    // TODO: start view.
    /*AzulModel model = new AzulModel(testPlayers);
    System.out.println(model.getPlayers());
    System.out.println(model.getState());
    System.out.println((model.getTable().getPlates()));
    System.out.println(model.getTable().pickSameColorTiles(model.getTable().getPlates().get(0),
        ((ColorTile) model.getTable().getPlates().get(0).get(0)).getColor()));*/
  }
}
