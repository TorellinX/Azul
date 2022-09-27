package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.model.Player;
import de.lmu.ifi.sosylab.model.PlayerState;
import de.lmu.ifi.sosylab.view.ColorSchemes.ColorScheme;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Class to draw a complete player board for a single player.
 */
public class DrawPlayerBoard extends JPanel {

  private final DrawWall drawWall;
  private final DrawPattern drawPattern;
  private final DrawFloorline drawFloorline;
  private final JPanel namePanel;
  private final JLabel scoreLabel;
  private Player player;
  private ColorScheme colorScheme;

  /**
   * Constructs a complete player board panel in compound layout.
   *
   * @param player     player related to this board
   * @param controller game controller
   */
  public DrawPlayerBoard(Player player, Controller controller, ColorScheme colorScheme) {
    this.player = player;
    this.drawPattern = new DrawPattern(player, controller);
    this.drawWall = new DrawWall(player, controller);
    this.drawFloorline = new DrawFloorline(player, controller);
    this.colorScheme = colorScheme;
    setBackground(colorScheme.playerboard());

    // North: Player nick mit background color change für active player
    namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel playerNameLabel = new JLabel("<html><font size=\"5\">"
        + player.getNickname() + "</font></html>");
    namePanel.setPreferredSize(new Dimension(playerBoardPreferredWidth(), 30));
    namePanel.add(playerNameLabel);
    setPlayerLabelBackgroundColor(player);
    add(namePanel, BorderLayout.NORTH);

    // linke Seite: die pattern lines mit Buttons
    add(drawPattern, BorderLayout.EAST);

    // Mitte ggf ein gap?
    JPanel gapPanelCenter = new JPanel();
    gapPanelCenter.setOpaque(false);
    gapPanelCenter.setPreferredSize(new Dimension(20, 150));
    add(gapPanelCenter, BorderLayout.CENTER);

    // rechte Seite: die Wall
    add(drawWall, BorderLayout.WEST);

    // South: floor line mit score
    JPanel floorLinePanel = new JPanel((new FlowLayout()));
    floorLinePanel.setBackground(colorScheme.playerboard());

    floorLinePanel.add(drawFloorline);

    JPanel scorePanel = new JPanel(new BorderLayout());
    scorePanel.setOpaque(false);
    scorePanel.setPreferredSize(new Dimension(163, 60));
    floorLinePanel.add(scorePanel);
    scoreLabel = new JLabel();
    setScoreLabel(player.getScore());
    scorePanel.add(scoreLabel, BorderLayout.EAST);

    JPanel gapPanelFloor = new JPanel();
    gapPanelFloor.setOpaque(false);
    gapPanelFloor.setPreferredSize(new Dimension(0, 20));
    floorLinePanel.add(gapPanelFloor);

    add(floorLinePanel, BorderLayout.SOUTH);
    setPreferredSize(playerBoardPreferredSize(1));
    setVisible(true);
  }


  /**
   * Toggles the background color of the player label panel to highlight the player to move.
   *
   * @param player player linked to ths board
   */
  public void setPlayerLabelBackgroundColor(Player player) {

    if (player.getPlayerState().equals(PlayerState.TO_MOVE)) {
      namePanel.setBackground(colorScheme.activePlayer());
    } else {
      namePanel.setBackground(colorScheme.inactivePlayer());
    }

  }

  /**
   * Setter for score label for update of the score during run of the game.
   *
   * @param playerScore as returned by model
   */
  public void setScoreLabel(int playerScore) {
    scoreLabel.setForeground(colorScheme.scoreText());
    scoreLabel.setText("<html><font size=\"5\"> Score: " + playerScore + "</font></html>");
  }

  /**
   * Getter for the preferred size of the complete player board for pack method in playing view.
   *
   * @param playerNumberScaling number of players scaling factor for table center (1/2: 1; 3/4: 2)
   * @return preferred dimension
   */
  public Dimension playerBoardPreferredSize(int playerNumberScaling) {
    int horizontal;
    int vertical;
    horizontal = playerBoardPreferredWidth();
    vertical = playerNumberScaling * drawWall.getWallFrameSize() + 125;   // magic number ....
    return new Dimension(horizontal, vertical);
  }

  /**
   * Returns the preferred width of the complete player board for adaptations of neighboring panels.
   * For convenience purposes - could be extracted from dimension returned by preferred size
   * getter.
   *
   * @return preferred witdh in pixel
   */
  public int playerBoardPreferredWidth() {                        // refine magic number below....
    return (5 * drawPattern.getPatternCellSize() + drawWall.getWallFrameSize() + 100);
  }

  /**
   * Setter for color scheme of the player board.
   *
   * @param colorScheme current color scheme
   */
  public void setColorScheme(ColorScheme colorScheme) {
    this.colorScheme = colorScheme;
    drawWall.setColorScheme(colorScheme);
    drawPattern.setColorScheme(colorScheme);
    drawFloorline.setColorScheme(colorScheme);
  }

  /**
   * Routing for my nickname from multiplayer mode client for identification of active board events
   * listeners.
   *
   * @param myNickname player's nickname
   */
  public void setMyNickname(String myNickname) {
    drawPattern.setMyNickname(myNickname);
    drawFloorline.setMyNickname(myNickname);
  }
}
