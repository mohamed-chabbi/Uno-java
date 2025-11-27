import java.util.Scanner;

public class WildCard extends Card {

    public WildCard() {
        super(Color.NONE, CardType.WILD);
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
    }

    @Override
    public String toString() {
        return "WILD";
    }
}
