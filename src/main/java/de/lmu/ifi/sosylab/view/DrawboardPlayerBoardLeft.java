package de.lmu.ifi.sosylab.view;

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


  //private ColorTile[][] patternLinesPlayerOne;
  //private ColorTile[][] patternLinesPlayerThree;

  private HashMap<Integer, ArrayList> coordinatePatternLinesPlayerOne;


  public DrawboardPlayerBoardLeft() {
    setPreferredSize(new Dimension(400, 700));
    //initializePlayfieldLeft();
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

  //private void initializePlayfieldLeft() {
    //ArrayList<IntPair> firstPatternLinePlayerOne = new ArrayList<>();
    //firstPatternLinePlayerOne.add(new IntPair(145, 5));
    //coordinatePatternLinesPlayerOne.put(1, firstPatternLinePlayerOne);
  //}

  private void drawPlayerOnePlayfield(Graphics g, String nickname) {
    //super.paintComponent(g);
    //g = (Graphics2D) g;

    //Zuerst wird das obere Spielfeld gezeichnet, das immer gebraucht wird.
    //username links oben wird geschrieben
    g.setColor(Color.black);
    g.drawString(nickname, 5, 15);


    //Rechtecke der Patternlines werden gezeichnet.
    ((Graphics2D) g).setStroke(new BasicStroke(1));
    g.drawRect(145, 5, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(110, 40, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(145, 40, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(75, 75, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(110, 75, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(145, 75, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(40, 110, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(75, 110, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(110, 110, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(145, 110, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(05, 145, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(40, 145, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(75, 145, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(110, 145, widthOfPatternLineCell, heightOfPatternLineCell);
    g.drawRect(145, 145, widthOfPatternLineCell, heightOfPatternLineCell);

    //Hintergrund der Wall wird gezeichnet.
    g.setColor(playerboardcolor);
    g.fillRoundRect(200, 5, 181, 181, 20, 20);

    //Ränder der Rechtecke der Wall werden gezeichnet.
    ((Graphics2D) g).setStroke(new BasicStroke(2));
    g.setColor(Color.blue);
    g.drawRect(205, 10, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(240, 45, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(275, 80, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(310, 115, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(345, 150, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.setColor(Color.yellow);
    g.drawRect(240, 10, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(275, 45, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(310, 80, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(345, 115, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(205, 150, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.setColor(Color.red);
    g.drawRect(275, 10, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(310, 45, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(345, 80, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(205, 115, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(240, 150, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.setColor(Color.black);
    g.drawRect(310, 10, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(345, 45, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(205, 80, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(240, 115, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(275, 150, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.setColor(Color.green);
    g.drawRect(345, 10, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(205, 45, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(240, 80, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(275, 115, widthOfPlayFieldCell, heightOfPlayFieldCell);
    g.drawRect(310, 150, widthOfPlayFieldCell, heightOfPlayFieldCell);

    g.setColor(Color.black);

    //Minuspunkteleiste:
    g.setColor(floorlinecolor);
    g.drawString("-1", 15, 200);
    g.drawRect(05, 205, widthOfMinusCell, heightOfMinusCell);
    g.drawString("-1", 50, 200);
    g.drawRect(40, 205, widthOfMinusCell, heightOfMinusCell);
    g.drawString("-2", 85, 200);
    g.drawRect(75, 205, widthOfMinusCell, heightOfMinusCell);
    g.drawString("-2", 120, 200);
    g.drawRect(110, 205, widthOfMinusCell, heightOfMinusCell);
    g.drawString("-2", 155, 200);
    g.drawRect(145, 205, widthOfMinusCell, heightOfMinusCell);
    g.drawString("-3", 190, 200);
    g.drawRect(180, 205, widthOfMinusCell, heightOfMinusCell);
    g.drawString("-3", 225, 200);
    g.drawRect(215, 205, widthOfMinusCell, heightOfMinusCell);

    //Score:
    g.setColor(scorecolor);
    g.drawString("Punkte:", 5, 260);
    g.drawString("123", 50, 260);
  }


  private void drawPlayerThree(Graphics g, String nickname) {
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
