import java.util.LinkedList;

public class Board {

    private int maxMana = 1;
    private int currentMana;
    private int maxBoardSize = 7;
    private LinkedList<Minion> minionsOnBoard = new LinkedList<>();

    private Hand hand = new Hand();
    private Deck deck;
    private Hero hero;
    private TextUI ui = new TextUI();

    public Board(Hero hero, Deck deck) {
        this.hero = hero;
        this.deck = deck;
    }

    public int getCurrentMana(){
       return currentMana;
}


    public void playCard(Card card) {
        if (card instanceof Minion) {
            Minion minion = (Minion) card;
            playMinion(minion, card);

        } else if (card instanceof Spell) {
            Spell spell = (Spell) card;
            playSpell(spell, card);
        }
        else if (card instanceof Weapon) {
            Weapon weapon = (Weapon) card;
            playWeapon(weapon, card);
        }
       else {
        System.out.println("Something went wrong with instanceof");
        }
    }



    public void playMinion(Minion minion, Card card){
        if(minionsOnBoard.size()>=maxBoardSize){
            ui.displayMessage("Board is full. You can not play more minions.");
        }
        else {
            if (currentMana >= minion.getCardCost()) {
                currentMana = currentMana - minion.getCardCost();
                putMinionBoard(minion);
                getHand().getCardsInHand().remove(card);
            } else {
                ui.displayMessage("Card cost is too high.");
            }
        }
    }
    public void playSpell(Spell spell, Card card){
        if (currentMana >= spell.getCardCost()) {
            currentMana = currentMana - spell.getCardCost();
            ui.displayMessage("You got scammed.");
            getHand().getCardsInHand().remove(card);
        } else {
          ui.displayMessage("Card cost is too high.");
        }

    }
    public void playWeapon(Weapon weapon, Card card){
        if (currentMana >= weapon.getCardCost()) {
            currentMana = currentMana - weapon.getCardCost();
            getHero().equipWeapon(weapon);
            getHand().getCardsInHand().remove(card);

        } else {
          ui.displayMessage("Card cost is too high.");
        }
    }

    public void putMinionBoard(Minion minion) {
        ui.displayMessage("Choose the spot to place your minion");
        ui.displayMessage("1. Spot 1 \n" + "2. Spot 2 \n" + "3. Spot 3 \n" + "4. Spot 4 \n" + "5. Spot 5\n" + "6. Spot 6\n" + "7. Spot 7");
        switch (ui.getInput()) {
            case "1":
                minionsOnBoard.add(0,minion);
                break;
            case "2":
                minionsOnBoard.add(1,minion);
                break;
            case "3":
                minionsOnBoard.add(2,minion);
                break;
            case "4":
                minionsOnBoard.add(3,minion);
                break;
            case "5":
                minionsOnBoard.add(4,minion);
                break;
            case "6":
                minionsOnBoard.add(5,minion);
                break;
            case "7":
                minionsOnBoard.add(6,minion);
                break;
            default:
                ui.displayMessage("Your input was not valid, please try again.");
                break;

        }
    }


    public void startHandCurrentPlayer(){
        drawCard(3);
    }
    public void startHandEnemyPlayer(){
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

        m1.minionDeath(m1, minionsOnBoard);
        m2.minionDeath(m2, minionsOnBoard);

    }

    public void minionFace(Minion minion, Hero hero) {
        hero.loseHealth(minion.getMinionAttack());

    }

    public void heroFace(Hero h1, Hero h2) {
        h2.loseHealth(h1.getWeaponSlot().getWeaponSlotAttack());

        hero.getWeaponSlot().loseDurability();
        hero.getWeaponSlot().destroyWeapon();

    }

    public void heroAttackMinion(Hero hero, Minion minion) {
        minion.loseHealth(hero.getWeaponSlot().getWeaponSlotAttack());
        hero.getWeaponSlot().loseDurability();
        hero.getWeaponSlot().destroyWeapon();
        minion.minionDeath(minion, minionsOnBoard);
        hero.loseHealth(minion.getMinionAttack());

    }

    public Deck getDeck() {
        return deck;
    }

    public Hero getHero() {
        return hero;
    }

    public LinkedList<Minion> getMinionsOnBoard() {
        return minionsOnBoard;
    }

    public Hand getHand() {
        return hand;
    }
    /*public Card targetMinion(TextUI ui){
        Card pickedCard = null;
        for(int i = 0; i<cardsOnBoard.size(); i++) {
            ui.displayMessage(i+1 + ". " + cardsOnBoard.get(i).getCardName());
        }
        ui.displayMessage("Choose a minion.");
        int numberInput = ui.getNumericInputInt("Please enter a valid number.");
        if(numberInput<=cardsOnBoard.size() && numberInput > 0){
            pickedCard = cardsOnBoard.get(numberInput);
        } else{
            ui.displayMessage("Invalid number. Please try again.");
            targetMinion(ui);
        }
        return pickedCard;
    }*/

    public Minion targetMinion(TextUI ui){
        Minion pickedMinion = null;
        for(int i = 0; i< minionsOnBoard.size(); i++) {
            ui.displayMessage(i+1 + ". " + minionsOnBoard.get(i).getCardName());
        }
        ui.displayMessage("Choose a minion.");
        int numberInput = ui.getNumericInputInt("Please enter a valid number.");
        if(numberInput<= minionsOnBoard.size() && numberInput > 0){
            pickedMinion = minionsOnBoard.get(numberInput);
        } else{
            ui.displayMessage("Invalid number. Please try again.");
            targetMinion(ui);
        }
        return pickedMinion;
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
