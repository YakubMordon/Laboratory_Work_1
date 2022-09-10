import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void createFile(){
        try {
            File myFile = new File("G:\\file.txt");
            if (myFile.createNewFile()) {
                System.out.println("Файл створено: " + myFile.getName() + "\n");
            } else {
                System.out.println("Файл уже існує.\n");
                myFile.delete();
                myFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Помилка знайдена.\n");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createFile();
        Scanner reader = new Scanner(System.in);
        System.out.println("\n\tВведіть кількість учасників у кожній команді: ");
        int count = reader.nextInt();
        reader.nextLine();
        new BattleArena(count);
    }

}