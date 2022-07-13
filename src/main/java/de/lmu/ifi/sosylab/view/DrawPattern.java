package de.lmu.ifi.sosylab.view;

import javax.swing.*;
import java.awt.*;

public class DrawPattern extends JPanel {

    private int size;
    private final Color playerboardcolor = new Color(204, 201, 199);

    public DrawPattern() {

       this.size = 35;
    }

    public int getPatternCellSize() {
        return size;
    }

    public void setPatternCellSize(int newSize) {
        this.size = newSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        g2D.setColor(playerboardcolor);
        g2D.setStroke(new BasicStroke(1));

        for (int col = 0; col < 5; col++) {
            for (int row = 4 - col; row < 5; row++) {
                g2D.setColor(Color.BLACK);
                g2D.drawRect(col * size, row * size, size, size);
            }
        }
    }



}
