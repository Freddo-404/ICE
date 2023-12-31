import java.util.LinkedList;

public class HeroPower {


    public void useHeroPower(String heroClass, Board friendlyBoard, Board enemyBoard, TextUI ui) {

        switch (heroClass) {
            case "Mage":
                mageHeroPower(friendlyBoard, enemyBoard, ui);
            break;
            case "Hunter":
                hunterHeroPower(enemyBoard.getHero(), ui);
                break;
            case "Warlock":
                warlockHeroPower(friendlyBoard,ui);
                break;
            case "Paladin":
                paladinHeroPower(friendlyBoard,ui);
                break;
            case "Rogue":
                rogueHeroPower(friendlyBoard.getHero(),ui);
                break;
            default:
                System.out.println("No matching hero class");
                break;
        }
    }

    public void mageHeroPower(Board friendlyBoard, Board enemyBoard, TextUI ui) {


            friendlyBoard.fireballAny(1, enemyBoard,false);
            ui.displayMessage("Your target is hit by Fireblast and loses 1 hp.");


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
            Minion silverHandRecruit = new Minion("Silver Hand Recruit", 1, 1, 1,false);
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
