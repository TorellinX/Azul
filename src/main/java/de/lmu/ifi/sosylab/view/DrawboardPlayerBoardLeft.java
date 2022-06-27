package de.lmu.ifi.sosylab.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serial;
import javax.swing.JPanel;
import javax.swing.text.StyledEditorKit;

public class DrawboardPlayerBoardLeft extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel drawboardplayerboard;
    private Graphics2D g;

    public DrawboardPlayerBoardLeft(){
        setPreferredSize(new Dimension(400,700));
        repaint();
    }

    public JPanel getPanel(){
        return drawboardplayerboard;
    }


    @Override

    protected void paintComponent(Graphics g){

        super.paintComponent(g);
        g = (Graphics2D) g;


        //Zuerst wird das obere Spielfeld gezeichnet, das immer gebraucht wird.
        //username links oben wird geschrieben
        g.setColor(Color.black);
        g.drawString("user 1", 5,15);

        //Rechtecke der Patternlines werden gezeichnet.
        ((Graphics2D) g).setStroke(new BasicStroke(1));
        g.drawRect(145, 5, 35, 35);
        g.drawRect(110, 40, 35, 35);
        g.drawRect(145, 40, 35, 35);
        g.drawRect(75, 75, 35, 35);
        g.drawRect(110, 75, 35, 35);
        g.drawRect(145, 75, 35, 35);
        g.drawRect(40, 110, 35, 35);
        g.drawRect(75, 110, 35, 35);
        g.drawRect(110, 110, 35, 35);
        g.drawRect(145, 110, 35, 35);
        g.drawRect(05, 145, 35, 35);
        g.drawRect(40, 145, 35, 35);
        g.drawRect(75, 145, 35, 35);
        g.drawRect(110, 145, 35, 35);
        g.drawRect(145, 145, 35, 35);


        Color playerboardcolor = new Color(204, 201, 199);
        g.setColor(playerboardcolor);
        g.fillRoundRect(200,5,181, 181, 20, 20);


        //Ränder der Rechtecke der Wall werden gezeichnet.
        ((Graphics2D) g).setStroke(new BasicStroke(2));
        g.setColor(Color.blue);
        g.drawRect(205,10,31,31);
        g.drawRect(240,45,31,31);
        g.drawRect(275,80,31,31);
        g.drawRect(310,115,31,31);
        g.drawRect(345,150,31,31);
        g.setColor(Color.yellow);
        g.drawRect(240,10,31,31);
        g.drawRect(275,45,31,31);
        g.drawRect(310,80,31,31);
        g.drawRect(345,115,31,31);
        g.drawRect(205,150,31,31);
        g.setColor(Color.red);
        g.drawRect(275,10,31,31);
        g.drawRect(310,45,31,31);
        g.drawRect(345,80,31,31);
        g.drawRect(205,115,31,31);
        g.drawRect(240,150,31,31);
        g.setColor(Color.black);
        g.drawRect(310,10,31,31);
        g.drawRect(345,45,31,31);
        g.drawRect(205,80,31,31);
        g.drawRect(240,115,31,31);
        g.drawRect(275,150,31,31);
        g.setColor(Color.green);
        g.drawRect(345,10,31,31);
        g.drawRect(205,45,31,31);
        g.drawRect(240,80,31,31);
        g.drawRect(275,115,31,31);
        g.drawRect(310,150,31,31);

        g.setColor(Color.black);

        //Minuspunkteleiste:
        Color floorlinecolor = new Color(139, 0, 139);
        g.setColor(floorlinecolor);
        g.drawString("-1", 15, 200);
        g.drawRect(05, 205, 35, 35);
        g.drawString("-1", 50, 200);
        g.drawRect(40, 205, 35, 35);
        g.drawString("-2", 85, 200);
        g.drawRect(75, 205, 35, 35);
        g.drawString("-2", 120, 200);
        g.drawRect(110, 205, 35, 35);
        g.drawString("-2", 155, 200);
        g.drawRect(145, 205, 35, 35);
        g.drawString("-3", 190, 200);
        g.drawRect(180, 205, 35, 35);
        g.drawString("-3", 225, 200);
        g.drawRect(215, 205, 35, 35);

        //Score:
        Color score = new Color(235, 79, 0);
        g.setColor(score);
        g.drawString("Punkte:", 5, 260);
        g.drawString("123", 50, 260);



        //Hier wird das untere Spielfeld gezeichnet, das nur bei 3 Spielern gebraucht wird.
        //username links oben wird geschrieben
        g.setColor(Color.black);
        g.drawString("user 3", 5,315);

        //Rechtecke der Patternlines werden gezeichnet.

        ((Graphics2D) g).setStroke(new BasicStroke(1));
        g.drawRect(145, 305, 35, 35);
        g.drawRect(110, 340, 35, 35);
        g.drawRect(145, 340, 35, 35);
        g.drawRect(75, 375, 35, 35);
        g.drawRect(110, 375, 35, 35);
        g.drawRect(145, 375, 35, 35);
        g.drawRect(40, 410, 35, 35);
        g.drawRect(75, 410, 35, 35);
        g.drawRect(110, 410, 35, 35);
        g.drawRect(145, 410, 35, 35);
        g.drawRect(05, 445, 35, 35);
        g.drawRect(40, 445, 35, 35);
        g.drawRect(75, 445, 35, 35);
        g.drawRect(110, 445, 35, 35);
        g.drawRect(145, 445, 35, 35);


        g.setColor(playerboardcolor);
        g.fillRoundRect(200,300,185, 185, 20, 20);

        //Ränder der Rechtecke der Wall werden gezeichnet.
        ((Graphics2D) g).setStroke(new BasicStroke(2));
        g.setColor(Color.blue);
        g.drawRect(205,307,31,31);
        g.drawRect(240,342,31,31);
        g.drawRect(275,377,31,31);
        g.drawRect(310,412,31,31);
        g.drawRect(345,447,31,31);
        g.setColor(Color.yellow);
        g.drawRect(240,307,31,31);
        g.drawRect(275,342,31,31);
        g.drawRect(310,377,31,31);
        g.drawRect(345,412,31,31);
        g.drawRect(205,447,31,31);
        g.setColor(Color.red);
        g.drawRect(275,307,31,31);
        g.drawRect(310,342,31,31);
        g.drawRect(345,377,31,31);
        g.drawRect(205,412,31,31);
        g.drawRect(240,447,31,31);
        g.setColor(Color.black);
        g.drawRect(310,307,31,31);
        g.drawRect(345,342,31,31);
        g.drawRect(205,377,31,31);
        g.drawRect(240,412,31,31);
        g.drawRect(275,447,31,31);
        g.setColor(Color.green);
        g.drawRect(345,307,31,31);
        g.drawRect(205,342,31,31);
        g.drawRect(240,377,31,31);
        g.drawRect(275,412,31,31);
        g.drawRect(310,447,31,31);

        g.setColor(Color.black);

        //Minuspunkteleiste:
        g.setColor(floorlinecolor);
        g.drawString("-1", 15, 500);
        g.drawRect(05, 505, 35, 35);
        g.drawString("-1", 50, 500);
        g.drawRect(40, 505, 35, 35);
        g.drawString("-2", 85, 500);
        g.drawRect(75, 505, 35, 35);
        g.drawString("-2", 120, 500);
        g.drawRect(110, 505, 35, 35);
        g.drawString("-2", 155, 500);
        g.drawRect(145, 505, 35, 35);
        g.drawString("-3", 190, 500);
        g.drawRect(180, 505, 35, 35);
        g.drawString("-3", 225, 500);
        g.drawRect(215, 505, 35, 35);

        //Score:
        g.setColor(score);
        g.drawString("Punkte:", 5, 560);
        g.drawString("123", 50, 560);

    }

}
