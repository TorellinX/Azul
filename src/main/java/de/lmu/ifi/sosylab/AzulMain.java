package de.lmu.ifi.sosylab;

import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.PlayerState;
import java.util.ArrayList;
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
    //... view sends through controller "String[] playerNames"
    // <mock>
    String[] playerNames = {"Player1", "Player2", "Player3", "Player4"};
    //  </mock>
    GameModel model = new GameModel(createPlayerObjects(playerNames));

    System.out.println(model.getPlayerNames());
    // TODO: start view.

  }

  /**
   * Creates player objects from the list of player names.
   *
   * @param playerNames the list of player names
   * @return a list of player objects
   */
  private static List<Player> createPlayerObjects(String[] playerNames) {
    List<Player> playerObjects = new ArrayList<>();
    for (String name : playerNames) {
      playerObjects.add(new Player(name));
    }
    return playerObjects;
  }
}
