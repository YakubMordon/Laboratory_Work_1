import java.util.Scanner;

public class Main {
    public static int formula(int n){ return (n*(n+1))/2; }
    public static int reverseFormula(int a){
        return (int)Math.sqrt(2*a);
    }

    public static void triangleNumbers(Fibonachi[] arr, int i){  //
        int numb = reverseFormula(arr[i].getNumber());
        if(arr[i].getNumber() == formula(numb)){          //���� ����� n, ��� �� ��������� �� �������, ��� ���������� ������� ����� Գ�������
            System.out.println(arr[i].getNumber() + " ");  //�� �������� �� �����
        }
    }

    public static void createArr(Fibonachi[] arr, int i){  //����� ��� ��������� ������ ����������
        arr[i] = new Fibonachi();      //��������� ��'���� �������� ������ ���������� ��� ���� ����������

        if(i == 0){
            arr[i].setNumber(0);
        }
        else if (i == 1) {
            arr[i].setNumber(1);
        }
        else{
            arr[i].setNumber(arr[i - 1].getNumber() + arr[i - 2].getNumber());
        }
        arr[i].setOrderOfNumb(i+1);

        System.out.println(arr[i].getNumber() + " ");
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);  //����������� ��������

        System.out.println("������ ������� ������ ����� Գ�������");

        int count = reader.nextInt();      //���������� ������� ������ �����

        Fibonachi[] arr = new Fibonachi[count];      //����������� ������ ���������� �����

        System.out.println("����� Գ�������: ");

        for(int i = 0; i < count; i++)    //���� ���������� ������
            createArr(arr,i);

        System.out.println("����� Գ�������, �� � �������� ���������� �������: ");
        for (int i = 0; i < count; i++)                 //���� ����������� ��������� ����� �� ������������ ����� Գ�������
            triangleNumbers(arr,i);
    }
}