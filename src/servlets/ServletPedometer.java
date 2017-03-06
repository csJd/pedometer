package servlets;

import beans.SportRecordsDay;
import beans.SportRecordsStatistics;
import beans.User;
import dao.SportRecordDao;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dd on 2017/3/6.
 */
@WebServlet(name = "ServletPedometer", urlPatterns = "/Pedometer")
public class ServletPedometer extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        UserDao ud = new UserDao();
        SportRecordDao srd = new SportRecordDao();

        String method = req.getParameter("method");
        switch (method) {
            case "week":
                break;
            case "month":
                break;
            case "year":
                break;
            case "total":
                break;
        }
    }

    private void week(HttpServletRequest req, UserDao ud, SportRecordDao srd) {
        User user = new User();
        ArrayList<SportRecordsStatistics> listWeek = srd.dateStatistics(user);
        ArrayList<SportRecordsDay> listDya = srd.dayStatistics(user);
    }
}
