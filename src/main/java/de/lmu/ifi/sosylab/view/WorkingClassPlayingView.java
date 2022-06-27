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
        DrawboardPlayerBoardLeft drawboardplayerboardleft = new DrawboardPlayerBoardLeft();
        DrawboardPlayerBoardRight drawboardplayerboardright = new DrawboardPlayerBoardRight();
        DrawboardTableCenter drawboardTableCenter = new DrawboardTableCenter();


        //Oberes Panel wird mit Combobox gefüllt.
        JPanel panelUp = new JPanel();
        panelUp.setSize(1200, 75);
        panelUp.setLayout(new FlowLayout());
        JComboBox<String> menu = new JComboBox<String>(new String[] {"- menu -","restart", "leave", "end game"});
        panelUp.add(menu);
        panelUp.setBackground(backroundColor);


        //Linkes Panel wird mit Buttons gefüllt:

        drawboardplayerboardleft.setLayout(null);

        JButton firstrowu1button = new JButton();
        firstrowu1button.setBounds(145, 5, 35, 35);
        firstrowu1button.setVisible(false);
        firstrowu1button.setEnabled(true);

        JButton secondrowu1button = new JButton();
        secondrowu1button.setBounds(110, 40, 70, 35);
        secondrowu1button.setVisible(false);
        secondrowu1button.setEnabled(true);

        JButton thirdrowu1button = new JButton();
        thirdrowu1button.setBounds(75, 75, 105, 35);
        thirdrowu1button.setVisible(false);
        thirdrowu1button.setEnabled(true);

        JButton fourthrowu1button = new JButton();
        fourthrowu1button.setBounds(40, 110, 140, 35);
        fourthrowu1button.setVisible(false);
        fourthrowu1button.setEnabled(true);

        JButton fifthrowu1button = new JButton();
        fifthrowu1button.setBounds(5, 145, 175, 35);
        fifthrowu1button.setVisible(false);
        fifthrowu1button.setEnabled(true);

        JButton floorlineu1button = new JButton();
        floorlineu1button.setBounds(5, 205, 245, 35);
        floorlineu1button.setVisible(false);
        floorlineu1button.setEnabled(true);

        drawboardplayerboardleft.add(firstrowu1button);
        drawboardplayerboardleft.add(secondrowu1button);
        drawboardplayerboardleft.add(thirdrowu1button);
        drawboardplayerboardleft.add(fourthrowu1button);
        drawboardplayerboardleft.add(fifthrowu1button);
        drawboardplayerboardleft.add(floorlineu1button);

        JButton firstrowu3 = new JButton();
        firstrowu3.setBounds(145, 305, 35, 35);
        firstrowu3.setVisible(false);
        firstrowu3.setEnabled(true);

        JButton secondrowu3button = new JButton();
        secondrowu3button.setBounds(110, 340, 70, 35);
        secondrowu3button.setVisible(false);
        secondrowu3button.setEnabled(true);

        JButton thirdrowu3button = new JButton();
        thirdrowu3button.setBounds(75, 375, 105, 35);
        thirdrowu3button.setVisible(false);
        thirdrowu3button.setEnabled(true);

        JButton fourthrowu3button = new JButton();
        fourthrowu3button.setBounds(40, 410, 140, 35);
        fourthrowu3button.setVisible(false);
        fourthrowu3button.setEnabled(true);

        JButton fifthrowu3button = new JButton();
        fifthrowu3button.setBounds(5, 445, 175, 35);
        fifthrowu3button.setVisible(false);
        fifthrowu3button.setEnabled(true);

        JButton floorlineu3button = new JButton();
        floorlineu3button.setBounds(5, 505, 245, 35);
        floorlineu3button.setVisible(false);
        floorlineu3button.setEnabled(true);

        drawboardplayerboardleft.add(firstrowu3);
        drawboardplayerboardleft.add(secondrowu3button);
        drawboardplayerboardleft.add(thirdrowu3button);
        drawboardplayerboardleft.add(fourthrowu3button);
        drawboardplayerboardleft.add(fifthrowu3button);
        drawboardplayerboardleft.add(floorlineu3button);


        //Rechtes Panel wird mit Buttons gefüllt:

        drawboardplayerboardright.setLayout(null);

        JButton firstrowu2button = new JButton();
        firstrowu2button.setBounds(145, 5, 35, 35);
        firstrowu2button.setVisible(false);
        firstrowu2button.setEnabled(true);

        JButton secondrowu2button = new JButton();
        secondrowu2button.setBounds(110, 40, 70, 35);
        secondrowu2button.setVisible(false);
        secondrowu2button.setEnabled(true);

        JButton thirdrowu2button = new JButton();
        thirdrowu2button.setBounds(75, 75, 105, 35);
        thirdrowu2button.setVisible(false);
        thirdrowu2button.setEnabled(true);

        JButton fourthrowu2button = new JButton();
        fourthrowu2button.setBounds(40, 110, 140, 35);
        fourthrowu2button.setVisible(false);
        fourthrowu2button.setEnabled(true);

        JButton fifthrowu2button = new JButton();
        fifthrowu2button.setBounds(5, 145, 175, 35);
        fifthrowu2button.setVisible(false);
        fifthrowu2button.setEnabled(true);

        JButton floorlineu2button = new JButton();
        floorlineu2button.setBounds(5, 205, 245, 35);
        floorlineu2button.setVisible(false);
        floorlineu2button.setEnabled(true);

        drawboardplayerboardright.add(firstrowu2button);
        drawboardplayerboardright.add(secondrowu2button);
        drawboardplayerboardright.add(thirdrowu2button);
        drawboardplayerboardright.add(fourthrowu2button);
        drawboardplayerboardright.add(fifthrowu2button);
        drawboardplayerboardright.add(floorlineu2button);

        JButton firstrowu4button = new JButton();
        firstrowu4button.setBounds(145, 305, 35, 35);
        firstrowu4button.setVisible(false);
        firstrowu4button.setEnabled(true);

        JButton secondrowu4button = new JButton();
        secondrowu4button.setBounds(110, 340, 70, 35);
        secondrowu4button.setVisible(false);
        secondrowu4button.setEnabled(true);

        JButton thirdrowu4button = new JButton();
        thirdrowu4button.setBounds(75, 375, 105, 35);
        thirdrowu4button.setVisible(false);
        thirdrowu4button.setEnabled(true);

        JButton fourthrowu4button = new JButton();
        fourthrowu4button.setBounds(40, 410, 140, 35);
        fourthrowu4button.setVisible(false);
        fourthrowu4button.setEnabled(true);

        JButton fifthrowu4button = new JButton();
        fifthrowu4button.setBounds(5, 445, 175, 35);
        fifthrowu4button.setVisible(false);
        fifthrowu4button.setEnabled(true);

        JButton floorlineu4button = new JButton();
        floorlineu4button.setBounds(5, 505, 245, 35);
        floorlineu4button.setVisible(false);
        floorlineu4button.setEnabled(true);

        drawboardplayerboardright.add(firstrowu4button);
        drawboardplayerboardright.add(secondrowu4button);
        drawboardplayerboardright.add(thirdrowu4button);
        drawboardplayerboardright.add(fourthrowu4button);
        drawboardplayerboardright.add(fifthrowu4button);
        drawboardplayerboardright.add(floorlineu4button);


        //Unteres Panel wird erzeugt.
        JPanel panelSouth = new JPanel();
        panelSouth.setBackground(backroundColor);

        //Teile des Borderlayouts werden mit Panels und Graphikelementen gefüllt.
        Container c = frame.getContentPane();
        c.add(panelUp, BorderLayout.NORTH);
        c.add(panelSouth, BorderLayout.SOUTH);
        c.add(drawboardplayerboardright, BorderLayout.EAST);
        c.add(drawboardplayerboardleft, BorderLayout.WEST);
        c.add(drawboardTableCenter, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1300, 800);
        frame.setVisible(true);
    }
}

