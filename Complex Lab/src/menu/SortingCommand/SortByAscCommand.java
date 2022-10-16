package menu.SortingCommand;

import AirlinesContent.*;
import AirlinesContent.PlaneTypes.Plane;
import menu.MenuItem;

import java.util.logging.Logger;

public class SortByAscCommand implements MenuItem {
    @Override
    public void execute(Airline content, Logger logger) {
        content.getCompanyPlanes().setPlanes(Plane.sortPlanes(content.getCompanyPlanes().getPlanes(), Plane.rangeSorting.ASC,logger));
    }
}
