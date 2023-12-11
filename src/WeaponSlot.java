public class WeaponSlot {
    private int weaponSlotAttack;
    private int currentDurability;

    public WeaponSlot(int weaponSlotAttack, int currentDurability) {
        this.weaponSlotAttack = weaponSlotAttack;
        this.currentDurability = currentDurability;
    }


    public  String equipWeapon(Weapon weapon){

        setWeaponSlotAttack(weapon.getWeaponAttack());
        setCurrentDurability(weapon.getWeaponDurability());

        return "Weapon equipped";
    }
   public String useWeapon(){
        return null;
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
