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

/**
 * Created by dd on 2017/3/7.
 */
@WebServlet(name = "ServletAddFriends", urlPatterns = "/add-friend")
public class ServletAddFriends extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();

        UserDao ud = new UserDao();
        UserRelativeDao urd = new UserRelativeDao();

        UserRelative ur = new UserRelative();
        ur.setUid1(Integer.valueOf(req.getParameter("uid1")));
        ur.setUid2(Integer.valueOf(req.getParameter("uid2")));
        if (ur.getUid1() == ur.getUid2()) {
            writer.write("false"); //不能添加自己为好友
            return;
        }

        if (!urd.exists(ur)) {
            urd.addUserRelative(ur);
            writer.write("true"); //添加成功
        } else {
            writer.write("false"); //对方已经是你的好友
        }

        writer.flush();
        writer.close();
    }
}
