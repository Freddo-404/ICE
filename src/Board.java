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

        //minions lack a way to die when hp reaches 0
        int m2Health = m2.getMinionCurrentHealth()-m1.getMinionAttack();
        int m1Health = m1.getMinionCurrentHealth()-m2.getMinionAttack();
        String health = m1Health + " " + m2Health;
        return health;
    }

    public String minionFace(Minion minion, Hero hero){
        return null;
    }
}
/*
_____________________________HERO HEALTH: 30______deck: 20________
|                                                  mana:4        |
|        {2/3}    [5/5]         [3/3]                            |
|                                                                |
|                                                                |
|________________________________________________________________|
|                                                                |
|                                                                |
|                                                                |
|    [2/3]    [5/5]         [3/3]                                |
|                                                 mana:5         |
|____________________________HERO HEALTH: 25______deck: 15_______|
*/
