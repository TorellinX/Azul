package de.lmu.ifi.sosylab.controller;

import static java.util.Objects.requireNonNull;

import de.lmu.ifi.sosylab.model.Color;
import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.model.Plate;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.PlayerState;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameController implements Controller{

  GameModel model;

  //View view;

  public GameController(GameModel model) {
    this.model = requireNonNull(model);
  }

  public void placeTilesFromPlateEvent(Plate plate, Color color, Player player, int row) {
    model.pickTilesFromPlate(plate,color,player, row);
  }

  public void placeTilesFromTableCenterEvent(Color color, Player player, int row) {
    model.pickTilesFromTableCenter(color, player, row);
  }


}
