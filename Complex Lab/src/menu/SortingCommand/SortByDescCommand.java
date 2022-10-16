package menu.SortingCommand;

import AirlinesContent.Airline;
import AirlinesContent.PlaneTypes.Plane;
import menu.MenuItem;

import java.util.logging.Logger;

public class SortByDescCommand implements MenuItem {
    @Override
    public void execute(Airline content, Logger logger) {
        content.getCompanyPlanes().setPlanes(Plane.sortPlanes(content.getCompanyPlanes().getPlanes(), Plane.rangeSorting.DESC,logger));
    }
}
