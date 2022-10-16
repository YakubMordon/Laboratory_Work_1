package menu.InsertCommand;

import AirlinesContent.Airline;
import AirlinesContent.PlaneTypes.*;
import menu.MenuItem;

import java.util.Scanner;
import java.util.logging.Logger;

public class InsertPassangerPlaneCommand implements MenuItem {

    @Override
    public void execute(Airline content, Logger logger) {
        Scanner reader = new Scanner(System.in);

        System.out.println("\nWrite your name of model: ");
        logger.info("������� ����� �����");
        String model = reader.nextLine();

        System.out.println("\nWrite flight range of your plane: ");
        logger.info("������� �������� �������");
        int flightRange = Integer.parseInt(reader.nextLine());

        content.getCompanyPlanes().addPlane(Plane.PlaneType.PASSENGER,model,flightRange,logger);
    }
}
