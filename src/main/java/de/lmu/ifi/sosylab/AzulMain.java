package de.lmu.ifi.sosylab;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.controller.GameController;
import de.lmu.ifi.sosylab.model.Color;
import de.lmu.ifi.sosylab.model.ColorTile;
import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.PlayerState;
import java.util.ArrayList;
import java.util.Arrays;
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
    GameModel model = new GameModel();
    Controller controller = new GameController(model);

    //View view = GameView(model, controller);
    //controller.setView(view);

    // TODO: start view.
  }

  /**
   * Creates player objects from the list of player names.
   *
   * @param playerNames the list of player names
   * @return a list of player objects
   */
  private static List<Player> createPlayerObjects(String[] playerNames) {
    if (playerNames.length < 2 || playerNames.length > 4) {
      throw new IllegalArgumentException("Invalid number of player names, needs to be from 2 to 4");
    }
    List<Player> playerObjects = new ArrayList<>();
    for (String name : playerNames) {
      playerObjects.add(new Player(name));
    }
    return playerObjects;
  }
}
