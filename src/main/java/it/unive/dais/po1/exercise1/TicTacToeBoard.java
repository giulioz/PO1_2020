package it.unive.dais.po1.exercise1;

/**
 * This class represents a tic tac toe board
 *
 * @since 1.0
 */
public class TicTacToeBoard {
    private final Mark[][] board;

    public TicTacToeBoard() {
        board = new Mark[3][3];
    }

    /**
     * Puts a mark in a given cell
     *
     * @param c the mark to put in the board
     * @param x the x coordinate of the cell to be filled
     * @param y the y coordinate of the cell to be filled
     * @return true if the cell was empty, the game was not ended (e.g., no winner yet) and
     * it filled it, false otherwise
     */
    public boolean put(Mark c, int x, int y) {
        if (winner() == null && getMark(x, y) == null) {
            board[x][y] = c;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the mark of a cell, null if empty
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     * @return the mark in the given cell, null if the cell is empty
     */
    public Mark getMark(int x, int y) {
        return board[x][y];
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

    /**
     * Returns the winner of the game
     *
     * @return the mark of the winner of the game, or null if there is not yet a winner
     */
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
        else if (winnerDiag2 != null)
            return winnerDiag2;

        return null;
    }

    /**
     * Returns true if the board is full
     *
     * @return true iff the board is full
     */
    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null)
                    return false;
            }
        }
        return true;
    }

}
