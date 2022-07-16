package de.lmu.ifi.sosylab.view;

import static java.util.Objects.requireNonNull;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.controller.GameController;
import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.PlayerState;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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

  private ArrayList<JButton> buttonsFirstPlayer;
  private ArrayList<JButton> buttonsSecondPlayer;
  private ArrayList<JButton> buttonsThridPlayer;
  private ArrayList<JButton> buttonsFourthPlayer;

  private ArrayList<JButton> buttonsFactory;
  private ArrayList<JButton> buttonsTable;

  private IntPair[] positionButtonsFactory;
  private ArrayList<IntPair> positionButtonTable;
  private int widthOfButtons = 35;
  private int hightOfButtons = 35;

  private int playerCount;
  private List<String> nicknames;
  private List<Player> player;
  private Controller controller;
  private GameModel model;
  private DrawPlayerBoard firstPlayerBoard;
  private DrawPlayerBoard secondPlayerBoard;
  private DrawPlayerBoard thirdPlayerBoard;
  private DrawPlayerBoard fourthPlayerBoard;

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
    setResizable(false);
    // setSize(1300, 800);
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
    drawboardTableCenter = new DrawboardTableCenter(model, player.size());
    addButtonPlayboard();
    addActionListenerFactory();
    addActionListenerTableCenter();
    drawboardTableCenter.setLayout(null);

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
    add(drawboardTableCenter, BorderLayout.CENTER);
    add(playingViewRight, BorderLayout.EAST);

  }


  /**
   * Adds the buttons for the Playbord.
   */
  private void addButtonPlayboard() {

    positionButtonsFactory = new IntPair[]{new IntPair(17, 17), new IntPair(58, 17),
        new IntPair(17, 58), new IntPair(58, 58), new IntPair(167, 17),
        new IntPair(208, 17),
        new IntPair(167, 58), new IntPair(208, 58),
        new IntPair(317, 17), new IntPair(358, 17), new IntPair(317, 58),
        new IntPair(358, 58),
        new IntPair(92, 117), new IntPair(133, 117), new IntPair(92, 158),
        new IntPair(133, 158),
        new IntPair(242, 117), new IntPair(283, 117), new IntPair(242, 158),
        new IntPair(283, 158),
        new IntPair(92, 242), new IntPair(133, 242), new IntPair(92, 283),
        new IntPair(133, 283),
        new IntPair(242, 242), new IntPair(283, 242), new IntPair(242, 283),
        new IntPair(283, 283),
        new IntPair(92, 367), new IntPair(133, 367), new IntPair(92, 408),
        new IntPair(133, 408),
        new IntPair(242, 367), new IntPair(283, 367), new IntPair(242, 408),
        new IntPair(283, 408)};

    buttonsFactory = new ArrayList<>();
    for (int i = 0; i < positionButtonsFactory.length; i++) {
      buttonsFactory.add(new JButton());
      buttonsFactory.get(i)
          .setBounds(positionButtonsFactory[i].getX(), positionButtonsFactory[i].getY(),
              widthOfButtons, hightOfButtons);
      buttonsFactory.get(i).setOpaque(false);
      buttonsFactory.get(i).setContentAreaFilled(false);
      buttonsFactory.get(i).setBorderPainted(false);
    }

    for (int j = 0; j < buttonsFactory.size(); j++) {
      drawboardTableCenter.add(buttonsFactory.get(j));
    }

    positionButtonTable = new ArrayList<>();

    positionButtonTable.add(new IntPair(25, 505));

    for (int i = 505; i <= 625; i += 40) {
      for (int j = 105; j <= 345; j += 40) {
        positionButtonTable.add(new IntPair(j, i));
      }
    }

    buttonsTable = new ArrayList<>();
    for (int count = 0; count < positionButtonTable.size(); count++) {
      buttonsTable.add(new JButton());
      buttonsTable.get(count)
          .setBounds(positionButtonTable.get(count).getX(), positionButtonTable.get(count).getY(),
              widthOfButtons, hightOfButtons);
      buttonsTable.get(count).setOpaque(false);
      buttonsTable.get(count).setContentAreaFilled(false);
      buttonsTable.get(count).setBorderPainted(false);

    }

    for (int m = 0; m < buttonsTable.size(); m++) {
      drawboardTableCenter.add(buttonsTable.get(m));
    }
  }


  /**
   * Adds ActionListeners for Factory buttons.
   */
  private void addActionListenerFactory() {
    /*
    for (int i = 0; i < buttonsFactory.size(); i++) {
      coutnCach = i;
      row = i;
      buttonsFactory.get(i).addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          de.lmu.ifi.sosylab.model.Color color = drawboardTableCenter.getColorOfTileOnPlate(
              buttonsFactory.get(coutnCach).getX(), buttonsFactory.get(coutnCach).getY());
          System.out.println("ActionEvent from buttonsFactory #" + coutnCach + " X:"
              + buttonsFactory.get(coutnCach).getX() + " Y:" + buttonsFactory.get(coutnCach).getY()
              + ", color " + color);
          System.out.println(buttonsFactory.indexOf(buttonsFactory.get(row)) + " "
          +buttonsFactory.get(row).getX() + " " + buttonsFactory.get(row).getY());
        if(controller.pickTilesFromPlate(color, model.getPlayers().get(
        model.getPlayerToMoveIndex()), model.getPlates().get(
              drawboardTableCenter.getPlate(buttonsFactory.get(coutnCach).getX(),
              buttonsFactory.get(coutnCach).getY())))){
          System.out.println("y");
          } else {
          System.out.println("N");
        }


          System.out.println("ActionEvent from buttonsFactory #");
        }
      });
    }

     */

    for (int i = 0; i < buttonsFactory.size(); i++) {
      final int final_i = i;
      buttonsFactory.get(i).addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          System.out.println(
              buttonsFactory.indexOf(buttonsFactory.get(final_i)) + " " + buttonsFactory.get(
                  final_i).getX() + " " + buttonsFactory.get(final_i).getY());

          de.lmu.ifi.sosylab.model.Color color = drawboardTableCenter.getColorOfTileOnPlate(
              buttonsFactory.get(final_i).getX(), buttonsFactory.get(final_i).getY());

          if (controller.pickTilesFromPlate(color,
              model.getPlayers().get(model.getPlayerToMoveIndex()),
              model.getPlates().get(
                  drawboardTableCenter.getPlate(buttonsFactory.get(final_i).getX(),
                      buttonsFactory.get(final_i).getY())))) {
            System.out.println("y");
          } else {
            System.out.println("N");
          }
        }
      });
    }
  }

  /**
  * Adds ActionListeners for Table Center buttons.
   */
  private void addActionListenerTableCenter() {
    for (int i = 0; i < buttonsTable.size(); i++) {
      final int final_i = i;
      buttonsTable.get(i).addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          System.out.println(
              buttonsTable.indexOf(buttonsTable.get(final_i)) + " " + buttonsTable.get(final_i)
                  .getX() + " " + buttonsTable.get(final_i).getY());

          de.lmu.ifi.sosylab.model.Color color = drawboardTableCenter.getColorOfTileTableCenter(
              buttonsTable.get(final_i).getX(), buttonsTable.get(final_i).getY());

          if (controller.pickTilesFromTableCenter(color,
              model.getPlayers().get(model.getPlayerToMoveIndex()))) {
            System.out.println("pickTilesFromTableCenter: Yes");
          } else {
            System.out.println("pickTilesFromTableCenter: No");
          }
        }
      });
    }
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


