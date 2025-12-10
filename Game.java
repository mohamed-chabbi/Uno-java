import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    private List<Player> players;
    private Deck deck;
    private List<Card> discardPile;
    private Card topCard;
    private int currentPlayerIndex;
    private int direction;
    private Scanner scanner;
    private boolean gameOver;
    
    //constructuer
    public Game(List<String> playerNames) {
        this.players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        this.deck = new Deck();
        this.discardPile = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.direction = 1;
        this.scanner = new Scanner(System.in);
        this.gameOver = false;
    }
    
    public void start() {
        System.out.println("--= UNO GAME STARTS =--\n");
        
        // 7 cards to each player
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                Card card = deck.draw();
                if (card != null) {
                    player.addCard(card);
                }
            }
        }
        
        //first card
        topCard = deck.draw();
        discardPile.add(topCard);
        System.out.println("Starting card: " + topCard + "\n");
        
        // Main game loop
        while (!gameOver) {
            playTurn();
        }
        
        scanner.close();
    }
    
    private void playTurn() {
        Player currentPlayer = players.get(currentPlayerIndex);
        
        System.out.println("\n-- " + currentPlayer.getName() + "'s Turn --");
        System.out.println("Top card: " + topCard);
        System.out.println("Your hand (" + currentPlayer.getHandSize() + " cards):");
        displayHand(currentPlayer);
        
        // Check if player has playable cards
        List<Card> playableCards = currentPlayer.getPlayableCards(topCard);
        
        if (playableCards.isEmpty()) {
            // Player must draw
            System.out.println("No playable cards. Drawing a card...");
            Card drawnCard = drawCard();
            if (drawnCard != null) {
                currentPlayer.addCard(drawnCard);
                System.out.println("Drew: " + drawnCard);
                
                // Check if drawn card can be played
                if (drawnCard.canPlay(topCard)) {
                    System.out.println("You can play the drawn card! Play it? (yes/no)");
                    String response = scanner.nextLine().trim().toLowerCase();
                    if (response.equals("yes") || response.equals("y")) {
                        playCard(currentPlayer, drawnCard);
                        return;
                    }
                }
            }
            moveToNextPlayer();
            return;
        }
        
        // Player has playable cards
        System.out.println("\nPlayable cards:");
        for (int i = 0; i < playableCards.size(); i++) {
            System.out.println((i + 1) + ". " + playableCards.get(i));
        }
        
        System.out.print("Choose a card to play (1-" + playableCards.size() + "): ");
        int choice = getValidChoice(1, playableCards.size());
        Card chosenCard = playableCards.get(choice - 1);
        
        playCard(currentPlayer, chosenCard);
    }
    
    private void playCard(Player player, Card card) {
        player.removeCard(card);
        discardPile.add(card);
        topCard = card;
        
        System.out.println(player.getName() + " plays: " + card);
        
        // Check for UNO
        if (player.getHandSize() == 1) {
            System.out.print("Say UNO! (type 'uno'): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("uno")) {
                player.sayUno();
                System.out.println(player.getName() + " says UNO!");
            } else {
                System.out.println(player.getName() + " forgot to say UNO! Drawing 2 penalty cards.");
                forceDraw(player, 2);
            }
        }
        
        // Check for win
        if (player.getHandSize() == 0) {
            System.out.println("\n=== " + player.getName() + " WINS! ===");
            gameOver = true;
            return;
        }
        
        // Apply card effect
        card.applyEffect(this);
        
        moveToNextPlayer();
    }
    
    private void displayHand(Player player) {
        List<Card> hand = player.getHand();
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ". " + hand.get(i));
        }
    }
    
    private int getValidChoice(int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                int choice = Integer.parseInt(input);
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.print("Invalid choice. Enter " + min + "-" + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a number: ");
            }
        }
    }
    
    public Color chooseColor() {
        System.out.println("Choose a color:");
        System.out.println("1. RED");
        System.out.println("2. GREEN");
        System.out.println("3. BLUE");
        System.out.println("4. YELLOW");
        System.out.print("Your choice (1-4): ");
        
        int choice = getValidChoice(1, 4);
        
        switch (choice) {
            case 1: return Color.RED;
            case 2: return Color.GREEN;
            case 3: return Color.BLUE;
            case 4: return Color.YELLOW;
            default: return Color.RED;
        }
    }
    
    public void skipNextPlayer() {
        System.out.println("Next player is skipped!");
        moveToNextPlayer(); // Skip happens by moving twice
    }
    
    public void reverse() {
        direction *= -1;
        System.out.println("Direction reversed!");
    }
    
    public void forceDraw(Player player, int count) {
    for (int i = 0; i < count; i++) {
        Card card = drawCard();
        if (card != null) {
            player.addCard(card);
        }
    }
}

private Card drawCard() {
    // Check if deck is empty
    if (deck.isEmpty()) {
        System.out.println("Deck is empty. Reshuffling discard pile...");
        reshuffleDiscardPile();
    }
    
    return deck.draw();
}

private void reshuffleDiscardPile() {
    if (discardPile.size() <= 1) {
        System.out.println("Warning: Not enough cards to reshuffle!");
        return;
    }
    
    // Keep the top card, reshuffle the rest
    Card top = discardPile.remove(discardPile.size() - 1);
    deck.addCards(new ArrayList<>(discardPile));
    discardPile.clear();
    discardPile.add(top);
}

public Player getNextPlayer() {
    int nextIndex = (currentPlayerIndex + direction + players.size()) % players.size();
    return players.get(nextIndex);
}

private void moveToNextPlayer() {
    currentPlayerIndex = (currentPlayerIndex + direction + players.size()) % players.size();
}
}