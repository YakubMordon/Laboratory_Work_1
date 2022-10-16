package menu.FileCommand;

import AirlinesContent.Airline;
import File.*;
import menu.MenuItem;

import java.util.Scanner;
import java.util.logging.Logger;

public class WriteFileCommand implements MenuItem {
    @Override
    public void execute(Airline content, Logger logger) throws Exception {
        Scanner reader = new Scanner(System.in);
        System.out.println("\nWrite your file name: ");
        String destination = reader.nextLine();
        new FileOper().writeFile(destination,content,logger);
    }
}
