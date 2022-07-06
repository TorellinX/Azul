package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.model.ColorTile;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.PlayerBoard;
import de.lmu.ifi.sosylab.model.Tile;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Right Panel of the Game, carries the wall.
 */
public class DrawboardPlayerBoardRight extends DrawboardPlayerBoard {

  private static final long serialVersionUID = 1L;
  // A field "Graphics2D g" is not required, as always the current graphics object is referenced.
  // private Graphics2D g;
  private Color playerboardcolor = new Color(204, 201, 199);
  private Color scorecolor = new Color(235, 79, 0);
  private int sizeOfPatternLineCell = 35;
  private int sizeOfMinusCell = 35;

  private HashMap<Integer, IntPair[]> coordinatePatternLinesPlayerTwo = new HashMap<>();
  private HashMap<Integer, IntPair[]> coordinateWallPlayerTwo = new HashMap<>();
  private IntPair[] coordinateMinusPlayerTwo;

  private HashMap<Integer, IntPair[]> coordinatePatternLinesPlayerFour = new HashMap<>();
  private HashMap<Integer, IntPair[]> coordinateWallPlayerFour = new HashMap<>();
  private IntPair[] coordinateMinusPlayerFour;
  private int playerCount;
  private List<String> nicknames;
  private List<Player> player;


  /**
   * Constructor of the panel.
   *
   * @param playerCount Number of Player
   * @param nicknames   Nickames of Player
   * @param player      Player
   */

  public DrawboardPlayerBoardRight(int playerCount, List<String> nicknames, List<Player> player) {
    this.playerCount = playerCount;
    List<String> namesList = Collections.unmodifiableList(nicknames);
    this.nicknames = namesList;
    List<Player> playerList = Collections.unmodifiableList(player);
    this.player = playerList;
    initializePlayfieldRight();
    setPreferredSize(new Dimension(400, 700));
    //repaint();
  }


  /**
   * Calls the methods to draw the players.
   *
   * @param g graphics object - kind of internal reference
   */

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // Self assignemet, as Graphics is superclass zu Graphics2G
    // g = (Graphics2D) g;
    drawPlayerTwoPlayerBoard(g);
    drawPlayerTwo(g);

