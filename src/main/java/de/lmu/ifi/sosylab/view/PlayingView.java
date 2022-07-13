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
  private DrawboardPlayerBoardLeft drawboardPlayerBoardLeft;
  private DrawboardPlayerBoardRight drawboardPlayerBoardRight;
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
    ;

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setResizable(false);
    // setSize(1300, 800);
    setTitle("Azul");
    setLayout(new BorderLayout());

    createPlayingView();

    addListeners();

    for (Player player : model.getPlayers()) {
      if (player.getState() == PlayerState.TO_MOVE) {
        System.out.println("Active Player: " + player);
      } else {
        System.out.println("Inactive Player: " + player);
      }
    }

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


    //Zeichenelemente werden übergeben.
//    drawboardPlayerBoardLeft = new DrawboardPlayerBoardLeft(playerCount, nicknames, player);
//    drawboardPlayerBoardRight = new DrawboardPlayerBoardRight(playerCount, nicknames, player);
    drawboardTableCenter = new DrawboardTableCenter(model);
    addButtonPlayboard();
/*
    addButtonsPlayerOne();
    addActionListenerFirstPlayer();
    addButtonPlayerTwo();
    addActionListenerSecondPlayer();
*/
    addActionListenerFactory();
    addActionListenerTableCenter();

/*    if (player.size() == 3) {
      addButtonPlayerThree();
      addActionListenerThridPlayer();
    }
    if (player.size() == 4) {
      addButtonPlayerThree();
      addActionListenerThridPlayer();

      addButtonPlayerFour();
      addActionListenerFourthPlayer();
    }
*/
    // drawboardPlayerBoardLeft.setLayout(null);
    // drawboardPlayerBoardRight.setLayout(null);

    //Mittleres Panel wird mit Buttons gefüllt:

    drawboardTableCenter.setLayout(null);

    //Unteres Panel wird erzeugt.
    JPanel panelSouth = new JPanel();
    panelSouth.setBackground(backroundColor);
    add(panelSouth, BorderLayout.SOUTH);

    //Weitere Teile des Borderlayouts werden mit Panels und Graphikelementen gefüllt.
    // Container c = getContentPane();


    JPanel playingViewLeft = new JPanel(new BorderLayout());
    DrawPlayerBoard firstPlayerBoard = new DrawPlayerBoard(player.get(0), controller);
    playingViewLeft.setPreferredSize(firstPlayerBoard.playerBoardPreferredSize());
    playingViewLeft.add(firstPlayerBoard, BorderLayout.NORTH);
    add(playingViewLeft, BorderLayout.WEST);

    JPanel playingViewRight = new JPanel(new BorderLayout());
    DrawPlayerBoard secondPlayerBoard = new DrawPlayerBoard(player.get(1), controller);
    playingViewRight.setPreferredSize(secondPlayerBoard.playerBoardPreferredSize());
    playingViewRight.add(secondPlayerBoard, BorderLayout.NORTH);
    add(playingViewRight, BorderLayout.EAST);

    add(drawboardTableCenter, BorderLayout.CENTER);

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
    }
  }
}


