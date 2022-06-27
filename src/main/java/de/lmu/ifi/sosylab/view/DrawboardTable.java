package de.lmu.ifi.sosylab.view;

import javax.swing.*;
import java.awt.*;

class DrawboardTable extends JPanel{
    public DrawboardTable(){
        setPreferredSize(new Dimension(400,600));
    }

    @Override

    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        //Factories werden gemalt:
        g.setColor(Color.black);
        g.drawOval(0,0,100,100);
        g.drawOval(150,0,100,100);
        g.drawOval(300,0,100,100);
        g.drawOval(75,100,100,100);
        g.drawOval(225,100,100,100);
        g.drawOval(75,225,100,100);
        g.drawOval(225,225,100,100);
        g.drawOval(75,350,100,100);
        g.drawOval(225,350,100,100);

        //Spielfeld wird gemalen:
        g.setColor(Color.gray);
        g.fillRect(5,500, 390, 190);

        //Beispielsteine in Factory:
        g.setColor(Color.red);
        g.fillRect(5,5,40,40);
        g.fillRect(55,5,40,40);
        g.setColor(Color.blue);
        g.fillRect(5,55,40,40);
        g.setColor(Color.green);
        g.fillRect(55,55,40,40);


        //Beispielstein am Spielfeld:
        g.setColor(Color.red);
        g.fillRect(10,505,40,40);
        g.fillRect(55,505,40,40);
        g.setColor(Color.blue);
        g.fillRect(10,550,40,40);
        g.setColor(Color.green);










    }
}
