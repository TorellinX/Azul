package de.lmu.ifi.sosylab.server.testclient;

import java.util.Scanner;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

public class TestClient {

  public static void main(String[] args) {
    WebSocketClient webSocketClient = new StandardWebSocketClient();
    WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
    stompClient.setMessageConverter(new MappingJackson2MessageConverter());
    stompClient.setTaskScheduler(new ConcurrentTaskScheduler());

    String url = "ws://127.0.0.1:8080/websocket";
    StompSessionHandler sessionHandler = new MySessionHandler();
    stompClient.connect(url, sessionHandler);

    // check if connection is alive, if not reconnect

    new Scanner(System.in).nextLine(); //Don't close immediately.
  }


}
