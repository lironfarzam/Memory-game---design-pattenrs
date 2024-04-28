package model;

import java.util.Scanner;
import patterns.command.Command;
import patterns.command.FlipCommand;

/**
 * HumanPlayer class extends Player for interactive gameplay managed by a human.
 */
public class HumanPlayer extends Player {
    private Scanner scanner;

    /**
     * Constructor for HumanPlayer that initializes the player with a name and board.
     *
     * @param name  The name of the player.
     * @param board The game board used for decision making.
     */
    public HumanPlayer(String name, Board board) {
        super(name, board);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Plays a turn for the human player by processing inputs for card flipping,
     * ending the turn, or undoing the last move.
     *
     * @return TurnResult indicating the result of the turn including any card flips.
     */
    @Override
    public TurnResult playTurn() {
        int cardCounter = 2;
        int[] firstCard = null;
        int[] secondCard = null;

        System.out.println(getName() + ", enter command or coordinates (e.g., 1A, end, undo, help): ");
        while (cardCounter != 0) {
            String input = scanner.nextLine().trim().toUpperCase();

            switch (input) {
                case "END":
                    endTheGame();
                    return new TurnResult(false, null);  // End the turn without any actions
                case "UNDO":
                    undoLastMove();
                    updateUI();  // Update the UI to display the flipped card
                    return new TurnResult(false, null);  // Allow for continuation of the turn
                case "HELP":
                    displayHelp();
                    updateUI();
                    break;
                case "Z":
                    System.out.println("Undoing last move...");
                    if (firstCard != null) {
                        handleCardFlip(firstCard);
                        cardCounter++;
                        firstCard = null;
                    }
                    break;
                default:
                {
                    if (cardCounter == 2) {
                        firstCard = parseCardInput(input);
                        if (firstCard != null && handleCardFlip(firstCard)) 
                        {
                            cardCounter--;
                            System.out.println("First card selected. Enter the second card coordinates: or type 'z' to undo the last move.");
                        }
                    }
                    else if (cardCounter == 1) 
                    {
                        secondCard = parseCardInput(input);
                        if (secondCard != null && handleCardFlip(secondCard))
                        {
                            cardCounter--;
                            return new TurnResult(true, new int[]{firstCard[0], firstCard[1], secondCard[0], secondCard[1]});
                        }
                    }
                    else 
                    {
                        System.out.println("Invalid command or coordinates. Please enter again:"); // Invalid input
                    }
                }
            }
        }
        return null;  // Should not reach this point
    }

    /**
     * Undoes the last move by flipping the last two cards back over.
     */
    private void endTheGame() {
        System.out.println("Ending the game...");
        mediator.notifyGameEnd();
        
    }

    /**
     * Undoes the last move by flipping the last two cards back over.
     */
    private void displayHelp() {
        System.out.println("Enter card coordinates (e.g., 1A, 2B) to flip a card.");
        System.out.println("Type 'end' to end the game.");
        System.out.println("Type 'undo' to undo the last move.");
        System.out.println();
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    /**
     * Executes flip commands for cards selected by the player.
     *
     * @param indices Array of indices representing card positions.
     * @return TurnResult representing the outcome of the card flip.
     */
    private boolean handleCardFlip(int[] indices) {
        if (indices.length == 2) {  // Expecting two integers for row and column
            Card card = board.getCardAt(indices[0], indices[1]);
            if (card == null || card.isMatched())
            {
                updateUI();  // Update the UI to display the flipped card
                System.out.println("Invalid card coordinates. Please enter again:");
                return false;
            }
            Command flipCommand = new FlipCommand(card);
            flipCommand.execute();
            updateUI();  // Update the UI to display the flipped card
            return true;
        } else {
            System.out.println("Invalid card coordinates. Please enter again:");
            return false;
        }
    }

    /**
     * Parses input from the player for card coordinates.
     *
     * @param input Player input string.
     * @return Array of integers representing row and column indices of the card.
     */
    private int[] parseCardInput(String input) {
        if (input.matches("\\d[A-Z]")) {  // Simple regex to match patterns like 1A, 2B, etc.
            int row = Character.getNumericValue(input.charAt(0)) - 1;  // Convert to zero-based index
            int col = input.charAt(1) - 'A';
            if (board.isValidPosition(row, col) && !board.isCardFlipped(row, col)) {
                return new int[]{row, col};
            }
        }
        return null;
    }

    /**
     * Closes the scanner to release resources.
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
            scanner = null; // Help GC and prevent further use
        }
    }

    /**
     * Updates the player's score based on the score of the current turn.
     *
     * @param score the score of the current turn.
     */
    @Override
    public void update(int score) {
        addScore(score);
    }

    /**
     * Resets the player's score and name to empty strings, and sets the board and scanner to null.
     *
     * @return null
     */
    @Override
    public Object resetPlayer() {
        score = 0;
        name = "";
        board = null;
        scanner = null;
        return null;
    }


}
