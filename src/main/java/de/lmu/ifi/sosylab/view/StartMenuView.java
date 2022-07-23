package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.client.ClientApplication;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import lombok.SneakyThrows;

/**
 * Displays the logo and allows for choice between hotseat and server based multiplayer.
 */
public class StartMenuView extends JFrame {

  private JPanel graphic;
  private JPanel buttons;
  private JButton hotSeatButton;
  private JButton multiPlayerButton;
  private JFrame thisFrame;


  private static final String LOCAL_GAME = "localGame";
  private static final String MULTIPLAYER = "multiplayer";

  /**
   * Constructor - see class description.
   */
  public StartMenuView() {
    super("Azul");
    thisFrame = this;
    setLayout(new BorderLayout());

    // Link zu MainMenuView, temporär nötig
    // this.view = view;

    // Logo einrichten und anzeigen
    graphic = new JPanel();
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

  }



  private void hotSeatButtonView() {
    hotSeatButton = new JButton("HOTSEAT");
    buttons.add(hotSeatButton);
    hotSeatButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        HotseatMenuView hotseatMenuView = new HotseatMenuView();
        thisFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
        // thisFrame.setVisible(false);
      }
    });

  }

  private void multiPlayerButtonView() {
    multiPlayerButton = new JButton("MULTIPLAYER");
    buttons.add(multiPlayerButton);

    multiPlayerButton.addActionListener(new ActionListener() {
      @SneakyThrows
      @Override
      public void actionPerformed(ActionEvent e) {
        ClientApplication client = new ClientApplication();
        new MultiplayerLobbyView(client);
        thisFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        System.out.println("Hotseat");
        dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
        // thisFrame.setVisible(false);
      }
    });

  }

  public void showStartView() {
    thisFrame.setVisible(true);
  }



}
