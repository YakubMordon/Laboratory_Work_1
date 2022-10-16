package menu.GlobalCommand;

import AirlinesContent.Airline;
import menu.MenuItem;

import java.util.logging.Logger;

public class ExitCommand implements MenuItem {
    @Override
    public void execute(Airline content, Logger logger) {
        logger.finest("Завершуємо програму ");

        System.out.println("Ending the process");
        System.exit(0);
    }
}
