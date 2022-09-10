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
        System.out.println("\n������ ������� ���� �� ������� ���� ����� (�������� ������� - 3, ������� ���������� ����� ���� ���������� �����):");
        this.readyCharge = reader.nextInt();
        if(this.readyCharge > 3){
            this.readyCharge = 3;
            System.out.println("\n�������� ������� ������ �� " + this.readyCharge + " ������� ���� �������� ���� ����� 3");
        }
    }

    public int attack(FileWriter myWriter){
        Random random = new Random();
        if(isCharged()){
            damage = bercerkMode(myWriter);
            this.charge = 0;
        }else{
            int damage = this.damage - random.nextInt(1,this.damage - (int)((double)2/4 * (double)this.damage));
            System.out.println("\n������ " + this.name + " �������� ����� " + damage);
            System.out.println("\n���������� ���� �� ������� ����� " + (this.readyCharge - this.charge));
            try {
                myWriter.write("\n������ " + this.name + " �������� ����� " + damage);
                myWriter.write("\n���������� ���� �� ������� ����� " + (this.readyCharge - this.charge));
            }catch (IOException e) {
                System.out.println("������� ��������.");
                e.printStackTrace();
            }
            this.charge += 1;
        }
        return damage;
    }
    public void weakAttack(BaseDroid attacker, int amount, double weakening, FileWriter myWriter){
        Random random = new Random();

        amount = (int)((double)(attacker.attack(myWriter)) * random.nextDouble(1));

        System.out.println("\n����� " + this.name + " �������� ����� " + amount);

        this.health -= amount;

        if (this.health < 0)
            this.health = 0;

        try {
            myWriter.write("\n����� " + this.name + " �������� ����� " + amount);
        }catch (IOException e) {
            System.out.println("������� ��������.");
            e.printStackTrace();
        }
    }
    private int bercerkMode(FileWriter myWriter){

        Random random = new Random();

        int damage = 0;

        int count = 2 + random.nextInt(6);

        for(int i = 0; i < count; i++)
            damage += this.damage - random.nextInt(1,this.damage - (int)((double)3/4 * (double)this.damage));

        System.out.println("\n�������� ����� � " + count + " �� �������� " + damage + " ����� ������ " + this.name);

        try {
            myWriter.write("\n�������� ����� � " + count + " �� �������� " + damage + " ����� ������ " + this.name);
        }catch (IOException e){
            System.out.println("������� ��������.");
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
