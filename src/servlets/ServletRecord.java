package servlets;

import beans.SportRecord;
import beans.User;
import dao.SportRecordDao;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dd on 2017/3/6.
 */
@WebServlet(name = "ServletRecord", urlPatterns = "/record")
public class ServletRecord extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();

        UserDao ud = new UserDao();
        SportRecordDao srd = new SportRecordDao();
        User user = new User();
        user.setUsername(req.getParameter("username"));
        int uid = ud.getUserId(user);
        if (uid < 0) {
            writer.write("error_username");
        } else {
            SportRecord record = new SportRecord();
            record.setUid(uid);
            record.setDistance(Double.valueOf(req.getParameter("distance")));
            record.setTime(Integer.valueOf(req.getParameter("time")));
            record.setSpeed(Double.valueOf(req.getParameter("speed")));
            record.setStartTime(req.getParameter("startTime"));
            record.setStopTime(req.getParameter("stopTime"));
            record.setStepCount(Integer.parseInt(req.getParameter("stepCount")));
            String dateRegex = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";
            String hint = "false"; //输入是否合法，是否成功添加到数据库
            if (record.getStartTime().matches(dateRegex)) {
                if (srd.addRecord(record)) {
                    hint = "true";
                }
                //writer.write(record.getStartTime() + "走了" + record.getStepCount() + "步！\n" + hint);
                writer.write(hint);
            }
        }
        writer.flush();
        writer.close();
    }
}
