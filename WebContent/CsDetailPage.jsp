<%
/* 
@Project : WIC
@File name : CsDetailPage.jsp
@Date : 2020.11.07
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
				<h3>Detail</h3>
			</div>
		</div>
	</div>
	<!-- Container End -->
</section>
<!-- page title -->
	<c:set var="dto" value="${requestScope.dto}"/>
	<c:set var="currentPage" value="${requestScope.currentPage}"/>
	<c:set var="pageSize" value="${requestScope.pageSize}"/>
	<c:set var="sessionId" value="${requestScope.sessionId}"/>
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
                    <form action="#">
                        <fieldset class="p-4">
							<div class="form-group">
							<div class="row">
                                    <div class="col-lg-6 pt-2">
                                        NUMBER <input type="text" value="${dto.cs_num}" class="form-control" readonly>
                                    </div>
                                    <div class="col-lg-6 py-2">
                                        WriteDate <input type="text" value="${dto.cs_date}" class="form-control" readonly>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-6 pt-2">
                                        ID <input type="email" value="${dto.id }" class="form-control" readonly>
                                    </div>
                                    <div class="col-lg-6 py-2">
                                      NAME <input type="text" value="${dto.name }" class="form-control" readonly>
                                    </div>
                              </div>
                                <div class="row"></div>
                            </div>   
                              SUBJECT
                            <input type="text" value="${dto.cs_title }" class="form-control" readonly>
                            <textarea name="message" placeholder="Message *" class="border w-100 p-3 mt-3 mt-lg-4" readonly>"${dto.cs_content}"</textarea>
                            <div class="btn-grounp">
	                              <a href="<%=request.getContextPath()%>/csPage.cs?currentPage=${currentPage}&pageSize=${pageSize}">
	                              	<input type="button" class="btn btn-primary mt-2" value="뒤로">
	                         	  </a>
								 
								 <c:if test="${dto.id != 'admin@admin.com'}">
	                              <a href="<%=request.getContextPath()%>/csReWritePage.cs?cs_num=${dto.cs_num}&currentPage=${currentPage}&pageSize=${pageSize}">
	                                <button type="button" class="btn btn-primary mt-2 float-right">답글</button>
	                              </a> 
	                             </c:if>
                             
	                             <c:if test="${sessionId == dto.id || sessionId eq 'admin@admin.com'}">
	                             	<a href="./contact-us-write.html"> 
	                                	<button type="button"  class="btn btn-primary mt-2 ">삭제</button>
	                              	</a>
	                              	<a href="<%=request.getContextPath()%>/csEditPage.cs?cs_num=${dto.cs_num}&currentPage=${currentPage}&pageSize=${pageSize}">
										<button type="button" class="btn btn-primary mt-2 float-right">수정</button>
								  	</a>
								</c:if>
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