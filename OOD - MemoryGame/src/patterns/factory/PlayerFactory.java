package patterns.factory;

import model.Board;
import model.Player;
import patterns.builders.ComputerPlayerBuilder;
import patterns.builders.PlayerBuilder;
import patterns.builders.HumanPlayerBuilder;

/**
 * Factory class for creating Player instances.
 */
public class PlayerFactory {
    /**
     * Creates a new Player instance based on the provided parameters.
     * @param type The type of player to create (Human or Computer
     * @param name The name of the player.
     * @param board The game board.
     * @param difficulty The difficulty level of the computer player (if applicable).
     * @return A new Player instance.
     */
    public static Player createPlayer(String type, String name, Board board, int difficulty) {
        PlayerBuilder builder;
        switch (type.toLowerCase()) {
            case "human":
                builder = new HumanPlayerBuilder().setBoard(board).setName(name);
                break;
            case "computer":
                builder = new ComputerPlayerBuilder().setBoard(board).setName(name).setDifficulty(difficulty);
                break;
            default:
                throw new IllegalArgumentException("Unknown player type: " + type);
        }
        return builder.build();
    }
}