<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/1/29
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>图书列表</title>
</head>
<body>
<h3 align="center">图书馆藏</h3>
<div align="center">${user.username},欢迎回来! <a href="/logout">注销</a> </div>
<div align="center">
    <form action="${pageContext.request.contextPath}/book/list" id="form01">
        书名: <input type="text" name="bookname" id="bookname" value="${bookVo.bookname}">
        分类: <select name="type">
                <option value="">-请选择-</option>
                <option value="1" <c:if test="${bookVo.type==1}">selected</c:if>>java</option>
                <option value="2" <c:if test="${bookVo.type==2}">selected</c:if>>blender</option>
                <option value="3" <c:if test="${bookVo.type==3}">selected</c:if>>其它</option>
              </select>
        状态: <input type="radio" name="isborrow" value="0" <c:if test="${bookVo.isborrow==0}">checked</c:if>>未借
              <input type="radio" name="isborrow" value="1" <c:if test="${bookVo.isborrow==1}">checked</c:if>>已借出
        <br/>

        时间区间: <input type="date" name="starttime"
                     value="<fmt:formatDate value="${bookVo.starttime}" pattern="yyyy-MM-dd"/>">
                  <input type="date" name="endtime"
                     value="<fmt:formatDate value="${bookVo.endtime}" pattern="yyyy-MM-dd"/>">
        <br/>
        <input type="submit" value="查询"/>
        <input onclick="clearContent()" type="button" value="重置"/>
    </form>
</div>

    <table border="1px" cellspacing="0" cellpadding="0" align="center" style="width: 50%;height: 50%;">
        <tr>
            <td align="center">序号</td>
            <td align="center">书名</td>
            <td align="center">作者</td>
            <td align="center">类别</td>
            <td align="center">状态</td>
            <td align="center">价格</td>
            <td align="center">出版时间</td>
        </tr>
        <c:choose>
            <c:when test="${not empty bookList}">
                <c:forEach var="book" items="${bookList}" varStatus="vs" >
                    <tr>
                        <td align="center">${vs.index+1+pageBean.pageSize*(pageBean.currPage-1)}</td>
                        <td align="center">${book.bookname}</td>
                        <td align="center">${book.author}</td>
                        <td align="center">
                            <c:choose>
                                <c:when test="${book.type==1}">java</c:when>
                                <c:when test="${book.type==2}">blender</c:when>
                                <c:otherwise>
                                    其它
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td align="center">
                            <c:if test="${book.isborrow==0}">
                                <a href='javascript:void(0)' onclick='borrowBook(${book.id});'>可借</a>
                            </c:if>
                            <c:if test="${book.isborrow==1}">
                                已借出&nbsp;&nbsp;
                                <a href='javascript:void(0)' onclick='returnBook(${book.id});'>退还</a>
                            </c:if>
                        </td>
                        <td align="center"><fmt:formatNumber type="number" value="${book.price}" pattern="#.00"/></td>
                        <td align="center"><fmt:formatDate value="${book.publishtime}" pattern="yyyy-MM-dd"/></td>
                    </tr>

                </c:forEach>
                <tr align="right">
                    <td colspan="7">
                        <c:choose>
                            <c:when test="${pageBean.currPage==1}">
                                <a style="pointer-events:none;color: #393939;" disabled="disabled">上一页</a>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:void(0)" onclick="previous(${pageBean.currPage})" >上一页</a>
                            </c:otherwise>
                        </c:choose>


                        <c:forEach begin="1" end="${pageBean.totalPage}" step="1" var="value">
                            <a href="javascript:void(0)" onclick="clickPage(${value})" style="<c:if test='${pageBean.currPage!=value}'>text-decoration:none;</c:if>" >${value}</a>
                        </c:forEach>

                        <c:choose>
                            <c:when test="${pageBean.currPage==pageBean.totalPage}">
                                <a style="pointer-events:none;color: #393939;" href="/book/list?currPage=${pageBean.currPage+1}">下一页</a>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:void(0)" onclick="next(${pageBean.currPage})">下一页</a>
                            </c:otherwise>
                        </c:choose>
                        共${pageBean.totalPage}页
                        跳转到:
                        <select id="select01">
                            <c:forEach begin="1" end="${pageBean.totalPage}" step="1" var="value">
                                <option <c:if test="${pageBean.currPage==value}">selected</c:if>>${value} </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="7" align="center">没有找到相关内容</td>
                </tr>
            </c:otherwise>
        </c:choose>



    </table>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.min.js"></script>
<script>
    $("#select01").change(function () {
        var formData = $("#form01").serialize();
        window.location.href = "${pageContext.request.contextPath}/book/list?currPage="+$("#select01").val()+'&'+formData;
    });

    
    function previous(currPage) {
        var formData = $("#form01").serialize();
        window.location.href = '/book/list?currPage='+(currPage-1)+'&'+formData;
    }

    function next(currPage) {
        var formData = $("#form01").serialize();
        window.location.href = '/book/list?currPage='+(currPage+1)+'&'+formData;
    }
    
    function clearContent() {
        $("#form01").find('input[type=text],select,input[type=date],input[type=hidden]').each(function() {
            $(this).val('');
        });
        $('input[type=radio][name="isborrow"]:checked').prop("checked", false);
    }

    function clickPage(page) {
        var formData = $("#form01").serialize();
        window.location.href = '/book/list?currPage='+page+'&'+formData;
    }
    
    function borrowBook(bookid) {
        var formData = $("#form01").serialize();
        window.location.href = '/book/borrowBook?id='+bookid+'&isborrow='+1+'&'+formData;
    }
    
    function returnBook(bookid) {
        window.location.href = '/book/borrowBook?id='+bookid+'&isborrow='+0;
    }
    
</script>
</html>
