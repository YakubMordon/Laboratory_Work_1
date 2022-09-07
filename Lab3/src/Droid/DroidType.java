package Droid;

import java.util.Random;
import java.util.Scanner;

public class DroidType extends BaseDroid {
    private double armor;
    private int weapon;

    @Override
    public String toString() {
        return "\nНазва дроїда: " + this.name + "\nКількість здоров'я: " + this.health + "\nКількість пошкоджень: " + this.damage +
                "\nКількість урону від зброї: +" + this.weapon + "\nПроцент поглинання урону: " + this.armor * 100 + "%";
    }

    public DroidType(){
       Scanner reader = new Scanner(System.in);
       System.out.println("\nВведіть назву дроїда:");
       this.name = reader.nextLine();
       System.out.println("\nВиберіть один з наступних варіантів (1 - Берсерк, 2 - Танк, 3 - Воїн-лицар, 4 - Вписати свій варіант): ");
       int var = reader.nextInt();
       reader.nextLine();
       while(true){
               if(var == 1) {
                   this.health = 70;
                   this.damage = 27;
                   this.armor = 0.2;
                   this.weapon = 12;
                   var = Integer.MAX_VALUE;
               } else if (var == 2) {
                   this.health = 150;
                   this.damage = 10;
                   this.armor = 0.8;
                   this.weapon = 5;
                   var = Integer.MAX_VALUE;
               } else if (var == 3) {
                   this.health = 100;
                   this.damage = 20;
                   this.armor = 0.5;
                   this.weapon = 8;
                   var = Integer.MAX_VALUE;
               } else if (var == 4) {
                   System.out.println("\nВведіть кількість очків здоров'я (Максимальна кількість здоров'я - 200):");
                   this.health = reader.nextInt() % 201;
                   reader.nextLine();
                   System.out.println("\nВведіть кількість урону (Максимальна кількість урону - 40):");
                   this.damage = reader.nextInt() % 40;
                   reader.nextLine();
                   System.out.println("\nВведіть відсоток поглинання урону (Максимальна кількість поглинання урону - 0.95):");
                   this.armor = reader.nextDouble() % 0.95;
                   System.out.println("\nВиберіть озброєння(0 - +0 урону,1 - +4 урону, 2 - +7 урону, 3 - +11 урону):");
                   int wpn = reader.nextInt();
                   reader.nextLine();
                   while(true){
                       if(wpn == 0) {
                           this.weapon = 0;
                           wpn = Integer.MAX_VALUE;
                       } else if (wpn == 1) {
                           this.weapon = 4;
                           wpn = Integer.MAX_VALUE;
                       } else if (wpn == 2) {
                           this.weapon = 7;
                           wpn = Integer.MAX_VALUE;
                       } else if (wpn == 3) {
                           this.weapon = 11;
                           wpn = Integer.MAX_VALUE;
                       }
                       if(wpn == Integer.MAX_VALUE)
                           break;
                       System.out.println("\nВи ввели неправильне значення, введіть ще раз:");
                       wpn = reader.nextInt();
                       reader.nextLine();
                   }
                   var = Integer.MAX_VALUE;
               }
           if(var == Integer.MAX_VALUE)
               break;
           System.out.println("\nВи ввели неправильне значення, введіть ще раз:");
           var = reader.nextInt();
           reader.nextLine();
       }
    }

    public int attack(){
        Random random = new Random();
        int damage = this.damage + this.weapon - random.nextInt(this.damage - 3/4*this.damage);
        System.out.println("\nНанесено урону " + damage);
        return damage;
    }

    public void weakAttack(DroidType attacker,int amount,double weakening){
        Random random = new Random();
        amount = (int)((double)(attacker.attack()) * this.armor);
        System.out.println("\nНанесено урону " + amount);
        this.health -= amount;
        weakening = random.nextDouble(0.1);
        System.out.println("\nЧерез пошкодження броні, шанс поглинання урону впав на " + (weakening * 100) + "%");
        this.armor -= weakening;
    }
}
