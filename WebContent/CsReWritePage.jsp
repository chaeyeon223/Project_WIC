<%
/* 
@Project : WIC
@File name : CsWritePage.jsp
@Date : 2020.11.12
@Author : 정민찬
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
    
    <!-- Bootstrap -->
    <link rel="stylesheet" href="resource/style/contact-us/bootstrap.min.css">  
    
    <!-- Font Awesome -->
    <link href="" rel="stylesheet"> 
    
    <!-- CUSTOM CSS -->
    <link href="resource/style/contact-us/style.css" rel="stylesheet">
    <link href="resource/style/top-bottom/top-style.css" rel="stylesheet">
</head>
<body>


	<!-- Header-->
		<jsp:include page="WEB-INF/views/common/Top.jsp"></jsp:include>
	<!-- /Header -->
    <section class="page-title">
	<!-- Container Start -->
	<div class="container">
		<div class="row">
			<div class="col-md-8 offset-md-2 text-center">
				<!-- Title text -->
				<h3>Rewrite</h3>
			</div>
		</div>
	</div>
	<!-- Container End -->
</section>
<!-- page title -->
	<c:set var="csDto" value="${requestScope.csDto}"/>
	<c:set var="memberDto" value="${requestScope.memberDto}"/>
	<c:set var="currentPage" value="${requestScope.currentPage}"/>
	<c:set var="pageSize" value="${requestScope.pageSize}"/>
<!-- contact us start-->
<section class="section">
  <div class="container">
      <div class="row">
        <div class="contact-us-content p-4">
          <h1 class="pt-1">Hello, what's on your mind?</h1>
          <p class="pt-1">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla elit dolor, blandit vel euismod ac, lentesque et dolor. Ut id tempus ipsum.</p>
        </div>
      </div>
      <div class="row">
          <div class="col-md-12">
                  <form action="<%=request.getContextPath()%>/csReWrite.cs?currentPage=${currentPage}&pageSize=${pageSize}" method="post" >
                  	  <input type="hidden" type="text" name="cs_num" value="${csDto.cs_num}">
                  	  <input type="hidden" type="text" name="cs_refer" value="${csDto.cs_reffer}">
                  	  <input type="hidden" type="text" name="cs_depth" value="${csDto.cs_depth}">
                  	  <input type="hidden" type="text" name="cs_step" value="${csDto.cs_step}">
                  	  
                      <fieldset class="p-4">
                          <div class="form-group">

								<div class="row">
									<div class="col-md-10"></div>
									<div class="col-md-2">
											<select name="cs_secret" class="form-control">
												<option value="0" selected>공개글</option>
												<option value="1">비밀글</option>
											</select>
									</div>

								</div>
								<div class="row">
                                  <div class="col-lg-6 pt-2">
                                    ID <input type="email" value="${memberDto.id}" class="form-control" readonly name="id">
                                  </div>
                                  <div class="col-lg-6 py-2">
                                    NAME <input type="text" value="${memberDto.name}" class="form-control" readonly name="name">
                                  </div>
                                </div>    
                          </div>   
                            SUBJECT
                            <input type="text" placeholder="subject *" class="form-control" value="[RE]${csDto.cs_title}" required name="title">
                            <br>
                          <textarea  placeholder="Message *" class="border w-100 p-3 mt-3 mt-lg-4" required name="content"></textarea>
                          
                          <div class="btn-grounp">
                              <button type="summit" class="btn btn-primary mt-2 float-right">작성하기</button>
                              <a href="<%=request.getContextPath()%>/csDetailPage.cs?currentPage=${currentPage}&pageSize=${pageSize}&cs_num=${csDto.cs_num}">
                              	<input type="button" class="btn btn-primary mt-2" value="뒤로가기">
                         	  </a>
                          </div>
                      </fieldset>
                  </form>
          </div>
      </div>
  </div>
</section>
	<!-- bottom-->
		<jsp:include page="WEB-INF/views/common/Bottom.jsp"></jsp:include>
	<!-- /bottom -->
</body>
</html>