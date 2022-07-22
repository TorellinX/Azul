package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.client.ClientApplication;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
  // TODO: change type of players in player list to Player in final implementation
  // TODO: chat to coordinate players in room?
  public RoomView(String roomID, List<String> players) {
    super(roomID);

    setVisible(true);
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    // Header of header panel: label
    JPanel headerPanel = new JPanel(new BorderLayout());
    JPanel headerPanelHeader = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel headerPanelHeaderLabel = new JLabel("Players in " + roomID);
    headerPanelHeader.add(headerPanelHeaderLabel);
    headerPanel.add(headerPanelHeader, BorderLayout.NORTH);

    // Body of header panel: players list
    JPanel playersList = new JPanel(new GridLayout(4,1));
    for (int i = 0; i < players.size(); i++) {
      JPanel playerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
      JTextField player = new JTextField("", 25);
      playerPanel.add(player);
      playersList.add(playerPanel);
      if (i < players.size()) {
        player.setText(players.get(i));
      }

    }
    headerPanel.add(playersList, BorderLayout.CENTER);

    // dummy for bottom of header panel, remove is not required
    // ToDo: remove or use for something
    JPanel dummyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel dummy = new JLabel("SOUTH panel of NORTH panel...");
    dummyPanel.add(dummy);
    headerPanel.add(dummyPanel, BorderLayout.SOUTH);

    // Position header panel
    add(headerPanel, BorderLayout.NORTH);

    // Body panel, currently http client output to api/start
    // TODO: something.... -> perhaps chat hier?
    JPanel textAreaPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JTextArea textArea = new JTextArea("Server Ausgabe für Test Button");
    textAreaPanel.add(textArea);
    add(textAreaPanel, BorderLayout.CENTER);

    // Bottom panel for buttons
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton testButton = new JButton("Test server response to api/start with 3 fakes + nickname.");
    buttonPanel.add(testButton);
    add(buttonPanel, BorderLayout.SOUTH);

    // Call http client to add new new user to room and whatsoever
    // ToDo: whatsoever required
    ClientApplication client = new ClientApplication();

    testButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        textArea.setText(client.serverPost(players.get(players.size() - 1)));
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
