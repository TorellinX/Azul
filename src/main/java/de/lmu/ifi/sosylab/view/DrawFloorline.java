package de.lmu.ifi.sosylab.view;

import javax.swing.*;
import java.awt.*;

public class DrawFloorline extends JPanel {

    private final Color playerboardcolor = new Color(204, 201, 199);
    private final Color floorlinecolor = new Color(139, 0, 139);
    private int size;
    private String[] textPenaltyPoints = new String[]{"-1", "-1", "-2", "-2", "-2", "-3", "-3"};

    public DrawFloorline() {

        this.size = 35;

    }

    public int getFloorlineCellSize() {
        return size;
    }

    public void setFloorlineCellSize(int newSize) {
        this.size = newSize;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        g2D.setColor(playerboardcolor);
        g2D.setStroke(new BasicStroke(2));
        for (int col = 0; col < textPenaltyPoints.length; col++) {
            g2D.setColor(floorlinecolor);
            g2D.drawRect(col * size, 10, size, size);
        }

        g.setColor(floorlinecolor);
        for (int i = 0; i < textPenaltyPoints.length; i++) {
            g.drawString(textPenaltyPoints[i], size * i, 0);
        }
    }




}


