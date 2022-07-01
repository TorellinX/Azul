package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;


public class PlayingView extends JFrame {
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


  public PlayingView(int playerCount, List<String> nicknames, List<Player> player) {
    this.playerCount = playerCount;
    this.nicknames = nicknames;
    this.player = player;


    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setResizable(false);
    setSize(1300, 800);
    setTitle("Azul");
    setLayout(new BorderLayout());

    createPlayingView();

    startGame();
  }

  private void startGame(){
    setVisible(true);
  }

  private void createPlayingView() {
    Color backroundColor = new Color(135, 206, 250);
    //Oberes Panel wird mit Combobox gefüllt.
    JPanel panelUp = new JPanel();
    panelUp.setSize(1200, 75);
    panelUp.setLayout(new FlowLayout());
    JComboBox<String> menu = new JComboBox<String>(new String[]{"- menu -", "restart", "leave", "end game"});
    panelUp.add(menu);
    panelUp.setBackground(backroundColor);

    //Zeichenelemente werden übergeben.
    drawboardPlayerBoardLeft = new DrawboardPlayerBoardLeft(playerCount, nicknames, player);
    drawboardPlayerBoardRight = new DrawboardPlayerBoardRight(playerCount, nicknames, player);
    drawboardTableCenter = new DrawboardTableCenter();

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

    addButtonsPlayerOne();
    addButtonPlayerTwo();
    if(player.size()==3){
      addButtonPlayerThree();
    }
    if(player.size() == 4){
      addButtonPlayerFour();
    }
    addButtonPlayboard();
  }

  private void addButtonsPlayerOne(){
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

    for(int i = 0; i < buttonsFirstPlayer.size(); i++){
      buttonsFirstPlayer.get(i).setVisible(false);
      buttonsFirstPlayer.get(i).setEnabled(true);
    }
  }

  private void addButtonPlayerTwo(){
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

    for(int i = 0; i < buttonsSecondPlayer.size(); i++){
      buttonsSecondPlayer.get(i).setVisible(false);
      buttonsSecondPlayer.get(i).setEnabled(true);
    }
  }
  private void addButtonPlayerThree(){
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

    drawboardPlayerBoardRight.add(firstrowu3button);
    drawboardPlayerBoardRight.add(secondrowu3button);
    drawboardPlayerBoardRight.add(thirdrowu3button);
    drawboardPlayerBoardRight.add(fourthrowu3button);
    drawboardPlayerBoardRight.add(fifthrowu3button);
    drawboardPlayerBoardRight.add(floorlineu3button);

    for(int i = 0; i < buttonsThridPlayer.size(); i++){
      buttonsThridPlayer.get(i).setVisible(false);
      buttonsThridPlayer.get(i).setEnabled(true);
    }
  }

  private void addButtonPlayerFour(){
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

    for(int i = 0; i < buttonsFourthPlayer.size(); i++){
      buttonsFourthPlayer.get(i).setVisible(false);
      buttonsFourthPlayer.get(i).setEnabled(true);
    }
  }

  private void addButtonPlayboard(){

    positionButtonsFactory = new IntPair[]{new IntPair(17, 17), new IntPair(58, 17), new IntPair(17, 58), new IntPair(58, 58), new IntPair(167, 17), new IntPair(208, 17), new IntPair(167, 58), new IntPair(208, 58),
        new IntPair(317, 17), new IntPair(358, 17), new IntPair(317, 58), new IntPair(358, 58), new IntPair(92, 117), new IntPair(133, 117), new IntPair(92, 158), new IntPair(133, 158),
        new IntPair(242, 117), new IntPair(283, 117), new IntPair(242, 158), new IntPair(283, 158), new IntPair(92, 242), new IntPair(133, 242), new IntPair(92, 283), new IntPair(133, 283),
        new IntPair(242, 242), new IntPair(283, 242), new IntPair(242, 283), new IntPair(283, 283), new IntPair(92, 367), new IntPair(133, 367), new IntPair(92, 408), new IntPair(133, 408), new IntPair(242, 367), new IntPair(283, 367), new IntPair(242, 408), new IntPair(283, 408)};

    buttonsFactory = new ArrayList<>();
    for(int i = 0; i < positionButtonsFactory.length; i++){
      buttonsFactory.add(new JButton());
      buttonsFactory.get(i).setBounds(positionButtonsFactory[i].getX(), positionButtonsFactory[i].getY(), widthOfButtons, hightOfButtons);
      buttonsFactory.get(i).setVisible(false);
      buttonsFactory.get(i).setEnabled(true);
    }

    for(int j = 0; j < buttonsFactory.size(); j++){
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
    for(int count = 0; count < positionButtonTable.size(); count++){
      buttonsTable.add(new JButton());
      buttonsTable.get(count).setBounds(positionButtonTable.get(count).getX(), positionButtonTable.get(count).getY(), widthOfButtons, hightOfButtons);
      buttonsTable.get(count).setVisible(false);
      buttonsTable.get(count).setEnabled(true);
    }

    for(int m = 0; m < buttonsTable.size(); m++){
      drawboardTableCenter.add(buttonsTable.get(m));
    }
  }

  private void addActionListener(){



  }
}

