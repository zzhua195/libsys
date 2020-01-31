<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/1/30
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/register/register" method="post" id="form01">
    <h3 align="center">用户注册</h3>
    <div align="center" style="color: red;">${errorMsg}</div>
    <table align="center" width="50%" height="50%" border="1px" cellspacing="0">
        <tr>
            <td>用户名</td>
            <td><input type="text" placeholder="输入一个用户名" name="username" value="${username}" id="username"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" placeholder="输入密码" name="password" id="password"></td>
        </tr>
        <tr>
            <td>确认密码</td>
            <td><input type="password" placeholder="请再次确认密码" id="password2"></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="button" value="返回" onclick="window.history.back(-1)">
                <input type="submit" value="提交">
            </td>
        </tr>
    </table>
    <div align="center">${successMsg}</div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.min.js"></script>
<script>
    $("#form01").submit(function (e) {
        var password = $("#password").val();
        var password2 = $("#password2").val();
        if(password==password2){
            return true;
        }else{
            window.alert("密码输入不一致");
            $("#password").val("");
            $("#password2").val("");
            $("#password").focus();
            return false;
        }

    });
</script>

</body>
</html>
