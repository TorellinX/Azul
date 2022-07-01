package de.lmu.ifi.sosylab;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.controller.GameController;
import de.lmu.ifi.sosylab.model.ColorTile;
import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.model.PenaltyTile;
import de.lmu.ifi.sosylab.model.Plate;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.view.MainMenuView;
import java.util.ArrayList;
import java.util.Arrays;

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
    MainMenuView menuView = new MainMenuView(model, controller);

    //controller.setView(menuView);
    // TODO: start view.

    // testModel(model);
    testControllerModel(model, controller);

  }

  private static void testModel(GameModel model) {
    ArrayList<String> playerNames = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      playerNames.add("Player" + i);
    }
    model.createPlayers(playerNames);
    System.out.println(model.getPlayerNames());
    System.out.println("ROUND: " + model.getRound());
    System.out.print("Plates: ");
    for (Plate plate : model.getPlates()) {
      System.out.print(plate.getTiles() + ", ");
    }
    System.out.println();
    System.out.println("Active Player: " + model.getPlayerToMoveIndex());
    for (int i = 0; i < 9; i++) {
      model.pickTilesFromPlate(model.getPlates().get(i),
          model.getPlates().get(i).getTiles().get(0).getColor());
      model.setTiles(model.getPlayers().get(model.getPlayerToMoveIndex()), i % 4);
      model.pickTilesFromTableCenter(
          ((ColorTile) model.getTableCenter().getTiles().get(0)).getColor(),
          model.getPlayers().get(model.getPlayerToMoveIndex()));
      model.setTiles(model.getPlayers().get(model.getPlayerToMoveIndex()), i % 4);
    }
    model.pickTilesFromPlate(model.getPlates().get(5),
        model.getPlates().get(5).getTiles().get(0).getColor());
    model.setTiles(model.getPlayers().get(model.getPlayerToMoveIndex()), 5 % 4);
    while (model.getTableCenter().getTiles().size() != 0) {
      if (model.getTableCenter().getTiles().get(0) instanceof PenaltyTile) {
        break;
      }
      model.pickTilesFromTableCenter(
          ((ColorTile) model.getTableCenter().getTiles().get(0)).getColor(),
          model.getPlayers().get(model.getPlayerToMoveIndex()));
      model.setTiles(model.getPlayers().get(model.getPlayerToMoveIndex()), -1);
    }
    for (Player player : model.getPlayers()) {
      System.out.println(player.getNickname() + " Wall: ");
      for (boolean[] rowW : player.getPlayerBoard().getWall()) {
        System.out.println(Arrays.toString(rowW));
      }
      System.out.println();
    }
    System.out.print("Scores: ");
    for (Player player : model.getPlayers()) {
      System.out.print(player.getScore() + ", ");
    }
    System.out.println();
    System.out.println("ROUND: " + model.getRound());
    System.out.print("Plates: ");
    for (Plate plate : model.getPlates()) {
      System.out.print(plate.getTiles() + ", ");
    }
    System.out.println();
    System.out.println("TableCenter: " + model.getTableCenter().getTiles());
    //
    for (int i = 0; i < 9; i++) {
      model.pickTilesFromPlate(model.getPlates().get(i),
          model.getPlates().get(i).getTiles().get(0).getColor());
      model.setTiles(model.getPlayers().get(model.getPlayerToMoveIndex()), i % 4);
      model.setTiles(model.getPlayers().get(model.getPlayerToMoveIndex()), (i + 1) % 4);
    }
  }

  private static void testControllerModel(GameModel model, Controller controller) {
    ArrayList<String> playerNames = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      playerNames.add("Player" + i);
    }
    controller.startGame(playerNames);
    model.createPlayers(playerNames);
    System.out.println(model.getPlayerNames());
    System.out.println("ROUND: " + model.getRound());
    System.out.print("Plates: ");
    for (Plate plate : model.getPlates()) {
      System.out.print(plate.getTiles() + ", ");
    }
    System.out.println();
    System.out.println("Active Player: " + model.getPlayerToMoveIndex());
    for (int i = 0; i < 9; i++) {
      System.out.println("     controller.pickTilesFromPlate: " +
          controller.pickTilesFromPlate(model.getPlates().get(i).getTiles().get(0).getColor(),
              model.getPlayers().get(model.getPlayerToMoveIndex()),
              model.getPlates().get(i)));
      System.out.println("     controller.placeTiles: " +
          controller.placeTiles(model.getPlayers().get(model.getPlayerToMoveIndex()), i % 4));
      System.out.println("     pickTilesFromTableCenter: " +
          controller.pickTilesFromTableCenter(
              ((ColorTile) model.getTableCenter().getTiles().get(0)).getColor(),
              model.getPlayers().get(model.getPlayerToMoveIndex())));
      System.out.println("     controller.placeTiles: " +
          controller.placeTiles(model.getPlayers().get(model.getPlayerToMoveIndex()), i % 4));

      System.out.println("TableCenter: " + model.getTableCenter().getTiles());
    }
    System.out.println("     controller.pickTilesFromPlate: " +
        controller.pickTilesFromPlate(model.getPlates().get(5).getTiles().get(0).getColor(),
            model.getPlayers().get(model.getPlayerToMoveIndex()),
            model.getPlates().get(5)));
    System.out.println("     controller.placeTiles: " +
        controller.placeTiles(model.getPlayers().get(model.getPlayerToMoveIndex()), 5 % 4));
    int round = model.getRound();
    System.out.println(model.getTableCenter().getTiles());
    while (model.getTableCenter().getTiles().size() != 0) {
      if (model.getTableCenter().getTiles().get(0) instanceof PenaltyTile) {
        break;
      }
      controller.pickTilesFromTableCenter(
          ((ColorTile) model.getTableCenter().getTiles().get(0)).getColor(),
          model.getPlayers().get(model.getPlayerToMoveIndex()));
      System.out.println("     pickTilesFromTableCenter: D1, round: " + model.getRound());
      controller.placeTiles(model.getPlayers().get(model.getPlayerToMoveIndex()), -1);
    }
    for (Player player : model.getPlayers()) {
      System.out.println(player.getNickname() + " Wall: ");
      for (boolean[] rowW : player.getPlayerBoard().getWall()) {
        System.out.println(Arrays.toString(rowW));
      }
      System.out.println();
    }
    System.out.print("Scores: ");
    for (Player player : model.getPlayers()) {
      System.out.print(player.getScore() + ", ");
    }
    System.out.println();
    System.out.println("ROUND: " + model.getRound());
    System.out.print("Plates: ");
    for (Plate plate : model.getPlates()) {
      System.out.print(plate.getTiles() + ", ");
    }
    System.out.println();
    System.out.println("TableCenter: " + model.getTableCenter().getTiles());
    //
    for (int i = 0; i < 9; i++) {
      controller.pickTilesFromPlate(model.getPlates().get(i).getTiles().get(0).getColor(),
          model.getPlayers().get(model.getPlayerToMoveIndex()),
          model.getPlates().get(i));
      controller.placeTiles(model.getPlayers().get(model.getPlayerToMoveIndex()), i % 4);
      controller.placeTiles(model.getPlayers().get(model.getPlayerToMoveIndex()), (i + 1) % 4);
    }
  }
}
