package patterns.state.game;

import core.Game;
import core.GameStateManager;

/**
 * InitializingState class that represents the initializing state of the game.
 */
public class InitializingState implements GameState {
    private GameStateManager manager;

    /**
     * Constructs a new InitializingState object.
     * @param manager The game state manager.
     */
    public InitializingState(GameStateManager manager) {
        this.manager = manager;
    }

    /**
     * Handles the initializing state of the game.
     * @param game The game object.
     */
    @Override
    public void handle(Game game) {
        try {
            System.out.println("Game initialized and ready to play.");
            manager.goToWaitingForPlayerState();  // Transition to the next state
        } catch (Exception e) {
            System.err.println("Failed to initialize game: " + e.getMessage());
        }
    }
}
