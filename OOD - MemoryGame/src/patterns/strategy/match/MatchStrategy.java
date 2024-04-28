package patterns.strategy.match;

import model.Card;

/**
 * The MatchStrategy interface defines a method for determining if two cards match.
 */
public interface MatchStrategy {
    boolean doCardsMatch(Card card1, Card card2);
}
