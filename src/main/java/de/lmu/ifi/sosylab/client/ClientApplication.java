package de.lmu.ifi.sosylab.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.lmu.ifi.sosylab.InformationWrapper;
import de.lmu.ifi.sosylab.server.Room;
import de.lmu.ifi.sosylab.server.User;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import lombok.Getter;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
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

  @Getter
  List<Room> rooms;

  User user;

  @Getter
  String roomId;


  /**
   * Constructor for http client.
   */
  public ClientApplication() {

  }

  /**
   * login to the server.
   *
   * @param username username
   */
  @SneakyThrows
  public String register(String username) {
    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
    MediaType mediaType = MediaType.parse("text/plain");
    RequestBody body = RequestBody.create(username, mediaType);
    Request request = new Request.Builder()
        .url("http://localhost:8080/api/user/register")
        .method("POST", body)
        .addHeader("Content-Type", "text/plain")
        .build();
    Response response = client.newCall(request).execute();
    user = new ObjectMapper().readValue(response.body().string(), User.class);
    return user.getUsername();
  }

  /**
   * Get a list of rooms from the server.
   *
   * @return list of rooms
   */
  public List<Room> requestRooms() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    System.out.println("Getting rooms...");
    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
    MediaType mediaType = MediaType.parse("text/plain");
    Request request = new Request.Builder()
        .url("http://localhost:8080/api/rooms")
        .method("GET", null)
        .build();
    Response response = client.newCall(request).execute();
    //get id and name of rooms from response
    rooms = new ObjectMapper().readValue(response.body().string(), new TypeReference<List<Room>>() {
    });
    return rooms;
  }

  /**
   * Create a new room.
   *
   * @param name name of the room
   * @return
   */

  public boolean createRoom(String name) throws IOException {
    System.out.println("Creating room...");
    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
    MediaType mediaType = MediaType.parse("application/javascript");
    RequestBody body = RequestBody.create(name, mediaType);
    Request request = new Request.Builder()
        .url("http://localhost:8080/api/rooms/create")
        .method("POST", body)
        .addHeader("Content-Type", "application/javascript")
        .build();
    Response response = client.newCall(request).execute();
    if (response.isSuccessful()) {
      System.out.println("Room created");
      return true;
    } else {
      System.out.println("Room creation failed");
      return false;
    }
  }

  /**
   * Join a room.
   *
   * @param roomId id of the room
   * @return
   */
  public boolean joinRoom(String roomId) throws IOException {
    System.out.println("Joining room..." + roomId);
    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(
        "{\"userToken\": \"" + user.getToken() + "\",\"roomId\": \"" + roomId + "\"}", mediaType);
    System.out.println(body);
    Request request = new Request.Builder()
        .url("http://localhost:8080/api/rooms/join")
        .method("POST", body)
        .addHeader("Content-Type", "application/json")
        .build();
    Response response = client.newCall(request).execute();
    System.out.println(response.body().string());
    if (response.isSuccessful()) {
      System.out.println("Room joined");
      this.roomId = roomId;
      return true;
    } else {
      System.out.println("Room join failed");
      return false;
    }
  }

  @SneakyThrows
  public boolean startGameRequest(String roomId) {
    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
    MediaType mediaType = MediaType.parse("text/plain");
    RequestBody body = RequestBody.create("", mediaType);
    Request request = new Request.Builder()
        .url("http://localhost:8080/api/rooms/id/" + roomId + "/start")
        .method("POST", body)
        .build();
    Response response = client.newCall(request).execute();
    if (response.isSuccessful()) {
      System.out.println("Game started");
      return true;
    } else {
      System.out.println("Game start failed");
      return false;
    }
  }


  public void startGame() {
    Thread t = new Thread(() -> {
      WebSocketClient webSocketClient = new StandardWebSocketClient();
      WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
      stompClient.setMessageConverter(new MappingJackson2MessageConverter());
      stompClient.setTaskScheduler(new ConcurrentTaskScheduler());

      String url = "ws://127.0.0.1:8080/websocket";
      StompSessionHandler sessionHandler = new ClientGame(roomId, user.getUsername(), this);
      stompClient.connect(url, sessionHandler);

      // check if connection is alive, if not reconnect
      new Scanner(System.in).nextLine(); //Don't close immediately.
    });
    t.start();
  }


  /**
   * Get the list of users in a room.
   *
   * @param roomId id of the room
   * @return
   */
  public List<String> getUsers(String roomId) throws IOException {
    System.out.println("Getting users...");
    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create("{\n    \"roomId\": \"" + roomId + "\"\n}", mediaType);
    Request request = new Request.Builder()
        .url("http://localhost:8080/api/rooms/users")
        .method("POST", body)
        .addHeader("Content-Type", "application/json")
        .build();
    Response response = client.newCall(request).execute();
    if (response.isSuccessful()) {
      System.out.println("Users retrieved");
      List<String> users = new ObjectMapper().readValue(response.body().string(),
          new TypeReference<List<String>>() {
          });
      return users;
    } else {
      System.out.println("Users retrieval failed");
      return null;
    }
  }


  /**
   * Authentication of player for http client.
   *
   * @param nickname player nickname
   * @param passwd   password
   * @return http client object
   */
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

  /**
   * http request for server (currently hardcoded, as there is only one, yet).
   *
   * @param uri    uri
   * @param uriGet GetMapping
   * @return server response
   */
  public String serverGet(String uri, String uriGet) {
    // noch ohne auth: replace in try -> authenticate(user, pwd)
    try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
      // send request
      HttpGet sendRequest = new HttpGet(uri + uriGet);
      // execute request
      HttpResponse getResponse = httpClient.execute(sendRequest);
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

  /**
   * Http post to server.
   *
   * @param uri     uri
   * @param uriPost PostMapping
   * @param entity  entity to post
   * @return response
   */
  public String serverPost(String uri, String uriPost, StringEntity entity) {
    // noch ohne auth: replace in try -> authenticate(user, pwd)
    try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
      // generate post
      HttpPost post = new HttpPost(uri + uriPost);
      post.setHeader("Accept", "application/json");
      post.setHeader("Content-type", "application/json");
      post.setEntity(entity);
      // execute request
      HttpResponse getResponse = httpClient.execute(post);
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

  // get room index by id
  public int getRoomIndex(String roomId) {
    for (int i = 0; i < rooms.size(); i++) {
      if (rooms.get(i).getId().equals(roomId)) {
        return i;
      }
    }
    return -1;
  }

  // end class

  @SneakyThrows
  public Boolean pickTileFromPlate(InformationWrapper informationWrapper) {
    System.out.println("Picking tile from plate...");
    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(String.valueOf(informationWrapper), mediaType);
    Request request = new Request.Builder()
        .url("http://localhost:8080/api/room/" + rooms + "/pickTilePlate")
        .method("POST", body)
        .addHeader("Content-Type", "application/json")
        .build();
    Response response = client.newCall(request).execute();
    return true;
  }

  @SneakyThrows
  public Boolean pickTileFromTableCenter(InformationWrapper informationWrapper) {
    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(String.valueOf(informationWrapper), mediaType);
    Request request = new Request.Builder()
        .url("http://localhost:8080/api/room/" + rooms + "/pickTileFromTableCenter")
        .method("POST", body)
        .addHeader("Content-Type", "application/json")
        .build();
    Response response = client.newCall(request).execute();
    if (response.isSuccessful()) {
      return true;
    } else {
      return false;
    }
  }

  @SneakyThrows
  public Boolean placeTiles(InformationWrapper informationWrapper) {
    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(String.valueOf(informationWrapper), mediaType);
    Request request = new Request.Builder()
        .url("http://localhost:8080/api/room/" + rooms + "/placeTile")
        .method("POST", body)
        .addHeader("Content-Type", "application/json")
        .build();
    Response response = client.newCall(request).execute();
    if (response.isSuccessful()) {
      return true;
    } else {
      return false;
    }
  }
}
