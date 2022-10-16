package menu;

import AirlinesContent.Airline;

import menu.CapacityCommand.*;
import menu.FileCommand.*;
import menu.GlobalCommand.*;
import menu.InsertCommand.*;
import menu.SortingCommand.*;

import java.util.*;
import java.util.logging.Logger;

public class MainMenu {
    private Map<String,MenuItem> menuItems;

    public MainMenu(){
        menuItems = new LinkedHashMap<>();

        menuItems.put("Total Carrying Capacity",new TotalCarryingCapCommand());
        menuItems.put("Total Passenger Capacity",new TotalPassengerCapCommand());

        menuItems.put("Read File",new ReadFileCommand());
        menuItems.put("Write File",new WriteFileCommand());

        menuItems.put("Create Company",new CreateCompanyCommand());
        menuItems.put("Exit",new ExitCommand());
        menuItems.put("Filter",new FilterCommand());
        menuItems.put("Info",new InfoCommand());

        menuItems.put("Insert Cargo",new InsertCargoPlaneCommand());
        menuItems.put("Insert Passenger",new InsertPassangerPlaneCommand());

        menuItems.put("Sort By Asc",new SortByAscCommand());
        menuItems.put("Sort By Desc",new SortByDescCommand());

    }

    public void execute(String command, Airline content, Logger logger) throws Exception {

        if(menuItems.get(command) == null){

            logger.info("Введено неправильну команду");

            System.out.println("Incorrect command!");
            return;
        }

        if(Objects.equals(content.getAirlineName(), "") && !Objects.equals(command, "Create Company")){
            Scanner reader = new Scanner(System.in);

            logger.info("Ви ще не створили компанії, напишіть ім'я своєї компанії: ");
            System.out.println("\nYou haven't created company, write your company name: ");
            content.setAirlineName(reader.nextLine());
            return;
        }

        logger.info("Виконання команди: " + command);
        menuItems.get(command).execute(content,logger);
    }

    public Set<String> getAllCommands(){
        return menuItems.keySet();
    }
}
