package it.unive.dais.po1.web;

import org.springframework.context.ApplicationEvent;

public class GameUpdateEvent extends ApplicationEvent {
  private final String gameId;
  private final GameState gameState;

  public GameUpdateEvent(Object source, String gameId, GameState gameState) {
    super(source);
    this.gameId = gameId;
    this.gameState = gameState;
  }

  public String getGameId() {
    return gameId;
  }

  public GameState getGameState() {
    return gameState;
  }
}
