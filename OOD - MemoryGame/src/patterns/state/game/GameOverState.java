package patterns.state.game;

import core.Game;
import core.GameStateManager;

/**
 * The game over state.
 */
public class GameOverState implements GameState {
    private GameStateManager manager;

    /**
     * Creates a new game over state.
     * @param manager The game state manager.
     */
    public GameOverState(GameStateManager manager) {
        this.manager = manager;
    }

    /**
     * Handles the game over state.
     * @param game The game.
     */
    @Override
    public void handle(Game game) {
        try{
            System.out.println("Game over! Displaying results...");
            game.displayResults();
        }
        catch (Exception e){
            System.out.println("An error occurred while trying to determine the winner.");
        }
    }
}