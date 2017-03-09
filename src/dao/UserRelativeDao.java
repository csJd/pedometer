package dao;

import beans.UserRelative;
import utils.MyJdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by dd on 2017/3/7.
 */
public class UserRelativeDao {

    private Connection conn = null;
    private PreparedStatement pStat = null;
    private ResultSet rs = null;

    public void close() {
        MyJdbcUtils.close(conn, pStat, rs);
    }

    public boolean addUserRelative(UserRelative ur) {
        try {
            conn = MyJdbcUtils.getConn();
            String sql = "INSERT INTO user_relatives VALUES(NULL, ?, ?)";
            pStat = conn.prepareStatement(sql);
            pStat.setInt(1, ur.getUid1());
            pStat.setInt(2, ur.getUid2());
            int cnt = pStat.executeUpdate();
            return cnt > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close();
        }
    }

    public boolean exists(UserRelative ur) {
        String sql = "SELECT * FROM user_relatives" +
                " WHERE uid1 = " + ur.getUid1() +
                " AND uid2 = " + ur.getUid2();
        try {
            conn = MyJdbcUtils.getConn();
            pStat = conn.prepareStatement(sql);
            ResultSet rs = pStat.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close();
        }
    }

    public ArrayList<Integer> findAllId(UserRelative ur, int op) {
        String sql = "SELECT uid1 FROM user_relatives WHERE uid2 = ?";
        int uid = ur.getUid2();
        if (op == 2) {
            sql = "SELECT uid2 FROM user_relatives WHERE uid1 = ?";
            uid = ur.getUid1();
        }
        ArrayList<Integer> ret = new ArrayList<>();
        try {
            conn = MyJdbcUtils.getConn();
            pStat = conn.prepareStatement(sql);
            pStat.setInt(1, uid);
            rs = pStat.executeQuery();
            while (rs.next()) {
                ret.add(rs.getInt(1));
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return ret;
        }
    }
}
