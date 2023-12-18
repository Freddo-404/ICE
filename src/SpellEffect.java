import java.util.Random;

public class SpellEffect extends Effect {
    private String spellName;

    public SpellEffect(String spellName) {
        this.spellName = spellName;
    }

    public Boolean useSpellEffect(Board myBoard, Board enemyBoard, TextUI ui) {
        Boolean activateSpell = false;
        switch (spellName) {
            case "The coin":
                if(myBoard.getCurrentMana()<10) {
                    myBoard.setCurrentMana(myBoard.getCurrentMana() + 1);
                    ui.displayMessage("You gain 1 mana.");
                    activateSpell = true;
                }else{
                    myBoard.setCurrentMana(myBoard.getCurrentMana());
                    ui.displayMessage("Your mana is full.");
                    activateSpell = true;
                }
                break;

            //Mage spells
            case "Fireball":
                myBoard.fireballAny(6, enemyBoard,false);
                ui.displayMessage("Your target is hit by Fireball and loses 6 hp.");
                activateSpell = true;
                break;
            case "Arcane Intellect":
                myBoard.drawCard(2);
                ui.displayMessage("You draw 2 cards.");
                activateSpell = true;
                break;
            case "Polymorph":
                ui.displayMessage("Do you want to Polymorph an enemy minion or a friendly minion?");
                Minion pickedMinion = myBoard.enemyOrFriendlyMinion(enemyBoard, ui);
                if(!(pickedMinion==null)) {
                    pickedMinion.setMinionCurrentHealth(1);
                    pickedMinion.setMinionMaxHealth(1);
                    pickedMinion.setMinionAttack(1);
                    pickedMinion.setCardName("Sheep");
                    ui.displayMessage("Your target is now a sheep. Mæææ!");
                    activateSpell = true;
                }
                else{
                    ui.displayMessage("You couldn't find a target to use Polymorph on.");
                    activateSpell = false;
                }
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
                            enemyBoard.getMinionsOnBoard().get(0).minionDeath(enemyBoard.getMinionsOnBoard().get(0),enemyBoard.getMinionsOnBoard(),enemyBoard.getMinionsWithTaunt());
                            break;
                        case 2:
                            enemyBoard.getMinionsOnBoard().get(1).loseHealth(1);
                            enemyBoard.getMinionsOnBoard().get(1).minionDeath(enemyBoard.getMinionsOnBoard().get(1),enemyBoard.getMinionsOnBoard(),enemyBoard.getMinionsWithTaunt());
                            break;
                        case 3:
                            enemyBoard.getMinionsOnBoard().get(2).loseHealth(1);
                            enemyBoard.getMinionsOnBoard().get(2).minionDeath(enemyBoard.getMinionsOnBoard().get(2),enemyBoard.getMinionsOnBoard(),enemyBoard.getMinionsWithTaunt());
                            break;
                        case 4:
                            enemyBoard.getMinionsOnBoard().get(3).loseHealth(1);
                            enemyBoard.getMinionsOnBoard().get(3).minionDeath(enemyBoard.getMinionsOnBoard().get(3),enemyBoard.getMinionsOnBoard(),enemyBoard.getMinionsWithTaunt());
                            break;
                        case 5:
                            enemyBoard.getMinionsOnBoard().get(4).loseHealth(1);
                            enemyBoard.getMinionsOnBoard().get(4).minionDeath(enemyBoard.getMinionsOnBoard().get(4),enemyBoard.getMinionsOnBoard(),enemyBoard.getMinionsWithTaunt());
                            break;
                        case 6:
                            enemyBoard.getMinionsOnBoard().get(5).loseHealth(1);
                            enemyBoard.getMinionsOnBoard().get(5).minionDeath(enemyBoard.getMinionsOnBoard().get(5),enemyBoard.getMinionsOnBoard(),enemyBoard.getMinionsWithTaunt());
                            break;
                        case 7:
                            enemyBoard.getMinionsOnBoard().get(6).loseHealth(1);
                            enemyBoard.getMinionsOnBoard().get(6).minionDeath(enemyBoard.getMinionsOnBoard().get(6),enemyBoard.getMinionsOnBoard(),enemyBoard.getMinionsWithTaunt());
                            break;
                        default:
                            ui.displayMessage("Something went wrong with ranNum in Arcane Missiles.");

                    }
                }
                ui.displayMessage("You deal 3 damage randomly split among all enemy characters");
                activateSpell = true;
                break;
            case "Ice Lance":
                myBoard.fireballAny(0, enemyBoard,true);

