package app;

import core.CommandLineInterface;
import core.GameManager;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static boolean playAgain = true;

    public static void main(String[] args) {
        LOGGER.info("Welcome to the Memory Card Game!");
        try {
            CommandLineInterface cli = new CommandLineInterface();

            while (playAgain) {
                GameManager gameManager = GameManager.getInstance();
                // Map<String, Object> settings = promptForGameSetup();
                Map<String, Object> settings = cli.promptForGameSetup();
                gameManager.setupGame((int) settings.get("numPlayers"),
                                      (String) settings.get("boardSize"),
                                      (int) settings.get("difficultyLevel"));
                gameManager.startGame();
                gameManager.resetGame();  // Reset the game for a new round
                // delete the game instance
                gameManager = null;
                playAgain = cli.playAgain();  
            }
            cli.close();
            LOGGER.info("Thank you for playing!");
            // RuntimeException for end of game from user input to exit.
        } catch (RuntimeException e) {
            LOGGER.log(Level.SEVERE, " ", e);
            System.out.println("Thank you for playing!");
            
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred during the game", e);
            System.out.println("An unexpected error occurred. Please try restarting the game.");
        }
    }

    /**
     * Prompts the user for game setup settings such as number of players, board size, and difficulty level.
     * For Dibagin purposes, hardcoded settings are used.
     * @return A map of the user's selected settings.
     */
    private static Map<String, Object> promptForGameSetup() {
        // For demonstration, hardcoded settings are used. You may replace this with interactive user input.
        Map<String, Object> settings = new java.util.HashMap<>();
        settings.put("numPlayers", 0);
        settings.put("boardSize", "Small");
        settings.put("difficultyLevel", 3);
        return settings;
    }
}
