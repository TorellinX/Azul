package de.lmu.ifi.sosylab.view;

import javax.swing.*;
import java.awt.*;

class TestSpielfeld extends JPanel{
    public TestSpielfeld(){
        repaint();
    }

    @Override

    protected void paintComponent(Graphics g){

        super.paintComponent(g);
        g.setColor(Color.black);
        g.drawRect(160, 0, 40, 40);

        g.setColor(Color.black);
        g.drawRect(120, 40, 40, 40);
        g.drawRect(160, 40, 40, 40);
        g.drawRect(80, 80, 40, 40);
        g.drawRect(120, 80, 40, 40);
        g.drawRect(160, 80, 40, 40);
        g.drawRect(40, 120, 40, 40);
        g.drawRect(80, 120, 40, 40);
        g.drawRect(120, 120, 40, 40);
        g.drawRect(160, 120, 40, 40);
        g.drawRect(00, 160, 40, 40);
        g.drawRect(40, 160, 40, 40);
        g.drawRect(80, 160, 40, 40);
        g.drawRect(120, 160, 40, 40);
        g.drawRect(160, 160, 40, 40);
        g.setColor(Color.gray);
        g.fillRect(200,0,200,200);
        g.setColor(Color.blue);
        g.drawRect(201,1,38,38);
        g.drawRect(241,41,38,38);
        g.drawRect(281,81,38,38);
        g.drawRect(321,121,38,38);
        g.drawRect(361,161,38,38);
        g.setColor(Color.yellow);
        g.drawRect(241,1,38,38);
        g.drawRect(281,41,38,38);
        g.drawRect(321,81,38,38);
        g.drawRect(361,121,38,38);
        g.drawRect(201,161,38,38);
        g.setColor(Color.red);
        g.drawRect(281,1,38,38);
        g.drawRect(321,41,38,38);
        g.drawRect(361,81,38,38);
        g.drawRect(201,121,38,38);
        g.drawRect(241,161,38,38);
        g.setColor(Color.black);
        g.drawRect(321,1,38,38);
        g.drawRect(361,41,38,38);
        g.drawRect(201,81,38,38);
        g.drawRect(241,121,38,38);
        g.drawRect(281,161,38,38);
        g.setColor(Color.green);
        g.drawRect(361,1,38,38);
        g.drawRect(201,41,38,38);
        g.drawRect(241,81,38,38);
        g.drawRect(281,121,38,38);
        g.drawRect(321,161,38,38);







    }
}
