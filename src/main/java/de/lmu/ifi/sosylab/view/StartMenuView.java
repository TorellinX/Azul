package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.controller.Controller;
import de.lmu.ifi.sosylab.model.GameModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartMenuView extends JFrame {

  private JPanel graphic;
  private JPanel buttons;
  private JButton hotSeatButton;
  private JButton multiPlayerButton;
  private JFrame thisFrame;


  private static final String LOCAL_GAME = "localGame";
  private static final String MULTIPLAYER = "multiplayer";

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
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

  }



  private void hotSeatButtonView() {
    hotSeatButton = new JButton("HOTSEAT");
    buttons.add(hotSeatButton);

    hotSeatButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        //view.showLocalMode();
        HotSeatMenuView testView = new HotSeatMenuView();
        dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
      }
    });

  }

  private void multiPlayerButtonView() {
    multiPlayerButton = new JButton("MULTIPLAYER");
    buttons.add(multiPlayerButton);

    multiPlayerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO: view.showMultiplayer();
        dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
      }
    });

  }

}