                activateSpell = true;
                break;
            case "Mirror Image":
                for (int i = 0; i < 2; i++) {
                    if (myBoard.getMinionsOnBoard().size() < 7) {
                        Minion mirrorImage = new Minion("Mirror Image", 1, 0, 2,true);
                        myBoard.getMinionsOnBoard().add(mirrorImage);
                        ui.displayMessage("You summon a 0/2 Mirror Image with taunt.");
                    } else {
                        ui.displayMessage("Not all Mirror images got summoned since your board is full.");
                    }
                }
                activateSpell = true;
                break;
            case "Frostbolt":
                myBoard.fireballAny(3, enemyBoard,true);
                ui.displayMessage("Your target is hit by Frostbolt and loses 3 hp. It's also frozen.");
                activateSpell = true;
                break;
            case "Pyroblast":
                myBoard.fireballAny(10, enemyBoard,false);
                //mangler frozen
                ui.displayMessage("Your target is hit by a mighty Pyroblast and loses 10 hp. Rest in peace.");
                activateSpell = true;
                break;
            case "Flamestrike":
                myBoard.flamestrike(4,enemyBoard);
                ui.displayMessage("You use Flamestrike and deal 4 damage to all enemy minion.");
                activateSpell = true;
                break;

            //Hunter spells
            case "Arcane Shot":
                myBoard.fireballAny(2, enemyBoard,false);
                ui.displayMessage("Your target is hit by Arcane Shot and loses 2 hp.");
                activateSpell = true;
                break;
            case "Animal Companion":
                Random ran1 = new Random();
                int randomCompanion;
                randomCompanion = ran1.nextInt(3);
                    switch (randomCompanion) {
                        case 0:
                            if (myBoard.getMinionsOnBoard().size() < 7) {
                                Minion animalCompanion = new Minion("Misha", 3, 4, 3,true);
                                myBoard.getMinionsOnBoard().add(animalCompanion);
                                ui.displayMessage("You summon a 4/3 bear with taunt.");
                            } else {
                                ui.displayMessage("Not enough space on board for a companion");
                            }
                            break;
                        case 1:
                            if (myBoard.getMinionsOnBoard().size() < 7) {
                                Minion animalCompanion2 = new Minion("Leokk", 3, 2, 4,false);
                                myBoard.getMinionsOnBoard().add(animalCompanion2);
                                ui.displayMessage("You summon a 2/4 Leokk.");
                            } else {
                                ui.displayMessage("Not enough space on board for a companion");
                            }

                            break;
                        case 2:
                            if (myBoard.getMinionsOnBoard().size() < 7) {
                                Minion animalCompanion3 = new Minion("Animal Companion", 3, 4, 2,false);
                                myBoard.getMinionsOnBoard().add(animalCompanion3);
                                ui.displayMessage("You summon a 4/2 Huffer.");
                            } else {
                                ui.displayMessage("Not enough space on board for a companion");
                            }
                            break;

                        default:
                            System.out.println("Random i animal companion virker ikke");
                            break;

                    }

                activateSpell = true;
                break;


                //Paladin spells
            case "Equality":
                for (Minion m : myBoard.getMinionsOnBoard()){
                    m.setMinionCurrentHealth(1);
                    m.setMinionMaxHealth(1);
                }
                for (Minion m : enemyBoard.getMinionsOnBoard()){
                    m.setMinionCurrentHealth(1);
                    m.setMinionMaxHealth(1);
                }
                ui.displayMessage("The health of all minions is changed to 1. They all suck now, but at least they are equal :).");
                activateSpell = true;
                break;
            case "Consecration":
                myBoard.flamestrike(2,enemyBoard);

                enemyBoard.getHero().loseHealth(2);

                ui.displayMessage("You use Consecration and deal 2 damage to all enemies.");
                activateSpell = true;
                break;
            case "Divine favor":
                while(enemyBoard.getHand().getCardsInHand().size()>myBoard.getHand().getCardsInHand().size()){
                    myBoard.drawCard(1);
                }
                ui.displayMessage("You draw cards until you have as many as your opponent.");
                activateSpell = true;
                break;


                //Warlock spells
            case "Soul Fire":
                myBoard.fireballAny(4, enemyBoard,false);
                myBoard.discardCard(1);

                ui.displayMessage("Your target is hit by Soul Fire and loses 4 hp. A random card from your card got discarded.");
                activateSpell = true;
                break;


                //Rogue spells
            case "Backstab":
            ui.displayMessage("Do you want to Backstab an enemy minion or a friendly minion?");

