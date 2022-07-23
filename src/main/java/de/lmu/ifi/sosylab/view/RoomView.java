package de.lmu.ifi.sosylab.view;

import de.lmu.ifi.sosylab.client.ClientApplication;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import lombok.SneakyThrows;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;

/**
 * Class for network based mulitplayer mode - premature.
 */
public class RoomView extends JFrame {

  private JFrame thisFrame;
  List<String> players;


  /**
   * Constructor - see class description.
   */
  // TODO: change type of players in player list to Player in final implementation and use getters
  // TODO: chat to coordinate players in room?
  public RoomView(String roomID, List<String> players, ClientApplication client) {
    super(roomID);
    thisFrame = this;
    this.players = players;

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
    JPanel playersList = new JPanel(new GridLayout(4, 1));
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
    JTextArea textArea1 = new JTextArea("Server Ausgabe für Test Button");
    textAreaPanel.add(textArea1);
    JTextArea textArea2 = new JTextArea("Serverausgabe GetMapping");
    textAreaPanel.add(textArea2);
    add(textAreaPanel, BorderLayout.CENTER);

    // Bottom panel for buttons
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton test1Button = new JButton("Server Post api/start.");
    buttonPanel.add(test1Button);
    JButton test2Button = new JButton("Server get api/getState.");
    buttonPanel.add(test2Button);
    JButton leaveButton = new JButton("LEAVE");
    buttonPanel.add(leaveButton);
    add(buttonPanel, BorderLayout.SOUTH);

    test1Button.addActionListener(new ActionListener() {
      @SneakyThrows
      @Override
      public void actionPerformed(ActionEvent e) {
        JSONArray names = new JSONArray();
        for (int i = 0; i < players.size(); i++) {
          names.put(players.get(i));
        }
        StringEntity namesArray = new StringEntity(names.toString(), "UTF-8");
        String uri = "http://localhost:8080/";
        String uriPost = "api/start";
        String user = "test";
        //textArea1.setText(client.serverPost(uri, uriPost, namesArray));
        textArea1.setText(client.register(user));
      }
    });

    test2Button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String uri = "http://localhost:8080/";
        String uriGet = "api/getState";
        textArea2.setText(client.serverGet(uri, uriGet));
      }
    });

    leaveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        MultiplayerLobbyView multiplayerLobbyView = new MultiplayerLobbyView(client);
        thisFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
      }
    });

  }
}
