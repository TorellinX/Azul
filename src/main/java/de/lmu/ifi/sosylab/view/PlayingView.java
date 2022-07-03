package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.model.Plate;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.PlayerState;
import de.lmu.ifi.sosylab.model.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * Displays the playing field.
 */

public class PlayingView extends JFrame implements PropertyChangeListener {

  @Serial
  private static final long serialVersionUID = 1L;
  
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
   * Constructor of Class.
   * @param playerCount Number of Players
   * @param nicknames Nicknames of Player
   * @param controller controller
   * @param model model
   */
  public PlayingView(int playerCount, List<String> nicknames, Controller controller,
      GameModel model) {
    this.playerCount = playerCount;
    this.nicknames = nicknames;
    this.player = model.getPlayers();
    this.controller = controller;
    this.model = model;

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setResizable(false);
    setSize(1300, 800);
    setTitle("Azul");
    setLayout(new BorderLayout());

    createPlayingView();

    startGame();

    for (Player player : model.getPlayers()) {
      if (player.getState() == PlayerState.TO_MOVE) {
        System.out.println("Active Player: " + player);
      } else {
        System.out.println("Inactive Player: " + player) ;
      }
    }

  }

  /**
   * Shows the Game.
   */
  private void startGame(){
    setVisible(true);
  }

  /**
   * Creates the view.
   * Adds the buttons and the associated ActionListeners.
   */

  private void createPlayingView() {
    Color backroundColor = new Color(135, 206, 250);
    //Oberes Panel wird mit Combobox gefüllt.
    JPanel panelUp = new JPanel();
    panelUp.setSize(1200, 75);
    panelUp.setLayout(new FlowLayout());
    JComboBox<String> menu = new JComboBox<String>(
        new String[]{"- menu -", "restart", "leave", "end game"});
    panelUp.add(menu);
    panelUp.setBackground(backroundColor);

    //Zeichenelemente werden übergeben.
    drawboardPlayerBoardLeft = new DrawboardPlayerBoardLeft(playerCount, nicknames, player);
    drawboardPlayerBoardRight = new DrawboardPlayerBoardRight(playerCount, nicknames, player);
    drawboardTableCenter = new DrawboardTableCenter(model);

    addButtonPlayboard();
    addButtonsPlayerOne();
    addActionListenerFirstPlayer();
    addButtonPlayerTwo();
    addActionListenerSecondPlayer();
    addActionListenerFactory();
    addActionListenerTableCenter();
    if(player.size() == 3){
      addButtonPlayerThree();
      addActionListenerThridPlayer();
    }
    if(player.size() == 4){
      addButtonPlayerThree();
      addActionListenerThridPlayer();

      addButtonPlayerFour();
      addActionListenerFourthPlayer();
    }

    drawboardPlayerBoardLeft.setLayout(null);
    drawboardPlayerBoardRight.setLayout(null);

    //Mittleres Panel wird mit Buttons gefüllt:

    drawboardTableCenter.setLayout(null);

    //Unteres Panel wird erzeugt.
    JPanel panelSouth = new JPanel();
    panelSouth.setBackground(backroundColor);

    //Teile des Borderlayouts werden mit Panels und Graphikelementen gefüllt.
    Container c = getContentPane();
    c.add(panelUp, BorderLayout.NORTH);
    c.add(panelSouth, BorderLayout.SOUTH);
    c.add(drawboardPlayerBoardRight, BorderLayout.EAST);
    c.add(drawboardPlayerBoardLeft, BorderLayout.WEST);
    c.add(drawboardTableCenter, BorderLayout.CENTER);


  }

  /**
   *Adds the buttons of player one.
   */

