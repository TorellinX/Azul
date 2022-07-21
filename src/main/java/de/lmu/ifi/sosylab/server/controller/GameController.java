package de.lmu.ifi.sosylab.server.controller;


import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.model.Plate;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.PlayerBoard;
import de.lmu.ifi.sosylab.model.TableCenter;
import de.lmu.ifi.sosylab.server.RoomManager;
import de.lmu.ifi.sosylab.server.Rooms;
import java.util.List;
import org.springframework.boot.autoconfigure.data.ConditionalOnRepositoryType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

  RoomManager roomManager = new RoomManager();

  // Send message if client connects to server

  @MessageMapping("/game/newUser")
  @SendTo("/topic/public")
  public void newUser(@Payload String message) {
    System.out.println("newUser: " + message);
  }

  @MessageMapping("/game/create")
  @SendTo("/topic/rooms")
  public List<Rooms> create(String name) {
    System.out.println(name);
    roomManager.createRoom(name, "klklfjfdk");
    return roomManager.roomsList;
  }


  @MessageMapping("/game/plates")
  @SendTo("/topic/messages")
  public List<Plate> greeting(String message) throws Exception {
    System.out.println(message);
    Thread.sleep(1000); // simulated delay
    List<String> players = List.of("Player1", "Player2", "Player3", "Player4");
    GameModel model = new GameModel();
    //de.lmu.ifi.sosylab.controller.Controller controller = new GameController(model);
    model.createPlayers(players);
    System.out.println("here");
    return model.getPlates();
  }

  @MessageMapping("/game/model")
  @SendTo("/topic/messages")
  public GameModel model(String message) throws Exception {
    System.out.println(message);
    Thread.sleep(1000); // simulated delay
    List<String> players = List.of("Player1", "Player2", "Player3", "Player4");
    GameModel model = new GameModel();
    //de.lmu.ifi.sosylab.controller.Controller controller = new GameController(model);
    model.createPlayers(players);
    System.out.println("here");
    de.lmu.ifi.sosylab.controller.Controller controller = new de.lmu.ifi.sosylab.controller.GameController(model);
    //PlayingView playingView = new PlayingView(model.getPlayers().size(), model.getPlayerNames(), controller, model);
    //playingView.setVisible(true);
    return model;
  }

  @MessageMapping("/game/tablecenter")
  @SendTo("/topic/messages")
  public TableCenter tableCenter() throws Exception {
    Thread.sleep(500); // simulated delay
    List<String> players = List.of("Player1", "Player2", "Player3", "Player4");
    GameModel model = new GameModel();
    //de.lmu.ifi.sosylab.controller.Controller controller = new GameController(model);
    model.createPlayers(players);
    return model.getTableCenter();
  }

  @MessageMapping("/game/player")
  @SendTo("/topic/messages")
  public Player getPlayer(String message) throws Exception {
    System.out.println(message);
    Thread.sleep(500); // simulated delay
    List<String> players = List.of("Player1", "Player2", "Player3", "Player4");
    GameModel model = new GameModel();
    //de.lmu.ifi.sosylab.controller.Controller controller = new GameController(model);
    model.createPlayers(players);
    return model.getPlayerToMove();
  }

  @MessageMapping("/game/playerboard")
  @SendTo("/topic/messages")
  public PlayerBoard getPlayerBoard(String message) throws Exception {
    System.out.println(message);
    Thread.sleep(500); // simulated delay
    List<String> players = List.of("Player1", "Player2", "Player3", "Player4");
    GameModel model = new GameModel();
    //de.lmu.ifi.sosylab.controller.Controller controller = new GameController(model);
    model.createPlayers(players);
    return model.getPlayerToMove().getPlayerBoard();
  }

  @MessageMapping("/process-message") // /app/process-message
  @SendTo("/topic/messages")
  public String processMessage(String message) throws Exception {
    System.out.println("Hello, " + message);
    Thread.sleep(1000); // simulated delay
    return "Hello, " + message + "!";
  }


}
