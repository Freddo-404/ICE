public class Spell extends Card {
    private SpellEffect effect;

    public Spell(String cardName, int cardCost) {
        super(cardName, cardCost);
    }
    public SpellEffect getEffect(){
        return effect;
    }
}
