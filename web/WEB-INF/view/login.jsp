<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/1/28
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
</head>
<body>
<h1 align="center">图书登录系统</h1>
    <div align="center" style="color: red;">${errorMsg}</div>
    <table align="center" border="1" cellspacing="0" cellpadding="0" style="width:50%;height: 50%;">

        <tbody>
            <tr>
                <td align="center">登录名</td>
                <td><input type="text" name="username" id="name" style="border: 0;height: auto" value="${username}" width="auto"></td>
            </tr>
            <tr>
                <td align="center">密码</td>
                <td><input type="text" name="username" id="pwd" style="border: 0;height: auto;" width="auto"></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="button" value="登陆" onclick="login()">
                    <input type="button" value="注册" onclick="register()">
                </td>
            </tr>
        </tbody>
    </table>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.min.js"></script>
<script>
    function login() {
        var username = $("#name").val();
        var password = $("#pwd").val();

        window.location.href = '/login/login?username='+username+'&password='+password;

    }

    function register(){
        window.location.href = '/register/toRegister';
    }
</script>
</html>
