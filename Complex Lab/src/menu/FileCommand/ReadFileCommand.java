package menu.FileCommand;

import AirlinesContent.Airline;
import File.*;
import menu.MenuItem;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static message.SendEmail.sendMessage;

public class ReadFileCommand implements MenuItem {
    @Override
    public void execute(Airline content, Logger logger) throws Exception {
        FileOper fileRead = new FileOper();
        Scanner reader = new Scanner(System.in);
        System.out.println("\nWrite your file name: ");
        String destination = reader.nextLine();

        try {
            content = fileRead.readFile(destination,logger);
        } catch (Exception e) {
            logger.log(Level.WARNING,"Знайдено критичну помилку: ",e);
            System.out.println("The error occurred.\n");
            sendMessage("Знайдено критичну помилку: " + e);
            e.printStackTrace();
        }
    }
}
