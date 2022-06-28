package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.view.GraphicAzul;
import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class AzulNewNetworkGameView extends JPanel{

    @Serial
    private static final long serialVersionUID = 1L;
    private JPanel mainPanel;


        public AzulNewNetworkGameView(){
            createView();
        }

        public JPanel getPanel(){
            return mainPanel;
        }

        private void createView(){

            mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());


            GraphicAzul graphicAzul = new GraphicAzul();


            //Oberes Panel wird mit Überschrift gefüllt.
            JPanel panelUp = new JPanel();
            panelUp.setLayout(new GridLayout(1,2));
            JLabel multiplayerText = new JLabel("Multiplayer");
            //JLabel newNetworkGameText = new JLabel("Join a Server or create on");
            panelUp.add(multiplayerText);
            //panelUp.add(newNetworkGameText);
            JTextField nickname = new JTextField("Nickname eingeben...");
            //JTextField room = new JTextField("Raum eingeben...");
            panelUp.add(nickname);
            //panelUp.add(room);

            panelUp.setBackground(new Color(135, 206, 250));





            //Mittleres Panel aka Tabelle mit Spielen


            String[] columNames = {"Room Number", "Room Name", "Number of Players"};
            Object[][] data = {{"1", "Test", "2"}};
            JTable listOfServer = new JTable(data, columNames);

            JScrollPane pane = new JScrollPane(listOfServer);





            //Unteres Panel wird erstellt und mit Buttons gefüllt.
            JPanel panelBottom = new JPanel();
            //panelBottom.setLayout(new FlowLayout());
            panelBottom.setLayout(new GridLayout(1,3));
            JButton enterRoomButton = new JButton("Enter Room");
            JButton backButton = new JButton("Back to Gamemodeselection");
            JButton createRoom = new JButton("Create a new Gameroom");
            panelBottom.add(enterRoomButton);
            panelBottom.add(backButton);
            panelBottom.add(createRoom);
            panelBottom.setBackground(new Color(135, 206, 250));






            mainPanel.add(panelUp, BorderLayout.NORTH);
            mainPanel.add(pane, BorderLayout.CENTER);
            mainPanel.add(panelBottom, BorderLayout.SOUTH);
        }

        /*
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


         */


    }


