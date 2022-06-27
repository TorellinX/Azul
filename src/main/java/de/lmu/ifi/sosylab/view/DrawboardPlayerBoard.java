package de.lmu.ifi.sosylab.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serial;
import javax.swing.JPanel;

public class DrawboardPlayerBoard extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel drawboardplayerboard;
    private Graphics2D g;



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
        g = (Graphics2D) g;
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


        Color playerboardcolor = new Color(204, 201, 199);
        g.setColor(playerboardcolor);
        g.fillRoundRect(200,0,200, 200, 20, 20);
        //g.fillRect(200,0,200,200);

        //Ränder der Rechtecke werden gezeichnet.
        ((Graphics2D) g).setStroke(new BasicStroke(2));
        g.setColor(Color.blue);
        g.drawRect(201,1,35,35);
        g.drawRect(241,41,35,35);
        g.drawRect(281,81,35,35);
        g.drawRect(321,121,35,35);
        g.drawRect(361,161,35,35);
        g.setColor(Color.yellow);
        g.drawRect(241,1,35,35);
        g.drawRect(281,41,35,35);
        g.drawRect(321,81,35,35);
        g.drawRect(361,121,35,35);
        g.drawRect(201,161,35,35);
        g.setColor(Color.red);
        g.drawRect(281,1,35,35);
        g.drawRect(321,41,35,35);
        g.drawRect(361,81,35,35);
        g.drawRect(201,121,35,35);
        g.drawRect(241,161,35,35);
        g.setColor(Color.black);
        g.drawRect(321,1,35,35);
        g.drawRect(361,41,35,35);
        g.drawRect(201,81,35,35);
        g.drawRect(241,121,35,35);
        g.drawRect(281,161,35,35);
        g.setColor(Color.green);
        g.drawRect(361,1,35,35);
        g.drawRect(201,41,35,35);
        g.drawRect(241,81,35,35);
        g.drawRect(281,121,35,35);
        g.drawRect(321,161,35,35);
    }


    public void setPreferredSize(int i, int i1) {
        drawboardplayerboard.setSize(400,400);
    }
}
