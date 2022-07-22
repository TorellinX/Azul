package de.lmu.ifi.sosylab.view;

import static de.lmu.ifi.sosylab.view.ColorSchemes.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Displays the menu for hotseat mode with player name entry and start/back buttons.
 */
public class HotseatMenuView extends JFrame {

  private final JPanel playerControlPanel;
  private final JPanel playerButtonsPanel;
  private final JPanel controlButtonsPanel;
  private final JTextField[] nicknameInputFields = new JTextField[4];
  private final JFrame thisFrame;
  private int numberOfPlayers = 2;
  private ColorScheme colorScheme = classic;
  private final Color hotseatMenu = new Color(135, 206, 250);

  /**
   * Constructor - see class description.
   */
  public HotseatMenuView() {
    super("Azul - New Local Game");
    thisFrame = this;
    setLayout(new BorderLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBackground(hotseatMenu);

    // Logo einrichten und anzeigen

    JPanel graphic = new JPanel();
    graphic.setBackground(hotseatMenu);
    add(graphic, BorderLayout.NORTH);
    GraphicAzul graphicAzul = new GraphicAzul();
    graphic.add(graphicAzul.azulPanel);

    // Unter dem Logo Kontrollen einrichten und anzeigen

    // Player - Kontroll - Panel
    playerControlPanel = new JPanel(new BorderLayout());
    playerControlPanel.setBackground(hotseatMenu);
    // Im Main Frame positionieren
    add(playerControlPanel, BorderLayout.CENTER);
    // Mit Buttons
    playerButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    playerButtonsPanel.setOpaque(false);
    addPlayerButtonView();
    removePlayerButtonView();
    playerControlPanel.add(playerButtonsPanel, BorderLayout.CENTER);

    JPanel playerNicknamePanel = new JPanel();
    playerNicknamePanel.setOpaque(false);
    playerNicknamePanel.setLayout(new FlowLayout());
    JPanel nicknameFieldsPanel = new JPanel();
    BoxLayout layout = new BoxLayout(nicknameFieldsPanel, BoxLayout.Y_AXIS);
    nicknameFieldsPanel.setLayout(layout);
    nicknameFieldsPanel.setOpaque(false);
    for (int i = 0; i < 4; i++) {
      nicknameInputFields[i] = new JTextField(25);
      nicknameInputFields[i].setText("Player " + (i + 1));
      nicknameFieldsPanel.add(nicknameInputFields[i]);
      nicknameInputFields[i].addActionListener(e -> {
        //
      });
      nicknameInputFields[i].addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          //((JTextField) e.getSource()).setText("");
        }
      });
    }
    nicknameInputFields[2].setEnabled(false);
    nicknameInputFields[3].setEnabled(false);
    playerNicknamePanel.add(nicknameFieldsPanel);
    playerControlPanel.add(playerNicknamePanel, BorderLayout.NORTH);

    // Game-Kontroll-Panel
    JPanel gameControlPanel = new JPanel(new BorderLayout());
    gameControlPanel.setBackground(hotseatMenu);
    // Im Main Frame positionieren
    add(gameControlPanel, BorderLayout.SOUTH);
    // Game-Kontroll-Panel befüllen
    controlButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    controlButtonsPanel.setOpaque(false);
    startGameButtonView();
    startGameThemeView();
    backGameButtonView();
    gameControlPanel.add(controlButtonsPanel, BorderLayout.CENTER);

    // Ende set-up, packen, sichtbar machen
    pack();
    setVisible(true);

  }

  private void addPlayerButtonView() {
    JButton addPlayerButton = new JButton("ADD PLAYER");
    playerButtonsPanel.add(addPlayerButton);

    addPlayerButton.addActionListener(e -> {
      if (!nicknameInputFields[2].isEnabled()) {
        nicknameInputFields[2].setEnabled(true);
        numberOfPlayers++;
      } else if (!nicknameInputFields[3].isEnabled()) {
        nicknameInputFields[3].setEnabled(true);
        numberOfPlayers++;
      }
    });
  }

  private void removePlayerButtonView() {
    JButton removePlayerButton = new JButton("REMOVE PLAYER");
    playerButtonsPanel.add(removePlayerButton);

    removePlayerButton.addActionListener(e -> {
      if (nicknameInputFields[3].isEnabled()) {
        nicknameInputFields[3].setEnabled(false);
        numberOfPlayers--;
      } else if (nicknameInputFields[2].isEnabled()) {
        nicknameInputFields[2].setEnabled(false);
        numberOfPlayers--;
      }
    });

  }

  private void startGameButtonView() {
    JButton startGameButton = new JButton("START GAME");
    controlButtonsPanel.add(startGameButton);
    startGameButton.addActionListener(e -> {
          if (getNicknames().size() < 2 || getNicknames().size() > 4) {
            JOptionPane.showMessageDialog(null,
                "It was not possible to create a Game, there can be only between two and four players.");
            return;
          }
          if (areNicknamesCorrect()) {
            System.out.println("Nicknames" + getNicknames());
            new PlayingView(getNicknames().size(), getNicknames(), colorScheme);
          }
        }
    );
  }

  private void startGameThemeView() {
    JLabel theme = new JLabel("  THEME: ");
    controlButtonsPanel.add(theme);
    JComboBox<String> startGameComboBox = new JComboBox<>(new String[]{
        "Classic", "Beach", "Candy", "Cosmic"});
    controlButtonsPanel.add(startGameComboBox);
    startGameComboBox.addActionListener(e -> {
      String selectedTheme = (String) startGameComboBox.getSelectedItem();
      Objects.requireNonNull(selectedTheme);
      switch (selectedTheme) {
        case "Classic" -> colorScheme = classic;
        case "Beach" -> colorScheme = beach;
        case "Candy" -> colorScheme = candy;
        case "Cosmic" -> colorScheme = cosmic;
        default -> throw new IllegalArgumentException("Non valid color scheme: " + selectedTheme);
      }
      //dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
    });
  }

  private void backGameButtonView() {
    JButton backGameButton = new JButton("BACK TO MAIN MENU");
    controlButtonsPanel.add(backGameButton);

    backGameButton.addActionListener(e -> {
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
      new StartMenuView();
    });
  }

  /**
   * Checks that there are no duplicate or empty nicknames.
   *
   * @return true if there are no duplicate or empty nicknames
   */
  private boolean areNicknamesCorrect() {
    for (JTextField nicknameField : nicknameInputFields) {
      if (!nicknameField.isEnabled()) {
        break;
      }
      String nickname = nicknameField.getText();
      for (int i = 0; i < numberOfPlayers; i++) {
        if (nicknameField == nicknameInputFields[i]) {
          continue;
        }
        if (!nicknameInputFields[i].isEnabled()) {
          continue;
        }
        if (nickname.equals(nicknameInputFields[i].getText())) {
          JOptionPane.showMessageDialog(null, "Enter different player names.");
          return false;
        }
      }
      if (nickname.equals("")) {
        JOptionPane.showMessageDialog(null, "Choose a Nickname!");
        return false;
      }
    }
    return true;
  }

  /**
   * Returns the list of players' nicknames.
   *
   * @return list of nicknames
   */
  public List<String> getNicknames() {
    ArrayList<String> listOfPlayer = new ArrayList<>();
    for (int i = 0; i < numberOfPlayers; i++) {
      if (!nicknameInputFields[i].isEnabled()) {
        break;
      }
      listOfPlayer.add(nicknameInputFields[i].getText());
    }
    return listOfPlayer;
  }
}
