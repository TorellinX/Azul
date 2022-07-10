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
  private IntPair[] coordinateMinusPlayerTwo;

  private HashMap<Integer, IntPair[]> coordinatePatternLinesPlayerFour = new HashMap<>();
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
    drawWallFrames(g, wallCoordinatesProPlayer[1].getX(), wallCoordinatesProPlayer[1].getY());
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
    drawWallFrames(g, wallCoordinatesProPlayer[3].getX(), wallCoordinatesProPlayer[3].getY());
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

    drawWall(playerBoardPlayer2.getWall(), wallCoordinatesProPlayer[1].getX(),
        wallCoordinatesProPlayer[1].getY(), g);

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

    drawWall(playerBoardPlayer4.getWall(), wallCoordinatesProPlayer[3].getX(),
        wallCoordinatesProPlayer[3].getY(), g);

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
