<%
/* 
@Project : WIC
@File name : MemberEdit.jsp
@Date : 2020.11.13
@Author : 박채연
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
	<!-- 객체 호출 -->
	<c:set var="member" value="${requestScope.member}" />
	<div class="container">
	<!-- 어디로보낼지?  -->
	<form form action="<%= request.getContextPath()%>/myInfoEdit.my" method="post" enctype="multipart/form-data" id="form" class="form">
		<div class="form-control">
			<label for="id">ID</label>
			<input type="email" id="id" class="id" name="id" value="${id}" readonly/>
		</div>
		<div class="form-control">
			<label for="pwd">PASSWORD</label>
			<input type="text" id="pwd" class="pwd" name="pwd" value="${member.pwd}" />
		</div>
		<div class="form-control">
			<label for="name">NAME</label>
			<input type="text" id="name" name="name" value="${member.name}" />
		</div>
		<div class="form-control">
			<label for="addr">ADDRESS</label>
			<input type="text" id="addr" name="addr" value="${member.addr}" />
		</div>
		<div class="form-control">
			<label for="img">UPLOAD IMAGE</label>
			<br>
			<img id="preview" class="preview" src="upload/${member.profile_pic}" alt="Fail to load a profile_pic">
			<input type="file" id="filename" name="filename" class="filename" accept="image/*">
		</div>
		
		<div style="width:100%;">
		<input type="button" class="btn-cancel" value="CANCEL"
		onclick="location.href='<%= request.getContextPath()%>/myPage.my'">
		<input type="submit" class="btn-edit" value="EDIT"
		onclick="location.href='<%= request.getContextPath()%>/myInfoEdit.my'">
		</div>
		
	</form>
</div>

</body>

</html>