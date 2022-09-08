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
                    System.out.println("Файл створено: " + myFile.getName() + "\n");
               } else {
                    System.out.println("Файл уже існує.\n");
                    myFile.delete();
                    myFile.createNewFile();
               }
          } catch (IOException e) {
               System.out.println("Помилка знайдена.\n");
               e.printStackTrace();
          }

          firstTeam = new DroidType[count];
          secondTeam = new DroidType[count];

          for(int i = 0; i < this.firstTeam.length; i++){
               System.out.println("\nПара бійців-дроїдів №" + (i + 1) + "\n");
               System.out.println("\n\tВведіть учасника першої команди для бою: \n");
               this.firstTeam[i] = new DroidType();
               System.out.println("\n\tВведіть учасника другої команди для бою: \n");
               this.secondTeam[i] = new DroidType();
          }

          System.out.println("\nСписок створених дроїдів першої команди: ");
          printTeam(firstTeam);

          System.out.println("\nСписок створених дроїдів другої команди: ");
          printTeam(secondTeam);

          boolean winnerTeam = fight(this.firstTeam.length);

          System.out.println("\nСклад команди, яка перемогла у бої: ");

          if(winnerTeam)
               printTeam(firstTeam);
          else
               printTeam(secondTeam);

          System.out.println("\n\nВиведення з файлу\n");
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
                    System.out.println("\nСтало здоров'я у дроїда " + this.defender.getName() + " - " + this.defender.getHealth());
                    myWriter.write("\nСтало здоров'я у дроїда " + this.defender.getName() + " - " + this.defender.getHealth() + "\n");
               }
               else if(rand > 7) {
                    System.out.println("Дроїд не зміг попасти по сопернику");
                    myWriter.write("Дроїд не зміг попасти по сопернику\n");
               }
               else {
                    this.defender.weakAttack(this.attacker,amount,weakening);
                    myWriter.write("\nНанесено урону " + amount);
                    myWriter.write("\n\nЧерез пошкодження броні, шанс поглинання урону впав на " + (weakening * 100) + "%\n");
               }

          } catch (IOException e) {
               System.out.println("Помилка знайдена.");
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
