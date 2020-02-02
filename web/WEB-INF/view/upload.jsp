<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/1
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件</title>
</head>
<body>
    <form target="target" action="/file/upload" method="post" id="form01" enctype="multipart/form-data">
        选择文件:<input type="file" name="mf" id="mf">
        <input type="button" value="上传" id="btn01">
    </form>
    <br/>
    <input type="button" value="返回" onclick="goBack()">
    <iframe id="target" name="target" style="display: none"></iframe>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.min.js"></script>
<script>
    $("#btn01").click(function () {
        $("#form01").submit();
        $("#target").on('load',function () {
            var text = $(this).contents().find("body").text();      //获取到的是json的字符串
            var resp = $.parseJSON(text);
            if(resp.flag){
                alert("上传成功");
                $("#mf").val('');
            }else{
                alert("上传失败，请重试");
            }
        });
    });
    
    function goBack() {
        window.location.href = '${pageContext.request.contextPath}/file/toFile';
    }
    
</script>
</html>
