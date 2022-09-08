import Droid.DroidType;

import java.util.Random;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileInputStream;
public class BattleArena {

     private DroidType[] firstTeam;
     private DroidType[] secondTeam;

     private DroidType attacker;
     private DroidType defender;

     BattleArena(int count){
          try {
               File myFile = new File("G:\\file.txt");
               if (myFile.createNewFile()) {
                    System.out.println("���� ��������: " + myFile.getName() + "\n");
               } else {
                    System.out.println("���� ��� ����.\n");
                    myFile.delete();
                    myFile.createNewFile();
               }
          } catch (IOException e) {
               System.out.println("������� ��������.\n");
               e.printStackTrace();
          }

          firstTeam = new DroidType[count];
          secondTeam = new DroidType[count];

          for(int i = 0; i < this.firstTeam.length; i++){
               System.out.println("\n���� �����-����� �" + (i + 1) + "\n");
               System.out.println("\n\t������ �������� ����� ������� ��� ���: \n");
               this.firstTeam[i] = new DroidType();
               System.out.println("\n\t������ �������� ����� ������� ��� ���: \n");
               this.secondTeam[i] = new DroidType();
          }

          System.out.println("\n������ ��������� ����� ����� �������: ");
          printTeam(firstTeam);

          System.out.println("\n������ ��������� ����� ����� �������: ");
          printTeam(secondTeam);

          boolean winnerTeam = fight(this.firstTeam.length);

          System.out.println("\n����� �������, ��� ��������� � ��: ");

          if(winnerTeam)
               printTeam(firstTeam);
          else
               printTeam(secondTeam);

          System.out.println("\n\n��������� � �����\n");
          printFile();
     }

     boolean fight(int length){

          Random random = new Random();

          boolean result = true;

          int numb1 = 0,numb2 = 0, count1 = length, count2 = length;

          Boolean indicator = true;

          try {
               FileWriter myWriter = new FileWriter("G:\\file.txt");
               while (true) {

                    if (random.nextBoolean()) {

                         numb1 = random.nextInt(count1);
                         attacker = this.firstTeam[numb1];

                         numb2 = random.nextInt(count2);
                         defender = this.secondTeam[numb2];

                         indicator = true;
                    } else {

                         numb2 = random.nextInt(count2);
                         attacker = this.secondTeam[numb2];

                         numb1 = random.nextInt(count1);
                         defender = this.firstTeam[numb1];

                         indicator = false;
                    }

                    action(myWriter);

                    if (count1 == 1 && count2 == 1) {
                         DroidType temp = defender;
                         defender = attacker;
                         attacker = temp;
                    } else {
                         if(!defender.isAlive()){

                              if(indicator){

                                   if((count2 - 1) < 0) break;

                                   DroidType temp = this.secondTeam[numb2];
                                   this.secondTeam[numb2] = this.secondTeam[count2 - 1];
                                   this.secondTeam[count2 - 1] = temp;

                                   count2--;

                              }else{

                                   if((count1 - 1) < 0) break;

                                   DroidType temp = this.firstTeam[numb1];
                                   this.firstTeam[numb1] = this.firstTeam[count1 - 1];
                                   this.firstTeam[count1 - 1] = temp;

                                   count1--;
                              }
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


     private void action(FileWriter myWriter){

          Random random = new Random();

          int rand = random.nextInt() % 10;
          int amount,weakening;

          amount = weakening = 0;

          try {

               if(rand < 5) {
                    int health = this.defender.getHealth() - this.attacker.attack();
                    this.defender.setHealth(health);
                    if(this.defender.getHealth() < 0)
                         this.defender.setHealth(0);
                    System.out.println("\n����� ������'� � ����� " + this.defender.getName() + " - " + this.defender.getHealth());
                    myWriter.write("\n����� ������'� � ����� " + this.defender.getName() + " - " + this.defender.getHealth() + "\n");
               }
               else if(rand > 7) {
                    System.out.println("���� �� ��� ������� �� ���������");
                    myWriter.write("���� �� ��� ������� �� ���������\n");
               }
               else {
                    this.defender.weakAttack(this.attacker,amount,weakening);
                    myWriter.write("\n�������� ����� " + amount);
                    myWriter.write("\n\n����� ����������� ����, ���� ���������� ����� ���� �� " + (weakening * 100) + "%\n");
               }

          } catch (IOException e) {
               System.out.println("������� ��������.");
               e.printStackTrace();
          }
     }

     private void printTeam(DroidType[] team){
          for(int i = 0; i < team.length; i++)
               System.out.println(team[i].toString());
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
