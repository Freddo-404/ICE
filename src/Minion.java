public class Minion extends Card {

    private int minionAttack;
    private int minionCurrentHealth;
    private int minionMaxHealth;
    private MinionEffect effect;

    public Minion(String cardName, int cardCost, int minionAttack, int minionMaxHealth) {
        super(cardName, cardCost);
    }

    public void setMinionHealth(int health){


    }
   /* public MinionEffect getEffect() {
        return effect;
    }
*/

    public int getMinionMaxHealth() {
        return minionMaxHealth;
    }
    public int getMinionAttack() {
        return minionAttack;
    }


    public int getMinionCurrentHealth() {
        return minionCurrentHealth;
    }

}
