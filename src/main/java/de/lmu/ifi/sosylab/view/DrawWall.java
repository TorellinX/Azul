package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.model.ColorTile;
import de.lmu.ifi.sosylab.model.Player;

import javax.swing.*;
import java.awt.*;

public class DrawWall extends JPanel {

    private int size;
    private final Color playerboardcolor = new Color(204, 201, 199);
    private final Player player;
    private final Controller controller;
    private boolean[][] wall;

    public DrawWall(Player player, Controller controller) {
        this.controller = controller;
        this.player = player;
        this.size = 35;
        setPreferredSize(new Dimension(getWallFrameSize(), getWallFrameSize()));
    }

    public int getWallCellSize() {
        return size;
    }

    public int getWallFrameSize() {
        return (5 * size + 40);
    }

    public void setWallCellSize(int newSize) {
        this.size = newSize;
    }

    // Draws the wall
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        g2D.setColor(playerboardcolor);
        g2D.fillRoundRect(0, 0, getWallFrameSize(), getWallFrameSize(), 20, 20);

        g2D.setStroke(new BasicStroke(2));

        wall = player.getPlayerBoard().getWall();

        int colorNumber = 0;
        for (int col = 0; col < 5; col++) {
            for (int row = 0; row < 5; row++) {
                colorNumber = (col + (4 - row)) % 5;
                switch (colorNumber) {
                    case 0 -> g2D.setColor(Color.yellow);
                    case 1 -> g2D.setColor(Color.red);
                    case 2 -> g2D.setColor(Color.black);
                    case 3 -> g2D.setColor(Color.green);
                    case 4 -> g2D.setColor(Color.blue);
                    default -> throw new IllegalStateException("Unexpected value: " + colorNumber);
                }

                g2D.drawRect(10 + col * size + col * 5, 10 + row * size + row * 5, size, size);

                if (wall[row][col]) {
                    g2D.fillRect(10 + col * size + col * 5, 10 + row * size + row * 5, size, size);
                }

            }
        }
        g2D.setColor(Color.black);
    }
}