  private void addButtonsPlayerOne() {
    JButton firstrowu1button = new JButton();
    firstrowu1button.setBounds(145, 5, 35, 35);

    JButton secondrowu1button = new JButton();
    secondrowu1button.setBounds(110, 40, 70, 35);

    JButton thirdrowu1button = new JButton();
    thirdrowu1button.setBounds(75, 75, 105, 35);

    JButton fourthrowu1button = new JButton();
    fourthrowu1button.setBounds(40, 110, 140, 35);

    JButton fifthrowu1button = new JButton();
    fifthrowu1button.setBounds(5, 145, 175, 35);

    JButton floorlineu1button = new JButton();
    floorlineu1button.setBounds(5, 205, 245, 35);

    buttonsFirstPlayer = new ArrayList<>();
    buttonsFirstPlayer.add(firstrowu1button);
    buttonsFirstPlayer.add(secondrowu1button);
    buttonsFirstPlayer.add(thirdrowu1button);
    buttonsFirstPlayer.add(fourthrowu1button);
    buttonsFirstPlayer.add(fifthrowu1button);
    buttonsFirstPlayer.add(floorlineu1button);
    
    drawboardPlayerBoardLeft.add(firstrowu1button);
    drawboardPlayerBoardLeft.add(secondrowu1button);
    drawboardPlayerBoardLeft.add(thirdrowu1button);
    drawboardPlayerBoardLeft.add(fourthrowu1button);
    drawboardPlayerBoardLeft.add(fifthrowu1button);
    drawboardPlayerBoardLeft.add(floorlineu1button);

    for (int i = 0; i < buttonsFirstPlayer.size(); i++) {
      buttonsFirstPlayer.get(i).setOpaque(false);
      buttonsFirstPlayer.get(i).setContentAreaFilled(false);
      buttonsFirstPlayer.get(i).setBorderPainted(false);
    }
  }

  /**
   *Adds the buttons of player two.
   */
  private void addButtonPlayerTwo() {
    JButton firstrowu2button = new JButton();
    firstrowu2button.setBounds(145, 5, 35, 35);

    JButton secondrowu2button = new JButton();
    secondrowu2button.setBounds(110, 40, 70, 35);

    JButton thirdrowu2button = new JButton();
    thirdrowu2button.setBounds(75, 75, 105, 35);

    JButton fourthrowu2button = new JButton();
    fourthrowu2button.setBounds(40, 110, 140, 35);

    JButton fifthrowu2button = new JButton();
    fifthrowu2button.setBounds(5, 145, 175, 35);

    JButton floorlineu2button = new JButton();
    floorlineu2button.setBounds(5, 205, 245, 35);

    buttonsSecondPlayer = new ArrayList<>();
    buttonsSecondPlayer.add(firstrowu2button);
    buttonsSecondPlayer.add(secondrowu2button);
    buttonsSecondPlayer.add(thirdrowu2button);
    buttonsSecondPlayer.add(fourthrowu2button);
    buttonsSecondPlayer.add(fifthrowu2button);
    buttonsSecondPlayer.add(floorlineu2button);

    drawboardPlayerBoardRight.add(firstrowu2button);
    drawboardPlayerBoardRight.add(secondrowu2button);
    drawboardPlayerBoardRight.add(thirdrowu2button);
    drawboardPlayerBoardRight.add(fourthrowu2button);
    drawboardPlayerBoardRight.add(fifthrowu2button);
    drawboardPlayerBoardRight.add(floorlineu2button);

    for(int i = 0; i < buttonsSecondPlayer.size(); i++) {
      buttonsSecondPlayer.get(i).setOpaque(false);
      buttonsSecondPlayer.get(i).setContentAreaFilled(false);
      buttonsSecondPlayer.get(i).setBorderPainted(false);
    }
  }