                Minion pickedMinion1 = myBoard.enemyOrFriendlyMinion(enemyBoard, ui);
                if(!(pickedMinion1==null)) {
                    if (pickedMinion1.getMinionCurrentHealth() == pickedMinion1.getMinionMaxHealth()) {
                        pickedMinion1.loseHealth(2);
                        pickedMinion1.minionDeath(pickedMinion1, enemyBoard.getMinionsOnBoard(),enemyBoard.getMinionsWithTaunt());
                        pickedMinion1.minionDeath(pickedMinion1, myBoard.getMinionsOnBoard(), myBoard.getMinionsWithTaunt());
                        activateSpell = true;
                    } else {
                        ui.displayMessage("You can not target a damaged minions");
                        activateSpell = false;
                    }
                }
                else{
                    ui.displayMessage("You couldn't find a target to use Backstab on.");
                    activateSpell = false;
                }
                break;
            case "Shadowstep":
                Minion pickedMinion2 = myBoard.pickMinion(myBoard.getMinionsOnBoard());
                if(!(pickedMinion2==null)){
                    //gør så de ikke kan koste negativ mana (Dette skulle nu være fikset -fred2)
                    if(pickedMinion2.getCardCost()>=2){
                        pickedMinion2.setMinionReadyToAttack(false);
                        pickedMinion2.setMinionMaxHealth(pickedMinion2.getOriginalHealth());
                        //mana cost burde også sættes op igen efter minionen bliver spillet igen. Det kræver dog at man holder styr på hvilke minions der tidligere er blevet shadowstepped.
                        myBoard.getMinionsOnBoard().remove(pickedMinion2);
                        if(myBoard.getHand().getCardsInHand().size()<10) {
                            myBoard.getHand().getCardsInHand().add(pickedMinion2);
                            pickedMinion2.setCardCost(pickedMinion2.getCardCost()-2);
                            pickedMinion2.setMinionAttack(pickedMinion2.getOriginalAttack());
                        }
                        activateSpell = true;
                    }else {
                        pickedMinion2.setMinionReadyToAttack(false);
                        pickedMinion2.setCardCost(0);
                        myBoard.getMinionsOnBoard().remove(pickedMinion2);
                        myBoard.getHand().getCardsInHand().add(pickedMinion2);
                        activateSpell = true;
                    }

                }
                else {
                    ui.displayMessage("Your board is empty so you're unable to use Shadowstep.");
                    activateSpell = false;
                }
                break;
            case "Deadly Poison":
                    if(myBoard.getHero().getWeaponSlot().getCurrentDurability()>0){
                        myBoard.getHero().getWeaponSlot().setWeaponSlotAttack(myBoard.getHero().getWeaponSlot().getWeaponSlotAttack()+2);
                        activateSpell = true;
                    }
                    else{
                       ui.displayMessage("You have no weapon equipped to use Deadly Poison on.");
                        activateSpell = false;
                    }
                break;
            case "Preparation":
             // skal kunne gøre så når man har spillet den skal den næste spell man spiller koste 3 mindre mana
                ui.displayMessage("fuck preparation");
                activateSpell = true;
                break;
            case "Cold Blood":
                if(myBoard.getCombo()==false) {
                    ui.displayMessage("Do you want to add +2 attack to a friendly minion or an enemy minion.");
                    Minion pickedMinion3 = myBoard.friendlyOrEnemyMinion(enemyBoard, ui);
                    if (!(pickedMinion3 == null)) {
                        pickedMinion3.setMinionAttack(pickedMinion3.getMinionAttack() + 2);
                        activateSpell = true;
                    } else {
                        ui.displayMessage("You couldn't find a target to use Cold Blood on.");
                        activateSpell = false;
                    }
                } else{
                    ui.displayMessage("COMBO: Do you want to add +4 attack to a friendly minion or an enemy minion.");
                    Minion pickedMinion3 = myBoard.friendlyOrEnemyMinion(enemyBoard, ui);
                    if (!(pickedMinion3 == null)) {
                        pickedMinion3.setMinionAttack(pickedMinion3.getMinionAttack() + 4);
                        activateSpell = true;
                    } else {
                        ui.displayMessage("You couldn't find a target to use Cold Blood on.");
                        activateSpell = false;
                    }
                }
                    // mangler combo hvis vi har tid så den giver 4 skade i stedet
                break;
            case "Eviscerate":
                if(myBoard.getCombo()==false) {
                    myBoard.fireballAny(2, enemyBoard,false);
                    ui.displayMessage("you deal 2 damage");
                    activateSpell = true;
                }else{
                    myBoard.fireballAny(4, enemyBoard,false);
                    ui.displayMessage("COMBO: you deal 4 damage");
                    activateSpell = true;
                }
                //mangler combo så den skader 4
                break;
            case "Sap":
                Minion pickedMinion4 = myBoard.pickMinion(enemyBoard.getMinionsOnBoard());
                if(!(pickedMinion4==null)){
                    pickedMinion4.setMinionReadyToAttack(false);
                    enemyBoard.getMinionsOnBoard().remove(pickedMinion4);
                    if(enemyBoard.getHand().getCardsInHand().size()<10) {
                        enemyBoard.getHand().getCardsInHand().add(pickedMinion4);
                        pickedMinion4.setMinionMaxHealth(pickedMinion4.getOriginalAttack());
                        pickedMinion4.setMinionMaxHealth(pickedMinion4.getOriginalHealth());
                    }
                    activateSpell = true;
                }
                else {
                    ui.displayMessage("Your opponent's board is empty so you're unable to use Sap.");
                    activateSpell = false;
                }
                break;
            case "Fan of Knives":

                myBoard.drawCard(1);
            myBoard.flamestrike(1,enemyBoard);
            ui.displayMessage("You use Fan of Knives and deal 1 damage to all enemy minions");
            activateSpell = true;

            break;
            case"Shiv":
                myBoard.fireballAny(1, enemyBoard,false);
                myBoard.drawCard(1);
                ui.displayMessage("You deal 1 damage to your target and draw a card");
                activateSpell = true;
                break;
            default:
                System.out.println("Spell missing in SpellEffect");
                break;

        }
        return activateSpell;

    }










   }


