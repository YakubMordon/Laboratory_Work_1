package menu.CapacityCommand;

import AirlinesContent.Airline;
import AirlinesContent.PlaneTypes.Plane;
import menu.MenuItem;

import java.util.logging.Logger;

public class TotalCarryingCapCommand implements MenuItem {

    @Override
    public void execute(Airline content, Logger logger) {
        content.getCompanyPlanes().setTotalCarryingCap(Plane.getTotalCarryingCap(content.getCompanyPlanes().getPlanes(),logger));
    }
}
