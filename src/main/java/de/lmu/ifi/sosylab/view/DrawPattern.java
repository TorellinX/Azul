package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.model.ColorTile;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.PlayerState;
import de.lmu.ifi.sosylab.view.ColorSchemes.ColorScheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Component drawing class for pattern lines including action listeners.
 */
public class DrawPattern extends JPanel {

  private int slotSize = 35;
  private final int borderSize = 1;
  private final int tileSize = slotSize - borderSize * 2;
  private final int arcSize = 6;
  private ColorScheme colorScheme;
  private final ArrayList<JButton> patternButtons;
  int count;
  private final Player player;
  private final Controller controller;
  private ColorTile[][] patternLines;
  private String myNickname = "";

  /**
   * Constructs the pattern lines and links it to player and controller.
   *
   * @param player     player related to the board containing these pattern lines
   * @param controller game controller
   */
  public DrawPattern(Player player, Controller controller) {
    this.player = player;
    this.controller = controller;
    setPreferredSize(new Dimension(5 * slotSize + 5, 5 * slotSize + 40));
    setLayout(null);

    patternButtons = new ArrayList<>();
    JButton firstRowButton = new JButton();
    firstRowButton.setBounds(slotSize * 4, 10, slotSize,
        slotSize);
    patternButtons.add(firstRowButton);
    add(firstRowButton);
    JButton secondRowButton = new JButton();
    secondRowButton.setBounds(slotSize * 3, 15 + slotSize,
        slotSize * 2, slotSize);
    patternButtons.add(secondRowButton);
    add(secondRowButton);
    JButton thirdRowButton = new JButton();
    thirdRowButton.setBounds(slotSize * 2, 20 + slotSize * 2,
        slotSize * 3, slotSize);
    patternButtons.add(thirdRowButton);
    add(thirdRowButton);
    JButton fourthRowButton = new JButton();
    fourthRowButton.setBounds(slotSize, 25 + slotSize * 3,
        slotSize * 4, slotSize);
    patternButtons.add(fourthRowButton);
    add(fourthRowButton);
    JButton fifthRowButton = new JButton();
    fifthRowButton.setBounds(0, 30 + slotSize * 4, slotSize * 5,
        slotSize);
    patternButtons.add(fifthRowButton);
    add(fifthRowButton);

    // hide buttons
    for (int i = 0; i < patternButtons.size(); i++) {
      patternButtons.get(i).setOpaque(false);
      patternButtons.get(i).setContentAreaFilled(false);
      patternButtons.get(i).setBorderPainted(false);
    }

    for (int i = 0; i < 5; i++) {
      final int count = i;
      patternButtons.get(count).addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          System.out.println(player.getNickname() + " - " + count);
          if (myNickname.equals("") ||
                  (player.getPlayerState().equals(PlayerState.TO_MOVE) && player.getNickname().equals(myNickname))) {
            controller.placeTiles(player, count);
          }
//          controller.placeTiles(player, count);
        }
      });
    }
  }

  public int getPatternCellSize() {
    return slotSize;
  }

  public void setPatternSlotSize(int newSize) {
    this.slotSize = newSize;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    setBackground(colorScheme.playerboard());
    Graphics2D g2D = (Graphics2D) g;

    g2D.setColor(colorScheme.playerboard());
    g2D.setStroke(new BasicStroke(1));

    ColorTile[][] invPatternLines = player.getPlayerBoard().getPatternLines();
    patternLines = player.getPlayerBoard().getPatternLines();
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < invPatternLines[i].length; j++) {
        patternLines[i][invPatternLines[i].length - 1 - j] = invPatternLines[i][j];
      }
    }

    for (int row = 0; row < 5; row++) {
      for (int col = 4 - row; col < 5; col++) {
        g2D.setColor(colorScheme.patternlineFrame());
        g2D.drawRect(col * slotSize, 10 + row * slotSize + row * 5, slotSize, slotSize);

        if (patternLines[row][4 - col] != null) {
          String customColor = patternLines[row][4 - col].toString();
          Color tileColor = switch (customColor) {
            case "BLACK" -> colorScheme.black();
            case "WHITE" -> colorScheme.green();
            case "BLUE" -> colorScheme.blue();
            case "YELLOW" -> colorScheme.yellow();
            case "RED" -> colorScheme.red();
            default -> colorScheme.playerboard();
          };

          g2D.setColor(tileColor);
          g2D.fillRoundRect(col * slotSize + borderSize,
              10 + row * slotSize + row * 5 + borderSize,
              tileSize, tileSize, arcSize, arcSize);
        }
      }
    }
  }

  /**
   * Setter for color scheme of the pattern lines.
   *
   * @param colorScheme current color scheme
   */
  public void setColorScheme(ColorScheme colorScheme) {
    this.colorScheme = colorScheme;
  }

  /**
   * Routing endpoint for my nickname from multiplayer mode client for identification of allowed pattern events.
   *
   * @param myNickname
   */
  public void setMyNickname(String myNickname) {
    this.myNickname = myNickname;
  }
}
