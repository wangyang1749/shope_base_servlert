<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户登录</title>
<jsp:include page="../inc/css.jsp"></jsp:include>

</head>
<body>
	<div class="container">
<jsp:include page="../inc/header.jsp"></jsp:include>
	<div class="d-flex justify-content-center" >
	
	<form action="<%=request.getContextPath() %>/user?method=login" method="POST">
	<h2>用户登录</h2>
	  <div class="form-group">
	    <label >用户名</label>
	    <input type="text" class="form-control"  name="username"/>
	  </div>
	   <div class="form-group">
	    <label>用户密码</label>
	    <input type="password" class="form-control" name="password"/>
	  </div>
		<input type="submit" value="登录"  class="btn btn-primary"/>
			${error }
	</form>

	</div>
	</div>
</body>
</html>