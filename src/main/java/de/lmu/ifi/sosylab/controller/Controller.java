package de.lmu.ifi.sosylab.controller;

import de.lmu.ifi.sosylab.model.Color;
import de.lmu.ifi.sosylab.model.Plate;
import de.lmu.ifi.sosylab.model.Player;
import java.util.List;

/**
 * Controller interface between view and model.
 */
public interface Controller {

  void start();

  /**
   * Start the Game with a List of player names.
   *
   * @param playerNames Lst of player names.
   */
  boolean startGame(List<String> playerNames);


  /**
   * Pick tiles from a Plate.
   *
   * @param plate Plate from where the tiles where selected.
   * @param color Color of the selected tiles.
   * @return can the tiles be picked
   */
  boolean pickTilesFromPlate(Color color, Player player, Plate plate);

  /**
   * Pick tiles from the table in the center.
   *
   * @param color Color of the selected tiles.
   * @return can the tiles be picked.
   */
  boolean pickTilesFromTableCenter(Color color, Player player);

  /**
   * Places selected tiles on PlayerBoard.
   *
   * @param player current Player.
   * @param row    selected Row.
   */
  boolean placeTiles(Player player, int row);


  public void dispose();

}
