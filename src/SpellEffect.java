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
                            enemyBoard.getMinionsOnBoard().get(0).minionDeath(enemyBoard.getMinionsOnBoard().get(0),enemyBoard.getMinionsOnBoard());
                            break;
                        case 2:
                            enemyBoard.getMinionsOnBoard().get(1).loseHealth(1);
                            enemyBoard.getMinionsOnBoard().get(1).minionDeath(enemyBoard.getMinionsOnBoard().get(1),enemyBoard.getMinionsOnBoard());
                            break;
                        case 3:
                            enemyBoard.getMinionsOnBoard().get(2).loseHealth(1);
                            enemyBoard.getMinionsOnBoard().get(2).minionDeath(enemyBoard.getMinionsOnBoard().get(2),enemyBoard.getMinionsOnBoard());
                            break;
                        case 4:
                            enemyBoard.getMinionsOnBoard().get(3).loseHealth(1);
                            enemyBoard.getMinionsOnBoard().get(3).minionDeath(enemyBoard.getMinionsOnBoard().get(3),enemyBoard.getMinionsOnBoard());
                            break;
                        case 5:
                            enemyBoard.getMinionsOnBoard().get(4).loseHealth(1);
                            enemyBoard.getMinionsOnBoard().get(4).minionDeath(enemyBoard.getMinionsOnBoard().get(4),enemyBoard.getMinionsOnBoard());
                            break;
                        case 6:
                            enemyBoard.getMinionsOnBoard().get(5).loseHealth(1);
                            enemyBoard.getMinionsOnBoard().get(5).minionDeath(enemyBoard.getMinionsOnBoard().get(5),enemyBoard.getMinionsOnBoard());
                            break;
                        case 7:
                            enemyBoard.getMinionsOnBoard().get(6).loseHealth(1);
                            enemyBoard.getMinionsOnBoard().get(6).minionDeath(enemyBoard.getMinionsOnBoard().get(6),enemyBoard.getMinionsOnBoard());
                            break;
                        default:
                            ui.displayMessage("Something went wrong with ranNum in Arcane Missiles.");

                    }
                }
                ui.displayMessage("You deal 3 damage randomly split among all enemy characters");
                break;
            case "Ice Lance":
                ui.displayMessage("Fuck Ice Lane");
                break;
            case "Mirror Image":
                for (int i = 0; i < 2; i++) {
                    if (myBoard.getMinionsOnBoard().size() < 7) {
                        Minion mirrorImage = new Minion("Mirror Image", 1, 0, 2);
                        myBoard.getMinionsOnBoard().add(mirrorImage);
                        ui.displayMessage("You summon a 0/2 Mirror Image.");
                    } else {
                        ui.displayMessage("Not all Mirror images got summoned since your board is full.");
                    }
                }
                break;
            case "Frostbolt":
                myBoard.fireballAny(3, enemyBoard);
                //mangler frozen
                ui.displayMessage("Your target is hit by Frostbolt and loses 3 hp. It's also frozen.");
            case "Pyroblast":
                myBoard.fireballAny(10, enemyBoard);
                //mangler frozen
                ui.displayMessage("Your target is hit by a mighty Pyroblast and loses 10 hp. Rest in peace.");
                break;
            case "Flamestrike":
                     for(Minion m : enemyBoard.getMinionsOnBoard()){
                         m.loseHealth(4);
                     }

                    for(Minion m : enemyBoard.getMinionsOnBoard()){
                        m.minionDeath(m, enemyBoard.getMinionsOnBoard());
                    }
                    ui.displayMessage("You use Flamestrike and deal 4 damage to all enemy minion.");
                break;
            //Hunter spells
            case "Arcane Shot":
                myBoard.fireballAny(2, enemyBoard);
                ui.displayMessage("Your target is hit by Arcane Shot and loses 2 hp.");
                break;
            case "Animal Companion":
                Random ran1 = new Random();
                int randomCompanion =3;

                for (int i = 0; i < 3; i++) {
                    randomCompanion = ran1.nextInt(enemyBoard.getMinionsOnBoard().size() + 1);
                    switch (randomCompanion) {
                        case 0:
                            if (myBoard.getMinionsOnBoard().size() < 7) {
                                Minion animalCompanion = new Minion("Animal Companion", 3, 4, 3);
                                myBoard.getMinionsOnBoard().add(animalCompanion);
                                ui.displayMessage("You summon a 4/3 BJØRN.");
                            } else {
                                ui.displayMessage("Not enough space on board for a companion");
                            }
                            break;
                        case 1:
                            if (myBoard.getMinionsOnBoard().size() < 7) {
                                Minion animalCompanion2 = new Minion("Animal Companion", 3, 2, 4);
                                myBoard.getMinionsOnBoard().add(animalCompanion2);
                                ui.displayMessage("You summon a 2/4 Monkey.");
                            } else {
                                ui.displayMessage("Not enough space on board for a companion");
                            }

                            break;
                        case 2:
                            if (myBoard.getMinionsOnBoard().size() < 7) {
                                Minion animalCompanion3 = new Minion("Animal Companion", 3, 2, 4);
                                myBoard.getMinionsOnBoard().add(animalCompanion3);
                                ui.displayMessage("You summon a 4/2 Boar.");
                            } else {
                                ui.displayMessage("Not enough space on board for a companion");
                            }
                            break;

                        default:
                            System.out.println("Spell missing in SpellEffect");
                            break;

                    }


                }
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


