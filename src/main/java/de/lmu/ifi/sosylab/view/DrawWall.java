package de.lmu.ifi.sosylab.view;

import javax.swing.*;
import java.awt.*;

public class DrawWall extends JPanel {

    private int size;
    private final Color playerboardcolor = new Color(204, 201, 199);

    public DrawWall() {

        this.size = 35;

    }

    public int getWallCellSize() {
        return size;
    }

    public void setWallCellSize(int newSize) {
        this.size = newSize;
    }

    // Draws the wall
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Graphics2D g2D = (Graphics2D) g;

        g.setColor(playerboardcolor);
        g.fillRoundRect(0, 0, 5 * size + 5, 5 * size + 5, 20, 20);

        int colorNumber = 0;
        for (int col = 0; col < 5; col++) {
            for (int row = 0; row < 5; row++) {
                colorNumber = (col + row) % 5;
                switch (colorNumber) {
                    case 0 -> g.setColor(Color.blue);
                    case 1 -> g.setColor(Color.green);
                    case 2 -> g.setColor(Color.black);
                    case 3 -> g.setColor(Color.red);
                    case 4 -> g.setColor(Color.yellow);
                    default -> throw new IllegalStateException("Unexpected value: " + colorNumber);
                }
                g.drawRect(col * size, (5 - row) * size, size, size);
            }
        }
        g.setColor(Color.black);
    }
}
