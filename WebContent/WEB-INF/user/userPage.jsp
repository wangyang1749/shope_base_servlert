<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
        <meta charset="UTF-8">
        <title>用户列表</title>
        <jsp:include page="../inc/css.jsp"></jsp:include>
</head>

<body>
        <div class="container">
                <jsp:include page="../inc/header.jsp"></jsp:include>

                <table class="table">
                        <thead>
                                <tr>
                                        <th>ID</th>
                                        <th>用户名</th>
                                        <th>用户手机</th>
                                        <th>用户地址</th>
                                        <th>用户类型</th>
                           				 <th>操作</th>
                                </tr>
                        </thead>
                        <c:forEach items="${page.list}" var="item">
                                <tr>
                                        <td>${item.id}</td>
                                        <td>${item.username}</td>
                                        <td>${item.phone}</td>
                                        <td>${item.address}</td>
                                        <td>${item.type}</td>
                                      
                                        <td>
                                              
                                                <a
                                                        href="<%=request.getContextPath() %>/user.admin?method=delete&id=${item.id}">删除</a>
                                                <a
                                                        href="<%=request.getContextPath() %>/user.admin?method=updateInput&id=${item.id}">修改</a>
                                        </td>
                                </tr>
                        </c:forEach>
                </table>

                <nav aria-label="Page navigation example">
                        <ul class="pagination">
                                <li class="page-item"><a class="page-link" href="<%=request.getContextPath() %>/user.admin?method=page&page=1">Previous</a></li>
                                <c:forEach begin="1" end="${page.pageSize}" var="v">
                                               
                                <li class="page-item"><a class="page-link" href="<%=request.getContextPath() %>/user.admin?method=page&page=${v}">${v}</a></li>
                                </c:forEach>
   
                                <li class="page-item"><a class="page-link" href="<%=request.getContextPath() %>/user.admin?method=page&page=${page.pageSize}">Next</a></li>
                        </ul>
                </nav>

        </div>

</body>

</html>