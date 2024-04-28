package ui;

import core.Game;
import model.Card;
import model.Player;

import java.util.List;
import java.util.Scanner;

/**
 * GameUI is responsible for handling user input and displaying the game state.
 */
public class GameUI {
    private static GameUI instance; // Singleton instance
    private Game game;
    private Scanner scanner;
    private boolean waitForEnter = true;

    /**
     * Constructs a GameUI with a reference to the game instance.
     * @param game The game instance.
     */
    private GameUI(Game game, boolean waitForEnter) {
        this.game = game;
        this.scanner = new Scanner(System.in);
        this.waitForEnter = waitForEnter;
    }

    /**
     * Provides access to the singleton instance of GameUI.
     * @param game The game instance to be used for UI interaction.
     * @return The singleton instance of GameUI.
     */
    public static GameUI getInstance(Game game, boolean waitForEnter) {
        if (instance == null) {
            instance = new GameUI(game, waitForEnter);
        }
        else {
            instance.game = game;
            instance.waitForEnter = waitForEnter;
        }
        return instance;
    }

    /**
     * Displays the welcome message and instructions for the game.
     */
    public void displayWelcomeMessage() {
        System.out.println("Welcome to Memory Card Game!");
        System.out.println();
        promptEnterToContinue();
        displayInstructions();
    }

    public void displayInstructions() {
        System.out.println("Instructions:");
        System.out.println();
        System.out.println("1. The game board contains a grid of face-down cards.");
        System.out.println("2. Players take turns to flip two cards.");
        System.out.println("3. If the two cards match, the player earns points.");
        System.out.println("4. If the two cards do not match, the player loses his/her turn.");
        System.out.println("5. The game ends when all cards have been matched.");
        System.out.println("6. The player with the highest score wins the game.");
        System.out.println();
        promptEnterToContinue();
    }


    /**
     * Displays the game board with face-down cards.
     */
    public void displayBoard() {
        game.getBoard().displayBoard();
    }

    /**
     * Displays the current scores of all players.
     */
    public void displayTurn() {
        System.out.println("It is now " + game.getCurrentPlayer().getName() + "'s turn.");
    }


    /**
     * Displays the result of flipping two cards.
     * @param card1 The first card flipped.
     * @param card2 The second card flipped.
     * @param isMatch Whether the two cards are a match.
     */
    public void displayFlipResult(Card card1, Card card2, boolean isMatch) {
        System.out.println("Flipped cards:");
        System.out.println("Card 1: " + card1.display() + " Card 2: " + card2.display());
        if (isMatch) {
            System.out.println("Congratulations! You found a match!");
        } else {
            System.out.println("Sorry, the cards do not match.");
        }
        if (waitForEnter) {
            promptEnterToContinue();
        }
    }

