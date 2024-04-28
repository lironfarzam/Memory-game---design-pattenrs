package core;

import model.Board;
import model.Player;
import model.TurnResult;
import patterns.mediator.BoardMediator;
import patterns.memento.Caretaker;
import patterns.state.game.GameState;
import patterns.strategy.match.MatchStrategy;
import patterns.strategy.score.ScoreStrategy;
import ui.GameUI;

import java.util.ArrayList;
import java.util.List;
import model.Card;
import patterns.command.Command;
import patterns.command.FlipCommand;
import patterns.factory.CardFactory;
import patterns.factory.PlayerFactory;
import patterns.memento.Memento;
import patterns.observer.GameObserver;

import java.util.logging.Logger;

/**
 * Manages the memory card game logic and state using the State and Strategy patterns.
 * Supports dynamic player configurations and different matching strategies.
 */
public class Game {
    private Board board;
    private List<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;
    private GameStateManager stateManager;
    private MatchStrategy matchStrategy;
    private ScoreStrategy scoreStrategy;
    private BoardMediator mediator;
    private Caretaker caretaker = new Caretaker();
    private static final Logger LOGGER = Logger.getLogger(GameManager.class.getName());
    private List<GameObserver> observers = new ArrayList<>();

    private GameUI gameUI;

    /**
     * Constructs a Game instance with specified settings.
     * @param numPlayers Number of players in the game.
     * @param boardSize Size of the game board (Small, Medium, Large).
     * @param difficulty Computer player difficulty (if applicable).
     * @param matchStrategy The matching strategy to use.
     */
    public Game(int numPlayers, String boardSize, int difficulty, MatchStrategy matchStrategy, ScoreStrategy scoreStrategy) {
        this.matchStrategy = matchStrategy;
        this.scoreStrategy = scoreStrategy;
        this.mediator = new BoardMediator(null, this); // Board is null initially
        this.stateManager = new GameStateManager(this);
        initializeGame(numPlayers, boardSize, difficulty, matchStrategy);
    }

    /**
     * Initializes the game with the specified settings.
     * @param numPlayers Number of players in the game.
     * @param boardSize Size of the game board (Small, Medium, Large).
     * @param difficulty Computer player difficulty (if applicable).
     * @param matchStrategy The matching strategy to use.
     */
    private void initializeGame(int numPlayers, String boardSize, int difficulty, MatchStrategy matchStrategy) {
        int numberOfPairs = determinePairs(boardSize);
        this.board = new Board(numberOfPairs, matchStrategy);
        this.board.setupBoard(new CardFactory());
        this.board.displayBoard();
        this.mediator.setBoard(this.board); // Update mediator with the initialized board
        initializePlayers(numPlayers, difficulty);
        this.gameUI = GameUI.getInstance(this,numPlayers > 0 ? true : false);
    }

    /**
     * Resets the game for a new round.
     */
    public void resetGame() {
        // Reset all components necessary for a new game
        if (board != null) {
            board.resetBoard();
        }
        if (players != null) {
            players.forEach(Player::resetPlayer);
        }
        if (observers != null) {
            observers.clear();
        }
        
        board = null;
        players.clear();
        players = new ArrayList<>();
        currentPlayerIndex = 0;
        
        LOGGER.info("Game has been reset.");
    }

    /**
     * Initializes players for the game using the Builder pattern for flexible player creation.
     * @param numPlayers Number of players in the game.
     * @param difficulty Computer player difficulty (if applicable).
     */
    private int determinePairs(String size) {
        return switch (size.toLowerCase()) {
            case "small" -> 13;
            case "medium" -> 26;
            case "large" -> 52;
            default -> 52;
        };
    }

    /**
     * Initializes players for the game using the Builder pattern for flexible player creation.
     * @param numPlayers Number of players in the game.
     * @param difficulty Computer player difficulty (if applicable).
     */
    private void initializePlayers(int numPlayers, int difficulty) {
        for (int i = 0; i < numPlayers; i++) {
            Player player = PlayerFactory.createPlayer("human", "Player " + (i + 1), board, 0);
            player.setMediator(mediator);
            players.add(player);
        }
        for (int i = 0; i < 2 - numPlayers; i++) {
            Player player = PlayerFactory.createPlayer("computer", "Computer " + (i + 1), board, difficulty);
            player.setMediator(mediator);
            players.add(player);
        }
    }
    

