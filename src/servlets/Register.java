package servlets;

import beans.User;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dd on 2017/3/2.
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String username = req.getParameter("username");
        String account = req.getParameter("account");
        String password = req.getParameter("password");

        UserDao ud = new UserDao();

        if(ud.findByUserName(username)){
            req.getSession().setAttribute("hint", "用户名已存在，注册失败！");
            req.getSession().setAttribute("color", "red");
            resp.sendRedirect("register.jsp");
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setAccount(account);
            if(ud.addUser(user)){
                req.getSession().setAttribute("hint", "注册成功，请登录！");
                req.getSession().setAttribute("color", "green");
            } else {
                req.getSession().setAttribute("hint", "服务错误");
                req.getSession().setAttribute("color", "red");
            }
            resp.sendRedirect("index.jsp");
        }
    }
}
