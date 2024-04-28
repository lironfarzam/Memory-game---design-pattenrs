package patterns.observer;


/**
 * Interface for observers in the memory card game.
 */
public interface GameObserver {
    /**
     * Update the observer based on changes in the game.
     */
    void update(int score);

    /**
     * Update the observer based on changes in the game.
     */
    void update();
}