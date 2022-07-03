package de.lmu.ifi.sosylab.view;

// import de.lmu.ifi.sosylab.model.*;

import de.lmu.ifi.sosylab.model.ColorTile;
import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.model.PenaltyTile;
import de.lmu.ifi.sosylab.model.Plate;
import de.lmu.ifi.sosylab.model.TableCenter;
import de.lmu.ifi.sosylab.model.Tile;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JPanel;

/**
 * Part of the game - table center.
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
  private TableCenter tableCenter;
  private List<Plate> listFactorys;
  private GameModel model;

  /**
   * Initializes the table center.
   *
   * @param model game model instance
   */
  public DrawboardTableCenter(GameModel model) {
    setPreferredSize(new Dimension(400, 600));
    initialize();
    this.model = model;
    this.tableCenter = model.getTableCenter();
    this.listFactorys = model.getPlates();
  }

  private void initialize() {
    firstFactory = new IntPair[]{new IntPair(17, 17), new IntPair(58, 17),
        new IntPair(17, 58),
        new IntPair(58, 58)};
    secondFactory = new IntPair[]{new IntPair(167, 17), new IntPair(208, 17),
        new IntPair(167, 58),
        new IntPair(208, 58)};
    thirdFactory = new IntPair[]{new IntPair(317, 17), new IntPair(358, 17),
        new IntPair(317, 58),
        new IntPair(358, 58)};
    fourthFactory = new IntPair[]{new IntPair(92, 117), new IntPair(133, 117),
        new IntPair(92, 158),
        new IntPair(133, 158)};
    fifthFactory = new IntPair[]{new IntPair(242, 117), new IntPair(283, 117),
        new IntPair(242, 158), new IntPair(283, 158)};
    sixthFactory = new IntPair[]{new IntPair(92, 242), new IntPair(133, 242),
        new IntPair(92, 283),
        new IntPair(133, 283)};
    seventhFactory = new IntPair[]{new IntPair(242, 242), new IntPair(283, 242),
        new IntPair(242, 283), new IntPair(283, 283)};
    eighthFactory = new IntPair[]{new IntPair(92, 367), new IntPair(133, 367),
        new IntPair(92, 408),
        new IntPair(133, 408)};
    ninthFactory = new IntPair[]{new IntPair(242, 367), new IntPair(283, 367),
        new IntPair(242, 408), new IntPair(283, 408)};

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
        new IntPair(80, 230), new IntPair(230, 230), new IntPair(80, 355),
        new IntPair(230, 355)};

    //Table Center Steine werden hinzugefügt
    positionTilesTableCenter = new ArrayList<>();

    positionTilesTableCenter.add(new IntPair(25, 505));

    for (int i = 505; i <= 625; i += 40) {
      for (int j = 105; j <= 345; j += 40) {
        positionTilesTableCenter.add(new IntPair(j, i));
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

    drawTableCenter(g);

  }

  public void repaintCenterBoard() {
    repaint();
  }

  private void drawFacotry(Graphics g) {
    for (int i = 0; i < positionOfFactory.length; i++) {
      Color backroundColor = new Color(135, 206, 250);
      g.setColor(backroundColor);
      g.fillOval(positionOfFactory[i].getX(), positionOfFactory[i].getY(), widthOfFactory,
          hightOfFactory);
      g.setColor(Color.black);
      ((Graphics2D) g).setStroke(new BasicStroke(2));
      g.drawOval(positionOfFactory[i].getX(), positionOfFactory[i].getY(), widthOfFactory,
          hightOfFactory);
    }
  }

  private void drawTilesFactory(Graphics g) {
    // System.out.print("drawTilesFactory");
    // System.out.print("Plates: ");
    // for (Plate plate : listFactorys) {
    //   System.out.print(plate.getTiles() + ", ");
    // }
    // System.out.println();

    for (int i = 0; i < listFactorys.size(); i++) {
      Plate plate = listFactorys.get(i);
      List<ColorTile> colorTilesFacotry = plate.getTiles();

      IntPair[] cach = mapFactorys.get(i + 1);
      for (int j = 0; j < colorTilesFacotry.size(); j++) {
        de.lmu.ifi.sosylab.model.Color color = colorTilesFacotry.get(j).getColor();

        if (color.equals(de.lmu.ifi.sosylab.model.Color.YELLOW)) {
          g.setColor(Color.yellow);
        }
        if (color.equals(de.lmu.ifi.sosylab.model.Color.BLACK)) {
          g.setColor(Color.black);
        }
        if (color.equals(de.lmu.ifi.sosylab.model.Color.WHITE)) {
          g.setColor(Color.green);
        }
        if (color.equals(de.lmu.ifi.sosylab.model.Color.BLUE)) {
          g.setColor(Color.blue);
        }
        if (color.equals(de.lmu.ifi.sosylab.model.Color.RED)) {
          g.setColor(Color.red);
        }
        g.fillRect(cach[j].getX(), cach[j].getY(), widthOfCell, hightOfCell);
      }

    }


  }

  private void drawTableCenter(Graphics g) {
    List<Tile> tileList = model.getTableCenter().getTiles();
    System.out.println("TableCenter tiles: " + tileList);
    for (int i = 0; i < tileList.size(); i++) {
      if (tileList.get(i) instanceof PenaltyTile) {
        g.setColor(Color.gray);
      } else {
        de.lmu.ifi.sosylab.model.Color colorOfTile = ((ColorTile) tileList.get(i)).getColor();

        if (colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.YELLOW)) {
          g.setColor(Color.yellow);
        }
        if (colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.BLACK)) {
          g.setColor(Color.black);
        }
        if (colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.WHITE)) {
          g.setColor(Color.green);
        }
        if (colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.BLUE)) {
          g.setColor(Color.blue);
        }
        if (colorOfTile.equals(de.lmu.ifi.sosylab.model.Color.RED)) {
          g.setColor(Color.red);
        }

        g.fillRect(positionTilesTableCenter.get(i).getX(), positionTilesTableCenter.get(i).getY(),
            widthOfCell, hightOfCell);
      }
      g.fillRect(positionTilesTableCenter.get(i).getX(), positionTilesTableCenter.get(i).getY(),
          widthOfCell, hightOfCell);
    }
  }

  /**
   * Getter for the color of a tile on a plate addresed by table center related coordinates.
   *
   * @param x x- coordinate
   * @param y y - coordinate
   * @return color of the addressed tile
   */
  public de.lmu.ifi.sosylab.model.Color getColorOfTileOnPlate(int x, int y) {
    de.lmu.ifi.sosylab.model.Color toReturn = de.lmu.ifi.sosylab.model.Color.RED;
    for (int count = 1; count < listFactorys.size(); count++) {
      IntPair[] cach = mapFactorys.get(count);
      Plate plate = listFactorys.get(count - 1);
      List<ColorTile> colorTilesFacotry = plate.getTiles();

      for (int i = 0; i < cach.length; i++) {
        if (x == cach[i].getX() && y == cach[i].getY()) {
          toReturn = colorTilesFacotry.get(i).getColor();
        }
      }
    }
    return toReturn;

  }

  /**
   * Get the count number for a particular plate in table center addressed by table center
   * related coordinates.
   *
   * @param x x-coordinate
   * @param y y-coordinate
   * @return number of the plate
   */
  public int getPlate(int x, int y) {
    int fac = 0;

    for (int i = 1; i < mapFactorys.size(); i++) {
      IntPair[] cach = mapFactorys.get(i);
      for (int j = 0; j < cach.length; j++) {
        if (x == cach[j].getX() && y == cach[j].getY()) {
          fac = i;
        }
      }
    }
    return fac;
  }
}