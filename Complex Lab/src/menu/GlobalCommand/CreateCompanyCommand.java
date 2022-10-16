package menu.GlobalCommand;

import AirlinesContent.Airline;
import menu.MenuItem;

import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;

public class CreateCompanyCommand implements MenuItem {
    @Override
    public void execute(Airline content, Logger logger) {

        logger.finer("��������� �������...");

        if(Objects.equals(content.getAirlineName(), "")) {
            Scanner reader = new Scanner(System.in);

            logger.finer("������� ����� ������");

            System.out.println("\nWrite your company name: ");
            content.setAirlineName(reader.nextLine());
        }else {
            logger.finer("������� ��� ����");
            System.out.println("\nYour company is created. Try something else");
        }
    }
}
