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
import java.io.Serial;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.swing.JPanel;

/**
 * Left Panel of the Game. Herein players one and two are drawn.
 */

public class DrawboardPlayerBoardLeft extends DrawboardPlayerBoard {

  @Serial
  private static final long serialVersionUID = 1L;
  private JPanel drawboardplayerboard;
  // A field "Graphics2D g" is not required, as always the current graphics object is referenced.
  // private Graphics2D g;
  private final Color playerboardcolor = new Color(204, 201, 199);
  private final int sizeOfPatternLineCell = 35;
  private final int sizeOfMinusCell = 35;

  private HashMap<Integer, IntPair[]> coordinatePatternLinesPlayerOne = new HashMap<>();
  private HashMap<Integer, IntPair[]> coordinateWallPlayerOne = new HashMap<>();
  private IntPair[] coordinateMinusPlayerOne;

  private HashMap<Integer, IntPair[]> coordinatePatternLinesPlayerThree = new HashMap<>();
  private HashMap<Integer, IntPair[]> coordinateWallPlayerThree = new HashMap<>();
  private IntPair[] coordinateMinusPlayerThree;
  private int playerCount;
  private List<String> nicknames;
  private List<Player> player;


  /**
   * Draw lefthand side of the playerboard.
   *
   * @param playerCount  number of players
   * @param nicknames    list of player nicknames
   * @param player       list of player instances
   */
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
   * @param g graphics object - kind of "internal reference"
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // Self assignemet, as Graphics is superclass zu Graphics2G
    // g = (Graphics2D) g;
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
        new IntPair(110, 110), new IntPair(145, 110)};
    IntPair[] fifthPatternLinePlayerOne = {new IntPair(05, 145), new IntPair(40, 145),
        new IntPair(75, 145), new IntPair(110, 145), new IntPair(145, 145)};

    coordinatePatternLinesPlayerOne.put(1, firstPatternLinePlayerOne);
    coordinatePatternLinesPlayerOne.put(2, secondPatternLinePlayerOne);
    coordinatePatternLinesPlayerOne.put(3, thirdPatternLinePlayerOne);
    coordinatePatternLinesPlayerOne.put(4, fourthPatternLinePlayerOne);
    coordinatePatternLinesPlayerOne.put(5, fifthPatternLinePlayerOne);

    //Wall Player One
    IntPair[] blueWallPlayerOne = {new IntPair(205, 10), new IntPair(240, 45), new IntPair(275, 80),
        new IntPair(310, 115), new IntPair(345, 150)};
    IntPair[] yellowWallPlayerOne = {new IntPair(240, 10), new IntPair(275, 45),
        new IntPair(310, 80), new IntPair(345, 115), new IntPair(205, 150)};
    IntPair[] redWallPlayerOne = {new IntPair(275, 10), new IntPair(310, 45), new IntPair(345, 80),
        new IntPair(205, 115), new IntPair(240, 150)};
    IntPair[] blackWallPlayerOne = {new IntPair(310, 10), new IntPair(345, 45),
        new IntPair(205, 80), new IntPair(240, 115), new IntPair(275, 150)};
    IntPair[] greenWallPlayerOne = {new IntPair(345, 10), new IntPair(205, 45),
        new IntPair(240, 80), new IntPair(275, 115), new IntPair(310, 150)};

    coordinateWallPlayerOne.put(1, blueWallPlayerOne);
    coordinateWallPlayerOne.put(2, yellowWallPlayerOne);
    coordinateWallPlayerOne.put(3, redWallPlayerOne);
    coordinateWallPlayerOne.put(4, blackWallPlayerOne);
    coordinateWallPlayerOne.put(5, greenWallPlayerOne);

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
        new IntPair(110, 410), new IntPair(145, 410)};
    IntPair[] fifthPatternLinePlayerThree = {new IntPair(05, 445), new IntPair(40, 445),
        new IntPair(75, 445), new IntPair(110, 445), new IntPair(145, 445)};

    coordinatePatternLinesPlayerThree.put(1, firstPatternLinePlayerThree);
    coordinatePatternLinesPlayerThree.put(2, secondPatternLinePlayerThree);
    coordinatePatternLinesPlayerThree.put(3, thirdPatternLinePlayerThree);
    coordinatePatternLinesPlayerThree.put(4, fourthPatternLinePlayerThree);
    coordinatePatternLinesPlayerThree.put(5, fifthPatternLinePlayerThree);

    // Wall Player Three
    IntPair[] blueWallPlayerThree = {new IntPair(205, 307), new IntPair(240, 342),
        new IntPair(275, 377), new IntPair(310, 412), new IntPair(345, 447)};
    IntPair[] yellowWallPlayerThree = {new IntPair(240, 307), new IntPair(275, 342),
        new IntPair(310, 377), new IntPair(345, 412), new IntPair(205, 447)};
    IntPair[] redWallPlayerThree = {new IntPair(275, 307), new IntPair(310, 342),
        new IntPair(345, 377), new IntPair(205, 412), new IntPair(240, 447)};
    IntPair[] blackWallPlayerThree = {new IntPair(310, 307), new IntPair(345, 342),
        new IntPair(205, 377), new IntPair(240, 412), new IntPair(275, 447)};
    IntPair[] greenWallPlayerThree = {new IntPair(345, 307), new IntPair(205, 342),
        new IntPair(240, 377), new IntPair(275, 412), new IntPair(310, 447)};

    coordinateWallPlayerThree.put(1, blueWallPlayerThree);
    coordinateWallPlayerThree.put(2, yellowWallPlayerThree);
    coordinateWallPlayerThree.put(3, redWallPlayerThree);
    coordinateWallPlayerThree.put(4, blackWallPlayerThree);
    coordinateWallPlayerThree.put(5, greenWallPlayerThree);

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
   * @param g graphics object - kind of "internal reference"
   */
  private void drawPlayerOnePlayerBoard(Graphics g) {
    drawPatternLinesFrames(g, coordinatePatternLinesPlayerOne);
    drawWallBackground(g, 200, 5);
    drawWallFrames(g, coordinateWallPlayerOne);
    drawFloorLineFrames(g, coordinateMinusPlayerOne, 15, 200);
  }

  /**
   * Player board of the third player is drawn.
   *
   * @param g graphics object - kind of "internal reference"
   */
  private void drawPlayerThreePlayerBoard(Graphics g) {
    drawPatternLinesFrames(g, coordinatePatternLinesPlayerThree);
    drawWallBackground(g, 200, 300);
    drawWallFrames(g, coordinateWallPlayerThree);
    drawFloorLineFrames(g, coordinateMinusPlayerThree, 15, 500);
  }

  /**
   * Draws player one according to the model.
   *
   * @param g graphics object - kind of "internal reference"
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

    // Draw Wall of Player One
    boolean[][] wall = playerBoardPlayer1.getWall();

    //draw Blue
    IntPair[] blueWall = coordinateWallPlayerOne.get(1);
    g.setColor(Color.blue);
    if (wall[0][1]) {
      g.fillRect(blueWall[0].getX(), blueWall[0].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[1][2]) {
      g.fillRect(blueWall[1].getX(), blueWall[1].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[2][3]) {
      g.fillRect(blueWall[2].getX(), blueWall[2].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[3][4]) {
      g.fillRect(blueWall[3].getX(), blueWall[3].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[4][0]) {
      g.fillRect(blueWall[4].getX(), blueWall[4].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    //draw Yellow
    IntPair[] yellowWall = coordinateWallPlayerOne.get(2);
    g.setColor(Color.yellow);
    if (wall[0][1]) {
      g.fillRect(yellowWall[0].getX(), yellowWall[0].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[1][2]) {
      g.fillRect(yellowWall[1].getX(), yellowWall[1].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[2][3]) {
      g.fillRect(yellowWall[2].getX(), yellowWall[2].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[3][4]) {
      g.fillRect(yellowWall[3].getX(), yellowWall[3].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[4][0]) {
      g.fillRect(yellowWall[4].getX(), yellowWall[4].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }

    //draw Red
    IntPair[] redWall = coordinateWallPlayerOne.get(3);
    g.setColor(Color.red);
    if (wall[0][2]) {
      g.fillRect(redWall[0].getX(), redWall[0].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[1][3]) {
      g.fillRect(redWall[1].getX(), redWall[1].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[2][4]) {
      g.fillRect(redWall[2].getX(), redWall[2].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[3][0]) {
      g.fillRect(redWall[3].getX(), redWall[3].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[4][1]) {
      g.fillRect(redWall[4].getX(), redWall[4].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }

    //draw Black
    IntPair[] blackWall = coordinateWallPlayerOne.get(4);
    g.setColor(Color.black);
    if (wall[0][3]) {
      g.fillRect(blackWall[0].getX(), blackWall[0].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[1][4]) {
      g.fillRect(blackWall[1].getX(), blackWall[1].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[2][0]) {
      g.fillRect(blackWall[2].getX(), blackWall[2].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[3][1]) {
      g.fillRect(blackWall[3].getX(), blackWall[3].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[4][2]) {
      g.fillRect(blackWall[4].getX(), blackWall[4].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }

    //draw Green
    IntPair[] greenWall = coordinateWallPlayerOne.get(5);
    g.setColor(Color.green);
    if (wall[0][4]) {
      g.fillRect(greenWall[0].getX(), greenWall[0].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[1][0]) {
      g.fillRect(greenWall[1].getX(), greenWall[1].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[2][1]) {
      g.fillRect(greenWall[2].getX(), greenWall[2].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[3][2]) {
      g.fillRect(greenWall[3].getX(), greenWall[3].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[4][3]) {
      g.fillRect(greenWall[4].getX(), greenWall[4].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }

    //drawFloorlineTiles
    List<Tile> floorLine = playerBoardPlayer1.getFloorLine();
    if (floorLine.size() == 0) {
      ;
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
   * @param g graphics object - kind of "internal reference"
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

    // Draw Wall of Player One
    boolean[][] wall = playerBoardPlayer3.getWall();
    //draw Blue
    IntPair[] blueWall = coordinateWallPlayerThree.get(1);
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (wall[i][j] == true) {
          g.setColor(Color.blue);
          g.fillRect(blueWall[i].getX(), blueWall[i].getY(), sizeOfPatternLineCell,
              sizeOfPatternLineCell);
        }
      }
    }
    //draw Yellow
    IntPair[] yellowWall = coordinateWallPlayerThree.get(2);
    g.setColor(Color.yellow);
    if (wall[0][1] == true) {
      g.fillRect(yellowWall[0].getX(), yellowWall[0].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[1][2] == true) {
      g.fillRect(yellowWall[1].getX(), yellowWall[1].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[2][3] == true) {
      g.fillRect(yellowWall[2].getX(), yellowWall[2].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[3][4] == true) {
      g.fillRect(yellowWall[3].getX(), yellowWall[3].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[4][0] == true) {
      g.fillRect(yellowWall[4].getX(), yellowWall[4].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }

    //draw Red
    IntPair[] redWall = coordinateWallPlayerThree.get(3);
    g.setColor(Color.red);
    if (wall[0][2] == true) {
      g.fillRect(redWall[0].getX(), redWall[0].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[1][3] == true) {
      g.fillRect(redWall[1].getX(), redWall[1].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[2][4] == true) {
      g.fillRect(redWall[2].getX(), redWall[2].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[3][0] == true) {
      g.fillRect(redWall[3].getX(), redWall[3].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[4][1] == true) {
      g.fillRect(redWall[4].getX(), redWall[4].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }

    //draw Black
    IntPair[] blackWall = coordinateWallPlayerThree.get(4);
    g.setColor(Color.black);
    if (wall[0][3] == true) {
      g.fillRect(blackWall[0].getX(), blackWall[0].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[1][4] == true) {
      g.fillRect(blackWall[1].getX(), blackWall[1].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[2][0] == true) {
      g.fillRect(blackWall[2].getX(), blackWall[2].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[3][1] == true) {
      g.fillRect(blackWall[3].getX(), blackWall[3].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[4][2] == true) {
      g.fillRect(blackWall[4].getX(), blackWall[4].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }

    //draw Green
    IntPair[] greenWall = coordinateWallPlayerThree.get(5);
    g.setColor(Color.green);
    if (wall[0][4] == true) {
      g.fillRect(greenWall[0].getX(), greenWall[0].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[1][0] == true) {
      g.fillRect(greenWall[1].getX(), greenWall[1].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[2][1] == true) {
      g.fillRect(greenWall[2].getX(), greenWall[2].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[3][2] == true) {
      g.fillRect(greenWall[3].getX(), greenWall[3].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }
    if (wall[4][3] == true) {
      g.fillRect(greenWall[4].getX(), greenWall[4].getY(), sizeOfPatternLineCell,
          sizeOfPatternLineCell);
    }

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
