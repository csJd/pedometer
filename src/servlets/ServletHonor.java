package servlets;

import beans.HonorUser;
import dao.HonorUserDao;
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

/**
 * Created by dd on 2017/3/9.
 */
@WebServlet(name = "ServletHonor", urlPatterns = "/honor")
public class ServletHonor extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        HonorUserDao hud = new HonorUserDao();
        HonorUser hu = new HonorUser();
        hu.setUid(Integer.valueOf(req.getParameter("uid")));
        ArrayList<Integer> honorList = hud.allHonor(hu);
        JSONArray ja = new JSONArray();
        for (int i : honorList) {
            JSONObject jo = new JSONObject();
            jo.put("hid", i);
        }
        PrintWriter writer = resp.getWriter();
        writer.write(ja.toString());
        writer.flush();
        writer.close();
    }
}
