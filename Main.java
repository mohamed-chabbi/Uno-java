/*
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = List.of("human", "bot1", "bot2", "bot3");
        Game game = new Game(names);
        game.start();
    }
}
*/

//costume
/*
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== UNO Game Setup ===");
        
        // Ask for number of human players (1-4)
        int humanPlayers;
        do {
            System.out.print("Enter number of human players (1-4): ");
            humanPlayers = sc.nextInt();
            sc.nextLine(); // Consume newline
        } while (humanPlayers < 1 || humanPlayers > 4);
        
        // Create player list
        List<String> playerNames = new ArrayList<>();
        
        // Add human players names
        for (int i = 1; i <= humanPlayers; i++) {
            System.out.print("Enter name for Human Player " + i + ": ");
            String name = sc.nextLine().trim();
            playerNames.add(name);
        }
        
        // Add bots
        int botPlayers = 4 - humanPlayers;
        for (int i = 1; i <= botPlayers; i++) {
            playerNames.add("Bot" + i);
        }
        
        // Show configuration
        System.out.println("\nGame configuration:");
        System.out.println("Human players: " + humanPlayers);
        System.out.println("Bot players: " + botPlayers);
        System.out.println("Players: " + playerNames);
        
        // Start the game
        System.out.println("\n=== Starting Game ===");
        Game game = new Game(playerNames);
        game.start();
        
        sc.close();
    }
}
*/