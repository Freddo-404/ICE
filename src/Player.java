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
                this.board = new Board(new Hero("Valeera", "Rogue"),new  Deck(io.readDeckData("src/rogue.txt")));
                break;
            case "2":
                this.board = new Board(new Hero("Jaina", "Mage"),new  Deck(io.readDeckData("src/mage.txt")));
                break;
            case "3":
                this.board = new Board(new Hero("Gul'dan", "Warlock"),new  Deck(io.readDeckData("src/warlock.txt")));
                break;
            case "4":
                this.board = new Board(new Hero("Uther", "Paladin"),new  Deck(io.readDeckData("src/paladin.txt")));
                break;
            case "5":
                this.board = new Board(new Hero("Rexxar", "Hunter"),new  Deck(io.readDeckData("src/hunter.txt")));
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

