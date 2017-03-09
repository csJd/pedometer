package beans;

/**
 * Created by dd on 2017/3/9.
 */
public class RecordRank implements Comparable<RecordRank> {
    private boolean isSelf = false;
    private String username;
    private Double totalDistance;

    @Override
    public int compareTo(RecordRank r) {
        return r.totalDistance.compareTo(totalDistance);
    }

    public boolean isSelf() {
        return isSelf;
    }

    public void setSelf(boolean self) {
        isSelf = self;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Double totalDistance) {
        this.totalDistance = totalDistance;
    }
}
