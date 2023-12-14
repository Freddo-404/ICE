import java.util.LinkedList;

public class HeroPower {
    public void useHeroPower(Hero hero, Board board, TextUI ui, LinkedList<Minion> minionsOnBoard) {

        switch (hero.getHeroClass()) {
            case "mage":
                mageHeroPower(board, ui, minionsOnBoard);
            break;
            case "hunter":
                hunterHeroPower(hero);
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

    public void mageHeroPower(Board board, TextUI ui, Board enemyBoard, Board friendlyBoard, Hero enemyHero){

        Minion targetMinion;
        board.targetAny(ui, enemyBoard, friendlyBoard, enemyHero);
        /*ui.displayMessage("Please pick a target type. \n 1. Enemy minion \n 2. Enemy hero \n 3. Friendly minion \n 4. Enemy hero");
        String input = ui.getInput();
        switch(input){
            case "1":
                targetMinion = board.targetMinion(ui,board);
                targetMinion.loseHealth(1);
                targetMinion.minionDeath(targetMinion, friendlyMinionsOnBoard);
                break;
            case "2":
                targetMinion = board.targetMinion(ui,board);
                targetMinion.loseHealth(1);
                targetMinion.minionDeath(targetMinion, enemyMinionsOnBoard);
        }*/

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
