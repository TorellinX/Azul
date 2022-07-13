package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DrawPattern extends JPanel {

    private int size;
    private final Color playerboardcolor = new Color(204, 201, 199);
    private ArrayList<JButton> patternButtons;
    int count;

    public DrawPattern(Player player, Controller controller) {

       this.size = 35;
       setPreferredSize(new Dimension(5 * size + 5, 5 * size + 40));

        patternButtons = new ArrayList<>();
        JButton firstRowButton = new JButton();
        firstRowButton.setBounds(getPatternCellSize() * 4, 5, getPatternCellSize(),
               getPatternCellSize());
        patternButtons.add(firstRowButton);
        add(firstRowButton, BorderLayout.EAST);
        JButton secondRowButton = new JButton();
        secondRowButton.setBounds(getPatternCellSize() * 3, 5 + getPatternCellSize(),
                getPatternCellSize(), getPatternCellSize());
        patternButtons.add(secondRowButton);
        add(secondRowButton, BorderLayout.EAST);
        JButton thirdRowButton = new JButton();
        thirdRowButton.setBounds(getPatternCellSize() * 2, 5 + getPatternCellSize() * 2,
                getPatternCellSize(), getPatternCellSize());
        patternButtons.add(thirdRowButton);
        add(thirdRowButton, BorderLayout.EAST);
        JButton fourthRowButton = new JButton();
        fourthRowButton.setBounds(getPatternCellSize(), 5 + getPatternCellSize() * 3,
                getPatternCellSize(), getPatternCellSize());
        patternButtons.add(fourthRowButton);
        add(fourthRowButton, BorderLayout.EAST);
        JButton fifthRowButton = new JButton();
        fifthRowButton.setBounds(0, 5 + getPatternCellSize() * 4, getPatternCellSize(),
                getPatternCellSize());
        patternButtons.add(fifthRowButton);
        add(fifthRowButton, BorderLayout.EAST);

        // hide buttons
        for (int i = 0; i < patternButtons.size(); i++) {
            patternButtons.get(i).setOpaque(false);
            patternButtons.get(i).setContentAreaFilled(false);
            patternButtons.get(i).setBorderPainted(true);
        }


        for (int i = 0; i < 5; i++) {
            count = i;
            patternButtons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("P1" + count);
                    controller.placeTiles(player, count);
                }
            });
        }
    }

    public int getPatternCellSize() {
        return size;
    }

    public void setPatternCellSize(int newSize) {
        this.size = newSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        g2D.setColor(playerboardcolor);
        g2D.setStroke(new BasicStroke(1));

        for (int col = 0; col < 5; col++) {
            for (int row = 4 - col; row < 5; row++) {
                g2D.setColor(Color.BLACK);
                g2D.drawRect(col * size, 10 + row * size + row * 5, size, size);
            }
        }
    }



}
