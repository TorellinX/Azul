package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.model.*;
import de.lmu.ifi.sosylab.view.ColorSchemes.ColorScheme;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static de.lmu.ifi.sosylab.model.GameModel.TILES_PER_PLATE;

/**
 * Center Panel of the Game. Shows the panels and the center of the table.
 */
public class DrawboardTableCenter extends JPanel {

  private final int cellSize = 35;
  private final int plateSize = 100;
  private final int tableWidth = 395;
  private final int tableHeight = 185;
  private final int gapSize = 6;
  private final int arcSize = 6;
  private ArrayList<IntPair> positionTilesTableCenter;
  private List<IntPair[]> tilesOnAllPlatesCoordinates;
  private IntPair[] positionOfPlates;
  private final TableCenter tableCenter;
  private final IntPair tableCoordinates = new IntPair(5, 490);
  private final List<Plate> listPlates;
  private final GameModel model;
  private final Controller controller;
  private ArrayList<JButton> buttonsFactory;
  private ArrayList<JButton> buttonsTable;
  private final int buttonsSize = 35;
  private ColorScheme colorScheme;
  private BufferedImage backgroundIMGClassic;
  private BufferedImage backgroundIMGCosmic;
  private PlayingView themesItems;
  private PlayingView themeSelected;


  /**
   * Initializes the table center.
   *
   * @param model game model instance
   */
  public DrawboardTableCenter(GameModel model, Controller controller, int playerNumber) {
    if (playerNumber > 2) {
      setPreferredSize(new Dimension(410, 900));
    } else {
      setPreferredSize(new Dimension(410, 700));
    }
    this.model = model;
    this.controller = controller;
    this.tableCenter = model.getTableCenter();
    this.listPlates = model.getPlates();
    initialize();
  }

  /**
   * Sets the coordinates for the individual objects.
   */
  private void initialize() {
    addPlatesLocations();
    addLocationsTableCenter();
    addButtonsPlayboardCenter();
    addActionListenerFactory();
    addActionListenerTableCenter();


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
    setOpaque(false);

    drawClassicBackground(g2D);
    drawPlates(g2D);
    drawTableCenter(g2D);
    //addThemesActionListener(g2D);
  }

