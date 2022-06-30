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
    //... view sends through controller "String[] playerNames"
    // <mock>
    String[] playerNames = {"Player1", "Player2", "Player3", "Player4"};
    //  </mock>
    GameModel model = new GameModel(createPlayerObjects(playerNames));
    // notifyObservers() ????

    // TODO: start view.

    //... EVENT "pick tile" from view through controller with Color, Player  and place (Plate oder
    // TableCenter)
    // if (place instance of Plate) {
    //   pickTilesFromPlate(plate, color);
    // }
    // if (place instance of TableCenter) {
    //   pickTilesFromTableCenter(color, player);
    // }
    // <waiting for "set to row" event>

    //... EVENT "set to row" from view through controller with Player and row (patternLines (0-4) or
    // floorLine (-1))
    // if (!setPickedTiles(player, row)) {
    //    <waiting for "set to row" event>
    // }
    // notifyObservers() ????

    // ...

    System.out.println(model.getPlayerNames());
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
