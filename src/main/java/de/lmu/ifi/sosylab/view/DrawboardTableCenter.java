package main.java.de.lmu.ifi.sosylab.view;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class DrawboardTableCenter extends JPanel {

  private int hightOfCell = 35;
  private int widthOfCell = 35;
  private int hightOfFactory = 100;
  private int widthOfFactory = 100;

  private ArrayList<IntPair> positionBlockTableCenter;

  private IntPair[] positionOfFactory;

  private IntPair[] firstFactory;
  private IntPair[] secondFactory;
  private IntPair[] thirdFactory;
  private IntPair[] fourthFactory;
  private IntPair[] fifthFactory;
  private IntPair[] sixthFactory;
  private IntPair[] seventhFactory;
  private IntPair[] eighthFactory;
  private IntPair[] ninthFactory;


  public DrawboardTableCenter() {
    setPreferredSize(new Dimension(400, 600));
    initialize();
  }

  private void initialize() {


    firstFactory = new IntPair[]{new IntPair(17, 17), new IntPair(58, 17), new IntPair(17, 58), new IntPair(58, 58)};
    secondFactory = new IntPair[]{new IntPair(167, 17), new IntPair(208, 17), new IntPair(167, 58), new IntPair(208, 58)};
    thirdFactory = new IntPair[]{new IntPair(317, 17), new IntPair(358, 17), new IntPair(317, 58), new IntPair(358, 58)};
    fourthFactory = new IntPair[]{new IntPair(92, 117), new IntPair(133, 117), new IntPair(92, 158), new IntPair(133, 158)};
    fifthFactory = new IntPair[]{new IntPair(242, 117), new IntPair(283, 117), new IntPair(242, 158), new IntPair(283, 158)};
    sixthFactory = new IntPair[]{new IntPair(92, 242), new IntPair(133, 242), new IntPair(92, 283), new IntPair(133, 283)};
    seventhFactory = new IntPair[]{new IntPair(242, 242), new IntPair(283, 242), new IntPair(242, 283), new IntPair(283, 283)};
    eighthFactory = new IntPair[]{new IntPair(92, 367), new IntPair(133, 367), new IntPair(92, 408), new IntPair(133, 408)};
    ninthFactory = new IntPair[]{new IntPair(242, 367), new IntPair(283, 367), new IntPair(242, 408), new IntPair(283, 408)};


    positionOfFactory = new IntPair[]{new IntPair(5, 5), new IntPair(155, 5),
        new IntPair(305, 5), new IntPair(80, 105), new IntPair(230, 105),
        new IntPair(80, 230), new IntPair(230, 230), new IntPair(80, 355), new IntPair(230, 355)};

    //Table Center Steine werden hinzugefügt
    positionBlockTableCenter = new ArrayList<>();

    positionBlockTableCenter.add(new IntPair(25, 505));

    for (int i = 505; i <= 625; i += 40) {
      for (int j = 105; j <= 345; j += 40) {
        positionBlockTableCenter.add(new IntPair(j, i));
      }
    }


  }


  @Override

  protected void paintComponent(Graphics g) {

    super.paintComponent(g);

    Color tableColor = new Color(234, 182, 118);
    g.setColor(tableColor);
    g.fillRoundRect(5, 490, 395, 185, 20, 20);

    drawFacotry(g);
    drawTilesFactory(g);

  }

  private void drawFacotry(Graphics g){
    for(int i = 0; i < positionOfFactory.length; i++){
      Color backroundColor = new Color(135, 206, 250);
      g.setColor(backroundColor);
      g.fillOval(positionOfFactory[i].getX(), positionOfFactory[i].getY(), widthOfFactory, hightOfFactory);
      g.setColor(Color.black);
      ((Graphics2D) g).setStroke(new BasicStroke(2));
      g.drawOval(positionOfFactory[i].getX(), positionOfFactory[i].getY(), widthOfFactory, hightOfFactory);
    }
  }

  private void drawTilesFactory(Graphics g){
    //TODO Tiles zeichnen nach Inhalt des Modells
  }
}