package patterns.state.game;

import core.Game;
import core.GameStateManager;

/**
 * PlayingState class that represents the playing state of the game.
 */
public class PlayingState implements GameState {
    private GameStateManager manager;

    /**
     * Constructs a new PlayingState object.
     * @param manager The game state manager.
     */
    public PlayingState(GameStateManager manager) {
        this.manager = manager;
    }

    /**
     * Handles the playing state of the game.
     * @param game The game object.
     */
    @Override
    public void handle(Game game) {
        try{
            if (game.isGameOver()) {
                manager.goToGameOverState();
            } else {
                game.processGameTurn();
                manager.goToWaitingForPlayerState();
            }
        }
        catch (Exception e){
            System.out.println("An error occurred while trying to handle the game.");
        }
    }
}
