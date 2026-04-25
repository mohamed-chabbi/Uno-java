import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== UNO Game Setup ===");
        
        int humanPlayers;
        do {
            System.out.print("Enter number of human players (1-4): ");
            humanPlayers = sc.nextInt();
            sc.nextLine();
        } while (humanPlayers < 1 || humanPlayers > 4);
        
        List<String> playerNames = new ArrayList<>();
        
        for (int i = 1; i <= humanPlayers; i++) {
            System.out.print("Enter name for Human Player " + i + ": ");
            String name = sc.nextLine().trim();
            playerNames.add(name);
        }
        
        int botPlayers = 4 - humanPlayers;
        for (int i = 1; i <= botPlayers; i++) {
            playerNames.add("Bot" + i);
        }
        
        System.out.println("\nGame configuration:");
        System.out.println("Human players: " + humanPlayers);
        System.out.println("Bot players: " + botPlayers);
        System.out.println("Players: " + playerNames);
        
        System.out.println("\n=== Starting Game ===");
        Game game = new Game(playerNames);
        game.start();
        
        sc.close();
    }
}