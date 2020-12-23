<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新密码</title>
<jsp:include page="../inc/css.jsp"></jsp:include>

</head>
<body>
	<div class="container">
	<jsp:include page="../inc/header.jsp"></jsp:include>
	<div class="d-flex justify-content-center" >
	<form action="<%=request.getContextPath() %>/user.do?method=changePassword" method="POST">
	<div class="form-group">
	    <label >输入原来密码</label>
	    <input type=""password"" name="beforePassword" class="form-control"/>
	  </div>
	  <div class="form-group">
	    <label >输入修改后密码</label>
	    <input type="password" name="afterPassword" class="form-control"/>
	  </div>
		
		<input type="submit" value="更改密码" class="btn btn-primary"/>
	</form>
	</div>
	</div>
</body>
</html>