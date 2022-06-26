package de.lmu.ifi.sosylab.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serial;
import javax.swing.JPanel;

public class DrawboardPlayerBoard extends JPanel {

    private static final long serialVersionUID = 1L;



    public DrawboardPlayerBoard(){
        repaint();
    }


    @Override

    protected void paintComponent(Graphics g){

        super.paintComponent(g);


        g.setColor(Color.black);
        g.drawRect(160, 0, 40, 40);
        g.setColor(Color.gray);
        g.fillRect(160, 0, 40, 40);

    }




}
