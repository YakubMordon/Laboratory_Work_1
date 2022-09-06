import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("\n\t¬вед≥ть к≥льк≥сть учасник≥в у кожн≥й команд≥: ");
        int count = reader.nextInt();
        reader.nextLine();
        new BattleArena(count);
    }
}