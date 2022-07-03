package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
/**
 * Right Panel of the Game.
 * In which players two and four are drawn.
 */

public class DrawboardPlayerBoardRight extends JPanel {

  private static final long serialVersionUID = 1L;
  private Graphics2D g;
  private Color playerboardcolor = new Color(204, 201, 199);
  private Color scorecolor = new Color(235, 79, 0);
  private Color floorlinecolor = new Color(139, 0, 139);
  private int heightOfPatternLineCell = 35;
  private int widthOfPatternLineCell = 35;
  private int heightOfPlayFieldCell = 31;
  private int widthOfPlayFieldCell = 31;
  private int heightOfMinusCell = 35;
  private int widthOfMinusCell = 35;

  private HashMap<Integer, IntPair[]> coordinatePatternLinesPlayerTwo = new HashMap<>();
  private HashMap<Integer, IntPair[]> coordinateWallPlayerTwo = new HashMap<>();
  private IntPair[] coordinateMinusPlayerTwo;

  private HashMap<Integer, IntPair[]> coordinatePatternLinesPlayerFour = new HashMap<>();
  private HashMap<Integer, IntPair[]> coordinateWallPlayerFour = new HashMap<>();
  private IntPair[] coordinateMinusPlayerFour;
  private int playerCount;
  private List<String> nicknames;
  private List<Player> player;
  private String TEXT_POINTS = "Points: ";


  /**
   * Constructor of the panel.
   * @param playerCount Number of Player
   * @param nicknames Nickames of Player
   * @param player Player
   */

  public DrawboardPlayerBoardRight(int playerCount, List<String> nicknames, List<Player> player) {
    this.playerCount = playerCount;
    this.nicknames = nicknames;
    this.player = player;
    initializePlayfieldRight();
    setPreferredSize(new Dimension(400, 700));
    //repaint();
  }


  /**
   * Calls the methods to draw the players.
   * @param g the <code>Graphics</code> object to protect
   */

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g = (Graphics2D) g;
    drawPlayerTwoPlayfield(g);
    drawPlayerTwo(g);

