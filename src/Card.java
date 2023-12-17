import java.util.ArrayList;

public abstract class Card {
    private String cardName;
    private String heroClass;
    private int cardCost;
    private int originalCost;

    public Card(String cardName, int cardCost){
    this.cardName = cardName;
    this.cardCost = cardCost;
    this.originalCost = cardCost;
    }

    public String getCardName() {
        return cardName;
    }


    public int getCardCost() {
        return cardCost;
    }
    public int getOriginalCost(){
        return originalCost;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
    public void setCardCost(int cardCost){
        this.cardCost = cardCost;
    }
}
