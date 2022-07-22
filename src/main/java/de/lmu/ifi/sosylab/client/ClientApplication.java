package de.lmu.ifi.sosylab.client;

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

import java.io.IOException;
import java.util.Scanner;

/**
 * Http client application for multiplayer game.
 */
public class ClientApplication {


  /**
   * Constructor for http client.
   */
  public ClientApplication() {

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
   * @param uri       uri
   * @param uriGet    GetMapping
   * @return          server response
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
   * @param uri       uri
   * @param uriPost   PostMapping
   * @param entity    entity to post
   * @return          response
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

  // end class
}
