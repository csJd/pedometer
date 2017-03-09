package servlets;

import beans.HonorUser;
import dao.HonorUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dd on 2017/3/9.
 */
@WebServlet(name = "ServletAddHonor", urlPatterns = "/add-honor")
public class ServletAddHonor extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        HonorUserDao hud = new HonorUserDao();
        HonorUser honorUser = new HonorUser();
        honorUser.setHid(Integer.valueOf(req.getParameter("hid")));
        honorUser.setUid(Integer.valueOf(req.getParameter("uid")));

        PrintWriter writer = resp.getWriter();
        if (hud.addHonorUser(honorUser)) {
            writer.write("true");
        } else {
            writer.write("false");
        }

        writer.flush();
        writer.close();
    }
}
