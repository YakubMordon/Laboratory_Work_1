import AirlinesContent.Airline;
import menu.MainMenu;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;

import static message.SendEmail.sendMessage;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {
        try {
            Handler fileHandler = new FileHandler();
            logger.setUseParentHandlers(false);
            logger.addHandler(fileHandler);

            logger.info("Оголошення змінних: Сканнера, Консольного меню та пустого класу Airline, для подальшого заповнення");

            Scanner reader = new Scanner(System.in);
            MainMenu mainMenu = new MainMenu();
            Airline content = new Airline();

            logger.info("Виклик функції, у якій використовуємо консольне меню та вибираємо команду");
            while(true){
                System.out.println("\nAvailable commands: " + mainMenu.getAllCommands());
                System.out.println("\nWrite your command: ");
                String command = reader.nextLine();
                mainMenu.execute(command,content,logger);
            }

        } catch (Exception e) {
            logger.log(Level.WARNING,"Знайдено критичну помилку: ",e);
            System.out.println("The error occurred.\n");
            sendMessage("Знайдено критичну помилку: " + e);
            e.printStackTrace();
        }

    }
}