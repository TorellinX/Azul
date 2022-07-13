package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.PlayerState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DrawPlayerBoard extends JPanel {

    private final Color activePlayerColor = Color.green;
    private final Color inactivePlayerColor = Color.black;
    private final DrawWall drawWall;
    private final DrawPattern drawPattern;
    private final DrawFloorline drawFloorline;
    private ArrayList<JButton> patternButtons;
    private int count;


    public DrawPlayerBoard(Player player, Controller controller) {

        JPanel playerBoard = new JPanel(new BorderLayout());
        // North: Player nick mit background color change für active player
        // TODO: Player nick mit highlight for to move
        // rechte Seite: die Wall
        drawWall = new DrawWall();
        playerBoard.add(drawWall, BorderLayout.EAST);
        // Mitte ggf ein gap?
        // linke Seite: die pattern lines mit Buttons
        // besser mit koordinaten?
        drawPattern = new DrawPattern();
        playerBoard.add(drawPattern, BorderLayout.WEST);
        patternButtons = new ArrayList<>();
        JButton firstRowButton = new JButton();
        firstRowButton.setBounds(drawPattern.getPatternCellSize() * 4, 5, drawPattern.getPatternCellSize(), drawPattern.getPatternCellSize());
        patternButtons.add(firstRowButton);
        playerBoard.add(firstRowButton, BorderLayout.WEST);
        JButton secondRowButton = new JButton();
        secondRowButton.setBounds(drawPattern.getPatternCellSize() * 3, 5 + drawPattern.getPatternCellSize(), drawPattern.getPatternCellSize(), drawPattern.getPatternCellSize());
        patternButtons.add(secondRowButton);
        playerBoard.add(secondRowButton, BorderLayout.WEST);
        JButton thirdRowButton = new JButton();
        thirdRowButton.setBounds(drawPattern.getPatternCellSize() * 2, 5 + drawPattern.getPatternCellSize() * 2, drawPattern.getPatternCellSize(), drawPattern.getPatternCellSize());
        patternButtons.add(thirdRowButton);
        playerBoard.add(thirdRowButton, BorderLayout.WEST);
        JButton fourthRowButton = new JButton();
        fourthRowButton.setBounds(drawPattern.getPatternCellSize(), 5 + drawPattern.getPatternCellSize() * 3, drawPattern.getPatternCellSize(), drawPattern.getPatternCellSize());
        patternButtons.add(fourthRowButton);
        playerBoard.add(fourthRowButton, BorderLayout.WEST);
        JButton fifthRowButton = new JButton();
        fifthRowButton.setBounds(0, 5 + drawPattern.getPatternCellSize() * 4, drawPattern.getPatternCellSize(), drawPattern.getPatternCellSize());
        patternButtons.add(fifthRowButton);
        playerBoard.add(fifthRowButton, BorderLayout.WEST);

        addPatternLinesButtonsActionListeners(player, controller);

        // South: floor line mit score
        JPanel floorLinePanel = new JPanel((new BorderLayout()));
        playerBoard.add(floorLinePanel, BorderLayout.SOUTH);
        drawFloorline = new DrawFloorline();
        floorLinePanel.add(drawFloorline, BorderLayout.WEST);
        JButton floorlineButton = new JButton();
        floorlineButton.setBounds(0, 10, drawFloorline.getFloorlineCellSize(), drawFloorline.getFloorlineCellSize());
        floorLinePanel.add(floorlineButton, BorderLayout.WEST);
        JLabel scoreLabel = new JLabel();
        floorLinePanel.add(scoreLabel);
        scoreLabel.setText("Score: " + Integer.toString(player.getScore()));
        // TODO: set score color

    }

    private void addPatternLinesButtonsActionListeners(Player player, Controller controller) {

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

    // vll als einfacher JTextField und change background color ??
    void drawNickname(Graphics g, Player player, int x, int y) {
        if (player.getState().equals(PlayerState.TO_MOVE)) {
            g.setColor(activePlayerColor);
            //g.setFont(new Font("default", Font.BOLD, 12));
            g.setFont(g.getFont().deriveFont(Font.BOLD));
        } else {
            g.setColor(inactivePlayerColor);
            // g.setFont(new Font("default", Font.PLAIN, 12));
            g.setFont(g.getFont().deriveFont(Font.BOLD));
        }
        g.drawString(player.getNickname(), x, y);
        g.setFont(g.getFont().deriveFont(Font.PLAIN));
    }


}
