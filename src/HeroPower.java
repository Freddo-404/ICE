import java.util.LinkedList;

public class HeroPower {
    public void useHeroPower(Hero hero, Board board) {

        switch (hero.getHeroClass()) {
            case "mage":
                mageHeroPower();
            break;
            case "hunter":
                hunterHeroPower();
                break;
            case "warlock":
                warlockHeroPower(hero, board);
                break;
            case "paladin":
                paladinHeroPower();
                break;
            case "rogue":
                rogueHeroPower(hero);
                break;
            default:
                //"No matching hero class";
                break;
        }
    }

    public void mageHeroPower(Board board, TextUI ui, LinkedList<Minion> minionsOnBoard){

        Minion targetMinion = board.targetMinion(ui);
        targetMinion.loseHealth(1);
        targetMinion.minionDeath(targetMinion, minionsOnBoard);
        //return "You use Fireblast on " + targetMinion.getCardName() + ". It loses 1 hp.";

    }
    public void hunterHeroPower(Hero enemyHero){

        enemyHero.loseHealth(2);
        //return "You use Steady Shot. Enemy hero loses 2 hp.";
    }
   public void warlockHeroPower(Hero hero, Board board){
        board.drawCard(1);
        hero.loseHealth(2);

        //"You use Life Tap. Draw 1 card and lose 2 hp.";
    }
   public void paladinHeroPower(){

    }
   public void rogueHeroPower(Hero hero){
        Weapon dagger = new Weapon("Dagger", 1, 1, 2);
        hero.equipWeapon(dagger);

        // "You use Dagger Mastery. Equip a 1/2 Dagger";
    }
}
