package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by dd on 2017/3/2.
 */
public class MyJdbcUtils {
    public static String DB_DRIVER = "com.mysql.jdbc.Driver";
    public static String DB_URL = "jdbc:mysql://localhost:3306/pedometer";
    public static String DB_USER = "root";
    public static String DB_PWD = "9508";

    public static Connection getConn(){
        try{
            Class.forName(DB_DRIVER).newInstance();
            return DriverManager.getConnection(DB_URL,DB_USER,DB_PWD);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void close(Connection conn, PreparedStatement pStat, ResultSet rs) {
        try{
            if (rs != null) rs.close();
            if (pStat != null) pStat.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
