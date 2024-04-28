package patterns.command;

import model.Card;

/**
 * FlipCommand class that encapsulates the action of flipping a card.
 */
public class FlipCommand implements Command {
    private Card card;
    private boolean wasFaceUpBefore;  // To remember the initial state for undo purposes

    /**
     * Constructs a FlipCommand for a specific card.
     * @param card the card that will be flipped by this command.
     */
    public FlipCommand(Card card) {
        this.card = card;
        this.wasFaceUpBefore = card.isFaceUp();
    }

    /**
     * Executes the command to flip the card.
     * This method flips the card to the opposite of its current state unless it is matched.
     */
    @Override
    public void execute() {
        if (!card.isMatched()) {  // Prevent flipping if the card is already matched
            card.flip();
        }
    }

    /**
     * Undoes the flip command, restoring the card to its prior state.
     * This action is only relevant if the card was not matched after being flipped.
     */
    @Override
    public void undo() {
        if (card.isFaceUp() != wasFaceUpBefore) {  // Check if the state has changed since execution
            card.flip();
        }
    }
}
