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
        Color chosen = game.chooseColor();
        this.setColor(chosen);
        System.out.println("Color changed to " + chosen);
    }

    @Override
    public String toString() {
        return "WILD";
    }
}
