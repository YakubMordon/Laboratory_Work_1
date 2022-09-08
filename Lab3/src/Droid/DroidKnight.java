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
        System.out.println("\n������ ������� ���������� ����� (����������� ������� ���������� ����� - 0.95):");
        this.armor = reader.nextDouble() % 0.95;
        System.out.println("\n������� ��������(0 - +0 �����,1 - +4 �����, 2 - +7 �����, 3 - +11 �����):");
        int wpn = reader.nextInt();
        reader.nextLine();
        while(wpn < 0 && wpn > 3){
            System.out.println("\n�� ����� ����������� ��������, ������ �� ���:");
            wpn = reader.nextInt();
            reader.nextLine();
        }
        this.weapon = weapon[wpn];
    }

    public int attack(FileWriter myWriter){
        Random random = new Random();
        int damage = this.damage + this.weapon - random.nextInt(this.damage - 1/4*this.damage);
        System.out.println("\n������ " + this.name + " �������� ����� " + damage);
        try {
            myWriter.write("\n������ " + this.name + " �������� ����� " + damage);
        }catch (IOException e){
            System.out.println("������� ��������.");
            e.printStackTrace();
        }
        return damage;
    }

    public void weakAttack(BaseDroid attacker, int amount, double weakening, FileWriter myWriter){
        Random random = new Random();
        amount = (int)((double)(attacker.attack(myWriter)) *  (1 - this.armor));
        System.out.println("\n����� " + this.name + " �������� ����� " + amount);
        this.health -= amount;
        if (this.health < 0)
            this.health = 0;
        weakening = random.nextDouble(0.1);
        System.out.println("\n����� ����������� ����, ���� ���������� ����� ���� �� " + (weakening * 100) + "% � ����� " + this.name);
        try {
            myWriter.write("\n����� " + this.name + " �������� ����� " + amount);
            myWriter.write("\n����� ����������� ����, ���� ���������� ����� ���� �� " + (weakening * 100) + "% � ����� " + this.name);
        }catch (IOException e) {
            System.out.println("������� ��������.");
            e.printStackTrace();
        }
        this.armor -= weakening;
    }

    @Override
    public String toString() {
        return "\n����� �����: " + this.name + "\nʳ������ ������'�: " + this.health + "\nʳ������ ����������: " + this.damage +
                "\nʳ������ ����� �� ����: +" + this.weapon + "\n������� ���������� �����: " + this.armor * 100 + "%";
    }

    public double getArmor() { return armor; }

    public int getWeapon() { return weapon; }

    public void setArmor(double armor) { this.armor = armor; }

    public void setWeapon(int weapon) { this.weapon = weapon; }
}
