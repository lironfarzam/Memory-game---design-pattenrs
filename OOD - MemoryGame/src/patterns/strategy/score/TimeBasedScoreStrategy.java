package patterns.strategy.score;

import model.Player;
import model.Card;

/**
 * Time-based scoring strategy awards more points for quicker matches.
 */
public class TimeBasedScoreStrategy implements ScoreStrategy {
    private static final int BASE_POINTS = 5;  // Base points for a match
    private long startTime;

    /**
     * Constructs a TimeBasedScoreStrategy object.
     */
    public TimeBasedScoreStrategy() {
        this.startTime = System.currentTimeMillis();
    }

    /**
     * Updates the player's score based on the result of the turn.
     * @param player the player whose score is being updated.
     * @param isMatch whether the turn resulted in a match.
     * @param cards the cards that were flipped in the turn.
     * @return the points to add to the player's score.
     */
    @Override
    public int updateScore(Player player, boolean isMatch, Card[] cards) {
        if (isMatch) {
            long currentTime = System.currentTimeMillis();
            // Reduce points as time increases, quicker matches score higher
            long timeTaken = currentTime - startTime;
            int scoreToAdd = (int) (BASE_POINTS * 1000 / (timeTaken + 100));  // Normalize scoring
            startTime = currentTime; 
            return scoreToAdd;
             // Reset start time for the next turn
        }
        return 0;
    }
}
