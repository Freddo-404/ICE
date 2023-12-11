import java.util.LinkedList;

public class Hand {
  private  int maxHandSize = 10;
  private LinkedList<Card>cardsInHand = new LinkedList<>();


    public LinkedList<Card> getCardsInHand() {
        return cardsInHand;
    }

  public int getMaxHandSize() {
    return maxHandSize;
  }
}
