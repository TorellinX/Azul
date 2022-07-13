package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.PlayerState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;


public class DrawPlayerBoard extends JPanel /*implements PropertyChangeListener*/ {

    private final Color activePlayerColor = Color.green;
    private final Color inactivePlayerColor = new Color(204, 201, 199);
    private final DrawWall drawWall;
    private final DrawPattern drawPattern;
    private final DrawFloorline drawFloorline;
    private ArrayList<JButton> patternButtons;
    private JButton floorlineButton;
    private JLabel playerNameLabel;
    private JPanel labelPanel;
    private JLabel scoreLabel;
    private Player player;

    public DrawPlayerBoard(Player player, Controller controller) {
        this.player = player;
        this.drawPattern = new DrawPattern(player, controller);
        this.drawWall = new DrawWall(player, controller);
        this.drawFloorline = new DrawFloorline(player, controller);
//        Color backroundColor = new Color(255, 0, 0);
//        setBackground(backroundColor);
        labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // North: Player nick mit background color change für active player
        playerNameLabel = new JLabel("<html><font size=\"5\">" + player.getNickname() + "</font></html>");
        labelPanel.setPreferredSize(new Dimension(playerBoardPreferredWidth(), 30));
        labelPanel.add(playerNameLabel);
        setPlayerLabelBackgroundColor(player);
        add(labelPanel, BorderLayout.NORTH);


        // linke Seite: die pattern lines mit Buttons
        add(drawPattern, BorderLayout.EAST);

        // Mitte ggf ein gap?
        JPanel gapPanel = new JPanel();
        gapPanel.setPreferredSize(new Dimension(20, 150));
        add(gapPanel, BorderLayout.CENTER);

        // rechte Seite: die Wall
        add(drawWall, BorderLayout.WEST);

        // South: floor line mit score
        JPanel floorLinePanel = new JPanel((new BorderLayout()));
        floorLinePanel.add(drawFloorline, BorderLayout.WEST);
        scoreLabel = new JLabel("<html><font size=\"5\"> Score: " + Integer.toString(player.getScore())
                + "</font></html>");
        floorLinePanel.add(scoreLabel, BorderLayout.EAST);
        // TODO: set score label color if desired

        add(floorLinePanel, BorderLayout.SOUTH);

        setPreferredSize(playerBoardPreferredSize(1));
        setVisible(true);

    }



    public void setPlayerLabelBackgroundColor(Player player) {

        if (player.getState().equals(PlayerState.TO_MOVE)) {
            labelPanel.setBackground(activePlayerColor);
        } else {
            labelPanel.setBackground(inactivePlayerColor);
        }

    }

    public void setScoreLabel(int playerScore) {
        scoreLabel.setText("<html><font size=\"5\"> Score: " + Integer.toString(playerScore)
                    + "</font></html>");
    }

    public Dimension playerBoardPreferredSize(int playerNumber) {
        int horizontal = 0;
        int vertical = 0;
        horizontal = playerBoardPreferredWidth();
        vertical = playerNumber * drawWall.getWallFrameSize() + 200;     // magic number to be refined....
        return new Dimension(horizontal, vertical);
    }

    public int playerBoardPreferredWidth() {
        return (5 * drawPattern.getPatternCellSize() + drawWall.getWallFrameSize() + 100);  // refine magic number ....
    }


    /**
     * Will be informed when the model is updated.
     */
//    @Override
//    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
//        SwingUtilities.invokeLater(() -> handleModelUpdate(propertyChangeEvent));
//    }

    /**
     * Redraws the playing field.
     *
     * @param event property change event
     */

//    private void handleModelUpdate(PropertyChangeEvent event) {
//        if (event.getPropertyName().equals("Model changed")) {
//            repaint();
//            System.out.println("Score for " + player.getNickname() +": " + player.getScore());
//            scoreLabel.setText("<html><font size=\"5\"> Score: " + Integer.toString(player.getScore())
//                    + "</font></html>");
//        }
//    }
}
