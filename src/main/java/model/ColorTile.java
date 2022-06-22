package model;

public class ColorTile extends Tile{
  private final Color color;

  public ColorTile(Color color){
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

  @Override
  public String toString(){
    return color.toString();
  }
}