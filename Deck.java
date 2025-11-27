import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    // Attributes
    private List<Card> cards;

    // Constructor
    public Deck() {
        cards = new ArrayList<>();
        createDeck();      // t3dmo + dir shuffle auto
    }

    // Create UNO deck
    private void createDeck() {

    
        for (Color color : new Color[]{
            Color.RED, 
            Color.GREEN, 
            Color.BLUE,
             Color.YELLOW}
             ) {

            // 0 card 
            cards.add(new NumberCard(color, 0));

            // numbers 1–9
            for (int n = 1; n <= 9; n++) {
                cards.add(new NumberCard(color, n));
                cards.add(new NumberCard(color, n));
            }
        }

        // action 
        for (Color color : new Color[]{Color.RED,
         Color.GREEN,
          Color.BLUE,
           Color.YELLOW}
           ){
            for (int i = 0; i < 2; i++) {
                cards.add(new ActionCard(color, ActionType.SKIP));
                cards.add(new ActionCard(color, ActionType.REVERSE));
                cards.add(new ActionCard(color, ActionType.DRAW_TWO));
            }
        }

        //Wild
        for (int i = 0; i < 4; i++) {
            cards.add(new WildCard());
            cards.add(new WildDrawfour());
        }

        // Shuffling
        Collections.shuffle(cards);
    }
    // tn7i top
    public Card draw() {
        if (cards.isEmpty()) return null;
        return cards.remove(0);   // return and removes
    }
    // ns79ha fl game
    public int size() {
        return cards.size();
    }
}
