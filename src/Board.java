import java.util.LinkedList;
import java.util.Random;

public class Board {

    private int maxMana = 0;
    private int currentMana = 0;
    private int fatigueCount = 0;
    private int maxBoardSize = 7;
    private LinkedList<Minion> minionsOnBoard = new LinkedList<>();
    private LinkedList<Minion> minionsWithTaunt = new LinkedList<>();


    private Hand hand = new Hand();
    private Deck deck;
    private Hero hero;
    private TextUI ui = new TextUI();
    private Boolean combo = false;
    public Board(Hero hero, Deck deck) {
        this.hero = hero;
        this.deck = deck;
    }




    public void playCard(Card card, Board myBoard, Board enemyPlayer) {
        if (card instanceof Minion) {
            Minion minion = (Minion) card;
            playMinion(minion, card);
           combo = true;

        } else if (card instanceof Spell) {
            Spell spell = (Spell) card;
            playSpell(spell, card, myBoard, enemyPlayer);
            combo = true;

        }
        else if (card instanceof Weapon) {
            Weapon weapon = (Weapon) card;
            playWeapon(weapon, card);
            combo = true;

        }
       else {
        System.out.println("Something went wrong with instanceof");
        }
       card.setCardCost(card.getOriginalCost());
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
                if(minion.getTaunt()){
                    minionsWithTaunt.add(minion);
                }
            } else {
                ui.displayMessage("Card cost is too high.");
            }
        }
    }
    public void playSpell(Spell spell, Card card, Board myBoard, Board enemyBoard){
        if (currentMana >= spell.getCardCost()) {
            Boolean activated = spell.getSpellEffect().useSpellEffect(myBoard,enemyBoard,ui);
            if(activated) {
                currentMana = currentMana - spell.getCardCost();
                getHand().getCardsInHand().remove(card);
            }

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
                minionsOnBoard.add(0, minion);
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
                hand.getCardsInHand().add(minion);
                setCurrentMana(getCurrentMana()+ minion.getCardCost());
                ui.displayMessage("Your input was not valid, please try again.");
                break;

        }
    }

    public void pickCard(Player currentPlayer, Player enemyPlayer) {
        ui.displayMessage("Pick a card you want to play");
        ui.displayHand(currentPlayer);
        Card card;
        try {
            switch (ui.getInput()) {
                case "1":
                    card = getHand().getCardsInHand().get(0);
                    playCard(card, currentPlayer.getBoard(),enemyPlayer.getBoard());
                    break;
                case "2":
                    card = getHand().getCardsInHand().get(1);
                    playCard(card, currentPlayer.getBoard(),enemyPlayer.getBoard());
                    break;
                case "3":
                    card = getHand().getCardsInHand().get(2);
                    playCard(card, currentPlayer.getBoard(),enemyPlayer.getBoard());
                    break;
                case "4":
                    card = getHand().getCardsInHand().get(3);
                    playCard(card, currentPlayer.getBoard(),enemyPlayer.getBoard());
                    break;
                case "5":
                    card = getHand().getCardsInHand().get(4);
                    playCard(card, currentPlayer.getBoard(),enemyPlayer.getBoard());
                    break;
                case "6":
                    card = getHand().getCardsInHand().get(5);
                    playCard(card, currentPlayer.getBoard(),enemyPlayer.getBoard());
                    break;
                case "7":
                    card = getHand().getCardsInHand().get(6);
                    playCard(card, currentPlayer.getBoard(),enemyPlayer.getBoard());
                    break;
                case "8":
                    card = getHand().getCardsInHand().get(7);
                    playCard(card, currentPlayer.getBoard(),enemyPlayer.getBoard());
                    break;
                case "9":
                    card = getHand().getCardsInHand().get(8);
                    playCard(card, currentPlayer.getBoard(),enemyPlayer.getBoard());
                    break;
                case "10":
                    card = getHand().getCardsInHand().get(9);
                    playCard(card, currentPlayer.getBoard(),enemyPlayer.getBoard());
                    break;
                default:
                    ui.displayMessage("Your input was invalid, please try again");
                    pickCard(currentPlayer,enemyPlayer);
            }
        } catch (IndexOutOfBoundsException e) {
            ui.displayMessage("Please pick a card from the list");
            pickCard(currentPlayer,enemyPlayer);
        }

    }


    public void startHandCurrentPlayer(){
        drawCard(3);
    }

    public void startHandEnemyPlayer() {
        drawCard(4);
        Spell coin = new Spell("The coin", 0);
        hand.getCardsInHand().add(coin);
    }


    public void drawCard(int amount) {
        for (int i = 0; i < amount; i++) {
            if (!deck.getCardsInDeck().isEmpty()) {
                if (hand.getCardsInHand().size() < hand.getMaxHandSize()) {
                    hand.getCardsInHand().add(deck.getCardsInDeck().poll());
                } else {
                    Card discardedCard = deck.getCardsInDeck().poll();

                    if (discardedCard instanceof Spell) {
                        Spell spell = (Spell) discardedCard;
                        ui.displayMessage("Your hand is full. Discarded: " + spell.getCardName() + ", Mana cost: " + spell.getCardCost());
                    } else if (discardedCard instanceof Weapon) {
                        Weapon weapon = (Weapon) discardedCard;
                        ui.displayMessage("Your hand is full. Discarded: " + weapon.getCardName() + ", Mana cost: " + weapon.getCardCost() + ", Attack: " + weapon.getWeaponAttack() + ", Durability: " + weapon.getWeaponDurability());

                    } else if (discardedCard instanceof Minion) {
                        Minion minion = (Minion) discardedCard;
                        ui.displayMessage("Your hand is full. Discarded: " + minion.getCardName() + ", Mana cost: " + minion.getCardCost() + ", Attack: " + minion.getMinionAttack() + ", Health: " + minion.getMinionMaxHealth());
                    } else {
                        System.out.println("Something with instanceof isn't working.");
                    }
                }

            } else {
                drawFatigue();
            }
        }
    }




    public void drawFatigue() {
        setFatigueCount(getFatigueCount() + 1);
        hero.loseHealth(fatigueCount);

    }
    public void discardCard(int amount){
        int ranNum;
        for (int i = 0; i < amount; i++) {
            if (!getHand().getCardsInHand().isEmpty()) {
                Random ran = new Random();
                ranNum = ran.nextInt(getHand().getCardsInHand().size());
                getHand().getCardsInHand().remove(ranNum);
            }
        }
    }
    public boolean CheckIfTaunt(Board enemyboard) {
        for (Minion t : enemyboard.minionsOnBoard) {
            if (t.getTaunt()) {
                return true;

            }
        }
        return false;
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

    public void heroAttackMinion(Hero hero, Minion enemyMinion, Board enemyBoard) {
        enemyMinion.loseHealth(hero.getWeaponSlot().getWeaponSlotAttack());
        hero.getWeaponSlot().loseDurability();
        hero.getWeaponSlot().destroyWeapon();
        enemyMinion.minionDeath(enemyMinion, enemyBoard.getMinionsOnBoard());
        hero.loseHealth(enemyMinion.getMinionAttack());

    }

    public Minion pickMinion(LinkedList<Minion> minionList){

        ui.displayMinionsOnBoardlist(minionList);
        Minion minion = null;

        if(!minionList.isEmpty()) {
            try {
                switch (ui.getInput()) {
                    case "1":
                        minion = minionList.get(0);
                        break;
                    case "2":
                        minion = minionList.get(1);
                        break;
                    case "3":
                        minion = minionList.get(2);
                        break;
                    case "4":
                        minion = minionList.get(3);
                        break;
                    case "5":
                        minion = minionList.get(4);
                        break;
                    case "6":
                        minion = minionList.get(5);
                        break;
                    case "7":
                        minion = minionList.get(6);
                        break;
                    default:
                        ui.displayMessage("Your input was invalid, please try again");
                        return pickMinion(minionList);
                }
            } catch (IndexOutOfBoundsException e) {
                ui.displayMessage("Please pick a minion from the list");
                return pickMinion(minionList);
            }
            return minion;
        }
        return minion;
    }

    public Minion friendlyOrEnemyMinion(Board enemyBoard, TextUI ui) {
        ui.displayMessage("1. Friendly minion \n2. Enemy minion \n");
        Minion minion = null;
        switch (ui.getInput()){
            case "1":
                minion = pickMinion(getMinionsOnBoard());
                break;
            case "2":
                minion = pickMinion(enemyBoard.getMinionsOnBoard());
                break;
            default:
                return friendlyOrEnemyMinion(enemyBoard, ui);
        }
        return minion;
    }
        public Minion enemyOrFriendlyMinion(Board enemyBoard, TextUI ui) {
            ui.displayMessage("1. Enemy minion \n2. Friendly minion \n");
            Minion minion = null;
            switch (ui.getInput()){
                case "1":
                    minion = pickMinion(enemyBoard.getMinionsOnBoard());
                    break;
                case "2":
                    minion = pickMinion(getMinionsOnBoard());
                    break;
                default:
                    return enemyOrFriendlyMinion(enemyBoard, ui);
        }
            return minion;
        }

        public void fireballMinion (int dmg, LinkedList<Minion> minionList){
            if(!minionList.isEmpty()) {
                Minion pickedMinion = pickMinion(minionList);

                pickedMinion.loseHealth(dmg);
                pickedMinion.minionDeath(pickedMinion, minionList);
            } else {
                ui.displayMessage("There are no minions to target.");
            }
        }

        public void fireballHero (int dmg, Hero heroTarget){
            heroTarget.loseHealth(dmg);
        }

        public void fireballAny (int dmg, Board enemyBoard){
            if(!(minionsOnBoard.isEmpty() && enemyBoard.getMinionsOnBoard().isEmpty())) {
                int input = ui.getNumericInputInt("Pick the desired type of target. \n 1. Minion \n 2. Heroes");
                switch (input) {
                    case 1:
                        if (minionsOnBoard.isEmpty()) {
                            fireballMinion(dmg, enemyBoard.minionsOnBoard);
                        } else if (enemyBoard.getMinionsOnBoard().isEmpty()) {
                            fireballMinion(dmg, minionsOnBoard);
                        } else {
                            int inputMinion = ui.getNumericInputInt("Would you like to target an enemy minion or a friendly minion? \n 1. Enemy \n 2. Friendly");
                            switch (inputMinion) {
                                case 1:
                                    fireballMinion(dmg, enemyBoard.minionsOnBoard);
                                    break;
                                case 2:
                                    fireballMinion(dmg, minionsOnBoard);
                                    break;
                                default:
                                    ui.displayMessage("Your input was invalid. Please try again.");
                                    fireballAny(dmg, enemyBoard);
                            }
                        }


                        break;
                    case 2:
                        int inputHero = ui.getNumericInputInt("Would you like to target an enemy hero or a friendly hero? \n 1. Enemy \n 2. Friendly");
                        switch (inputHero) {
                            case 1:
                                fireballHero(dmg, enemyBoard.getHero());

                                break;
                            case 2:
                                fireballHero(dmg, hero);
                                break;
                            default:
                                ui.displayMessage("Your input was invalid. Please try again.");
                                fireballAny(dmg, enemyBoard);
                        }

                }

            }
            else{
                int inputHero2 = ui.getNumericInputInt("Would you like to target an enemy hero or a friendly hero? \n 1. Enemy \n 2. Friendly");
                switch (inputHero2) {
                    case 1:
                        fireballHero(dmg, enemyBoard.getHero());

                        break;
                    case 2:
                        fireballHero(dmg, hero);
                        break;
                    default:
                        ui.displayMessage("Your input was invalid. Please try again.");
                        fireballAny(dmg, enemyBoard);
                }
            }
        }


        public int getCurrentMana () {
            return currentMana;
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

    public LinkedList<Minion> getMinionsWithTaunt() {
        return minionsWithTaunt;
    }

    public void setFatigueCount(int fatigueCount) {
        this.fatigueCount = fatigueCount;
    }
    public boolean getCombo(){
        return combo;
    }
    public void setCombo(boolean combo){
        this.combo = combo;
    }


}

