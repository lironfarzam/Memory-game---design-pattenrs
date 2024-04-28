package model;

import patterns.mediator.BoardMediator;
import patterns.observer.GameObserver;


/**
 * Player class represents a player in the memory card game.
 */
public abstract class Player implements GameObserver{
    protected String name;
    protected int score;
    protected Board board;  // Reference to the game board
    protected BoardMediator mediator;


    /**
     * Constructor for Player class.
     * @param name the name of the player.
     * @param board the game board.
     */
    public Player(String name, Board board) {
        this.name = name;
        this.score = 0;
        this.board = board;
    }

    /**
     * Plays a turn for the player. Should be implemented by each subclass.
     * @return the result of the turn.
     */
    public abstract TurnResult playTurn();


    /**
     * Adds points to the player's score.
     * @param points the points to add.
     */
    public void addScore(int points) {
        this.score += points;
    }

    /**
     * Gets the player's score.
     * @return the player's score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the player's name.
     * @return the player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the player's score based on the score of the current turn.
     * @param score the score of the current turn.
     */
    public void update(int score) {
        addScore(score);
    }

    /**
     * Updates the UI.
     */
    public void updateUI() {
        mediator.notifyUI();
    }

    /**
     * Pauses the game for a specified duration.
     * @param seconds the duration to pause in seconds.
     */
    public void setMediator(BoardMediator mediator) {
        this.mediator = mediator;
    }

    /**
     * Pauses the game for a specified number of seconds to visualize the player's turn.
     * @param seconds the number of seconds to pause the game.
     */
    public void undoLastMove() {
        mediator.requestUndo(this);
    }

    /**
     * Pauses the game for a specified number of seconds to visualize the player's turn.
     * @param seconds the number of seconds to pause the game.
     */
    public void update() {
        // Do nothing
    }

    /**
     * Resets the player's state.
     */
    public abstract Object resetPlayer();
    
}
