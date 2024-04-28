package patterns.strategy.score;

import model.Player;
import model.Card;


/**
 * Simple scoring strategy awards points for each match.
 */
public class SimpleScoreStrategy implements ScoreStrategy {
    private static final int MATCH_POINTS = 10;  // Points awarded for a match

    @Override
    public int updateScore(Player player, boolean isMatch, Card[] cards) {
        if (isMatch) {
             return MATCH_POINTS;
        } else {
            return 0;
        }
    }
}
