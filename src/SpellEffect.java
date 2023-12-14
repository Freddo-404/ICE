public class SpellEffect extends Effect{
private String spellName;

public SpellEffect(String spellName){
    this.spellName=spellName;
}
   public void useSpellEffect(Board myBoard){

       switch (spellName){
           case "The coin":
               myBoard.setCurrentMana(myBoard.getCurrentMana()+1);
               break;
           default:
               System.out.println("Spell missing in SpellEffect");
               break;
       }


   }


}
