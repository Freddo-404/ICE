import java.util.LinkedList;

public class Minion extends Card {

    private int minionAttack;
    private int minionCurrentHealth;
    private int minionMaxHealth;
    private Boolean minionReadyToAttack;
    private MinionEffect effect;
    private int frozenCount;

    public Minion(String cardName, int cardCost, int minionAttack, int minionMaxHealth) {
        super(cardName, cardCost);
        this.minionAttack = minionAttack;
        this.minionMaxHealth = minionMaxHealth;
        this.minionCurrentHealth = minionMaxHealth;
        this.minionReadyToAttack = false;
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

    public void setMinionMaxHealth(int minionMaxHealth) {
        this.minionMaxHealth = minionMaxHealth;
    }

    public void setMinionAttack(int minionAttack) {
        this.minionAttack = minionAttack;
    }

    public void setMinionReadyToAttack(Boolean minionReadyToAttack) {
        this.minionReadyToAttack = minionReadyToAttack;
    }

    public Boolean getMinionReadyToAttack() {
        return minionReadyToAttack;
    }

    public int getFrozenCount() {
        return frozenCount;
    }

    public void setFrozenCount(int frozenCount) {
        this.frozenCount = frozenCount;
    }

}
