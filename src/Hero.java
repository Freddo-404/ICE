public class Hero {
    private String heroName;
    private String heroClass;
    private int heroCurrentHealth;
    private int heroMaxHealth;
    private WeaponSlot weaponSlot = new WeaponSlot(0,0);
    private boolean heroReadyToAttack;
    private int frozenCount;

    private HeroPower heroPower = new HeroPower();

    Hero(String heroName, String heroClass){
        this.heroName = heroName;
        this.heroClass = heroClass;
        this.heroCurrentHealth = 30;
        this.heroMaxHealth = 30;
        this.frozenCount = 0;
    }


    public void loseHealth(int dmgTaken){
        setHeroCurrentHealth(getHeroCurrentHealth()-dmgTaken);
    }
    public boolean heroDeath(){
        if(heroCurrentHealth<=0) {
            return true;
        }
        else{
            return false;
        }
    }


    public void equipWeapon(Weapon weapon){
        weaponSlot.equipWeapon(weapon);
    }

    public String getHeroClass() {
        return heroClass;
    }

    public HeroPower getHeroPower() {
        return heroPower;
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

    public String getHeroName() {
        return heroName;
    }

    public int getFrozenCount() {
        return frozenCount;
    }

    public void setFrozenCount(int frozenCount) {
        this.frozenCount = frozenCount;
    }

}