package de.lmu.ifi.sosylab.view;
import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class AzulNewNetworkGameView extends JFrame{

    @Serial
    private static final long serialVersionUID = 1L;


        public AzulNewNetworkGameView(){
        }


        public static void main(String[] args) {

            JFrame frame = new JFrame("BorderLayout");
            frame.setLayout(new BorderLayout());
            frame.setVisible(true);

            Color backroundColor = new Color(135, 206, 250);


            GraphicAzul graphicAzul = new GraphicAzul();


            //Oberes Panel wird mit Überschrift gefüllt.
            JPanel panelUp = new JPanel();
            JLabel newNetworkGameText = new JLabel("New Network Game");
            panelUp.add(newNetworkGameText);
            panelUp.setBackground(backroundColor);




            //Mittleres Panel wird erstellt und mit Buttons gefüllt.
            JPanel panelCenter = new JPanel();
            panelCenter.setLayout(new FlowLayout());
            JTextField nickname = new JTextField("Nickname eingeben...");
            JTextField room = new JTextField("Raum eingeben...");
            panelCenter.add(nickname);
            panelCenter.add(room);
            panelCenter.setBackground(backroundColor);



            //Unteres Panel wird erstellt und mit Buttons gefüllt.
            JPanel panelBottom = new JPanel();
            panelBottom.setLayout(new FlowLayout());
            JButton enterRoomButton = new JButton("Enter Room");
            JButton backButton = new JButton("Back Game");
            panelBottom.add(enterRoomButton);
            panelBottom.add(backButton);
            panelBottom.setBackground(backroundColor);



            Container c = frame.getContentPane();
            c.add(panelUp, BorderLayout.NORTH);
            c.add(panelCenter, BorderLayout.CENTER);
            c.add(panelBottom, BorderLayout.SOUTH);

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.setVisible(true);
        }



    }


