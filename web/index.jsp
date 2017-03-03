<%--
  Created by IntelliJ IDEA.
  User: dd
  Date: 2017/3/2
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>登录</title>
  </head>
  <body>

  <%-- 提示信息 --%>
  <%
      String hint = (String) session.getAttribute("hint");
      String color = (String) session.getAttribute("color");
      if (hint != null) {
  %>
  <div style="color: <%=color%>"><%=hint%> test </div>
  <%
          session.removeAttribute("hint");
      }
  %>

  <div style="font-size: 26px">请输入用户信息：</div>
  <form action="Login">
      <table>
          <tr>
              <td>用户名：</td>
              <td><input type="text" name="username"/></td>
          </tr>
          <tr>
              <td>密码：</td>
              <td><input type="text" name="password"/></td>
          </tr>
          <tr>
              <td>
                  <input type="submit" value="登录"/>
                  <input type="reset" value="重置"/>
              </td>
          </tr>
      </table>
  </form>
  <br/>
  <a href="register.jsp">还没有账号？点击此处注册。</a>
  </body>
</html>

