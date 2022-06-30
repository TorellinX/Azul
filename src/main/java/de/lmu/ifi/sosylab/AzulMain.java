package de.lmu.ifi.sosylab;

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
    // TODO: start view.

    //controller.startGame(String[] playerNames) ===> model.createPlayerObjects(String[] playerNames)
    // <mock>
    String[] playerNames = {"Player0", "Player1", "Player2", "Player3"};
    //  </mock>
    startGame(playerNames);
    // notifyObservers() ????
  }

  public static void startGame(String[] playerNames) {
    GameModel model = new GameModel(createPlayerObjects(playerNames));

    System.out.println(model.getPlayerNames());
    System.out.println("Active Player: " + model.getPlayerToMoveIndex());
    // controller.pickTile(Color color, Player player, Object place) ===> model.pickTile(Color color, Player player, Object place)
    model.pickTile(model.getPlates().get(0).getTiles().get(0).getColor(),
        model.getPlayers().get(model.getPlayerToMoveIndex()), model.getPlates().get(0));
    // controller.setToRow(Player player, int row)  ===> model.setToRow(Player player, int row)
    model.setToRow(model.getPlayers().get(model.getPlayerToMoveIndex()), 0);
    model.pickTile(((ColorTile) model.getTableCenter().getTiles().get(1)).getColor(),
        model.getPlayers().get(model.getPlayerToMoveIndex()), model.getTableCenter());
    model.setToRow(model.getPlayers().get(model.getPlayerToMoveIndex()), 3);

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
