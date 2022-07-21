package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.client.ClientApplication;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Class for network based mulitplayer mode - premature.
 */
public class RoomView extends JFrame {

  /**
   * Constructor - see class description.
   */
  public RoomView() {
    super("Room View");

    setVisible(true);
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    JPanel textFieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    add(textFieldPanel, BorderLayout.NORTH);
    JTextField textField = new JFormattedTextField("Display server response. "
        + "EXIT on close window.");
    textFieldPanel.add(textField);

    JPanel textAreaPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    add(textAreaPanel, BorderLayout.CENTER);
    JTextArea textArea = new JTextArea("Ausgabe");
    textAreaPanel.add(textArea);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    add(buttonPanel, BorderLayout.SOUTH);
    JButton testButton = new JButton("Request test response from server");
    buttonPanel.add(testButton);

    ClientApplication client = new ClientApplication("testuser");

    testButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        textArea.setText(client.serverPost(""));
        // thisFrame.setVisible(false);
      }
    });

    /*
    JOptionPane.showMessageDialog(null,
        "Not fully implemented, yet.\n Window will close.",
        "Dead End", JOptionPane.ERROR_MESSAGE);

    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    */
  }
}
