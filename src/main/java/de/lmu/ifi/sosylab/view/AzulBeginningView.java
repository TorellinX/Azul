package de.lmu.ifi.sosylab.view;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class AzulBeginningView extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;

    public AzulBeginningView(){
    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("BorderLayout");
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        Color backroundColor = new Color(135, 206, 250);

        GraphicAzul graphicAzul = new GraphicAzul();

        //Oberes Panel wird mit Logo gefüllt.
        JPanel panelUp = new JPanel();
        panelUp.add(graphicAzul.azulPanel);
        panelUp.setBackground(backroundColor);

        //Mittleres Panel wird erstellt und mit Buttons gefüllt.
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new FlowLayout());
        JButton localGameButton = new JButton("Local Game");
        JButton networkGameButton = new JButton("Network Game");
        panelCenter.add(localGameButton);
        panelCenter.add(networkGameButton);
        panelCenter.setBackground(backroundColor);


        Container c = frame.getContentPane();
        c.add(panelUp, BorderLayout.NORTH);
        c.add(panelCenter, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setVisible(true);
    }



}

