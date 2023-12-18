import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Game {

    private TextUI ui = new TextUI();
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player enemyPlayer;
    private Player tempPlayer;
    private Boolean heroPowerUsed;
    private Boolean gameOver;
    private Boolean heroReadyToAttack;



    public void mainMenu() {
        ui.displayMessage("Hello and welcome to ScuffedStone! \n" + "\n" + "Please select a choice" + "\n" +
                "1. Play \n" + "2. Quit");
        String input = ui.getInput();
        switch (input) {
            case "1":
                startUp();
                break;
            case "2":
                break;
            default:
                ui.displayMessage("Invalid input. Redirecting back to start menu.");
                mainMenu();
        }


    }

    public void gameLoop() {

        while (!gameOver) {
            ui.displayGame(currentPlayer, enemyPlayer);
            playerChoiceMenu();
            winCondition();
        }
        mainMenu();
    }

    public void startUp() {
        new Thread(() -> {
            //BackgroundMusic.playBackgroundMusic("/Users/frederikdupont/Downloads/hearthstonemusic.wav");
        }).start();
        ui.displayMessage("Please enter a name for Player 1.");
        player1 = new Player(ui.getInput());
        ui.displayMessage("Please enter a name for Player 2.");
        player2 = new Player(ui.getInput());
        ui.displayMessage("Please choose a class for " + player1.getPlayerName() + " by entering a number between 1 and 5.");
        player1.chooseHero(ui);
        ui.displayMessage("Please choose a class for " + player2.getPlayerName() + " by entering a number between 1 and 5.");
        player2.chooseHero(ui);

        initializeGame();
    }

    public void initializeGame() {
        Collections.shuffle(player1.getBoard().getDeck().getCardsInDeck());
        Collections.shuffle(player2.getBoard().getDeck().getCardsInDeck());
        Random ran = new Random();
        int ranNum = ran.nextInt(2);

        if (ranNum == 0) {
            currentPlayer = player1;
            enemyPlayer = player2;
        } else if (ranNum == 1) {
            currentPlayer = player2;
            enemyPlayer = player1;
        } else {
            ui.displayMessage("Something went wrong with ranNum");
        }
        currentPlayer.getBoard().startHandCurrentPlayer();
        enemyPlayer.getBoard().startHandEnemyPlayer();
        gameOver = false;
        heroPowerUsed = false;

        //Giver Mana til den spiller der starter
        currentPlayer.getBoard().setMaxMana(currentPlayer.getBoard().getMaxMana() + 1);
        currentPlayer.getBoard().setCurrentMana(currentPlayer.getBoard().getMaxMana());

        currentPlayer.getBoard().drawCard(1);

        gameLoop();

    }

    public void playerChoiceMenu() {
        ui.displayMessage("\n What would you like to do?");
        ui.displayMessage("1. Play card \n" + "2. Attack with minion \n" + "3. Attack with hero \n" + "4. Use Hero power \n" + "5. End turn");
        switch (ui.getInput()) {

            case "1":
                currentPlayer.getBoard().pickCard(currentPlayer,enemyPlayer);
                break;
            case "2":
                attackWithMinion();
                break;
            case "3":
                attackWithWeapon();
                break;
            case "4":
                heroPower();
                break;
            case "5":
                endTurn();
                break;
            default:
                ui.displayMessage("Your input was not valid, please try again.");
                playerChoiceMenu();
                break;
        }
    }

    public void endTurn() {
        /*try {
            Thread.sleep(1300);*/
            tempPlayer = currentPlayer;
            currentPlayer = enemyPlayer;
            enemyPlayer = tempPlayer;

            heroPowerUsed = false;
            heroReadyToAttack = true;
            currentPlayer.getBoard().setCombo(false);


            //Giver Mana
            if (currentPlayer.getBoard().getMaxMana() < 10) {
                currentPlayer.getBoard().setMaxMana(currentPlayer.getBoard().getMaxMana() + 1);
            }
            currentPlayer.getBoard().setCurrentMana(currentPlayer.getBoard().getMaxMana());


            currentPlayer.getBoard().drawCard(1);



            //Frozon count
            for (Minion m : currentPlayer.getBoard().getMinionsOnBoard()) {
                if(m.getFrozenCount()>0) {
                    m.setFrozenCount(m.getFrozenCount() - 1);
                }
            }
            for (Minion m : enemyPlayer.getBoard().getMinionsOnBoard()) {
                if(m.getFrozenCount()>0) {
                    m.setFrozenCount(m.getFrozenCount() - 1);
                }
            }
            if(currentPlayer.getBoard().getHero().getFrozenCount()>0){
                currentPlayer.getBoard().getHero().setFrozenCount(currentPlayer.getBoard().getHero().getFrozenCount()-1);
            }
            if(enemyPlayer.getBoard().getHero().getFrozenCount()>0){
                enemyPlayer.getBoard().getHero().setFrozenCount(enemyPlayer.getBoard().getHero().getFrozenCount()-1);
            }

            //Minion ready to attack
            for (Minion m : currentPlayer.getBoard().getMinionsOnBoard()) {
                if(m.getFrozenCount()==0) {
                    m.setMinionReadyToAttack(true);
                }
                else{
                    m.setMinionReadyToAttack(false);
                }
            }
            for (Minion m : enemyPlayer.getBoard().getMinionsOnBoard()) {
                m.setMinionReadyToAttack(false);
            }

        /*} catch(InterruptedException e){
            ui.displayMessage("Something's gone terribly wrong.");
        }*/
    }

    public void winCondition() {

        if (currentPlayer.getBoard().getHero().heroDeath() && enemyPlayer.getBoard().getHero().heroDeath()) {
            ui.displayMessage("It's a draw!!  You will be sent back to the main menu \n");
            gameOver = true;
        } else if (currentPlayer.getBoard().getHero().heroDeath()) {
            ui.displayMessage(enemyPlayer.getPlayerName() + " Wins!!! You will be sent back to the main menu \n");
            gameOver = true;
        } else if (enemyPlayer.getBoard().getHero().heroDeath()) {
            ui.displayMessage(currentPlayer.getPlayerName() + " Wins!!! You will be sent back to the main menu \n");
            gameOver = true;
        }

    }

    public void attackWithMinion() {
        if (!currentPlayer.getBoard().getMinionsOnBoard().isEmpty()) {

            if (!currentPlayer.getBoard().getMinionReadyToAttackList().isEmpty()) {

                ui.displayMessage("Pick a minion you want to attack with");
                Minion pickedMinion = currentPlayer.getBoard().pickMinion(currentPlayer.getBoard().getMinionReadyToAttackList());

                if (!enemyPlayer.getBoard().getMinionsOnBoard().isEmpty()) {
                    ui.displayMessage("Do you want to attack an enemy minion or the enemy hero?");
                    ui.displayMessage("1. Enemy minion \n" + "2. Enemy hero");
                    switch (ui.getInput()) {
                        case "1":
                            ui.displayMessage("Pick a minion to attack");
                            currentPlayer.getBoard().minionClash(pickedMinion, enemyPlayer.getBoard().pickMinion(enemyPlayer.getBoard().getMinionsOnBoard()), enemyPlayer.getBoard());
                            break;
                        case "2":
                            currentPlayer.getBoard().minionFace(pickedMinion, enemyPlayer.getBoard().getHero());
                            break;
                        default:
                            ui.displayMessage("Your input was not valid, please try again.");
                            //playerChoiceMenu();
                    }
                } else {
                    currentPlayer.getBoard().minionFace(pickedMinion, enemyPlayer.getBoard().getHero());
                }

            } else {
                ui.displayMessage("You have no minions on the board that can attack this turn");
            }

        } else {
            ui.displayMessage("You have no minions on the board to attack with");
        }
    }

    public void attackWithWeapon() {
        if (currentPlayer.getBoard().getHero().getWeaponSlot().getWeaponSlotAttack() > 0) {
            if (currentPlayer.getBoard().getHero().getFrozenCount() == 0) {
                if (heroReadyToAttack) {


                    ui.displayMessage("Do you want to attack an enemy minion or the enemy hero?");
                    ui.displayMessage("1. Enemy minion \n" + "2. Enemy hero");
                    switch (ui.getInput()) {
                        case "1":
                            if (!enemyPlayer.getBoard().getMinionsOnBoard().isEmpty()) {
                                ui.displayMessage("Pick a minion to attack");
                                currentPlayer.getBoard().heroAttackMinion(currentPlayer.getBoard().getHero(), enemyPlayer.getBoard().pickMinion(enemyPlayer.getBoard().getMinionsOnBoard()), enemyPlayer.getBoard());
                                heroReadyToAttack = false;
                            } else {
                                ui.displayMessage("There are no enemy minions to attack");
                            }
                            break;
                        case "2":
                            currentPlayer.getBoard().heroFace(currentPlayer.getBoard().getHero(), enemyPlayer.getBoard().getHero());
                            heroReadyToAttack = false;
                            break;
                        default:
                            ui.displayMessage("Your input was not valid, please try again.");
                            //playerChoiceMenu();
                    }
                }
                else {
                    ui.displayMessage("You have already attacked.");
                }

            }else{
                ui.displayMessage("Your hero is frozen and is unable to attack.");
            }


        } else{
            ui.displayMessage("You have no weapon equipped.");
        }
    }







    public void heroPower(){
        if(!heroPowerUsed) {
            if (currentPlayer.getBoard().getCurrentMana() > 1) {

                currentPlayer.getBoard().setCurrentMana(currentPlayer.getBoard().getCurrentMana() - 2);
                currentPlayer.getBoard().getHero().getHeroPower().useHeroPower(currentPlayer.getBoard().getHero().getHeroClass(), currentPlayer.getBoard(), enemyPlayer.getBoard(), ui);
                heroPowerUsed = true;

                //Tænkt at være paladin lmao
                if (currentPlayer.getBoard().getHero().getHeroClass().equals("Paladin") && currentPlayer.getBoard().getMinionsOnBoard().size() >= 7) {
                    currentPlayer.getBoard().setCurrentMana(currentPlayer.getBoard().getCurrentMana() + 2);
                    heroPowerUsed = false;
                }

            } else {
                ui.displayMessage("You don't have enough mana to use your hero power. 2 mana required.");
            }
        }
        else{
            ui.displayMessage("Hero power can only be used once per turn.");
        }
    }


}
