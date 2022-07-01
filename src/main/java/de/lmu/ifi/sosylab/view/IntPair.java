package main.java.de.lmu.ifi.sosylab.view;

/**
 * Die Klasse speichert zwei Int als Paar, damit die Koordinaten für die Felder übergeben werden.
 */

public class IntPair {
  private int x;
  private int y;


  public IntPair(int x, int y){

    this.x = x;
    this.y = y;
  }

  public int getX(){
    return x;
  }
  public int getY(){
    return y;
  }
}
