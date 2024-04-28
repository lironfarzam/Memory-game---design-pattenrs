package patterns.strategy.difficulty;

import model.Board;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

/**
 * Concrete strategy for Hard level difficulty.
 * Advanced Memory and Strategic Moves: This is the most complex and "intelligent" strategy.
 * It involves maintaining a complete memory of all cards that have been flipped and strategically
 * choosing cards that maximize the chances of making a match. It tries to utilize the known
 * positions of all cards to form pairs deliberately rather than randomly.
 * Behavior: First, it checks if there are any immediate matches available from the known
 * flipped cards. If no matches are found, it uses the memory of all seen cards to strategically
 * select cards that might form a match based on a more complex set of rules. If all else fails,
 * it flips a card by strategically choosing locations that might help in future turns, rather
 * than random selection.
 */
public class HardStrategy implements DifficultyStrategy {

    Random random = new Random();

    @Override
    public int[][] selectCards(Board board) {
        List<int[]> seenCards = board.getSeenCards();

        // try fined pairs in seen
        for (int[] firstCard : seenCards) {
            for (int[] secondCard : seenCards) {
                if (!Arrays.equals(firstCard, secondCard) && board.isPotentialMatch(firstCard, secondCard)) {
                    return new int[][]{firstCard, secondCard};
                }
            }
        }
        // try flip 1 random and cheak is the second is seen
        List<int[]> validCards = board.getUnseenAndUnmatchedCardIndices();

        // If no seen pairs found, attempt to find a pair using unseen cards
        if (!validCards.isEmpty()) 
        {
            int[] firstCard = validCards.get(random.nextInt(validCards.size()));
            for (int[] secondCard : seenCards) {
                if (board.isPotentialMatch(firstCard, secondCard)) {
                   return new int[][]{firstCard, secondCard};
                }
            }
        }
        // If no pairs found, flip a random card different from the last one
        int[] firstCard = validCards.get(random.nextInt(validCards.size()));
        int[] secondCard = validCards.get(random.nextInt(validCards.size()));
        while (Arrays.equals(firstCard, secondCard)) {
            secondCard = validCards.get(random.nextInt(validCards.size()));
        }
        return new int[][]{firstCard, secondCard};
    }
}