    if(playerCount == 4){
      drawPlayerFourPlayfield(g, nicknames.get(3));
      drawPlayerFour(g);
    }
  }

  /**
   * Sets the coordinates for the individual objecte.
   */
  private void initializePlayfieldRight(){
    //Pattern Lines of Player Two
    IntPair[] firstPatternLinePlayerTwo = {new IntPair(145, 5)};
    IntPair[] secondPatternLinePlayerTwo = {new IntPair(145, 40), new IntPair(110, 40)};
    IntPair[] thirdPatternLinePlayerTwo = {new IntPair(145, 75), new IntPair(110, 75), new IntPair(75, 75)};
    IntPair[] fourthPatternLinePlayerTwo = {new IntPair(145, 110), new IntPair(110, 110), new IntPair(75, 110), new IntPair(40, 110)};
    IntPair[] fifthPatternLinePlayerTwo = {new IntPair(145, 145), new IntPair(110, 145), new IntPair(75, 145), new IntPair(40, 145), new IntPair(05, 145)};

    coordinatePatternLinesPlayerTwo.put(1, firstPatternLinePlayerTwo);
    coordinatePatternLinesPlayerTwo.put(2, secondPatternLinePlayerTwo);
    coordinatePatternLinesPlayerTwo.put(3, thirdPatternLinePlayerTwo);
    coordinatePatternLinesPlayerTwo.put(4, fourthPatternLinePlayerTwo);
    coordinatePatternLinesPlayerTwo.put(5, fifthPatternLinePlayerTwo);

    //Wall Player two
    IntPair[] blueWallPlayerTwo = {new IntPair(205, 10), new IntPair(240, 45), new IntPair(275, 80), new IntPair(310, 115), new IntPair(345, 150)};
    IntPair[] yellowWallPlayerTwo = {new IntPair(240, 10), new IntPair(275, 45), new IntPair(310, 80), new IntPair(345, 115), new IntPair(205, 150)};
    IntPair[] redWallPlayerTwo = {new IntPair(275, 10), new IntPair(310, 45), new IntPair(345, 80), new IntPair(205, 115), new IntPair(240, 150)};
    IntPair[] blackWallPlayerTwo = {new IntPair(310, 10), new IntPair(345, 45), new IntPair(205, 80), new IntPair(240, 115), new IntPair(275, 150)};
    IntPair[] greenWallPlayerTwo = {new IntPair(345, 10), new IntPair(205, 45), new IntPair(240, 80), new IntPair(275, 115), new IntPair(310, 150)};

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
      x+=35;
    }



    //Pattern Lines of Player Four
    IntPair[] firstPatternLinePlayerFour = {new IntPair(145, 305)};
    IntPair[] secondPatternLinePlayerFour = {new IntPair(145, 340), new IntPair(110, 340)};
    IntPair[] thirdPatternLinePlayerFour = {new IntPair(75, 375), new IntPair(110, 375), new IntPair(145, 375)};
    IntPair[] fourthPatternLinePlayerFour = {new IntPair(40, 410), new IntPair(75, 410), new IntPair(110, 410), new IntPair(145, 410)};
    IntPair[] fifthPatternLinePlayerFour = {new IntPair(05, 445), new IntPair(40, 445), new IntPair(75, 445), new IntPair(110, 445), new IntPair(145, 445)};

    coordinatePatternLinesPlayerFour.put(1, firstPatternLinePlayerFour);
    coordinatePatternLinesPlayerFour.put(2, secondPatternLinePlayerFour);
    coordinatePatternLinesPlayerFour.put(3, thirdPatternLinePlayerFour);
    coordinatePatternLinesPlayerFour.put(4, fourthPatternLinePlayerFour);
    coordinatePatternLinesPlayerFour.put(5, fifthPatternLinePlayerFour);

    // Wall Player Four
    IntPair[] blueWallPlayerThree = {new IntPair(205, 307), new IntPair(240, 342), new IntPair(275, 377), new IntPair(310, 412), new IntPair(345, 447)};
    IntPair[] yellowWallPlayerThree = {new IntPair(240, 307), new IntPair(275, 342), new IntPair(310, 377), new IntPair(345, 412), new IntPair(205, 447)};
    IntPair[] redWallPlayerThree = {new IntPair(275, 307), new IntPair(310, 342), new IntPair(345, 377), new IntPair(205, 412), new IntPair(240, 447)};
    IntPair[] blackWallPlayerThree = {new IntPair(310, 307), new IntPair(345, 342), new IntPair(205, 377), new IntPair(240, 412), new IntPair(275, 447)};
    IntPair[] greenWallPlayerThree = {new IntPair(345, 307), new IntPair(205, 342), new IntPair(240, 377), new IntPair(275, 412), new IntPair(310, 447)};

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
   * Playing field of the second player is drawn.
   * @param g
   */

  private void drawPlayerTwoPlayfield(Graphics g){

    //Rechtecke der Patternlines werden gezeichnet.
    ((Graphics2D) g).setStroke(new BasicStroke(1));

    for (int count = 1; count <= 5; count++) {
      IntPair[] speicher = coordinatePatternLinesPlayerTwo.get(count);
      for (int i = 0; i < speicher.length; i++) {
        g.drawRect(speicher[i].getX(), speicher[i].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
      }
    }
    //Hintergrund der Wall wird gezeichnet.
    g.setColor(playerboardcolor);
    g.fillRoundRect(200, 5, 181, 181, 20, 20);

    //Ränder der Rechtecke der Wall werden gezeichnet.
    ((Graphics2D) g).setStroke(new BasicStroke(2));
    g.setColor(Color.blue);
    for (int count = 1; count <= 5; count++) {
      IntPair[] zwischenspeicher = coordinateWallPlayerTwo.get(count);
      for (int i = 0; i < zwischenspeicher.length; i++) {
        g.drawRect(zwischenspeicher[i].getX(), zwischenspeicher[i].getY(), widthOfPlayFieldCell, heightOfPlayFieldCell);
      }
      if (count == 1) {
        g.setColor(Color.yellow);
      }
      if (count == 2) {
        g.setColor(Color.red);
      }
      if (count == 3) {
        g.setColor(Color.black);
      }
      if (count == 4) {
        g.setColor(Color.green);
      }
    }
    g.setColor(Color.black);

    //Minuspunkteleiste:
    g.setColor(floorlinecolor);
    g.drawString("-1", 15, 200);
    g.drawString("-1", 50, 200);
    g.drawString("-2", 85, 200);
    g.drawString("-2", 120, 200);
    g.drawString("-2", 155, 200);
    g.drawString("-3", 190, 200);
    g.drawString("-3", 225, 200);

    for (int i = 0; i < coordinateMinusPlayerTwo.length; i++) {
      g.drawRect(coordinateMinusPlayerTwo[i].getX(), coordinateMinusPlayerTwo[i].getY(), widthOfMinusCell, heightOfMinusCell);
    }

  }

  /**
   * Playing field of the fourth player is drawn.
   * @param g
   */

  private void drawPlayerFourPlayfield(Graphics g, String nickname){
    g.setColor(Color.black);
    g.drawString(nickname, 5, 315);

    //Rechtecke der Patternlines werden gezeichnet.
    ((Graphics2D) g).setStroke(new BasicStroke(1));

    for (int count = 1; count <= 5; count++) {
      IntPair[] speicher = coordinatePatternLinesPlayerFour.get(count);
      for (int i = 0; i < speicher.length; i++) {
        g.drawRect(speicher[i].getX(), speicher[i].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
      }
    }

    g.setColor(playerboardcolor);
    g.fillRoundRect(200, 300, 185, 185, 20, 20);
    ((Graphics2D) g).setStroke(new BasicStroke(2));
    g.setColor(Color.blue);

    for (int count = 1; count <= 5; count++) {
      IntPair[] zwischenspeicher = coordinateWallPlayerFour.get(count);
      for (int i = 0; i < zwischenspeicher.length; i++) {
        g.drawRect(zwischenspeicher[i].getX(), zwischenspeicher[i].getY(), widthOfPlayFieldCell, heightOfPlayFieldCell);
      }
      if (count == 1) {
        g.setColor(Color.yellow);
      }
      if (count == 2) {
        g.setColor(Color.red);
      }
      if (count == 3) {
        g.setColor(Color.black);
      }
      if (count == 4) {
        g.setColor(Color.green);
      }
    }
    g.setColor(Color.black);
    g.setColor(floorlinecolor);

    g.drawString("-1", 15, 500);
    g.drawString("-1", 50, 500);
    g.drawString("-2", 85, 500);
    g.drawString("-2", 120, 500);
    g.drawString("-2", 155, 500);
    g.drawString("-3", 190, 500);
    g.drawString("-3", 225, 500);
    g.setColor(floorlinecolor);

    for (int i = 0; i < coordinateMinusPlayerFour.length; i++) {
      g.drawRect(coordinateMinusPlayerFour[i].getX(), coordinateMinusPlayerFour[i].getY(), widthOfMinusCell, heightOfMinusCell);
    }

  }
  /**
   *Draws player two according to the model.
   * @param g Graphics Element
   */

  private void drawPlayerTwo(Graphics g){
    Player player2 = player.get(1);

    PlayerBoard playerBoardPlayer2 = player2.getPlayerBoard();

    ColorTile[][] patternLines = playerBoardPlayer2.getPatternLines();

    if(player2.getState().equals(PlayerState.TO_MOVE)){
      g.setColor(Color.green);
      g.drawString(player2.getNickname(), 5, 15);
    }else {
      g.setColor(Color.black);
      g.drawString(player2.getNickname(), 5, 15);
    }



    //Draw Pattern Line of Player One

    for (int i = 1; i < (patternLines.length+1); i++) {
      for (int j = 0; j < patternLines[(i-1)].length; j++) {
        if(patternLines[(i-1)][j] != null){

          IntPair[] cache = coordinatePatternLinesPlayerTwo.get(i);

            de.lmu.ifi.sosylab.model.Color colorOfTile = patternLines[i-1][j].getColor();
            if(colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.BLACK)){
              g.setColor(Color.black);
            } else if (colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.BLUE)) {
              g.setColor(Color.blue);
            } else if (colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.RED)) {
              g.setColor(Color.red);
            } else if (colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.YELLOW)) {
              g.setColor(Color.yellow);
            } else if (colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.WHITE)){
              //Keine Ahnung warum Model White, aber View macht grün draus
              g.setColor(Color.green);
            }
            g.fillRect(cache[j].getX(), cache[j].getY(), widthOfPatternLineCell, heightOfPatternLineCell);

        }
      }
    }

    // Draw Wall of Player One

    boolean[][] wall = playerBoardPlayer2.getWall();



    //draw Blue
    IntPair[] blueWall = coordinateWallPlayerTwo.get(1);
    for(int i = 0; i < 5; i++){
      for(int j = 0; j < 5; j++){
        if(wall[i][j] == true){
          g.setColor(Color.blue);
          g.fillRect(blueWall[i].getX(), blueWall[i].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
        }
      }
    }
    //draw Yellow
    IntPair[] yellowWall = coordinateWallPlayerTwo.get(2);
    g.setColor(Color.yellow);
    if(wall[0][1] == true){
      g.fillRect(yellowWall[0].getX(),yellowWall[0].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[1][2] == true){
      g.fillRect(yellowWall[1].getX(),yellowWall[1].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[2][3] == true){
      g.fillRect(yellowWall[2].getX(),yellowWall[2].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[3][4] == true){
      g.fillRect(yellowWall[3].getX(),yellowWall[3].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[4][0] == true){
      g.fillRect(yellowWall[4].getX(),yellowWall[4].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }


    //draw Red

    IntPair[] redWall = coordinateWallPlayerTwo.get(3);
    g.setColor(Color.red);
    if(wall[0][2] == true){
      g.fillRect(redWall[0].getX(),redWall[0].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[1][3] == true){
      g.fillRect(redWall[1].getX(),redWall[1].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[2][4] == true){
      g.fillRect(redWall[2].getX(),redWall[2].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[3][0] == true){
      g.fillRect(redWall[3].getX(),redWall[3].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[4][1] == true){
      g.fillRect(redWall[4].getX(),redWall[4].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }

    //draw Black

    IntPair[] blackWall = coordinateWallPlayerTwo.get(4);
    g.setColor(Color.black);
    if(wall[0][3] == true){
      g.fillRect(blackWall[0].getX(),blackWall[0].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[1][4] == true){
      g.fillRect(blackWall[1].getX(),blackWall[1].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[2][0] == true){
      g.fillRect(blackWall[2].getX(),blackWall[2].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[3][1] == true){
      g.fillRect(blackWall[3].getX(),blackWall[3].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[4][2] == true){
      g.fillRect(blackWall[4].getX(),blackWall[4].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }



    //draw Green
    IntPair[] greenWall = coordinateWallPlayerTwo.get(5);
    g.setColor(Color.green);
    if(wall[0][4] == true){
      g.fillRect(greenWall[0].getX(),greenWall[0].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[1][0] == true){
      g.fillRect(greenWall[1].getX(),greenWall[1].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[2][1] == true){
      g.fillRect(greenWall[2].getX(),greenWall[2].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[3][2] == true){
      g.fillRect(greenWall[3].getX(),greenWall[3].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[4][3] == true){
      g.fillRect(greenWall[4].getX(),greenWall[4].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }

    //drawMinuesTiles

    List<Tile> floorLine = playerBoardPlayer2.getFloorLine();

    if(floorLine.size() == 0){

    }else {
      for(int i = 0; i < floorLine.size(); i++){
        if(floorLine.get(i).toString() == "(-1)"){
          g.setColor(Color.gray);
        }else {
          if(floorLine.get(i).toString() == "BLUE"){
            g.setColor(Color.blue);
          }
          if(floorLine.get(i).toString() == "YELLOW"){
            g.setColor(Color.yellow);
          }
          if(floorLine.get(i).toString() == "RED"){
            g.setColor(Color.red);
          }
          if(floorLine.get(i).toString() == "BLACK"){
            g.setColor(Color.black);
          }
          if(floorLine.get(i).toString() == "WHITE"){
            g.setColor(Color.green);
          }
        }
        g.fillRect(coordinateMinusPlayerTwo[i].getX(), coordinateMinusPlayerTwo[i].getY(), widthOfMinusCell, heightOfMinusCell);
      }
    }

    //Score:
    g.setColor(scorecolor);
    g.drawString(TEXT_POINTS, 5, 260);
    g.drawString(Integer.toString(player2.getScore()), 50, 260);
  }

  /**
   *Draws player three according to the model.
   * @param g Graphics Element
   */

  private void drawPlayerFour(Graphics g){
    Player player4 = player.get(3);

    PlayerBoard playerBoardPlayer4 = player4.getPlayerBoard();

    ColorTile[][] patternLines = playerBoardPlayer4.getPatternLines();


    if(player4.getState().equals(PlayerState.TO_MOVE)){
      g.setColor(Color.green);
      g.drawString(player4.getNickname(), 5, 315);
    }
    else{
      g.setColor(Color.black);
      g.drawString(player4.getNickname(), 5, 315);
    }


    //Draw Pattern Line of Player One

    for (int i = 1; i < (patternLines.length+1); i++) {
      for (int j = 0; j < patternLines[(i-1)].length; j++) {
        if(patternLines[(i-1)][j] != null){

          IntPair[] cache = coordinatePatternLinesPlayerFour.get(i);

          de.lmu.ifi.sosylab.model.Color colorOfTile = patternLines[i-1][j].getColor();
          if(colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.BLACK)){
            g.setColor(Color.black);
          } else if (colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.BLUE)) {
            g.setColor(Color.blue);
          } else if (colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.RED)) {
            g.setColor(Color.red);
          } else if (colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.YELLOW)) {
            g.setColor(Color.yellow);
          } else if (colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.WHITE)){
            //Keine Ahnung warum Model White, aber View macht grün draus
            g.setColor(Color.green);
          }
          g.fillRect(cache[j].getX(), cache[j].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
        }
      }
    }

    // Draw Wall of Player One

    boolean[][] wall = playerBoardPlayer4.getWall();


    //draw Blue
    IntPair[] blueWall = coordinateWallPlayerFour.get(1);
    for(int i = 0; i < 5; i++){
      for(int j = 0; j < 5; j++){
        if(wall[i][j] == true){
          g.setColor(Color.blue);
          g.fillRect(blueWall[i].getX(), blueWall[i].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
        }
      }
    }
    //draw Yellow
    IntPair[] yellowWall = coordinateWallPlayerFour.get(2);
    g.setColor(Color.yellow);
    if(wall[0][1] == true){
      g.fillRect(yellowWall[0].getX(),yellowWall[0].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[1][2] == true){
      g.fillRect(yellowWall[1].getX(),yellowWall[1].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[2][3] == true){
      g.fillRect(yellowWall[2].getX(),yellowWall[2].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[3][4] == true){
      g.fillRect(yellowWall[3].getX(),yellowWall[3].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[4][0] == true){
      g.fillRect(yellowWall[4].getX(),yellowWall[4].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }


    //draw Red

    IntPair[] redWall = coordinateWallPlayerFour.get(3);
    g.setColor(Color.red);
    if(wall[0][2] == true){
      g.fillRect(redWall[0].getX(),redWall[0].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[1][3] == true){
      g.fillRect(redWall[1].getX(),redWall[1].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[2][4] == true){
      g.fillRect(redWall[2].getX(),redWall[2].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[3][0] == true){
      g.fillRect(redWall[3].getX(),redWall[3].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[4][1] == true){
      g.fillRect(redWall[4].getX(),redWall[4].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }

    //draw Black

    IntPair[] blackWall = coordinateWallPlayerFour.get(4);
    g.setColor(Color.black);
    if(wall[0][3] == true){
      g.fillRect(blackWall[0].getX(),blackWall[0].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[1][4] == true){
      g.fillRect(blackWall[1].getX(),blackWall[1].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[2][0] == true){
      g.fillRect(blackWall[2].getX(),blackWall[2].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[3][1] == true){
      g.fillRect(blackWall[3].getX(),blackWall[3].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[4][2] == true){
      g.fillRect(blackWall[4].getX(),blackWall[4].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }

    //draw Green
    IntPair[] greenWall = coordinateWallPlayerFour.get(5);
    g.setColor(Color.green);
    if(wall[0][4] == true){
      g.fillRect(greenWall[0].getX(),greenWall[0].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[1][0] == true){
      g.fillRect(greenWall[1].getX(),greenWall[1].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[2][1] == true){
      g.fillRect(greenWall[2].getX(),greenWall[2].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[3][2] == true){
      g.fillRect(greenWall[3].getX(),greenWall[3].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }
    if(wall[4][3] == true){
      g.fillRect(greenWall[4].getX(),greenWall[4].getY(), widthOfPatternLineCell, heightOfPatternLineCell);
    }

    //drawMinuesTiles

    List<Tile> floorLine = playerBoardPlayer4.getFloorLine();

    if(floorLine.size() == 0){

    }else {
      for(int i = 0; i < floorLine.size(); i++){
        if(floorLine.get(i).toString() == "(-1)"){
          g.setColor(Color.gray);
        }else {
          if(floorLine.get(i).toString() == "BLUE"){
            g.setColor(Color.blue);
          }
          if(floorLine.get(i).toString() == "YELLOW"){
            g.setColor(Color.yellow);
          }
          if(floorLine.get(i).toString() == "RED"){
            g.setColor(Color.red);
          }
          if(floorLine.get(i).toString() == "BLACK"){
            g.setColor(Color.black);
          }
          if(floorLine.get(i).toString() == "WHITE"){
            g.setColor(Color.green);
          }
        }
        g.fillRect(coordinateMinusPlayerFour[i].getX(), coordinateMinusPlayerFour[i].getY(), widthOfMinusCell, heightOfMinusCell);
      }
    }

    g.setColor(scorecolor);
    g.drawString(TEXT_POINTS, 5, 560);
    g.drawString(Integer.toString(player4.getScore()), 50, 560);
  }

}
