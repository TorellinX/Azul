package de.lmu.ifi.sosylab.controller;

import static java.util.Objects.requireNonNull;

import de.lmu.ifi.sosylab.model.Color;
import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.model.Plate;
import de.lmu.ifi.sosylab.model.PlateState;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.State;
import java.util.List;

public class GameController implements Controller{

  GameModel model;

  //View view;

  public GameController(GameModel model) {
    this.model = requireNonNull(model);
  }

  public void startGame(List<String> playerNames) {
    model.createPlayers(playerNames);
    model.setState(State.RUNNING);
  }

  public boolean pickTilesFromPlate(Plate plate, Color color) {
    if(!model.getPlates().stream().anyMatch(p -> p == plate)){
      return false;
    }
    if(plate.getState() == PlateState.EMPTY){
      return false;
    }
    if(!plate.containsColor(color)){
      return false;
    }
    return true;
  }

  public boolean pickTilesFromTableCenter(Color color) {
    if (model.getTableCenter().getColorTiles().stream().anyMatch(colorTile -> colorTile.getColor() == color)) {
      return false;
    }
    return true;
  }

  public boolean placePlateTiles(Plate plate, Color color, Player player, int rowIndex) {
    if (player.getPlayerBoard().countFreeFieldsInRow(rowIndex) > 0 && player.getPlayerBoard().getPatternLineColor(rowIndex) == color) {
      model.pickTilesFromPlate(plate, color, player, rowIndex);
      return true;
    }
    else {
      return false;
    }
  }

  public boolean placeTableCenterTiles(Color color, Player player, int rowIndex) {
    if (player.getPlayerBoard().countFreeFieldsInRow(rowIndex) > 0 && player.getPlayerBoard().getPatternLineColor(rowIndex) == color) {
      model.pickTilesFromTableCenter(color, player, rowIndex);
      return true;
    }
    else {
      return false;
    }
  }


}
