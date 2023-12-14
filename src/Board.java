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
                putMinionBoard(minion);
                currentMana = currentMana - minion.getCardCost();
                getHand().getCardsInHand().remove(card);
            } else {
                ui.displayMessage("Card cost is too high.");
            }
        }
    }
    public void playSpell(Spell spell, Card card, Player currentPlayer){
        if (currentMana >= spell.getCardCost()) {
            currentMana = currentMana - spell.getCardCost();
            spell.getSpellEffect().useSpellEffect(currentPlayer.getBoard());
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
                if(minionsOnBoard.size()>=1){
                    minionsOnBoard.add(1,minion);
                }
                else{
                minionsOnBoard.add(minion);}
                break;
            case "3":
                if(minionsOnBoard.size()>=2){
                    minionsOnBoard.add(2,minion);
                }
                else{
                    minionsOnBoard.add(minion);}
                break;
            case "4":
                if(minionsOnBoard.size()>=3){
                    minionsOnBoard.add(3,minion);
                }
                else{
                    minionsOnBoard.add(minion);}
                break;
            case "5":
                if(minionsOnBoard.size()>=4){
                    minionsOnBoard.add(4,minion);
                }
                else{
                    minionsOnBoard.add(minion);}
                break;
            case "6":
                if(minionsOnBoard.size()>=5){
                    minionsOnBoard.add(5,minion);
                }
                else{
                    minionsOnBoard.add(minion);}
                break;
            case "7":
                if(minionsOnBoard.size()>=6){
                    minionsOnBoard.add(6,minion);
                }
                else{
                    minionsOnBoard.add(minion);}
                break;
            default:
                ui.displayMessage("Your input was not valid, please try again.");
                break;

        }
    }
    public void pickCard(Player currentPlayer) {
        ui.displayMessage("Pick a card you want to play");
        ui.displayHand(currentPlayer);
        Card card;
        try {
            switch (ui.getInput()) {
                case "1":
                    card = getHand().getCardsInHand().get(0);
                    playCard(card, currentPlayer);
                    break;
                case "2":
                    card = getHand().getCardsInHand().get(1);
                    playCard(card, currentPlayer);
                    break;
                case "3":
                    card = getHand().getCardsInHand().get(2);
                    playCard(card, currentPlayer);
                    break;
                case "4":
                    card = getHand().getCardsInHand().get(3);
                    playCard(card, currentPlayer);
                    break;
                case "5":
                    card = getHand().getCardsInHand().get(4);
                    playCard(card, currentPlayer);
                    break;
                case "6":
                    card = getHand().getCardsInHand().get(5);
                    playCard(card, currentPlayer);
                    break;
                case "7":
                    card = getHand().getCardsInHand().get(6);
                    playCard(card, currentPlayer);
                    break;
                case "8":
                    card = getHand().getCardsInHand().get(7);
                    playCard(card, currentPlayer);
                    break;
                case "9":
                    card = getHand().getCardsInHand().get(8);
                    playCard(card, currentPlayer);
                    break;
                case "10":
                    card = getHand().getCardsInHand().get(9);
                    playCard(card, currentPlayer);
                    break;
                default:
                    ui.displayMessage("Your input was invalid, please try again");
                    pickCard(currentPlayer);
            }
        } catch (IndexOutOfBoundsException e) {
            ui.displayMessage("Please pick a card from the list");
            pickCard(currentPlayer);
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

    public void minionClash(Minion myMinion, Minion enemyMinion, Board enemyBoard) {

        enemyMinion.loseHealth(myMinion.getMinionAttack());
        myMinion.loseHealth(enemyMinion.getMinionAttack());

        myMinion.minionDeath(myMinion, minionsOnBoard);
        enemyMinion.minionDeath(enemyMinion, enemyBoard.getMinionsOnBoard());

        myMinion.setMinionReadyToAttack(false);

    }

    public void minionFace(Minion minion, Hero hero) {
        hero.loseHealth(minion.getMinionAttack());
        minion.setMinionReadyToAttack(false);
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
/*
    public Minion pickMinion(){
        ui.displayMinionsOnBoardlist(minionsOnBoard);
        try {
            switch (ui.getInput()) {
                case "1":
                    return minionsOnBoard.get(0);
                case "2":
                    return minionsOnBoard.get(1);
                case "3":
                    return minionsOnBoard.get(2);
                case "4":
                    return minionsOnBoard.get(3);
                case "5":
                    return minionsOnBoard.get(4);
                case "6":
                    return minionsOnBoard.get(5);
                case "7":
                    return minionsOnBoard.get(6);
                default:
                    ui.displayMessage("Your input was invalid, please try again");
                    pickMinion();
            }
        } catch (IndexOutOfBoundsException e) {
            ui.displayMessage("Please pick a minion from the list");
            pickMinion();
        }
        return null;
    }

 */
    public Minion pickMinion(LinkedList<Minion> minionList){
        ui.displayMinionsOnBoardlist(minionList);
        try {
            switch (ui.getInput()) {
                case "1":
                    return minionList.get(0);
                case "2":
                    return minionList.get(1);
                case "3":
                    return minionList.get(2);
                case "4":
                    return minionList.get(3);
                case "5":
                    return minionList.get(4);
                case "6":
                    return minionList.get(5);
                case "7":
                    return minionList.get(6);
                default:
                    ui.displayMessage("Your input was invalid, please try again");
                    pickMinion(minionList);
            }
        } catch (IndexOutOfBoundsException e) {
            ui.displayMessage("Please pick a minion from the list");
            pickMinion(minionList);
        }
        return pickMinion(minionList);
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
    public LinkedList<Minion> getMinionReadyToAttackList(){
        LinkedList<Minion> minionReadyToAttackList = new LinkedList<>();
        for(Minion m : minionsOnBoard){
            if(m.getMinionReadyToAttack()){
                minionReadyToAttackList.add(m);
            }
        }
        return minionReadyToAttackList;
    }

}

