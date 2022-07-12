package de.lmu.ifi.sosylab.view;

import static de.lmu.ifi.sosylab.model.PlayerBoard.WALL_SIZE;
import static java.util.Objects.requireNonNull;

import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.PlayerState;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 * DrawboardPLayerBoard parent class.
 */
public class DrawboardPlayerBoard extends JPanel {

  private final Color playerboardcolor = new Color(204, 201, 199);
  private final Color scorecolor = new Color(235, 79, 0);
  private final Color floorlinecolor = new Color(139, 0, 139);
  private final Color patternLinesColorRight = Color.black;
  private final Color activePlayerColor = Color.green;
  private final Color inactivePlayerColor = Color.black;
  private final Color tileBlue = Color.blue;
  private final Color tileYellow = Color.yellow;
  private final Color tileRed = Color.red;
  private final Color tileBlack = Color.black;
  private final Color tileGreen = Color.green;


  private final int sizeOfPatternLineCell = 35;
  private final int sizeOfWallCell = 31;
  private final int wallBorder = 4;
  private final int sizeOfMinusCell = 35;
  IntPair[] wallCoordinatesProPlayer = {new IntPair(205, 10), new IntPair(205, 10),
      new IntPair(205, 307), new IntPair(205, 307)};


  void drawNickname(Graphics g, Player player, int x, int y) {
    if (player.getState().equals(PlayerState.TO_MOVE)) {
      g.setColor(activePlayerColor);
      //g.setFont(new Font("default", Font.BOLD, 12));
      g.setFont(g.getFont().deriveFont(Font.BOLD));
    } else {
      g.setColor(inactivePlayerColor);
      // g.setFont(new Font("default", Font.PLAIN, 12));
      g.setFont(g.getFont().deriveFont(Font.BOLD));
    }
    g.drawString(player.getNickname(), x, y);
    g.setFont(g.getFont().deriveFont(Font.PLAIN));
  }

  void drawScores(Graphics g, Player player, int x, int y) {
    g.setColor(scorecolor);
    g.setFont(g.getFont().deriveFont(Font.BOLD));
    String textPoints = "Points: ";
    g.drawString(textPoints + Integer.toString(player.getScore()), x, y);
    g.setFont(g.getFont().deriveFont(Font.PLAIN));
  }

  void drawPatternLinesFrames(Graphics g, HashMap<Integer, IntPair[]> coordinatePatternLines) {
    ((Graphics2D) g).setStroke(new BasicStroke(1));
    g.setColor(patternLinesColorRight);
    for (int count = 1; count <= 5; count++) {
      IntPair[] speicher = coordinatePatternLines.get(count);
      for (int i = 0; i < speicher.length; i++) {
        g.drawRect(speicher[i].getX(), speicher[i].getY(), sizeOfPatternLineCell,
            sizeOfPatternLineCell);
      }
    }
  }

  void drawWallBackground(Graphics g, int x, int y) {
    g.setColor(playerboardcolor);
    g.fillRoundRect(x, y, 181, 181, 20, 20);
  }

  void drawWallFrames(Graphics g, int x, int y) {
    ((Graphics2D) g).setStroke(new BasicStroke(2));
    for (int row = 0; row < WALL_SIZE; row++) {
      for (int col = 0; col < WALL_SIZE; col++) {
        de.lmu.ifi.sosylab.model.Color color = getColorOnWall(row, col);
        switch (color) {
          case BLUE -> g.setColor(tileBlue);
          case YELLOW -> g.setColor(tileYellow);
          case RED -> g.setColor(tileRed);
          case BLACK -> g.setColor(tileBlack);
          case WHITE -> g.setColor(tileGreen);
          default -> throw new IllegalArgumentException("Color not recognised.");
        }
        g.drawRect(x + (sizeOfWallCell + wallBorder) * col,
            y + (sizeOfWallCell + wallBorder) * row,
            sizeOfWallCell, sizeOfWallCell);
      }
    }
    g.setColor(Color.black);
  }

  void drawWall(boolean[][] wall, int x, int y, Graphics g) {
    requireNonNull(wall);
    for (int row = 0; row < wall.length; row++) {
      for (int col = 0; col < wall[0].length; col++) {
        de.lmu.ifi.sosylab.model.Color color = getColorOnWall(row, col);
        switch (color) {
          case BLUE -> g.setColor(tileBlue);
          case YELLOW -> g.setColor(tileYellow);
          case RED -> g.setColor(tileRed);
          case BLACK -> g.setColor(tileBlack);
          case WHITE -> g.setColor(tileGreen);
          default -> throw new IllegalArgumentException("Color not recognised.");
        }
        if (wall[row][col]) {
          g.fillRect(x + (sizeOfWallCell + wallBorder) * col,
              y + (sizeOfWallCell + wallBorder) * row, sizeOfWallCell, sizeOfWallCell);
        }
      }
    }
  }

  void drawFloorLineFrames(Graphics g, IntPair[] coordinateMinusForPlayer, int x, int y) {
    String[] textPenaltyPoints = new String[]{"-1", "-1", "-2", "-2", "-2", "-3", "-3"};
    g.setColor(floorlinecolor);
    for (int i = 0; i < textPenaltyPoints.length; i++) {
      g.drawString(textPenaltyPoints[i], x + sizeOfMinusCell * i, y);
    }
    for (int i = 0; i < coordinateMinusForPlayer.length; i++) {
      g.drawRect(coordinateMinusForPlayer[i].getX(), coordinateMinusForPlayer[i].getY(),
          sizeOfMinusCell, sizeOfMinusCell);
    }
  }




  /**
   * Getter for color of the tile, located on the specified row and column on the wall.
   *
   * @param row    on the wall (from 0 to 4)
   * @param column on the wall (from 0 to 4)
   * @return de.lmu.ifi.sosylab.model.Color of tile
   */
  private de.lmu.ifi.sosylab.model.Color getColorOnWall(int row, int column) {
    return de.lmu.ifi.sosylab.model.Color.values()[(de.lmu.ifi.sosylab.model.Color.values().length +
        column - row) % WALL_SIZE];
  }
}
