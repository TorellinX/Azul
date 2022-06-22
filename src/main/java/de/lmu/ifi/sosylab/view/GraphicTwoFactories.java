package de.lmu.ifi.sosylab.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serial;

public class GraphicTwoFactories extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;

    public JPanel twoFactoriesPanel = new JPanel();

    public GraphicTwoFactories(){
        init();
    }

    private void init() {
        JLabel label = new JLabel(showImg());
        twoFactoriesPanel.add(label);
    }

    private ImageIcon showImg() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/twoFactories.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ImageIcon(img);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GraphicTwoFactories());
    }

    public void setPlayerBoardPanel(boolean x)
    {
        twoFactoriesPanel.setVisible(x);
    }
}


