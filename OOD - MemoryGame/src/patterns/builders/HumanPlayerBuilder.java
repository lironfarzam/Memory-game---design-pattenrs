package patterns.builders;

import model.Board;
import model.HumanPlayer;

/**
 * Concrete builder class for creating HumanPlayer instances.
 */
public class HumanPlayerBuilder implements PlayerBuilder {
    private String name;
    private Board board;

    /**
     * Sets the name of the player.
     * @param name The name of the player.
     * @return The builder instance.
     */
    @Override
    public PlayerBuilder setName(String name) {
        this.name = name;
        return this;
    }


    /**
     * Sets the game board for the player.
     * @param board The game board.
     * @return The builder instance.
     */
    @Override
    public PlayerBuilder setBoard(Board board) {
        this.board = board;
        return this;
    }

    /**
     * Builds a new HumanPlayer instance based on the provided parameters.
     * @return A new HumanPlayer instance.
     */
    @Override
    public HumanPlayer build() {
        return new HumanPlayer(name,board);
    }


}


