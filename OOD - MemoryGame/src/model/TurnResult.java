package model;

/**
 * Represents the result of a player's turn.
 */
public class TurnResult {
    private boolean successful;
    private int[] cardIndices;

    /**
     * Constructs a TurnResult with a success status and the indices of the flipped cards.
     * @param successful whether the turn was successful.
     * @param cardIndices the indices of the flipped cards.
     */
    public TurnResult(boolean successful, int[] cardIndices) {
        this.successful = successful;
        this.cardIndices = cardIndices;
    }

    /**
     * Checks if the turn was successful.
     * @return true if the turn was successful, false otherwise.
     */
    public boolean isSuccessful() {
        return successful;
    }

    /**
     * Gets the indices of the flipped cards.
     * @return the indices of the flipped cards.
     */
    public int[] getCardIndices() {
        return cardIndices;
    }
}
