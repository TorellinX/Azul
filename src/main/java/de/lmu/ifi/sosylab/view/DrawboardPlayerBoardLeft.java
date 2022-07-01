package main.java.de.lmu.ifi.sosylab.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DrawboardPlayerBoardLeft extends JPanel {

  private static final long serialVersionUID = 1L;
  private JPanel drawboardplayerboard;
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

  private HashMap<Integer, IntPair[]> coordinatePatternLinesPlayerOne = new HashMap<>();
  private HashMap<Integer, IntPair[]> coordinateWallPlayerOne = new HashMap<>();
  private IntPair[] coordinateMinusPlayerOne;

  private HashMap<Integer, IntPair[]> coordinatePatternLinesPlayerThree = new HashMap<>();
  private HashMap<Integer, IntPair[]> coordinateWallPlayerThree = new HashMap<>();
  private IntPair[] coordinateMinusPlayerThree;


  public DrawboardPlayerBoardLeft() {
    initializePlayfieldLeft();
    setPreferredSize(new Dimension(400, 700));
    repaint();
  }

  public JPanel getPanel() {
    return drawboardplayerboard;
  }


  @Override

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g = (Graphics2D) g;
    drawPlayerOnePlayfield(g, "Hubertus von Nepomuk");
    drawPlayerThree(g, "Walter von der Vogelweide");
  }

  private void initializePlayfieldLeft() {
    //Pattern Lines of Player One
    IntPair[] firstPatternLinePlayerOne = {new IntPair(145, 6)};
    IntPair[] secondPatternLinePlayerOne = {new IntPair(145, 40), new IntPair(110, 40)};
    IntPair[] thirdPatternLinePlayerOne = {new IntPair(145, 75), new IntPair(110, 75), new IntPair(75, 75)};
    IntPair[] fourthPatternLinePlayerOne = {new IntPair(145, 110), new IntPair(110, 110), new IntPair(75, 110), new IntPair(40, 110)};
    IntPair[] fifthPatternLinePlayerOne = {new IntPair(145, 145), new IntPair(110, 145), new IntPair(75, 145), new IntPair(40, 145), new IntPair(05, 145)};

    coordinatePatternLinesPlayerOne.put(1, firstPatternLinePlayerOne);
    coordinatePatternLinesPlayerOne.put(2, secondPatternLinePlayerOne);
    coordinatePatternLinesPlayerOne.put(3, thirdPatternLinePlayerOne);
    coordinatePatternLinesPlayerOne.put(4, fourthPatternLinePlayerOne);
    coordinatePatternLinesPlayerOne.put(5, fifthPatternLinePlayerOne);

    //Wall Player One
    IntPair[] blueWallPlayerOne = {new IntPair(205, 10), new IntPair(240, 45), new IntPair(275, 80), new IntPair(310, 115), new IntPair(345, 150)};
    IntPair[] yellowWallPlayerOne = {new IntPair(240, 10), new IntPair(275, 45), new IntPair(310, 80), new IntPair(345, 115), new IntPair(205, 150)};
    IntPair[] redWallPlayerOne = {new IntPair(275, 10), new IntPair(310, 45), new IntPair(345, 80), new IntPair(205, 115), new IntPair(240, 150)};
    IntPair[] blackWallPlayerOne = {new IntPair(310, 10), new IntPair(345, 45), new IntPair(205, 80), new IntPair(240, 115), new IntPair(275, 150)};
    IntPair[] greenWallPlayerOne = {new IntPair(345, 10), new IntPair(205, 45), new IntPair(240, 80), new IntPair(275, 115), new IntPair(310, 150)};

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
      x+=35;
    }

    //Pattern Lines of Player Three
    IntPair[] firstPatternLinePlayerThree = {new IntPair(145, 305)};
    IntPair[] secondPatternLinePlayerThree = {new IntPair(145, 340), new IntPair(110, 340)};
    IntPair[] thirdPatternLinePlayerThree = {new IntPair(75, 375), new IntPair(110, 375), new IntPair(145, 375)};
    IntPair[] fourthPatternLinePlayerThree = {new IntPair(40, 410), new IntPair(75, 410), new IntPair(110, 410), new IntPair(145, 410)};
    IntPair[] fifthPatternLinePlayerThree = {new IntPair(05, 410), new IntPair(40, 410), new IntPair(75, 410), new IntPair(110, 410), new IntPair(145, 410)};

    coordinatePatternLinesPlayerThree.put(1, firstPatternLinePlayerThree);
    coordinatePatternLinesPlayerThree.put(2, secondPatternLinePlayerThree);
    coordinatePatternLinesPlayerThree.put(3, thirdPatternLinePlayerThree);
    coordinatePatternLinesPlayerThree.put(4, fourthPatternLinePlayerThree);
    coordinatePatternLinesPlayerThree.put(5, fifthPatternLinePlayerThree);

    // Wall Player Three
    IntPair[] blueWallPlayerThree = {new IntPair(205, 307), new IntPair(240, 342), new IntPair(275, 377), new IntPair(310, 412), new IntPair(345, 447)};
    IntPair[] yellowWallPlayerThree = {new IntPair(240, 307), new IntPair(275, 342), new IntPair(310, 377), new IntPair(345, 412), new IntPair(205, 447)};
    IntPair[] redWallPlayerThree = {new IntPair(275, 307), new IntPair(310, 342), new IntPair(345, 377), new IntPair(205, 412), new IntPair(240, 447)};
    IntPair[] blackWallPlayerThree = {new IntPair(310, 307), new IntPair(345, 342), new IntPair(205, 377), new IntPair(240, 412), new IntPair(275, 447)};
    IntPair[] greenWallPlayerThree = {new IntPair(345, 307), new IntPair(205, 342), new IntPair(240, 377), new IntPair(275, 412), new IntPair(310, 447)};

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

  private void drawPlayerOnePlayfield(Graphics g, String nickname) {
    //Zuerst wird das obere Spielfeld gezeichnet, das immer gebraucht wird.
    //username links oben wird geschrieben
    g.setColor(Color.black);
    g.drawString(nickname, 5, 15);


    //Rechtecke der Patternlines werden gezeichnet.
    ((Graphics2D) g).setStroke(new BasicStroke(1));

    for (int count = 1; count <= 5; count++) {
      IntPair[] speicher = coordinatePatternLinesPlayerOne.get(count);
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
      IntPair[] zwischenspeicher = coordinateWallPlayerOne.get(count);
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

    for (int i = 0; i < coordinateMinusPlayerOne.length; i++) {
      g.drawRect(coordinateMinusPlayerOne[i].getX(), coordinateMinusPlayerOne[i].getY(), widthOfMinusCell, heightOfMinusCell);
    }

    //Score:
    g.setColor(scorecolor);
    g.drawString("Punkte:", 5, 260);
    g.drawString("123", 50, 260);
  }


  private void drawPlayerThree(Graphics g, String nickname) {



    //alt
    //super.paintComponent(g);
    //g = (Graphics2D) g;

    //Hier wird das untere Spielfeld gezeichnet, das nur bei 3 Spielern gebraucht wird.
    //username links oben wird geschrieben
    g.setColor(Color.black);
    g.drawString(nickname, 5, 315);

    //Rechtecke der Patternlines werden gezeichnet.

    ((Graphics2D) g).setStroke(new BasicStroke(1));
    g.drawRect(145, 305, widthOfPatternLineCell, heightOfPatternLineCell);

    g.drawRect(110, 340, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(145, 340, widthOfPatternLineCell, heightOfPatternLineCell);

    g.drawRect(75, 375, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(110, 375, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(145, 375, widthOfPatternLineCell, heightOfPatternLineCell);

    g.drawRect(40, 410, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(75, 410, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(110, 410, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(145, 410, widthOfPatternLineCell, heightOfPatternLineCell);

    g.drawRect(05, 445, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(40, 445, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(75, 445, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(110, 445, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(145, 445, widthOfPatternLineCell, heightOfPatternLineCell);


    g.setColor(playerboardcolor);
    g.fillRoundRect(200, 300, 185, 185, 20, 20);

    //Ränder der Rechtecke der Wall werden gezeichnet.
    ((Graphics2D) g).setStroke(new BasicStroke(2));
    g.setColor(Color.blue);
    g.drawRect(205, 307, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(240, 342, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(275, 377, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(310, 412, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(345, 447, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.setColor(Color.yellow);
    g.drawRect(240, 307, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(275, 342, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(310, 377, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(345, 412, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(205, 447, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.setColor(Color.red);
    g.drawRect(275, 307, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(310, 342, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(345, 377, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(205, 412, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(240, 447, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.setColor(Color.black);
    g.drawRect(310, 307, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(345, 342, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(205, 377, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(240, 412, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(275, 447, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.setColor(Color.green);
    g.drawRect(345, 307, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(205, 342, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(240, 377, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(275, 412, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(310, 447, widthOfPlayFieldCell, heightOfPlayFieldCell);

    g.setColor(Color.black);

    //Minuspunkteleiste:
    g.setColor(floorlinecolor);
    g.drawString("-1", 15, 500);
    g.drawRect(05, 505, widthOfMinusCell, heightOfMinusCell);
    g.drawString("-1", 50, 500);
    g.drawRect(40, 505, widthOfMinusCell, heightOfMinusCell);
    g.drawString("-2", 85, 500);
    g.drawRect(75, 505, widthOfMinusCell, heightOfMinusCell);
    g.drawString("-2", 120, 500);
    g.drawRect(110, 505, widthOfMinusCell, heightOfMinusCell);
    g.drawString("-2", 155, 500);
    g.drawRect(145, 505, widthOfMinusCell, heightOfMinusCell);
    g.drawString("-3", 190, 500);
    g.drawRect(180, 505, widthOfMinusCell, heightOfMinusCell);
    g.drawString("-3", 225, 500);
    g.drawRect(215, 505, widthOfMinusCell, heightOfMinusCell);

    //Score:
    g.setColor(scorecolor);
    g.drawString("Punkte:", 5, 560);
    g.drawString("123", 50, 560);
  }

  private void drawPlayerOne(Graphics g) {

  }

  private void updatePlayerOne() {

  }

}
