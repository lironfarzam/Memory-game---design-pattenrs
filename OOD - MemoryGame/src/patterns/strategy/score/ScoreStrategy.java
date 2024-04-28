package patterns.strategy.score;

import model.Player;
import model.Card;

/**
 * The ScoreStrategy interface defines the method for calculating scores.
 */
public interface ScoreStrategy {
    int updateScore(Player player, boolean isMatch, Card[] cards);
}
