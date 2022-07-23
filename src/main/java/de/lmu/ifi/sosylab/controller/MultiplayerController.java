package de.lmu.ifi.sosylab.controller;

import de.lmu.ifi.sosylab.InformationWrapper;
import de.lmu.ifi.sosylab.client.ClientApplication;
import de.lmu.ifi.sosylab.model.Color;
import de.lmu.ifi.sosylab.model.Plate;
import de.lmu.ifi.sosylab.model.Player;
import java.util.List;

public class MultiplayerController implements Controller {

  ClientApplication clientApplication;

  public MultiplayerController(ClientApplication clientApplication) {
    this.clientApplication = clientApplication;
  }

  @Override
  public void start() {

  }

  @Override
  public boolean startGame(List<String> playerNames) {
    return false;
  }

  @Override
  public boolean pickTilesFromPlate(Color color, Player player, Plate plate) {
    InformationWrapper informationWrapper = new InformationWrapper();
    informationWrapper.setColor(color);
    informationWrapper.setPlayer(player);
    informationWrapper.setPlate(plate);
    System.out.println(informationWrapper);
    return clientApplication.pickTileFromPlate(informationWrapper);
  }

  @Override
  public boolean pickTilesFromTableCenter(Color color, Player player) {
    InformationWrapper informationWrapper = new InformationWrapper();
    informationWrapper.setColor(color);
    informationWrapper.setPlayer(player);
    return clientApplication.pickTileFromTableCenter(informationWrapper);
  }

  @Override
  public boolean placeTiles(Player player, int row) {
    InformationWrapper informationWrapper = new InformationWrapper();
    informationWrapper.setPlayer(player);
    informationWrapper.setRow(row);
    return clientApplication.placeTiles(informationWrapper);
  }

  @Override
  public void dispose() {

  }
}
