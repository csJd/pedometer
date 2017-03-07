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

    public boolean findByUserName(String username){
        conn = MyJdbcUtils.getConn();
        try {
            String sql = "SELECT * FROM users WHERE username = ?";
            pStat = conn.prepareStatement(sql);
            pStat.setString(1, username);
            rs = pStat.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close();
        }
    }

    public boolean userLogin(String username, String password){
        conn = MyJdbcUtils.getConn();
        try {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            pStat = conn.prepareStatement(sql);
            pStat.setString(1, username);
            pStat.setString(2, password);
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

    //根据用户名查询用户id
    public int getUserId(String username) {
        conn = MyJdbcUtils.getConn();
        try {
            String sql = "SELECT id FROM users WHERE username = " + username;
            pStat = conn.prepareStatement(sql);
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

    //根据账户查询用户id
    public int getUserIdByAccount(String account) {
        conn = MyJdbcUtils.getConn();
        try {
            String sql = "SELECT id FROM users WHERE account = " + account;
            pStat = conn.prepareStatement(sql);
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

    public String getUsername(int id) {
        conn = MyJdbcUtils.getConn();
        try {
            String sql = "SELECT username FROM users WHERE id = " + id;
            pStat = conn.prepareStatement(sql);
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
