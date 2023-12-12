import java.util.Collections;
import java.util.Random;

public class Game {

    private int turnCount;
    private TextUI ui;
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
        ui.displayMessage("Please choose a class for " + player1.getPlayerName() + "by entering a number between 1 and 5.");
        player1.chooseHero();
        ui.displayMessage("Please choose a class for " + player2.getPlayerName() + "by entering a number between 1 and 5.");
        player2.chooseHero();

        initializeGame();
    }

    public void initializeGame(){
        Collections.shuffle(player1.getBoard().getDeck().getCardsInDeck());
        Collections.shuffle(player2.getBoard().getDeck().getCardsInDeck());
        Random ran = new Random();
        int ranNum = ran.nextInt(2);

        if (ranNum==1){
            currentPlayer = player1;
            previousPlayer = player2;
        } else if (ranNum==2) {
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

    public void winCondition(){
       if(currentPlayer.getBoard().getHero().heroDeath()){
           ui.displayMessage(currentPlayer.getPlayerName()+ "Wins!!! You will be sent back to the main menu");
           mainMenu();
       }


    }

}
