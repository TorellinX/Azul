package de.lmu.ifi.sosylab.view;

import javax.swing.*;
import java.awt.*;

class DrawboardTableCenter extends JPanel{
    public DrawboardTableCenter(){
        setPreferredSize(new Dimension(400,600));
    }

    @Override

    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        //Plates werden gemalt:
        Color backroundColor = new Color(135, 206, 250);
        g.setColor(backroundColor);
        g.fillOval(5,5,100,100);
        g.fillOval(155,5,100,100);
        g.fillOval(305,5,100,100);
        g.fillOval(80,105,100,100);
        g.fillOval(230,105,100,100);
        g.fillOval(80,230,100,100);
        g.fillOval(230,230,100,100);
        g.fillOval(80,355,100,100);
        g.fillOval(230,355,100,100);
        g.setColor(Color.black);
        ((Graphics2D) g).setStroke(new BasicStroke(2));
        g.drawOval(5,5,100,100);
        g.drawOval(155,5,100,100);
        g.drawOval(305,5,100,100);
        g.drawOval(80,105,100,100);
        g.drawOval(230,105,100,100);
        g.drawOval(80,230,100,100);
        g.drawOval(230,230,100,100);
        g.drawOval(80,355,100,100);
        g.drawOval(230,355,100,100);

        //Spielfeld wird gemalen:
        Color tableColor = new Color(234, 182, 118);
        g.setColor(tableColor);
        g.fillRoundRect(5,490,395, 185, 20, 20);

        //Beispielsteine in Tables:
        //Table1:
        g.setColor(Color.red);
        g.fillRect(17,17,35,35);
        g.setColor(Color.yellow);
        g.fillRect(58,17,35,35);
        g.setColor(Color.blue);
        g.fillRect(17,58,35,35);
        g.setColor(Color.green);
        g.fillRect(58,58,35,35);
        //Table2:
        g.setColor(Color.red);
        g.fillRect(167,17,35,35);
        g.setColor(Color.yellow);
        g.fillRect(208,17,35,35);
        g.setColor(Color.blue);
        g.fillRect(167,58,35,35);
        g.setColor(Color.green);
        g.fillRect(208,58,35,35);
        //Table3:
        g.setColor(Color.red);
        g.fillRect(317,17,35,35);
        g.setColor(Color.yellow);
        g.fillRect(358,17,35,35);
        g.setColor(Color.blue);
        g.fillRect(317,58,35,35);
        g.setColor(Color.green);
        g.fillRect(358,58,35,35);
        //Table4:
        g.setColor(Color.red);
        g.fillRect(92,117,35,35);
        g.setColor(Color.yellow);
        g.fillRect(133,117,35,35);
        g.setColor(Color.blue);
        g.fillRect(92,158,35,35);
        g.setColor(Color.green);
        g.fillRect(133,158,35,35);
        //Table5:
        g.setColor(Color.red);
        g.fillRect(242,117,35,35);
        g.setColor(Color.yellow);
        g.fillRect(283,117,35,35);
        g.setColor(Color.blue);
        g.fillRect(242,158,35,35);
        g.setColor(Color.green);
        g.fillRect(283,158,35,35);
        //Table6:
        g.setColor(Color.red);
        g.fillRect(92,242,35,35);
        g.setColor(Color.yellow);
        g.fillRect(133,242,35,35);
        g.setColor(Color.blue);
        g.fillRect(92,283,35,35);
        g.setColor(Color.green);
        g.fillRect(133,283,35,35);
        //Table7:
        g.setColor(Color.red);
        g.fillRect(242,242,35,35);
        g.setColor(Color.yellow);
        g.fillRect(283,242,35,35);
        g.setColor(Color.blue);
        g.fillRect(242,283,35,35);
        g.setColor(Color.green);
        g.fillRect(283,283,35,35);
        //Table8:
        g.setColor(Color.red);
        g.fillRect(92,367,35,35);
        g.setColor(Color.yellow);
        g.fillRect(133,367,35,35);
        g.setColor(Color.blue);
        g.fillRect(92,408,35,35);
        g.setColor(Color.green);
        g.fillRect(133,408,35,35);
        //Table9:
        g.setColor(Color.red);
        g.fillRect(242,367,35,35);
        g.setColor(Color.yellow);
        g.fillRect(283,367,35,35);
        g.setColor(Color.blue);
        g.fillRect(242,408,35,35);
        g.setColor(Color.green);
        g.fillRect(283,408,35,35);


        //Beispielstein am Tisch:
        g.setColor(Color.gray);
        g.fillRect(25,505,35,35);
        g.setColor(Color.blue);
        g.fillRect(105,505,35,35);
        g.setColor(Color.green);
        g.fillRect(145,505,35,35);
        g.setColor(Color.red);
        g.fillRect(185,505,35,35);
        g.setColor(Color.black);
        g.fillRect(225,505,35,35);
        g.setColor(Color.red);
        g.fillRect(265,505,35,35);
        g.setColor(Color.black);
        g.fillRect(305,505,35,35);
        g.setColor(Color.blue);
        g.fillRect(345,505,35,35);
        g.setColor(Color.blue);
        g.fillRect(105,545,35,35);
        g.setColor(Color.green);
        g.fillRect(145,545,35,35);
        g.setColor(Color.black);
        g.fillRect(185,545,35,35);
        g.setColor(Color.blue);
        g.fillRect(225,545,35,35);
        g.setColor(Color.yellow);
        g.fillRect(265,545,35,35);
        g.setColor(Color.red);
        g.fillRect(305,545,35,35);
        g.setColor(Color.blue);
        g.fillRect(345,545,35,35);
        g.setColor(Color.black);
        g.fillRect(105,585,35,35);
        g.setColor(Color.blue);
        g.fillRect(145,585,35,35);
        g.setColor(Color.black);
        g.fillRect(185,585,35,35);
        g.setColor(Color.green);
        g.fillRect(225,585,35,35);
        g.setColor(Color.black);
        g.fillRect(265,585,35,35);
        g.setColor(Color.green);
        g.fillRect(305,585,35,35);
        g.setColor(Color.yellow);
        g.fillRect(345,585,35,35);
        g.setColor(Color.yellow);
        g.fillRect(105,625,35,35);
        g.setColor(Color.red);
        g.fillRect(145,625,35,35);
        g.setColor(Color.red);
        g.fillRect(185,625,35,35);
        g.setColor(Color.blue);
        g.fillRect(225,625,35,35);
        g.setColor(Color.black);
        g.fillRect(265,625,35,35);
        g.setColor(Color.black);
        g.fillRect(305,625,35,35);
    }
}
