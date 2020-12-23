<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户注册</title>
<jsp:include page="../inc/css.jsp"></jsp:include>

</head>
<body>
	<div class="container">
<jsp:include page="../inc/header.jsp"></jsp:include>
	<div class="d-flex justify-content-center" >
	
	<form action="<%=request.getContextPath() %>/user?method=register" method="POST">
	<h2>用户注册</h2>
	<div class="form-group">
	    <label >用户名</label>
	    <input type="text" class="form-control"  name="username"/>
	 </div>
	 <div class="form-group">
	    <label >用户密码</label>
	    	<input  class="form-control"  type="password" name="password"/>
	 </div>
	 <div class="form-group">
	    <label >联系电话</label>
	    <input type="text" name="phone"  class="form-control"/>
	 </div>
 	 <div class="form-group">
	    <label >地址</label>
	    <input type="text" name="address" class="form-control"/>
	 </div>
	 
		<input type="submit" value="注册"  class="btn btn-primary"/>
	</form>
	</div>
	</div>
</body>
</html>