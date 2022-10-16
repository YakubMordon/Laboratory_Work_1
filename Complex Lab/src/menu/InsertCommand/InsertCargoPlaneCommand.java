package menu.InsertCommand;

import AirlinesContent.Airline;
import AirlinesContent.PlaneTypes.Plane;
import menu.MenuItem;

import java.util.Scanner;
import java.util.logging.Logger;

public class InsertCargoPlaneCommand implements MenuItem {
    @Override
    public void execute(Airline content, Logger logger) {

        Scanner reader = new Scanner(System.in);

        System.out.println("\nWrite your name of model: ");
        logger.info("¬водимо назву модел≥");
        String model = reader.nextLine();

        System.out.println("\nWrite flight range of your plane: ");
        logger.info("¬водимо дальн≥сть польоту");
        int flightRange = Integer.parseInt(reader.nextLine());

        content.getCompanyPlanes().addPlane(Plane.PlaneType.CARGO,model,flightRange, logger);
    }
}
