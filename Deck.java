import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;
    
    public Deck() {
        cards = new ArrayList<>();
        createDeck();
        shuffle();
    }

    private void createDeck() {
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};

        // Number cards
        for (Color color : colors) {
            // 0 cards
            cards.add(new NumberCard(color, 0));

            //number cards
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
            cards.add(new WildDrawFour());
        }
    }
   
   //t5lt
    public void shuffle() {
        Collections.shuffle(cards);
    }
    //tn7i une cart + check ida deck vide
    public Card draw() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }
    //ns79ha fl gqme
    public void addCards(List<Card> newCards) {
        cards.addAll(newCards);
        shuffle();
    }
    //check ida frgha
    public boolean isEmpty() {
        return cards.isEmpty();
    }
}