package de.lmu.ifi.sosylab.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serial;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraphicAzul extends JPanel {

  @Serial
  private static final long serialVersionUID = 1L;

  public JPanel azulPanel = new JPanel();

  public GraphicAzul() {
    init();
  }

  private void init() {
    JLabel label = new JLabel(showImg());
    azulPanel.add(label);
  }

  private ImageIcon showImg() {
    BufferedImage img = null;
    try {
      img = ImageIO.read(getClass().getResourceAsStream("/Azul.PNG"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new ImageIcon(img);
  }

}


