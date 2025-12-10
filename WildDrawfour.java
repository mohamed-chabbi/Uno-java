
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
    Color chosen = game.chooseColor();
    this.setColor(chosen);
    System.out.println("Color changed to " + chosen);
    Player next = game.getNextPlayer();
    game.forceDraw(next, 4);
    System.out.println(next.getName() + " draws 4 cards!");
}
    @Override
    public String toString() {
        return "WILD +4";
    }
}