package it.unive.dais.po1.web.games;

import it.unive.dais.po1.exercise2.Board;
import it.unive.dais.po1.exercise2.Mark;
import it.unive.dais.po1.exercise2.Player;

public class TicTacToeRandomPlayer extends Player {
  public TicTacToeRandomPlayer(Mark mark) {
    super(mark);
  }

  @Override
  public boolean play(Board board) {
    if (board.winner() != null || board.isFull()) {
      return false;
    }

    boolean added = false;
    while (!added) {
      int x = (int) (Math.random() * 3.0);
      int y = (int) (Math.random() * 3.0);
      added = board.putMark(ownMark, x, y);
    }

    return true;
  }
}
