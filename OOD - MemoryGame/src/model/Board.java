package model;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

import patterns.factory.CardFactory;
import patterns.strategy.match.MatchStrategy;

/**
 * Board class that manages the cards on the game board.
 */
public class Board {
    private List<Card> cards;
    private int numberOfPairs;
    private int rows;
    private int cols;
    private MatchStrategy matchStrategy;
    
    /**
     * Constructor for the Board class.
     * @param numberOfPairs Number of card pairs.
     */
    public Board(int numberOfPairs, MatchStrategy matchStrategy) {
        this.cards = new ArrayList<>();
        this.numberOfPairs = numberOfPairs;
        this.cols = 13;
        this.rows = (numberOfPairs * 2) / cols;
        this.matchStrategy = matchStrategy;
    }

    /**
     * Sets up the game board with a specified number of pairs using a card factory.
     * @param cardFactory Factory to create card objects.
     */
    public void setupBoard(CardFactory cardFactory) {
        this.cards.clear(); // Clear existing cards if any
        this.cards = cardFactory.createPairsOfCards(numberOfPairs); // Create pairs of cards
        Collections.shuffle(this.cards); // Shuffle the cards to randomize the board layout
    }

    /**
     * Resets the board by clearing all cards.
     */
    public void resetBoard() {
        this.cards.clear(); // Clear existing cards
    }
    

    /**
     * Returns whether all cards have been matched, indicating the game is over.
     * @return true if all cards are matched, false otherwise.
     */
    public boolean isAllMatched() {
        return cards.stream().allMatch(Card::isMatched);
    }

    /**
     * Displays the board state for debugging or command-line interaction purposes.
     */
    public void displayBoard() {
        System.out.print("  ");
        
        for (int col = 0; col < cols; ++col) {
            System.out.printf("   %c  ", 'A' + col);
        }
        System.out.println("\n");

        for (int row = 0; row < rows; ++row) {
            System.out.printf("%d ", row + 1);
            for (int col = 0; col < cols; ++col) {
                Card card = getCardAt(row, col);
                System.out.printf(" " + card.display());
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Remaining pairs: " + numberOfPairs);
        System.out.println();
    }

    /**
     * Gets the card at the specified index.
     * @param index The index of the card to retrieve.
     * @return The card at the specified index, or null if index is out of bounds.
     */
    public Card getCardAt(int index) {
        if (index >= 0 && index < cards.size()) {
            return cards.get(index);
        }
        return null;
    }

    /**
     * Gets the number of rows on the board.
     * @return The number of rows.
     */
    public int getRowsSize() {
        return rows;
    }

    /**
     * Gets the number of columns on the board.
     * @return The number of columns.
     */
    public int getColsSize() {
        return cols;
    }

    /**
     * Retrieves a list of indices of cards that have been seen but not yet matched.
     * @return List of indices of seen but unmatched cards.
     */
    public List<int[]> getSeenCards() {
        List<int[]> seenCards = new ArrayList<>();
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).isSeen() && !cards.get(i).isMatched()) {
                seenCards.add(new int[]{i / cols, i % cols}); // Store as row and column index
            }
        }
        return seenCards;
    }

        /**
     * Provides a list of cards that have not been seen and are not matched.
     * @return A list of card indices that are still in play.
     */
    public List<int[]> getUnseenAndUnmatchedCardIndices() {
        List<int[]> validCards = new ArrayList<>();
        for (int index = 0; index < cards.size(); index++) {
            Card card = cards.get(index);
            if (!card.isSeen() && !card.isMatched()) {
                int row = index / cols;
                int col = index % cols;
                validCards.add(new int[]{row, col});
            }
        }
        return validCards;
    }

    /**
     * Flips the card at the specified row and column.
     * @param row The row of the card to flip.
     * @param col The column of the card to flip.
     * @return true if the card was successfully flipped, false otherwise.
     */
    public boolean flipCard(int row, int col) {
        int index = row * cols + col;
        if (index >= 0 && index < cards.size()) {
            cards.get(index).flip();
            return true;
        }
        return false;
    }

    /**
     * Gets the card at the specified row and column.
     * @param row The row of the card to retrieve.
     * @param col The column of the card to retrieve.
     * @return The card at the specified row and column, or null if the index is out of bounds.
     */
    public Card getCardAt(int row, int col) {
        return getCardAt(row * cols + col);
    }

    /**
     * Determines if two specified cards are a potential match.
     * @param firstCard Coordinates of the first card (row, col).
     * @param secondCard Coordinates of the second card (row, col).
     * @return true if the two cards match, false otherwise.
     */
    public boolean isPotentialMatch(int[] firstCard, int[] secondCard) {

        Card card1 = getCardAt(firstCard[0], firstCard[1]);
        Card card2 = getCardAt(secondCard[0], secondCard[1]);
        return card1 != null && card2 != null &&
                     !card1.isMatched() && !card2.isMatched() &&
                     card1.getId() != card2.getId() &&
                     matchStrategy.doCardsMatch(card1, card2);
    }

    /**
     * Reduces the number of pairs by one when a match is found.
     */
    public void decreaseNumPairs() {
        numberOfPairs--;
    }

    /**
     * Checks the number of remaining pairs.
     * @return Number of remaining pairs.
     */
    public int getNumPairs() {
        return numberOfPairs;
    }

    /**
     * Returns the total count of cards on the board.
     * @return The total number of cards.
     */
    public int getCardCount() {
        return cards.size();
    }

    /**
     * Checks if the specified position is valid on the board.
     * @param row The row index.
     * @param col The column index.
     * @return true if the position is valid, false otherwise.
     */
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /**
     * Checks if the card at the specified position is face up.
     * @param row The row index.
     * @param col The column index.
     * @return true if the card is face up, false otherwise.
     */
    public boolean isCardFlipped(int row, int col) {
        return getCardAt(row, col).isFaceUp();
    }
}
