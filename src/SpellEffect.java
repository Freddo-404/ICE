import java.util.Random;

public class SpellEffect extends Effect {
    private String spellName;
private boolean combo;
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
            case "Backstab":
            ui.displayMessage("Do you want to Backstab an enemy minion or a friendly minion?");
                Minion pickedMinion1 = enemyOrFriendlyMinion(myBoard, enemyBoard, ui);
                if(pickedMinion1.getMinionCurrentHealth()==pickedMinion1.getMinionMaxHealth()){
                    pickedMinion1.setMinionCurrentHealth(pickedMinion1.getMinionCurrentHealth()-2);
                }else{
                    ui.displayMessage("You can not target damaged minions");
                    // skal returnere kortet og mana her hvis man ikke kan spille det

                }
            case"Shadowstep":
                myBoard.getMinionsOnBoard().remove(myBoard.pickMinion(myBoard.getMinionsOnBoard()));
                myBoard.pickMinion(myBoard.getMinionsOnBoard()).setCardCost(myBoard.pickMinion(myBoard.getMinionsOnBoard()).getCardCost()-2);
                myBoard.getHand().getCardsInHand().add(myBoard.pickMinion(myBoard.getMinionsOnBoard()));
                // den her skal også kigges på for jeg har cooked men over cooked

                break;
            case "Deadly Poison":
                    if(myBoard.getHero().getWeaponSlot().getCurrentDurability()<0){
                        myBoard.getHero().getWeaponSlot().setWeaponSlotAttack(myBoard.getHero().getWeaponSlot().getWeaponSlotAttack()+2);
                }
                    else{
                       // skal returnere kortet og mana her hvis man ikke kan spille det
                    }
                break;
            case "Preparation":
             // skal kunne gøre så når man har spillet den skal den næste spell man spiller koste 3 mindre mana
                break;
            case "Cold Blood":
                    ui.displayMessage("Do you want to add +2 attack to an enemy minion or friendly minion");
                    Minion pickedMinion2 = enemyOrFriendlyMinion(myBoard, enemyBoard, ui);
                    pickedMinion2.setMinionAttack(pickedMinion2.getMinionAttack()+2);
                    // mangler combo hvis vi har tid så den giver 4 skade i stedet
                break;
            case "Eviscerate":
                myBoard.fireballAny(2, enemyBoard);
                ui.displayMessage("you deal 2 damage");

                //mangler combo så den skader 4
            case "Sap":
                enemyBoard.getMinionsOnBoard().remove(enemyBoard.pickMinion(enemyBoard.getMinionsOnBoard()));
                enemyBoard.getHand().getCardsInHand().add(enemyBoard.pickMinion(enemyBoard.getMinionsOnBoard()));
                // det her virker nok slet ikke som det skal pls fix
                break;
            case "fan of Knives":
                myBoard.drawCard(1);
            for(Minion m : enemyBoard.getMinionsOnBoard()){
                m.loseHealth(1);
            }
            for (Minion m : enemyBoard.getMinionsOnBoard()){
                m.minionDeath(m, enemyBoard.getMinionsOnBoard());
            }
            ui.displayMessage("You use Fan of Knives and deal 1 damage to all enemy minions");
            break;
            case"Shiv":
                myBoard.fireballAny(1, enemyBoard);
                myBoard.drawCard(1);
                ui.displayMessage("You deal 1 damage and draw a card");
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


