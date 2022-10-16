package menu.GlobalCommand;

import AirlinesContent.PlaneTypes.*;
import AirlinesContent.Airline;
import menu.MenuItem;

import java.util.logging.Logger;

public class InfoCommand implements MenuItem {
    @Override
    public void execute(Airline content, Logger logger) {
        logger.info("Виводимо назву компанії ");
        System.out.println(content.getAirlineName());
        Plane.listOfPlanes(content.getCompanyPlanes().getPlanes(),logger);

    }
}
