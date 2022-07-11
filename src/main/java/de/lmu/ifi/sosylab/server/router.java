package de.lmu.ifi.sosylab.server;

import de.lmu.ifi.sosylab.AzulMain;
import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.controller.GameController;
import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.model.Plate;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.State;
import de.lmu.ifi.sosylab.model.TableCenter;
import de.lmu.ifi.sosylab.view.MainMenuView;
import de.lmu.ifi.sosylab.view.PlayingView;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class router {

  GameModel model;
  Controller controller;
  List<String> players;


  public router() {
    model = new GameModel();
    controller = new GameController(model);
    players = List.of("Player1", "Player2", "Player3", "Player4");
    model.createPlayers(players);
    }


  @GetMapping("/plates")
  public List<Plate> getPlates(){
    return model.getPlates();
  }

  @GetMapping("/getPlayers")
  public List<Player> getPlayers(){
    return model.getPlayers();
  }

  @GetMapping("/getState")
  public State getState(){
    return model.getState();
  }

  @GetMapping("/test")
  public String test() {
    return "test";
  }


}
