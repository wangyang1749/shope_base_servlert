<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 商品添加</title>
<jsp:include page="../inc/css.jsp"></jsp:include>

</head>
<body>
<div class="container">
<jsp:include page="../inc/header.jsp"></jsp:include>

	<form action="<%=request.getContextPath() %>/goods.admin?method=add" method="POST" enctype="multipart/form-data"  >
		<h2>商品添加</h2>
		<div class="form-group">
		    <label >商品图片</label>
		    <input type="file" class="form-control"  name="img"/>
		 </div>
		 
		 <div class="form-group">
		    <label >商品名称</label>
		    	<input  class="form-control"  type="text" name="name"/>
		 </div>
		 
		 <div class="form-group">
		    <label >商品价格</label>
		    <input type="text" name="price"  class="form-control"/>
		 </div>
		 
	 	 <div class="form-group">
		    <label >商品数量</label>
		    <input type="text" name="count" class="form-control"/>
		 </div>
		 
	 	 <div class="form-group">
		    <label >商品描述</label>
		    <input type="text" name="description" class="form-control"/>
		 </div>
		 

	 
		<input type="submit" value="添加商品"  class="btn btn-primary"/>
	</form>
</div>
</body>
</html>