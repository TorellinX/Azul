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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping("/api")
public class router {

  GameModel model;
  Controller controller;
  List<String> players;


  public router() {
    //model = new GameModel();
    //controller = new GameController(model);
    //players = List.of("Player1", "Player2", "Player3", "Player4");
    //model.createPlayers(players);
    }

    @PostMapping("/start")
    public List<String> players(@RequestBody List<String> players) {
      System.out.println(players);
      this.players = players;
      start();
      return this.players;
    }

    public void start() {
      model = new GameModel();
      controller = new GameController(model);
      model.createPlayers(players);
      //PlayingView playingView = new PlayingView(players.size(), players, controller, model);
      //playingView.setVisible(true);
    }


  @GetMapping("/plates")
  public List<Plate> getPlates(){
    return model == null ? null : model.getPlates();
  }

  @GetMapping("/getPlayers")
  public List<Player> getPlayers(){
    return model == null ? null : model.getPlayers();
  }

  @GetMapping("/getState")
  public State getState(){
    return model == null ? null : model.getState();
  }


  @GetMapping("/getTableCenter")
  public State getTableCenter(){
    return model == null ? null: model.getState();
  }

  @GetMapping("/test")
  public String test() {
    return "test";
  }


}
