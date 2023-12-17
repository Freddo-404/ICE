import java.util.ArrayList;

public abstract class Card {
    private String cardName;
    private String heroClass;
    private int cardCost;
    private boolean taunt;

    public Card(String cardName, int cardCost){
    this.cardName = cardName;
    this.cardCost = cardCost;
    }

    public String getCardName() {
        return cardName;
    }


    public int getCardCost() {
        return cardCost;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
    public void setCardCost(int cardCost){
        this.cardCost = cardCost;
    }
}
