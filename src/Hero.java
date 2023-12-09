public class Hero {
    private String heroName;
    private String heroClass;
    private int heroCurrentHealth;
    private int heroMaxHealth;
    private WeaponSlot weaponSlot = new WeaponSlot(0,0);

    private HeroPower heroPower = new HeroPower();


    public void loseHealth(int dmgTaken){
        setHeroCurrentHealth(getHeroCurrentHealth()-dmgTaken);
    }

    public void equipWeapon(Weapon weapon){
        weaponSlot.equipWeapon(weapon);
    }

    public String getHeroClass() {
        return heroClass;
    }

    public int getHeroCurrentHealth() {
        return heroCurrentHealth;
    }

    public void setHeroCurrentHealth(int heroCurrentHealth) {
        this.heroCurrentHealth = heroCurrentHealth;
    }
}
