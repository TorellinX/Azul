package de.lmu.ifi.sosylab.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serial;
import java.util.Objects;

/**
 * Depicts the initial splash screen with picture for Azul.
 */
public class GraphicTablealt extends JPanel {

  @Serial
  private static final long serialVersionUID = 1L;

  public JPanel backroundTablePanel = new JPanel();

  public GraphicTablealt() {
    init();
  }

  private void init() {
    try {
      JLabel label = new JLabel(showImg());
      backroundTablePanel.add(label);
    } catch (NullPointerException e) {
      JLabel label = new JLabel("Error! Splash picture not found.");
      backroundTablePanel.add(label);
    }
  }

  private ImageIcon showImg() {
    // BufferedImage img = null;
    try {
      BufferedImage img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/BackroundTableCenter.jpg")));
      return new ImageIcon(img);
    } catch (IOException e) {
      e.printStackTrace();
    }
    // return new ImageIcon(img);
    return null;
  }

}


