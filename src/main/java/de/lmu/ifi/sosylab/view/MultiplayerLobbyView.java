package de.lmu.ifi.sosylab.view;

import static java.util.Objects.requireNonNull;

import de.lmu.ifi.sosylab.model.Player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Starts a JFrame which displays the menu items of the game.
 */
public class MultiplayerLobbyView extends JFrame {

  private final JPanel multiPlayerLobbyPanel;
  // private final JPanel multiPlayerControlButtons;
  private final JPanel gameControlButtons;
  private JTextField nicknameOnline;
  private JFrame thisFrame;
  ArrayList<LobbyElements> lobbyElementsList = new ArrayList<>();

  /**
   * Constructor of the class.
   *
   */
  public MultiplayerLobbyView() {
    super("Azul - Multiplayer Mode - Lobby");
    thisFrame = this;
    setLayout(new BorderLayout());
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    // Logo einrichten und anzeigen

    JPanel graphic = new JPanel();
    add(graphic, BorderLayout.NORTH);
    GraphicAzul graphicAzul = new GraphicAzul();
    graphic.add(graphicAzul.azulPanel);
    graphic.setBackground(new Color(135, 206, 250));

    // Unter dem Logo Kontrollen einrichten und anzeigen

    // Multiplayer - Lobby - Panel
    multiPlayerLobbyPanel = new JPanel(new FlowLayout());
    multiPlayerLobbyPanel.setBackground(new Color(135, 206, 250));
    // Im Main Frame positionieren
    add(multiPlayerLobbyPanel, BorderLayout.CENTER);

    // Lobby Elemente positionieren
    // TODO: Zahl der existierenden Räume abfragen (Bsp 5), Elementliste anlegen
    // TODO: laufendes update, davor Elementliste nach join requests durchsuchen und in den neuen Zyklus weiterreichen...
    List<String> fakeRooms = Arrays.asList("Buenos Aires", "Córdoba", "La Plata", "Montevideo", "Ascunión");    // FAKE!
    List<String> players = Arrays.asList("Player1", "Player2", "Player3");                                      // FAKE!

    JPanel lobbyElementsPanel = new JPanel(new GridLayout(5, 1));
    for (int i = 0; i < 5; i++) {
      LobbyElements lobbyElements = new LobbyElements(fakeRooms.get(i), players);
      lobbyElementsList.add(lobbyElements);
      lobbyElementsPanel.add(lobbyElements);
    }
    multiPlayerLobbyPanel.add(lobbyElementsPanel, BorderLayout.CENTER);
    multiPlayerLobbyPanel.setBackground(new Color(135, 206, 250));


    // Game - Kontroll - Panel
    JPanel gameControlPanel = new JPanel(new BorderLayout());
    gameControlPanel.setBackground(new Color(135, 206, 250));
    // Im Main Frame positionieren
    add(gameControlPanel, BorderLayout.SOUTH);
    // Game - Kontroll - Panel befüllen
    gameControlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
    connectButtonView();
    leaveOnlineModeButtonView();
    createRoomButtonView();
    gameControlPanel.add(gameControlButtons, BorderLayout.CENTER);

    // Ende set-up, packen, sichtbar machen
    pack();
    setVisible(true);

  }


  private void connectButtonView() {
    JButton connectButton = new JButton("ENTER ROOM");
    gameControlButtons.add(connectButton);

    connectButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO: step through lobbyElementsList, get nickname: if != "" -> this room! (my be > 1)
        // TODO: room view(s) (with chat function?) offering to start game, or leave back to lobby

        // Anmerkung: Man kann theoretisch mehrere Räume joinen, das ist kein bug, sondern ein feature... ("simultan - Azul" :D )
      }
    });

  }

  private void leaveOnlineModeButtonView() {
    JButton leaveButton = new JButton("LEAVE");
    gameControlButtons.add(leaveButton);

    leaveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
        StartMenuView startView = new StartMenuView();
      }
    });

  }

  private void createRoomButtonView() {
    JButton createRoomButton = new JButton("CREATE ROOM");
    gameControlButtons.add(createRoomButton);

    createRoomButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        //TODO: create new room @server, close this window, restart lobby view, then join room, then enter room
      }
    });

  }
  // end class 1
}

class LobbyElements extends JPanel {

  private String result = "";
  ArrayList<JTextField> playerFields = new ArrayList<>();

  /**
   * Generate lobby elements from server data.
   *
   * @param roomID    room name
   * @param players   players list (preliminary: String, else: Player)
   */
  public LobbyElements(String roomID, List<String> players) {

    // Main panel for one lobby element
    setLayout(new BorderLayout());
    // Add header
    JPanel roomText = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel roomLabel = new JLabel("Room: " + roomID);
    roomText.add(roomLabel);
    add(roomText, BorderLayout.NORTH);

    JPanel roomStatus = new JPanel(new BorderLayout());

    JPanel playerText = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel playerLabel = new JLabel("     Players:    ");
    playerText.add(playerLabel);
    playerText.setPreferredSize(new Dimension(100, 50));
    roomStatus.add(playerLabel, BorderLayout.WEST);

    JPanel playersList = new JPanel(new GridLayout(4,1));
    for (int i = 0; i < 4; i++) {
      JTextField player = new JTextField("", 25);
      playerFields.add(player);
      playersList.add(player);
      if (i < players.size()) {
        player.setText(players.get(i));
      }

    }
    roomStatus.add(playersList, BorderLayout.CENTER);

    JPanel joinButtonPanel = new JPanel(new BorderLayout());
    JPanel joinButtonPanelInset = new JPanel(new FlowLayout(FlowLayout.CENTER));
    joinButtonPanelInset.setPreferredSize(new Dimension(100, 40));
    JButton joinRoom = new JButton("JOIN");
    joinButtonPanelInset.add(joinRoom);
    joinButtonPanel.add(joinButtonPanelInset, BorderLayout.SOUTH);
    joinRoom.setBounds(new Rectangle(50, 20));
    if (players.size() > 3) {
      joinRoom.setEnabled(false);
    }
    // TODO: disable also, if game in room is active.
    roomStatus.add(joinButtonPanel, BorderLayout.EAST);

    add(roomStatus, BorderLayout.CENTER);

    joinRoom.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        result = JOptionPane.showInputDialog(null,
            "Enter nickname:", "Join " + roomID, JOptionPane.QUESTION_MESSAGE);
        if (result == null) { result = ""; }
        playerFields.get(players.size()).setText(result);
        if (result.isEmpty()) {
          playerFields.get(players.size()).setBackground(Color.white);
        } else {
          playerFields.get(players.size()).setBackground(Color.green);
        }
      }
    });
  }

  /**
   * Get nickname, if this lobby element (correlated to a room) shall be joined
   *
   * @return nickname -> if empty string, no join request!
   */
  public String getNickName() {
    return result;
  }

  // end class 2
}