package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.controller.GameController;
import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.PlayerState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serial;
import java.util.Collections;
import java.util.List;

/**
 * Graphic display of the playing view.
 */
public class PlayingView extends JFrame implements PropertyChangeListener {

  @Serial
  private static final long serialVersionUID = 1L;
  private JPanel menu;
  JComboBox<String> menuItems = new JComboBox<String>(
      new String[]{"- menu -", "restart", "leave", "end game"});
  private DrawboardTableCenter drawboardTableCenter;
  private int playerCount;
  private List<String> nicknames;
  private List<Player> player;
  private Controller controller;
  private GameModel model;
  private DrawPlayerBoard firstPlayerBoard;
  private DrawPlayerBoard secondPlayerBoard;
  private DrawPlayerBoard thirdPlayerBoard;
  private DrawPlayerBoard fourthPlayerBoard;
  private GraphicTablePanel graphicTablePanel;

  /**
   * Initializes the playing view.
   *
   * @param playerCount number of players
   * @param nicknames list of nicknames of players
   */
  public PlayingView(int playerCount, List<String> nicknames) {
    super("Azul Playing View");
    this.playerCount = playerCount;
    List<String> unmodNameList = Collections.unmodifiableList(nicknames);
    this.nicknames = unmodNameList;
    this.model = new GameModel();
    this.controller = new GameController(model);

    if (controller.startGame(nicknames)) {
      this.player = model.getPlayers();
    } else {
      JOptionPane.showMessageDialog(null,
              "Game could not be started!",
              "Internal Error!", JOptionPane.ERROR_MESSAGE);
      dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
    for (Player player : model.getPlayers()) {
      if (player.getState() == PlayerState.TO_MOVE) {
        System.out.println("Active Player: " + player);
      } else {
        System.out.println("Inactive Player: " + player);
      }
    }

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setResizable(true);
    setTitle("Azul");
    setLayout(new BorderLayout());
    createPlayingView();
    addListeners();
    pack();

    setVisible(true);
  }

  /**
   * Creates the view. Adds the buttons and the associated ActionListeners.
   */

  private void createPlayingView() {

    // Menu
    //Oberes Panel wird mit Combobox gefüllt.
    menu = new JPanel();
    // menu.setSize(1200, 75);
    menu.setLayout(new FlowLayout(FlowLayout.CENTER));
    Color backroundColor = new Color(135, 206, 250);
    menu.setBackground(backroundColor);
    menu.add(menuItems);
    add(menu, BorderLayout.NORTH);

    // Mittlere Zone wird befüllt
    // Center definieren: table center
    JPanel playingViewCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
    graphicTablePanel = new GraphicTablePanel<>();
    drawboardTableCenter = new DrawboardTableCenter(model, controller, player.size());
    drawboardTableCenter.setLayout(null);
    drawboardTableCenter.setOpaque(false);
    playingViewCenter.add(graphicTablePanel);
    playingViewCenter.add(drawboardTableCenter);



    //graphic = new JPanel(new BorderLayout());
    //graphic.setLayout(null);
    //add(graphic, BorderLayout.CENTER);
    //GraphicTable graphicTable = new GraphicTable();
    //graphic.add(graphicTable.backroundTablePanel);
    //graphic.setVisible(true);

    // Linkes und rechtes panel mit Playerboards belegen
    JPanel playingViewLeft = new JPanel(new BorderLayout());
    firstPlayerBoard = new DrawPlayerBoard(player.get(0), controller);
    playingViewLeft.setPreferredSize(firstPlayerBoard.playerBoardPreferredSize(1));
    playingViewLeft.add(firstPlayerBoard, BorderLayout.NORTH);

    JPanel playingViewRight = new JPanel(new BorderLayout());
    secondPlayerBoard = new DrawPlayerBoard(player.get(1), controller);
    playingViewRight.setPreferredSize(secondPlayerBoard.playerBoardPreferredSize(1));
    playingViewRight.add(secondPlayerBoard, BorderLayout.NORTH);

    if (player.size() > 2) {
      thirdPlayerBoard = new DrawPlayerBoard(player.get(2), controller);
      playingViewLeft.setPreferredSize(thirdPlayerBoard.playerBoardPreferredSize(2));
      playingViewLeft.add(thirdPlayerBoard, BorderLayout.SOUTH);
    }

    if (player.size() > 3) {
      fourthPlayerBoard = new DrawPlayerBoard(player.get(3), controller);
      playingViewRight.setPreferredSize(fourthPlayerBoard.playerBoardPreferredSize(2));
      playingViewRight.add(fourthPlayerBoard, BorderLayout.SOUTH);
    }

    // Zuordnung ausführen.
    add(playingViewLeft, BorderLayout.WEST);
    add(graphicTablePanel,BorderLayout.CENTER);
    add(playingViewCenter, BorderLayout.CENTER);
    add(playingViewRight, BorderLayout.EAST);

  }


   private void addListeners() {

    model.addPropertyChangeListener(this);
    menuItems.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (menuItems.getSelectedItem().equals("end game")) {
          System.exit(0);
        }
        System.out.println(menuItems.getSelectedItem());
      }

      private void dispose() {
      }
    });
  }

  /**
   * Will be informed when the model is updated.
   */
  @Override
  public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
    SwingUtilities.invokeLater(() -> handleModelUpdate(propertyChangeEvent));
  }

  /**
   * Redraws the playing field.
   *
   * @param event property change event
   */
  private void handleModelUpdate(PropertyChangeEvent event) {
    if (event.getPropertyName().equals("Model changed")) {
      repaint();
      firstPlayerBoard.setScoreLabel(player.get(0).getScore());
      firstPlayerBoard.setPlayerLabelBackgroundColor(player.get(0));
      secondPlayerBoard.setScoreLabel(player.get(1).getScore());
      secondPlayerBoard.setPlayerLabelBackgroundColor(player.get(1));
      if (player.size() > 2) {
        thirdPlayerBoard.setScoreLabel(player.get(2).getScore());
        thirdPlayerBoard.setPlayerLabelBackgroundColor(player.get(2));
      }
      if (player.size() > 3) {
        fourthPlayerBoard.setScoreLabel(player.get(3).getScore());
        fourthPlayerBoard.setPlayerLabelBackgroundColor(player.get(3));
      }
    }
  }
}


