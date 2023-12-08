public class Weapon extends Card{
    private int weaponAttack;
    private int weaponDurability;
    private WeaponEffect effect;


    public Weapon(String cardName, int cardCost, int weaponAttack, int weaponDurability, WeaponEffect effect) {
        super(cardName, cardCost);
    }

    public int getWeaponAttack() {
        return weaponAttack;
    }
    public int getWeaponDurability() {
        return weaponDurability;
    }


    public WeaponEffect getEffect() {
        return effect;
    }
}
