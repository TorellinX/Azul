package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.controller.GameController;
import de.lmu.ifi.sosylab.model.GameModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

/**
 * Starts a JFrame which displays the menu items of the game.
 */

public class MainMenuView extends JFrame {

  private int width = 1200;
  private int hight = 700;
  private JFrame mainFrame;
  private CardLayout layout;

  private static final String MAIN_MENU = "mainMenu";
  private static final String LOCAL_GAME = "localGame";
  private static final String MULTIPLAYER = "multiplayer";
  private static final String PLAYING_VIEW = "playingview";
  private JPanel panel;
  //Buttons für  Main Menu
  private JButton hotseat;
  private JButton multiplayer;
  //Elemente für Local Players
  private JButton addPlayer;
  private JButton removePlayer;
  private JButton backToMenu;
  private JButton startGame;
  private JTextField nicknameLocal;
  //Elemente für Multiplayers
  private JButton connect;
  private JButton createRoom;
  private JButton leaveOnlineMode;
  private JTextField nicknameOnline;
  private JTable localPlayers;
  private String userNameOnline;
  private int numberOfPlayers = 0;
  private JButton openplayingfield;
  private final GameModel model;
  private final Controller controller;


  /**
   * Constructor of the class
   *
   * @param controller Controller
   * @param model      Model
   */
  public MainMenuView(GameModel model, Controller controller) {
    super("Azul");

    this.model = requireNonNull(model);
    this.controller = requireNonNull(controller);

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setResizable(false);
    setSize(width, hight);

    initialize();
    creatMainMenuPanel();
    createLocalPlayerAddView();
    createMulitplayerView();

    addListeners();

    showMainMenu();

  }

  /**
   * Sets the organization of the window.
   */
  private void initialize() {
    layout = new CardLayout();
    panel = new JPanel(layout);
    setContentPane(panel);

  }

  /**
   * Creates the main menu window.
   */
  private void creatMainMenuPanel() {
    //Panel mainMenu
    JPanel mainMenu = new JPanel(new BorderLayout());
    add(mainMenu, MAIN_MENU);
    //Create Panel for Graphics and Buttons
    JPanel graphic = new JPanel();
    JPanel buttons = new JPanel();
    GraphicAzul graphicAzul = new GraphicAzul();

    //Logo anzeigen
    graphic.add(graphicAzul.azulPanel);
    graphic.setBackground(new Color(135, 206, 250));

    // Mittleres Panel wird mit Buttons gefüllt.
    buttons.setLayout(new FlowLayout());
    hotseat = new JButton("Hotseat");
    multiplayer = new JButton("Multiplayer");
    buttons.add(hotseat);
    buttons.add(multiplayer);
    buttons.setBackground(new Color(135, 206, 250));

    mainMenu.add(graphic, BorderLayout.NORTH);
    mainMenu.add(buttons, BorderLayout.CENTER);
  }

  /**
   * Creates the local player menu window.
   */

  private void createLocalPlayerAddView() {
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
    localPlayers = new JTable(model);

    JScrollPane nickNames = new JScrollPane(localPlayers);
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

  /**
   * Creates the Multiplayer menu window.
   */
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
    createRoom = new JButton("Create New Room");

    panelBottomMultiplayer.add(connect);
    panelBottomMultiplayer.add(leaveOnlineMode);
    panelBottomMultiplayer.add(createRoom);

    multiplayerPanel.add(panelUpMultiplayer, BorderLayout.NORTH);
    multiplayerPanel.add(panelCenterMultiplayer, BorderLayout.CENTER);
    multiplayerPanel.add(panelBottomMultiplayer, BorderLayout.SOUTH);
  }

  /**
   * The main menu is displayed.
   */

  private void showMainMenu() {
    layout.show(getContentPane(), MAIN_MENU);
    setVisible(true);
  }

  /**
   * The local menu is displayed.
   */

  private void showLocalMode() {
    layout.show(getContentPane(), LOCAL_GAME);
    setVisible(true);
  }

  /**
   * Clears the Input.
   */
  private void clearTextInTextfield() {
    nicknameLocal.setText("");
  }

  /**
   * The Multiplayer menu is displayed.
   */

  private void showMultiplayer() {
    layout.show(getContentPane(), MULTIPLAYER);
    setVisible(true);
  }

  /**
   * The Game View is displayed.
   */

  private void showGame() {
    setVisible(false);
    PlayingView playingviewframe = new PlayingView(getNicknames().size(), getNicknames(),
        controller, model);
  }


  /**
   * Adds Action Listener to the Buttons.
   */
  private void addListeners() {
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

    nicknameLocal.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        clearTextInTextfield();
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
        // checks if a list element is selected
        if (localPlayers.getSelectedRow() == -1) {
          JOptionPane.showMessageDialog(null, "Please select a user to delete");
        } else {
          removePlayerLocalGame();
        }
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
      public void actionPerformed(ActionEvent e) {
        if (controller.startGame(getNicknames())) {
          showGame();
        } else {
          JOptionPane.showMessageDialog(null,
              "It was not possible to create a Game, there can only be 2-4 players");
        }
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


  /**
   * Adds a Player to the Local Game. Displays a Error if more than 4 Player are in the list or
   * Nickname is already in use.
   */
  private void addPlayerToLocalGame(String nickname) {
    DefaultTableModel modelOfLocalPlayer = (DefaultTableModel) localPlayers.getModel();
    Boolean isUserNameTaken = false;

    for (int i = 0; i < numberOfPlayers; i++) {
      if (nickname.equals((String) modelOfLocalPlayer.getValueAt(i, 0))) {
        JOptionPane.showMessageDialog(null, "User already in the list");
        isUserNameTaken = true;
      }
    }
    if (numberOfPlayers >= 4) {
      JOptionPane.showMessageDialog(null, "There can't be more than 4 Players in the Game");
      isUserNameTaken = true;

    }

    if (!isUserNameTaken) {
      modelOfLocalPlayer.addRow(new Object[]{nickname});
      numberOfPlayers++;
    }
  }

  /**
   * Removes a player from the local Player Table.
   */

  private void removePlayerLocalGame() {
    int row = localPlayers.getSelectedRow();

    DefaultTableModel model = (DefaultTableModel) localPlayers.getModel();

    model.removeRow(row);
  }

  /**
   * Returns the list of players' nicknames.
   *
   * @return list of nicknames
   */
  public ArrayList<String> getNicknames() {
    ArrayList<String> listOfPlayer = new ArrayList<>();
    DefaultTableModel localPlayer = (DefaultTableModel) localPlayers.getModel();

    for (int i = 0; i < localPlayer.getRowCount(); i++) {
      listOfPlayer.add((String) localPlayer.getValueAt(i, 0));
    }
    System.out.println(listOfPlayer);
    return listOfPlayer;
  }
}