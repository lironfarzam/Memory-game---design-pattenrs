package patterns.builders;

import model.Card;

/**
 * CardBuilder class for constructing Card objects in a step-by-step process.
 */
public class CardBuilder {
    private int id;
    private char number;
    private String symbol;
    private String color;

    /**
     * Constructs a CardBuilder with default values.
     */
    public CardBuilder setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Sets the number of the card.
     * @param number the number of the card.
     * @return the CardBuilder object.
     */
    public CardBuilder setNumber(char number) {
        this.number = number;
        return this;
    }

    /**
     * Sets the symbol of the card.
     * @param symbol the symbol of the card.
     * @return the CardBuilder object.
     */
    public CardBuilder setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    /**
     * Sets the color of the card.
     * @param color the color of the card.
     * @return the CardBuilder object.
     */
    public CardBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    /**
     * Builds the Card object with the specified attributes.
     * @return the constructed Card object.
     */
    public Card build() {
        if (id <= 0 || number == '0' || symbol.isEmpty()) {
            throw new IllegalStateException("Card must have a valid id, number, and symbol.");
        }
        return new Card(id, number, symbol, color);
    }
}
