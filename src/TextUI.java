import java.util.LinkedList;
import java.util.Scanner;

public class TextUI {
    protected Scanner scan = new Scanner(System.in);

    //shows a message and returns the user's input as a String
    public String getInput(){
        return scan.nextLine();
    }


    public void displayMessage(String msg){

        System.out.println(msg);

    }



    public float getNumericInput(String msg) {
        System.out.println(msg);
        String input = getInput();
        float num;
        try {
            num = Float.parseFloat(input);
        }catch (NumberFormatException var5){
            displayMessage("Please enter a number and use '.' instead of ','");
            num= this.getNumericInput(msg);
        }
        return num;
    }

    public int getNumericInputInt(String msg) {
        System.out.println(msg);
        String input = getInput();
        int num;
        try {
            num = Integer.parseInt(input);
        }catch (NumberFormatException var5){
            displayMessage("Please enter a number");
            num= this.getNumericInputInt(msg);
        }
        return num;
    }

    public void displayMinionsOnBoardlist(LinkedList<Minion> minionsOnBoard){
        int count = 1;
        for (Minion m : minionsOnBoard){
            displayMessage(count + ". " +m.getCardName() + ", Attack: "+m.getMinionAttack() + ", Health: "+m.getMinionCurrentHealth());
            count++;
        }

    }

    public void displayHand(Player player) {
        displayMessage(player.getPlayerName() + "'s hand:");
        int count = 1;



        for (Card c : player.getBoard().getHand().getCardsInHand()) {

            if (c instanceof Spell) {
                Spell spell = (Spell) c;
                displayMessage(count + ". " + "Spell: "+spell.getCardName() + ", Mana cost: "+spell.getCardCost());
            } else if (c instanceof Weapon) {
                Weapon weapon = (Weapon) c;
                displayMessage(count + ". " + "Weapon: "+weapon.getCardName()+ ", Mana cost: "+weapon.getCardCost()+ ", Attack: "+weapon.getWeaponAttack()+", Durability: "+weapon.getWeaponDurability());
            } else if (c instanceof Minion) {
                String taunt= "";
                Minion minion = (Minion) c;
                if(minion.getTaunt()){ taunt= ", Taunt.";

                }
                displayMessage(count  + ". " +"Minion: "+ minion.getCardName() + ", Mana cost: "+minion.getCardCost() + ", Attack: "+minion.getMinionAttack() + ", Health: "+minion.getMinionMaxHealth()+ taunt);
            }else{
                System.out.println("Something with instanceof isn't working.");
            }
            count++;
        }
    }

    public void displayNumberOfCardsInHand(Player enemyPlayer){
        displayMessage(enemyPlayer.getPlayerName() + "'s number of cards in hand: "+enemyPlayer.getBoard().getHand().getCardsInHand().size());
    }

    public String displayMinion(Minion minion){
        String ready = "";
        String taunt = "";
        if(minion.getFrozenCount()>0){
            ready="F";
        }
        if(minion.getMinionReadyToAttack()){
            ready="*";
        }
        if(minion.getTaunt()){
            taunt="T";
        }

        return taunt+("["+minion.getMinionAttack()+"/"+minion.getMinionCurrentHealth())+"]"+ready;

    }

    public String displayMinionsOnBoard(LinkedList<Minion> minionsOnBoard){
        String str = "   ";
        for (Minion m: minionsOnBoard) {
            str+=displayMinion(m);
            str+="   ";
        }

        return str;
    }



    public void displayBoard(Board myBoard,Board enemyBoard){
        String friendlyFrozon = "";
        String enemyFrozon = "";
        if(myBoard.getHero().getFrozenCount()>0){
            friendlyFrozon="F";
        }
        if(enemyBoard.getHero().getFrozenCount()>0){
            enemyFrozon="F";
        }


        String str = "";

        str+= "________"+"Weapon: "+enemyBoard.getHero().getWeaponSlot().getWeaponSlotAttack()+"/"+enemyBoard.getHero().getWeaponSlot().getCurrentDurability()+"_________HERO HEALTH: "+enemyBoard.getHero().getHeroCurrentHealth()+" "+enemyFrozon+"_______deck: "+enemyBoard.getDeck().getCardsInDeck().size()+"_______"+"\n";
        str+="|                                                 mana: "+enemyBoard.getCurrentMana()+"/"+enemyBoard.getMaxMana()+"      "+"\n";
        str+= displayMinionsOnBoard(enemyBoard.getMinionsOnBoard())+"\n";
        str+="|                                                                |"+"\n";
        str+="|                                                                |"+"\n";
        str+="|________________________________________________________________|"+"\n";
        str+="|                                                                |"+"\n";
        str+="|                                                                |"+"\n";
        str+="|                                                                |"+"\n";
        str+=displayMinionsOnBoard(myBoard.getMinionsOnBoard())+"\n";
        str+="|                                                 mana: "+myBoard.getCurrentMana()+"/"+myBoard.getMaxMana()+"      "+"\n";
        str+="|_______"+"Weapon: "+myBoard.getHero().getWeaponSlot().getWeaponSlotAttack()+"/"+myBoard.getHero().getWeaponSlot().getCurrentDurability()+"________HERO HEALTH: "+myBoard.getHero().getHeroCurrentHealth()+" "+friendlyFrozon+"_______deck: "+myBoard.getDeck().getCardsInDeck().size()+"_______|"+"\n";
        displayMessage(str);
    }

    public void displayGame(Player currentPlayer,Player enemyPlayer){
        displayNumberOfCardsInHand(enemyPlayer);
        displayBoard(currentPlayer.getBoard(), enemyPlayer.getBoard());
        displayHand(currentPlayer);
    }

}
