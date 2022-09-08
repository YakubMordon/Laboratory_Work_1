import Droid.*;

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

public class BattleArena {
     private BaseDroid[] firstTeam;
     private BaseDroid[] secondTeam;
     private BaseDroid attacker;
     private BaseDroid defender;
     BattleArena(int count){

          firstTeam = new BaseDroid[count];
          secondTeam = new BaseDroid[count];

          System.out.println("\n\n������ �������� ����� �������:\n");
          createArr(firstTeam);
          System.out.println("\n\n������ �������� ����� �������:\n");
          createArr(secondTeam);

          System.out.println("\n������ ��������� ����� ����� �������: ");
          printTeam(firstTeam);

          System.out.println("\n������ ��������� ����� ����� �������: ");
          printTeam(secondTeam);

          System.out.println("\n����� �������, ��� ��������� � ��: ");

          if(fight(this.firstTeam.length))
               printTeam(firstTeam);
          else
               printTeam(secondTeam);

          System.out.println("\n\n��������� � �����\n");
          printFile();
     }

     private void createArr(BaseDroid[] team){
          Scanner reader = new Scanner(System.in);
          BaseDroid[] input = { new BaseDroid(), new DroidKnight(), new DroidBercerk() };

          BaseDroid[] template = { new BaseDroid(110,31), new BaseDroid(150,17), new BaseDroid(135,24)
                  ,new DroidKnight(70,27,12,0.2), new DroidKnight(150,10,5,0.8), new DroidKnight(100,20,8,0.5)
                  ,new DroidBercerk(80,33,3), new DroidBercerk(165,17,4) , new DroidBercerk(120,26,3) };

          for(int i = 0; i < team.length; i++){
               System.out.println("\n\t������ �������� ������� ��� ���: \n");
               System.out.println("\n������ ��� ����� (1 - ������� ���, 2 - ����-�����, 3 - ����-�������):\n");
               int type = reader.nextInt();
               reader.nextLine();
               System.out.println("\n������ ����� �����:");
               team[i].setName(reader.nextLine());
               System.out.println("\n������� ���� � ��������� ��������� ������� (1 - �������, 2 - ����, 3 - ���-�����, 4 - ������� ��� ������): ");
               int var = reader.nextInt();
               reader.nextLine();
               while(var < 1 && var > 4){
                    System.out.println("\n�� ����� ����������� ��������.������ �������� �� ���\n");
                    System.out.println("\n������� ���� � ��������� ������� (1 - �������, 2 - ����, 3 - ���-�����, 4 - ������� ��� ������): ");
                    var = reader.nextInt();
                    reader.nextLine();
               }
               if(var == 4){
                    team[i] = input[type - 1];
               }else{
                    var += (int)Math.pow(3,(type-1));
                    if(type == 1)
                         var -= 1;
                    team[i] = template[var];
               }
          }
     }
     private boolean fight(int length){

          Random random = new Random();
          boolean result = true, indicator;
          int numb1 = 0,numb2 = 0, count1 = length, count2 = length;

          try {
               FileWriter myWriter = new FileWriter("G:\\file.txt");
               while (true) {
                    numb1 = random.nextInt(count1);
                    numb2 = random.nextInt(count2);

                    if(random.nextBoolean()){
                         swapAttacker(firstTeam,secondTeam,numb1,numb2);
                         indicator = true;
                    }else{
                         swapAttacker(secondTeam,firstTeam,numb2,numb1);
                         indicator = false;
                    }

                    action(myWriter);

                    if(!defender.isAlive()){
                        if(indicator){
                              checkTeammate(secondTeam,count2,numb2);
                              count2--;
                        }else{
                              checkTeammate(firstTeam,count1,numb1);
                              count1--;
                        }
                    }

                    if (!this.firstTeam[0].isAlive()) {
                         result = false;
                         break;
                    }

                    if (!this.secondTeam[0].isAlive())
                         break;
               }
               myWriter.close();
          }catch(IOException e){
               e.printStackTrace();
          }
          return result;
     }

     private void swapAttacker(BaseDroid[] team1,BaseDroid[] team2,int numb1,int numb2){
          attacker = team1[numb1];
          defender = team2[numb2];
     }

     private void checkTeammate(BaseDroid[] team, int count, int numb) {
          BaseDroid temp = team[numb];
          team[numb] = team[count - 1];
          team[count - 1] = temp;
     }
     private void action(FileWriter myWriter){

          Random random = new Random();

          int rand = random.nextInt() % 10;
          int amount = 0;
          double weakening = 0;

          try {

               if(rand < 5) {
                    int health = this.defender.getHealth() - this.attacker.attack(myWriter);
                    this.defender.setHealth(health);
                    System.out.println("\n����� ������'� � ����� " + this.defender.getName() + " - " + this.defender.getHealth());
                    myWriter.write("\n����� ������'� � ����� " + this.defender.getName() + " - " + this.defender.getHealth() + "\n");
               }
               else if(rand > 7) {
                    System.out.println("���� �� ��� ������� �� ���������");
                    myWriter.write("���� �� ��� ������� �� ���������\n");
               }
               else {
                    this.defender.weakAttack(this.attacker,amount,weakening, myWriter);
               }

          } catch (IOException e) {
               System.out.println("������� ��������.");
               e.printStackTrace();
          }
     }
     private void printTeam(BaseDroid[] team){
          for(int i = 0; i < team.length; i++) {
               System.out.println("\n----------------------\n");
               System.out.println(team[i].toString());
          }
     }
     private void printFile(){
          try{
               System.setIn(new FileInputStream("G:\\file.txt"));
               String content = new String(System.in.readAllBytes());
               System.out.println(content);
          }catch (IOException e){
               e.printStackTrace();
          }
     }
}
