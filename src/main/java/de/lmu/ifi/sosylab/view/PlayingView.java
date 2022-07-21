package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.controller.GameController;
import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.view.ColorSchemes.ColorScheme;

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
  public Object addActionlistener;
  private JPanel menu;
  JComboBox<String> menuItems = new JComboBox<String>(
      new String[]{"- menu -", "restart", "leave", "end game"});
  private DrawboardTableCenter drawboardTableCenter;
  private int playerCount;
  private List<String> nicknames;
  private List<Player> players;
  private Controller controller;
  private GameModel model;
  private DrawPlayerBoard[] playerBoards;
  private ColorScheme colorScheme;
  //private DrawBackground drawBackground;
  JComboBox<String> themesItems = new JComboBox<String>(
          new String[]{"- themes -", "Classic", "Beach", "Cosmic"});


  /**
   * Initializes the playing view.
   *
   * @param playerCount number of players
   * @param nicknames   list of nicknames of players
   */
  public PlayingView(int playerCount, List<String> nicknames) {
    super("Azul Playing View");
    this.playerCount = playerCount;
    List<String> unmodNameList = Collections.unmodifiableList(nicknames);
    this.nicknames = unmodNameList;
    this.model = new GameModel();
    this.controller = new GameController(model);

    if (controller.startGame(nicknames)) {
      this.players = model.getPlayers();
    } else {
      JOptionPane.showMessageDialog(null,
          "Game could not be started!",
          "Internal Error!", JOptionPane.ERROR_MESSAGE);
      dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setResizable(true);
    setTitle("Azul");
    setLayout(new BorderLayout());
    setColors(ColorSchemes.classic); // TODO: the ability to choose the color scheme in the menu (ComboBox)
    getContentPane().setBackground(colorScheme.playingView());  // TODO: replace with image
    //TODO: implementieren setPlayingViewBackground()
    setPlayingViewBackground();

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
    menu.setBackground(colorScheme.menu());
    menu.add(menuItems);
    menu.add(themesItems);
    add(menu, BorderLayout.NORTH);

    // Mittlere Zone wird befüllt
    // Center definieren: table center
    JPanel playingViewCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
    playingViewCenter.setOpaque(false);
    //drawClassicBackground = new DrawBackground();
    drawboardTableCenter = new DrawboardTableCenter(model, controller, players.size());
    drawboardTableCenter.setColorScheme(colorScheme);
    drawboardTableCenter.setLayout(null);
    //playingViewCenter.add(drawClassicBackground);
    playingViewCenter.add(drawboardTableCenter);


    playerBoards = new DrawPlayerBoard[players.size()];
    for (int i = 0; i < players.size(); i++) {
      playerBoards[i] = new DrawPlayerBoard(players.get(i), controller, colorScheme);
      playerBoards[i].setColorScheme(colorScheme);
    }
    // Linkes und rechtes panel mit Playerboards belegen
    //drawBackground = new DrawBackground();
    JPanel playingViewLeft = new JPanel(new BorderLayout());
    playingViewLeft.setOpaque(false);
    playingViewLeft.setPreferredSize(playerBoards[0].playerBoardPreferredSize(1));
    //playingViewLeft.add(drawBackground);
    playingViewLeft.add(playerBoards[0], BorderLayout.NORTH);

    //drawBackground = new DrawBackground();
    JPanel playingViewRight = new JPanel(new BorderLayout());
    playingViewRight.setOpaque(false);
    playingViewRight.setPreferredSize(playerBoards[1].playerBoardPreferredSize(1));
    //playingViewRight.add(drawBackground);
    playingViewRight.add(playerBoards[1], BorderLayout.NORTH);

    if (players.size() == 3) {
      playingViewLeft.setPreferredSize(playerBoards[2].playerBoardPreferredSize(2));
      playingViewLeft.add(playerBoards[2], BorderLayout.SOUTH);
    }

    if (players.size() > 3) {
      playingViewRight.setPreferredSize(playerBoards[2].playerBoardPreferredSize(2));
      playingViewRight.add(playerBoards[2], BorderLayout.SOUTH);
      playingViewLeft.setPreferredSize(playerBoards[3].playerBoardPreferredSize(2));
      playingViewLeft.add(playerBoards[3], BorderLayout.SOUTH);
    }

    // Zuordnung ausführen.
    add(playingViewLeft, BorderLayout.WEST);
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
      playerBoards[0].setScoreLabel(players.get(0).getScore());
      playerBoards[0].setPlayerLabelBackgroundColor(players.get(0));
      playerBoards[1].setScoreLabel(players.get(1).getScore());
      playerBoards[1].setPlayerLabelBackgroundColor(players.get(1));
      if (players.size() > 2) {
        playerBoards[2].setScoreLabel(players.get(2).getScore());
        playerBoards[2].setPlayerLabelBackgroundColor(players.get(2));
      }
      if (players.size() > 3) {
        playerBoards[3].setScoreLabel(players.get(3).getScore());
        playerBoards[3].setPlayerLabelBackgroundColor(players.get(3));
      }
    }
  }

  private void setColors(ColorScheme colorScheme) {
    this.colorScheme = colorScheme;
  }


  private void setPlayingViewBackground() {
  }

  public void addActionlistener(ActionListener actionListener) {
  }

  public void getSelectedItem(){
    String themeSelected;
    themeSelected = (String) themesItems.getSelectedItem();
  }


}


