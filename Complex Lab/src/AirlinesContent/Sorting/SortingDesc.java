package AirlinesContent.Sorting;

import AirlinesContent.PlaneTypes.Plane;

import java.util.Comparator;

public class SortingDesc implements Comparator<Plane> {
    @Override
    public int compare(Plane a, Plane b) {
        return b.getFlightRange() - a.getFlightRange();
    }
}
