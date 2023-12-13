import java.util.LinkedList;

public class Deck {



   private LinkedList<Card> cardsInDeck = new LinkedList<>();

   public Deck(LinkedList<Card> cardsInDeck){
      this.cardsInDeck = cardsInDeck;
   }
public LinkedList<Card> getCardsInDeck(){
      return cardsInDeck;
}




}
