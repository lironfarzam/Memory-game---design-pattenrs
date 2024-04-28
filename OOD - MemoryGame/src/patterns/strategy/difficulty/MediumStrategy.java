package patterns.strategy.difficulty;

import model.Board;
import java.util.List;
import java.util.Random;
import java.util.Arrays;


/**
 * Concrete strategy for Medium level difficulty.
 * Basic Memory Utilization: This strategy involves a basic level of memory usage.
 * It retains information about previously seen cards (flipped but not matched yet)
 * and tries to make a match if possible. If no remembered card can form a match,
 * it randomly selects a card to flip.
 * Behavior: Randomly selects a card that has been flipped previously (if available)
 * to attempt forming a pair. If no pairs can be identified from memory, it resorts
 * to flipping a card at random, similar to the EasyStrategy.
 */
public class MediumStrategy implements DifficultyStrategy {
    @Override
    public int[][] selectCards(Board board) {
        List<int[]> knownCards = board.getSeenCards();
        Random random = new Random();

        if (!knownCards.isEmpty()) {
            int index = random.nextInt(knownCards.size());
            int[] firstCard = knownCards.get(index);
            int[] secondCard;

            do {
                secondCard = knownCards.get(random.nextInt(knownCards.size()));
            } while (Arrays.equals(firstCard, secondCard));

            return new int[][]{firstCard, secondCard};
        } else {
            return selectRandomPair(board);
        }
    }

    /**
     * Selects a random pair of cards from the board.
     * @param board The game board.
     * @return A pair of randomly selected cards.
     */
    private int[][] selectRandomPair(Board board) {
        Random random = new Random();
        int firstIndex = random.nextInt(board.getCardCount());
        int secondIndex;

        do {
            secondIndex = random.nextInt(board.getCardCount());
        } while (firstIndex == secondIndex); // Ensure two different cards are selected

        return new int[][]{{firstIndex}, {secondIndex}};
    }
}
