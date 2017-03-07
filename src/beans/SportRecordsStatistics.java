package beans;

import java.util.ArrayList;

/**
 * Created by dd on 2017/3/6.
 */
public class SportRecordsStatistics {
    private double totalDistance; //总运动距离
    private int totalTime;  //运动时间
    private int totalCount;  //运动步数
    private int date; //这条记录属于哪一周，或哪一月，或哪一年
    private ArrayList<SportRecordsDay> list = new ArrayList<>();

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public ArrayList<SportRecordsDay> getList() {
        return list;
    }

    public void setList(ArrayList<SportRecordsDay> list) {
        this.list = list;
    }
}
