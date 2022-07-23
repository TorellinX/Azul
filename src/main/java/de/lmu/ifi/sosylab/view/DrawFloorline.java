package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.model.PenaltyTile;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.PlayerState;
import de.lmu.ifi.sosylab.model.Tile;
import de.lmu.ifi.sosylab.view.ColorSchemes.ColorScheme;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Component drawing class for floorline including action listener.
 */
public class DrawFloorline extends JPanel {

  private int slotSize = 35;
  private final int borderSize = 1;
  private final int tileSize = slotSize - borderSize * 2;
  private final int arcSize = 6;
  private ColorScheme colorScheme;
  private final String[] textPenaltyPoints = new String[]{"-1", "-1", "-2", "-2", "-2", "-3", "-3"};
  private final Player player;
  private String myNickname = "";

  /**
   * Constructs the floorline and links it to player and controller.
   *
   * @param player     player related to the board containing this floorline
   * @param controller game controller
   */
  public DrawFloorline(Player player, Controller controller) {
    this.player = player;
    setPreferredSize(new Dimension(7 * slotSize + 10, 60));
    setLayout(null);

    JButton floorlineButton = new JButton();
    add(floorlineButton);
    floorlineButton.setBounds(0, 20, getFloorlineCellSize() * 7, getFloorlineCellSize());
    floorlineButton.setOpaque(false);
    floorlineButton.setContentAreaFilled(false);
    floorlineButton.setBorderPainted(false);
    floorlineButton.addActionListener(e -> {
      System.out.println(player.getNickname() + " - " + "floorLineButton");
      if (myNickname.equals("") ||
              (player.getPlayerState().equals(PlayerState.TO_MOVE) && player.getNickname().equals(myNickname))) {
        controller.placeTiles(player, -1);
      }
      // controller.placeTiles(player, -1);
    });

  }

  public int getFloorlineCellSize() {
    return slotSize;
  }

  public void setFloorlineCellSize(int newSize) {
    this.slotSize = newSize;
  }


  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(colorScheme.playerboard());
    Graphics2D g2D = (Graphics2D) g;

    g2D.setColor(colorScheme.floorlineFrame());
    for (int i = 0; i < textPenaltyPoints.length; i++) {
      g2D.drawString(textPenaltyPoints[i], slotSize * i, 15);
    }

    g2D.setStroke(new BasicStroke(2));

    List<Tile> floorLine = player.getPlayerBoard().getFloorLine();

    for (int col = 0; col < textPenaltyPoints.length; col++) {
      g2D.setColor(colorScheme.floorlineFrame());
      g2D.drawRect(col * slotSize, 20, slotSize, slotSize);
    }

    if (floorLine.size() != 0) {
      for (int col = 0; col < floorLine.size(); col++) {
        if (floorLine.get(col) instanceof PenaltyTile) {
          g.setColor(colorScheme.penalty());
        } else {
          switch (floorLine.get(col).toString()) {
            case "BLUE" -> g.setColor(colorScheme.blue());
            case "YELLOW" -> g.setColor(colorScheme.yellow());
            case "RED" -> g.setColor(colorScheme.red());
            case "BLACK" -> g.setColor(colorScheme.black());
            case "WHITE" -> g.setColor(colorScheme.green());
            default -> g.setColor(colorScheme.playerboard());
          }
        }
        g2D.fillRoundRect(col * slotSize + borderSize, 20 + borderSize, tileSize, tileSize,
            arcSize, arcSize);
        if (floorLine.get(col) instanceof PenaltyTile) {
          g2D.setColor(colorScheme.penaltyText());
          g2D.setFont(this.getFont().deriveFont(Font.BOLD, 18));
          g2D.drawString("1", col * slotSize + borderSize + tileSize / 3,
              20 + borderSize + 2 * tileSize / 3);
        }
      }
    }
  }

  /**
   * Setter for color scheme of the floor line.
   * @param colorScheme current color scheme
   */
  public void setColorScheme(ColorScheme colorScheme) {
    this.colorScheme = colorScheme;
  }

  /**
   * Routing for my nickname from multiplayer mode client for identification of allowed floorline events.
   *
   * @param myNickname
   */
  public void setMyNickname(String myNickname) {
    this.myNickname = myNickname;
  }
}


