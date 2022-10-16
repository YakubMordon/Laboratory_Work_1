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

            logger.info("���������� ������: ��������, ����������� ���� �� ������� ����� Airline, ��� ���������� ����������");

            Scanner reader = new Scanner(System.in);
            MainMenu mainMenu = new MainMenu();
            Airline content = new Airline();

            logger.info("������ �������, � ��� ������������� ��������� ���� �� �������� �������");
            while(true){
                System.out.println("\nAvailable commands: " + mainMenu.getAllCommands());
                System.out.println("\nWrite your command: ");
                String command = reader.nextLine();
                mainMenu.execute(command,content,logger);
            }

        } catch (Exception e) {
            logger.log(Level.WARNING,"�������� �������� �������: ",e);
            System.out.println("The error occurred.\n");
            sendMessage("�������� �������� �������: " + e);
            e.printStackTrace();
        }

    }
}