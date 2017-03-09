<%--
  Created by IntelliJ IDEA.
  User: dd
  Date: 2017/3/6
  Time: 9:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加运动记录</title>
</head>
<body>
<div style="font-size: 26px">请输入记录信息：</div>
<br/>
<form action="record">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username"/></td>
        </tr>
        <tr>
            <td>距离：</td>
            <td><input type="text" name="distance"/></td>
        </tr>
        <tr>
            <td>运动时长：</td>
            <td><input type="number" name="time"/></td>
        </tr>
        <tr>
            <td>速度：</td>
            <td><input type="text" name="speed"/></td>
        </tr>
        <tr>
            <td>开始时间：</td>
            <td><input type="datetime" name="startTime"/></td>
        </tr>
        <tr>
            <td>停止时间：</td>
            <td><input type="datetime" name="stopTime"/></td>
        </tr>
        <tr>
            <td>步数：</td>
            <td><input type="number" name="stepCount"/></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="提交"/>
                <input type="reset" value="重置"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
