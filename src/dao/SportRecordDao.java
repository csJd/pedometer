package dao;

import beans.SportRecord;
import beans.SportRecordsDay;
import beans.SportRecordsStatistics;
import beans.User;
import utils.MyJdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by dd on 2017/3/3.
 */
public class SportRecordDao {

    private Connection conn = null;
    private PreparedStatement pStat = null;
    private ResultSet rs = null;

    public void close() {
        MyJdbcUtils.close(conn, pStat, rs);
    }

    public boolean addRecord(SportRecord record) {
        conn = MyJdbcUtils.getConn();
        try {
            //id,uid,distance,time,speed,startTime,stopTime,stepCount
            String sql = "INSERT INTO sport_records VALUES(null, ?, ?, ?, ?, ?, ?, ?)";
            pStat = conn.prepareStatement(sql);
            pStat.setInt(1, record.getUid());
            pStat.setDouble(2, record.getDistance());
            pStat.setInt(3, record.getTime());
            pStat.setDouble(4, record.getSpeed());
            pStat.setString(5, record.getStartTime());
            pStat.setString(6, record.getStopTime());
            pStat.setInt(7, record.getStepCount());
            int cnt = pStat.executeUpdate();
            return cnt > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close();
        }
    }

    public ArrayList<SportRecordsStatistics> dateStatistics(User user) {
        return null;
    }

    public ArrayList<SportRecordsDay> dayStatistics(User user) {
        return null;
    }

    public void selectDay(int day, SportRecordsDay srd) {

    }

    public SportRecord sum(User user) {
        return null;
    }


}
