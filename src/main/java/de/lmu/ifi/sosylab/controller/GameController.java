package de.lmu.ifi.sosylab.controller;

import static java.util.Objects.requireNonNull;

import de.lmu.ifi.sosylab.model.Color;
import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.model.Plate;
import de.lmu.ifi.sosylab.model.PlateState;
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

  public void pickTilesFromPlateCheck(Plate plate, Color color) {
    if(!model.getPlates().stream().anyMatch(p -> p == plate)){
      //TODO: This Plate does not exist.
    }
    if(plate.getState() == PlateState.EMPTY){
      //TODO This Plate is empty.
    }
    if(!plate.containsColor(color)){
      //TODO: This Color does not exist on this Plate.
    }
    //TODO: Notify valid MOVE
  }

  public void pickTilesFromTableCenter(Color color) {
      if (model.getTableCenter().getColorTiles().stream().anyMatch(colorTile -> colorTile.getColor() == color)) {
        //TODO: Color not in TableCenter.
      }
  }

  public void placeTilesFromPlateEvent(Plate plate, Color color, Player player, int rowIndex) {
    if (player.getPlayerBoard().countFreeFieldsInRow(rowIndex) > 0 && player.getPlayerBoard().getPatternLineColor(rowIndex) == color) {
      model.pickTilesFromPlate(plate, color, player, rowIndex);
    }
    else {
      //TODO: notify Player, not placeable
    }
  }

  public void placeTilesFromTableCenterEvent(Color color, Player player, int rowIndex) {
    if (player.getPlayerBoard().countFreeFieldsInRow(rowIndex) > 0 && player.getPlayerBoard().getPatternLineColor(rowIndex) == color) {
      model.pickTilesFromTableCenter(color, player, rowIndex);
    }
    else {
      //TODO: notify Player, not placeable
    }
  }


}
