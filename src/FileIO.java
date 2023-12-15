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
                        String cardName = row[1];
                        int cardCost = Integer.parseInt(row[2].split(": ")[1]);
                        int minionAttack = Integer.parseInt(row[3].split(": ")[1]);
                        int minionMaxHealth = Integer.parseInt(row[4].split(": ")[1]);
                        Minion minion = new Minion(cardName,cardCost,minionAttack,minionMaxHealth);
                        data.add(minion);
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

