<%
/* 
@Project : WIC
@File name : MemberManagemnetMain.jsp
@Date : 2020.11.12
@Author : 문지연
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,700,900"> 
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/mediaelementplayer.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="resource/style/bootstrap.min.css">
<link rel="stylesheet" href="resource/style/MemberManagementMain-style.css">
<title>Login and Register</title>

</head>

<body>
	<header>
		<%@ include file="/WEB-INF/views/common/Top.jsp" %>  
	</header>
	
	<div class="site-blocks-cover overlay inner-page-cover w-100"
			style="background-image: url(resource/image/img_001.jpg);" data-aos="fade"
			data-stellar-background-ratio="0.5">
			<div class="container" style="width:100%">
			<div
				class="row align-items-center justify-content-center text-center">
				<div class="col-md-6" data-aos="fade-up" data-aos-delay="400">
						<h1 class="text-white">Management</h1>
						<a href="Main.jsp">Home</a><span class="mx-2 text-white">&bullet;</span>
					<span class="text-white">Management</span>
				</div>
			</div>
		</div>
	</div>
	
	
	<!-- Contents -->
		<div class="site-section">
			<div class="container" data-aos="fade-up">
				<div class="row justify-content-center">

					<div>
						<h3 class="mb-5 my-4 text-center">회원 관리</h3>
					</div>						
					<div class="col-md-12 mb-5">						
						
					    <table id="example" class="display table text-center" style="width:100%;">
					        <thead class="myHead">
					            <tr>	
					            	<th>PROFILE_PIC</th>
					            	<th>CLOSET_NUM</th>
									<th>ID</th>
									<th>NAME</th>
									<th>ADDRESS</th>
									<th>EDIT</th>
									<th>DELETE</th>
					            </tr>
					        </thead>
					        <tbody>
								<c:forEach var="list" items="${requestScope.memberList}">
									<tr id="tbodyTr">
										<td><img src="upload/${list.profile_pic}" style="width: 30px; height:30px;"></td>
										<td>${list.closet_num}</td>
										<td>${list.id}</td>
										<td>${list.name}</td>
										<td>${list.addr}</td>
										<td >
											<button type="button" class="btn btn-indigo btn-sm m-0"
												onclick="location.href='<%= request.getContextPath()%>/manageMemberEditPage.Mg?id=${list.id}&${list.profile_pic}'">EDIT</button>
										</td>
										<td >
											<button type="button" class="btn btn-indigo btn-sm m-0"
											onclick="location.href='<%= request.getContextPath()%>/manageMemberDelete.Mg?id=${list.id}&closet_num=${list.closet_num}'">DELETE</button>
										</td>
									</tr>
								</c:forEach>
					        </tbody>
					    </table>
					
						
						
						<br>
			<!-- MemberSearch -->
						<!-- <div class="row">	
							<div class="my-auto col-md-3">
								<input type="search" class="form-control" id="search" name="search" placeholder="Search">
							</div>
						</div>
						<br>
						<br>
						
						
						
						<table id="result-table" class="display table text-center" style="width:100%;">
					        <tr class="text-center">
					            	<th>PROFILE_PIC</th>
									<th>CLOSET_NUM</th>
									<th>ID</th>
									<th>PWD</th>
									<th>NAME</th>
									<th>ADDRESS</th>
									<th>DEPTNO</th>
					        </tr>
					        <tbody id="tbody">
					        </tbody>
				      	</table>
						 -->
					</div>
				</div>
			</div>
		</div>
	
<script src="resource/javascript/MemberManagement.js"></script>
<script src="resource/javascript/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.js"></script>
</body>

</html>