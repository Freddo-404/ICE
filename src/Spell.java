public class Spell extends Card {
    private SpellEffect spellEffect = new SpellEffect( getCardName());

    public Spell(String cardName, int cardCost) {
        super(cardName, cardCost);
    }
    public SpellEffect getSpellEffect(){
        return spellEffect;
    }


}
