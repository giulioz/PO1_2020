package it.unive.dais.po1.web;

import org.springframework.context.ApplicationEvent;

public class CloseGameEvent extends ApplicationEvent {
  private final String gameId;

  public CloseGameEvent(Object source, String gameId) {
    super(source);
    this.gameId = gameId;
  }

  public String getGameId() {
    return gameId;
  }
}
