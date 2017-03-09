package dao;

import beans.User;
import utils.MyJdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by dd on 2017/3/2.
 */
public class UserDao {

    private Connection conn = null;
    private PreparedStatement pStat = null;
    private ResultSet rs = null;

    public void close(){
        MyJdbcUtils.close(conn, pStat, rs);
    }

    public boolean exists(User user) {
        conn = MyJdbcUtils.getConn();
        try {
            String sql = "SELECT * FROM users WHERE username = ? OR password = ?";
            pStat = conn.prepareStatement(sql);
            pStat.setString(1, user.getUsername());
            pStat.setString(2, user.getAccount());
            rs = pStat.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close();
        }
    }

    public boolean userLogin(User user) {
        conn = MyJdbcUtils.getConn();
        try {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            pStat = conn.prepareStatement(sql);
            pStat.setString(1, user.getUsername());
            pStat.setString(2, user.getPassword());
            rs = pStat.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close();
        }
    }

    public boolean addUser(User user) {
        try {
            conn = MyJdbcUtils.getConn();
            String sql = "INSERT INTO users VALUES(null, ?, ?, ?)";
            pStat = conn.prepareStatement(sql);
            pStat.setString(1, user.getUsername());
            pStat.setString(2, user.getAccount());
            pStat.setString(3, user.getPassword());
            int cnt = pStat.executeUpdate();
            return cnt > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close();
        }
    }

    //根据用户名或账号查询用户id
    public int getUserId(User user) {
        conn = MyJdbcUtils.getConn();
        try {
            String sql = "SELECT id FROM users WHERE username = ? OR account = ?";
            pStat = conn.prepareStatement(sql);
            pStat.setString(1, user.getUsername());
            pStat.setString(2, user.getAccount());
            rs = pStat.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            close();
        }
    }

    public String getUsername(User user) {
        conn = MyJdbcUtils.getConn();
        try {
            String sql = "SELECT username FROM users WHERE id = ?";
            pStat = conn.prepareStatement(sql);
            pStat.setInt(1, user.getId());
            rs = pStat.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            close();
        }
    }
}
