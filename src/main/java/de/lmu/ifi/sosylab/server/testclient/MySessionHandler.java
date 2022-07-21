package de.lmu.ifi.sosylab.server.testclient;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.controller.GameController;
import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.PlayerBoard;
import de.lmu.ifi.sosylab.model.TableCenter;
import de.lmu.ifi.sosylab.view.PlayingView;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nonapi.io.github.classgraph.json.JSONDeserializer;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

@Slf4j
public class MySessionHandler extends StompSessionHandlerAdapter {


  @SneakyThrows
  @Override
  public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
    session.subscribe("/topic/messages", this);
    session.send("/app/game/model", "test");
    // session.send("/app/game/newUser", "Player1-Test");
    // session.send("/app/game/newUser", "Player2-Test");
    log.info("New session: {}", session.getSessionId());
  }

  @Override
  public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
    exception.printStackTrace();
  }

  @Override
  public Type getPayloadType(StompHeaders headers) {
    return GameModel.class;
  }

  @SneakyThrows
  @Override
  public void handleFrame(StompHeaders headers, Object payload) {
    System.out.println(headers);
    //Plate plate = (Plate) payload;
    //System.out.println(plate.containsColor(Color.BLUE));
    //log.info("Received: {}", (Plate) payload);
    System.out.println(payload);
    GameModel model = (GameModel) payload;
    Controller controller = new GameController(model);
    PlayingView playingView = new PlayingView(model.getPlayers().size(), model.getPlayerNames(), controller, model);
    Thread.sleep(1000);

    // System.out.println(model.getState());
  }



}
