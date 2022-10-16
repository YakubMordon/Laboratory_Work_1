package menu.GlobalCommand;

import AirlinesContent.Airline;
import AirlinesContent.PlaneTypes.Plane;
import menu.MenuItem;

import java.util.logging.Logger;

public class FilterCommand implements MenuItem {
    @Override
    public void execute(Airline content, Logger logger) {
        content.getCompanyPlanes().setPlanes(Plane.filterPlanes(content.getCompanyPlanes().getPlanes(),logger));
    }
}
