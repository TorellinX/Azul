package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.model.ColorTile;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.PlayerState;
import de.lmu.ifi.sosylab.view.ColorSchemes.ColorScheme;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Component drawing class for pattern lines including action listeners.
 */
public class DrawPattern extends JPanel {

  private int slotSize = 35;
  private final int borderSize = 1;
  private final int tileSize = slotSize - borderSize * 2;
  private final int arcSize = 6;
  private ColorScheme colorScheme;
  private ArrayList<JButton> patternButtons;
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
    setOpaque(false);
    setLayout(null);

    addPatternLineButtons();
    addButtonsActionListener();
  }

  private void updatePatternLines() {
    // TODO: change drawing and get rid of this method
    ColorTile[][] invPatternLines = player.getPlayerBoard().getPatternLines();
    patternLines = player.getPlayerBoard().getPatternLines();
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < invPatternLines[i].length; j++) {
        patternLines[i][invPatternLines[i].length - 1 - j] = invPatternLines[i][j];
      }
    }
  }

  private void addPatternLineButtons() {
    patternButtons = new ArrayList<>();
    // TODO: use loop, get rid of button variables, calculate y-coordinate
    JButton firstRowButton = new JButton();
    firstRowButton.setBounds(slotSize * 4, 10, slotSize, slotSize);
    patternButtons.add(firstRowButton);
    add(firstRowButton);
    JButton secondRowButton = new JButton();
    secondRowButton.setBounds(slotSize * 3, 15 + slotSize, slotSize * 2, slotSize);
    patternButtons.add(secondRowButton);
    add(secondRowButton);
    JButton thirdRowButton = new JButton();
    thirdRowButton.setBounds(slotSize * 2, 20 + slotSize * 2, slotSize * 3, slotSize);
    patternButtons.add(thirdRowButton);
    add(thirdRowButton);
    JButton fourthRowButton = new JButton();
    fourthRowButton.setBounds(slotSize, 25 + slotSize * 3, slotSize * 4, slotSize);
    patternButtons.add(fourthRowButton);
    add(fourthRowButton);
    JButton fifthRowButton = new JButton();
    fifthRowButton.setBounds(0, 30 + slotSize * 4, slotSize * 5, slotSize);
    patternButtons.add(fifthRowButton);
    add(fifthRowButton);

    hidePatternLineButtons();
  }


  private void hidePatternLineButtons() {
    for (JButton patternButton : patternButtons) {
      patternButton.setOpaque(false);
      patternButton.setContentAreaFilled(false);
      patternButton.setBorderPainted(false);
    }
  }


  private void addButtonsActionListener(){
    for (int i = 0; i < 5; i++) {
      final int count = i;
      patternButtons.get(count).addActionListener(e -> {
        System.out.println(player.getNickname() + ", pattern row: " + count);
        if (myNickname.equals("") ||
            (player.getPlayerState().equals(PlayerState.TO_MOVE) && player.getNickname()
                .equals(myNickname))) {
          controller.placeTiles(player, count);
        }
      });
    }
  }


  @Override
  protected void paintComponent(Graphics g) {
    // super.paintComponent(g);
    updatePatternLines();
    drawPatternLines(g);
  }


  private void drawPatternLines(Graphics g) {
    ((Graphics2D) g).setStroke(new BasicStroke(1));

    for (int row = 0; row < 5; row++) {
      for (int col = 4 - row; col < 5; col++) {
        g.setColor(colorScheme.patternlineFrame());
        g.drawRect(col * slotSize, 10 + row * slotSize + row * 5, slotSize, slotSize);

        // TODO: invern indexes and get rid of initializePatternLines() invertion
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

          g.setColor(tileColor);
          g.fillRoundRect(col * slotSize + borderSize,
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
   * Routing endpoint for my nickname from multiplayer mode client for identification of allowed
   * pattern events.
   *
   * @param myNickname player's nickname
   */
  public void setMyNickname(String myNickname) {
    this.myNickname = myNickname;
  }


  public int getPatternCellSize() {
    return slotSize;
  }

  public void setPatternSlotSize(int newSize) {
    this.slotSize = newSize;
  }
}
