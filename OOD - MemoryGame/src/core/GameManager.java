package core;

import patterns.strategy.match.MatchStrategy;
import patterns.strategy.score.ScoreStrategy;
import patterns.strategy.score.SimpleScoreStrategy;
import ui.GameUI;
import patterns.observer.UIObserver;
import patterns.strategy.match.FullMatchStrategy;

import java.util.List;
import java.util.logging.Logger;

import model.Board;
import model.Player;

/**
 * GameManager class that manages the state and flow of the game.
 * Implements Singleton pattern to ensure there is only one instance of GameManager.
 */
public class GameManager {
    private static GameManager instance;
    private Game currentGame;
    private boolean gameRunning;
    private static final Logger LOGGER = Logger.getLogger(GameManager.class.getName());
    MatchStrategy matchStrategy = new FullMatchStrategy(); // Consider injecting this dependency
    ScoreStrategy scoreStrategy = new SimpleScoreStrategy(); // Consider injecting this dependency
    GameUI gameUI;
    UIObserver uiObserver;


    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private GameManager() {
        this.gameRunning = false;
    }

    /**
     * Provides access to the singleton instance of GameManager.
     * @return The singleton instance of GameManager.
     */
    public static synchronized GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    /**
     * Sets up the game with the provided configuration parameters.
     * @param numPlayers Number of players in the game.
     * @param boardSize Size of the game board (Small, Medium, Large).
     * @param difficulty Computer player difficulty (if applicable).
     */
    public void setupGame(int numPlayers, String boardSize, int difficulty) {
        try {
            currentGame = new Game(numPlayers, boardSize, difficulty, matchStrategy, scoreStrategy);
            this.gameUI = GameUI.getInstance(currentGame,numPlayers > 0 ? true : false);
            this.uiObserver = new UIObserver(gameUI);
            currentGame.addObserver(uiObserver);
            LOGGER.info("Game setup complete.");
            gameUI.displayMemoryGame();

        } catch (IllegalArgumentException e) {
            LOGGER.severe("Failed to setup game: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Starts the game and maintains the game loop.
     */
    public void startGame() {
        if (currentGame == null) {
            LOGGER.warning("Game setup is not complete. Please set up the game first.");
            return;
        }
        this.gameRunning = true;
        gameUI.displayWelcomeMessage();
        LOGGER.info("Game started.");
        currentGame.startGame();
    }

    /**
     * Resets the game state.
     */
    public void resetGame() {
        if (currentGame != null) {
            currentGame.resetGame();  // Ensure the current game resets its state
            
        }
        currentGame = null;  // Allow for garbage collection
        LOGGER.info("Game state has been reset.");
    }

    /**
     * Ends the game and performs any necessary cleanup.
     */
    public void endGame() {
        this.gameRunning = false;
        if (currentGame != null) {
            currentGame.finishGame();
        }
        resetGame();
        LOGGER.info("Game has ended.");
    }

    /**
     * Checks if the game is currently running.
     * @return true if the game is running, false otherwise.
     */
    public boolean isGameRunning() {
        return this.gameRunning;
    }

    /**
     * Checks if the game is over.
     * @return true if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        return currentGame.isGameOver();
    }

    /**
     * Gets the current game board.
     * @return The current game board.
     */
    public Board getBoard() {
        return currentGame.getBoard();
    }

    /**
     * Gets the list of players in the current game.
     * @return The list of players in the current game.
     */
    public List<Player> getPlayers() {
        return currentGame.getPlayers();
    }

    /**
     * Gets the current game instance.
     * @return The current game instance.
     */
    public Game getCurrentGame() {
        return currentGame;
    }

    /**
     * Adds a UI observer to the game.
     * @param uiObserver The UI observer to add.
     */
    public void addObserver(UIObserver uiObserver) {
        currentGame.addObserver(uiObserver);
    }


}
