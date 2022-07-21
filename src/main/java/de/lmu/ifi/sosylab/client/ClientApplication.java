package de.lmu.ifi.sosylab.client;

import java.io.IOException;
import java.util.Scanner;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

/**
 * Http client application for multiplayer game.
 */
public class ClientApplication {

  String user;
  String pwd = "empty";

  /**
   * Constructor for http client.
   *
   * @param nickname player nickname
   */
  public ClientApplication(String nickname) {
    this.user = nickname;
  }

  /**
   * Authentication of player for http client.
   *
   * @param nickname  player nickname
   * @param passwd    password
   * @return          http client object
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
   * @return  server response
   */
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

  /**
   * Http post to server.
   *
   * @param input  input for server
   * @return       true if success
   */
  public boolean serverPost(String input) {
    // noch ohne auth: replace in try -> authenticate(user, pwd)
    try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
      // send request
      HttpPost post = new HttpPost("http://localhost:8080/api/start");
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
        System.out.println(scan.nextLine());
      }
      return true;

    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }

  }

  // end class
}
