<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/1
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>文件列表</title>
</head>
<body>

    <div align="center">
        <a href="/file/toUpload">上传文件</a>
    </div>

<table border="1px" cellspacing="0" cellpadding="0" align="center" style="width: 50%;height: 50%;">
<tr>
    <td align="center">序号</td>
    <td align="center">真实名</td>
    <td align="center">文件名</td>
    <td align="center">文件类型</td>
    <td align="center">文件大小</td>
    <td align="center">上传者</td>
    <td align="center">上传时间</td>
    <td align="center">链接</td>
</tr>
<c:choose>
    <c:when test="${not empty attachmentList}">
        <c:forEach var="attachment" items="${attachmentList}" varStatus="vs" >
            <tr>
                <td align="center">${vs.index+1}</td>
                <td align="center">${attachment.realname}</td>
                <td align="center">${attachment.filename}</td>
                <td align="center">${attachment.filetype}</td>
                <td align="center">${attachment.filesize}</td>
                <td align="center">${attachment.uploader}</td>
                <td align="center"><fmt:formatDate value="${attachment.uploadtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>
                    <a target="target" href="/file/download?id=${attachment.id}">下载</a>
                </td>
            </tr>

        </c:forEach>
    </c:when>
    <c:otherwise>
        <tr>
            <td colspan="8" align="center">暂无</td>
        </tr>
    </c:otherwise>
</c:choose>
</table>
    <iframe style="display: none;" id="target" name="target"></iframe>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.min.js"></script>
    <script>
        $("#target").on('load',function () {
            var text = $(this).contents().find("body").text();
            var json = $.parseJSON(text);
            if(!json.flag){
                alert(json.msg);
            }
        });
    </script>
</body>
</html>
