public class Player {

    private String playerName;
    private Board board;
    private int mana;
    private TextUI ui;

    public void playerChoiceMenu(){
        ui.displayMessage("What would you like to do?");
        switch(ui.getInput()){
            case "0":
                break;
        }
    }

}
