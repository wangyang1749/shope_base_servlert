<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>用户更新</title>
	<jsp:include page="../inc/css.jsp"></jsp:include>
</head>

<body>
	<div class="container">
		<jsp:include page="../inc/header.jsp"></jsp:include>
		<div class="d-flex justify-content-center">
			<form action="<%=request.getContextPath() %>/user.admin?method=update" method="POST">
					<input type="text" class="form-control" name="id" value="${user.id}" />
				<h2>用户注册</h2>
				<div class="form-group">
					<label>用户名</label>
					<input type="text" class="form-control" name="username" value="${user.username}" />
				</div>
				<div class="form-group">
					<label>用户密码</label>
					<input class="form-control" type="password" name="password"  value="${user.password}"/>
				</div>
				<div class="form-group">
					<label>联系电话</label>
					<input type="text" name="phone" class="form-control" value="${user.phone}"/>
				</div>
				<div class="form-group">
					<label>地址</label>
					<input type="text" name="address" class="form-control"value="${user.address}" />
				</div>

				<div class="form-group">
						<label>类型</label>
						<input type="text" name="type" class="form-control"value="${user.type}" />
					</div>

				<input type="submit" value="更新" class="btn btn-primary" />
			</form>
		</div>
	</div>
</body>

</html>