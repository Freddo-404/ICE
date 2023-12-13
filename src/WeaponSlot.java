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
   public void useWeaponMinion(Minion m, LinkedList<Minion> minionsOnOpponentsBoard){

        m.setMinionCurrentHealth(m.getMinionCurrentHealth()-weaponSlotAttack);
        m.minionDeath(m, minionsOnOpponentsBoard);
        loseDurability();
        boolean ifDestroyed = destroyWeapon();

        //boolean er til at printe hvis våbnet blev ødelagt. burde skrive
        // noget info til brugeren måske om enemy minion og ens våben, evt hero health
    }

    public void useWeaponFace(Hero enemyHero){

        enemyHero.loseHealth(weaponSlotAttack);
        loseDurability();
        boolean ifDestroyed = destroyWeapon();
    }
    public boolean destroyWeapon(){

        if(currentDurability <= 0){
            weaponSlotAttack = 0;
        } return true;
    }

    public void loseDurability(){

        currentDurability = currentDurability - 1;
    }

    public void setWeaponSlotAttack(int weaponSlotAttack) {
        this.weaponSlotAttack = weaponSlotAttack;
    }

    public int getCurrentDurability() {
        return currentDurability;
    }

    public void setCurrentDurability(int currentDurability) {
        this.currentDurability = currentDurability;
    }
    public int getWeaponSlotAttack(){
        return weaponSlotAttack;
    }
}
