package patterns.strategy.match;

import model.Card;

/**
 * Matches cards based on both symbol and color for a stricter match criteria.
 */
public class FullMatchStrategy implements MatchStrategy {
    @Override
    public boolean doCardsMatch(Card card1, Card card2) {
        return  card1.getId() != card2.getId() &&
                card1.getSymbol().equals(card2.getSymbol()) &&
                card1.getColor().equals(card2.getColor()) && 
                card1.getNumber() == card2.getNumber();
    }
}