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
