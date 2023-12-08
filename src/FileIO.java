import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class FileIO implements FileEditor {

    public LinkedList<Card> readMinionData(String cardPath){
        LinkedList<Card>data = new LinkedList<>();
        File file = new File(cardPath);
        try{
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()){
                String s = scan.nextLine();
                String [] row = s.split(",");

                String cardName = row[0];
                int cardCost = Integer.parseInt(row[1]);
                int minionAttack = Integer.parseInt(row[2]);
                int minionMaxHealth = Integer.parseInt(row[3]);


                 Card card = new Minion(cardName,cardCost,minionAttack,minionMaxHealth);
                data.add(card);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("file not found");
        }
return data;
    }


    public LinkedList<Card> readWeaponData(String cardPath){
        LinkedList<Card>data = new LinkedList<>();
        File file = new File(cardPath);
        try{
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()){
                String s = scan.nextLine();
                String [] row = s.split(",");

                String cardName = row[0];
                int cardCost = Integer.parseInt(row[1]);
                int weaponAttack = Integer.parseInt(row[2]);
                int weaponDurability = Integer.parseInt(row[3]);


                Card weapon = new Weapon(cardName,cardCost,weaponAttack, weaponDurability);
                data.add(weapon);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("file not found");
        }
        return data;
    }
    public LinkedList<Card> readSpellData(String cardPath){
        LinkedList<Card>data = new LinkedList<>();
        File file = new File(cardPath);
        try{
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()){
                String s = scan.nextLine();
                String [] row = s.split(",");

                String cardName = row[0];
                int cardCost = Integer.parseInt(row[1]);


                Card spell = new Spell(cardName,cardCost);
                data.add(spell);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("file not found");
        }
        return data;
    }
}

