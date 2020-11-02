package it.unive.dais.po1.web;

import it.unive.dais.po1.exercise2.Board;
import it.unive.dais.po1.exercise2.Mark;
import it.unive.dais.po1.exercise2.Player;
import it.unive.dais.po1.web.games.TicTacToeBoard;
import it.unive.dais.po1.web.games.TicTacToeRandomPlayer;

public class GameState {
  private final Board currentBoard;

  private final Player playerA;
  private final Player playerB;
  private Player lastPlayer;

  public GameState() {
    this.currentBoard = new TicTacToeBoard();
    this.playerA = new TicTacToeRandomPlayer(Mark.getCircle());
    this.playerB = new TicTacToeRandomPlayer(Mark.getCross());
    this.lastPlayer = this.playerA;
  }

  public Board getCurrentBoard() {
    return currentBoard;
  }

  public Player getPlayerA() {
    return playerA;
  }

  public Player getPlayerB() {
    return playerB;
  }

  public void switchPlayer() {
    if (lastPlayer == playerA) {
      lastPlayer = playerB;
    } else {
      lastPlayer = playerA;
    }
  }

  public Player getLastPlayer() {
    return lastPlayer;
  }

  public Mark getWinner() {
    return currentBoard.winner();
  }
}
