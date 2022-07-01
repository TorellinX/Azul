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
    // <mock> TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT
    String[] playerNames = {"Player0", "Player1", "Player2", "Player3"};
    //  </mock> TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT

    startGame(playerNames);
  }

  public static void startGame(String[] playerNames) {
    GameModel model = new GameModel(createPlayerObjects(playerNames));
    //GameModel model = new GameModel();
    Controller controller = new GameController(model);
    //View view = GameView(model, controller);

    //controller.setView(view);
    // TODO: start view.

    // <test> TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT
    test(model);
    // </test> TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT

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

  private static void test(GameModel model) {
    System.out.println(model.getPlayerNames());
    System.out.print("Plates: ");
    for (Plate plate : model.getPlates()) {
      System.out.print(plate.getTiles() + ", ");
    }
    System.out.println();
    System.out.println("Active Player: " + model.getPlayerToMoveIndex());
    for (int i = 0; i < 9; i++) {
      // controller.pickTile(Color color, Player player, Object place) ===> model.pickTile(Color color, Player player, Object place)
      model.pickTile(model.getPlates().get(i).getTiles().get(0).getColor(),
          model.getPlayers().get(model.getPlayerToMoveIndex()), model.getPlates().get(i));
      // controller.setToRow(Player player, int row)  ===> model.setToRow(Player player, int row)
      model.setToRow(model.getPlayers().get(model.getPlayerToMoveIndex()), i % 4);
      model.pickTile(((ColorTile) model.getTableCenter().getTiles().get(0)).getColor(),
          model.getPlayers().get(model.getPlayerToMoveIndex()), model.getTableCenter());
      model.setToRow(model.getPlayers().get(model.getPlayerToMoveIndex()), i % 4);
    }
    model.pickTile(model.getPlates().get(5).getTiles().get(0).getColor(),
        model.getPlayers().get(model.getPlayerToMoveIndex()), model.getPlates().get(5));
    model.setToRow(model.getPlayers().get(model.getPlayerToMoveIndex()), 5 % 4);
    System.out.println("Plates: ");
    for (Plate plate : model.getPlates()) {
      System.out.print(plate.getTiles() + ", ");
    }
    System.out.println("TableCenter: " + model.getTableCenter().getTiles());
    while (model.getTableCenter().getTiles().size() != 0) {
      if (model.getTableCenter().getTiles().get(0) instanceof PenaltyTile) {
        break;
      }
      model.pickTile(((ColorTile) model.getTableCenter().getTiles().get(0)).getColor(),
          model.getPlayers().get(model.getPlayerToMoveIndex()), model.getTableCenter());
      model.setToRow(model.getPlayers().get(model.getPlayerToMoveIndex()), -1);
    }
    for (Player player : model.getPlayers()) {
      System.out.println(player.getNickname() + " Wall: ");
      for (boolean[] rowW : player.getPlayerBoard().getWall()) {
        System.out.println(Arrays.toString(rowW));
      }
      System.out.println();
    }
    System.out.println("TableCenter: " + model.getTableCenter().getTiles());
    System.out.println("Plates: ");
    for (Plate plate : model.getPlates()) {
      System.out.print(plate.getTiles() + ", ");
    }
    System.out.println("TableCenter: " + model.getTableCenter().getTiles());
    System.out.print("scores: ");
    for (Player player : model.getPlayers()) {
      System.out.print(player.getScore() + ", ");
    }
    //
    for (int i = 0; i < 9; i++) {
      model.pickTile(model.getPlates().get(i).getTiles().get(0).getColor(),
          model.getPlayers().get(model.getPlayerToMoveIndex()), model.getPlates().get(i));
      model.setToRow(model.getPlayers().get(model.getPlayerToMoveIndex()), i % 4);
      model.setToRow(model.getPlayers().get(model.getPlayerToMoveIndex()), (i + 1) % 4);
    }
  }
}
