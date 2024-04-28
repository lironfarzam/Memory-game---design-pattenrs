package patterns.strategy.difficulty;

import model.Board;

/**
 * The DifficultyStrategy interface defines the method for the computer player's strategy.
 * It returns an array of two integer arrays, each containing the index of a card to flip.
 */
public interface DifficultyStrategy {
    int[][] selectCards(Board board);
}
