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
                //en eller anden metode her
                break;
            case "2":
                break;
        }


    }

    public void gameLoop(){

    }

    public void winCondition(){

    }

}
