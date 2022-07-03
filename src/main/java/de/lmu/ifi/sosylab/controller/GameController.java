package de.lmu.ifi.sosylab.controller;

import static java.util.Objects.requireNonNull;

import de.lmu.ifi.sosylab.model.Color;
import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.model.Plate;
import de.lmu.ifi.sosylab.model.PlateState;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.RoundState;
import de.lmu.ifi.sosylab.model.State;
import java.util.List;

public class GameController implements Controller {

  GameModel model;
  //View view;

  public GameController(GameModel model) {
    this.model = requireNonNull(model);
  }

  public boolean startGame(List<String> playerNames) {
    if (playerNames.size() > 4 || playerNames.size() < 2) {
      return false;
    } else {
      model.createPlayers(playerNames);
      model.setState(State.RUNNING);
      return true;
    }
  }

  public boolean pickTilesFromPlate(Color color, Player player, Plate plate) {
    if (player != model.getPlayers().get(model.getPlayerToMoveIndex())) {
      // pickTile from non-active player
      return false;
    }
    if (model.getRoundState() != RoundState.WAIT) {
      // pickTile not allowed in another round state
      return false;
    }
    if (!model.getPlates().stream().anyMatch(p -> p == plate)) {
      return false;
    }
    if (plate.getState() == PlateState.EMPTY) {
      return false;
    }
    if (!plate.containsColor(color)) {
      return false;
    }
    return model.pickTilesFromPlate(plate, color);
  }

  public boolean pickTilesFromTableCenter(Color color, Player player) {
    if (player != model.getPlayers().get(model.getPlayerToMoveIndex())) {
      // pickTile from non-active player
      return false;
    }
    if (model.getRoundState() != RoundState.WAIT) {
      // pickTile not allowed in another round state
      return false;
    }
    if (!model.getTableCenter().getColorTiles().stream()
        .anyMatch(colorTile -> colorTile.getColor() == color)) {
      return false;
    }
    return model.pickTilesFromTableCenter(color, player);
  }

/*  public boolean placePlateTiles(Plate plate, Color color, Player player, int rowIndex) {
    if (player.getPlayerBoard().countFreeFieldsInRow(rowIndex) > 0
        && player.getPlayerBoard().getPatternLineColor(rowIndex) == color) {
      model.pickTilesFromPlate(plate, color, player, rowIndex);
      return true;
    } else {
      return false;
    }
  }

  public boolean placeTableCenterTiles(Color color, Player player, int rowIndex) {
    if (player.getPlayerBoard().countFreeFieldsInRow(rowIndex) > 0
        && player.getPlayerBoard().getPatternLineColor(rowIndex) == color) {
      model.pickTilesFromTableCenter(color, player, rowIndex);
      return true;
    } else {
      return false;
    }
  }*/

  public boolean placeTiles(Player player, int row) {
    return model.setTiles(player, row);
  }
}
