package de.lmu.ifi.sosylab.view;

import javax.swing.*;
import java.awt.*;

/**
 * Depicts the initial splash screen with picture for Azul.
 */
public class GraphicTable<graphicTablePanel> extends JPanel {

  private Image image;

  public GraphicTable() {
    super();
  }

  public GraphicTable(boolean isDoubleBuffered) {
    super(isDoubleBuffered);
  }

  public GraphicTable(LayoutManager layout) {
    super(layout);
  }

  public GraphicTable(LayoutManager layout, boolean isDoubleBuffered) {
    super(layout, isDoubleBuffered);
  }

  public void setBackgroundImage(Image image) {
    this.image = image;
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (image != null) {
      g.drawImage(image, 0, 0, this);
    }


    //Erzeugen des Bildes
    Image img = Toolkit.getDefaultToolkit().getImage(getClass()
            .getResource("/BackgroundTableCenter.jpg"));

    //PicturePanel generieren, Hintergrundbild festlegen und in ein anderes Panel eindocken

    GraphicTable graphicTable = new GraphicTable(new BorderLayout());
    graphicTable.setBackgroundImage(img);
  }
  }






