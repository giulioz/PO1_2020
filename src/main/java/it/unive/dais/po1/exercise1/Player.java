package it.unive.dais.po1.exercise1;

/**
 * This class represents a player of the game
 *
 * @since 1.0
 */
public class Player {
    private final Mark mark;

    /**
     * Intializes a player using the given Mark
     * @param mark the mark of the player
     */
    public Player(Mark mark) {
        this.mark = mark;
    }

    /**
     * Plays a round of the game.
     *
     * @param board
     * @return true if the player was able to
     */
    public boolean play(TicTacToeBoard board) {
        if (board.winner() != null || board.isFull()) {
            return false;
        }

        boolean added = false;
        while (!added) {
            int x = (int)(Math.random() * 3.0);
            int y = (int)(Math.random() * 3.0);
            added = board.put(mark, x, y);
        }

        return true;
    }
}
