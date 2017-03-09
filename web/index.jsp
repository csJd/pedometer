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

  <div style="font-size: 26px">请输入用户信息：</div>
  <form action="login">
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

  <a href="register.jsp">还没有账号？点击此处注册。</a><br/><br/>

  <a href="addRecord.jsp">点击添加运动记录。</a><br/><br/>

  查询运动记录信息:
  <form action="statistics">
      <table>
          <tr>
              <td>method：</td>
              <td><select name="method"/>
                  <option value="total">total</option>
                  <option value="year">year</option>
                  <option value="month">month</option>
                  <option value="week">week</option>
              </td>
          </tr>
          <tr>
              <td>username：</td>
              <td><input type="text" name="username"/></td>
          </tr>
          <tr>
              <td>
                  <input type="submit" value="查询"/>
                  <input type="reset" value="重置"/>
              </td>
          </tr>
      </table>
  </form>
  </body>
</html>

