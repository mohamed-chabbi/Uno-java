public class ActionCard extends Card {

    private ActionType action;

    public ActionCard(Color color, ActionType action) {
        super(color, CardType.valueOf(action.name()));
        this.action = action;
    }

    public ActionType getAction() {
        return action;
    }

    @Override
    public boolean canPlay(Card topCard) {
        return this.color == topCard.getColor() || this.type == topCard.getType();
    }

    @Override
    public void applyEffect(Game game) {
        if (action == ActionType.SKIP) {
            game.skipNextPlayer();
        }
        else if (action == ActionType.REVERSE) {
            game.reverse();
        }
        else if (action == ActionType.DRAW_TWO) {
            Player next = game.getNextPlayer();
            game.forceDraw(next, 2);
        }
    }
    
    @Override
    public String toString() {
        return color + " " + action;
    }
}
