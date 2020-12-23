<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>商品详情</title>
    <jsp:include page="../inc/css.jsp"></jsp:include>
</head>

<body>
    <div class="container">
        <jsp:include page="../inc/header.jsp"></jsp:include>
        <div class="row">
            <div class="col-5 d-flex justify-content-center">
                <img src="${goods.img}" alt="" style="width: 341px;">
            </div>
            <div class="col-7">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">商品名称：${goods.name}</li>
                    <li class="list-group-item">商品价格：${goods.price}</li>
                    <li class="list-group-item">商品库存：${goods.count}</li>
                    <li class="list-group-item">商品描述：${goods.description}</li>
             
                </ul>
                <a href="" class="btn btn-primary">点击购买</a>
            </div>
        </div>
    </div>
</body>

</html>