package de.lmu.ifi.sosylab.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
    // setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLayout(new BorderLayout());

    JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    add(headerPanel, BorderLayout.NORTH);
    JLabel header = new JLabel("Room Name:");
    headerPanel.add(header);

    JPanel textFieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    add(textFieldPanel, BorderLayout.CENTER);
    JTextField textField = new JFormattedTextField("Testeintrag");
    textFieldPanel.add(textField);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    add(buttonPanel, BorderLayout.SOUTH);
    JButton addRoomtoServer = new JButton("Create Room");
    buttonPanel.add(addRoomtoServer);

    JOptionPane.showMessageDialog(null,
        "Not fully implemented, yet.\n Window will close.",
        "Dead End", JOptionPane.ERROR_MESSAGE);

    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
  }
}
