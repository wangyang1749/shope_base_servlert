<%@page import="com.shop.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
User user = (User)session.getAttribute("user");
%>
 <ul class="nav justify-content-center mt-2">
<c:choose>
   <c:when test="${empty user}"> 
  
	  <li class="nav-item">
   		<a class="nav-link" href="<%=request.getContextPath() %>/user">欢迎页</a>
	  </li>
	  <li class="nav-item">
	      <a class="nav-link" href="<%=request.getContextPath() %>/user?method=loginInput">用户登录</a>
	  
	  </li>
	   <li class="nav-item">
	   		<a class="nav-link" href="<%=request.getContextPath() %>/user?method=registerInput">用户注册</a>
	  </li>

   </c:when>
   <c:otherwise>
     <li class="nav-item">
	     <a class="nav-link" href="<%=request.getContextPath() %>/user">首页</a>
	  </li>
	  <li class="nav-item">
	     <a class="nav-link" href="<%=request.getContextPath() %>/user.do?method=info">我的信息</a>
	  </li>
	
	  <li class="nav-item">
	     <a class="nav-link" href="<%=request.getContextPath() %>/user.do?method=changePasswordInput">更改密码</a>
	  </li>

	 	 <c:choose>
		   <c:when test="${user.type eq 1}"> 
		    <li class="nav-item">
	 		<a class="nav-link" href="<%=request.getContextPath() %>/goods.admin?method=page">商品管理</a>
	 		 </li>
	 		  <li class="nav-item">
				<a class="nav-link" href="<%=request.getContextPath() %>/goods.admin?method=addInput">商品添加</a>
			 </li>
			   <li class="nav-item">
	 		<a class="nav-link" href="<%=request.getContextPath() %>/user.admin?method=page">用户管理</a>
	 		 </li>
		      <li class="nav-item">
				<span class="nav-link">欢迎管理员[${user.username}]登录本系统</span>
			  </li>
			
		   </c:when>
		   <c:otherwise>
		     <li class="nav-item">
				<span class="nav-link">欢迎[${user.username}]登录本系统</span>
			  </li>
			
	      </c:otherwise>
		</c:choose>
		  <li class="nav-item">
	 		<a class="nav-link" href="<%=request.getContextPath() %>/user.do?method=logout">退出</a>
	  </li>
   </c:otherwise>
  
</c:choose>

</ul>

<hr>





