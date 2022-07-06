package de.lmu.ifi.sosylab.view;

/**
 * The class stores two Int as a pair so that the coordinates for the fields are passed.
 */

public class IntPair {

  private int horizontal;
  private int vertical;

  /**
   * Constructor of Class.
   *
   * @param x horizontal coordinate
   * @param y vertical coordinate
   */
  public IntPair(int x, int y) {

    horizontal = x;
    vertical = y;
  }

  /**
   * Get X.
   *
   * @return X-Coordinate
   */
  public int getX() {
    return horizontal;
  }

  /**
   * Get Y.
   *
   * @return Y-Coordinate
   */
  public int getY() {
    return vertical;
  }
}
