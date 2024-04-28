package patterns.observer;

import java.util.List;
import model.Player;

/**
 * Concrete observer for updating game-related information such as scores.
 */
public class ScoreObserver implements GameObserver {
    private List<Player> players;

    /**
        * Constructs a ScoreObserver with a list of players to observe.
        * @param players The list of players to observe.
        */
    public ScoreObserver(List<Player> players) {
        this.players = players;
    }

    /**
     * Updates the observer based on changes in the players' scores.
     * This method is called whenever the observable state changes.
     */
    @Override   
    public void update(int x) {
        displayScores();
    }

    /**
     * Displays the updated scores of all players.
     */
    private void displayScores() {
        System.out.println("Updated Scores:");
        players.forEach(player -> System.out.println(player.getName() + ": " + player.getScore()));
    }

    /**
     * Updates the observer based on changes in the game.
     * This method is called whenever the observable state changes.
     */
    @Override
    public void update() {
        displayScores();
    }
}
