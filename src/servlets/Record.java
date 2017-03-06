package servlets;

import beans.SportRecord;
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
@WebServlet(name = "Record", urlPatterns = "/record")
public class Record extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();

        UserDao ud = new UserDao();
        SportRecordDao srd = new SportRecordDao();
        int uid = ud.getUserId(req.getParameter("username"));
        if (uid < 0) {
            writer.write("用户名不存在！");
        } else {
            SportRecord record = new SportRecord();
            record.setUid(uid);
            record.setDistance(Double.valueOf(req.getParameter("distance")));
            record.setTime(Integer.valueOf(req.getParameter("time")));
            record.setSpeed(Double.valueOf(req.getParameter("speed")));
            record.setStartTime(req.getParameter("startTime"));
            record.setStopTime(req.getParameter("stopTime"));
            record.setStepCount(Integer.parseInt(req.getParameter("stepCount")));

            String hint = "添加到数据库失败！";
            if (srd.addRecord(record)) {
                hint = "添加到数据库成功！";
            }
            writer.write(record.getStartTime() + "走了" + record.getStepCount() + "步！\n" + hint);
        }
        writer.close();
    }
}
