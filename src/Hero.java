public class Hero {
    private String heroName;
    private String heroClass;
    private int heroCurrentHealth = 30;
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

    public HeroPower getHeroPower() {
        return heroPower;
    }

    public int getHeroCurrentHealth() {
        return heroCurrentHealth;
    }

    public void setHeroCurrentHealth(int heroCurrentHealth) {
        this.heroCurrentHealth = heroCurrentHealth;
    }
    public boolean heroDeath(){
        if(heroCurrentHealth<=0){

        }return true;

    }
    public WeaponSlot getWeaponSlot(){


        return weaponSlot;
    }

}
