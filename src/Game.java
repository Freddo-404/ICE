import java.util.Collections;

public class Game {

    private int turnCount;
    private TextUI ui;
    private Player player1;
    private Player player2;

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

    }

    public void winCondition(){

    }

}
