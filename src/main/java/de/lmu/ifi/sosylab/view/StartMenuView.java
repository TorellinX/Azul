package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.client.ClientApplication;
import de.lmu.ifi.sosylab.view.ColorSchemes.ColorScheme;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import lombok.SneakyThrows;

/**
 * Displays the logo and allows for choice between hotseat and server based multiplayer.
 */
public class StartMenuView extends JFrame {

  private static final String LOCAL_GAME = "localGame";
  private static final String MULTIPLAYER = "multiplayer";
  private final JPanel buttons;
  private final JFrame thisFrame;
  private JButton hotSeatButton;
  private JButton multiPlayerButton;

  /**
   * Constructor - see class description.
   */
  public StartMenuView() {
    super("Azul");
    thisFrame = this;
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLayout(new BorderLayout());

    // Logo einrichten und anzeigen
    JPanel graphic = new JPanel();
    add(graphic, BorderLayout.CENTER);
    GraphicAzul graphicAzul = new GraphicAzul();
    graphic.add(graphicAzul.azulPanel);
    graphic.setBackground(new Color(135, 206, 250));

    // Buttons einrichten und anzeigen
    buttons = new JPanel();
    add(buttons, BorderLayout.SOUTH);
    buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
    hotSeatButtonView();
    multiPlayerButtonView();

    // Packen und sichtbar machen
    pack();
    setVisible(true);

    // Default action beim Klicken auf "X"
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setFrameLocationToScreenCenter();
  }


  private void hotSeatButtonView() {
    hotSeatButton = new JButton("HOTSEAT");
    ColorScheme.changeFontOf(hotSeatButton);
    buttons.add(hotSeatButton);
    hotSeatButton.addActionListener(e -> {
      new HotseatMenuView(this.getLocation());
      closeWindow();
      // thisFrame.setVisible(false);
    });
  }

  /**
   * Places the window in the middle of the screen.
   */
  private void setFrameLocationToScreenCenter() {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    this.setLocation((screenSize.width - this.getWidth()) / 2,
        (screenSize.height - this.getHeight()) / 2);
  }

  private void closeWindow() {
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
  }

  private void multiPlayerButtonView() {
    multiPlayerButton = new JButton("MULTIPLAYER");
    ColorScheme.changeFontOf(multiPlayerButton);
    buttons.add(multiPlayerButton);

    multiPlayerButton.addActionListener(new ActionListener() {
      @SneakyThrows
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("multiPlayerButton actionPerformed, "
            + "thread: " + Thread.currentThread().getId());
        new MultiplayerLobbyView(new ClientApplication());
        closeWindow();
        // thisFrame.setVisible(false);
      }
    });
  }
}
