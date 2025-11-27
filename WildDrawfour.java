import java.util.Scanner;

public class WildDrawfour extends Card {

    public WildDrawfour() {
        super(Color.NONE, CardType.WILD_DRAW_FOUR);
    }

    @Override
    public boolean canPlay(Card topCard) {
        return true;
    }

    @Override
    public void applyEffect(Game game) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a color (RED, GREEN, BLUE, YELLOW): ");
        String chosen = scanner.next();

        switch (chosen) {
            case "RED":    this.color = Color.RED; break;
            case "GREEN":  this.color = Color.GREEN; break;
            case "BLUE":   this.color = Color.BLUE; break;
            case "YELLOW": this.color = Color.YELLOW; break;
        }
        System.out.println("Color changed to " + this.color);
        Player next = game.getNextPlayer();
        game.forceDraw(next, 4);
        System.out.println(next.getName() + " draws 4 cards!");
    }

    @Override
    public String toString() {
        return "WILD +4";
    }
}