package de.lmu.ifi.sosylab.controller;

import static java.util.Objects.requireNonNull;

import de.lmu.ifi.sosylab.model.Color;
import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.model.Plate;
import de.lmu.ifi.sosylab.model.Player;

public class Controller {

  GameModel model;

  public Controller(GameModel model) {
    this.model = requireNonNull(model);
  }

  public void placeTilesFromPlateEvent(Plate plate, Color color, Player player, int row) {
    model.pickTilesFromPlate(plate,color,player, row);
  }

  public void placeTilesFromTableCenterEvent(Color color, Player player, int row) {
    model.pickTilesFromTableCenter(color, player, row);
  }


}
