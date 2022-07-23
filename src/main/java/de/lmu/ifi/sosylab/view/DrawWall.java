package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.view.ColorSchemes.ColorScheme;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * Component drawing class for wall.
 */
public class DrawWall extends JPanel {

  private int slotSize = 35;
  private final int borderSize = 1;
  private final int tileSize = slotSize - borderSize * 2;
  private final int arcSize = 6;
  private final Player player;
  private final Controller controller;
  private boolean[][] wall;
  private ColorScheme colorScheme;

  /**
   * Constructs the wall and links it to player and controller.
   *
   * @param player     player related to the board containing this wall
   * @param controller game controller
   */
  public DrawWall(Player player, Controller controller) {
    this.controller = controller;
    this.player = player;
    setPreferredSize(new Dimension(getWallFrameSize(), getWallFrameSize()));
  }

  /**
   * Get wall cell size.
   *
   * @return size in pixel
   */
  public int getWallSlotSize() {
    return slotSize;
  }

  /**
   * Get wall background frame size.
   *
   * @return size in pixel
   */
  public int getWallFrameSize() {
    return (5 * slotSize + 40);
  }

  /**
   * Set wall cell size. Background frame size also depends on this setting.
   *
   * @param newSize in pixel
   */
  public void setWallSlotSize(int newSize) {
    this.slotSize = newSize;
  }

  // Draws the wall
  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);

    setBackground(colorScheme.playerboard());
    Graphics2D g2D = (Graphics2D) g;
    g2D.setColor(colorScheme.wallBackground());
    g2D.fillRoundRect(0, 0, getWallFrameSize(), getWallFrameSize(), 20, 20);

    g2D.setStroke(new BasicStroke(2));

    wall = player.getPlayerBoard().getWall();

    int colorNumber;
    for (int col = 0; col < 5; col++) {
      for (int row = 0; row < 5; row++) {
        colorNumber = (col + (4 - row)) % 5;
        switch (colorNumber) {
          case 0 -> g2D.setColor(colorScheme.yellow());
          case 1 -> g2D.setColor(colorScheme.red());
          case 2 -> g2D.setColor(colorScheme.black());
          case 3 -> g2D.setColor(colorScheme.green());
          case 4 -> g2D.setColor(colorScheme.blue());
          default -> throw new IllegalStateException("Unexpected value: " + colorNumber);
        }

        g2D.drawRoundRect(10 + col * slotSize + col * 5, 10 + row * slotSize + row * 5, slotSize,
            slotSize, arcSize, arcSize);

        if (wall[row][col]) {
          g2D.fillRoundRect(10 + col * slotSize + col * 5 + borderSize,
              10 + row * slotSize + row * 5 + borderSize,
              tileSize, tileSize, arcSize, arcSize);
        }
      }
    }
    g2D.setColor(Color.black);
  }

  /**
   * Setter for color scheme of the wall.
   *
   * @param colorScheme current color scheme
   */
  public void setColorScheme(ColorScheme colorScheme) {
    this.colorScheme = colorScheme;
  }
}
