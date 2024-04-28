package patterns.state.game;

import core.Game;
import core.GameStateManager;

/**
 * WaitingForPlayerState class that represents the waiting for player state of the game.
 */
public class WaitingForPlayerState implements GameState {
    private GameStateManager manager;

    public WaitingForPlayerState(GameStateManager manager) {
        this.manager = manager;
    }

    /**
     * Handles the waiting for player state of the game.
     * @param game The game object.
     */
    @Override
    public void handle(Game game) {
        try{
            if (game.isGameOver()) {
                manager.goToGameOverState();
            } else {
                // game.switchPlayer();
                manager.goToPlayingState();
            }
        }
        catch (Exception e){
            System.out.println("An error occurred while trying to handle the game.");
        }
    }
}
