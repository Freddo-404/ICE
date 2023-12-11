import java.util.LinkedList;

public class WeaponSlot {
    private int weaponSlotAttack;
    private int currentDurability;

    public WeaponSlot(int weaponSlotAttack, int currentDurability) {
        this.weaponSlotAttack = weaponSlotAttack;
        this.currentDurability = currentDurability;
    }


    public  void equipWeapon(Weapon weapon){

        setWeaponSlotAttack(weapon.getWeaponAttack());
        setCurrentDurability(weapon.getWeaponDurability());

    }
   public void useWeaponMinion(Minion m, LinkedList<Card> minionsOnOpponentsBoard){

        m.setMinionCurrentHealth(m.getMinionCurrentHealth()-weaponSlotAttack);
        m.minionDeath(m, minionsOnOpponentsBoard);
        currentDurability = currentDurability - 1;
        boolean ifDestroyed = destroyWeapon();

        //boolean er til at printe hvis våbnet blev ødelagt. burde skrive
        // noget info til brugeren måske om enemy minion og ens våben, evt hero health
    }

    public void useWeaponFace(Hero enemyHero){

        enemyHero.loseHealth(weaponSlotAttack);
        currentDurability = currentDurability - 1;
        boolean ifDestroyed = destroyWeapon();
    }
    public boolean destroyWeapon(){

        if(currentDurability <= 0){
            weaponSlotAttack = 0;
        } return true;
    }

    public void setWeaponSlotAttack(int weaponSlotAttack) {
        this.weaponSlotAttack = weaponSlotAttack;
    }

    public void setCurrentDurability(int currentDurability) {
        this.currentDurability = currentDurability;
    }
    public int getWeaponSlotAttack(){
        return weaponSlotAttack;
    }
}
