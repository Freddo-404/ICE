import java.util.Random;

public class SpellEffect extends Effect {
    private String spellName;

    public SpellEffect(String spellName) {
        this.spellName = spellName;
    }

    public void useSpellEffect(Board myBoard, Board enemyBoard, TextUI ui) {

        switch (spellName) {
            case "The coin":
                myBoard.setCurrentMana(myBoard.getCurrentMana() + 1);
                ui.displayMessage("You gain 1 mana.");
                break;

            //Mage spells
            case "Fireball":
                myBoard.fireballAny(6, enemyBoard);
                ui.displayMessage("Your target is hit by Fireball and loses 6 hp.");
                break;
            case "Arcane Intellect":
                myBoard.drawCard(2);
                ui.displayMessage("You draw 2 cards.");
                break;
            case "Polymorph":
                ui.displayMessage("Do you want to Polymorph an enemy minion or a friendly minion?");
                Minion pickedMinion = enemyOrFriendlyMinion(myBoard, enemyBoard, ui);

                pickedMinion.setMinionCurrentHealth(1);
                pickedMinion.setMinionMaxHealth(1);
                pickedMinion.setMinionAttack(1);
                pickedMinion.setCardName("Sheep");
                ui.displayMessage("Your target is now a sheep. Mæææ!");
                break;
            case "Arcane Missiles":
                Random ran = new Random();
                int ranNum;

                for (int i = 0; i < 3 ; i++) {
                    ranNum = ran.nextInt(enemyBoard.getMinionsOnBoard().size() + 1);
                    switch (ranNum) {
                        case 0:
                            enemyBoard.getHero().loseHealth(1);
                            break;
                        case 1:
                            enemyBoard.getMinionsOnBoard().get(0).loseHealth(1);
                            break;
                        case 2:
                            enemyBoard.getMinionsOnBoard().get(1).loseHealth(1);
                            break;
                        case 3:
                            enemyBoard.getMinionsOnBoard().get(2).loseHealth(1);
                            break;
                        case 4:
                            enemyBoard.getMinionsOnBoard().get(3).loseHealth(1);
                            break;
                        case 5:
                            enemyBoard.getMinionsOnBoard().get(4).loseHealth(1);
                            break;
                        case 6:
                            enemyBoard.getMinionsOnBoard().get(5).loseHealth(1);
                            break;
                        case 7:
                            enemyBoard.getMinionsOnBoard().get(6).loseHealth(1);
                            break;
                        default:
                            ui.displayMessage("Something went wrong with ranNum in Arcane Missiles.");

                    }
                }
                ui.displayMessage("You deal 3 damage randomly split among all enemy characters");
                break;
            case "Ice Lance":

                break;
            default:
                System.out.println("Spell missing in SpellEffect");
                break;

        }


    }


    public Minion enemyOrFriendlyMinion(Board myBoard, Board enemyBoard, TextUI ui) {
        ui.displayMessage("1. Enemy minion \n 2. Friendly minion \n");
        Minion minion = null;
        switch (ui.getInput()){
            case "1":
                minion = myBoard.pickMinion(enemyBoard.getMinionsOnBoard());
                break;
            case "2":
                minion = myBoard.pickMinion(myBoard.getMinionsOnBoard());
                break;
            default:
                return enemyOrFriendlyMinion(myBoard, enemyBoard, ui);
        }
        return minion;
    }



   }


