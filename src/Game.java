import java.util.Collections;
import java.util.Random;

public class Game {

    private TextUI ui = new TextUI();
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player enemyPlayer;
    private Player tempPlayer;
    private Boolean gameOver;



    public void mainMenu(){
        ui.displayMessage("Hello and welcome to ScuffedStone! \n"+"\n"+"Please select a choice"+"\n"+
                "1. Play \n" + "2. Quit");
        String input = ui.getInput();
        switch (input){
            case "1":
                startUp();
                break;
            case "2":
                break;
        }


    }

    public void gameLoop(){

        while(!gameOver) {
            ui.displayGame(currentPlayer, enemyPlayer);
            playerChoiceMenu();
            winCondition();
        }
        mainMenu();
    }

    public void startUp(){
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

    public void initializeGame(){
        Collections.shuffle(player1.getBoard().getDeck().getCardsInDeck());
        Collections.shuffle(player2.getBoard().getDeck().getCardsInDeck());
        Random ran = new Random();
        int ranNum = ran.nextInt(2);

        if (ranNum==0){
            currentPlayer = player1;
            enemyPlayer = player2;
        } else if (ranNum==1) {
            currentPlayer = player2;
            enemyPlayer = player1;
        }
        else{
            ui.displayMessage("Something went wrong with ranNum");
        }
        currentPlayer.getBoard().startHandCurrentPlayer();
        enemyPlayer.getBoard().startHandEnemyPlayer();
        gameOver=false;

        //Giver Mana til den spiller der starter
        currentPlayer.getBoard().setMaxMana(currentPlayer.getBoard().getMaxMana()+1);
        currentPlayer.getBoard().setCurrentMana(currentPlayer.getBoard().getMaxMana());

        currentPlayer.getBoard().drawCard(1);

        gameLoop();

    }

    public void playerChoiceMenu(){
        ui.displayMessage("\n What would you like to do?");
        ui.displayMessage("1. Play card \n" + "2. Attack with minion \n" + "3. Attack with hero \n" + "4. Use Hero power \n" + "5. End turn");
        switch(ui.getInput()){

            case "1":
                playerChoiceMenu();
                break;
            case "2":
                playerChoiceMenu();
                break;
            case "3":
                playerChoiceMenu();
                break;
            case "4":
                playerChoiceMenu();
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

    public void endTurn(){

        tempPlayer = currentPlayer;
        currentPlayer = enemyPlayer;
        enemyPlayer = tempPlayer;

        //Giver Mana
        if(currentPlayer.getBoard().getMaxMana()<10) {
            currentPlayer.getBoard().setMaxMana(currentPlayer.getBoard().getMaxMana() + 1);
        }
        currentPlayer.getBoard().setCurrentMana(currentPlayer.getBoard().getMaxMana());


        currentPlayer.getBoard().drawCard(1);


    }
    public void winCondition(){

        if(currentPlayer.getBoard().getHero().heroDeath() && enemyPlayer.getBoard().getHero().heroDeath()){
            ui.displayMessage("It's a draw!!  You will be sent back to the main menu \n");
            gameOver=true;
        }
        else if(currentPlayer.getBoard().getHero().heroDeath()){
           ui.displayMessage(enemyPlayer.getPlayerName()+ " Wins!!! You will be sent back to the main menu \n");
           gameOver=true;
       } else if (enemyPlayer.getBoard().getHero().heroDeath()) {
            ui.displayMessage(currentPlayer.getPlayerName()+ " Wins!!! You will be sent back to the main menu \n");
            gameOver=true;
       }

    }

}
