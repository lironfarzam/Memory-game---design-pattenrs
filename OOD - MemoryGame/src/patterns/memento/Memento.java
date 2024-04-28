package patterns.memento;

import model.Card;
import model.Player;

/**
 * Memento class used to store the state of successfully matched cards and score changes.
 */
public class Memento {
    private Card[] matchedCards;       // Stores only matched cards
    private int scoreChange;          // Score change associated with these cards
    private Player player;            // Player who made the match

    /**
     * Constructs a Memento with matched cards, score change, and the player.
     * @param matchedCards Array of Cards that were matched.
     * @param scoreChange Score change resulting from the match.
     * @param player Player who scored the match.
     */
    public Memento(Card[] matchedCards, int scoreChange, Player player) {
        this.matchedCards = matchedCards.clone();  // Clone the array to ensure immutability
        this.scoreChange = scoreChange;
        this.player = player;
    }

    /**
     * Gets the matched cards.
     * @return Array of matched cards.
     */
    public Card[] getMatchedCards() {
        return matchedCards.clone();
    }

    /**
     * Gets the score change.
     * @return Score change associated with the match.
     */
    public int getScoreChange() {
        return scoreChange;
    }

    /**
     * Gets the player who made the match.
     * @return Player who made the match.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Revert the match effect by marking the cards as unmatched and adjusting the player's score.
     */
    public void undoMatch() {
        for (Card card : matchedCards) {
            card.setMatched(false); // Make sure this method is implemented in Card
        }
        player.addScore(-scoreChange);
    }

}
