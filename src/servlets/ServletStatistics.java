package servlets;

import beans.SportRecord;
import beans.SportRecordsDay;
import beans.SportRecordsStatistics;
import beans.User;
import dao.SportRecordDao;
import dao.UserDao;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by dd on 2017/3/6.
 */
@WebServlet(name = "ServletStatistics", urlPatterns = "/statistics")
public class ServletStatistics extends HttpServlet {

    private PrintWriter writer;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        writer = resp.getWriter();
        UserDao ud = new UserDao();
        SportRecordDao srd = new SportRecordDao();

        String method = req.getParameter("method");
        switch (method) {
            case "week":
                week(req, ud, srd);
            case "month":
                month(req, ud, srd);
                break;
            case "year":
                year(req, ud, srd);
                break;
            case "total":
                total(req, ud, srd);
                break;
            default:
                break;
        }
        writer.flush();
        writer.close();
    }

    private void total(HttpServletRequest req, UserDao ud, SportRecordDao srd) {
        User user = new User();
        String username = req.getParameter("username");
        user.setUsername(username);

        //search userId by username
        int id = ud.getUserId(user);
        if (id < 0) {
            writer.write("userNotExist");
            return;
        }

        user.setId(id);
        user.setSql("%Y"); //search by year
        ArrayList<SportRecordsStatistics> listYear = srd.dateStatistics(user);
        ArrayList<Double> list = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            list.add(0.0);
        }
        for (SportRecordsStatistics srs : listYear) {
            int year = srs.getDate(); //get the year
            String str = ("" + year).substring(3, 4);
            list.set(Integer.valueOf(str), srs.getTotalDistance());
        }

        SportRecord sr = srd.sum(user);
        JSONObject json = new JSONObject();
        JSONArray ja = new JSONArray();
        try {
            for (Double dist : list) {
                JSONObject jo = new JSONObject();
                jo.put("totalDistance", dist);
                ja.put(jo);
            }
            json.put("total", ja);
            json.put("totalDistance", sr.getDistance());
            json.put("totalStepCount", sr.getStepCount());
            json.put("totalTime", sr.getTime());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        writer.print(json.toString());
    }

    //按照每一年,来统计运动信息
    private void year(HttpServletRequest request, UserDao ud, SportRecordDao srd) {
        User user = new User();
        String username = request.getParameter("username");
        user.setUsername(username);
        user.setId(ud.getUserId(user));
        user.setSql("%Y"); //按年
        ArrayList<SportRecordsStatistics> listYear = srd.dateStatistics(user);
        user.setSql("%Y%m"); //按月
        ArrayList<SportRecordsStatistics> listMonth = srd.dateStatistics(user);

        // 月份统计出来的date值是比如201701表示一月份的数据.按照年份统计出来的比如2017表示2017年,所以根据date值来匹配.
        for (SportRecordsStatistics sry : listYear) {
            // SportsRecordDay 表示每一个月份的数据
            ArrayList<SportRecordsDay> list = sry.getList();
            for (int i = 0; i < 12; i++) {
                list.add(new SportRecordsDay());
            }
            for (SportRecordsStatistics srm : listMonth) {
                int year = srm.getDate() / 100;
                if (year == sry.getDate()) {
                    //day表示每个月的运动参数
                    SportRecordsDay srday = new SportRecordsDay();
                    srday.setTotalDistance(srm.getTotalDistance());
                    list.set(srm.getDate() % 100 - 1, srday);
                }
            }
        }

        // 将listYear中的数据,以json字符串的形式,返回给客户端.
        JSONArray ja = new JSONArray();
        for (SportRecordsStatistics sry : listYear) {
            JSONObject json = new JSONObject();
            try {
                json.put("totalDistance", sry.getTotalDistance());
                json.put("totalStepCount", sry.getTotalCount());
                json.put("totalTime", sry.getTotalTime());
                json.put("year", sry.getDate());
                JSONArray ja2 = new JSONArray();
                for (SportRecordsDay srday : sry.getList()) {
                    JSONObject json2 = new JSONObject();
                    json2.put("monthTotalDistance", srday.getTotalDistance());// 某一月运动总距离
                    ja2.put(json2);
                }
                json.put("yearTotal", ja2);
                ja.put(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        writer.print(ja.toString());
    }

    //按照每一月,来统计运动信息
    private void month(HttpServletRequest request, UserDao ud, SportRecordDao srd) {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setId(ud.getUserId(user));

        user.setSql("%Y%m");  //按月查询
        ArrayList<SportRecordsStatistics> listMonth = srd.dateStatistics(user);
        ArrayList<SportRecordsDay> listDay = srd.dayStatistics(user);
        // 遍历listMonth,和listday,使listday中每一天所属的月份与listMonth中每个元素所属的月份数匹配,对listday中的数据,进行分类,封装到listMonth中去.
        for (SportRecordsStatistics srm : listMonth) {
            for (int i = 0; i < 31; i++) {
                srm.getList().add(new SportRecordsDay());
            }
            for (SportRecordsDay srday : listDay) {
                if (srm.getDate() % 100 == srday.getMonth()) {
                    srm.getList().set(srday.getDay() - 1, srday);
                }
            }
        }

        // 将listMonth中的数据,以json字符串的形式,返回给客户端.
        JSONArray ja = new JSONArray();
        for (SportRecordsStatistics srw : listMonth) {
            JSONObject json = new JSONObject();
            try {
                json.put("totalDistance", srw.getTotalDistance());
                json.put("totalStepCount", srw.getTotalCount());
                json.put("totalTime", srw.getTotalTime());
                json.put("month", srw.getDate());
                JSONArray ja2 = new JSONArray();
                for (SportRecordsDay srday : srw.getList()) {
                    JSONObject json2 = new JSONObject();
                    json2.put("dayTotalDistance", srday.getTotalDistance());// 某一天运动总距离
                    ja2.put(json2);
                }
                json.put("monthTotal", ja2);
                ja.put(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        writer.print(ja.toString());
    }

    private void week(HttpServletRequest req, UserDao ud, SportRecordDao srd) {
        User user = new User();
        //获取提交的用户名
        String username = req.getParameter("username");
        user.setUsername(username);
        // 根据用户名查询id.
        int id = ud.getUserId(user);
        user.setId(id);
        // 给user设置查询语句
        user.setSql("%Y%u");
        // 查询数据库,按星期,对各项参数求和.每个星期作为一个元素.存到集合中,其中,每个星期的距离,需要每一天的具体数据.
        ArrayList<SportRecordsStatistics> listWeek = srd.dateStatistics(user);
        // listWeek集合中的元素,每个SportsRecordStatistics对象,少一个属性,每个星期的每一天的运动距离总和.
        // 查询每一天的运动参数总和
        ArrayList<SportRecordsDay> listDay = srd.dayStatistics(user);
        // 遍历listweek,和listday,使listday中每一天所属的星期数与listweek中每个元素所属的星期数匹配,对listday中的数据,进行分类,封装到listweek中去.
        for (SportRecordsStatistics srw : listWeek) {
            // 给srw中的封装每一个元素的list集合属性赋值.每个集合有七个值.
            for (int i = 0; i < 7; i++) {
                srw.getList().add(new SportRecordsDay());
            }
            for (SportRecordsDay rd : listDay) {

                if (srw.getDate() % 100 == rd.getWeek()) {
                    // 拿到该天属于星期几. 0表示星期一
                    int weekDay = rd.getWeekDay();
                    // 将对应的值填到对应的位置上.
                    srw.getList().set(weekDay, rd);
                }
            }
        }
        // 将listweek中的数据,以json字符串的形式,返回给客户端.
        JSONArray ja = new JSONArray();
        for (SportRecordsStatistics srw : listWeek) {
            JSONObject json = new JSONObject();
            try {
                json.put("totalDistance", srw.getTotalDistance());
                json.put("totalStepCount", srw.getTotalCount());
                json.put("totalTime", srw.getTotalTime());
                json.put("week", srw.getDate());
                JSONArray ja2 = new JSONArray();
                for (SportRecordsDay srday : srw.getList()) {
                    JSONObject json2 = new JSONObject();
                    json2.put("dayTotalDistance", srday.getTotalDistance());// 某一天运动总距离
                    ja2.put(json2);
                }
                json.put("weekTotal", ja2);
                ja.put(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        writer.print(ja.toString());
    }
}
