import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private List<Player> players;
    private Deck deck;
    private Card topCard;
    private List<Card> discardPile;
    private int currentPlayerIndex;
    private boolean clockwise;
    private boolean gameOver;
    private Scanner scanner;
    
    public Game(List<String> playerNames) {
        this.players = new ArrayList<>();
        this.deck = new Deck();
        this.discardPile = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.clockwise = true;
        this.gameOver = false;
        this.currentPlayerIndex = 0;
        
        for (int i = 0; i < playerNames.size(); i++) {
            boolean isBot = (i != 0);
            players.add(new Player(playerNames.get(i), isBot));
        }
    }
    public void start() {
        System.out.println("=== UNO Game ===");
        
        dealCards();
        initializeDiscardPile();
        
        while (!gameOver) {
            Player currentPlayer = players.get(currentPlayerIndex);
            takeTurn(currentPlayer);
            
            if (currentPlayer.getHandSize() == 0) {
                gameOver = true;
                System.out.println("\n🎉 " + currentPlayer.getName() + " WINS! ");
                break;
            }
            
            moveToNextPlayer();
        }
        
        scanner.close();
        showFinalHands();
    }
    
    private void dealCards() {
        System.out.println("Dealing 7 cards to each player...");
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                Card card = drawCard();
                if (card != null) {
                    player.addCard(card);
                }
            }
        }
    }
    
    private void initializeDiscardPile() {
    do {
        topCard = drawCard();
    } while (topCard != null && topCard.getType() != CardType.NUMBER);
    
    discardPile.add(topCard);
    System.out.println("First card: " + topCard);
}
    
    private void takeTurn(Player player) {
        System.out.println("\n========================================");
        System.out.println("Top card: " + topCard);
        System.out.println(player.getName() + " turn");
        
        if (!player.hasPlayableCard(topCard)) {
            System.out.println("No playable card. Drawing one card...");
            Card drawnCard = drawCard();
            if (drawnCard != null) {
                player.addCard(drawnCard);
                System.out.println("Drew: " + drawnCard);
                
                if (drawnCard.canPlay(topCard)) {
                    System.out.println("Playing drawn card...");
                    playCard(player, drawnCard);
                }
            }
            return;
        }
        
        if (player.isBot()) {
            botTurn(player);
        } else {
            humanTurn(player);
        }
    }
    
    private void humanTurn(Player player) {
        player.showHand();
        List<Card> playableCards = player.getPlayableCards(topCard);
        
        System.out.println("\nPlayable cards:");
        for (int i = 0; i < playableCards.size(); i++) {
            System.out.println("[" + i + "] " + playableCards.get(i));
        }
        
        System.out.print("\nChoose a card (index): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        Card chosenCard = playableCards.get(choice);
        playCard(player, chosenCard);
        
        if (player.getHandSize() == 1) {
            System.out.print("Say UNO? (y/n): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("y")) {
                player.sayUno();
            }
        }
    }
    
    private void botTurn(Player player) {
        List<Card> playableCards = player.getPlayableCards(topCard);
        
        if (!playableCards.isEmpty()) {
            Card cardToPlay = playableCards.get(0);
            System.out.println(player.getName() + " plays: " + cardToPlay);
            playCard(player, cardToPlay);
            
            if (player.getHandSize() == 1) {
                player.sayUno();
            }
        }
    }
    
    private void playCard(Player player, Card card) {
        player.removeCard(card);
        discardPile.add(card);
        topCard = card;
        
        card.applyEffect(this);
        
        System.out.println("New top: " + topCard);
    }
    
    private Card drawCard() {
        Card drawn = deck.draw();
        
        if (drawn == null && discardPile.size() > 1) {
            reshuffleDeckFromDiscard();
            drawn = deck.draw();
        }
        
        return drawn;
    }
    
    private void reshuffleDeckFromDiscard() {
        if (discardPile.size() <= 1) {
            return;
        }
        
        Card currentTop = discardPile.remove(discardPile.size() - 1);
        
        List<Card> cardsToReshuffle = new ArrayList<>(discardPile);
        deck.addCards(cardsToReshuffle);
        
        discardPile.clear();
        discardPile.add(currentTop);
        topCard = currentTop;
        
        System.out.println("Reshuffled discard pile");
    }
    
    private void moveToNextPlayer() {
        if (clockwise) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        } else {
            currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        }
    }
    
    public void skipNextPlayer() {
        System.out.println("Skip");
        moveToNextPlayer();
    }
    
    public void reverse() {
        clockwise = !clockwise;
        System.out.println("Reverse");
    }
    
    public void forceDraw(Player player, int numberOfCards) {
        System.out.println(player.getName() + " draws " + numberOfCards + " cards");
        for (int i = 0; i < numberOfCards; i++) {
            Card drawnCard = drawCard();
            if (drawnCard != null) {
                player.addCard(drawnCard);
            }
        }
    }
    
    public Player getNextPlayer() {
        int nextIndex = clockwise ? 
            (currentPlayerIndex + 1) % players.size() : 
            (currentPlayerIndex - 1 + players.size()) % players.size();
        return players.get(nextIndex);
    }
    
    public Color chooseColor() {
    Player currentPlayer = players.get(currentPlayerIndex);
    
    if (currentPlayer.isBot()) {
        return Color.RED;
    }
    System.out.println("\nChoose color:");
    System.out.println("1. RED   2. GREEN   3. BLUE   4. YELLOW");
    
    int choice;
        System.out.print("Choice (1-4): ");
        choice = scanner.nextInt();
        scanner.nextLine();

    return switch (choice) {
        case 1 -> Color.RED;
        case 2 -> Color.GREEN;
        case 3 -> Color.BLUE;
        case 4 -> Color.YELLOW;
        default -> Color.RED;
    };
}
    
    private void showFinalHands() {
        System.out.println("\n=== Game Over ===");
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getHandSize() + " cards");
        }
    }
}