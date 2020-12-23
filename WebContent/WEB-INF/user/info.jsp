<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>账户查询</title>
<jsp:include page="../inc/css.jsp"></jsp:include>

</head>
<body>
	<div class="container">
<jsp:include page="../inc/header.jsp"></jsp:include>


<div class="d-flex justify-content-center" >

	<ul class="list-group list-group-flush">
	<li class="list-group-item">用户名: ${user.username }</li>
	  <li class="list-group-item">用户手机: ${user.phone }</li>
	  <li class="list-group-item">用户地址: ${user.address }</li>
	  
	</ul>
	
</div>


</div>
</body>
</html>