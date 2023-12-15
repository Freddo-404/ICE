import java.util.LinkedList;

public class HeroPower {


    public void useHeroPower(String heroClass,Board myBoard, Board enemyBoard, TextUI ui) {

        switch (heroClass) {
            case "Mage":
                mageHeroPower(myBoard, enemyBoard, ui);
            break;
            case "Hunter":
                hunterHeroPower(enemyBoard.getHero(), ui);
                break;
            case "Warlock":
                warlockHeroPower(myBoard,ui);
                break;
            case "Paladin":
                paladinHeroPower(myBoard,ui);
                break;
            case "Rogue":
                rogueHeroPower(myBoard.getHero(),ui);
                break;
            default:
                System.out.println("No matching hero class");
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
    public void hunterHeroPower(Hero enemyHero, TextUI ui){
        enemyHero.loseHealth(2);
        ui.displayMessage("You use Steady Shot. Enemy hero loses 2 hp.");
    }
   public void warlockHeroPower(Board myBoard, TextUI ui){
        myBoard.drawCard(1);
        myBoard.getHero().loseHealth(2);
        ui.displayMessage("You use Life Tap. Draw 1 card and lose 2 hp.");
    }
   public void paladinHeroPower(Board myBoard, TextUI ui){
        if(myBoard.getMinionsOnBoard().size()<7) {
            Minion silverHandRecruit = new Minion("Silver Hand Recruit", 1, 1, 1);
            myBoard.getMinionsOnBoard().add(silverHandRecruit);
            ui.displayMessage("You use Reinforce. Summon a 1/1 Silver Hand Recruit.");
        }
        else{
            ui.displayMessage("You can't use Reinforce. You board is full.");
        }

    }
   public void rogueHeroPower(Hero hero, TextUI ui){
        Weapon dagger = new Weapon("Dagger", 1, 1, 2);
        hero.equipWeapon(dagger);
        ui.displayMessage("You use Dagger Mastery. Equip a 1/2 Dagger.");
    }

}
