package it.unive.dais.po1.web.games;

import it.unive.dais.po1.exercise2.Board;
import it.unive.dais.po1.exercise2.Mark;

public class TicTacToeBoard extends Board {
  public TicTacToeBoard() {
    super(3);
  }

  private Mark checkDirection(int x, int y, int dx, int dy) {
    Mark mark = getMark(x, y);

    for (; x >= 0 && x < 3 && y >= 0 && y < 3; x += dx, y += dy) {
      if (getMark(x, y) != mark) {
        mark = null;
      }
    }

    return mark;
  }

  @Override
  public Mark winner() {
    for (int i = 0; i < 3; i++) {
      Mark winnerColumn = checkDirection(i, 0, 0, 1);
      Mark winnerRow = checkDirection(0, i, 1, 0);
      if (winnerColumn != null)
        return winnerColumn;
      else if (winnerRow != null)
        return winnerRow;
    }

    Mark winnerDiag1 = checkDirection(0, 0, 1, 1);
    Mark winnerDiag2 = checkDirection(2, 0, -1, 1);
    if (winnerDiag1 != null)
      return winnerDiag1;
    else return winnerDiag2;
  }
}
