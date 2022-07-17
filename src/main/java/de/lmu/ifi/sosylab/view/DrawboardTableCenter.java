package de.lmu.ifi.sosylab.view;

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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.swing.JPanel;

/**
 * Center Panel of the Game. Shows the panels and the center of the table.
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
  public DrawboardTableCenter(GameModel model, int playerNumber) {
    if (playerNumber > 2) {
      setPreferredSize(new Dimension(410, 900));
    } else {
      setPreferredSize(new Dimension(410, 700));
    }
    initialize();
    List<GameModel> gameModelList = new ArrayList<>();
    gameModelList.add(model);
    List<GameModel> unmodGameModelList = Collections.unmodifiableList(gameModelList);
    this.model = unmodGameModelList.get(0);
    this.tableCenter = model.getTableCenter();
    this.listFactorys = model.getPlates();
  }

  /**
   * Sets the coordinates for the individual objects.
   */
  private void initialize() {
    firstFactory = new IntPair[]{
        new IntPair(17, 17),
        new IntPair(58, 17),
        new IntPair(17, 58),
        new IntPair(58, 58)};
    secondFactory = new IntPair[]{
        new IntPair(167, 17),
        new IntPair(208, 17),
        new IntPair(167, 58),
        new IntPair(208, 58)};
    thirdFactory = new IntPair[]{
        new IntPair(317, 17),
        new IntPair(358, 17),
        new IntPair(317, 58),
        new IntPair(358, 58)};
    fourthFactory = new IntPair[]{
        new IntPair(92, 117),
        new IntPair(133, 117),
        new IntPair(92, 158),
        new IntPair(133, 158)};
    fifthFactory = new IntPair[]{
        new IntPair(242, 117),
        new IntPair(283, 117),
        new IntPair(242, 158),
        new IntPair(283, 158)};
    sixthFactory = new IntPair[]{
        new IntPair(92, 242),
        new IntPair(133, 242),
        new IntPair(92, 283),
        new IntPair(133, 283)};
    seventhFactory = new IntPair[]{
        new IntPair(242, 242),
        new IntPair(283, 242),
        new IntPair(242, 283),
        new IntPair(283, 283)};
    eighthFactory = new IntPair[]{
        new IntPair(92, 367),
        new IntPair(133, 367),
        new IntPair(92, 408),
        new IntPair(133, 408)};
    ninthFactory = new IntPair[]{
        new IntPair(242, 367),
        new IntPair(283, 367),
        new IntPair(242, 408),
        new IntPair(283, 408)};

    mapFactorys = new HashMap<>();
    mapFactorys.put(0, firstFactory);
    mapFactorys.put(1, secondFactory);
    mapFactorys.put(2, thirdFactory);
    mapFactorys.put(3, fourthFactory);
    mapFactorys.put(4, fifthFactory);
    mapFactorys.put(5, sixthFactory);
    mapFactorys.put(6, seventhFactory);
    mapFactorys.put(7, eighthFactory);
    mapFactorys.put(8, ninthFactory);

    positionOfFactory = new IntPair[]{
        new IntPair(5, 5),
        new IntPair(155, 5),
        new IntPair(305, 5),
        new IntPair(80, 105),
        new IntPair(230, 105),
        new IntPair(80, 230),
        new IntPair(230, 230),
        new IntPair(80, 355),
        new IntPair(230, 355)};

    // collect possible positions for stones in game location "table center"
    positionTilesTableCenter = new ArrayList<>();

    positionTilesTableCenter.add(new IntPair(25, 505));

    for (int row = 505; row <= 625; row += 40) {                    // three rows
      for (int col = 105; col <= 345; col += 40) {                  // six columns
        positionTilesTableCenter.add(new IntPair(col, row));
      }
    }


  }

  /**
   * Calls the individual methods to draw the table center components.
   *
   * @param g the <code>Graphics</code> object to protect (internal reference)
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2D = (Graphics2D) g;    // Type g2D required for stroke methods - use unified, not both, g and g2D

    // draw the the game location "table center" as darkend field
    Color tableColor = new Color(234, 182, 118);
    g2D.setColor(tableColor);
    g2D.fillRoundRect(5, 490, 395, 185, 20, 20);

    // updateTable();        // empty method
    // draw the plates
    drawFacotry(g2D);
    // draw the tiles on the factories
    drawTilesFactory(g2D);
    // draw game location "table center"
    drawTableCenter(g2D);

  }

  /**
   * Draws the plate panels in the center of the table.
   *
   * @param g2D graphics object - kind of "internal reference"
   */
  private void drawFacotry(Graphics2D g2D) {
    for (int i = 0; i < positionOfFactory.length; i++) {
      Color backroundColor = new Color(135, 206, 250);
      g2D.setColor(backroundColor);
      g2D.fillOval(positionOfFactory[i].getX(), positionOfFactory[i].getY(), widthOfFactory,
          hightOfFactory);
      g2D.setColor(Color.black);
      g2D.setStroke(new BasicStroke(2));
      g2D.drawOval(positionOfFactory[i].getX(), positionOfFactory[i].getY(), widthOfFactory,
          hightOfFactory);
    }
  }

  /**
   * Draws the tiles on the factories.
   *
   * @param g2D graphics object - kind of "internal reference"
   */
  private void drawTilesFactory(Graphics2D g2D) {
    // System.out.print("drawTilesFactory");
    // System.out.print("Plates: ");
    // for (Plate plate : listFactorys) {
    //   System.out.print(plate.getTiles() + ", ");
    // }
    // System.out.println();

    for (int i = 0; i < listFactorys.size(); i++) {

      Plate plate = listFactorys.get(i);
      List<ColorTile> colorTilesOnFactory = plate.getTiles();
      IntPair[] cach = mapFactorys.get(i);

      for (int j = 0; j < colorTilesOnFactory.size(); j++) {
        de.lmu.ifi.sosylab.model.Color color = colorTilesOnFactory.get(j).getColor();
        switch (color) {
          case YELLOW -> g2D.setColor(Color.yellow);
          case RED -> g2D.setColor(Color.red);
          case BLUE -> g2D.setColor(Color.blue);
          case BLACK -> g2D.setColor(Color.black);
          case GREEN -> g2D.setColor(Color.green);
          default -> throw new IllegalStateException("Unexpected value: " + color);
        }
        g2D.fillRect(cach[j].getX(), cach[j].getY(), widthOfCell, hightOfCell);

      }

    }


  }

  /**
   * Draws the tiles in the center of the table.
   *
   * @param g2D graphics object - kind of "internal reference"
   */
  private void drawTableCenter(Graphics2D g2D) {
    List<Tile> tileList = model.getTableCenter().getTiles();
    //System.out.println("TableCenter tiles: " + tileList);
    for (int i = 0; i < tileList.size(); i++) {
      if (tileList.get(i) instanceof PenaltyTile) {        // penaltiy tile can only be index 0
        g2D.setColor(Color.gray);
      } else {
        de.lmu.ifi.sosylab.model.Color colorOfTile = ((ColorTile) tileList.get(i)).getColor();
        switch (colorOfTile) {
          case YELLOW -> g2D.setColor(Color.yellow);
          case RED -> g2D.setColor(Color.red);
          case BLUE -> g2D.setColor(Color.blue);
          case BLACK -> g2D.setColor(Color.black);
          case GREEN -> g2D.setColor(Color.green);
          default -> throw new IllegalStateException("Unexpected value: " + colorOfTile);
        }
      }
      if (tileList.get(0) instanceof PenaltyTile) {
        g2D.fillRect(positionTilesTableCenter.get(i).getX(), positionTilesTableCenter.get(i).getY(),
            widthOfCell, hightOfCell);
      } else {
        g2D.fillRect(positionTilesTableCenter.get(i + 1).getX(),
            positionTilesTableCenter.get(i + 1).getY(),
            widthOfCell, hightOfCell);
      }

    }

  }

  /**
   * Getter for the color of a tile on a plate addressed by table center related coordinates.
   *
   * @param x x- coordinate
   * @param y y - coordinate
   * @return color of the addressed tile
   */
  public de.lmu.ifi.sosylab.model.Color getColorOfTileOnPlate(int x, int y) {
    de.lmu.ifi.sosylab.model.Color toReturn = de.lmu.ifi.sosylab.model.Color.RED;
    for (int count = 0; count < listFactorys.size(); count++) {
      IntPair[] cache = mapFactorys.get(count);
      Plate plate = listFactorys.get(count);
      List<ColorTile> colorTilesFacotry = plate.getTiles();

      for (int i = 0; i < cache.length; i++) {
        if (x == cache[i].getX() && y == cache[i].getY()) {
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
  public de.lmu.ifi.sosylab.model.Color getColorOfTileTableCenter(int x, int y) {
    de.lmu.ifi.sosylab.model.Color toReturn = de.lmu.ifi.sosylab.model.Color.RED;

    for (int i = 0; i < positionTilesTableCenter.size(); i++) {
      int horizontalCache = positionTilesTableCenter.get(i).getX();
      int verticalCache = positionTilesTableCenter.get(i).getY();
      if (x == horizontalCache && y == verticalCache) {
        toReturn = model.getTableCenter().getColorTiles().get(i - 1).getColor();
      }
    }
    return toReturn;
  }

  /**
   * Returns for a coordinate which plate is located there.
   *
   * @param x X-Coordinate
   * @param y Y-Coordinate
   * @return Int of Plate
   */

  public int getPlate(int x, int y) {
    int fac = 0;

    for (int i = 0; i < mapFactorys.size(); i++) {
      IntPair[] cach = mapFactorys.get(i);
      for (int j = 0; j < cach.length; j++) {
        if (x == cach[j].getX() && y == cach[j].getY()) {
          fac = i;
        }
      }
    }
    return fac;
  }

  private void updateTable() {
    //this.tileListColor = tableCenter.getColorTiles();
  }
}