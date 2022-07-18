package de.lmu.ifi.sosylab.view;

import static de.lmu.ifi.sosylab.model.GameModel.TILES_PER_PLATE;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.model.ColorTile;
import de.lmu.ifi.sosylab.model.GameModel;
import de.lmu.ifi.sosylab.model.PenaltyTile;
import de.lmu.ifi.sosylab.model.Plate;
import de.lmu.ifi.sosylab.model.TableCenter;
import de.lmu.ifi.sosylab.model.Tile;
import de.lmu.ifi.sosylab.view.ColorSchemes.ColorScheme;
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * Center Panel of the Game. Shows the panels and the center of the table.
 */
public class DrawboardTableCenter extends JPanel {

  private final int cellSize = 35;
  private final int plateSize = 100;
  private final int gapSize = 6;
  private final int arcSize = 6;
  private ArrayList<IntPair> positionTilesTableCenter;
  private List<IntPair[]> tilesOnPlateCoordinates;
  private IntPair[] positionOfPlates;
  private final TableCenter tableCenter;
  private final List<Plate> listPlates;
  private final GameModel model;
  private final Controller controller;
  private ArrayList<JButton> buttonsFactory;
  private ArrayList<JButton> buttonsTable;
  private final int buttonsSize = 35;
  private ColorScheme colorScheme;


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
    initialize();
    this.model = model;
    this.controller = controller;
    this.tableCenter = model.getTableCenter();
    this.listPlates = model.getPlates();
  }

  /**
   * Sets the coordinates for the individual objects.
   */
  private void initialize() {

    addLocationsPlayboardCenter();
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
    // draw the game location "table center" as darkened field
    g2D.setColor(colorScheme.table());
    g2D.fillRoundRect(5, 490, 395, 185, 20, 20);

    // draw the plates
    drawPlates(g2D);
    // draw the tiles on the factories
    drawTilesOnPlates(g2D);
    // draw game location "table center"
    drawTableCenter(g2D);

  }

  /**
   * Draws the plate panels in the center of the table.
   *
   * @param g2D graphics object - kind of "internal reference"
   */
  private void drawPlates(Graphics2D g2D) {
    for (IntPair positionOfPlate : positionOfPlates) {
      g2D.setColor(colorScheme.plateFill());
      g2D.fillOval(positionOfPlate.getX(), positionOfPlate.getY(), plateSize,
          plateSize);
      g2D.setColor(colorScheme.plateBorder());
      g2D.setStroke(new BasicStroke(2));
      g2D.drawOval(positionOfPlate.getX(), positionOfPlate.getY(), plateSize,
          plateSize);
    }
  }

  /**
   * Draws the tiles on the factories.
   *
   * @param g2D graphics object - kind of "internal reference"
   */
  private void drawTilesOnPlates(Graphics2D g2D) {
    for (int i = 0; i < listPlates.size(); i++) {
      List<ColorTile> colorTilesOnCurrentPlate = listPlates.get(i).getTiles();
      IntPair[] tilesCoordinates = tilesOnPlateCoordinates.get(i);

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
   * Draws the tiles in the center of the table.
   *
   * @param g2D graphics object - kind of "internal reference"
   */
  private void drawTableCenter(Graphics2D g2D) {
    List<Tile> tileList = model.getTableCenter().getTiles();
    for (int i = 0; i < tileList.size(); i++) {
      if (tileList.get(i) instanceof PenaltyTile) {        // penaltiy tile can only be index 0
        g2D.setColor(colorScheme.penalty());
      } else {
        de.lmu.ifi.sosylab.model.Color colorOfTile = ((ColorTile) tileList.get(i)).getColor();
        switch (colorOfTile) {
          case YELLOW -> g2D.setColor(colorScheme.yellow());
          case RED -> g2D.setColor(colorScheme.red());
          case BLUE -> g2D.setColor(colorScheme.blue());
          case BLACK -> g2D.setColor(colorScheme.black());
          case WHITE -> g2D.setColor(colorScheme.green());
          default -> throw new IllegalStateException("Unexpected value: " + colorOfTile);
        }
      }
      if (tileList.get(0) instanceof PenaltyTile) {
        g2D.fillRoundRect(positionTilesTableCenter.get(i).getX(),
            positionTilesTableCenter.get(i).getY(),
            cellSize, cellSize, arcSize, arcSize);
        g2D.setColor(colorScheme.penaltyText());
        g2D.setFont(this.getFont().deriveFont(Font.BOLD, 18));
        g2D.drawString("1", positionTilesTableCenter.get(0).getX() + cellSize / 3,
            positionTilesTableCenter.get(0).getY() + 2 * cellSize / 3);
      } else {
        g2D.fillRoundRect(positionTilesTableCenter.get(i + 1).getX(),
            positionTilesTableCenter.get(i + 1).getY(),
            cellSize, cellSize, arcSize, arcSize);
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
    de.lmu.ifi.sosylab.model.Color colorOfTile = de.lmu.ifi.sosylab.model.Color.RED;
    for (int count = 0; count < listPlates.size(); count++) {
      IntPair[] tilesCoordinates = tilesOnPlateCoordinates.get(count);
      Plate plate = listPlates.get(count);
      List<ColorTile> colorTilesPlate = plate.getTiles();

      for (int i = 0; i < tilesCoordinates.length; i++) {
        if (x == tilesCoordinates[i].getX() && y == tilesCoordinates[i].getY()) {
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
  public int getPlateIndex(int x, int y) {
    int plateIndex = 0;
    for (IntPair[] tilesCoordinates : tilesOnPlateCoordinates) {
      for (IntPair tilesCoordinate : tilesCoordinates) {
        if (x == tilesCoordinate.getX() && y == tilesCoordinate.getY()) {
          plateIndex = tilesOnPlateCoordinates.indexOf(tilesCoordinates);
        }
      }
    }
    return plateIndex;
  }

  /**
   * Calculates coordinates of tiles for one plate.
   * @param plateX x-coordinate of left upper tile on plate
   * @param plateY y-coordinate of left upper tile on plate
   * @return coordinates of tiles for one plate
   */
  private IntPair[] calculateTilesOnPlateCoordinates(int plateX, int plateY) {
    List<IntPair> tilesOnPlateCoordinates = new ArrayList<>();
    for (int row = 0; row < TILES_PER_PLATE / 2; row++) {
      for (int col = 0; col < TILES_PER_PLATE / 2; col++) {
        tilesOnPlateCoordinates.add(new IntPair(plateX + col * (cellSize + gapSize),
            plateY + row * (cellSize + gapSize)));
      }
    }
    return tilesOnPlateCoordinates.toArray(IntPair[]::new);
  }

  /**
   * Adds plates and game location "table center"
   */
  private void addLocationsPlayboardCenter() {
    List<IntPair> coordinatesOfFirstTilesOnPlates = new ArrayList<>();
    int platesCenterX = 167;
    int platesCenterY = 217;
    int plateOffset = plateSize + 25;
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        coordinatesOfFirstTilesOnPlates.add(new IntPair(platesCenterX - plateOffset + row * plateOffset,
            platesCenterY - plateOffset + col * plateOffset));
      }
    }

    tilesOnPlateCoordinates = new ArrayList<>();
    for (IntPair plate : coordinatesOfFirstTilesOnPlates) {
      tilesOnPlateCoordinates.add(calculateTilesOnPlateCoordinates(plate.getX(), plate.getY()));
    }

    int offsetPlatesToTiles = 12;
    positionOfPlates = new IntPair[coordinatesOfFirstTilesOnPlates.size()];
    for (int i = 0; i < coordinatesOfFirstTilesOnPlates.size(); i++) {
      positionOfPlates[i] = new IntPair(
          coordinatesOfFirstTilesOnPlates.get(i).getX() - offsetPlatesToTiles,
          coordinatesOfFirstTilesOnPlates.get(i).getY() - offsetPlatesToTiles);
    }

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
   * Adds the buttons for the Playbord.
   */
  private void addButtonsPlayboardCenter() {
    buttonsFactory = new ArrayList<>();
    for (IntPair[] tilesOnPlateCoordinate : tilesOnPlateCoordinates) {
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

    ArrayList<IntPair> positionButtonTable = new ArrayList<>();
    positionButtonTable.add(new IntPair(25, 505));
    for (int i = 505; i <= 625; i += 40) {
      for (int j = 105; j <= 345; j += 40) {
        positionButtonTable.add(new IntPair(j, i));
      }
    }

    buttonsTable = new ArrayList<>();
    for (int count = 0; count < positionButtonTable.size(); count++) {
      buttonsTable.add(new JButton());
      buttonsTable.get(count)
          .setBounds(positionButtonTable.get(count).getX(), positionButtonTable.get(count).getY(),
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
        System.out.println(
            buttonsTable.indexOf(buttonsTable.get(final_i)) + " " + buttonsTable.get(final_i)
                .getX() + " " + buttonsTable.get(final_i).getY());

        de.lmu.ifi.sosylab.model.Color color = getColorOfTileTableCenter(
            buttonsTable.get(final_i).getX(), buttonsTable.get(final_i).getY());

        controller.pickTilesFromTableCenter(color,
            model.getPlayers().get(model.getPlayerToMoveIndex()));
      });
    }
  }

  /**
   * Setter for color scheme of the table center.
   * @param colorScheme current color scheme
   */
  public void setColorScheme(ColorScheme colorScheme) {
    this.colorScheme = colorScheme;
  }

}