package de.lmu.ifi.sosylab.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class DrawBackground extends JPanel {


  private BufferedImage backgroundIMG;

  public DrawBackground() {
      }
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;    // Type g2D required for stroke methods - use unified, not both, g and g2D
    drawBackground(g2D);
  }

  public void drawBackground(Graphics2D g2D){
    ClassLoader classLoader = null; //Name dieser klasse
    try {
      classLoader = Class.forName("de.lmu.ifi.sosylab.view.DrawBackground").getClassLoader();
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    var stream = (classLoader.getResourceAsStream("back_classic.png"));
    try {
      backgroundIMG = ImageIO.read(stream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    g2D.drawImage(backgroundIMG,0, 0,410, 900,this);
  }


}