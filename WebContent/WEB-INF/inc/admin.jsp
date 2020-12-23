<%@page import="com.shop.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <ul class="nav justify-content-center ">
  <li class="nav-item">
	<a class="nav-link" href="<%=request.getContextPath() %>/goods.admin?method=page">商品管理</a>
 </li>
   <li class="nav-item">
	<a class="nav-link" href="<%=request.getContextPath() %>/goods.admin?method=addInput">商品添加</a>
 </li>
 </ul>
 
