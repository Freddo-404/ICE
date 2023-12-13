import java.util.LinkedList;

public class Deck {

   private int fatigueCount = 0;

   private LinkedList<Card> cardsInDeck = new LinkedList<>();

   public Deck(LinkedList<Card> cardsInDeck){
      this.cardsInDeck = cardsInDeck;
   }
public LinkedList<Card> getCardsInDeck(){
      return cardsInDeck;
}



   public int getFatigueCount(){
      return fatigueCount;
   }
   public void setFatigueCount(int fatigueCount) {
      this.fatigueCount = fatigueCount;
   }


}
