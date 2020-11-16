<%
/* 
@Project : WIC
@File name : loginRegister.jsp
@Date : 2020.11.09
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/18cfffd9e0.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="resource/style/loginRegister-style.css" />
<title>Login and Register</title>

</head>

<body>
	<header>
		<%@ include file="/WEB-INF/views/common/Top.jsp" %>  
	</header>


	<div class="container" id="container">
		<div id="sign-up-container" class="form-container sign-up-container">
			<form action="<%= request.getContextPath()%>/signUp.my" method="post" enctype="multipart/form-data">
			<div>
				 <div class="field">
                        <input onkeyup="checkEmail()" type="email" id="id" name="id" autocomplete="off" placeholder="email@email.com" />
                        <input type="button" id="check_btn" class="check_btn" name="check_btn" value="Check" />		
                    <div class="check-icons">
                        <span class="icon1 fas fa-exclamation"></span>
                        <span class="icon2 fas fa-check"></span>
                    </div>
                    </div>
                    <div class="error-text">
                        Id should be valid Email address
                    </div>
                <div class="field">
                <input onkeyup="checkPwd()" type="password" id="pwd" name="pwd" autocomplete="off" placeholder="Password" />
                <div class="check-icons">
                    <span class="icon3 fas fa-exclamation"></span>
                    <span class="icon4 fas fa-check"></span>
                </div>
                </div>
                <div class="error-text2">
                    Password should be 4 to 12 digits of letters and numbers
                </div>
            <input type="text" id="name" name="name" placeholder="UserName" />
            <div style="width: 100%;">
					<input type="text" id="postcode" name="postcode" placeholder="postcode"> 
					<input type="button" id="postcode_btn" name="postcode_btn" onclick="findAddress()"
						value="postcode" />
			</div>
				<input type="text" id="addr" name="addr" placeholder="address"> 
				<input type="text" id="detailAddr" name="detailAddr" placeholder="detail Address"> 
				<input type="file" id="filename" name="filename" accept="image/*">
				<input type="submit" id="signup_btn" name="signup_btn" value="Sign Up">
			</div>
			</form>
		</div>
		<div class="form-container sign-in-container">
			<form action="<%= request.getContextPath()%>/signIn.my" method="post">
				<h1>Sign in</h1>

				<input type="email" id="userid" name="id" placeholder="email@email.com" /> 
				<input type="password" id="userpwd" name="pwd" placeholder="Password" />

				<input type="submit" id="signin_btn" name="signin_btn" value="Sign In">
			</form>
		</div>
		<div class="overlay-container">
			<div class="overlay">
				<div class="overlay-panel overlay-left">
					<h1>Create your Closet!</h1>
					<p>To share your unique style with others</p>
					<button class="ghost" id="signIn">Sign In</button>
				</div>
				<div class="overlay-panel overlay-right">
					<h1>Hello, Friend!</h1>
					<p>
						Enter your personal details <br> and show us your style
					</p>
					<button class="ghost" id="signUp">Sign Up</button>
				</div>
			</div>
	
			
			</div>
			
		</div>

		<!-- Bottom -->
		
		<%@ include file="/WEB-INF/views/common/Bottom.jsp" %>  
		
		
	
		
		
</body>
<script src="resource/javascript/loginRegister.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</html>