  /**
   *Adds the buttons of player three.
   */
  private void addButtonPlayerThree() {
    JButton firstrowu3button = new JButton();
    firstrowu3button.setBounds(145, 305, 35, 35);

    JButton secondrowu3button = new JButton();
    secondrowu3button.setBounds(110, 340, 70, 35);

    JButton thirdrowu3button = new JButton();
    thirdrowu3button.setBounds(75, 375, 105, 35);

    JButton fourthrowu3button = new JButton();
    fourthrowu3button.setBounds(40, 410, 140, 35);

    JButton fifthrowu3button = new JButton();
    fifthrowu3button.setBounds(5, 445, 175, 35);

    JButton floorlineu3button = new JButton();
    floorlineu3button.setBounds(5, 505, 245, 35);

    buttonsThridPlayer = new ArrayList<>();
    buttonsThridPlayer.add(firstrowu3button);
    buttonsThridPlayer.add(secondrowu3button);
    buttonsThridPlayer.add(thirdrowu3button);
    buttonsThridPlayer.add(fourthrowu3button);
    buttonsThridPlayer.add(fifthrowu3button);
    buttonsThridPlayer.add(floorlineu3button);

    drawboardPlayerBoardLeft.add(firstrowu3button);
    drawboardPlayerBoardLeft.add(secondrowu3button);
    drawboardPlayerBoardLeft.add(thirdrowu3button);
    drawboardPlayerBoardLeft.add(fourthrowu3button);
    drawboardPlayerBoardLeft.add(fifthrowu3button);
    drawboardPlayerBoardLeft.add(floorlineu3button);

    for (int i = 0; i < buttonsThridPlayer.size(); i++) {
      buttonsThridPlayer.get(i).setOpaque(false);
      buttonsThridPlayer.get(i).setContentAreaFilled(false);
      buttonsThridPlayer.get(i).setBorderPainted(false);
    }
  }

  /**
   *Adds the buttons of player four.
   */

  private void addButtonPlayerFour() {
    JButton firstrowu4button = new JButton();
    firstrowu4button.setBounds(145, 305, 35, 35);

    JButton secondrowu4button = new JButton();
    secondrowu4button.setBounds(110, 340, 70, 35);

    JButton thirdrowu4button = new JButton();
    thirdrowu4button.setBounds(75, 375, 105, 35);

    JButton fourthrowu4button = new JButton();
    fourthrowu4button.setBounds(40, 410, 140, 35);

    JButton fifthrowu4button = new JButton();
    fifthrowu4button.setBounds(5, 445, 175, 35);

    JButton floorlineu4button = new JButton();
    floorlineu4button.setBounds(5, 505, 245, 35);

    buttonsFourthPlayer = new ArrayList<>();
    buttonsFourthPlayer.add(firstrowu4button);
    buttonsFourthPlayer.add(secondrowu4button);
    buttonsFourthPlayer.add(thirdrowu4button);
    buttonsFourthPlayer.add(fourthrowu4button);
    buttonsFourthPlayer.add(fifthrowu4button);
    buttonsFourthPlayer.add(floorlineu4button);


    drawboardPlayerBoardRight.add(firstrowu4button);
    drawboardPlayerBoardRight.add(secondrowu4button);
    drawboardPlayerBoardRight.add(thirdrowu4button);
    drawboardPlayerBoardRight.add(fourthrowu4button);
    drawboardPlayerBoardRight.add(fifthrowu4button);
    drawboardPlayerBoardRight.add(floorlineu4button);

    for (int i = 0; i < buttonsFourthPlayer.size(); i++) {
      buttonsFourthPlayer.get(i).setOpaque(false);
      buttonsFourthPlayer.get(i).setContentAreaFilled(false);
      buttonsFourthPlayer.get(i).setBorderPainted(false);
    }
  }

