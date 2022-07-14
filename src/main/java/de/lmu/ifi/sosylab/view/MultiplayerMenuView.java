package de.lmu.ifi.sosylab.view;

import static java.util.Objects.requireNonNull;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.model.State;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 * Starts a JFrame which displays the menu items of the game.
 */

public class MultiplayerMenuView extends JFrame /*implements PropertyChangeListener*/ {

  private final JPanel multiPlayerControlPanel;
  // private final JPanel multiPlayerControlButtons;
  private final JPanel gameControlButtons;
  private JTextField nicknameOnline;
  private JFrame thisFrame;

  /**
   * Constructor of the class.
   *
   */
  public MultiplayerMenuView() {
    super("Azul - Multiplayer Mode");
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

    // Multiplayer - Kontroll - Panel
    multiPlayerControlPanel = new JPanel(new BorderLayout());
    multiPlayerControlPanel.setBackground(new Color(135, 206, 250));
    // Im Main Frame positionieren
    add(multiPlayerControlPanel, BorderLayout.CENTER);
    // Multiplayer - Kontroll - Panel befüllen
    // Mit Namenseingabefeld
    addPlayerTextFieldView();
    // ToDo: Weitere Kontrollen ???
    // Tabelle mit Daten, Namen, etc
    JPanel mulitPlayerControlPanelCenter = new JPanel(new FlowLayout());
    multiPlayerControlPanel.add(mulitPlayerControlPanelCenter, BorderLayout.CENTER);
    multiPlayerControlPanel.setBackground(new Color(135, 206, 250));
    String[] columNamesMultiplayer = {"Room ID", "Room Name", "Anzahl der Player"};
    Object[][] dataMultiplayer = {{"1", "2", "3"}};
    JTable tableMultiplayer = new JTable(dataMultiplayer, columNamesMultiplayer);
    mulitPlayerControlPanelCenter.add(nicknameOnline);
    mulitPlayerControlPanelCenter.add(tableMultiplayer);

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

  private void addPlayerTextFieldView() {
    JPanel playerControlText = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel playerControlLabel = new JLabel("HIER BENUTZERNAME EINGEBEN: ");
    playerControlText.add(playerControlLabel);
    nicknameOnline = new JTextField("", 30);
    playerControlText.add(nicknameOnline);
    multiPlayerControlPanel.add(playerControlText, BorderLayout.NORTH);

    nicknameOnline.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,
            "Not implemented, yet.",
            "Dead End", JOptionPane.ERROR_MESSAGE);
        // addPlayerToOnlineGame(nicknameOnline.getText());
      }
    });

  }

  private void connectButtonView() {
    JButton connectButton = new JButton("ENTER ROOM");
    gameControlButtons.add(connectButton);

    connectButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,
            "Not implemented, yet.",
            "Dead End", JOptionPane.ERROR_MESSAGE);
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
        RoomView room = new RoomView();
      }
    });

  }

  /*
  @Override
  public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
    SwingUtilities.invokeLater(() -> handleModelUpdate(propertyChangeEvent));
  }

  private void handleModelUpdate(PropertyChangeEvent event) {
    if (event.getPropertyName().equals("GameState changed")) {
      // showGame();
    }
  }
  */
}