package de.lmu.ifi.sosylab.server.controller;

import java.util.Optional;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

public class PrecenceEventListener {

  private ParticipantRepository participantRepository;

  private SimpMessagingTemplate messagingTemplate;

  private String loginDestination;

  private String logoutDestination;

  public PrecenceEventListener(SimpMessagingTemplate messagingTemplate,
      ParticipantRepository participantRepository) {
    this.messagingTemplate = messagingTemplate;
    this.participantRepository = participantRepository;
  }

  @EventListener
  private void handleSessionConnected(SessionConnectEvent event) {
    SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
    String username = headers.getUser().getName();

    LoginEvent loginEvent = new LoginEvent(username);
    messagingTemplate.convertAndSend(loginDestination, loginEvent);

    // We store the session as we need to be idempotent in the disconnect event processing
    participantRepository.add(headers.getSessionId(), loginEvent);
  }

  @EventListener
  private void handleSessionDisconnect(SessionDisconnectEvent event) {

    System.out.println("disconnect");

    Optional.ofNullable(participantRepository.getParticipant(event.getSessionId()))
        .ifPresent(login -> {
          messagingTemplate.convertAndSend(logoutDestination, new LogoutEvent(login.getUsername()));
          participantRepository.removeParticipant(event.getSessionId());
        });
  }

  public void setLoginDestination(String loginDestination) {
    this.loginDestination = loginDestination;
  }

  public void setLogoutDestination(String logoutDestination) {
    this.logoutDestination = logoutDestination;
  }
}
