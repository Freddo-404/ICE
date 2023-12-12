public class Player {

    private String playerName;
    private Board board;
    private FileIO io = new FileIO();

    public Player(String playerName){
        this.playerName=playerName;
    }


    public void chooseHero(TextUI ui){
        ui.displayMessage("1. Rogue \n" + "2. Mage \n" + "3. Warlock \n" + "4. Paladin \n" + "5. Hunter");
        switch(ui.getInput()){
            case "1":
                board = new Board(new Hero("Valeera", "Rogue"),new  Deck(io.readDeckData("src/card.txt")));
                break;
            case "2":
                board = new Board(new Hero("Jaina", "Mage"),new  Deck(io.readDeckData("src/card.txt")));
                break;
            case "3":
                board = new Board(new Hero("Gul'dan", "Warlock"),new  Deck(io.readDeckData("src/card.txt")));
                break;
            case "4":
                board = new Board(new Hero("Uther", "Paladin"),new  Deck(io.readDeckData("src/card.txt")));
                break;
            case "5":
                board = new Board(new Hero("Rexxar", "Hunter"),new  Deck(io.readDeckData("src/card.txt")));
                break;
            default:
                ui.displayMessage("Your input was not valid, please try again.");
                chooseHero(ui);
                break;
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public Board getBoard() {
        return board;
    }
}