    /**
     * Displays the updated scores of all players sorted by score.
     * get all the players and sort them by score
     * then print the player name and score
     */
    public void displayScores() {
        List<Player> players = game.getPlayers();
        players.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()))
                .forEach(p -> System.out.println(p.getName() + ": " + p.getScore() + " points"));
    }
    
    /**
     * Displays the memory game ASCII art.
     */
    public void displayMemoryGame() {
        clearConsole();
        memoryGame();
        promptEnterToContinue();   
    }

    /**
     * Displays the memory game ASCII art.
     */
    public void memoryGame() {
        String header = "_____  ______   _______   _____  ______  ________  ________      ___    ___      ________  ________  _____  ______  _______      ";
        String line1 = "|\\   _ \\  _   \\|\\  ___ \\ |\\   _ \\  _   \\|\\   __  \\|\\   __  \\    |\\  \\  /  /|    |\\   ____\\|\\   __  \\|\\   _ \\  _   \\|\\  ___ \\     ";
        String line2 = "\\ \\  \\\\\\__\\ \\  \\ \\   __/|\\ \\  \\\\\\__\\ \\  \\ \\  \\|\\  \\ \\  \\|\\  \\   \\ \\  \\/  / /    \\ \\  \\___|\\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\ \\   __/|    ";
        String line3 = " \\ \\  \\\\|__| \\  \\ \\  \\_|/_\\ \\  \\\\|__| \\  \\ \\  \\\\\\  \\ \\   _  _\\   \\ \\    / /      \\ \\  \\  __\\ \\   __  \\ \\  \\\\|__| \\  \\ \\  \\_|/__  ";
        String line4 = "  \\ \\  \\    \\ \\  \\ \\  \\_|\\ \\ \\  \\    \\ \\  \\ \\  \\\\\\  \\ \\  \\\\  \\|   \\/  /  /        \\ \\  \\|\\  \\ \\  \\ \\  \\ \\  \\    \\ \\  \\ \\  \\_|\\ \\ ";
        String line5 = "   \\ \\__\\    \\ \\__\\ \\_______\\ \\__\\    \\ \\__\\ \\_______\\ \\__\\\\ _\\ __/  / /           \\ \\_______\\ \\__\\ \\__\\ \\__\\    \\ \\__\\ \\_______\\";
        String line6 = "    \\|__|     \\|__|\\|_______|\\|__|     \\|__|\\|_______|\\|__|\\|__|\\___/ /             \\|_______|\\|__|\\|__|\\|__|     \\|__|\\|_______|";
        String line7 = "                                                               \\|___|/                                                            ";
        
        clearConsole();
        System.out.println(header);
        System.out.println(line1);
        System.out.println(line2);
        System.out.println(line3);
        System.out.println(line4);
        System.out.println(line5);
        System.out.println(line6);
        System.out.println(line7);
    }
    

    /**
     * Displays the game header.
     */
    public void displayHeader() {
        String line0 = "    __  __  ____  __  __  _____  ____  _  _     ___    __    __  __  ____ ";
        String line1 = "   (  \\/  )( ___)(  \\/  )(  _  )(  _ \\( \\/ )   / __)  /__\\  (  \\/  )( ___)";
        String line2 = "    )    (  )__)  )    (  )(_)(  )   / \\  /   ( (_-. /(__)\\  )    (  )__) ";
        String line3 = "   (_/\\/\\_)(____)(_/\\/\\_)(_____)(_)\\_) (__)    \\___/(__)(__)(_/\\/\\_)(____)";
        System.out.println(line0);
        System.out.println(line1);
        System.out.println(line2);
        System.out.println(line3);
        System.out.println("   ------------------------------------------------------------------------");
        System.out.println();
        System.out.println();
        
    }
    
    /**
     * Displays the winner of the game.
     */
    public void displayWinner() {
        Player winner = game.determineWinner();
        if (winner == null) {
            System.out.println("It's a tie! No winner this time.");
        } else {
            System.out.println("Congratulations, " + winner.getName() + "! You are the winner!");
            System.out.println();
            // ASCII art for the frame of the winner
            System.out.println("  ___________");
            System.out.println(" '._==_==_=_.");
            System.out.println(".-\\:      /-.");
            System.out.println("| (|:.     |) |");
            System.out.println(" '-|:.     |-'");
            System.out.println("   \\::.    /");
            System.out.println("    '::. .'");
            System.out.println("      ) (");
            System.out.println("    _.' '._");
            System.out.println("   `\"\"\"\"\"\"`");
        }
    }

    /**
     * Ends the game and displays the final scores and winner.
     * This method is called when the game is over.
     */
    public void endGame() {
        clearConsole();
        memoryGame();
        printGameOver();
        displayScores();
        displayWinner();
    }

    /**
     * Prints the game over message.
     */
    public void printGameOver() {
        //     ________                        ________                     
        //     /  _____/_____    _____   ____   \_____  \___  __ ___________ 
        //    /   \  ___\__  \  /     \_/ __ \   /   |   \  \/ // __ \_  __ \
        //    \    \_\  \/ __ \|  Y Y  \  ___/  /    |    \   /\  ___/|  | \/
        //     \______  (____  /__|_|  /\___  > \_______  /\_/  \___  >__|   
        //            \/     \/      \/     \/          \/          \/       

        System.out.println(" ________                        ________                     ");
        System.out.println("/  _____/_____    _____   ____   \\_____  \\___  __ ___________ ");
        System.out.println("/   \\  ___\\__  \\  /     \\_/ __ \\   /   |   \\  \\/ // __ \\_  __ \\");
        System.out.println("\\    \\_\\  \\/ __ \\|  Y Y  \\  ___/  /   /_\\   \\   /\\  ___/|  | \\/");
        System.out.println(" \\______  (____  /__|_|  /\\___  > \\_______  /\\_/  \\___  >__|   ");
        System.out.println("        \\/     \\/      \\/     \\/          \\/          \\/       ");
        System.out.println();

        // .------..------..------..------.     .------..------..------..------.
        // |G.--. ||A.--. ||M.--. ||E.--. |.-.  |O.--. ||V.--. ||E.--. ||R.--. |
        // | :/\: || (\/) || (\/) || (\/) ((5)) | :/\: || :(): || (\/) || :(): |
        // | :\/: || :\/: || :\/: || :\/: |'-.-.| :\/: || ()() || :\/: || ()() |
        // | '--'G|| '--'A|| '--'M|| '--'E| ((1)) '--'O|| '--'V|| '--'E|| '--'R|
        // `------'`------'`------'`------'  '-'`------'`------'`------'`------'

        // System.out.println(".------..------..------..------.     .------..------..------..------.");
        // System.out.println("|G.--. ||A.--. ||M.--. ||E.--. |.-.  |O.--. ||V.--. ||E.--. ||R.--. |");
        // System.out.println("| :/\\: || (\\/) || (\\/) || (\\/) ((5)) | :/\\: || :(): || (\\/) || :(): |");
        // System.out.println("| :\\/: || :\\/: || :\\/: || :\\/: |'-.-.| :\\/: || ()() || :\\/: || ()() |");
        // System.out.println("| '--'G|| '--'A|| '--'M|| '--'E| ((1)) '--'O|| '--'V|| '--'E|| '--'R|");
        // System.out.println("`------'`------'`------'`------'  '-'`------'`------'`------'`------'");
    }

    /**
     * Prompts the user to press Enter to continue.
     */
    public void promptEnterToContinue() {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    /**
     * Closes the scanner used for user input.
     */
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }

    /**
     * Clears the console screen based on the operating system.
     */
    public void clearConsole() {
        try {
            String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Clear console for Unix-based operating systems
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Error clearing console: " + e.getMessage());
        }
    }
}
