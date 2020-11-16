<%
/* 
@Project : WIC
@File name : CsPage.jsp
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
<link rel="stylesheet"
	href="resource/style/contact-us/bootstrap.min.css">

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
					<h3>Contact us - MAIN</h3>
				</div>
			</div>
		</div>
	</section>
	
	
	<c:set var="currentPage" value="${requestScope.currentPage}"/>
	<!-- page title -->
	<section class="section">
		<div class="container">
			<h1 class="text-center">CONTACT-US-MAIN</h1>
			<br>
			<div class="row">
				<div class= "col-md-2"> 
				<form>
					<select name="pageSize" class="form-control" onchange="submit()">
						<c:forEach var="i" begin="5" end="20" step="5">
							<c:choose>
								<c:when test="${pageSize == i}">
									<option value="${i}" selected>${i}개</option>
								</c:when>
								<c:otherwise>
									<option value="${i}">${i}개</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</form>
				</div>
				<div class="col-md-8">
				</div>
				<div class="col-md-2">
					<form>
						<select name="showOption" class="form-control" onchange="#ID 또는 NAME">
								<option selected>전체 글보기</option>
								<option>공지사항</option>
								<option>Q&A</option>
						</select>
					</form>
				</div>
			</div>
			<br>
			<div class="row">
				<table class="table table-striped table-responsive-md ">
					<thead>
						<tr class="text-center">
							<th>글번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일자</th>
							<th>조회수</th>
						</tr>
					</thead>
						<c:forEach var="csList" items="${requestScope.csList}">
							<tr class="table-tr">
								<td class="py-auto text-center">${csList.cs_num}</td>
								<td> 
									<c:forEach var="i" begin="1" end="${csList.cs_depth}" step="1">
                        				&nbsp;&nbsp;&nbsp;
                    		 		</c:forEach>  
                    				<c:if test="${csList.cs_depth>0}">
										<img src='resource/image/re.gif'/>
									</c:if>
									<c:choose>
										<c:when test="${csList.cs_step == 0}">	                            	
		                            		<a href="<%= request.getContextPath()%>/csDetailPage.cs?cs_num=${csList.cs_num}&currentPage=${currentPage}&pageSize=${pageSize}&cs_secret=${csList.cs_secret}"><b>${csList.cs_title}<b/></a>
		                        		</c:when>
										<c:otherwise>
		                            		<a href="<%= request.getContextPath()%>/csDetailPage.cs?cs_num=${csList.cs_num}&currentPage=${currentPage}&pageSize=${pageSize}">${csList.cs_title}</a>
		                               </c:otherwise>
										</c:choose>
												<c:if test="${csList.cs_notice == 1}">
		                        			<img src='resource/image/jong2.png'/>
		                        		</c:if>
		                        		<c:if test="${csList.cs_secret == 1}">
		                        			<img src='resource/image/lock.png'/>
		                        		</c:if>
		                        		
									</td>
								<td class="text-center">${csList.name}</td>
								<td class="text-center">${csList.cs_date}</td>
								<td class="text-center">${csList.cs_count}</td>
							</tr>
						</c:forEach>
				</table>
				

				<c:set var="pageSize" value="${requestScope.pageSize}"/>			
				<c:set var="maxPageCount" value="${requestScope.maxPageCount}"/>
				<c:set var="startPage" value="${requestScope.startPage}"/>
				<c:set var="endPage" value="${requestScope.endPage}"/>
				
				</div>
				<div class="row">
					<table class="table table-responsive-md text-center col-md-12">
						<tr>
							<td>
								<c:if test="${currentPage>1}">
									<a href="<%= request.getContextPath()%>/csPage.cs?currentPage=${currentPage-1}&pageSize=${pageSize}">이전</a>
								</c:if> 
								<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
									<c:choose>
										<c:when test="${currentPage==i}">${i}</c:when>
										<c:otherwise>
											<a
												href="<%= request.getContextPath()%>/csPage.cs?currentPage=${i}&pageSize=${pageSize}"><b>${i}</b></a>
										</c:otherwise>
									</c:choose>
								</c:forEach> 
								<c:if test="${currentPage<maxPageCount}">
									<a
										href="<%= request.getContextPath()%>/csPage.cs?currentPage=${currentPage+1}&pageSize=${pageSize}">다음</a>
								</c:if></td>
						</tr>
					</table>
				</div>
						<div class="btn-grounp">
							<a href="<%=request.getContextPath()%>/csWritePage.cs?currentPage=${currentPage}&pageSize=${pageSize}">
								<input type="button" value="글쓰기" class="btn btn-primary mt-2 float-right"  >
							</a>


				</div>

				</div>
			

	</section>
	<!-- bottom-->
	<jsp:include page="WEB-INF/views/common/Bottom.jsp"></jsp:include>
	<!-- /bottom -->
</body>
<script>
	//Jquery를 통해서 $.ajax
</script>
</html>