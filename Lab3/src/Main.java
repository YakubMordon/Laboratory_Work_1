import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("\n\t������ ������� �������� � ����� ������: ");
        int count = reader.nextInt();
        reader.nextLine();
        new BattleArena(count);
    }
}