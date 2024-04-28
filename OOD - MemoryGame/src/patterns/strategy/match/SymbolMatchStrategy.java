package patterns.strategy.match;

import model.Card;

/**
 * Matches cards based on their symbol.
 */
public class SymbolMatchStrategy implements MatchStrategy {
    @Override
    public boolean doCardsMatch(Card card1, Card card2) {
        return card1.getId() != card2.getId() && card1.getSymbol().equals(card2.getSymbol());
    }
}