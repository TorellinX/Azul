package de.lmu.ifi.sosylab.client;

import static de.lmu.ifi.sosylab.view.ColorSchemes.classic;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.controller.GameController;
import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.view.ColorSchemes.ColorScheme;
import de.lmu.ifi.sosylab.view.PlayingView;
import java.lang.reflect.Type;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

@Slf4j
public class ClientGame extends StompSessionHandlerAdapter {

  String roomid;
  ClientApplication clientApplication;
  String username;
  private ColorScheme colorScheme = classic;

  public ClientGame(String roomid, String username, ClientApplication clientApplication) {
    this.roomid = roomid;
    this.username = username;
    this.clientApplication = clientApplication;
  }

  @SneakyThrows
  @Override
  public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
    session.subscribe("/topic/room/" + roomid + "/model", this);
    log.info("New session: {}", session.getSessionId());
  }

  @Override
  public void handleException(StompSession session, StompCommand command, StompHeaders headers,
      byte[] payload, Throwable exception) {
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
    //log.info("Received: {}", (Plate) payload);;
    System.out.println(payload);
    GameModel model = (GameModel) payload;
    Controller controller = new GameController(model);

    // SwingUtilities.invokeLater(() -> {
    PlayingView playingView = new PlayingView(model.getPlayers().size(),
        model.getPlayerNames(), colorScheme, controller, model);

    playingView.setVisible(true);
    //});

    System.out.println(model.getState());
  }
}
