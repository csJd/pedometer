package servlets;

import beans.RecordRank;
import beans.SportRecord;
import beans.User;
import beans.UserRelative;
import dao.SportRecordDao;
import dao.UserDao;
import dao.UserRelativeDao;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by dd on 2017/3/9.
 */
@WebServlet(name = "ServletRank", urlPatterns = "/rank")
public class ServletRank extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        UserRelative ur = new UserRelative();
        int id = Integer.valueOf(req.getParameter("id"));
        ur.setUid1(id);
        UserDao ud = new UserDao();
        UserRelativeDao urd = new UserRelativeDao();
        SportRecordDao srd = new SportRecordDao();
        ArrayList<Integer> friendList = urd.findAllId(ur, 2);
        friendList.add(id); //把自己添加到好友列表
        ArrayList<RecordRank> listRank = new ArrayList<>();

        for (int i : friendList) {
            User user = new User();
            user.setId(i);
            user.setUsername(ud.getUsername(user));
            SportRecord sr = srd.sum(user);
            RecordRank recordRank = new RecordRank();
            recordRank.setUsername(user.getUsername());
            recordRank.setSelf(i == id); //id = i 时为自己
            recordRank.setTotalDistance(sr.getDistance());
            listRank.add(recordRank);
        }

        Collections.sort(listRank);  //排序
        JSONArray ja = new JSONArray();
        for (RecordRank rank : listRank) {
            JSONObject jo = new JSONObject();
            jo.put("username", rank.getUsername());
            jo.put("isSelf", rank.isSelf());
            jo.put("totalDistance", rank.getTotalDistance());
            ja.put(ja);
        }

        PrintWriter writer = resp.getWriter();
        writer.write(ja.toString());
        writer.flush();
        writer.close();
    }
}
