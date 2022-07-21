package de.lmu.ifi.sosylab.server;

import de.lmu.ifi.sosylab.Authenticator;
import de.lmu.ifi.sosylab.InformationWrapper;
import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.model.*;

import java.util.List;

import org.springframework.web.bind.annotation.*;

/**
 * The class provides the interface for the API calls and processes them.
 */
@RestController
@RequestMapping("/api")
public class router {

  private Management management;

  /**
   * Constructor for the class.
   */
  public router() {
    management = new Management();
  }


  @GetMapping("/getInformationWrapper")
  public InformationWrapper getInforamtionWrapper() {
    InformationWrapper informationWrapper = new InformationWrapper();
    return informationWrapper;
  }

  @PostMapping("/createRoom")
  public InformationWrapper createRoom(@RequestBody InformationWrapper informationWrapper) {
    Authenticator authenticator = new Authenticator();
    InformationWrapper toReturn = new InformationWrapper();
    toReturn.setAuthenticatorRoom(authenticator);
    toReturn.setWasInputSuccesful(management.creatNewRoom(authenticator, informationWrapper.getAuthenticatorPlayer()));
    return toReturn;
  }

  @PostMapping("/start")
  public Boolean startGame(@RequestBody InformationWrapper informationWrapper) {
    return management.startGame(informationWrapper.getAuthenticatorRoom(), informationWrapper.getAuthenticatorPlayer());
  }

  @PostMapping("/addPlayer")
  public Boolean addPlayer(@RequestBody InformationWrapper informationWrapper) {
    return management.addPlayerToServer(informationWrapper.getAuthenticatorPlayer(), informationWrapper.getAuthenticatorRoom());

  }

  @PostMapping("/removePlayer")
  public Boolean removePlayer(@RequestBody InformationWrapper informationWrapper) {
    return management.removePlayerFromServer(informationWrapper.getAuthenticatorPlayer(), informationWrapper.getAuthenticatorRoom());
  }

  @GetMapping("/getRooms")
  public List<Room> getRooms() {
    return management.getAllRooms();
  }

  @PostMapping("/removeRoom")
  public Boolean removeRoom(@RequestBody InformationWrapper informationWrapper) {
    return management.removeRoom(informationWrapper.getAuthenticatorRoom(), informationWrapper.getAuthenticatorPlayer());
  }

  @PostMapping("/pickTilePlate")
  public Boolean pickTilePlate(@RequestBody InformationWrapper informationWrapper) {
    return management.pickTileFromPlate(informationWrapper);
  }

  @PostMapping("/pickTileFromTableCenter")
  public Boolean pickTileFromTableCenter(@RequestBody InformationWrapper informationWrapper) {
    return management.pickTileFromTableCenter(informationWrapper);
  }

  @PostMapping("/placeTile")
  public Boolean placeTile(@RequestBody InformationWrapper informationWrapper) {
    return management.placeTile(informationWrapper);
  }
}
