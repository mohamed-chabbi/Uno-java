public class NumberCard extends Card {

    private int number;

    public NumberCard(Color color, int number) {
        super(color, CardType.NUMBER);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean canPlay(Card topCard) {
        if (this.color == topCard.getColor()) {
            return true;
        }

        if (topCard instanceof NumberCard) {
            return this.number == ((NumberCard) topCard).getNumber();
        }

        return false;
    }

    @Override
    public void applyEffect(Game game) {
    }

    @Override
    public String toString() {
        return color + " " + number;
    }
}
