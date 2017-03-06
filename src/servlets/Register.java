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
@WebServlet(name = "Register", urlPatterns = "/register")
public class Register extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String username = req.getParameter("username");
        String account = req.getParameter("account");
        String password = req.getParameter("password");

        UserDao ud = new UserDao();
        PrintWriter writer = resp.getWriter();

        if(ud.findByUserName(username)){
            writer.write("用户名已存在，注册失败！");
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setAccount(account);
            if(ud.addUser(user)){
                writer.write("注册成功！");
            } else {
                writer.write("服务错误！");
            }
        }
        writer.flush();
        writer.close();
    }
}
