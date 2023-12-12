import java.util.Collections;
import java.util.Random;

public class Game {

    private int turnCount;
    private TextUI ui = new TextUI();
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player previousPlayer;

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
    ui.displayBoard(currentPlayer.getBoard(),previousPlayer.getBoard());
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
            previousPlayer = player2;
        } else if (ranNum==1) {
            currentPlayer = player2;
            previousPlayer = player1;
        }
        else{
            ui.displayMessage("Something went wrong with ranNum");
        }
        currentPlayer.getBoard().startHandCurrentPlayer();
        previousPlayer.getBoard().startHandPlayerPreviousPlayer();
        gameLoop();

    }

    public void playerChoiceMenu(){
        ui.displayMessage("What would you like to do?");
        ui.displayMessage("1. Play card \n" + "2. Attack with minion \n" + "3. Attack with hero \n" + "4. Use Hero power \n" + "5. End turn");
        switch(ui.getInput()){

            case "1":
                //currentPlayer.getBoard().playCard();
                break;
            case "2":
                //currentPlayer.getBoard().pickMinion();
                break;
            case "3":
                //pickTarget(); and attack
                break;
            case "4":
                currentPlayer.getBoard().getHero().getHeroPower().useHeroPower(currentPlayer.getBoard().getHero(), currentPlayer.getBoard());
                break;
            case "5":
                //endTurn();
                break;
            default:
                ui.displayMessage("Your input was not valid, please try again.");
                playerChoiceMenu();
                break;
        }
    }

    public void winCondition(){

    }

}
