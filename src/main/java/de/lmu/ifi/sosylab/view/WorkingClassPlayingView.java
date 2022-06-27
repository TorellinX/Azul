package de.lmu.ifi.sosylab.view;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;


public class WorkingClassPlayingView extends JFrame{
    @Serial
    private static final long serialVersionUID = 1L;



    public WorkingClassPlayingView(){
            }


    public static void main(String[] args) {

        JFrame frame = new JFrame("Azul");

        frame.setLayout(new BorderLayout());

        Color backroundColor = new Color(135, 206, 250);

        //Zeichenelemente werden übergeben.
        DrawboardPlayerBoard drawboardplayerboardleft = new DrawboardPlayerBoard();
        DrawboardPlayerBoard drawboardplayerboardright = new DrawboardPlayerBoard();
        DrawboardTable drawboardTable = new DrawboardTable();

        //Oberes Panel wird mit Combobox gefüllt.
        JPanel panelUp = new JPanel();
        panelUp.setSize(1200, 75);
        panelUp.setLayout(new FlowLayout());
        JComboBox<String> menu = new JComboBox<String>(new String[] {"- menu -","restart", "leave", "end game"});
        panelUp.add(menu);
        panelUp.setBackground(backroundColor);

        //Unteres Panel wird erzeugt.
        JPanel panelSouth = new JPanel();
        panelSouth.setBackground(backroundColor);

        //Teile des Borderlayouts werden mit Panels und Graphikelementen gefüllt.
        Container c = frame.getContentPane();
        c.add(panelUp, BorderLayout.NORTH);
        c.add(panelSouth, BorderLayout.SOUTH);
        c.add(drawboardplayerboardleft, BorderLayout.EAST);
        c.add(drawboardplayerboardright, BorderLayout.WEST);
        c.add(drawboardTable, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setVisible(true);
    }
}

