package de.lmu.ifi.sosylab.view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

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

  public DrawboardPlayerBoardRight(int playerCount, List<String> nicknames) {
    this.playerCount = playerCount;
    this.nicknames = nicknames;
    initializePlayfieldRight();
    setPreferredSize(new Dimension(400, 700));
    repaint();
  }


  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g = (Graphics2D) g;
    drawPlayerTwo(g, nicknames.get(1));

    if(playerCount == 4){
      drawPlayerFour(g, nicknames.get(3));
    }
  }


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
    IntPair[] fifthPatternLinePlayerFour = {new IntPair(05, 410), new IntPair(40, 410), new IntPair(75, 410), new IntPair(110, 410), new IntPair(145, 410)};

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



  private void drawPlayerTwo(Graphics g, String nickname){
    g.setColor(Color.black);
    g.drawString(nickname, 5, 15);


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

    //Score:
    g.setColor(scorecolor);
    g.drawString("Punkte:", 5, 260);
    g.drawString("123", 50, 260);
  }

  private void drawPlayerFour(Graphics g, String nickname){
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

    //Score:
    g.setColor(scorecolor);
    g.drawString("Punkte:", 5, 560);
    g.drawString("123", 50, 560);
  }

}
