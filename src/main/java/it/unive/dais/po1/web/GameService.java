package it.unive.dais.po1.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Map;

public class GameService implements ApplicationListener<ApplicationEvent> {
  private static final Log log = LogFactory.getLog(GameService.class);
  private final Map<String, GameState> games;
  @Autowired
  private ApplicationEventPublisher applicationEventPublisher;

  public GameService() {
    this.games = new HashMap<>();
  }

  @Override
  public void onApplicationEvent(ApplicationEvent event) {
    if (event instanceof RequestGameEvent) {
      String gameId = ((RequestGameEvent) event).getGameId();
      GameState gameState = games.get(gameId);
      if (gameState != null) {
        applicationEventPublisher.publishEvent(new GameUpdateEvent(this, gameId, gameState));
      }
    }

    if (event instanceof CloseGameEvent) {
      String gameId = ((CloseGameEvent) event).getGameId();
      games.remove(gameId);
    }

    if (event instanceof NewGameEvent) {
      String gameId = ((NewGameEvent) event).getGameId();
      GameState gameState = new GameState();
      games.put(gameId, gameState);
    }
  }

  @Scheduled(initialDelay = 0, fixedDelay = 1000)
  public void start() {
    for (Map.Entry<String, GameState> entry : games.entrySet()) {
      entry.getValue().getLastPlayer().play(entry.getValue().getCurrentBoard());
      applicationEventPublisher.publishEvent(new GameUpdateEvent(this, entry.getKey(), entry.getValue()));
      entry.getValue().switchPlayer();
    }
  }
}
