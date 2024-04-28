package core;

import patterns.state.game.GameState;
import patterns.state.game.GameOverState;
import patterns.state.game.InitializingState;
import patterns.state.game.PlayingState;
import patterns.state.game.WaitingForPlayerState;

/**
 * GameStateManager class manages the state of the game and transitions between different states.
 */
public class GameStateManager {
    private GameState currentState;
    private Game game;

    /**
     * Constructs a new GameStateManager object.
     * @param game The game object to manage.
     */
    public GameStateManager(Game game) {
        this.game = game;
        this.currentState = new InitializingState(this);  // Initialize with the starting state
    }

    /**
     * Sets the current state of the game.
     * @param newState The new state to set.
     */
    public void setState(GameState newState) {
        this.currentState = newState;
        this.currentState.handle(game);
    }

    /**
     * Handles the current state of the game.
     */
    public void handleState() {
        this.currentState.handle(game);
    }

    /**
     * Transitions to the initializing state.
     */
    public void goToInitializingState() {
        setState(new InitializingState(this));
    }

    /**
     * Transitions to the playing state.
     */
    public void goToPlayingState() {
        setState(new PlayingState(this));
    }

    /**
     * Transitions to the waiting for player state.
     */
    public void goToWaitingForPlayerState() {
        setState(new WaitingForPlayerState(this));
    }

    /**
     * Transitions to the game over state.
     */
    public void goToGameOverState() {
        setState(new GameOverState(this));
    }
}
