package patterns.strategy.score;

import model.Player;
import model.Card;


/**
 * Penalty-based scoring strategy subtracts points for incorrect matches.
 */
public class PenaltyScoreStrategy implements ScoreStrategy {
    private static final int MATCH_POINTS = 10;  // Points awarded for a match
    private static final int PENALTY_POINTS = -5;  // Points subtracted for a wrong match

    @Override
    public int updateScore(Player player, boolean isMatch, Card[] cards) {
        if (isMatch) {
            return MATCH_POINTS;
        } else {
            return PENALTY_POINTS;
        }
    }
}