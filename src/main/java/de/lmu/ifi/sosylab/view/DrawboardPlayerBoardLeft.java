package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.model.ColorTile;
import de.lmu.ifi.sosylab.model.PenaltyTile;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.PlayerBoard;
import de.lmu.ifi.sosylab.model.Tile;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Collections;
import java.io.Serial;
import java.util.HashMap;
import java.util.List;
import javax.swing.JPanel;

/**
 * Left Panel of the Game. In which players one and two are drawn.
 */

public class DrawboardPlayerBoardLeft extends DrawboardPlayerBoard {

  @Serial
  private static final long serialVersionUID = 1L;
  private JPanel drawboardplayerboard;
  private Graphics2D g;
  private final Color playerboardcolor = new Color(204, 201, 199);
  private final int sizeOfPatternLineCell = 35;
  private final int sizeOfMinusCell = 35;

  private HashMap<Integer, IntPair[]> coordinatePatternLinesPlayerOne = new HashMap<>();
  private IntPair[] coordinateMinusPlayerOne;

  private HashMap<Integer, IntPair[]> coordinatePatternLinesPlayerThree = new HashMap<>();
  private IntPair[] coordinateMinusPlayerThree;
  private int playerCount;
  private List<String> nicknames;
  private List<Player> player;


  public DrawboardPlayerBoardLeft(int playerCount, List<String> nicknames, List<Player> player) {
    this.playerCount = playerCount;
    List<String> namesList = Collections.unmodifiableList(nicknames);
    this.nicknames = namesList;
    List<Player> playerList = Collections.unmodifiableList(player);
    this.player = playerList;
    initializePlayfieldLeft();
    setPreferredSize(new Dimension(400, 700));
    // repaint();

  }

  /**
   * Calls the methods to draw the players.
   *
   * @param g the <code>Graphics</code> object to protect
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g = (Graphics2D) g;
    drawPlayerOnePlayerBoard(g);
    drawPlayerOne(g);

    if (playerCount > 2) {
      drawPlayerThreePlayerBoard(g);
      drawPlayerThree(g);
    }
  }

  /**
   * Sets the coordinates for the individual objecte.
   */

  private void initializePlayfieldLeft() {

    //Pattern Lines of Player One
    IntPair[] firstPatternLinePlayerOne = {new IntPair(145, 5)};
    IntPair[] secondPatternLinePlayerOne = {new IntPair(110, 40), new IntPair(145, 40)};
    IntPair[] thirdPatternLinePlayerOne = {new IntPair(75, 75), new IntPair(110, 75),
        new IntPair(145, 75)};
    IntPair[] fourthPatternLinePlayerOne = {new IntPair(40, 110), new IntPair(75, 110),
        new IntPair(110, 110), new IntPair(145, 110),};
    IntPair[] fifthPatternLinePlayerOne = {new IntPair(05, 145), new IntPair(40, 145),
        new IntPair(75, 145), new IntPair(110, 145), new IntPair(145, 145)};

    coordinatePatternLinesPlayerOne.put(1, firstPatternLinePlayerOne);
    coordinatePatternLinesPlayerOne.put(2, secondPatternLinePlayerOne);
    coordinatePatternLinesPlayerOne.put(3, thirdPatternLinePlayerOne);
    coordinatePatternLinesPlayerOne.put(4, fourthPatternLinePlayerOne);
    coordinatePatternLinesPlayerOne.put(5, fifthPatternLinePlayerOne);

    //Minus Player One
    coordinateMinusPlayerOne = new IntPair[7];
    int x = 05;
    for (int i = 0; i < coordinateMinusPlayerOne.length; i++) {
      coordinateMinusPlayerOne[i] = new IntPair(x, 205);
      x += 35;
    }

    //Pattern Lines of Player Three
    IntPair[] firstPatternLinePlayerThree = {new IntPair(145, 305)};
    IntPair[] secondPatternLinePlayerThree = {new IntPair(110, 340), new IntPair(145, 340)};
    IntPair[] thirdPatternLinePlayerThree = {new IntPair(75, 375), new IntPair(110, 375),
        new IntPair(145, 375)};
    IntPair[] fourthPatternLinePlayerThree = {new IntPair(40, 410), new IntPair(75, 410),
        new IntPair(110, 410), new IntPair(145, 410),};
    IntPair[] fifthPatternLinePlayerThree = {new IntPair(05, 445), new IntPair(40, 445),
        new IntPair(75, 445), new IntPair(110, 445), new IntPair(145, 445)};

    coordinatePatternLinesPlayerThree.put(1, firstPatternLinePlayerThree);
    coordinatePatternLinesPlayerThree.put(2, secondPatternLinePlayerThree);
    coordinatePatternLinesPlayerThree.put(3, thirdPatternLinePlayerThree);
    coordinatePatternLinesPlayerThree.put(4, fourthPatternLinePlayerThree);
    coordinatePatternLinesPlayerThree.put(5, fifthPatternLinePlayerThree);

    // Minus Player Three
    x = 15;
    coordinateMinusPlayerThree = new IntPair[7];
    for (int i = 0; i < coordinateMinusPlayerThree.length; i++) {

      coordinateMinusPlayerThree[i] = new IntPair(x, 500);
      x += 35;
    }
  }

