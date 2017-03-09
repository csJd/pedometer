package servlets;

import beans.User;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dd on 2017/3/2.
 */
@WebServlet(name = "ServletRegister", urlPatterns = "/register")
public class ServletRegister extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String username = req.getParameter("username");
        String account = req.getParameter("account");
        String password = req.getParameter("password");

        UserDao ud = new UserDao();
        PrintWriter writer = resp.getWriter();

        User user = new User();
        user.setUsername(username);
        user.setAccount(account);

        if (ud.exists(user)) {
            writer.write("false");
        } else {
            user.setPassword(password);
            if(ud.addUser(user)){
                writer.write("true");
            } else {
                writer.write("error");
            }
        }
        writer.flush();
        writer.close();
    }
}
