public class Hero {
    private String heroName;
    private String heroClass;
    private int heroCurrentHealth;
    private int heroMaxHealth = 30;
    private WeaponSlot weaponSlot = new WeaponSlot(0,0);

    private HeroPower heroPower = new HeroPower();

    Hero(String heroName, String heroClass){
        this.heroName = heroName;
        this.heroClass = heroClass;
    }


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
    public WeaponSlot getWeaponSlot(){

        return weaponSlot;
    }

}