  /**
   *Adds the buttons for the Playbord.
   */
  private void addButtonPlayboard() {

    positionButtonsFactory = new IntPair[]{new IntPair(17, 17), new IntPair(58, 17), new IntPair(17, 58), new IntPair(58, 58), new IntPair(167, 17), new IntPair(208, 17), new IntPair(167, 58), new IntPair(208, 58),
        new IntPair(317, 17), new IntPair(358, 17), new IntPair(317, 58), new IntPair(358, 58), new IntPair(92, 117), new IntPair(133, 117), new IntPair(92, 158), new IntPair(133, 158),
        new IntPair(242, 117), new IntPair(283, 117), new IntPair(242, 158), new IntPair(283, 158), new IntPair(92, 242), new IntPair(133, 242), new IntPair(92, 283), new IntPair(133, 283),
        new IntPair(242, 242), new IntPair(283, 242), new IntPair(242, 283), new IntPair(283, 283), new IntPair(92, 367), new IntPair(133, 367), new IntPair(92, 408), new IntPair(133, 408), new IntPair(242, 367), new IntPair(283, 367), new IntPair(242, 408), new IntPair(283, 408)};

    buttonsFactory = new ArrayList<>();
    for(int i = 0; i < positionButtonsFactory.length; i++) {
      buttonsFactory.add(new JButton());
      buttonsFactory.get(i).setBounds(positionButtonsFactory[i].getX(), positionButtonsFactory[i].getY(),
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
   *Adds ActionListeners for player one buttons.
   */

  private void addActionListenerFirstPlayer() {
    /*
    for(int i = 0; i < buttonsFirstPlayer.size(); i++){
      row = i;
      buttonsFirstPlayer.get(i).setName(Integer.toString(row));
      buttonsFirstPlayer.get(i).addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          final int final_i = Integer.parseInt(buttonsFirstPlayer.get(row).getName());
          controller.placeTiles(player.get(0), final_i);
          System.out.println(final_i);
        }
      });
    }

     */

    buttonsFirstPlayer.get(0).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P1" + 0);
        controller.placeTiles(player.get(0), 0);
      }
    });

    buttonsFirstPlayer.get(1).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P1" + 1);
        controller.placeTiles(player.get(0), 1);
      }
    });

    buttonsFirstPlayer.get(2).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P1" + 2);
        controller.placeTiles(player.get(0), 2);
      }
    });

    buttonsFirstPlayer.get(3).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P1" + 3);
        controller.placeTiles(player.get(0), 3);
      }
    });

    buttonsFirstPlayer.get(4).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P1" + 4);
        controller.placeTiles(player.get(0), 4);
      }
    });

    buttonsFirstPlayer.get(5).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P1" + 5);
        controller.placeTiles(player.get(0), -1);
      }
    });
  }

  /**
   *Adds ActionListeners for player two buttons.
   */
  private void addActionListenerSecondPlayer() {
    /*
    for(int i = 0; i < buttonsFirstPlayer.size(); i++){
      row = i;
      buttonsSecondPlayer.get(i).addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          controller.placeTiles(player.get(1), row);
        }
      });
    }

     */
    buttonsSecondPlayer.get(0).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P2" + 0);
        controller.placeTiles(player.get(1), 0);
      }
    });

    buttonsSecondPlayer.get(1).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P2" + 1);
        controller.placeTiles(player.get(1), 1);
      }
    });

    buttonsSecondPlayer.get(2).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P2" + 2);
        controller.placeTiles(player.get(1), 2);
      }
    });

    buttonsSecondPlayer.get(3).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P2" + 3);
        controller.placeTiles(player.get(1), 3);
      }
    });

    buttonsSecondPlayer.get(4).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P2" + 4);
        controller.placeTiles(player.get(1), 4);
      }
    });

    buttonsSecondPlayer.get(5).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P2" + 5);
        controller.placeTiles(player.get(1), -1);
      }
    });
  }

  /**
   *Adds ActionListeners for player three buttons.
   */
  private void addActionListenerThridPlayer() {
    /*
    for(int i = 0; i < buttonsThridPlayer.size(); i++){
      row = i;
      buttonsThridPlayer.get(i).addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          controller.placeTiles(player.get(2), row);
        }
      });
    }

     */
    buttonsThridPlayer.get(0).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P3" + 0);
        controller.placeTiles(player.get(2), 0);
      }
    });

    buttonsThridPlayer.get(1).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P3" + 1);
        controller.placeTiles(player.get(2), 1);
      }
    });

    buttonsThridPlayer.get(2).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P3" + 2);
        controller.placeTiles(player.get(2), 2);
      }
    });

    buttonsThridPlayer.get(3).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P3" + 3);
        controller.placeTiles(player.get(2), 3);
      }
    });

    buttonsThridPlayer.get(4).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P3" + 4);
        controller.placeTiles(player.get(1), 4);
      }
    });

    buttonsThridPlayer.get(5).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P3" + 5);
        controller.placeTiles(player.get(2), -1);
      }
    });
  }

  /**
   *Adds ActionListeners for player four buttons.
   */

  private void addActionListenerFourthPlayer() {
    /*
    for(int i = 0; i < buttonsFourthPlayer.size(); i++){
      row = i;
      buttonsFourthPlayer.get(i).addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          controller.placeTiles(player.get(3), row);
        }
      });
    }

     */
    buttonsFourthPlayer.get(0).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P4" + 0);
        controller.placeTiles(player.get(3), 0);
      }
    });

    buttonsFourthPlayer.get(1).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P4" + 1);
        controller.placeTiles(player.get(3), 1);
      }
    });

    buttonsFourthPlayer.get(2).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P4" + 2);
        controller.placeTiles(player.get(3), 2);
      }
    });

    buttonsFourthPlayer.get(3).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P4" + 3);
        controller.placeTiles(player.get(3), 3);
      }
    });

    buttonsFourthPlayer.get(4).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P4" + 4);
        controller.placeTiles(player.get(3), 4);
      }
    });

    buttonsFourthPlayer.get(5).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("P4" + 5);
        controller.placeTiles(player.get(3), -1);
      }
    });
  }

  /**
   *Adds ActionListeners for Factory buttons.
   */
  private void addActionListenerFactory(){
    /*
    for(int i = 0; i < buttonsFactory.size(); i++){
      coutnCach = i;
      row = i;
      buttonsFactory.get(i).addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        de.lmu.ifi.sosylab.model.Color color = drawboardTableCenter.getColorOfTileOnPlate(buttonsFactory.get(coutnCach).getX(), buttonsFactory.get(coutnCach).getY());
          System.out.println(buttonsFactory.indexOf(buttonsFactory.get(row)) + " " +buttonsFactory.get(row).getX() + " " + buttonsFactory.get(row).getY());
        if(controller.pickTilesFromPlate(color, model.getPlayers().get(model.getPlayerToMoveIndex()),
              model.getPlates().get(drawboardTableCenter.getPlate(buttonsFactory.get(coutnCach).getX(), buttonsFactory.get(coutnCach).getY())))){
          System.out.println("y");
          } else {
          System.out.println("N");
        }

        System.out.println("Test");
        }
      });
    }

     */


    for(int i = 0; i < buttonsFactory.size(); i++){
      final int final_i = i;
      buttonsFactory.get(i).addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          System.out.println(buttonsFactory.indexOf(buttonsFactory.get(final_i)) + " " +buttonsFactory.get(final_i).getX() + " " + buttonsFactory.get(final_i).getY());

          de.lmu.ifi.sosylab.model.Color color = drawboardTableCenter.getColorOfTileOnPlate(buttonsFactory.get(final_i).getX(), buttonsFactory.get(final_i).getY());

          if(controller.pickTilesFromPlate(color, model.getPlayers().get(model.getPlayerToMoveIndex()),
              model.getPlates().get(drawboardTableCenter.getPlate(buttonsFactory.get(final_i).getX(), buttonsFactory.get(final_i).getY())))){
            System.out.println("y");
          } else {
            System.out.println("N");
          }
        }
      });
    }

  }

  /**
   *Adds ActionListeners for Table Center buttons.
   */
  private void addActionListenerTableCenter() {
    for (int i = 0; i < buttonsTable.size(); i++) {
      final int final_i = i;
      buttonsTable.get(i).addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          System.out.println(buttonsTable.indexOf(buttonsTable.get(final_i)) + " " +buttonsTable.get(final_i).getX() + " " + buttonsTable.get(final_i).getY());

          de.lmu.ifi.sosylab.model.Color color = drawboardTableCenter.getColorOfTileTableCenter(buttonsTable.get(final_i).getX(), buttonsTable.get(final_i).getY());

          if(controller.pickTilesFromTableCenter(color, model.getPlayers().get(model.getPlayerToMoveIndex()))){
            System.out.println("pickTilesFromTableCenter: Yes");
          } else {
            System.out.println("pickTilesFromTableCenter: No");
          }

        }
      });
    }


  }

  /**
   *Will be informed when the model is updated.
   */
  @Override
  public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
    SwingUtilities.invokeLater(() -> handleModelUpdate(propertyChangeEvent));
  }

  /**
   * Redraws the playing field.
   * @param event
   */

  private void handleModelUpdate(PropertyChangeEvent event){
    if(event.getPropertyName().equals("Model changed")) {
      System.out.println("model changed");
      repaint();
    }
  }
}


