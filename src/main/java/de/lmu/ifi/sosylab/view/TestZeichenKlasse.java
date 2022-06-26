package de.lmu.ifi.sosylab.view;

import javax.swing.*;

/*
 * Die Klasse Draw01Panel zeigt wie man ohne
 * Java2D auf ein JPanel zeichnet.
 */
public class TestZeichenKlasse {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setTitle("Draw01");
        frame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);


        TestSpielfeld drawPanel = new TestSpielfeld();
        frame.getContentPane().add(drawPanel);

        frame.setSize(500,230);
        frame.setVisible(true);
    }
}
