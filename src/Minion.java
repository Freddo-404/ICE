import java.util.LinkedList;

public class Minion extends Card {

    private int minionAttack;
    private int minionCurrentHealth;
    private int minionMaxHealth;
    private Boolean minionReadyToAttack;
    private MinionEffect effect;
    private boolean taunt;
    private int originalAttack;
    private int originalHealth;
    private int frozenCount;

    public Minion(String cardName, int cardCost, int minionAttack, int minionMaxHealth, boolean taunt) {
        super(cardName, cardCost);
        this.minionAttack = minionAttack;
        this.minionMaxHealth = minionMaxHealth;
        this.minionCurrentHealth = minionMaxHealth;
        this.minionReadyToAttack = false;
        this.taunt = taunt;
        this.originalAttack = minionAttack;
        this.originalHealth = minionMaxHealth;

    }

    public void minionDeath(Minion minion, LinkedList<Minion> cardsOnBoard, LinkedList<Minion> tauntList){
        if (minionCurrentHealth <= 0){
            cardsOnBoard.remove(minion);
            if(minion.getTaunt()){
                tauntList.remove(minion);
            }

        }
    }
    public boolean getTaunt(){
        return taunt;
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
    public int getOriginalAttack(){
        return originalAttack;
    }
    public int getOriginalHealth(){
        return originalHealth;
    }


    public int getFrozenCount() {
        return frozenCount;
    }

    public void setFrozenCount(int frozenCount) {
        this.frozenCount = frozenCount;
    }

}
