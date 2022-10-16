package AirlinesContent.PlaneTypes;

import AirlinesContent.Sorting.*;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Plane {

    int passengerCapacity;
    int carryingCapacity;
    int flightRange;
    double fuelConsumption;

    String modelName;

    static int totalPassengerCap = 0;
    static int totalCarryingCap = 0;

    static ArrayList<Plane> planes = new ArrayList<Plane>();
    public enum PlaneType{
        PASSENGER,
        CARGO
    }

    PlaneType type;

    public enum rangeSorting{
        ASC,
        DESC
    }

    @Override
    public String toString() {
        return  " Passenger Capacity = " + passengerCapacity +
                ", Carrying Capacity = " + carryingCapacity +
                " tons, Flight Range = " + flightRange +
                " km, Fuel Consumption = " + fuelConsumption +
                " per km, Model = '" + modelName + '\'' + '\n';
    }

    public static void addPlane(PlaneType type, String model, int flightRange, Logger logger) {
        logger.finer("������ ���� �� ���� ������. �� ������� ������� ���, ���� �� ����");

        if (type == PlaneType.PASSENGER)
            planes.add(new Passenger(model, flightRange));
        else
            planes.add(new Cargo(model, flightRange));
    }

    public static void listOfPlanes(ArrayList<Plane> planes, Logger logger){

        logger.finest("�������� ������ ����� � ����� ������. � ��������, ���� ���� ����� � ������.");

        if(planes.size() == 0){
            System.out.println("There are no planes.");
            return;
        }

        for(int i = 0; i < planes.size(); i++) {
            System.out.print((i + 1) + " Type Of Plane: " + planes.get(i).getType() + "," + planes.get(i).toString());
        }
    }

    public static ArrayList<Plane> sortPlanes(ArrayList<Plane> planes, rangeSorting type, Logger logger) {
        logger.fine("��������� ArrayList, �� ��������� ��� ����������� ������. ���� ���������� �� ������� ������� ����� �����, ��� �������� ������� �����������.");
        ArrayList<Plane> sortedArray = new ArrayList<Plane>(planes);

        if (type == rangeSorting.ASC) {
            logger.info("������� �� ����������");
            sortedArray.sort(new SortingAsc());
        }else {
            logger.info("������� �� ���������");
            sortedArray.sort(new SortingDesc());
        }

        return sortedArray;
    }

    public static ArrayList<Plane> filterPlanes(ArrayList<Plane> planes, Logger logger){
        logger.fine("Գ������� ���� ����� �� ���������� ��������, �������� � ���������� ������� ������.");
        return planes.stream().filter(i -> i.fuelConsumption > 5 && i.fuelConsumption < 15).collect(Collectors.toCollection(ArrayList::new));
    }

    public static int getTotalPassengerCap(ArrayList<Plane> planes, Logger logger) {
        logger.finer("��������� �������� ������� ������������ ����, ��� �������� �� ������� �������� ����������" +
                " �� ��������� �������� ������� ������������ ����.");
        totalPassengerCap = 0;
        planes.forEach((plane) -> totalPassengerCap += plane.passengerCapacity);
        System.out.println("\nTotal Passenger Capacity: " + totalPassengerCap);
        return totalPassengerCap;
    }

    public static int getTotalCarryingCap(ArrayList<Plane> planes, Logger logger) {
        logger.finer("��������� �������� ����������������, ��� �������� �� ������� �������� ����������" +
                " �� ��������� �������� ����������������.");
        totalCarryingCap = 0;
        planes.forEach((plane) -> totalCarryingCap += plane.carryingCapacity);

        System.out.println("\nTotal Carrying Capacity: " + totalCarryingCap + " tons");
        return totalCarryingCap;
    }
    public int getFlightRange() { return flightRange; }

    public String getModelName() { return modelName; }

    public static ArrayList<Plane> getPlanes() { return planes; }

    public PlaneType getType() { return type; }

    public static void setPlanes(ArrayList<Plane> planes) { Plane.planes = planes; }

    public static void setTotalPassengerCap(int totalPassengerCap) { Plane.totalPassengerCap = totalPassengerCap; }

    public static void setTotalCarryingCap(int totalCarryingCap) { Plane.totalCarryingCap = totalCarryingCap; }
}