    if (playerCount == 4) {
      drawPlayerFourPlayerBoard(g, nicknames.get(3));
      drawPlayerFour(g);
    }
  }

  public void repaintRightBoard() {
    repaint();
  }

  /**
   * Sets the coordinates for the individual objects.
   */
  private void initializePlayfieldRight() {

    IntPair[] firstPatternLinePlayerTwo = {new IntPair(145, 5)};
    IntPair[] secondPatternLinePlayerTwo = {new IntPair(110, 40), new IntPair(145, 40)};
    IntPair[] thirdPatternLinePlayerTwo = {new IntPair(75, 75), new IntPair(110, 75),
        new IntPair(145, 75)};
    IntPair[] fourthPatternLinePlayerTwo = {new IntPair(40, 110), new IntPair(75, 110),
        new IntPair(110, 110), new IntPair(145, 110)};
    IntPair[] fifthPatternLinePlayerTwo = {new IntPair(05, 145), new IntPair(40, 145),
        new IntPair(75, 145), new IntPair(110, 145), new IntPair(145, 145)};

    coordinatePatternLinesPlayerTwo.put(1, firstPatternLinePlayerTwo);
    coordinatePatternLinesPlayerTwo.put(2, secondPatternLinePlayerTwo);
    coordinatePatternLinesPlayerTwo.put(3, thirdPatternLinePlayerTwo);
    coordinatePatternLinesPlayerTwo.put(4, fourthPatternLinePlayerTwo);
    coordinatePatternLinesPlayerTwo.put(5, fifthPatternLinePlayerTwo);

    //Wall Player two
    IntPair[] blueWallPlayerTwo = {new IntPair(205, 10), new IntPair(240, 45), new IntPair(275, 80),
        new IntPair(310, 115), new IntPair(345, 150)};
    IntPair[] yellowWallPlayerTwo = {new IntPair(240, 10), new IntPair(275, 45),
        new IntPair(310, 80), new IntPair(345, 115), new IntPair(205, 150)};
    IntPair[] redWallPlayerTwo = {new IntPair(275, 10), new IntPair(310, 45), new IntPair(345, 80),
        new IntPair(205, 115), new IntPair(240, 150)};
    IntPair[] blackWallPlayerTwo = {new IntPair(310, 10), new IntPair(345, 45),
        new IntPair(205, 80), new IntPair(240, 115), new IntPair(275, 150)};
    IntPair[] greenWallPlayerTwo = {new IntPair(345, 10), new IntPair(205, 45),
        new IntPair(240, 80), new IntPair(275, 115), new IntPair(310, 150)};

    coordinateWallPlayerTwo.put(1, blueWallPlayerTwo);
    coordinateWallPlayerTwo.put(2, yellowWallPlayerTwo);
    coordinateWallPlayerTwo.put(3, redWallPlayerTwo);
    coordinateWallPlayerTwo.put(4, blackWallPlayerTwo);
    coordinateWallPlayerTwo.put(5, greenWallPlayerTwo);

    //Minus Player two
    coordinateMinusPlayerTwo = new IntPair[7];
    int x = 05;
    for (int i = 0; i < coordinateMinusPlayerTwo.length; i++) {
      coordinateMinusPlayerTwo[i] = new IntPair(x, 205);
      x += 35;
    }

    //Pattern Lines of Player Four
    IntPair[] firstPatternLinePlayerFour = {new IntPair(145, 305)};
    IntPair[] secondPatternLinePlayerFour = {new IntPair(110, 340), new IntPair(145, 340)};
    IntPair[] thirdPatternLinePlayerFour = {new IntPair(75, 375), new IntPair(110, 375),
        new IntPair(145, 375)};
    IntPair[] fourthPatternLinePlayerFour = {new IntPair(40, 410), new IntPair(75, 410),
        new IntPair(110, 410), new IntPair(145, 410)};
    IntPair[] fifthPatternLinePlayerFour = {new IntPair(05, 445), new IntPair(40, 445),
        new IntPair(75, 445), new IntPair(110, 445), new IntPair(145, 445)};

    coordinatePatternLinesPlayerFour.put(1, firstPatternLinePlayerFour);
    coordinatePatternLinesPlayerFour.put(2, secondPatternLinePlayerFour);
    coordinatePatternLinesPlayerFour.put(3, thirdPatternLinePlayerFour);
    coordinatePatternLinesPlayerFour.put(4, fourthPatternLinePlayerFour);
    coordinatePatternLinesPlayerFour.put(5, fifthPatternLinePlayerFour);

    // Wall Player Four
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

    coordinateWallPlayerFour.put(1, blueWallPlayerThree);
    coordinateWallPlayerFour.put(2, yellowWallPlayerThree);
    coordinateWallPlayerFour.put(3, redWallPlayerThree);
    coordinateWallPlayerFour.put(4, blackWallPlayerThree);
    coordinateWallPlayerFour.put(5, greenWallPlayerThree);

    // Minus Player Four
    x = 15;
    coordinateMinusPlayerFour = new IntPair[7];
    for (int i = 0; i < coordinateMinusPlayerFour.length; i++) {

      coordinateMinusPlayerFour[i] = new IntPair(x, 500);
      x += 35;
    }
  }

  /**
   * Player board of the second player is drawn.
   *
   * @param g graphics object - kind of "internal reference"
   */
  private void drawPlayerTwoPlayerBoard(Graphics g) {
    drawPatternLinesFrames(g, coordinatePatternLinesPlayerTwo);
    drawWallBackground(g, 200, 5);
    drawWallFrames(g, coordinateWallPlayerTwo);
    drawFloorLineFrames(g, coordinateMinusPlayerTwo, 15, 200);
  }

  /**
   * Player board of the fourth player is drawn.
   *
   * @param g graphics object - kind of "internal reference"
   */
  private void drawPlayerFourPlayerBoard(Graphics g, String nickname) {
    drawPatternLinesFrames(g, coordinatePatternLinesPlayerFour);
    drawWallBackground(g, 200, 300);
    drawWallFrames(g, coordinateWallPlayerFour);
    drawFloorLineFrames(g, coordinateMinusPlayerFour, 15, 500);
  }

  /**
   * Draws player two according to the model.
   *
   * @param g graphics object - kind of "internal reference"
   */

  private void drawPlayerTwo(Graphics g) {
    Player player2 = player.get(1);

    PlayerBoard playerBoardPlayer2 = player2.getPlayerBoard();

    ColorTile[][] patternLines = playerBoardPlayer2.getPatternLines();

    drawNickname(g, player2, 5, 15);

    //Draw Pattern Line of Player Two
    for (int i = 1; i < (patternLines.length + 1); i++) {
      for (int j = 0; j < patternLines[(i - 1)].length; j++) {
        if (patternLines[(i - 1)][j] != null) {

          IntPair[] cache = coordinatePatternLinesPlayerTwo.get(i);

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

    // Draw Wall of Player Two
    boolean[][] wall = playerBoardPlayer2.getWall();
    //draw Blue
    IntPair[] blueWall = coordinateWallPlayerTwo.get(1);
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
    IntPair[] yellowWall = coordinateWallPlayerTwo.get(2);
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
    IntPair[] redWall = coordinateWallPlayerTwo.get(3);
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
    IntPair[] blackWall = coordinateWallPlayerTwo.get(4);
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
    IntPair[] greenWall = coordinateWallPlayerTwo.get(5);
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
    List<Tile> floorLine = playerBoardPlayer2.getFloorLine();
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
        g.fillRect(coordinateMinusPlayerTwo[i].getX(), coordinateMinusPlayerTwo[i].getY(),
            sizeOfMinusCell, sizeOfMinusCell);
      }
    }
    drawScores(g, player2, 5, 260);
  }

  /**
   * Draws player three according to the model.
   *
   * @param g Graphics Element
   */

  private void drawPlayerFour(Graphics g) {
    Player player4 = player.get(3);

    PlayerBoard playerBoardPlayer4 = player4.getPlayerBoard();

    ColorTile[][] patternLines = playerBoardPlayer4.getPatternLines();

    drawNickname(g, player4, 5, 315);

    //Draw Pattern Line of Player Four
    for (int i = 1; i < (patternLines.length + 1); i++) {
      for (int j = 0; j < patternLines[(i - 1)].length; j++) {
        if (patternLines[(i - 1)][j] != null) {

          IntPair[] cache = coordinatePatternLinesPlayerFour.get(i);

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

    // Draw Wall of Player Four
    boolean[][] wall = playerBoardPlayer4.getWall();
    //draw Blue
    IntPair[] blueWall = coordinateWallPlayerFour.get(1);
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
    IntPair[] yellowWall = coordinateWallPlayerFour.get(2);
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
    IntPair[] redWall = coordinateWallPlayerFour.get(3);
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
    IntPair[] blackWall = coordinateWallPlayerFour.get(4);
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
    IntPair[] greenWall = coordinateWallPlayerFour.get(5);
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
    List<Tile> floorLine = playerBoardPlayer4.getFloorLine();
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
        g.fillRect(coordinateMinusPlayerFour[i].getX(), coordinateMinusPlayerFour[i].getY(),
            sizeOfMinusCell, sizeOfMinusCell);
      }
    }

    drawScores(g, player4, 5, 560);
  }

}
