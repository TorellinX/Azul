package de.lmu.ifi.sosylab.view;

/**
 * Stores two integers as a pair for addressing fields on the table.
 */

public class IntPair {
  private int horizontal;
  private int vertical;

  /**
   * Set new 2D coordinates.
   *
   * @param x x-coordinate
   * @param y y-coordinate
   */
  public IntPair(int x, int y) {

    horizontal = x;
    vertical = y;
  }

  public int getX() {
    return horizontal;
  }

  public int getY() {
    return vertical;
  }
}
