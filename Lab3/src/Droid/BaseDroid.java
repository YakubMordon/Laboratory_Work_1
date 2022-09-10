package Droid;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class BaseDroid {
    protected String name;
    protected int health;
    protected int damage;

    public BaseDroid(){
        inputData();
    }

    public BaseDroid(int health,int damage){
        this.health = health;
        this.damage = damage;
    }
    protected void inputData(){
        Scanner reader = new Scanner(System.in);
        System.out.println("\nВведіть назву дроїда:");
        this.name = reader.nextLine();
        System.out.println("\nВведіть кількість очків здоров'я (Максимальна кількість здоров'я - 250):");
        this.health = reader.nextInt() % 251;
        reader.nextLine();
        System.out.println("\nВведіть кількість урону (Максимальна кількість урону - 50):");
        this.damage = reader.nextInt() % 51;
        reader.nextLine();
    }
    public Boolean isAlive(){
        return health > 0;
    }

    public int getDamage() { return damage; }
    public String getName() { return name; }
    public void setHealth(int health) { this.health = health; }

    public void setDamage(int damage) { this.damage = damage; }
    public void setName(String name) { this.name = name; }
    public int getHealth() { return health; }
    public int attack(FileWriter myWriter){
        Random random = new Random();

        int damage = this.damage - random.nextInt(1,this.damage - (int)((double)2/4 * (double)this.damage));

        System.out.println("\nДроїдом " + this.name + " нанесено урону " + damage);

        try {
            myWriter.write("\nДроїдом " + this.name + " нанесено урону " + damage);
        }catch (IOException e) {
            System.out.println("Помилка знайдена.");
            e.printStackTrace();
        }
        return damage;
    }

    public void weakAttack(BaseDroid attacker, int amount, double weakening, FileWriter myWriter){
        Random random = new Random();

        amount = (int)((double)(attacker.attack(myWriter)) * random.nextDouble(0.8));

        System.out.println("\nДроїду " + this.name + " нанесено урону " + amount);
        try {
            myWriter.write("\nДроїду " + this.name + " нанесено урону " + amount);
        }catch (IOException e) {
            System.out.println("Помилка знайдена.");
            e.printStackTrace();
        }

        this.health -= amount;

        if(this.health < 0)
            this.health = 0;
    }

    @Override
    public String toString() {
        return "\nНазва дроїда: " + this.name + "\nКількість здоров'я: " + this.health + "\nКількість пошкоджень: " + this.damage;
    }
}
