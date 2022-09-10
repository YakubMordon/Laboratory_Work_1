import Droid.*;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class BattleArena {
     private BaseDroid[] firstTeam;
     private BaseDroid[] secondTeam;
     BattleArena(int count){

          firstTeam = new BaseDroid[count];
          secondTeam = new BaseDroid[count];

          System.out.println("\n\nВведіть учасників першої команди:\n");
          createArr(firstTeam);
          System.out.println("\n\nВведіть учасників другої команди:\n");
          createArr(secondTeam);

          System.out.println("\nСписок створених дроїдів першої команди: ");
          printTeam(firstTeam);

          System.out.println("\nСписок створених дроїдів другої команди: ");
          printTeam(secondTeam);

          boolean result = fight(count);

          System.out.println("\nСклад команди, яка перемогла у бої: ");

          if(result)
               printTeam(firstTeam);
          else
               printTeam(secondTeam);

          System.out.println("\n\nВиведення з файлу\n");
          printFile();
     }

     private void createArr(BaseDroid[] team){
          Scanner reader = new Scanner(System.in);

          BaseDroid[] template = { new BaseDroid(110,31), new BaseDroid(150,17), new BaseDroid(135,24)
                  ,new DroidKnight(70,27,12,0.2), new DroidKnight(150,10,5,0.8), new DroidKnight(100,20,8,0.5)
                  ,new DroidBercerk(80,33,3), new DroidBercerk(165,17,4) , new DroidBercerk(120,26,3) };

          for(int i = 0; i < team.length; i++){
               System.out.println("\n\tВведіть учасника команди для бою: \n");
               System.out.println("\nВведіть тип дроїда (1 - Базовий тип, 2 - Дроїд-лицар, 3 - Дроїд-Берсерк):\n");
               int type = reader.nextInt();
               reader.nextLine();
               System.out.println("\nВиберіть один з наступних шаблонних варіантів (1 - Берсерк, 2 - Танк, 3 - Воїн-лицар, 4 - Вписати свій варіант): ");
               int var = reader.nextInt();
               reader.nextLine();
               while(var < 1 && var > 4){
                    System.out.println("\nВи ввели неправильне значення.Введіть значення ще раз\n");
                    System.out.println("\nВиберіть один з наступних варіантів (1 - Берсерк, 2 - Танк, 3 - Воїн-лицар, 4 - Вписати свій варіант): ");
                    var = reader.nextInt();
                    reader.nextLine();
               }
               if(var == 4){
                    switch (type){
                         case 1:
                              team[i] = new BaseDroid();
                              break;
                         case 2:
                              team[i] = new DroidKnight();
                              break;
                         case 3:
                              team[i] = new DroidBercerk();
                              break;
                    }
               }else{
                    var = var - 1 + 3 * (type-1);
                    if(type == 1 && var > 2)
                         var -= 1;
                    team[i] = template[var];
                    System.out.println("\nВведіть ім'я дроїда: ");
                    String name = reader.nextLine();
                    team[i].setName(name);
               }
          }
     }
     private boolean fight(int length){

          Random random = new Random();
          boolean result = true;

          try {
               FileWriter myWriter = new FileWriter("G:\\file.txt");
               while (true) {

                    if(random.nextBoolean()){
                         swapAttacker(firstTeam,secondTeam,length,myWriter,random);
                    }else{
                         swapAttacker(secondTeam,firstTeam,length,myWriter,random);
                    }

                    if (!checkTeam(firstTeam)) {
                         result = false;
                         break;
                    }

                    if (!checkTeam(secondTeam))
                         break;
               }
               myWriter.close();
          }catch(IOException e){
               e.printStackTrace();
          }
          return result;
     }

     private void swapAttacker(BaseDroid[] team1,BaseDroid[] team2,int length,FileWriter myWriter,Random random){
          int numb1,numb2;

          numb1 = numb2 = length - 1;

          if(length > 1) {
               numb1 = randomize(team1,length,random);
               numb2 = randomize(team2,length,random);
          }

          action(myWriter,team1[numb1],team2[numb2]);
     }

     private int randomize(BaseDroid[] team,int length,Random random){
          int numb = random.nextInt(length);

          while(!team[numb].isAlive())
               numb = random.nextInt(length);

          return numb;
     }

     private boolean checkTeam(BaseDroid[] team){
          boolean indicator = true;
          if(team.length == 1) {

               if(!team[0].isAlive())
                    indicator = false;

          }else {
               for (int i = 0; i < team.length; i++) {

                    if ((!team[0].isAlive() == !team[i].isAlive()) && !team[0].isAlive() == true)
                         indicator = false;
                    else
                         indicator = true;
               }
          }
          return indicator;
     }
     private void action(FileWriter myWriter,BaseDroid defender,BaseDroid attacker){

          Random random = new Random();

          int rand = random.nextInt() % 10;
          int amount = 0;
          double weakening = 0;

          try {

               if(rand < 5) {
                    attackingRound(defender,attacker,myWriter);
               }
               else if(rand > 7) {
                    System.out.println("Дроїд не зміг попасти по сопернику");
                    myWriter.write("Дроїд не зміг попасти по сопернику\n");
               }
               else {
                    defender.weakAttack(attacker,amount,weakening, myWriter);
               }

          } catch (IOException e) {
               System.out.println("Помилка знайдена.");
               e.printStackTrace();
          }
     }
     private void printTeam(BaseDroid[] team){
          for(int i = 0; i < team.length; i++) {
               System.out.println("\n----------------------\n");
               System.out.println(team[i].toString());
          }
     }

     private void attackingRound(BaseDroid defender,BaseDroid attacker,FileWriter myWriter){
          int health = defender.getHealth() - attacker.attack(myWriter);
          defender.setHealth(health);
          if(defender.getHealth() < 0)
               defender.setHealth(0);
          System.out.println("\nСтало здоров'я у дроїда " + defender.getName() + " - " + defender.getHealth());
          try {
               myWriter.write("\nСтало здоров'я у дроїда " + defender.getName() + " - " + defender.getHealth() + "\n");
          }catch (IOException e){
               System.out.println("Помилка знайдена.");
               e.printStackTrace();
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
