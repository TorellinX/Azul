package de.lmu.ifi.sosylab.client;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

/**
 * Http client application for multiplayer game.
 */
public class ClientApplication {

  private String url = "ws://127.0.0.1:8080/websocket";
  private StompSessionHandler sessionHandler = null;

  public void ClientApplication() {

    WebSocketClient webSocketClient = new StandardWebSocketClient();
    WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
    stompClient.setMessageConverter(new MappingJackson2MessageConverter());
    stompClient.setTaskScheduler(new ConcurrentTaskScheduler());

    StompSessionHandler sessionHandler = new ClientMessageProcessor();
    this.sessionHandler = sessionHandler;

    stompClient.connect(url, sessionHandler);

    new Scanner(System.in).nextLine(); //Don't close immediately.
  }


  public StompSessionHandler getSessionHandler() {
    return sessionHandler;
  }


}

  /*


  String user;
  String pwd = "empty";


  public ClientApplication(String nickname) {
    this.user = nickname;
  }


  private CloseableHttpClient authenticate(String nickname, String passwd) {

    CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    AuthScope scope = new AuthScope("http://localhost/", 8080);
    Credentials credentials = new UsernamePasswordCredentials(nickname, passwd);
    credentialsProvider.setCredentials(scope, credentials);
    HttpClientBuilder clientBuilder = HttpClients.custom();
    clientBuilder = clientBuilder.setDefaultCredentialsProvider(credentialsProvider);
    CloseableHttpClient httpClient = clientBuilder.build();

    return httpClient;

  }


  public String serverRequest() {
    // noch ohne auth: replace in try -> authenticate(user, pwd)
    try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
      // send request
      HttpGet sendRequest = new HttpGet("http://localhost:8080/api/test");
      // debug: print method
      System.out.println("Request: " + sendRequest.getMethod());
      // execute request
      HttpResponse getResponse = httpClient.execute(sendRequest);
      // debug: print status line
      System.out.println(getResponse.getStatusLine());
      // read response and return
      Scanner scan = new Scanner(getResponse.getEntity().getContent());
      String response = "";
      while (scan.hasNext()) {
        response += (scan.nextLine() + "\n");
      }
      return response;

    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }


  public String serverPost(String input) {
    // noch ohne auth: replace in try -> authenticate(user, pwd)
    try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
      // Build json array
      JSONArray names = new JSONArray();
      names.put("test1");
      names.put("test2");
      System.out.println(names.toString());
      StringEntity namesArray = new StringEntity(names.toString(), "UTF-8");
      // send request
      HttpPost post = new HttpPost("http://localhost:8080/api/start");
      post.setHeader("Accept", "application/json");
      post.setHeader("Content-type", "application/json");
      post.setEntity(namesArray);
      // debug: print method
      System.out.println("Request: " + post.getMethod());

      //TODO: clarify/build post struct

      // execute request
      HttpResponse getResponse = httpClient.execute(post);
      // debug: print status line
      System.out.println(getResponse.getStatusLine());
      // read response and return
      Scanner scan = new Scanner(getResponse.getEntity().getContent());
      String response = "";
      while (scan.hasNext()) {
        response += (scan.nextLine() + "\n");
      }
      return response;

    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

  }

  // end class
}
*/