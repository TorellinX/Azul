package de.lmu.ifi.sosylab.view;

import javax.swing.*;
import java.awt.*;

/**
 * Depicts the initial splash screen with picture for Azul.
 */
public class GraphicTablePanel<graphicTablePanel> extends JPanel {

  private Image image;

  public GraphicTablePanel() {
    super();
  }

  public GraphicTablePanel(boolean isDoubleBuffered) {
    super(isDoubleBuffered);
  }

  public GraphicTablePanel(LayoutManager layout) {
    super(layout);
  }

  public GraphicTablePanel(LayoutManager layout, boolean isDoubleBuffered) {
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
            .getResource("/BackroundTableCenter.jpg"));

    //PicturePanel generieren, Hintergrundbild festlegen und in ein anderes Panel eindocken

    GraphicTablePanel graphicTablePanel = new GraphicTablePanel(new BorderLayout());
    graphicTablePanel.setBackgroundImage(img);
  }
  }






