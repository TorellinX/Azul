package de.lmu.ifi.sosylab.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serial;

public class GraphicPlayerBoard extends JPanel{
    @Serial
    private static final long serialVersionUID = 1L;

    public JPanel playerBoardPanel = new JPanel();

    public GraphicPlayerBoard(){
        init();
    }

    private void init() {
        JLabel label = new JLabel(showImg());
        playerBoardPanel.add(label);
    }

    private ImageIcon showImg() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/playerBoard.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ImageIcon(img);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GraphicPlayerBoard());
    }

    public void setPlayerBoardPanel(boolean x)
    {
        playerBoardPanel.setVisible(x);
    }
}

