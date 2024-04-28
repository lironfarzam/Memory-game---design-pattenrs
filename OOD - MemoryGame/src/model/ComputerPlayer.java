package model;

import patterns.strategy.difficulty.DifficultyStrategy;
import patterns.command.Command;
import patterns.command.FlipCommand;


/**
 * ComputerPlayer class extends Player to include AI functionalities, allowing dynamic difficulty adjustments.
 */
public class ComputerPlayer extends Player {
    private DifficultyStrategy strategy;

    /**
     * Constructor for ComputerPlayer that sets the difficulty level and initializes the player with a name and board.
     * @param name The name of the player.
     * @param difficulty The difficulty level of the computer player.
     * @param board The game board used for decision making.
     */
    public ComputerPlayer(String name, DifficultyStrategy strategy, Board board) {
        super(name, board); // Ensure we're calling the super constructor correctly
        this.strategy = strategy;
    }



    /**
     * Performs a turn for the computer player using its strategy.
     * This method should ideally handle the entire turn logic, including card flipping and checking for matches.
     * @return the indices of the two cards flipped during the turn if successful, otherwise null.
     */
    @Override
    public TurnResult playTurn() {
        if (board.isAllMatched()) {
            System.out.println("All cards have been matched. Game over.");
            return null;
        }

        int[][] cardPairs = strategy.selectCards(board);

        if (cardPairs != null && cardPairs.length == 2) {
            int[] firstCard = cardPairs[0];
            int[] secondCard = cardPairs[1];

            Command flip1 = new FlipCommand(board.getCardAt(firstCard[0], firstCard[1]));
            Command flip2 = new FlipCommand(board.getCardAt(secondCard[0], secondCard[1]));

            updateUI();  // Update the UI to display the flipped card
            waitingEffect(1);  // Pause for visualization

            flip1.execute();  // Execute the first flip
            updateUI();  // Update the UI to display the flipped card
            waitingEffect(1);  // Pause for visualization
            
            flip2.execute();  // Execute the second flip
            updateUI();  // Update the UI to display the flipped card
            waitingEffect(1);  // Pause for visualization


                return new TurnResult(true, new int[] {firstCard[0], firstCard[1], secondCard[0], secondCard[1]});
        }

        System.out.println("Strategy failed to select valid card pairs.");
        return null;
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
     * Pauses the game for a specified number of seconds to visualize the computer player's turn.
     * @param seconds the number of seconds to pause the game.
     */
    private void waitingEffect (int seconds) {
        try {
            Thread.sleep(seconds * 1000);  // Pause for visualization
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Handle thread interruption
        }
    }

    /**
     * Resets the player's score, name, board, and strategy.
     * @return null
     */
    @Override
    public Object resetPlayer() {
        score = 0;
        name = "";
        board = null;
        strategy = null;
        return null;
    }
}
