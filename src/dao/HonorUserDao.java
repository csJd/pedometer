package dao;

import beans.HonorUser;
import utils.MyJdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by dd on 2017/3/9.
 */
public class HonorUserDao {
    private Connection conn = null;
    private PreparedStatement pStat = null;
    private ResultSet rs = null;

    public void close() {
        MyJdbcUtils.close(conn, pStat, rs);
    }

    public boolean addHonorUser(HonorUser hu) {
        try {
            conn = MyJdbcUtils.getConn();
            String sql = "INSERT INTO honor_users VALUES(NULL, ?, ?)";
            pStat = conn.prepareStatement(sql);
            pStat.setInt(1, hu.getUid());
            pStat.setInt(2, hu.getHid());
            int cnt = pStat.executeUpdate();
            return cnt > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close();
        }
    }

    public boolean exists(HonorUser hu) {
        try {
            conn = MyJdbcUtils.getConn();
            String sql = "SELECT * FROM honor_users WHERE uid = ? AND hid = ?";
            pStat = conn.prepareStatement(sql);
            pStat.setInt(1, hu.getUid());
            pStat.setInt(2, hu.getHid());
            rs = pStat.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close();
        }
    }

    public ArrayList<Integer> allHonor(HonorUser hu) {
        ArrayList<Integer> ret = new ArrayList<>();
        try {
            conn = MyJdbcUtils.getConn();
            String sql = "SELECT hid FROM honor_users WHERE uid = ?";
            pStat = conn.prepareStatement(sql);
            pStat.setInt(1, hu.getUid());
            rs = pStat.executeQuery();
            while (rs.next()) {
                ret.add(rs.getInt(1));
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return ret;
        } finally {
            close();
        }
    }
}
