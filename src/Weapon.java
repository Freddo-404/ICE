public class Weapon extends Card{
    private int weaponAttack;
    private int weaponDurability;
    private WeaponEffect effect;


    public Weapon(String cardName, int cardCost, int weaponAttack, int weaponDurability) {

        super(cardName, cardCost);
        this.weaponAttack = weaponAttack;
        this.weaponDurability = weaponDurability;
    }

    public int getWeaponAttack() {
        return weaponAttack;
    }
    public int getWeaponDurability() {
        return weaponDurability;
    }


}
