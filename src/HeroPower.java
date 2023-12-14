import java.util.LinkedList;

public class HeroPower {

    private String heroClass;

    public HeroPower(String heroClass){
        this.heroClass=heroClass;
    }
    public void useHeroPower(Board myBoard, Board enemyBoard, TextUI ui) {

        switch (heroClass) {
            case "mage":
                mageHeroPower(myBoard, enemyBoard, ui);
            break;
            case "hunter":
                hunterHeroPower(enemyBoard.getHero(), ui);
                break;
            case "warlock":
                warlockHeroPower(myBoard,ui);
                break;
            case "paladin":
                paladinHeroPower(myBoard,ui);
                break;
            case "rogue":
                rogueHeroPower(myBoard.getHero(),ui);
                break;
            default:
                //"No matching hero class";
                break;
        }
    }

    public void mageHeroPower(Board myBoard, Board enemyBoard, TextUI ui){
/*
        Minion targetMinion = board.targetMinion(ui, board);
        targetMinion.loseHealth(1);
        targetMinion.minionDeath(targetMinion, enemyMinionsOnBoard);
        ui.displayMessage("You use Fireblast. Your target loses 1 hp.");

 */
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
            //Giver mana tilbage fra at bruge hero power
            myBoard.setCurrentMana(myBoard.getCurrentMana()+2);
        }

    }
   public void rogueHeroPower(Hero hero, TextUI ui){
        Weapon dagger = new Weapon("Dagger", 1, 1, 2);
        hero.equipWeapon(dagger);
        ui.displayMessage("You use Dagger Mastery. Equip a 1/2 Dagger.");
    }

}
