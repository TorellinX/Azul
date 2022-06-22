package de.lmu.ifi.sosylab.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serial;
import javax.swing.JPanel;

public class DrawboardPlayerBoard extends JPanel {
    private static final long serialVersionUID = 1L;

    private Graphics2D g2D;

    public JPanel playerBoardPanel = new JPanel();

    public DrawboardPlayerBoard(){
    }

    @Override

    protected void paintComponent(Graphics g){

        super.paintComponent(g);
        g2D = (Graphics2D) g;

        g2D.setColor(Color.black);
        g2D.drawRect(160, 0, 40, 40);
        g2D.setColor(Color.gray);
        g2D.fillRect(160, 0, 40, 40);

    }
}
