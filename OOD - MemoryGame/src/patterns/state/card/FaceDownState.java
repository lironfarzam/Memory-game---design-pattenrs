package patterns.state.card;

import model.Card;

/**
 * Concrete state for when the card is face down.
 */
public class FaceDownState implements CardState {
    /**
     * Handles the behavior when the card is flipped from face down to face up.
     * @param card The card whose state is being handled.
     */
    @Override
    public void handleFlip(Card card) {
        // Assuming FaceUpState is another concrete state class that needs to be defined
        card.setState(new FaceUpState());
    }

    /**
     * Provides a visual representation of a face-down card.
     * @param card The card to display.
     * @return A string representing a face-down card, typically obscured.
     */
    @Override
    public String display(Card card) {
        return "[ ? ]";  // Placeholder for face-down cards
    }

    /**
     * Checks if the card is face up.
     * @return False, since the card is face down.
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
        return new FaceDownState();
    }

}
