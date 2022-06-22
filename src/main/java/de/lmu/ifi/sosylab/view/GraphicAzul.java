package de.lmu.ifi.sosylab.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serial;

public class GraphicAzul extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;

    public JPanel azulPanel = new JPanel();

    public GraphicAzul(){
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GraphicAzul());
    }

    public void setAzulPanel(boolean x)
    {
        azulPanel.setVisible(x);
    }
}


