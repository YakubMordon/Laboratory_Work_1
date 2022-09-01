import java.util.Scanner;

public class Main {
    public static Scanner reader = new Scanner(System.in);;
    public static Book[] createArr(int amount) {
        Book[] arr = new Book[amount];
        for(int i = 0; i < arr.length; i++){
            arr[i] = new Book();
            System.out.println("\n ������ �" + (i + 1));
            System.out.println("\n������ ���������������� ��� ������");
            int id = reader.nextInt();
            reader.nextLine();
            arr[i].setId(id);
            System.out.println("\n������ ����� ������");
            String name = reader.nextLine();
            arr[i].setName(name);
            System.out.println("\n������ ������ ������");
            String author = reader.nextLine();
            arr[i].setAuthor(author);
            System.out.println("\n������ ����������� ������");
            String publishingHouse = reader.nextLine();
            arr[i].setPublishingHouse(publishingHouse);
            System.out.println("\n������ �� ������� ������");
            int year = reader.nextInt();
            arr[i].setYear(year);
            System.out.println("\n������ ������� ������� ������");
            int pageAmount = reader.nextInt();
            arr[i].setPageAmount(pageAmount);
            System.out.println("\n������ ���� ������");
            int price = reader.nextInt();
            reader.nextLine();
            arr[i].setPrice(price);
        }
        return arr;
    }

    public static void printArr(Book[] arr){
        for(int i = 0; i < arr.length;i++)
            System.out.println(arr[i]);
    }

    public static void printAuthorsBook(Book[] arr){
        System.out.println("\n������ ������, ����� ����� ������ ��������:");
        String author = reader.nextLine();
        int count = 0;
        for(int i = 0;i < arr.length;i++){
            if(arr[i].getAuthor().compareTo(author) == 0){
                count++;
                System.out.println(arr[i]);
            }
        }
        if(count == 0)
            System.out.println("\n���� ���� ������ ������ � ������ ����");
    }

    public static void printPublishingHouse(Book[] arr){
        System.out.println("\n������ �����������, ����� ����� ������ ��������:");
        String publishingHouse = reader.nextLine();
        int count = 0;
        for(int i = 0;i < arr.length;i++){
            if(arr[i].getPublishingHouse().compareTo(publishingHouse) == 0){
                count++;
                System.out.println(arr[i]);
            }
        }
        if(count == 0)
            System.out.println("\n���� ���� ������ ����������� � ������ ����");
    }

    public static void printAfterYear(Book[] arr){
        System.out.println("\n������ ��,���� ����� ������ �������� �����:");
        int year = reader.nextInt();
        int count = 0;
        for(int i = 0;i < arr.length;i++){
            if(arr[i].getYear() > year){
                count++;
                System.out.println(arr[i]);
            }
        }
        if(count == 0)
            System.out.println("\n���� ���� ���� ������ ���� � ������ ����");
    }

    public static void main(String[] args) {
        System.out.println("������ ������� ������, �� ������ ������:\n");
        int amount = reader.nextInt();
        Book[] arr = createArr(amount);
        System.out.println("\n������ ����: ");
        printArr(arr);
        printAuthorsBook(arr);
        printPublishingHouse(arr);
        printAfterYear(arr);
    }
}