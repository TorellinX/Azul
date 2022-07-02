package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Center Panel of the Game.
 * Shows the panels and the center of the table.
 */

public class DrawboardTableCenter extends JPanel {

  private int hightOfCell = 35;
  private int widthOfCell = 35;
  private int hightOfFactory = 100;
  private int widthOfFactory = 100;

  private ArrayList<IntPair> positionTilesTableCenter;
  private HashMap<Integer, IntPair[]> mapFactorys;

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

  private List<Tile> tileList;
  private TableCenter tableCenter;
  private List<Plate> listFactorys;


  /**
   * Constructor of the class.
   * @param listFactorys List of Factorys
   */

  public DrawboardTableCenter(List<Plate> listFactorys) {
    setPreferredSize(new Dimension(400, 600));
    initialize();
    tableCenter = new TableCenter();
    this.tileList = tableCenter.getTiles();
    this.listFactorys = listFactorys;


  }

  /**
   * Sets the coordinates for the individual objects.
   */

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

    mapFactorys = new HashMap<>();
    mapFactorys.put(1, firstFactory);
    mapFactorys.put(2, secondFactory);
    mapFactorys.put(3, thirdFactory);
    mapFactorys.put(4, fourthFactory);
    mapFactorys.put(5, fifthFactory);
    mapFactorys.put(6, sixthFactory);
    mapFactorys.put(7, seventhFactory);
    mapFactorys.put(8, eighthFactory);
    mapFactorys.put(9, ninthFactory);

    positionOfFactory = new IntPair[]{new IntPair(5, 5), new IntPair(155, 5),
        new IntPair(305, 5), new IntPair(80, 105), new IntPair(230, 105),
        new IntPair(80, 230), new IntPair(230, 230), new IntPair(80, 355), new IntPair(230, 355)};

    //Table Center Steine werden hinzugefügt
    positionTilesTableCenter = new ArrayList<>();

    positionTilesTableCenter.add(new IntPair(25, 505));

    for (int i = 505; i <= 625; i += 40) {
      for (int j = 105; j <= 345; j += 40) {
        positionTilesTableCenter.add(new IntPair(j, i));
      }
    }


  }

  /**
   * Calls the individual methods to draw the playing field.
   * @param g the <code>Graphics</code> object to protect
   */


  @Override

  protected void paintComponent(Graphics g) {

    super.paintComponent(g);

    Color tableColor = new Color(234, 182, 118);
    g.setColor(tableColor);
    g.fillRoundRect(5, 490, 395, 185, 20, 20);

    drawFacotry(g);

    drawTilesFactory(g);
    drawTableCenter(g);

  }

  /**
   * Draws the panels in the center of the table.
   * @param g
   */
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

  /**
   * Draws the tiles on the Facotrys.
   * @param g
   */

  private void drawTilesFactory(Graphics g){

    for(int i = 0; i < listFactorys.size(); i++){
      Plate plate = listFactorys.get(i);
      List<ColorTile> colorTilesFacotry = plate.getTiles();

      IntPair[] cach = mapFactorys.get(i+1);
      for(int j = 0; j < cach.length; j++){
        if(colorTilesFacotry.size() != 0){
          de.lmu.ifi.sosylab.model.Color color = colorTilesFacotry.get(j).getColor();
          if(color.equals(de.lmu.ifi.sosylab.model.Color.YELLOW)){
            g.setColor(Color.yellow);
          }
          if(color.equals(de.lmu.ifi.sosylab.model.Color.BLACK)){
            g.setColor(Color.black);
          }
          if(color.equals(de.lmu.ifi.sosylab.model.Color.WHITE)){
            g.setColor(Color.green);
          }
          if(color.equals(de.lmu.ifi.sosylab.model.Color.BLUE)){
            g.setColor(Color.blue);
          }
          if(color.equals(de.lmu.ifi.sosylab.model.Color.RED)){
            g.setColor(Color.red);
          }
          g.fillRect(cach[j].getX(), cach[j].getY(), widthOfCell, hightOfCell);
        }
      }

    }



  }

  /**
   * Draws the tiles in the center of the table.
   * @param g
   */

  private void drawTableCenter(Graphics g){
    if(tileList.get(0) instanceof PenaltyTile){
      g.setColor(Color.gray);
      g.fillRect(positionTilesTableCenter.get(0).getX(), positionTilesTableCenter.get(0).getY(), widthOfCell, hightOfCell);
      for(int i = 1; i < tileList.size(); i++){
        de.lmu.ifi.sosylab.model.Color colorOfTile = ((ColorTile) tileList.get(i)).getColor();

        if(colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.YELLOW)){
          g.setColor(Color.yellow);
        }
        if(colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.BLACK)){
          g.setColor(Color.black);
        }
        if(colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.WHITE)){
          g.setColor(Color.green);
        }
        if(colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.BLUE)){
          g.setColor(Color.blue);
        }
        if(colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.RED)){
          g.setColor(Color.red);
        }
        g.fillRect(positionTilesTableCenter.get(i).getX(), positionTilesTableCenter.get(i).getX(), widthOfCell, hightOfCell);
        System.out.println("Table Center Drawn");

      }
    }

  }

  /**
   * Reads from a point which color of tiles is there.
   * @param x X-Coordinate
   * @param y Y-Coordinate
   * @return Color of Tile
   */

  public de.lmu.ifi.sosylab.model.Color getColorOfTileOnPlate(int x, int y){
    de.lmu.ifi.sosylab.model.Color toReturn = de.lmu.ifi.sosylab.model.Color.RED;
    for(int count = 1; count < listFactorys.size(); count++){
      IntPair[] cach =  mapFactorys.get(count);
      Plate plate = listFactorys.get(count-1);
      List<ColorTile> colorTilesFacotry = plate.getTiles();

      for (int i = 0; i < cach.length; i++){
        if(x == cach[i].getX() && y == cach[i].getY()) {
          toReturn = colorTilesFacotry.get(i).getColor();
          break;
        }
      }
    }
    return toReturn;

  }

  /**
   * Returns for a coordinate which plate is located there.
   * @param x X-Coordinate
   * @param y Y-Coordinate
   * @return Int of Plate
   */

  public int getPlate(int x, int y){
    int fac = 0;

    for(int i = 1; i < mapFactorys.size(); i++){
      IntPair[] cach =  mapFactorys.get(i);
      for(int j = 0; j < cach.length; j++){
        if(x == cach[j].getX() && y == cach[j].getY()){
          fac = i-1;
        }
      }
    }
    return fac;
  }
}