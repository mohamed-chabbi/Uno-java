public abstract class Card {
    protected Color color;
    protected CardType type;
    
    public Card(Color color, CardType type) {
        this.color = color;
        this.type = type;
    }
    
    public Color getColor() {
        return color;
    }
    
    public CardType getType() {
        return type;
    }
    
    protected void setColor(Color color) {
        this.color = color;
    }
    
    public abstract boolean canPlay(Card topCard);
    public abstract void applyEffect(Game game);
    
    @Override
    public String toString() {
        return color + " " + type;
    }
}