package patterns.state.card;

import model.Card;

/**
 * Concrete state for when the card is face up.
 */
public class FaceUpState implements CardState {
    /**
     * Handles the behavior when the card is flipped from face up to face down.
     * @param card The card whose state is being handled.
     */
    @Override
    public void handleFlip(Card card) {
        // Change the card's state back to FaceDownState
        card.setState(new FaceDownState());
    }

    /**
     * Provides a visual representation of a face-up card.
     * @param card The card to display.
     * @return A string representing the card's symbol, which is visible when the card is face up.
     */
    @Override
    public String display(Card card) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_BLACK = "\u001B[30m";
        String colotToPrint = card.getColor().equals("Red") ? ANSI_RED : ANSI_BLACK;
        return "[" + colotToPrint + card.getSymbol() + " " + card.getNumber() + ANSI_RESET + "]";  // Di
    }

    /**
     * Checks if the card is face up.
     * @return True, since the card is face up.
     */
    @Override
    public boolean isFaceUp() {
        return true;
    }

    /**
     * Clones the state of the card.
     * @return A clone of the card state.
     */
    @Override
    public CardState clone() {
        return new FaceUpState();
    }
}
