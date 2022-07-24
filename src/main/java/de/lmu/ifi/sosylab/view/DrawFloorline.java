package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.model.PenaltyTile;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.PlayerState;
import de.lmu.ifi.sosylab.model.Tile;
import de.lmu.ifi.sosylab.view.ColorSchemes.ColorScheme;
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

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
  private final int FLOORLINE_CAPACITY = 7;

  /**
   * Constructs the floorline and links it to player and controller.
   *
   * @param player     player related to the board containing this floorline
   * @param controller game controller
   */
  public DrawFloorline(Player player, Controller controller) {
    this.player = player;
    setPreferredSize(new Dimension(FLOORLINE_CAPACITY * slotSize + 10, 60));
    setOpaque(false);
    setLayout(null);

    addFloorLineButton(controller);
  }

  private void addFloorLineButton(Controller controller) {
    JButton floorlineButton = new JButton();
    floorlineButton.setBounds(0, 20, slotSize * FLOORLINE_CAPACITY, slotSize);
    floorlineButton.setOpaque(false);
    floorlineButton.setContentAreaFilled(false);
    floorlineButton.setBorderPainted(false);
    add(floorlineButton);

    floorlineButton.addActionListener(e -> {
      System.out.println(player.getNickname() + " - " + "floorLineButton");
      if (myNickname.equals("") ||
          (player.getPlayerState().equals(PlayerState.TO_MOVE) && player.getNickname()
              .equals(myNickname))) {
        controller.placeTiles(player, -1);
      }
    });
  }


  @Override
  protected void paintComponent(Graphics g) {
    //super.paintComponent(g);
    drawFloorlineText(g);
    drawFloorlineFrames(g);
    drawFloorlineTiles(g);
  }

  private void drawFloorlineText(Graphics g) {
    g.setColor(colorScheme.floorlineFrame());
    for (int i = 0; i < textPenaltyPoints.length; i++) {
      g.drawString(textPenaltyPoints[i], slotSize * i + (slotSize / 3), 15);
    }
  }

  public void drawFloorlineFrames(Graphics g) {
    ((Graphics2D) g).setStroke(new BasicStroke(2));
    g.setColor(colorScheme.floorlineFrame());
    for (int col = 0; col < FLOORLINE_CAPACITY; col++) {
      g.drawRect(col * slotSize, 20, slotSize, slotSize);
    }
  }

  private void drawFloorlineTiles(Graphics g) {
    List<Tile> floorLineTiles = player.getPlayerBoard().getFloorLine();
    if (floorLineTiles.size() == 0) {
      return;
    }
    for (int col = 0; col < floorLineTiles.size(); col++) {
      if (floorLineTiles.get(col) instanceof PenaltyTile) {
        g.setColor(colorScheme.penalty());
      } else {
        g.setColor(
            ColorSchemes.getColorByName(floorLineTiles.get(col).toString(), colorScheme));/**/
      }
      g.fillRoundRect(col * slotSize + borderSize, 20 + borderSize, tileSize, tileSize,
          arcSize, arcSize);
      if (floorLineTiles.get(col) instanceof PenaltyTile) {
        g.setColor(colorScheme.penaltyText());
        g.setFont(this.getFont().deriveFont(Font.BOLD, 18));
        g.drawString("1", col * slotSize + borderSize + tileSize / 3,
            20 + borderSize + 2 * tileSize / 3);
      }
    }
  }

  /**
   * Setter for color scheme of the floor line.
   *
   * @param colorScheme current color scheme
   */
  public void setColorScheme(ColorScheme colorScheme) {
    this.colorScheme = colorScheme;
  }

  /**
   * Routing for my nickname from multiplayer mode client for identification of allowed floorline
   * events.
   *
   * @param myNickname player's nickname
   */
  public void setMyNickname(String myNickname) {
    this.myNickname = myNickname;
  }


  public int getFloorlineSlotSize() {
    return slotSize;
  }

  public void setFloorlineCellSize(int newSize) {
    this.slotSize = newSize;
  }
}


