import java.util.LinkedList;

public class Deck {

   private int fatigueCount;
   private FileIO io = new FileIO();
   private LinkedList<Card> allWeapons = new LinkedList<>();
   private LinkedList<Card> allMinions = new LinkedList<>();
   private LinkedList<Card> allSpells = new LinkedList<>();

   private LinkedList<Card> cardsInDeck = new LinkedList<>();

   public Deck(LinkedList<Card> cardsInDeck){
      this.cardsInDeck = cardsInDeck;
   }



   public LinkedList<Card> getAllMinions(){
      allMinions = io.readMinionData("card.txt");
      return allMinions;
   }

   public LinkedList<Card> getAllWeapons(){
      allWeapons = io.readWeaponData("weapons.txt");
      return allWeapons;
   }
   public LinkedList<Card> getAllSpells(){
      allSpells = io.readSpellData("spells.txt");
      return allSpells;
   }
}
