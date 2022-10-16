package menu.CapacityCommand;

import AirlinesContent.Airline;
import AirlinesContent.PlaneTypes.Plane;
import menu.MenuItem;

import java.util.logging.Logger;

public class TotalPassengerCapCommand implements MenuItem {
    @Override
    public void execute(Airline content, Logger logger) {
        content.getCompanyPlanes().setTotalPassengerCap(Plane.getTotalPassengerCap(content.getCompanyPlanes().getPlanes(), logger));
    }
}
