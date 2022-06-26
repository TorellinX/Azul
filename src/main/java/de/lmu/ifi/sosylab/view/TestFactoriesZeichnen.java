package de.lmu.ifi.sosylab.view;

import javax.swing.*;

/*
 * Die Klasse Draw01Panel zeigt wie man ohne
 * Java2D auf ein JPanel zeichnet.
 */
public class TestFactoriesZeichnen {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setTitle("Factories");
        frame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);


        TestFactories drawPanel = new TestFactories();
        frame.getContentPane().add(drawPanel);

        frame.setSize(500,800);
        frame.setVisible(true);
    }
}
