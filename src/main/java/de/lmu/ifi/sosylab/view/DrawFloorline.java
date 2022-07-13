package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.model.PenaltyTile;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DrawFloorline extends JPanel {

    private final Color playerboardcolor = new Color(204, 201, 199);
    private final Color floorlinecolor = new Color(139, 0, 139);
    private int size;
    private String[] textPenaltyPoints = new String[]{"-1", "-1", "-2", "-2", "-2", "-3", "-3"};
    // private List<Tile> floorLine;
    private final Player player;

    public DrawFloorline(Player player, Controller controller) {
        this.player = player;
        this.size = 35;
        setPreferredSize(new Dimension(7 * size + 10, 60));
        setLayout(null);

        JButton floorlineButton = new JButton();
        add(floorlineButton);
        floorlineButton.setBounds(0, 20, getFloorlineCellSize() * 7, getFloorlineCellSize());
        floorlineButton.setOpaque(false);
        floorlineButton.setContentAreaFilled(false);
        floorlineButton.setBorderPainted(false);

        floorlineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(player.getNickname() + " - " +"floorLineButton");
                controller.placeTiles(player, -1);
            }
        });

    }

    public int getFloorlineCellSize() {
        return size;
    }

    public void setFloorlineCellSize(int newSize) {
        this.size = newSize;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

//        g2D.setColor(playerboardcolor);

        g2D.setColor(floorlinecolor);
        for (int i = 0; i < textPenaltyPoints.length; i++) {
            g2D.drawString(textPenaltyPoints[i], size * i, 15);
        }

        g2D.setStroke(new BasicStroke(2));

        List<Tile> floorLine =  player.getPlayerBoard().getFloorLine();

        for (int col = 0; col < textPenaltyPoints.length; col++) {
            g2D.setColor(floorlinecolor);
            g2D.drawRect(col * size, 20, size, size);
        }

        if (floorLine.size() != 0) {
            for (int col = 0; col < floorLine.size(); col++) {
                if (floorLine.get(col) instanceof PenaltyTile) {
                    g.setColor(Color.gray);
                } else {
                    if (floorLine.get(col).toString().equals("BLUE")) {
                        g.setColor(Color.blue);
                    }
                    if (floorLine.get(col).toString().equals("YELLOW")) {
                        g.setColor(Color.yellow);
                    }
                    if (floorLine.get(col).toString().equals("RED")) {
                        g.setColor(Color.red);
                    }
                    if (floorLine.get(col).toString().equals("BLACK")) {
                        g.setColor(Color.black);
                    }
                    if (floorLine.get(col).toString().equals("WHITE")) {
                        g.setColor(Color.green);
                    }
                }
                g2D.fillRect(col * size, 20, size, size);
            }

        }

    }


}


