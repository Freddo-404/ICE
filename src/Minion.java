import java.util.LinkedList;

public class Minion extends Card {

    private int minionAttack;
    private int minionCurrentHealth;
    private int minionMaxHealth;
    private MinionEffect effect;

    public Minion(String cardName, int cardCost, int minionAttack, int minionMaxHealth) {
        super(cardName, cardCost);
    }

    public void minionDeath(Minion minion, LinkedList<Minion> cardsOnBoard){
        if (minionCurrentHealth <= 0){
            cardsOnBoard.remove(minion);
        }
    }
    public void loseHealth(int dmgTaken){
        setMinionCurrentHealth(getMinionCurrentHealth()-dmgTaken);
    }

    public int getMinionMaxHealth() {
        return minionMaxHealth;
    }
    public int getMinionAttack() {
        return minionAttack;
    }


    public int getMinionCurrentHealth() {
        return minionCurrentHealth;
    }

    public void setMinionCurrentHealth(int newMinionHealth){
        this.minionCurrentHealth = newMinionHealth;

    }


}
