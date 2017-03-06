package beans;

import java.util.ArrayList;

/**
 * Created by dd on 2017/3/6.
 */
public class SportRecordsStatistics {
    private double totalDistance;
    private int totalTime;
    private int totalCount;
    private int date;
    private ArrayList<SportRecordsDay> list = new ArrayList<>();

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }
}
