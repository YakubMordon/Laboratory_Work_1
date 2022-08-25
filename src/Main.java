import java.util.Scanner;

public class Main {
    public static int formula(int n){ return (n*(n+1))/2; }
    public static int reverseFormula(int a){
        return (int)Math.sqrt(2*a);
    }

    public static void triangleNumbers(Fibonachi[] arr, int i){  //
        int numb = reverseFormula(arr[i].getNumber());
        if(arr[i].getNumber() == formula(numb)){          //Якщо число n, яке ми знаходимо із формули, при підставленні дорівнює числу Фібоначчі
            System.out.println(arr[i].getNumber() + " ");  //То виводимо на екран
        }
    }

    public static void createArr(Fibonachi[] arr, int i){  //Метод для створення масиву екземплярів
        arr[i] = new Fibonachi();      //Створення об'єкту всередині масиву екземплярів для його заповнення

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
        Scanner reader = new Scanner(System.in);  //Ініціалізація Сканнера

        System.out.println("Введіть кількість перших чисел Фібоначчі");

        int count = reader.nextInt();      //Зчитування кількості перших чисел

        Fibonachi[] arr = new Fibonachi[count];      //Ініціалізація масиву екземплярів класу

        System.out.println("Числа Фібоначчі: ");

        for(int i = 0; i < count; i++)    //Цикл заповнення масиву
            createArr(arr,i);

        System.out.println("Числа Фібоначчі, які є водночас трикутними числами: ");
        for (int i = 0; i < count; i++)                 //Цикл знаходження трикутних чисел із згенерованих чисел Фібоначчі
            triangleNumbers(arr,i);
    }
}