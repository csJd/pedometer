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

    private void close() {
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
        ArrayList<SportRecordsStatistics> ret = new ArrayList<>();

        String sql = "SELECT DATE_FORMAT(start_time, '" + user.getSql() + "') AS date," +
                " SUM(step_count) AS total_count," +
                " SUM(time) AS total_time," +
                " SUM(distance) AS total_distance" +
                " FROM sport_records" +
                " WHERE uid = " + user.getId() +
                " GROUP BY date";
        // System.out.println(sql);

        try {
            conn = MyJdbcUtils.getConn();
            pStat = conn.prepareStatement(sql);
            rs = pStat.executeQuery();
            while (rs.next()) {
                SportRecordsStatistics srs = new SportRecordsStatistics();
                srs.setDate(rs.getInt(1));
                srs.setTotalCount(rs.getInt(2));
                srs.setTotalTime(rs.getInt(3));
                srs.setTotalDistance(rs.getDouble(4));
                ret.add(srs);
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return ret;
        } finally {
            close();
        }
    }

    public ArrayList<SportRecordsDay> dayStatistics(User user) {
        ArrayList<SportRecordsDay> ret = new ArrayList<>();

        String sql = "SELECT SUM(distance) AS total_distance," +
                " SUM(time) AS total_time," +
                " SUM(step_count) AS total_count," +
                " DAY(start_time) AS day," +
                " MONTH(start_time) AS month," +
                " WEEK(start_time) AS week," +
                " WEEKDAY(start_time) AS weekday" +
                " FROM sport_records" +
                " WHERE uid = " + user.getId() +
                " GROUP BY start_time";

        try {
            conn = MyJdbcUtils.getConn();
            pStat = conn.prepareStatement(sql);
            rs = pStat.executeQuery();
            while (rs.next()) {
                SportRecordsDay srd = new SportRecordsDay();
                srd.setTotalDistance(rs.getDouble(1));
                srd.setTotalTime(rs.getInt(2));
                srd.setTotalCount(rs.getInt(3));
                srd.setDay(rs.getInt(4));
                srd.setMonth(rs.getInt(5));
                srd.setWeek(rs.getInt(6));
                srd.setWeekDay(rs.getInt(7));
                ret.add(srd);
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return ret;
        } finally {
            close();
        }
    }

    public SportRecord sum(User user) {
        SportRecord sr = new SportRecord();
        String sql = "SELECT SUM(step_count) AS step_count," +
                " SUM(time) AS time," +
                " SUM(distance) AS distance" +
                " FROM sport_records" +
                " WHERE uid = " + user.getId();
        try {
            conn = MyJdbcUtils.getConn();
            pStat = conn.prepareStatement(sql);
            rs = pStat.executeQuery();
            if (rs.next()) {
                sr.setStepCount(rs.getInt(1));
                sr.setTime(rs.getInt(2));
                sr.setDistance(rs.getDouble(3));
            }
            return sr;

        } catch (Exception e) {
            e.printStackTrace();
            return sr;
        } finally {
            close();
        }
    }

}
