import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;
    
    public Deck() {
        cards = new ArrayList<>();
        createDeck();
    }
    
    // Create UNO deck
    private void createDeck() {
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
        
        // Number cards
        for (Color color : colors) {
            // One 0 card per color
            cards.add(new NumberCard(color, 0));
            
            // Two of each number 1-9 per color
            for (int n = 1; n <= 9; n++) {
                cards.add(new NumberCard(color, n));
                cards.add(new NumberCard(color, n));
            }
        }
        
        // Action cards
        for (Color color : colors) {
            for (int i = 0; i < 2; i++) {
                cards.add(new ActionCard(color, ActionType.SKIP));
                cards.add(new ActionCard(color, ActionType.REVERSE));
                cards.add(new ActionCard(color, ActionType.DRAW_TWO));
            }
        }
        
        // Wild cards
        for (int i = 0; i < 4; i++) {
            cards.add(new WildCard());
            cards.add(new WildDrawfour());
        }
        
        // Shuffle
        shuffle();
    }
    
    public void shuffle() {
        Collections.shuffle(cards);
    }
    
    // Draw a card
    public Card draw() {
        if (cards.isEmpty()) {
            System.out.println("Deck is empty!");
            return null;
        }
        return cards.remove(0);
    }
    
    // Add cards back to deck (for reshuffling discard pile)
    public void addCards(List<Card> cardsToAdd) {
        cards.addAll(cardsToAdd);
        shuffle();
    }
    
    public int size() {
        return cards.size();
    }
    
    public boolean isEmpty() {
        return cards.isEmpty();
    }
}