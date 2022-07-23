package de.lmu.ifi.sosylab.server.controller;


import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.model.Plate;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.PlayerBoard;
import de.lmu.ifi.sosylab.model.TableCenter;
import de.lmu.ifi.sosylab.server.Room;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class APIController {

  //RoomManager roomManager = new RoomManager();

  // Send message if client connects to server

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  @MessageMapping("/secured/room")
  public void sendSpecific(@Payload String msg) {
  }

  @MessageMapping("/game/newUser")
  @SendTo("/topic/public")
  public void newUser(@Payload String message) {
    System.out.println("newUser: " + message);
  }

  @MessageMapping("/game/create")
  @SendTo("/topic/rooms")
  public List<Room> create(String name) {
    System.out.println(name);

    //return roomManager.roomsList;
    return null;
  }


  @MessageMapping("/game/plates")
  @SendTo("/topic/messages")
  public List<Plate> greeting(String message) throws Exception {
    System.out.println("greetings: " + message);
    Thread.sleep(1000); // simulated delay
    List<String> players = List.of("Player1", "Player2", "Player3", "Player4");
    GameModel model = new GameModel();
    //de.lmu.ifi.sosylab.controller.Controller controller = new GameController(model);
    model.createPlayers(players);
    System.out.println("here");
    return model.getPlates();
  }

  @MessageMapping("/game/room/{roomId}/model")
  @SendTo("/topic/room/{roomId}/model")
  public GameModel model(String message) throws Exception {
    System.out.println("model: " + message);
    Thread.sleep(1000); // simulated delay
    List<String> players = List.of("Player1", "Player222", "Player3", "Player4");
    GameModel model = new GameModel();
    //de.lmu.ifi.sosylab.controller.Controller controller = new GameController(model);
    model.createPlayers(players);
    System.out.println("here");
    de.lmu.ifi.sosylab.controller.Controller controller = new de.lmu.ifi.sosylab.controller.GameController(
        model);
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
    System.out.println("getPlayer :" + message);
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
    System.out.println("getPlayerBoard: " + message);
    Thread.sleep(500); // simulated delay
    List<String> players = List.of("Player1", "Player2", "Player3", "Player4");
    GameModel model = new GameModel();
    //de.lmu.ifi.sosylab.controller.Controller controller = new GameController(model);
    model.createPlayers(players);
    return model.getPlayerToMove().getPlayerBoard();
  }


  //publish GameModel to a specific topic with roomId
  //@MessageMapping("/game/model/{roomId}")
  //send current GameModel to all clients in the room
  public void sendGameModel(@DestinationVariable String roomId, GameModel model) {
    this.messagingTemplate.convertAndSend("/topic/room/" + roomId + "/model", model);
  }


}
