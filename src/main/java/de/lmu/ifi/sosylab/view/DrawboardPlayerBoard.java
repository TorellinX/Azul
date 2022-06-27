package de.lmu.ifi.sosylab.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serial;
import javax.swing.JPanel;
import javax.swing.text.StyledEditorKit;

public class DrawboardPlayerBoard extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel drawboardplayerboard;
    private Graphics2D g;
    private Object FontStyle;


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

        g.drawString("user 1", 5,15);

        //Rechtecke der Patternlines werden gezeichnet.
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
        g.fillRoundRect(200,0,185, 185, 20, 20);

        //Ränder der Rechtecke der Wall werden gezeichnet.
        ((Graphics2D) g).setStroke(new BasicStroke(2));
        g.setColor(Color.blue);
        g.drawRect(205,7,31,31);
        g.drawRect(240,42,31,31);
        g.drawRect(275,77,31,31);
        g.drawRect(310,112,31,31);
        g.drawRect(345,147,31,31);
        g.setColor(Color.yellow);
        g.drawRect(240,7,31,31);
        g.drawRect(275,42,31,31);
        g.drawRect(310,77,31,31);
        g.drawRect(345,112,31,31);
        g.drawRect(205,147,31,31);
        g.setColor(Color.red);
        g.drawRect(275,7,31,31);
        g.drawRect(310,42,31,31);
        g.drawRect(345,77,31,31);
        g.drawRect(205,112,31,31);
        g.drawRect(240,147,31,31);
        g.setColor(Color.black);
        g.drawRect(310,7,31,31);
        g.drawRect(345,42,31,31);
        g.drawRect(205,77,31,31);
        g.drawRect(240,112,31,31);
        g.drawRect(275,147,31,31);
        g.setColor(Color.green);
        g.drawRect(345,7,31,31);
        g.drawRect(205,42,31,31);
        g.drawRect(240,77,31,31);
        g.drawRect(275,112,31,31);
        g.drawRect(310,147,31,31);

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

    }


    public void setPreferredSize(int i, int i1) {
        drawboardplayerboard.setSize(400,700);
    }
}
