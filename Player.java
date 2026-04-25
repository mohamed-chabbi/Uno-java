import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;
    private boolean isBot;
    private boolean saidUno;

    public Player(String name, boolean isBot) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.isBot = isBot;
        this.saidUno = false;
    }

    public Player(String name) {
        this(name, false);
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getHandSize() {
        return hand.size();
    }

    public boolean isBot() {
        return isBot;
    }

    public boolean hasSaidUno() {
        return saidUno;
    }

    public void addCard(Card c) {
        hand.add(c);
        if (hand.size() > 1) {
            saidUno = false;
        }
    }

    public boolean removeCard(Card c) {
        boolean removed = hand.remove(c);
        if (hand.size() != 1) {
            saidUno = false;
        }
        return removed;
    }

    public Card getCard(int index) {
        if (index >= 0 && index < hand.size()) return hand.get(index);
        return null;
    }

    public void sayUno() {
        if (hand.size() == 1) {
            saidUno = true;
            System.out.println(name + " says UNO!");
        }
    }

    public boolean hasPlayableCard(Card topCard) {
        for (Card c : hand) {
            if (c.canPlay(topCard)) return true;
        }
        return false;
    }

    public List<Card> getPlayableCards(Card topCard) {
        List<Card> playable = new ArrayList<>();
        for (Card c : hand) {
            if (c.canPlay(topCard)) {
                playable.add(c);
            }
        }
        return playable;
    }

    public void showHand() {
        System.out.println("\n" + name + "'s hand (" + hand.size() + " cards):");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println("  [" + i + "] " + hand.get(i));
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
