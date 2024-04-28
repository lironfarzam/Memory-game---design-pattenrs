package patterns.builders;

import model.Board;
import model.ComputerPlayer;
import patterns.strategy.difficulty.DifficultyStrategy;
import patterns.strategy.difficulty.EasyStrategy;
import patterns.strategy.difficulty.HardStrategy;
import patterns.strategy.difficulty.MediumStrategy;


/**
 * ComputerPlayerBuilder class for constructing ComputerPlayer objects in a step-by-step process.
 */
public class ComputerPlayerBuilder implements ComputerPlayerBuilderinterface {
    private String name;
    private DifficultyStrategy strategy;
    private Board board;

    /**
     * Constructs a ComputerPlayerBuilder with default values.
     */
    @Override
    public ComputerPlayerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the difficulty level of the computer player.
     * @param difficulty the difficulty level of the computer player.
     * @return the ComputerPlayerBuilder object.
     */
    public ComputerPlayerBuilder setDifficulty(int difficulty) {
        switch (difficulty) {
            case 1:
                this.strategy = new EasyStrategy();
                break;
            case 2:
                this.strategy = new MediumStrategy();
                break;
            case 3:
                this.strategy = new HardStrategy();
                break;
            default:
                this.strategy = new EasyStrategy(); // Default to easy if difficulty is unknown
        }
        return this;
    }

    /**
     * Sets the game board for the computer player.
     * @param board the game board.
     * @return the ComputerPlayerBuilder object.
     */
    @Override
    public ComputerPlayerBuilder setBoard(Board board) {
        this.board = board;
        return this;
    }

    /**
     * Builds the ComputerPlayer object with the specified attributes.
     * @return the constructed ComputerPlayer object.
     */
    @Override
    public ComputerPlayer build() {
        return new ComputerPlayer(name, strategy, board);
    }
}
