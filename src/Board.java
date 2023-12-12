import java.util.LinkedList;

public class Board {

    private int maxMana = 1;
    private int maxBoardSize = 7;
    private LinkedList<Card> cardsOnBoard = new LinkedList<>();

    private Hand hand = new Hand();
    private Deck deck;
    private Hero hero;

    public Board(Hero hero, Deck deck) {
        this.hero = hero;
        this.deck = deck;
    }

    public String playCard() {
        return null;
    }

    public void startHandCurrentPlayer(){
        drawCard(3);
    }
    public void startHandPlayerPreviousPlayer(){
        drawCard(4);
        Spell coin = new Spell("The coin",0);
        hand.getCardsInHand().add(coin);
    }


    public void drawCard(int amount) {
        for (int i=0; i<amount; i++) {
            if (hand.getCardsInHand().size() <= hand.getMaxHandSize()) {
                if (!deck.getCardsInDeck().isEmpty()) {
                    hand.getCardsInHand().add(deck.getCardsInDeck().poll());
                } else {
                    drawFatigue();
                }
            } else {
                deck.getCardsInDeck().remove();
            }
        }
    }

    public void drawFatigue() {
        if(deck.getCardsInDeck().isEmpty()){
            int currentFatigue = deck.getFatigueCount();
            deck.setFatigueCount(currentFatigue+1);
            hero.loseHealth(currentFatigue);

        }

    }

    public void minionClash(Minion m1, Minion m2) {

        m2.loseHealth(m1.getMinionAttack());
        m1.loseHealth(m2.getMinionAttack());

        m1.minionDeath(m1, cardsOnBoard);
        m2.minionDeath(m2, cardsOnBoard);

    }

    public void minionFace(Minion minion, Hero hero) {
        hero.loseHealth(minion.getMinionAttack());

    }

    public void heroFace(Hero h1, Hero h2) {
        h2.loseHealth(h1.getWeaponSlot().getWeaponSlotAttack());

        hero.getWeaponSlot().setCurrentDurability(hero.getWeaponSlot().getCurrentDurability()-1);
        hero.getWeaponSlot().destroyWeapon();

    }

    public void heroAttackMinion(Hero hero, Minion minion) {
        minion.loseHealth(hero.getWeaponSlot().getWeaponSlotAttack());
        hero.getWeaponSlot().setCurrentDurability(hero.getWeaponSlot().getCurrentDurability()-1);
        hero.getWeaponSlot().destroyWeapon();
        minion.minionDeath(minion,cardsOnBoard);
        hero.loseHealth(minion.getMinionAttack());

    }

    public Deck getDeck() {
        return deck;
    }

    public Hero getHero(){
        return this.hero;
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
