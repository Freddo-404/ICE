import java.util.LinkedList;

public class Board {

    private int maxMana;
    private int maxBoardSize;
    private LinkedList<Card> cardsOnBoard;
    private Hand hand;
    private Deck deck;
    private Hero hero;
    private TextUI ui;

    public String playCard(){
       return null;
    }

    public String drawCard(){
        return null;
    }

    public String drawFatigue(){
        return null;
    }

    public String minionClash(Minion m1, Minion m2){

        int m2Health = m2.getMinionCurrentHealth()-m1.getMinionAttack();
        int m1Health = m1.getMinionCurrentHealth()-m2.getMinionAttack();
        String health = m1Health + " " + m2Health;
        return health;
    }

    public String minionFace(Minion minion, Hero hero){
        return null;
    }
}
