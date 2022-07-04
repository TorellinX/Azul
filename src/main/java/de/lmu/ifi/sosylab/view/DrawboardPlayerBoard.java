package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.PlayerState;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import javax.swing.JPanel;

public class DrawboardPlayerBoard extends JPanel{

  private final Color playerboardcolor = new Color(204, 201, 199);
  private final Color scorecolor = new Color(235, 79, 0);
  private final Color floorlinecolor = new Color(139, 0, 139);
  private final Color patternLinesColorRight = Color.black;
  private final Color activePlayerColorRight = Color.green;
  private final Color activePlayerColor = Color.green;
  private final Color inactivePlayerColor = Color.black;
  private String TEXT_POINTS = "Points: ";
  private int heightOfPatternLineCell = 35;
  private int widthOfPatternLineCell = 35;


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
    g.drawString(TEXT_POINTS + Integer.toString(player.getScore()), x, y);
    g.setFont(g.getFont().deriveFont(Font.PLAIN));
  }

  void drawPatternLinesFrames(Graphics g, HashMap<Integer, IntPair[]> coordinatePatternLines) {
    ((Graphics2D) g).setStroke(new BasicStroke(1));
    g.setColor(patternLinesColorRight);

    for (int count = 1; count <= 5; count++) {
      IntPair[] speicher = coordinatePatternLines.get(count);
      for (int i = 0; i < speicher.length; i++) {
        g.drawRect(speicher[i].getX(), speicher[i].getY(), widthOfPatternLineCell,
            heightOfPatternLineCell);
      }
    }

  }

}
