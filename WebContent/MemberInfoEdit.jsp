<%
/* 
@Project : WIC
@File name : MemberInfoEdit.jsp
@Date : 2020.11.12
@Author : 문지연
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<link rel="stylesheet" href="resource/style/memberEditPage-style.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Login and Register</title>

</head>

<body>
	<header>
		<%@ include file="/WEB-INF/views/common/Top.jsp" %>  
	</header>

	<c:set var="list" value="${requestScope.memberDto}" />
	<div class="container">
	<form form action="<%= request.getContextPath()%>/manageMemberEdit.Mg" method="post" enctype="multipart/form-data" id="form" class="form">
		<div class="form-control">
			<label for="id">ID</label>
			<input type="email" id="id" class="id" name="id" value="${list.id}" readonly />
		</div>
		<div class="form-control">
			<label for="pwd">PASSWORD</label>
			<input type="text" id="pwd" class="pwd" name="pwd" value="${list.pwd}" readonly />
		</div>
		<div class="form-control">
			<label for="name">NAME</label>
			<input type="text" id="name" name="name" value="${list.name}" />
		</div>
		<div class="form-control">
			<label for="addr">ADDRESS</label>
			<input type="text" id="addr" name="addr" value="${list.addr}" />
		</div>
		<div class="form-control">
			<label for="img">UPLOAD IMAGE</label>
			<br>
			<img id="preview" class="preview" src="upload/${list.profile_pic}" alt="Fail to load a profile_pic">
			<input type="file" id="profile_pic" name="profile_pic" class="filename" accept="image/*">
		</div>
		
		<div style="width:100%;">
		<input type="submit" class="btn-edit" value="EDIT">
		<input type="button" class="btn-cancel" value="CANCEL"
		onclick="location.href='<%= request.getContextPath()%>/managePage.Mg'">
		</div>
		
	</form>
</div>

</body>

</html>