    /**
     * Switches to the next player in the game.
     */
    public void switchPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        // LOGGER.info("Now it's " + getCurrentPlayer().getName() + "'s turn.");
    }

    /**
     * Returns the current player.
     *
     * @return The current player object.
     */
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    /**
     * Sets the current state of the game.
     *
     * @param newState The new state to be set.
     */
    public void setState(GameState newState) {
        this.stateManager.setState(newState);
    }

    /**
     * Adds an observer to the game.
     *
     * @param observer The observer to add.
     */
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }
    
    /**
     * Notifies all observers of the game.
     */
    public void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.update();
        }
    }

    /**
     * Removes an observer from the game.
     *
     * @param observer The observer to remove.
     */
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    /**
     * Determines the winner of the game based on scores and announces the result.
     */
    public Player determineWinner() {
        Player winner = players.stream().max((p1, p2) -> Integer.compare(p1.getScore(), p2.getScore())).orElse(null);
        return winner;
    }

    /**
     * Gets the game board.
     *
     * @return The game board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Sets the scoring strategy for the game.
     *
     * @param strategy The scoring strategy to use.
     */
    public void setScoreStrategy(ScoreStrategy strategy) {
        this.scoreStrategy = strategy;
    }

    /**
     * Updates the score of the current player based on whether a match was found.
     *
     * @param isMatch Whether the match was successful.
     * @param cards   The cards involved in the attempt.
     */
    public void updateScore(boolean isMatch, Card[] cards) {
        Player currentPlayer = getCurrentPlayer();
        int score = scoreStrategy.updateScore(currentPlayer, isMatch, cards);
        mediator.notifyScore(score,currentPlayer);
        if (isMatch){
            for (Card card : cards) card.setMatched(true);
            caretaker.saveMemento(new Memento(cards, score, currentPlayer));
        } 
    }
    
    /**
     * Ends the game and announces the winner.
     */
    public void finishGame() {
        stateManager.goToGameOverState();
    }

    /**
     * Checks if the game is over based on the state of the board.
     *
     * @return true if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        return board.isAllMatched();
    }

    /**
     * Starts the game and initializes the game state.
     */
    public void startGame() {
        System.out.println("Let's begin!");
        notifyObservers();
        stateManager.goToPlayingState();
    }

    /**
     * Processes the current player's turn by flipping cards and checking for matches.
     */
    public void processGameTurn() {
        if (isGameOver()) {
            finishGame();
            return;
        }

        Player currentPlayer = getCurrentPlayer();
        TurnResult result = currentPlayer.playTurn();

        // Check if turn resulted in a card flip action
        if (result.isSuccessful() && result.getCardIndices() != null && result.getCardIndices().length == 4) {
            int[] cardIndices = result.getCardIndices();

            // Retrieve the cards from the board
            Card card1 = board.getCardAt(cardIndices[0], cardIndices[1]);
            Card card2 = board.getCardAt(cardIndices[2], cardIndices[3]);

            // Check if the two selected cards match
            boolean isMatch = matchStrategy.doCardsMatch(card1, card2);

            gameUI.displayFlipResult(card1, card2, isMatch);

            if (isMatch) {
                // LOGGER.info("Match found by " + currentPlayer.getName());
                updateScore(isMatch, new Card[]{card1, card2});
                card1.setMatched(true);
                card2.setMatched(true);
            } else {
                // LOGGER.info("No match found. Next player's turn.");
                Command flip1 = new FlipCommand(card1);
                Command flip2 = new FlipCommand(card2);
                flip1.execute();
                flip2.execute();
                switchPlayer();
            }
            notifyObservers();

            if (isGameOver()) {
                finishGame();
            } else {
                // LOGGER.info(currentPlayer.getName() + " completed their turn.");
            }
        } else {
            // Handle other outcomes such as undo or end turn without card flips
            if (result.getCardIndices() == null) {
                // LOGGER.info("No cards were flipped during " + currentPlayer.getName() + "'s turn.");
                
            }
        }
    }


    /**
     * Flips a card at the specified row and column on the board.
     *
     * @param row The row index of the card.
     * @param col The column index of the card.
     */
    public void undoLastAction() {
        Memento lastState = caretaker.restoreMemento();
        if (lastState != null) {
            lastState.undoMatch();  // This reverts the last action
            LOGGER.info("Last action undone. Reverted score and card match states.");
        } else {
            LOGGER.info("No more actions to undo.");
        }
    }
    /**
     * Gets the list of players in the game.
     *
     * @return The list of players.
     */
    public List<Player> getPlayers() {
        return players;
    }

    public void displayResults() {
        gameUI.endGame();
    }
}
