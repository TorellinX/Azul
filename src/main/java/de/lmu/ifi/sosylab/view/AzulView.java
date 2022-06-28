package main.java.de.lmu.ifi.sosylab.view;


import de.lmu.ifi.sosylab.view.GraphicAzul;
import de.lmu.ifi.sosylab.view.PlayingView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AzulView extends JFrame {

  private int width = 1200;
  private int hight = 700;
  private JFrame mainFrame;
  private CardLayout layout;

  private static final String MAIN_MENU = "mainMenu";
  private static final String LOCAL_GAME = "localGame";
  private static final String MULTIPLAYER = "multiplayer";
  private static final String PLAYING_VIEW = "playingview";

  private JPanel panel;
  //Button  Main Menu
  private JButton hotseat;
  private JButton multiplayer;
  //Buttons Creat Local Play modus
  private JButton addPlayer;
  private JButton removePlayer;
  private JButton backToMenu;
  private JButton startGame;
  private JTextField nicknameLocal;
  //Buttons creat Multiplayer
  private JButton connect;
  private JButton createRoom;
  private JButton leaveOnlineMode;
  private JTextField nicknameOnline;
  private JTable localPlayer;
  private String userNameOnline;
  private int anzahlAnSpielern = 0;
  private JButton openplayingfield;


  PlayingView playingviewframe = new PlayingView();



  public AzulView() {
    super("Azul");

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setResizable(false);
    setSize(width, hight);

    initialize();
    creatMainMenuPanel();
    createlocalplayerAddView();
    createMulitplayerView();
    openPlayingView();

    addActionListener();

    showMainMenu();

  }

  private void initialize() {
    layout = new CardLayout();
    panel = new JPanel(layout);
    setContentPane(panel);
  }

  private void creatMainMenuPanel() {
    //Panel mainMenu
    JPanel mainMenu = new JPanel(new BorderLayout());
    add(mainMenu, MAIN_MENU);
    //Create Panel for Graphics and Buttons
    JPanel graphic = new JPanel();
    JPanel buttons = new JPanel();
    GraphicAzul graphicAzul = new GraphicAzul();

    //Logo anzeige
    graphic.add(graphicAzul.azulPanel);
    graphic.setBackground(new Color(135, 206, 250));
    // Mittleres Panel mit Buttons
    buttons.setLayout(new FlowLayout());
    hotseat = new JButton("Hotseat");
    multiplayer = new JButton("Multiplayer");
    buttons.add(hotseat);
    buttons.add(multiplayer);
    buttons.setBackground(new Color(135, 206, 250));

    mainMenu.add(graphic, BorderLayout.NORTH);
    mainMenu.add(buttons, BorderLayout.CENTER);
  }

  private void createlocalplayerAddView() {
    //Panel LocalGameOverview
    JPanel localgame = new JPanel(new BorderLayout());
    add(localgame, LOCAL_GAME);
    //Panel für die Überschrift
    JPanel panelUp = new JPanel();
    JLabel newLocalGameText = new JLabel("New Local Game");
    panelUp.add(newLocalGameText);
    panelUp.setBackground(new Color(135, 206, 250));

    //Linkes Panel
    //panelLeft.add(nickNames);

    // Mittleres
    JPanel panelCenter = new JPanel();
    panelCenter.setLayout(new FlowLayout());
    nicknameLocal = new JTextField("Benutzername eingeben:", 50);

    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Player Nick");
    localPlayer = new JTable(model);

    JScrollPane nickNames = new JScrollPane(localPlayer);
    panelCenter.add(nicknameLocal);
    panelCenter.add(nickNames);
    panelCenter.setBackground(new Color(135, 206, 250));

    //rechtes
    JPanel panelRight = new JPanel();
    panelRight.setLayout(new FlowLayout());
    addPlayer = new JButton("Add Player");
    removePlayer = new JButton("Remove Player");
    panelRight.add(addPlayer);
    panelRight.add(removePlayer);
    panelRight.setBackground(new Color(135, 206, 250));

    //unten
    JPanel panelBottom = new JPanel();
    panelBottom.setLayout(new FlowLayout());
    startGame = new JButton("Start Game");
    backToMenu = new JButton("Back Game");
    panelBottom.add(startGame);
    panelBottom.add(backToMenu);
    panelBottom.setBackground(new Color(135, 206, 250));

    localgame.add(panelUp, BorderLayout.NORTH);
    localgame.add(panelBottom, BorderLayout.SOUTH);
    localgame.add(panelCenter, BorderLayout.CENTER);
    //localgame.add(panelLeft, BorderLayout.WEST);
    localgame.add(panelRight, BorderLayout.EAST);

  }

  private void createMulitplayerView() {
    //create Multiplayer View Panel
    JPanel multiplayerPanel = new JPanel(new BorderLayout());
    add(multiplayerPanel, MULTIPLAYER);

    multiplayerPanel.setBackground(new Color(135, 206, 250));

    //Oberes Panel
    JPanel panelUpMultiplayer = new JPanel();
    JLabel networkGame = new JLabel("Multiplayer");
    panelUpMultiplayer.add(networkGame);

    // Mittleres Panel
    JPanel panelCenterMultiplayer = new JPanel(new FlowLayout());
    nicknameOnline = new JTextField("Nickname eingeben", 50);
    String[] columNamesMultiplayer = {"Room ID", "Room Name", "Anzahl der Player"};
    Object[][] dataMultiplayer = {{"1", "Hier könnte ihre Werbung stehen", "2"}};
    JTable tableMultiplayer = new JTable(dataMultiplayer, columNamesMultiplayer);
    panelCenterMultiplayer.add(nicknameOnline);
    panelCenterMultiplayer.add(tableMultiplayer);

    //Unteres Panel
    JPanel panelBottomMultiplayer = new JPanel(new GridLayout(1, 3));
    connect = new JButton("Enter Room");
    leaveOnlineMode = new JButton("Leave");
    createRoom = new JButton("Creat new Room");

    panelBottomMultiplayer.add(connect);
    panelBottomMultiplayer.add(leaveOnlineMode);
    panelBottomMultiplayer.add(createRoom);

    multiplayerPanel.add(panelUpMultiplayer, BorderLayout.NORTH);
    multiplayerPanel.add(panelCenterMultiplayer, BorderLayout.CENTER);
    multiplayerPanel.add(panelBottomMultiplayer, BorderLayout.SOUTH);
  }


  private void openPlayingView() {


  }


  private void showMainMenu() {
    layout.show(getContentPane(), MAIN_MENU);
    setVisible(true);
  }

  private void showLocalMode() {
    layout.show(getContentPane(), LOCAL_GAME);
    setVisible(true);
  }

  private void showMultiplayer() {
    layout.show(getContentPane(), MULTIPLAYER);
    setVisible(true);
  }

  private void showGame() {
    //create Playing View Panel
    PlayingView playingView = new PlayingView();
    playingView.setSize(1200,700);
    playingView.setVisible(true);
  }

  private void addActionListener() {
    hotseat.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showLocalMode();
      }
    });

    multiplayer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showMultiplayer();
      }
    });

    addPlayer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        addPlayerToLocalGame(nicknameLocal.getText());
      }
    });

    removePlayer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        removePlayerLocalGame();
      }
    });

    backToMenu.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showMainMenu();
      }
    });

    startGame.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) { showGame();
      }
    });

    connect.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

      }
    });

    createRoom.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFrame createRoomFrame = new JFrame();
        createRoomFrame.setVisible(true);
        createRoomFrame.setSize(500, 400);
        createRoomFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createRoomFrame.setLayout(new BorderLayout());
        JLabel header = new JLabel("Room Name:", 50);
        JTextField textField = new JFormattedTextField();
        JButton addRoomtoServer = new JButton("Create Room");

        createRoomFrame.add(header, BorderLayout.NORTH);
        createRoomFrame.add(textField, BorderLayout.CENTER);
        createRoomFrame.add(addRoomtoServer, BorderLayout.SOUTH);
      }
    });

    leaveOnlineMode.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showMainMenu();
      }
    });
  }

  private void addPlayerToLocalGame(String nickname){
    DefaultTableModel modelOfLoaclPlayer = (DefaultTableModel) localPlayer.getModel();
    Boolean isUserNameTaken = false;

    for(int i = 0; i < anzahlAnSpielern; i++){
      if(nickname.equals((String) modelOfLoaclPlayer.getValueAt(i, 0))){
        JOptionPane.showMessageDialog(null, "UserName ist bereits in Benützung");
        isUserNameTaken = true;
      }
    }
    if(anzahlAnSpielern >= 4){
      JOptionPane.showMessageDialog(null, "Maximal 4 Spieler erlaubt");
      isUserNameTaken = true;

    }

    if(!isUserNameTaken){
      modelOfLoaclPlayer.addRow(new Object[]{nickname});
      anzahlAnSpielern++;
    }
  }

  private void removePlayerLocalGame(){
    int row =  localPlayer.getSelectedRow();

    DefaultTableModel model = (DefaultTableModel) localPlayer.getModel();

    model.removeRow(row);
  }


}