import java.util.ArrayList;
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

    public String displayMinion(Card card){
        if (card instanceof Minion) {
            Minion minion = (Minion) card;
            return ("["+minion.getMinionCurrentHealth()+"/"+minion.getMinionAttack())+"]";
        }
        else{
            return "DisplayMinion Error";
        }


    }


    public String displayCardsOnBoard(LinkedList<Card> cardsOnBoard){
        String str = "     ";
        for (Card card : cardsOnBoard) {
            str+=displayMinion(card);
            str+="     ";
        }

        return str;
    }

    public void displayBoard(Board myBoard,Board enemyBoard){
        String str = "";
        str+= "_____________________________HERO HEALTH: "+myBoard.getHero().getHeroCurrentHealth()+"______deck: "+enemyBoard.getDeck().getCardsInDeck().size()+"________"+"\n";
        str+="|                                                  mana: "+enemyBoard.getCurrentMana()+"      |"+"\n";
        str+= displayCardsOnBoard(enemyBoard.getCardsOnBoard())+"\n";
        str+="|                                                                |"+"\n";
        str+="|                                                                |"+"\n";
        str+="|________________________________________________________________|"+"\n";
        str+="|                                                                |"+"\n";
        str+="|                                                                |"+"\n";
        str+="|                                                                |"+"\n";
        str+=displayCardsOnBoard(myBoard.getCardsOnBoard())+"\n";
        str+="|                                                 mana: "+myBoard.getCurrentMana()+"         |"+"\n";
        str+="|____________________________HERO HEALTH: "+myBoard.getHero().getHeroCurrentHealth()+"______deck: "+myBoard.getDeck().getCardsInDeck().size()+"_______|"+"\n";
        System.out.println(str);
    }


}
