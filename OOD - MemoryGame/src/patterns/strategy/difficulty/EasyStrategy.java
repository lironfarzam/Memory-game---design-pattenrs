package patterns.strategy.difficulty;

import model.Board;
import java.util.Random;

/**
 * Concrete strategy for Easy level difficulty.
 * Random Moves: This strategy performs the simplest action by randomly selecting any
 * card on the board to flip. It does not remember previous card positions and makes 
 * decisions purely on chance without attempting to match cards based on memory.
 * Behavior: Flips a single card at random coordinates each turn without
 * any attempt to find a match based on previous flips.
 */
public class EasyStrategy implements DifficultyStrategy {
    @Override
    public int[][] selectCards(Board board) {
        Random random = new Random();
        int firstIndex = random.nextInt(board.getCardCount());
        int secondIndex;

        do {
            secondIndex = random.nextInt(board.getCardCount());
        } while (firstIndex == secondIndex); // Ensure two different cards are selected

        return new int[][]{{firstIndex}, {secondIndex}};
    }
}