  /**
   * Draws the plate panels with tiles on the table.
   *
   * @param g2D graphics object - kind of "internal reference"
   */
  public void drawClassicBackground(Graphics2D g2D){
    ClassLoader classLoader = null; //Name dieser klasse
    try {
      classLoader = Class.forName("de.lmu.ifi.sosylab.view.DrawBackground").getClassLoader();
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    var stream = (classLoader.getResourceAsStream("back_classic.png"));
    try {
      backgroundIMGClassic = ImageIO.read(stream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    g2D.drawImage(backgroundIMGClassic,0, 0,410, 900,this);
  }

  public void drawCosmicBackground(Graphics2D g2D){
    ClassLoader classLoader2 = null; //Name dieser klasse
    try {
      classLoader2 = Class.forName("de.lmu.ifi.sosylab.view.DrawBackground").getClassLoader();
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    var stream = (classLoader2.getResourceAsStream("back_cosmic.png"));
    try {
      backgroundIMGCosmic = ImageIO.read(stream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    g2D.drawImage(backgroundIMGCosmic,0, 0,410, 900,this);
  }

  public void drawBeachBackground(Graphics2D g2D){
    ClassLoader classLoader = null; //Name dieser klasse
    try {
      classLoader = Class.forName("de.lmu.ifi.sosylab.view.DrawBackground").getClassLoader();
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    var stream = (classLoader.getResourceAsStream("back_beach.png"));
    try {
      backgroundIMGClassic = ImageIO.read(stream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    g2D.drawImage(backgroundIMGClassic,0, 0,410, 900,this);
  }


  private void drawPlates(Graphics2D g2D) {
    if (model.getState() == State.FINISHED) {
      return;
    }
    int numberOfPlates = listPlates.size();
    for (int i = 0; i < numberOfPlates; i++) {
      g2D.setColor(colorScheme.plateFill());
      g2D.fillOval(positionOfPlates[i].getX(), positionOfPlates[i].getY(), plateSize,
          plateSize);
      g2D.setColor(colorScheme.plateBorder());
      g2D.setStroke(new BasicStroke(2));
      g2D.drawOval(positionOfPlates[i].getX(), positionOfPlates[i].getY(), plateSize,
          plateSize);
    }
    drawTilesOnPlates(g2D);
  }

  /**
   * Draws the tiles on the factories.
   *
   * @param g2D graphics object - kind of "internal reference"
   */
  private void drawTilesOnPlates(Graphics2D g2D) {
    for (int i = 0; i < listPlates.size(); i++) {
      List<ColorTile> colorTilesOnCurrentPlate = listPlates.get(i).getTiles();
      IntPair[] tilesCoordinates = tilesOnAllPlatesCoordinates.get(i);

      for (int j = 0; j < colorTilesOnCurrentPlate.size(); j++) {
        de.lmu.ifi.sosylab.model.Color color = colorTilesOnCurrentPlate.get(j).getColor();
        switch (color) {
          case YELLOW -> g2D.setColor(colorScheme.yellow());
          case RED -> g2D.setColor(colorScheme.red());
          case BLUE -> g2D.setColor(colorScheme.blue());
          case BLACK -> g2D.setColor(colorScheme.black());
          case WHITE -> g2D.setColor(colorScheme.green());
          default -> throw new IllegalStateException("Unexpected value: " + color);
        }
        g2D.fillRoundRect(tilesCoordinates[j].getX(), tilesCoordinates[j].getY(), cellSize,
            cellSize, arcSize, arcSize);
      }
    }
  }

  /**
   * Draws the location "table center" with tiles.
   *
   * @param g2D graphics object
   */
  private void drawTableCenter(Graphics2D g2D) {
    if (model.getState() == State.FINISHED) {
      return;
    }
    g2D.setColor(colorScheme.table());
    g2D.fillRoundRect(tableCoordinates.getX(), tableCoordinates.getY(), tableWidth, tableHeight, 20,
        20);
    drawTableCenterTiles(g2D);
  }

  /**
   * Draws the tiles in the center of the table.
   *
   * @param g2D graphics object - kind of "internal reference"
   */
  private void drawTableCenterTiles(Graphics2D g2D) {
    List<Tile> tableCenterTiles = model.getTableCenter().getTiles();
    if (model.getState() == State.FINISHED) {
      return;
    }
    for (int i = 0; i < tableCenterTiles.size(); i++) {
      if (tableCenterTiles.get(i) instanceof PenaltyTile) {
        drawPenaltyTileOnTableCenter(g2D, i);
      } else {
        drawColorTileOnTableCenter(g2D, tableCenterTiles.get(i), i);
      }
    }
  }

  /**
   * Draws a penalty tile on the table center.
   *
   * @param g2D graphics object
   * @param i   index of the tile
   */
  private void drawPenaltyTileOnTableCenter(Graphics2D g2D, int i) {
    g2D.setColor(colorScheme.penalty());
    g2D.fillRoundRect(positionTilesTableCenter.get(i).getX(),
        positionTilesTableCenter.get(i).getY(),
        cellSize, cellSize, arcSize, arcSize);
    g2D.setColor(colorScheme.penaltyText());
    g2D.setFont(this.getFont().deriveFont(Font.BOLD, 18));
    g2D.drawString("1", positionTilesTableCenter.get(i).getX() + cellSize / 3,
        positionTilesTableCenter.get(0).getY() + 2 * cellSize / 3);
  }

  /**
   * Draws a color tile on the table center.
   *
   * @param g2D  graphics object
   * @param tile color tile
   * @param i    index of the tile
   */
  private void drawColorTileOnTableCenter(Graphics2D g2D, Tile tile, int i) {
    de.lmu.ifi.sosylab.model.Color colorOfTile = ((ColorTile) tile).getColor();
    switch (colorOfTile) {
      case YELLOW -> g2D.setColor(colorScheme.yellow());
      case RED -> g2D.setColor(colorScheme.red());
      case BLUE -> g2D.setColor(colorScheme.blue());
      case BLACK -> g2D.setColor(colorScheme.black());
      case WHITE -> g2D.setColor(colorScheme.green());
      default -> throw new IllegalStateException("Unexpected value: " + colorOfTile);
    }
    g2D.fillRoundRect(positionTilesTableCenter.get(i).getX(),
        positionTilesTableCenter.get(i).getY(),
        cellSize, cellSize, arcSize, arcSize);
  }

  /**
   * Getter for the color of a tile on a plate addressed by table center related coordinates.
   *
   * @param x x- coordinate
   * @param y y - coordinate
   * @return color of the addressed tile
   */
  public de.lmu.ifi.sosylab.model.Color getColorOfTileOnPlate(int x, int y) {
    de.lmu.ifi.sosylab.model.Color colorOfTile = de.lmu.ifi.sosylab.model.Color.RED;
    for (int count = 0; count < listPlates.size(); count++) {
      IntPair[] tilesCoordinates = tilesOnAllPlatesCoordinates.get(count);
      Plate plate = listPlates.get(count);
      List<ColorTile> colorTilesPlate = plate.getTiles();

      for (int i = 0; i < tilesCoordinates.length; i++) {
        if (x == tilesCoordinates[i].getX() && y == tilesCoordinates[i].getY()) {
          if (colorTilesPlate.size() == 0) {
            continue;
          }
          colorOfTile = colorTilesPlate.get(i).getColor();
        }
      }
    }
    return colorOfTile;
  }

  /**
   * Get the count number for a particular plate in table center addressed by table center related
   * coordinates.
   *
   * @param x x-coordinate
   * @param y y-coordinate
   * @return number of the plate
   */
  public de.lmu.ifi.sosylab.model.Color getColorOfTileTableCenter(int x, int y) {
    if (model.getTableCenter().getColorTiles().size() == 0) {
      return null;
    }
    de.lmu.ifi.sosylab.model.Color tileColor = null;

    for (int i = 0; i < positionTilesTableCenter.size(); i++) {
      int horizontalCache = positionTilesTableCenter.get(i).getX();
      int verticalCache = positionTilesTableCenter.get(i).getY();
      if (x == horizontalCache && y == verticalCache) {
        if (model.getTableCenter().getTiles().get(i) instanceof PenaltyTile) {
          return null; // try to pick the penalty tile
        }
        int colorTileIndex = i;
        if (model.getTableCenter().getTiles().get(0) instanceof PenaltyTile) {
          colorTileIndex--; // minus penalty tile
        }
        tileColor = model.getTableCenter().getColorTiles().get(colorTileIndex).getColor();
      }
    }
    return tileColor;
  }

  /**
   * Returns for a coordinate which plate is located there.
   *
   * @param x X-Coordinate
   * @param y Y-Coordinate
   * @return Int of Plate
   */
  public int getPlateIndex(int x, int y) {
    int plateIndex = 0;
    for (IntPair[] tilesCoordinates : tilesOnAllPlatesCoordinates) {
      for (IntPair tilesCoordinate : tilesCoordinates) {
        if (x == tilesCoordinate.getX() && y == tilesCoordinate.getY()) {
          plateIndex = tilesOnAllPlatesCoordinates.indexOf(tilesCoordinates);
        }
      }
    }
    return plateIndex;
  }

  /**
   * Calculates plates-specific coordinates.
   */
  private void addPlatesLocations() {
    int platesCenterX = 167;
    int platesCenterY = 217;
    int numberOfPlates = listPlates.size();
    IntPair[][] currentGrid;

    if (numberOfPlates < 9) {
      currentGrid = calculateDiagonalGrid(platesCenterX, platesCenterY);
    } else {
      currentGrid = calculateStraightGrid(platesCenterX, platesCenterY);
    }

    List<IntPair> firstTileOnPlatesCoordinates = calculateFirstTileOnPlates(currentGrid);
    tilesOnAllPlatesCoordinates = calculateTilesOnAllPlates(firstTileOnPlatesCoordinates);

    positionOfPlates = calculatePositionsOfPlates(firstTileOnPlatesCoordinates);
  }

  /**
   * Adds game location "table center".
   */
  private void addLocationsTableCenter() {
    // collect possible positions for stones in game location "table center"
    positionTilesTableCenter = new ArrayList<>();
    int tableTilesX = tableCoordinates.getX() + 20;
    int tableTilesY = tableCoordinates.getY() + 15;
    int tileAreaHeight = tableHeight - 65;
    int tileAreaWidth = tableWidth - 75;
    int gap = 5;
    for (int row = tableTilesY; row <= tableTilesY + tileAreaHeight; row += cellSize + gap) {
      for (int col = tableTilesX; col <= tableTilesX + tileAreaWidth; col += cellSize + gap) {
        positionTilesTableCenter.add(new IntPair(col, row));
      }
    }
  }

  /**
   * Calculates straight grid for plates with left upper tile coordinates.
   *
   * @param platesCenterX X-coordinate of the center of the plates grid
   * @param platesCenterY Y-coordinate of the center of the plates grid
   * @return straight grid for plates with left upper tile coordinates
   */
  private IntPair[][] calculateStraightGrid(int platesCenterX, int platesCenterY) {
    int gap = 20;
    int plateOffset = plateSize + gap;
    IntPair[][] straightGrid = new IntPair[3][3];
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        straightGrid[row][col] = new IntPair(platesCenterX - plateOffset + row * plateOffset,
            platesCenterY - plateOffset + col * plateOffset);
      }
    }
    return straightGrid;
  }

  /**
   * Calculates diagonal grid for plates with left upper tile coordinates.
   *
   * @param platesCenterX X-coordinate of the center of the plates grid
   * @param platesCenterY Y-coordinate of the center of the plates grid
   * @return diagonal grid for plates with left upper tile coordinates
   */
  private IntPair[][] calculateDiagonalGrid(int platesCenterX, int platesCenterY) {
    int gap = 15;
    int plateOffset = plateSize + gap;
    IntPair[][] diagonalGrid = new IntPair[3][3];

    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        diagonalGrid[row][col] = new IntPair(
            (int) Math.round(
                platesCenterX - plateOffset * Math.sqrt(2) + col * plateOffset / Math.sqrt(2) +
                    row * plateOffset / Math.sqrt(2)),
            (int) Math.round(
                platesCenterY - col * plateOffset / Math.sqrt(2) +
                    row * plateOffset / Math.sqrt(2)));
      }
    }
    return diagonalGrid;
  }

  /**
   * Gives coordinates of the left upper tile for every plate in the specified grid, taking into
   * account the order of filling the plates.
   *
   * @param grid specified grid
   * @return positions of the plates in diagonal grid
   */
  private List<IntPair> calculateFirstTileOnPlates(IntPair[][] grid) {
    List<IntPair> firstTileOnPlatesCoordinates = new ArrayList<>();
    firstTileOnPlatesCoordinates.add(grid[0][1]);
    firstTileOnPlatesCoordinates.add(grid[1][0]);
    firstTileOnPlatesCoordinates.add(grid[1][1]);
    firstTileOnPlatesCoordinates.add(grid[1][2]);
    firstTileOnPlatesCoordinates.add(grid[2][1]);
    firstTileOnPlatesCoordinates.add(grid[0][2]);
    firstTileOnPlatesCoordinates.add(grid[2][0]);
    firstTileOnPlatesCoordinates.add(grid[0][0]);
    firstTileOnPlatesCoordinates.add(grid[2][2]);
    return firstTileOnPlatesCoordinates;
  }

  /**
   * Calculates coordinates of all tiles for every plate.
   *
   * @param firstTileOnPlatesCoordinates coordinates of the left upper tile for every plate
   * @return coordinates of all tiles for every plate
   */
  private List<IntPair[]> calculateTilesOnAllPlates(List<IntPair> firstTileOnPlatesCoordinates) {
    tilesOnAllPlatesCoordinates = new ArrayList<>();
    for (IntPair plate : firstTileOnPlatesCoordinates) {
      tilesOnAllPlatesCoordinates.add(calculateTilesForOnePlate(plate.getX(), plate.getY()));
    }
    return tilesOnAllPlatesCoordinates;
  }

  /**
   * Calculates coordinates of tiles for one plate.
   *
   * @param plateX x-coordinate of left upper tile on plate
   * @param plateY y-coordinate of left upper tile on plate
   * @return coordinates of tiles for one plate
   */
  private IntPair[] calculateTilesForOnePlate(int plateX, int plateY) {
    List<IntPair> tilesForOnePlateCoordinates = new ArrayList<>();
    for (int row = 0; row < TILES_PER_PLATE / 2; row++) {
      for (int col = 0; col < TILES_PER_PLATE / 2; col++) {
        tilesForOnePlateCoordinates.add(new IntPair(plateX + col * (cellSize + gapSize),
            plateY + row * (cellSize + gapSize)));
      }
    }
    return tilesForOnePlateCoordinates.toArray(IntPair[]::new);
  }

  /**
   * Calculates coordinates of plates using coordinates of the left upper tile for every plate.
   *
   * @param firstTileOnPlatesCoordinates coordinates of the left upper tile for every plate
   * @return coordinates of plates
   */
  private IntPair[] calculatePositionsOfPlates(List<IntPair> firstTileOnPlatesCoordinates) {
    int offsetPlatesToTiles = 12;
    IntPair[] positionsOfPlates = new IntPair[firstTileOnPlatesCoordinates.size()];
    for (int i = 0; i < firstTileOnPlatesCoordinates.size(); i++) {
      positionsOfPlates[i] = new IntPair(
          firstTileOnPlatesCoordinates.get(i).getX() - offsetPlatesToTiles,
          firstTileOnPlatesCoordinates.get(i).getY() - offsetPlatesToTiles);
    }
    return positionsOfPlates;
  }

  /**
   * Adds the buttons for the Playbord.
   */
  private void addButtonsPlayboardCenter() {
    buttonsFactory = new ArrayList<>();
    for (IntPair[] tilesOnPlateCoordinate : tilesOnAllPlatesCoordinates) {
      for (int j = 0; j < TILES_PER_PLATE; j++) {
        JButton currentButton = new JButton();
        currentButton.setBounds(tilesOnPlateCoordinate[j].getX(),
            tilesOnPlateCoordinate[j].getY(), buttonsSize, buttonsSize);
        currentButton.setOpaque(false);
        currentButton.setContentAreaFilled(false);
        currentButton.setBorderPainted(false);
        buttonsFactory.add(currentButton);
      }
    }
    for (JButton button : buttonsFactory) {
      add(button);
    }

    buttonsTable = new ArrayList<>();
    for (int count = 0; count < positionTilesTableCenter.size(); count++) {
      buttonsTable.add(new JButton());
      buttonsTable.get(count)
          .setBounds(positionTilesTableCenter.get(count).getX(),
              positionTilesTableCenter.get(count).getY(),
              buttonsSize, buttonsSize);
      buttonsTable.get(count).setOpaque(false);
      buttonsTable.get(count).setContentAreaFilled(false);
      buttonsTable.get(count).setBorderPainted(false);
    }

    for (JButton jButton : buttonsTable) {
      add(jButton);
    }
  }


  /**
   * Adds ActionListeners for Factory buttons.
   */
  private void addActionListenerFactory() {

    for (int i = 0; i < buttonsFactory.size(); i++) {
      final int final_i = i;
      buttonsFactory.get(i).addActionListener(e -> {
        System.out.println(
            buttonsFactory.indexOf(buttonsFactory.get(final_i)) + " " + buttonsFactory.get(
                final_i).getX() + " " + buttonsFactory.get(final_i).getY());

        de.lmu.ifi.sosylab.model.Color color = getColorOfTileOnPlate(
            buttonsFactory.get(final_i).getX(), buttonsFactory.get(final_i).getY());
        /*if (color == null) {
          return;
        }*/
        if (controller.pickTilesFromPlate(color,
            model.getPlayers().get(model.getPlayerToMoveIndex()),
            model.getPlates().get(
                getPlateIndex(buttonsFactory.get(final_i).getX(),
                    buttonsFactory.get(final_i).getY())))) {

          System.out.println("y");
        } else {
          System.out.println("N");
        }
      });
    }
  }

  /**
   * Adds ActionListeners for Table Center buttons.
   */
  private void addActionListenerTableCenter() {
    for (int i = 0; i < buttonsTable.size(); i++) {
      final int final_i = i;
      buttonsTable.get(i).addActionListener(e -> {
        System.out.println("Button index: " +
            buttonsTable.indexOf(buttonsTable.get(final_i)) + " , X: " + buttonsTable.get(final_i)
            .getX() + " , Y: " + buttonsTable.get(final_i).getY());
        de.lmu.ifi.sosylab.model.Color color = getColorOfTileTableCenter(
            buttonsTable.get(final_i).getX(), buttonsTable.get(final_i).getY());
        if (color == null) {  // tried to pick the penalty tile
          return;
        }
        controller.pickTilesFromTableCenter(color,
            model.getPlayers().get(model.getPlayerToMoveIndex()));
      });
    }
  }

  /**
   * Setter for color scheme of the table center.
   *
   * @param colorScheme current color scheme
   */
  public void setColorScheme(ColorScheme colorScheme) {
    this.colorScheme = colorScheme;
  }

  public PlayingView getThemesItems() {
    return themesItems;
  }

  public PlayingView getThemeSelected(){
    return themeSelected;
  }

  public void addThemesActionListener(Graphics2D g2D){
    getThemesItems().addActionlistener(e -> {
      String themeSelected = String.valueOf(getThemeSelected());
      if (themeSelected == "Cosmic"){
        drawCosmicBackground(g2D);
        drawPlates(g2D);
        drawTableCenter(g2D);
        repaint();
      }
      if (themeSelected == "Beach"){
        drawBeachBackground(g2D);
        drawPlates(g2D);
        drawTableCenter(g2D);
        repaint();
      }
    });
  }

}