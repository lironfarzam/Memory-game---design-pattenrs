package patterns.state.card;

import model.Card;

/**
 * The CardState interface defines the state-specific behavior for cards.
 * Includes cloning support for the states.
 */
public interface CardState {
    void handleFlip(Card card);
    String display(Card card);
    boolean isFaceUp();

    /**
     * Method to clone the current state. Implementations must provide a copy of the current state.
     * @return A new instance of the current state.
     */
    CardState clone();
}
