package servlets;

import beans.UserRelative;
import dao.UserDao;
import dao.UserRelativeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by dd on 2017/3/7.
 */
@WebServlet(name = "ServletAttentionFans", urlPatterns = "/fans")
public class ServletAttentionFans extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();

        UserDao ud = new UserDao();
        UserRelativeDao urd = new UserRelativeDao();

        int uid = ud.getUserId(req.getParameter("username"));
        UserRelative ur = new UserRelative();
        ur.setUid1(uid);
        ur.setUid2(uid);
        ArrayList<Integer> list1 = urd.findAllId1(ur);
        ArrayList<Integer> list2 = urd.findAllId2(ur);
        writer.write("fans: \n");
        for (Integer i : list1) {
            writer.write("  " + ud.getUsername(i));
        }
        writer.write("following: \n");
        for (Integer i : list2) {
            writer.write("  " + ud.getUsername(i));
        }

        writer.flush();
        writer.close();
    }
}
