package de.lmu.ifi.sosylab.view;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class AzulNewLocalGameView extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    public AzulNewLocalGameView(){
    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("BorderLayout");
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        Color backroundColor = new Color(135, 206, 250);


        GraphicAzul graphicAzul = new GraphicAzul();


        //Oberes Panel wird mit Überschrift gefüllt.
        JPanel panelUp = new JPanel();
        JLabel newLocalGameText = new JLabel("New Local Game");
        panelUp.add(newLocalGameText);
        panelUp.setBackground(backroundColor);

        //Linkes Panel wird mit Tabelle gefüllt.
        JPanel panelLeft = new JPanel();
        JLabel listPlayers = new JLabel();
        listPlayers.setText("<html><body>Das<br>wird<br>eine<br>Tabelle</body></html>");
        panelLeft.add(listPlayers);
        panelLeft.setBackground(backroundColor);



        //Mittleres Panel wird erstellt und mit Buttons gefüllt.
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new FlowLayout());
        JTextField nickname = new JTextField("Das wird das Textfeld");
        panelCenter.add(nickname);
        panelCenter.setBackground(backroundColor);

        //Rechtes Panel wird erstellt und mit Button gefüllt.
        JPanel panelRight = new JPanel();
        panelRight.setLayout(new FlowLayout());
        JButton addPlayerButton = new JButton("Add Player");
        JButton removePlayerButton = new JButton("Remove Player");
        panelRight.add(addPlayerButton);
        panelRight.add(removePlayerButton);
        panelRight.setBackground(backroundColor);

        //Unteres Panel wird erstellt und mit Buttons gefüllt.
        JPanel panelBottom = new JPanel();
        panelBottom.setLayout(new FlowLayout());
        JButton startGameButton = new JButton("Start Game");
        JButton backButton = new JButton("Back Game");
        panelBottom.add(startGameButton);
        panelBottom.add(backButton);
        panelBottom.setBackground(backroundColor);



        Container c = frame.getContentPane();
        c.add(panelUp, BorderLayout.NORTH);
        c.add(panelLeft, BorderLayout.WEST);
        c.add(panelCenter, BorderLayout.CENTER);
        c.add(panelRight, BorderLayout.EAST);
        c.add(panelBottom, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setVisible(true);
    }



}
