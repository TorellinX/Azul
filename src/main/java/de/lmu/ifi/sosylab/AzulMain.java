package de.lmu.ifi.sosylab;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.controller.GameController;
import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.view.MainMenuView;

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

    GameModel model = new GameModel();
    Controller controller = new GameController(model);
    MainMenuView view = new MainMenuView(controller, model);

    model.addPropertyChangeListener(view);

  }
}
