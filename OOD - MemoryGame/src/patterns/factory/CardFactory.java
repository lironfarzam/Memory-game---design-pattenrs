package patterns.factory;

import model.Card;
import patterns.builders.CardBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * CardFactory class to handle the creation of cards for the memory card game using the Prototype pattern.
 */
public class CardFactory {
    private int cardIdCounter = 1;  // Ensure unique IDs for each card

    /**
     * Creates pairs of matching cards, each pair consisting of an original and a clone with unique IDs.
     * @param numberOfPairs Number of pairs of cards to create.
     * @param numbers Array of card numbers (characters).
     * @param symbols Array of symbols for the cards.
     * @param colors Array of colors of the cards.
     * @return List of cards with each pair having unique but identical cards.
     */
     public List<Card> createPairsOfCards(int numberOfPairs) {
        final char[] numbers = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
        final String[] symbols = {"♠", "♥", "♣", "♦"};
        final String[] colors = {"Black", "Red", "Black", "Red"};  // Colors corresponding to suits: spades, hearts, clubs, diamonds

        List<Card> cards = new ArrayList<>();
        CardBuilder builder = new CardBuilder();

        // Loop through as many times as needed to create the required number of pairs
        int pairCount = 0;
        while (pairCount < numberOfPairs) {
            for (int i = 0; i < symbols.length && pairCount < numberOfPairs; i++) {
                for (int j = 0; j < numbers.length && pairCount < numberOfPairs; j++) {
                    builder.setId(cardIdCounter++)
                            .setNumber(numbers[j])
                            .setSymbol(symbols[i])
                            .setColor(colors[i]);

                    Card original = builder.build();
                    Card clone = original.clone(); // Create a matching card using the clone method
                    clone.setId(cardIdCounter++); // Ensure the clone has a unique ID

                    cards.add(original);
                    cards.add(clone);
                    pairCount++;
                }
            }
        }

        return cards;
    }
}