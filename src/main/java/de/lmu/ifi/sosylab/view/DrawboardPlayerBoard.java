package de.lmu.ifi.sosylab.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serial;
import javax.swing.JPanel;

public class DrawboardPlayerBoard extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel drawboardplayerboard;



    public DrawboardPlayerBoard(){
        setPreferredSize(new Dimension(400,400));

        repaint();
    }

    public JPanel getPanel(){
        return drawboardplayerboard;
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


    public void setPreferredSize(int i, int i1) {
        drawboardplayerboard.setSize(400,400);
    }
}
