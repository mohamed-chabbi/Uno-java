import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void addCard(Card c) {
        hand.add(c);
    }

    public void removeCard(Card c) {
        hand.remove(c);
    }

    public boolean PlayableCard(Card topCard) {
        for (Card c : hand) {
            if (c.canPlay(topCard)) return true;
        }
        return false;
    }

    public List<Card> getPlayableCards(Card topCard) {
        List<Card> playable = new ArrayList<>();
        for (Card c : hand) {
            if (c.canPlay(topCard)) playable.add(c);
        }
        return playable;
    }

    @Override
    public String toString() {
        return name;
    }
}
