package de.lmu.ifi.sosylab;

import de.lmu.ifi.sosylab.view.StartMenuView;
import java.io.IOException;
import java.util.Scanner;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

// TODO: refine JavaDoc

/**
 * Starts the Azul Game.
 */
public class AzulMain {

  /**
   * Main method of the Azul game app.
   *
   * @param args unused
   */
  public static void main(String[] args) {

    // GameModel model = new GameModel();
    // Controller controller = new GameController(model);
    // MainMenuView view = new MainMenuView(controller, model);

    //controller.setView(menuView);
    //View view = GameView(model, controller);
    //controller.setView(view);
    //model.addPropertyChangeListener(view);
    // controller.start();
    StartMenuView startView = new StartMenuView();

  }
}
