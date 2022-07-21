package de.lmu.ifi.sosylab.client;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.controller.GameController;
import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.view.PlayingView;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

/**
 *  Message processor for Stomp Websocket client. Almost no direct calls, all "magic" :((
 */
public class ClientMessageProcessor extends StompSessionHandlerAdapter {


  /**
   * Implementation for <code>StompSessionHandlerAdapter</code>.
   * Once a connection is established, /topic/messages is subscribed to.
   */
    private Logger logger = LogManager.getLogger(ClientMessageProcessor.class);
    private StompSession session = null;

    @SneakyThrows
    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
      // Subscribe to current session
      session.subscribe("/topic/messages", this);
      this.session = session;

      // Testmessages -> was sollten die denn machen???
      // session.send("/app/game/model", "test");
      // session.send("/app/game/newUser", "Player1-Test");
      // session.send("/app/game/newUser", "Player2-Test");


      // Logging
      logger.info("New session: {}", session.getSessionId());
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
      logger.error("Got an exception", exception);
    }


    @Override
    public Type getPayloadType(StompHeaders headers) {
      return GameModel.class;
    }

  /**
   * Handler for STOMP communication frames.
   *
   * @param headers (no idea where from this is fed in)
   * @param payload (maybe from getPayloadType)
   */
    @SneakyThrows
    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
      System.out.println(headers);
      System.out.println(payload);
      GameModel model = (GameModel) payload;                // packed into frame to be sent
      Controller controller = new GameController(model);    // packed into frame to be sent

      logger.info("handleFrame end.");
    }


}
