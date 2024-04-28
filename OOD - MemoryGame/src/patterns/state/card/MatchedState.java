package patterns.state.card;

import model.Card;

/**
 * Concrete state for when the card has been matched with another card.
 */
public class MatchedState implements CardState {
    /**
     * Handles the attempt to flip a matched card. Typically, no action is taken because matched
     * cards should not be flipped back.
     * @param card The card whose state is being handled.
     */
    @Override
    public void handleFlip(Card card) {
        // Do nothing because matched cards should not flip back to face down.
    }

    /**
     * Provides a visual representation of a matched card.
     * @param card The card to display.
     * @return A string representing the card's symbol with an indication that it's matched.
     */
    @Override
    public String display(Card card) {
        return "     ";  // Show nothing for matched cards it's not here.
    }

    /**
     * Checks if the card is face up.
     * @return False, since the card is matched and not face up.
     */
    @Override
    public boolean isFaceUp() {
        return false;
    }
    
    /**
     * Clones the state of the card.
     * @return A clone of the card state.
     */
    @Override
    public CardState clone() {
        return new MatchedState();
    }
}
