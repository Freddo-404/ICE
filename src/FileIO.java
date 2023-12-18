import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class FileIO implements FileEditor {

    public LinkedList<Card> readDeckData(String cardPath){
        LinkedList<Card>data = new LinkedList<>();
        File file = new File(cardPath);
        try{
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()){
                String s = scan.nextLine();
                String [] row = s.split("; ");

                String cardType = row[0];

                switch (cardType){
                    case "Minion":
                        String cardName1 = row[1].split(": ")[1];
                        int cardCost1 = Integer.parseInt(row[2].split(": ")[1]);
                        int minionAttack = Integer.parseInt(row[3].split(": ")[1]);
                        int minionMaxHealth = Integer.parseInt(row[4].split(": ")[1]);
                        boolean taunt  = Boolean.parseBoolean(row[5].split(": ")[1]);
                        Minion minion = new Minion(cardName1,cardCost1,minionAttack,minionMaxHealth,taunt);
                        data.add(minion);
                        break;
                    case "Spell":
                        String cardName2 = row[1].split(": ")[1];
                        int cardCost2 = Integer.parseInt(row[2].split(": ")[1]);
                        Spell spell = new Spell(cardName2,cardCost2);
                        data.add(spell);
                        break;
                    case "Weapon":
                        String cardName3 = row[1].split(": ")[1];
                        int cardCost3 = Integer.parseInt(row[2].split(": ")[1]);
                        int weaponAttack = Integer.parseInt(row[3].split(": ")[1]);
                        int weaponDurability= Integer.parseInt(row[4].split(": ")[1]);
                        Weapon weapon = new Weapon(cardName3,cardCost3,weaponAttack,weaponDurability);
                        break;
                    default:
                        System.out.println("Invalid card type");
                        break;
                }

            }
        }
        catch(FileNotFoundException e){
            System.out.println("file not found");
        }
    return data;
    }

}

