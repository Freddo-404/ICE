import java.util.LinkedList;

public class Board {

    private int maxMana = 0;
    private int currentMana = 0;
    private int fatigueCount = 0;
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




    public void playCard(Card card, Player currentPlayer) {
        if (card instanceof Minion) {
            Minion minion = (Minion) card;
            playMinion(minion, card);

        } else if (card instanceof Spell) {
            Spell spell = (Spell) card;
            playSpell(spell, card, currentPlayer);
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
    public void playSpell(Spell spell, Card card, Player currentPlayer){
        if (currentMana >= spell.getCardCost()) {
            currentMana = currentMana - spell.getCardCost();
            spell.getSpellEffect().useSpellEffect(spell, currentPlayer.getBoard());
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
    public Card pickCard(Player player) {
        ui.displayMessage("Pick a card you want to play");
        ui.displayHand(player);
        Card card = null;
        try {
            switch (ui.getInput()) {
                case "1":
                    card = getHand().getCardsInHand().get(0);

                    break;
                case "2":
                    card = getHand().getCardsInHand().get(1);

                    break;
                case "3":
                    card = getHand().getCardsInHand().get(2);

                    break;
                case "4":
                    card = getHand().getCardsInHand().get(3);

                    break;
                case "5":
                    card = getHand().getCardsInHand().get(4);

                    break;
                case "6":
                    card = getHand().getCardsInHand().get(5);

                    break;
                case "7":
                    card = getHand().getCardsInHand().get(6);

                    break;
                case "8":
                    card = getHand().getCardsInHand().get(7);

                    break;
                case "9":
                    card = getHand().getCardsInHand().get(8);

                    break;
                case "10":
                    card = getHand().getCardsInHand().get(9);

                    break;
                default:
                    ui.displayMessage("Your input was invalid, please try again");
                    pickCard(player);
            }
        } catch (IndexOutOfBoundsException e) {
            ui.displayMessage("Please pick a card from the list");
            pickCard(player);
        }
        return card;

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
            if (!deck.getCardsInDeck().isEmpty()) {
                if (hand.getCardsInHand().size() < hand.getMaxHandSize()) {
                    hand.getCardsInHand().add(deck.getCardsInDeck().poll());
                }
                    else {
                        deck.getCardsInDeck().remove();
                    }
            }
            else {
                drawFatigue();
            }
        }
    }

    public void drawFatigue() {
        setFatigueCount(getFatigueCount()+1);
        hero.loseHealth(fatigueCount);

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
    public Minion targetMinion(TextUI ui, Board enemyBoard){
        Minion pickedMinion = null;
        for(int i = 0; i< enemyBoard.minionsOnBoard.size(); i++) {
            ui.displayMessage(i+1 + ". " + enemyBoard.minionsOnBoard.get(i).getCardName());
        }
        ui.displayMessage("Choose a minion.");
        int numberInput = ui.getNumericInputInt("Please enter a valid number.");
        if(numberInput<= enemyBoard.minionsOnBoard.size() && numberInput > 0){
            pickedMinion = enemyBoard.minionsOnBoard.get(numberInput);
        } else{
            ui.displayMessage("Invalid number. Please try again.");
            targetMinion(ui, enemyBoard);
        }
        return pickedMinion;
    }


    public int getCurrentMana(){
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
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

    public Hand getHand(){
        return hand;
    }

    public int getFatigueCount(){
        return fatigueCount;
    }
    public void setFatigueCount(int fatigueCount) {
        this.fatigueCount = fatigueCount;
    }


}

