package Droid;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class DroidKnight extends BaseDroid {
    private double armor;
    private int weapon;

    public DroidKnight(){
        inputData();
    }

    public DroidKnight(int health,int damage, int weapon, double armor){
        super(health,damage);
        if(health > 210)
            this.health = 210;
        if(damage > 40)
            this.damage = 40;
        this.weapon = weapon;
        this.armor = armor;
    }

    protected void inputData(){
        int[] weapon = {0,4,7,11};
        Scanner reader = new Scanner(System.in);
        super.inputData();
        System.out.println("\nВведіть відсоток поглинання урону (Максимальна кількість поглинання урону - 0.95):");
        this.armor = reader.nextDouble() % 0.95;
        System.out.println("\nВиберіть озброєння(0 - +0 урону,1 - +4 урону, 2 - +7 урону, 3 - +11 урону):");
        int wpn = reader.nextInt();
        reader.nextLine();
        while(wpn < 0 && wpn > 3){
            System.out.println("\nВи ввели неправильне значення, введіть ще раз:");
            wpn = reader.nextInt();
            reader.nextLine();
        }
        this.weapon = weapon[wpn];
    }

    public int attack(FileWriter myWriter){
        Random random = new Random();
        int damage = this.damage + this.weapon - random.nextInt(this.damage - 1/4*this.damage);
        System.out.println("\nДроїдом " + this.name + " нанесено урону " + damage);
        try {
            myWriter.write("\nДроїдом " + this.name + " нанесено урону " + damage);
        }catch (IOException e){
            System.out.println("Помилка знайдена.");
            e.printStackTrace();
        }
        return damage;
    }

    public void weakAttack(BaseDroid attacker, int amount, double weakening, FileWriter myWriter){
        Random random = new Random();
        amount = (int)((double)(attacker.attack(myWriter)) *  (1 - this.armor));
        System.out.println("\nДроїду " + this.name + " нанесено урону " + amount);
        this.health -= amount;
        if (this.health < 0)
            this.health = 0;
        weakening = random.nextDouble(0.1);
        System.out.println("\nЧерез пошкодження броні, шанс поглинання урону впав на " + (weakening * 100) + "% у дроїда " + this.name);
        try {
            myWriter.write("\nДроїду " + this.name + " нанесено урону " + amount);
            myWriter.write("\nЧерез пошкодження броні, шанс поглинання урону впав на " + (weakening * 100) + "% у дроїда " + this.name);
        }catch (IOException e) {
            System.out.println("Помилка знайдена.");
            e.printStackTrace();
        }
        this.armor -= weakening;
    }

    @Override
    public String toString() {
        return "\nНазва дроїда: " + this.name + "\nКількість здоров'я: " + this.health + "\nКількість пошкоджень: " + this.damage +
                "\nКількість урону від зброї: +" + this.weapon + "\nПроцент поглинання урону: " + this.armor * 100 + "%";
    }

    public double getArmor() { return armor; }

    public int getWeapon() { return weapon; }

    public void setArmor(double armor) { this.armor = armor; }

    public void setWeapon(int weapon) { this.weapon = weapon; }
}
