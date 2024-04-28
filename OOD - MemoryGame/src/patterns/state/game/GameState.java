package patterns.state.game;

import core.Game;

/**
 * GameState interface that defines the methods for handling game states.
 */
public interface GameState {
    void handle(Game game);
}
