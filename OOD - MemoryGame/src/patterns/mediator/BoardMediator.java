package patterns.mediator;

import core.Game;
import model.Board;
import model.Player;

/**
 * BoardMediator class that facilitates interaction between the board and its cards.
 */
public class BoardMediator {
    private Board board;
    private Game game;

    /**
     * Constructor for BoardMediator.
     * @param board The game board.
     * @param game The game instance.
     */
    public BoardMediator(Board board, Game game) {
        this.board = board;
        this.game = game;
    }
    
    /**
     * notifyScore method that updates the player's score.
     * @param score The score to update.
     * @param player The player to update.
     */
    public void notifyScore(int score, Player player) {
        player.update(score);
    }

    /**
     * notifyUI method that notifies the UI to update.
     */
    public void notifyUI() {
        game.notifyObservers();
    }

    /**
     * requestUndo method that requests an undo action from the game.
     * @param player The player requesting the undo.
     */
    public void requestUndo(Player player) {
            game.undoLastAction();
    }

    /**
     * requestHint method that requests a hint from the game.
     * @param player The player requesting the hint.
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * setBoard method that sets the game board.
     * @param board The game board.
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * getBoard method that gets the game board.
     * @return The game board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * getGame method that gets the game instance.
     * @return The game instance.
     */
    public Game getGame() {
        return game;
    }

    public void notifyGameEnd() {
        game.finishGame();
        // exit the game
        System.exit(0);
    }
    

}
