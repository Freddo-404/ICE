public class HeroPower {
    public String useHeroPower(Hero hero, Board board) {

        switch (hero.getHeroClass()) {
            case "mage":
                return mageHeroPower();
                break;
            case "hunter":
                return hunterHeroPower();
                break;
            case "warlock":
                return warlockHeroPower(hero, board);
                break;
            case "paladin":
                return paladinHeroPower();
                break;
            case "rogue":
                return rogueHeroPower();
                break;
            default:
                return "No matching hero class";
                break;
        }
    }

    public String mageHeroPower(){
        return null;
    }
    public String hunterHeroPower(Hero enemyHero){

        enemyHero.loseHealth(2);
        return "You use Steady Shot. Enemy hero loses 2 hp.";
    }
   public String warlockHeroPower(Hero hero, Board board){
        board.drawCard(1);
        hero.loseHealth(2);

        return "You use Life Tap. Draw 1 card and lose 2 hp.";
    }
   public String paladinHeroPower(){
        return null;
    }
   public String rogueHeroPower(Hero hero){
        Weapon dagger = new Weapon("Dagger", 1, 1, 2);
        hero.equipWeapon(dagger);

        return "You use Dagger Mastery. Equip a 1/2 Dagger";
    }
}
