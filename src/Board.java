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

    public void drawCard(int amount) {
        if (hand.getCardsInHand().size() <= hand.getMaxHandSize()) {
            if (!deck.getCardsInDeck().isEmpty()) {
                hand.getCardsInHand().add(deck.getCardsInDeck().poll());
            }
            else{
                drawFatigue();
            }
        } else {
            deck.getCardsInDeck().remove();
        }
    }

    public String drawFatigue() {
        return null;
    }

    public String minionClash(Minion m1, Minion m2) {

        m2.setMinionCurrentHealth(m2.getMinionCurrentHealth() - m1.getMinionAttack());
        m1.setMinionCurrentHealth(m1.getMinionCurrentHealth() - m2.getMinionAttack());
        String health = m1.getMinionCurrentHealth() + "," + m2.getMinionCurrentHealth();

        m1.minionDeath(m1, cardsOnBoard);

        m2.minionDeath(m2, cardsOnBoard);
        return health;
    }

    public String minionFace(Minion minion, Hero hero) {
        hero.setHeroCurrentHealth(hero.getHeroCurrentHealth() - minion.getMinionAttack());
        String health = String.valueOf(hero.getHeroCurrentHealth());
        return health;
    }

    public String heroFace(Hero h1, Hero h2) {
        h2.setHeroCurrentHealth(hero.getHeroCurrentHealth() - h1.getWeaponSlot().getWeaponSlotAttack());
        String health = String.valueOf(h2.getHeroCurrentHealth());
        return health;
    }

    public String heroAttackMinion(Hero hero, Minion minion) {
        minion.setMinionCurrentHealth(minion.getMinionCurrentHealth() - hero.getWeaponSlot().getWeaponSlotAttack());
        hero.setHeroCurrentHealth(hero.getHeroCurrentHealth() - minion.getMinionAttack());
        String health = String.valueOf(hero.getHeroCurrentHealth() + "," + minion.getMinionCurrentHealth());
        return health;
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
