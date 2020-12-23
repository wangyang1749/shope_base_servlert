<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
        <meta charset="UTF-8">
        <title>商品列表</title>
        <jsp:include page="inc/css.jsp"></jsp:include>
        <style>
                a {
                        text-decoration: none;
                }
        </style>
</head>

<body>
        <div class="container">
                <jsp:include page="inc/header.jsp"></jsp:include>



                <div class="row row-cols-1 row-cols-md-3">

                        <c:forEach items="${page.list}" var="item">
                                <div class="col mb-4">
                                        <a class="text-secondary"
                                                href="<%=request.getContextPath() %>/user.do?method=details&id=${item.id}">
                                                <div class="card">
                                                        <img src="${item.img}" class="card-img-top" alt="...">
                                                        <div class="card-body">
                                                                <h5 class="card-title">${item.name} | 价格：${item.price}
                                                                </h5>
                                                                <p class="card-text">${item.description}</p>
                                                        </div>
                                                        <div class="card-footer">
                                                                <small class="text-muted">${item.createTime}</small>
                                                        </div>
                                                </div>
                                        </a>

                                </div>
                        </c:forEach>

                </div>

                <nav aria-label="Page navigation example">
                        <ul class="pagination">
                                <li class="page-item"><a class="page-link"
                                                href="<%=request.getContextPath() %>/goods.admin?method=page&page=1">Previous</a>
                                </li>
                                <c:forEach begin="1" end="${page.pageSize}" var="v">

                                        <li class="page-item"><a class="page-link"
                                                        href="<%=request.getContextPath() %>/goods.admin?method=page&page=${v}">${v}</a>
                                        </li>
                                </c:forEach>

                                <li class="page-item"><a class="page-link"
                                                href="<%=request.getContextPath() %>/goods.admin?method=page&page=${page.pageSize}">Next</a>
                                </li>
                        </ul>
                </nav>
        </div>

</body>

</html>