package de.lmu.ifi.sosylab.view;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;


public class HilfsklasseDrawboard extends JFrame{
    @Serial
    private static final long serialVersionUID = 1L;

    private final DrawboardPlayerBoard drawboardplayerboard;


    public HilfsklasseDrawboard(){
        drawboardplayerboard = new DrawboardPlayerBoard();
    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("BorderLayout");

        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        Color backroundColor = new Color(135, 206, 250);



        GraphicTwoFactories twoFactories1 = new GraphicTwoFactories();
        GraphicTwoFactories twoFactories2 = new GraphicTwoFactories();
        GraphicTwoFactories twoFactories3 = new GraphicTwoFactories();
        GraphicThreeFactories threeFactories = new GraphicThreeFactories();

        //Oberes Panel wird mit Combobox gefüllt.
        JPanel panelUp = new JPanel();
        panelUp.setLayout(new FlowLayout());
        JComboBox<String> menu = new JComboBox<String>(new String[] {"restart", "leave", "end game"});
        panelUp.add(menu);
        panelUp.setBackground(backroundColor);


        //Linkes Panel wird erstellt und mit Spielfeld gefüllt.
        JPanel panelLeft = new JPanel();
        BoxLayout boxlayoutleft = new BoxLayout(panelLeft, BoxLayout.Y_AXIS);
        panelLeft.setLayout(boxlayoutleft);
        panelLeft.add(drawboardplayerboard);
        panelLeft.setBackground(backroundColor);

        //Rechtes Panel wird erstellt und mit Spielfeld gefüllt.
        JPanel panelRight = new JPanel();
        BoxLayout boxlayoutright = new BoxLayout(panelRight, BoxLayout.Y_AXIS);
        panelRight.setLayout(boxlayoutright);
        panelRight.add(graphicPlayerBoardRight1.playerBoardPanel);
        panelRight.add(graphicPlayerBoardRight2.playerBoardPanel);
        panelRight.setBackground(backroundColor);

        //Mittleres Panel wird erstellt und mit Tellern gefüllt.
        JPanel panelCenter = new JPanel();
        BoxLayout boxlayoutcenter = new BoxLayout(panelCenter, BoxLayout.Y_AXIS);
        panelCenter.setLayout(boxlayoutcenter);
        panelCenter.setSize(10,10);
        panelCenter.add(threeFactories.threeFactoriesPanel);
        panelCenter.add(twoFactories1.twoFactoriesPanel);
        panelCenter.add(twoFactories2.twoFactoriesPanel);
        panelCenter.add(twoFactories3.twoFactoriesPanel);
        panelCenter.setBackground(backroundColor);

        JPanel panelSouth = new JPanel();
        panelSouth.setBackground(backroundColor);

        Container c = frame.getContentPane();
        c.add(panelUp, BorderLayout.NORTH);
        c.add(panelSouth, BorderLayout.SOUTH);
        c.add(panelRight, BorderLayout.EAST);
        c.add(panelLeft, BorderLayout.WEST);
        c.add(panelCenter, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setVisible(true);
    }



}
