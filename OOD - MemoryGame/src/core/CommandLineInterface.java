package core;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Handles game instructions and processes user commands in a console-based game.
 */
public class CommandLineInterface implements AutoCloseable {
    private Scanner scanner;

    /**
     * Constructs a new CommandLineInterface object.
     */
    public CommandLineInterface() {
        scanner = new Scanner(System.in);
    }

    /**
     * Display the welcome message and game instructions.
     */
    public String getUserCommand() {
        System.out.print("Enter command: ");
        return scanner.nextLine().trim();
    }
    
    /**
     * Prompt user for game settings and initialize the game based on user input.
     * This method includes user interaction for choosing the number of players,
     * the board size, and the difficulty level.
     * @return An array of integers representing the number of players, board size, and difficulty level.
     */
    public Map<String, Object> promptForGameSetup(){
        int numPlayers = promptForInt("Please enter the number of players (1 or 2):", 0, 2);
        // int numPlayers = 1; // Default to single player
        String[] validSizes = {"Small", "Medium", "Large"};
        String boardSize = promptForString("Choose board size (Small, Medium, Large):", validSizes);
        // String boardSize = "Medium"; // Default board size
        int difficultyLevel = 1; // Default difficulty for single player
        if (numPlayers <= 1) {
            difficultyLevel = promptForInt("Enter computer difficulty level (1-Easy, 2-Medium, 3-Hard):", 1, 3);
            // difficultyLevel = 1; // Default difficulty for single player
        }

        // Return the game settings as a map
        Map<String, Object> settings = new HashMap<>();
        settings.put("numPlayers", numPlayers);
        settings.put("boardSize", boardSize);
        settings.put("difficultyLevel", difficultyLevel);

        return settings;
    }

    /**
     * Prompts the user for an integer within a specified range.
     * @param message The message to display to the user.
     * @param min The minimum value allowed.
     * @param max The maximum value allowed.
     * @return The integer value entered by the user.
     */
    private int promptForInt(String message, int min, int max) {
        System.out.println(message);
        String input;
        int number;
        while (true) {
            input = scanner.nextLine();
            try {
                number = Integer.parseInt(input);
                if (number >= min && number <= max) {
                    return number;
                }
            } catch (NumberFormatException e) {
                // Log error or handle it accordingly
            }
            System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ":");
        }
    }

    /**
     * Prompts the user for a string input from a list of valid values.
     * @param message The message to display to the user.
     * @param validValues An array of valid string values.
     * @return The string value entered by the user.
     */
    private String promptForString(String message, String[] validValues) {
        System.out.println(message);
        String input;
        while (true) {
            input = scanner.nextLine().trim();
            for (String validValue : validValues) {
                if (validValue.equalsIgnoreCase(input)) {
                    return input;
                }
            }
            System.out.println("Invalid input. Valid options are: " + String.join(", ", validValues));
        }
    }

    /**
     * Prompts the user to play again.
     * @return True if the user wants to play again, false otherwise.
     */
    public boolean playAgain() {
        System.out.print("Play again? (Y/N): ");
        String input = scanner.nextLine().trim().toLowerCase();
        Boolean playAgain = input.equals("y") || input.equals("yes");
        return playAgain;
    }

    /**
     * Closes the scanner to release system resources.
     */
    @Override
    public void close() {
        scanner.close();
    }
}
