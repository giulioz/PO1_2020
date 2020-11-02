package it.unive.dais.po1.web;

import org.springframework.context.ApplicationEvent;

public class NewGameEvent extends ApplicationEvent {
  private final String gameId;

  public NewGameEvent(Object source, String gameId) {
    super(source);
    this.gameId = gameId;
  }

  public String getGameId() {
    return gameId;
  }
}
