package de.lmu.ifi.sosylab.controller;

import de.lmu.ifi.sosylab.model.Color;
import de.lmu.ifi.sosylab.model.Plate;
import de.lmu.ifi.sosylab.model.Player;

public interface Controller {

  /**
   * place Tiles selected from Plate on PlayerBoard
   * @param plate Plate from where the Tiles are coming.
   * @param color Color of the selected Tiles.
   * @param player current Player.
   * @param row selected Row.
   */
  void placeTilesFromPlateEvent(Plate plate, Color color, Player player, int row);

  /**
   * place Tiles selected from TableCenter on PlayerBoard.
   * @param color Color of the selected Tiles.
   * @param player current Player.
   * @param row selected Row.
   */
  void placeTilesFromTableCenterEvent(Color color, Player player, int row);


  }
