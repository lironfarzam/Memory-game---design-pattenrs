package core;

import java.util.Scanner;

/**
 * GameConfigurator handles the initial configuration of the game based on user input.
 */
public class GameConfigurator {

    private Scanner scanner;

    /**
     * Constructs a new GameConfigurator object.
     */
    public GameConfigurator() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Configures and starts a new game based on user input.
     */
    public void configureGame() {
        System.out.println("Welcome to the Memory Card Game!");

        // Determine the number of players and type of game.
        int numPlayers = getNumberOfPlayers();
        String boardSize = getBoardSize();
        int difficulty = -1;

        if (numPlayers != 2) {
            difficulty = getComputerDifficulty();
        }

        // Setup the game with the collected configuration
        GameManager gameManager = GameManager.getInstance();
        gameManager.setupGame(numPlayers, boardSize, difficulty);
        gameManager.startGame();
    }

    /**
     * Gets the number of players from the user.
     */
    private int getNumberOfPlayers() {
        System.out.println("Select the number of players:");
        System.out.println("0 - Computer vs. Computer");
        System.out.println("1 - One player vs. Computer");
        System.out.println("2 - Player vs. Player");
        int choice = scanner.nextInt();
        while (choice < 0 || choice > 2) {
            System.out.println("Invalid choice, please choose 0, 1, or 2.");
            choice = scanner.nextInt();
        }
        return choice;
    }

    /**
     * Gets the desired board size from the user.
     */
    private String getBoardSize() {
        System.out.println("Select the board size:");
        System.out.println("Small - 13 pairs (Leaf symbol only)");
        System.out.println("Medium - 26 pairs (Face and Heart)");
        System.out.println("Large - 52 pairs (All 4 symbols)");
        System.out.print("Enter choice (Small, Medium, Large): ");
        String size = scanner.next();
        while (!size.equalsIgnoreCase("Small") && !size.equalsIgnoreCase("Medium") && !size.equalsIgnoreCase("Large")) {
            System.out.println("Invalid choice, please choose Small, Medium, or Large.");
            size = scanner.next();
        }
        return size;
    }

    /**
     * Gets the computer difficulty level if there's a computer player.
     */
    private int getComputerDifficulty() {
        System.out.println("Select the computer difficulty level:");
        System.out.println("1 - Easy");
        System.out.println("2 - Medium");
        System.out.println("3 - Hard");
        int level = scanner.nextInt();
        while (level < 1 || level > 3) {
            System.out.println("Invalid choice, please choose 1, 2, or 3.");
            level = scanner.nextInt();
        }
        return level;
    }
}
