package it.unive.dais.po1.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GameWSController extends TextWebSocketHandler implements ApplicationListener<ApplicationEvent> {
  private static final Log log = LogFactory.getLog(GameWSController.class);

  private final Map<String, WebSocketSession> sessions = new HashMap<>();

  @Autowired
  private ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) {
    applicationEventPublisher.publishEvent(new RequestGameEvent(this, session.getId()));
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) {
    log.info("New connection " + session.getId());
    sessions.put(session.getId(), session);
    applicationEventPublisher.publishEvent(new NewGameEvent(this, session.getId()));
    applicationEventPublisher.publishEvent(new RequestGameEvent(this, session.getId()));
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
    log.info("Closed connection " + session.getId());
    sessions.remove(session.getId());
    applicationEventPublisher.publishEvent(new CloseGameEvent(this, session.getId()));
  }

  @Override
  public void handleTransportError(WebSocketSession session, Throwable exception) {
    log.error(exception.getMessage());
  }

  @Override
  public void onApplicationEvent(ApplicationEvent event) {
    if (event instanceof GameUpdateEvent) {
      try {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(((GameUpdateEvent) event).getGameState());
        WebSocketSession session = sessions.get(((GameUpdateEvent) event).getGameId());
        session.sendMessage(new TextMessage(json));
      } catch (IOException e) {
        log.error(e);
      }
    }
  }
}
