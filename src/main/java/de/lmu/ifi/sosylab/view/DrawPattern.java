package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.model.ColorTile;
import de.lmu.ifi.sosylab.model.Player;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Component drawing class for pattern lines including action listeners.
 */
public class DrawPattern extends JPanel {

    private int size;
    private final Color playerboardcolor = new Color(204, 201, 199);
    private ArrayList<JButton> patternButtons;
    int count;
    private final Player player;
    private final Controller controller;
    private ColorTile[][] patternLines;

    /**
     * Constructs the pattern lines and links it to player and controller.
     *
     * @param player      player related to the board containing these pattern lines
     * @param controller  game controller
     */
    public DrawPattern(Player player, Controller controller) {
        this.player = player;
        this.controller = controller;
        this.size = 35;
        setPreferredSize(new Dimension(5 * size + 5, 5 * size + 40));
        setLayout(null);

        patternButtons = new ArrayList<>();
        JButton firstRowButton = new JButton();
        firstRowButton.setBounds(getPatternCellSize() * 4, 10, getPatternCellSize(),
               getPatternCellSize());
        patternButtons.add(firstRowButton);
        add(firstRowButton);
        JButton secondRowButton = new JButton();
        secondRowButton.setBounds(getPatternCellSize() * 3, 15 + getPatternCellSize(),
                getPatternCellSize() * 2, getPatternCellSize());
        patternButtons.add(secondRowButton);
        add(secondRowButton);
        JButton thirdRowButton = new JButton();
        thirdRowButton.setBounds(getPatternCellSize() * 2, 20 + getPatternCellSize() * 2,
                getPatternCellSize() * 3, getPatternCellSize());
        patternButtons.add(thirdRowButton);
        add(thirdRowButton);
        JButton fourthRowButton = new JButton();
        fourthRowButton.setBounds(getPatternCellSize(), 25 + getPatternCellSize() * 3,
                getPatternCellSize() * 4, getPatternCellSize());
        patternButtons.add(fourthRowButton);
        add(fourthRowButton);
        JButton fifthRowButton = new JButton();
        fifthRowButton.setBounds(0, 30 + getPatternCellSize() * 4, getPatternCellSize() * 5,
                getPatternCellSize());
        patternButtons.add(fifthRowButton);
        add(fifthRowButton);

        // hide buttons
        for (int i = 0; i < patternButtons.size(); i++) {
            patternButtons.get(i).setOpaque(false);
            patternButtons.get(i).setContentAreaFilled(false);
            patternButtons.get(i).setBorderPainted(false);
        }

        for (int i = 0; i < 5; i++) {
            final int count = i;
            patternButtons.get(count).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(player.getNickname() + " - " + count);
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

        ColorTile[][] invPatternLines = player.getPlayerBoard().getPatternLines();
        patternLines = player.getPlayerBoard().getPatternLines();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < invPatternLines[i].length; j++) {
                patternLines[i][invPatternLines[i].length - 1 - j] = invPatternLines[i][j];
            }
        }


        for (int row = 0; row < 5; row++) {
            for (int col = 4 - row; col < 5; col++) {
              g2D.setColor(Color.BLACK);
                g2D.drawRect(col * size, 10 + row * size + row * 5, size, size);

                if (patternLines[row][4 - col] != null) {
                    String customColor = patternLines[row][4 - col].toString();
                    Color color;
                    switch (customColor) {
                        case "BLACK":
                            color = Color.black;
                            break;
                        case "WHITE":
                            color = Color.green;
                            break;
                        case "BLUE":
                            color = Color.blue;
                            break;
                        case "YELLOW":
                            color = Color.yellow;
                            break;
                        case "RED":
                            color = Color.red;
                            break;
                        default:
                            color = playerboardcolor;
                    }

                    g2D.setColor(color);
                    g2D.fillRect(col * size, 10 + row * size + row * 5, size, size);
                }
           }
        }
    }
}
