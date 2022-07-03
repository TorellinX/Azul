package de.lmu.ifi.sosylab.view;

/**
 * The class stores two Int as a pair so that the coordinates for the fields are passed.
 */

public class IntPair {

  private int x;
  private int y;

  /**
   * Constructor of Class
   *
   * @param x
   * @param y
   */
  public IntPair(int x, int y) {

    this.x = x;
    this.y = y;
  }

  /**
   * Get X.
   *
   * @return X-Coordinate
   */
  public int getX() {
    return x;
  }

  /**
   * Get Y.
   *
   * @return Y-Coordinate
   */
  public int getY() {
    return y;
  }
}
