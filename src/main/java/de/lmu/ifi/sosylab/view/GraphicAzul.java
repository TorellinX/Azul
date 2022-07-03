package de.lmu.ifi.sosylab.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serial;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Main class to set up the graphic user interface for Azul.
 */
public class GraphicAzul extends JPanel {
  @Serial
  private static final long serialVersionUID = 1L;

  public JPanel azulPanel = new JPanel();

  public GraphicAzul() {
    init();
  }

  private void init() {
    try {
      JLabel label = new JLabel(showImg());
      azulPanel.add(label);
    } catch (NullPointerException e) {
      JLabel label = new JLabel("Error! Splash picture not found.");
      azulPanel.add(label);
    }
  }

  private ImageIcon showImg() {
    // BufferedImage img = null;
    try {
      BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/Azul.PNG"));
      return new ImageIcon(img);
    } catch (IOException e) {
      e.printStackTrace();
    }
    // return new ImageIcon(img);
    return null;
  }

}


