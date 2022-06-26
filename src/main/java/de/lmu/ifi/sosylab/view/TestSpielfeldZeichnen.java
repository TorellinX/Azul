package de.lmu.ifi.sosylab.view;

import javax.swing.*;

/*
 * Die Klasse Draw01Panel zeigt wie man ohne
 * Java2D auf ein JPanel zeichnet.
 */
public class TestSpielfeldZeichnen {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setTitle("Draw01");
        frame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,250);


        TestSpielfeld drawPanel = new TestSpielfeld();
        frame.getContentPane().add(drawPanel);

        frame.setSize(500,230);
        frame.setVisible(true);

        JButton firstrow = new JButton();
        firstrow.setVisible(true);
        firstrow.setOpaque(false);
        firstrow.setContentAreaFilled(false);
        firstrow.setBorderPainted(false);
        firstrow.setSize(200,40);
        firstrow.setLocation(0,0);
        frame.add(firstrow);

        JButton secondrow = new JButton();
        secondrow.setVisible(true);
        secondrow.setOpaque(false);
        secondrow.setContentAreaFilled(false);
        secondrow.setBorderPainted(false);
        secondrow.setSize(200,40);
        secondrow.setLocation(0,40);
        frame.add(secondrow);

        JButton thirdrow = new JButton();
        thirdrow.setVisible(true);
        thirdrow.setOpaque(false);
        thirdrow.setContentAreaFilled(false);
        thirdrow.setBorderPainted(false);
        thirdrow.setSize(200,40);
        thirdrow.setLocation(0,80);
        frame.add(thirdrow);

        JButton fourthrow = new JButton();
        fourthrow.setVisible(true);
        fourthrow.setOpaque(false);
        fourthrow.setContentAreaFilled(false);
        fourthrow.setBorderPainted(false);
        fourthrow.setSize(200,40);
        fourthrow.setLocation(0,120);
        frame.add(fourthrow);

        JButton fifthrow = new JButton();
        fifthrow.setVisible(true);
        fifthrow.setOpaque(false);
        fifthrow.setContentAreaFilled(false);
        fifthrow.setBorderPainted(false);
        fifthrow.setSize(200,40);
        fifthrow.setLocation(0,160);
        frame.add(fifthrow);


    }
}
