package de.lmu.ifi.sosylab;

import de.lmu.ifi.sosylab.model.AzulModel;
import de.lmu.ifi.sosylab.model.ColorTile;
import java.util.ArrayList;
import java.util.Arrays;

// TODO: refine JavaDoc
/**
 * Starts the Azul Game.
 */
public class AzulMain {

  public static void main(String[] args) {
    ArrayList<String> testPlayers = new ArrayList<>();
    testPlayers.add("Player1");
    testPlayers.add("Player2");

    AzulModel model = new AzulModel(testPlayers);
    System.out.println(model.getPlayers());
    System.out.println(model.getState());
    System.out.println((model.getTable().getPlates()));
    System.out.println(model.getTable().pickSameColorTiles(model.getTable().getPlates().get(0),
        ((ColorTile) model.getTable().getPlates().get(0).get(0)).getColor()));

  }
}
