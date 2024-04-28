package model;

import patterns.state.card.CardState;
import patterns.state.card.FaceDownState;
// import patterns.state.card.FaceUpState;
import patterns.state.card.MatchedState;

/**
 * Represents a card in the memory card game.
 */
public class Card implements Cloneable{
    private int id; // Unique identifier for matching logic
    private final char number; // Number on the card (1-9, A, J, Q, K as characters)
    private final String symbol; // Symbol or image on the card
    private final String color; // Color of the number (red or black)
    private CardState state; // Current state of the card
    private boolean isMatched; // Whether the card has been matched
    private boolean isSeen; // Whether the card has been seen

    /**
     * Constructs a card with a unique identifier, number, symbol, and color.
     * @param id the unique identifier used for matching cards.
     * @param symbol the symbol or image displayed on the card.
     * @param number the number on the card.
     * @param color the color of the number on the card.
     */
    public Card(int id, char number, String symbol, String color) {
        this.id = id;
        this.number = number;
        this.symbol = symbol;
        this.color = color;
        this.state = new FaceDownState();
        this.isMatched = false;
        this.isSeen = false;
    }

    /**
     * Clones the card object.
     * @return a cloned copy of the card.
     */
    @Override
    public Card clone() {
        try {
            Card copy = (Card) super.clone();
            copy.state = this.state.clone();
            // ID and matched status are reset as they should be unique to each instance.
            copy.isMatched = false;
            copy.isSeen = false; // Reset visibility for cloned cards.
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Clone not supported", e); // Can never happen if Cloneable is implemented
        }
    }

    // Setter for ID since ID should be unique and not cloned.
    public void setId(int id) {
        this.id = id;
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    /**
     * Flips the card to reveal its face.
     */
    public void flip() {
        this.state.handleFlip(this);
        this.isSeen = true;
    }

    /**
     * Sets the state of the card.
     * @param newState the new state of the card.
     */
    public void setState(CardState newState) {
        this.state = newState;
    }

    /**
     * Displays the card based on its current state.
     * @return the string representation of the card.
     */
    public String display() {
        return state.display(this);
    }

    /**
     * Checks if the card is a match.
     * @param otherCard the other card to compare.
     * @return true if the cards match, false otherwise.
     */
    public boolean isMatched() {
        return isMatched;
    }

    /**
     * Sets the matched status of the card.
     * @param matched the new matched status of the card.
     */
    public void setMatched(boolean matched) {
        this.isMatched = matched;
        if(matched)
        {
            setState(new MatchedState());
        }
        else
        {
            setState(new FaceDownState());
        }
    }

    /**
     * Checks if the card is a match.
     * @param otherCard the other card to compare.
     * @return true if the cards match, false otherwise.
     */
    public char getNumber() {
        return number;
    }

    /**
     * Gets the symbol on the card.
     * @return the symbol on the card.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Gets the color of the number on the card.
     * @return the color of the number on the card.
     */
    public String getColor() {
        return color;
    }

    /**
     * Checks if the card has been seen.
     * @return true if the card has been seen, false otherwise.
     */
    public boolean isSeen() {
        return isSeen;
    }
    
    /*
    * is face up or not based on the state
    * Return true if the card is face up, false otherwise.
    */
    public boolean isFaceUp() {
        return state.isFaceUp();
    }
}
