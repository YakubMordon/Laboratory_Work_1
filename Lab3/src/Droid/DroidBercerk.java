package Droid;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class DroidBercerk extends BaseDroid{

    private int charge;
    private int readyCharge;

    public DroidBercerk(){
        inputData();
    }

    public DroidBercerk(int health,int damage, int readyCharge){
        super(health,damage);
        if(health > 170)
            this.health = 170;
        this.charge = 0;
        this.readyCharge = readyCharge;
    }

    protected void inputData(){
        Scanner reader = new Scanner(System.in);
        super.inputData();
        System.out.println("\nВведіть кількість ходів до зарядки його вміння (мінімальна кількість - 3, зарядка збільшується тільки після повноцінної атаки):");
        this.readyCharge = reader.nextInt();
        if(this.readyCharge > 3){
            this.readyCharge = 3;
            System.out.println("\nЗначення зарядки змінено на " + this.readyCharge + " оскільки його значення було менше 3");
        }
    }

    public int attack(FileWriter myWriter){
        Random random = new Random();
        if(isCharged()){
            damage = bercerkMode(myWriter);
            this.charge = 0;
        }else{
            int damage = this.damage - random.nextInt(1,this.damage - (int)((double)2/4 * (double)this.damage));
            System.out.println("\nДроїдом " + this.name + " нанесено урону " + damage);
            System.out.println("\nЗалишилося ходів до зарядки вміння " + (this.readyCharge - this.charge));
            try {
                myWriter.write("\nДроїдом " + this.name + " нанесено урону " + damage);
                myWriter.write("\nЗалишилося ходів до зарядки вміння " + (this.readyCharge - this.charge));
            }catch (IOException e) {
                System.out.println("Помилка знайдена.");
                e.printStackTrace();
            }
            this.charge += 1;
        }
        return damage;
    }
    public void weakAttack(BaseDroid attacker, int amount, double weakening, FileWriter myWriter){
        Random random = new Random();

        amount = (int)((double)(attacker.attack(myWriter)) * random.nextDouble(1));

        System.out.println("\nДроїду " + this.name + " нанесено урону " + amount);

        this.health -= amount;

        if (this.health < 0)
            this.health = 0;

        try {
            myWriter.write("\nДроїду " + this.name + " нанесено урону " + amount);
        }catch (IOException e) {
            System.out.println("Помилка знайдена.");
            e.printStackTrace();
        }
    }
    private int bercerkMode(FileWriter myWriter){

        Random random = new Random();

        int damage = 0;

        int count = 2 + random.nextInt(6);

        for(int i = 0; i < count; i++)
            damage += this.damage - random.nextInt(1,this.damage - (int)((double)3/4 * (double)this.damage));

        System.out.println("\nВиконано комбо з " + count + " та нанесено " + damage + " урону дроїдом " + this.name);

        try {
            myWriter.write("\nВиконано комбо з " + count + " та нанесено " + damage + " урону дроїдом " + this.name);
        }catch (IOException e){
            System.out.println("Помилка знайдена.");
            e.printStackTrace();
        }
        return damage;
    }
    private boolean isCharged(){ return charge == readyCharge; }

    public int getCharge() { return charge; }

    public int getReadyCharge() { return readyCharge; }

    public void setCharge(int charge) { this.charge = charge; }

    public void setReadyCharge(int readyCharge) { this.readyCharge = readyCharge; }
}
