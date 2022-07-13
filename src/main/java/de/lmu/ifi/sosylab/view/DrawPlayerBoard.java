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
    private final Color inactivePlayerColor = new Color(204, 201, 199);
    private final DrawWall drawWall;
    private final DrawPattern drawPattern;
    private final DrawFloorline drawFloorline;
    private ArrayList<JButton> patternButtons;
    private JButton floorlineButton;
    private JLabel playerNameLabel;
    private JPanel labelPanel;
    private int count;


    public DrawPlayerBoard(Player player, Controller controller) {
        this.drawPattern = new DrawPattern(player, controller);
        this.drawWall = new DrawWall();
        this.drawFloorline = new DrawFloorline();
//        Color backroundColor = new Color(255, 0, 0);
//        setBackground(backroundColor);
        labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // North: Player nick mit background color change für active player
        playerNameLabel = new JLabel("<html>" + "<font face=\"cursive,serif\" size=\"4\">" + player.getNickname()
                + "</font face=\"cursive,serif\" size=\"4\">" + "</html>");
        labelPanel.setPreferredSize(new Dimension(playerBoardPreferredWidth(), 30));
        labelPanel.add(playerNameLabel);
        add(labelPanel, BorderLayout.NORTH);
        setPlayerLabelBackgroundColor(player);


        // linke Seite: die pattern lines mit Buttons
        // besser mit koordinaten?

        add(drawPattern, BorderLayout.EAST);
 /*       patternButtons = new ArrayList<>();
        JButton firstRowButton = new JButton();
        firstRowButton.setBounds(drawPattern.getPatternCellSize() * 4, 5, drawPattern.getPatternCellSize(),
                drawPattern.getPatternCellSize());
        patternButtons.add(firstRowButton);
        add(firstRowButton, BorderLayout.EAST);
        JButton secondRowButton = new JButton();
        secondRowButton.setBounds(drawPattern.getPatternCellSize() * 3, 5 + drawPattern.getPatternCellSize(),
                drawPattern.getPatternCellSize(), drawPattern.getPatternCellSize());
        patternButtons.add(secondRowButton);
        add(secondRowButton, BorderLayout.EAST);
        JButton thirdRowButton = new JButton();
        thirdRowButton.setBounds(drawPattern.getPatternCellSize() * 2, 5 + drawPattern.getPatternCellSize() * 2,
                drawPattern.getPatternCellSize(), drawPattern.getPatternCellSize());
        patternButtons.add(thirdRowButton);
        add(thirdRowButton, BorderLayout.EAST);
        JButton fourthRowButton = new JButton();
        fourthRowButton.setBounds(drawPattern.getPatternCellSize(), 5 + drawPattern.getPatternCellSize() * 3,
                drawPattern.getPatternCellSize(), drawPattern.getPatternCellSize());
        patternButtons.add(fourthRowButton);
        add(fourthRowButton, BorderLayout.EAST);
        JButton fifthRowButton = new JButton();
        fifthRowButton.setBounds(0, 5 + drawPattern.getPatternCellSize() * 4, drawPattern.getPatternCellSize(),
                drawPattern.getPatternCellSize());
        patternButtons.add(fifthRowButton);
        add(fifthRowButton, BorderLayout.EAST);

        addPatternLinesButtonsActionListeners(player, controller);
*/
        // Mitte ggf ein gap?
        //JPanel gapPanel = new JPanel();
        //gapPanel.setPreferredSize(new Dimension(20, 150));
        //add(gapPanel, BorderLayout.CENTER);

        // rechte Seite: die Wall
        add(drawWall, BorderLayout.WEST);

        // South: floor line mit score
        JPanel floorLinePanel = new JPanel((new BorderLayout()));
        add(floorLinePanel, BorderLayout.SOUTH);

        floorLinePanel.add(drawFloorline, BorderLayout.EAST);
        floorlineButton = new JButton();
        floorlineButton.setBounds(0, 10, drawFloorline.getFloorlineCellSize() * 7,
                drawFloorline.getFloorlineCellSize());
        floorLinePanel.add(floorlineButton, BorderLayout.EAST);
        floorlineButton.setOpaque(false);
        floorlineButton.setContentAreaFilled(false);
        floorlineButton.setBorderPainted(false);
        addFloorlineButtonListener(player, controller);

        JLabel scoreLabel = new JLabel("Score: " + Integer.toString(player.getScore()));
        floorLinePanel.add(scoreLabel, BorderLayout.WEST);
        // scoreLabel.setText("Score: " + Integer.toString(player.getScore()));
        // TODO: set score color

/*        // hide buttons
        for (int i = 0; i < patternButtons.size(); i++) {
            patternButtons.get(i).setOpaque(false);
            patternButtons.get(i).setContentAreaFilled(false);
            patternButtons.get(i).setBorderPainted(true);
        }
*/
        setPreferredSize(playerBoardPreferredSize());
        setVisible(true);

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

    private void addFloorlineButtonListener(Player player, Controller controller) {

        floorlineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("P1" + 5);
                controller.placeTiles(player, -1);
            }
        });
    }

    public void setPlayerLabelBackgroundColor(Player player) {

        if (player.getState().equals(PlayerState.TO_MOVE)) {
            labelPanel.setBackground(activePlayerColor);
        } else {
            labelPanel.setBackground(inactivePlayerColor);
        }

    }

    public Dimension playerBoardPreferredSize() {
        int horizontal = 0;
        int vertical = 0;
        horizontal = playerBoardPreferredWidth();
        vertical = drawWall.getWallFrameSize() + 200;     // magic number to be refined....
        return new Dimension(horizontal, vertical);
    }

    public int playerBoardPreferredWidth() {
        return (5 * drawPattern.getPatternCellSize() + drawWall.getWallFrameSize() + 100);  // refine magic number ....
    }


}