  /**
   * Player board of the first player is drawn.
   *
   * @param g
   */
  private void drawPlayerOnePlayerBoard(Graphics g) {
    drawPatternLinesFrames(g, coordinatePatternLinesPlayerOne);
    drawWallBackground(g, 200, 5);
    drawWallFrames(g, wallCoordinatesProPlayer[0].getX(), wallCoordinatesProPlayer[0].getY());
    drawFloorLineFrames(g, coordinateMinusPlayerOne, 15, 200);
  }

  /**
   * Player board of the third player is drawn.
   *
   * @param g
   */
  private void drawPlayerThreePlayerBoard(Graphics g) {
    drawPatternLinesFrames(g, coordinatePatternLinesPlayerThree);
    drawWallBackground(g, 200, 300);
    drawWallFrames(g, wallCoordinatesProPlayer[2].getX(), wallCoordinatesProPlayer[2].getY());
    drawFloorLineFrames(g, coordinateMinusPlayerThree, 15, 500);
  }

  /**
   * Draws player one according to the model.
   *
   * @param g Graphics Element
   */

  private void drawPlayerOne(Graphics g) {
    Player player1 = player.get(0);
    PlayerBoard playerBoardPlayer1 = player1.getPlayerBoard();
    ColorTile[][] patternLines = playerBoardPlayer1.getPatternLines();
    drawNickname(g, player1, 5, 15);

    //Draw Pattern Line of Player One
    for (int i = 1; i < (patternLines.length + 1); i++) {
      for (int j = 0; j < patternLines[(i - 1)].length; j++) {
        if (patternLines[(i - 1)][j] != null) {

          IntPair[] cache = coordinatePatternLinesPlayerOne.get(i);

          de.lmu.ifi.sosylab.model.Color colorOfTile = patternLines[i - 1][j].getColor();
          if (colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.BLACK)) {
            g.setColor(Color.black);
          } else if (colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.BLUE)) {
            g.setColor(Color.blue);
          } else if (colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.RED)) {
            g.setColor(Color.red);
          } else if (colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.YELLOW)) {
            g.setColor(Color.yellow);
          } else if (colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.WHITE)) {
            //Keine Ahnung warum Model White, aber View macht grün draus
            g.setColor(Color.green);
          }
          g.fillRect(cache[j].getX(), cache[j].getY(), sizeOfPatternLineCell,
              sizeOfPatternLineCell);
        }
      }
    }

    drawWall(playerBoardPlayer1.getWall(), wallCoordinatesProPlayer[0].getX(),
        wallCoordinatesProPlayer[0].getY(), g);

    //drawFloorlineTiles
    List<Tile> floorLine = playerBoardPlayer1.getFloorLine();
    if (floorLine.size() == 0) {
    } else {
      for (int i = 0; i < floorLine.size(); i++) {
        if (floorLine.get(i) instanceof PenaltyTile) {
          g.setColor(Color.gray);
        } else {
          if (floorLine.get(i).toString().equals("BLUE")) {
            g.setColor(Color.blue);
          }
          if (floorLine.get(i).toString().equals("YELLOW")) {
            g.setColor(Color.yellow);
          }
          if (floorLine.get(i).toString().equals("RED")) {
            g.setColor(Color.red);
          }
          if (floorLine.get(i).toString().equals("BLACK")) {
            g.setColor(Color.black);
          }
          if (floorLine.get(i).toString().equals("WHITE")) {
            g.setColor(Color.green);
          }
        }
        g.fillRect(coordinateMinusPlayerOne[i].getX(), coordinateMinusPlayerOne[i].getY(),
            sizeOfMinusCell, sizeOfMinusCell);
      }
    }
    drawScores(g, player1, 5, 260);
  }

  /**
   * Draws player three according to the model.
   *
   * @param g Graphics Element
   */

  private void drawPlayerThree(Graphics g) {
    Player player3 = player.get(2);
    PlayerBoard playerBoardPlayer3 = player3.getPlayerBoard();
    ColorTile[][] patternLines = playerBoardPlayer3.getPatternLines();
    drawNickname(g, player3, 4, 315);

    //Draw Pattern Line of Player Three
    for (int i = 1; i < (patternLines.length + 1); i++) {
      for (int j = 0; j < patternLines[(i - 1)].length; j++) {
        if (patternLines[(i - 1)][j] != null) {

          IntPair[] cache = coordinatePatternLinesPlayerThree.get(i);

          de.lmu.ifi.sosylab.model.Color colorOfTile = patternLines[i - 1][j].getColor();
          if (colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.BLACK)) {
            g.setColor(Color.black);
          } else if (colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.BLUE)) {
            g.setColor(Color.blue);
          } else if (colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.RED)) {
            g.setColor(Color.red);
          } else if (colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.YELLOW)) {
            g.setColor(Color.yellow);
          } else if (colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.WHITE)) {
            //Keine Ahnung warum Model White, aber View macht grün draus
            g.setColor(Color.green);
          }
          g.fillRect(cache[j].getX(), cache[j].getY(), sizeOfPatternLineCell,
              sizeOfPatternLineCell);
        }
      }
    }

    drawWall(playerBoardPlayer3.getWall(), wallCoordinatesProPlayer[2].getX(),
        wallCoordinatesProPlayer[2].getY(), g);

    //drawMinuesTiles
    List<Tile> floorLine = playerBoardPlayer3.getFloorLine();
    if (floorLine.size() == 0) {
      ;
    } else {
      for (int i = 0; i < floorLine.size(); i++) {
        if (floorLine.get(i).toString().equals("(-1)")) {
          g.setColor(Color.gray);
        } else {
          if (floorLine.get(i).toString().equals("BLUE")) {
            g.setColor(Color.blue);
          }
          if (floorLine.get(i).toString().equals("YELLOW")) {
            g.setColor(Color.yellow);
          }
          if (floorLine.get(i).toString().equals("RED")) {
            g.setColor(Color.red);
          }
          if (floorLine.get(i).toString().equals("BLACK")) {
            g.setColor(Color.black);
          }
          if (floorLine.get(i).toString().equals("WHITE")) {
            g.setColor(Color.green);
          }
        }
        g.fillRect(coordinateMinusPlayerThree[i].getX(), coordinateMinusPlayerThree[i].getY(),
            sizeOfMinusCell, sizeOfMinusCell);
      }
    }
    drawScores(g, player3, 5, 560);
  }


}
