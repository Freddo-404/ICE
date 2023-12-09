import java.util.ArrayList;

public abstract class Card {
    String cardName;
    String heroClass;
    int cardCost;

    public Card(String cardName, int cardCost){
    this.cardName = cardName;
    this.cardCost = cardCost;
    }

    public String getCardName() {
        return cardName;
    }


    public float getCardCost() {
        return cardCost;
    }
}
