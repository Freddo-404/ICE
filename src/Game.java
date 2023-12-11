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
        player1.setPlayerName(ui.getInput());
        ui.displayMessage("Please enter a name for Player 2.");
        player2.setPlayerName(ui.getInput());
        ui.displayMessage("Please choose a class for " + player1.getPlayerName() + "by entering a number between 1 and 5.");
        chooseHero(player1);
        ui.displayMessage("Please choose a class for " + player2.getPlayerName() + "by entering a number between 1 and 5.");
        chooseHero(player2);
    }

    public void chooseHero(Player player){
        ui.displayMessage("1. Rogue \n" + "2. Mage \n" + "3. Warlock \n" + "4. Paladin \n" + "5. Hunter");
        switch(ui.getInput()){
            case "1":
                Hero playerRogue = new Hero("Valeera", "Rogue");
                break;
            case "2":
                Hero playerMage = new Hero("Jaina", "Mage");
                break;
            case "3":
                Hero playerWarlock = new Hero("Gul'dan", "Warlock");
                break;
            case "4":
                Hero playerPaladin = new Hero("Uther", "Paladin");
                break;
            case "5":
                Hero playerHunter = new Hero("Rexxar", "Hunter");
                break;
            default:
            ui.displayMessage("Your input was not valid, please try again.");
            chooseHero(player);
            break;
        }
    }
    public void winCondition(){

    }

}
