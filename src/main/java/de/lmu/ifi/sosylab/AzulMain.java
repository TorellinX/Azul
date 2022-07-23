package de.lmu.ifi.sosylab;

import de.lmu.ifi.sosylab.view.StartMenuView;

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
    // ClientApplication clientApplication = new ClientApplication();
  }
}
