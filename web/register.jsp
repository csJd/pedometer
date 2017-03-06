<%--
  Created by IntelliJ IDEA.
  User: dd
  Date: 2017/3/2
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
</head>
<body>

<div style="font-size: 26px">请输入注册信息：</div>
<br/>
<form action="Register">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username"/></td>
        </tr>
        <tr>
            <td>账号：</td>
            <td><input type="text" name="account"/></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="text" name="password"/></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="注册"/>
                <input type="reset" value="重置"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
