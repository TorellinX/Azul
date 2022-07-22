package de.lmu.ifi.sosylab.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

//@Component
public class WebSocketEventListener {

  /*private static final Logger log = LoggerFactory.getLogger(WebSocketEventListener.class);

  @Autowired
  private SimpMessageSendingOperations sendingOperations;

  @EventListener
  public void handleSessionConnected(SessionConnectedEvent event) {
    log.info("New connection! " + event.getMessage());
  }

  @EventListener
  public void handleWebSocketDisconnectListener(SessionConnectedEvent event) {

    log.info("Disconnected! " + event);
  }
  */
}
