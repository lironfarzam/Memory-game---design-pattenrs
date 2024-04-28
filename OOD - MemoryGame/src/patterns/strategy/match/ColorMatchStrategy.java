package patterns.strategy.match;

import model.Card;

/**
 * Matches cards based on their color.
 * Assuming that Card class has a method getColor() that returns the color of the card.
 */
public class ColorMatchStrategy implements MatchStrategy {
    @Override
    public boolean doCardsMatch(Card card1, Card card2) {
        return card1.getId() != card2.getId() && card1.getColor().equals(card2.getColor());
    }
}