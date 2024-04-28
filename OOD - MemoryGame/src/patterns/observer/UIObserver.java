package patterns.observer;

import ui.GameUI;


/**
 * UIObserver is responsible for updating the user interface in response to game state changes.
 */
public class UIObserver implements GameObserver {
    private GameUI gameUI;

    
    /**
     * Constructs a UIObserver with a reference to the game and game UI instances.
     * @param game The game instance.
     * @param gameUI The game UI instance.
     */
    public UIObserver(GameUI gameUI) {
        this.gameUI = gameUI;
    }

    /**
     * Updates the observer based on changes in the game.
     * This method is called whenever the observable state changes.
     */
    @Override
    public void update() {
        clearConsole();
        displayHeader();
        updateBoardDisplay();
        updateScoreDisplay();
        updateTurnDisplay();
    }

    /**
     * Updates the observer based on changes in the game.
     * This method is called whenever the observable state changes.
     */
    private void updateBoardDisplay() {
        gameUI.displayBoard();
    }

    /**
     * Updates the observer based on changes in the game.
     * This method is called whenever the observable state changes.
     */
    private void updateScoreDisplay() {
        gameUI.displayScores();
    }

    /**
     * Updates the observer based on changes in the game.
     * This method is called whenever the observable state changes.
     */
    private void updateTurnDisplay() {
        gameUI.displayTurn();
    }
    
    /**
     * Clears the console.
     */
    private void clearConsole() {
        gameUI.clearConsole();
    }

    /**
     * Displays the header.
     */
    private void displayHeader() {
        gameUI.displayHeader();
    }

    /**
     * Updates the observer based on changes in the game.
     * This method is called whenever the observable state changes.
     */
    @Override
    public void update(int score) {
        update();
    }

}
