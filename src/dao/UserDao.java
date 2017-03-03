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
        try{
            if(rs!=null) rs.close();
            if (pStat != null) pStat.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        conn = MyJdbcUtils.getConn();
        try {
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

}
