package AirlinesContent.Sorting;

import AirlinesContent.PlaneTypes.Plane;

import java.util.Comparator;

public class SortingAsc implements Comparator<Plane> {
    @Override
    public int compare(Plane a, Plane b) {
        return a.getFlightRange() - b.getFlightRange();
    }
}
