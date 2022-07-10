package de.lmu.ifi.sosylab.view;

import static java.util.Objects.requireNonNull;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.controller.GameController;
import de.lmu.ifi.sosylab.model.GameModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class HotseatMenuView extends JFrame {

  private final JPanel playerControlPanel;
  private final JPanel playerControlButtons;
  private final JPanel gameControlButtons;
  private JTextField nicknameLocal;
  private final JFrame thisFrame;
  private final JTable localPlayers;
  private int numberOfPlayers = 0;


  public HotseatMenuView() {
    super("Azul - New Local Game");
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

    // Player - Kontroll - Panel
    playerControlPanel = new JPanel(new BorderLayout());
    playerControlPanel.setBackground(new Color(135, 206, 250));
    // Im Main Frame positionieren
    add(playerControlPanel, BorderLayout.CENTER);
    // Player - Kontroll - Panel befüllen
    // Mit Namenseingabefeld
    addPlayerTextFieldView();
    // Mit Buttons
    playerControlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
    addPlayerButtonView();
    removePlayerButtonView();
    playerControlPanel.add(playerControlButtons, BorderLayout.CENTER);
    // Mit Tabelle für eingegebene Namen
    DefaultTableModel tableModel = new DefaultTableModel();
    tableModel.addColumn("Player Nickname");
    localPlayers = new JTable(tableModel);
    JScrollPane nickNames = new JScrollPane(localPlayers);
    nickNames.setPreferredSize(new Dimension(400,90));
    playerControlPanel.add(nickNames, BorderLayout.SOUTH);

    // Game - Kontroll - Panel
    JPanel gameControlPanel = new JPanel(new BorderLayout());
    gameControlPanel.setBackground(new Color(135, 206, 250));
    // Im Main Frame positionieren
    add(gameControlPanel, BorderLayout.SOUTH);
    // Game - Kontroll - Panel befüllen
    gameControlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
    startGameButtonView();
    backGameButtonView();
    gameControlPanel.add(gameControlButtons, BorderLayout.CENTER);

    // Ende set-up, packen, sichtbar machen
    pack();
    setVisible(true);

  }

  private void addPlayerTextFieldView() {
    JPanel playerControlText = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel playerControlLabel = new JLabel("HIER BENUTZERNAME EINGEBEN: ");
    playerControlText.add(playerControlLabel);
    nicknameLocal = new JTextField("", 30);
    playerControlText.add(nicknameLocal);
    playerControlPanel.add(playerControlText, BorderLayout.NORTH);

    nicknameLocal.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        addPlayerToLocalGame(nicknameLocal.getText());
      }
    });

  }

  private void addPlayerButtonView() {
    JButton addPlayerButton = new JButton("ADD PLAYER");
    playerControlButtons.add(addPlayerButton);

    addPlayerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        addPlayerToLocalGame(nicknameLocal.getText());
      }
    });

  }

  private void removePlayerButtonView() {
    JButton removePlayerButton = new JButton("REMOVE PLAYER");
    playerControlButtons.add(removePlayerButton);

    removePlayerButton.addActionListener(new ActionListener() {
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

  }

  private void startGameButtonView() {
    JButton startGameButton = new JButton("START GAME");
    gameControlButtons.add(startGameButton);

    startGameButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (getNicknames().size() < 2 || getNicknames().size() > 4) {
          JOptionPane.showMessageDialog(null,
              "It was not possible to create a Game, there can only be 2-4 players");
        } else {

          PlayingView playingviewframe = new PlayingView(getNicknames().size(), getNicknames());

          // dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
        }
      }
    });

  }

  private void backGameButtonView() {
    JButton backGameButton = new JButton("BACK GAME");
    gameControlButtons.add(backGameButton);

    backGameButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
        StartMenuView startView = new StartMenuView();
      }
    });
  }

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

    nicknameLocal.setText("");
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
  public List<String> getNicknames() {
    ArrayList<String> listOfPlayer = new ArrayList<>();
    DefaultTableModel localPlayer = (DefaultTableModel) localPlayers.getModel();

    for (int i = 0; i < localPlayer.getRowCount(); i++) {
      listOfPlayer.add((String) localPlayer.getValueAt(i, 0));
    }
    System.out.println(listOfPlayer);
    return listOfPlayer;
  }

  /*
  private void showGame() {
    if (model.getState() == State.RUNNING) {
      setVisible(false);
      PlayingView playingviewframe = new PlayingView(getNicknames().size(), getNicknames(),
          controller, model);
      model.addPropertyChangeListener(playingviewframe);
    }
  }

  @Override
  public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
    SwingUtilities.invokeLater(() -> handleModelUpdate(propertyChangeEvent));
  }

  private void handleModelUpdate(PropertyChangeEvent event) {
    if (event.getPropertyName().equals("GameState changed")) {
      showGame();
    }
  }

   */
}