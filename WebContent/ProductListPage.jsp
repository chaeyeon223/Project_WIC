
<%
/* 
@Project : WIC
@File name : ProjcetListPage.jsp
@Date : 2020.11.07
@Author : 박선우
*/
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Projcet WIC</title>
	<link rel="stylesheet" href="resource/style/bootstrap-grid.css">
	<link rel="stylesheet" href="resource/style/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="resource/javascript/bootstrap.bundle.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
	<script src="resource/javascript/ProductListPage.js"></script>
	<script src="https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.min.js"></script>
	<link rel="stylesheet" href="resource/style/productListPage-style.css">
	
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/Top.jsp"></jsp:include>
	<div class="site-blocks-cover overlay inner-page-cover w-100"
			style="background-image: url(resource/image/img_001.jpg);" data-aos="fade"
			data-stellar-background-ratio="0.5">
			<div class="container" style="width:100%">
			<div
				class="row align-items-center justify-content-center text-center">
				<div class="col-md-6" data-aos="fade-up" data-aos-delay="400">
						<h1 class="text-white">Product</h1>
						<a href="Main.jsp">Home</a><span class="mx-2 text-white">&bullet;</span>
					<span class="text-white">Product</span>
				</div>
			</div>
		</div>
	</div>
	<div id="wrapper" class="my-4">
	<div class="col-md-8 mx-auto my-4">
		<form action="" id="search" name="search" method="get">
			<div class="inner-form row"> 
				<div class="col-md-9">
				<input class="col-md-12" type="text" id="searchbar" name="searchbar"
					placeholder="Type KeyWord's">
				</div>
				<div class="col-md-2">
				<button type="submit" id="submit">Search</button>
				</div>
			</div>
		</form>
	</div>
	
	<!-- prdList, filesList(JSTL) / test -->
	<c:set var="prdList" value="${requestScope.productList}"/>
	<c:set var="cartList" value="${requestScope.cartList}"/>
	<div class="container">
		<div class="grid" id="grid">
		<div class="grid-sizer"></div>
			<c:forEach var="product" items="${prdList}"> 
			
				<div class="grid-item">
						<img src="upload/${product.files.files_name}">
					<div class="overlay">
						<a href="<%=request.getContextPath()%>/ProductDetailPage.Pd?prd_num=${product.prd_num}">
						<h3>${product.prd_title}</h3>
						</a>
						<p> ${product.prd_content}</p>
					<c:choose>	
					<c:when test="${empty cartList}">
							<button class="far fa-heart like" id="like" value="${product.prd_num}"></button>
					</c:when>	
					 <c:otherwise>	
					<c:forEach var="cart" items="${cartList}">
						
						<c:if test="${product.prd_num != cart.prd_num}">
							<button class="far fa-heart like" value="${product.prd_num}"></button>
						</c:if>
						<c:if test="${product.prd_num == cart.prd_num}">
							<button class="fas fa-heart like" value="${product.prd_num}"></button>

						</c:if>
						
					</c:forEach>
					</c:otherwise>
					</c:choose>
						<button onclick="location.href='<%=request.getContextPath() %>/ProductDetailPage.Pd?prd_num=${product.prd_num}'">상세보기</button>
					</div>	
				</div>
			</c:forEach>
		</div>
	</div>

	<c:set var="pageSize" value="${requestScope.pageSize}"/>			
	<c:set var="maxPageCount" value="${requestScope.maxPageCount}"/>
	<c:set var="startPage" value="${requestScope.startPage}"/>
	<c:set var="endPage" value="${requestScope.endPage}"/>
	
	<div class="row">
			<table class="table table-responsive-md text-center col-md-12">
				<tr>
					<td>
						<c:if test="${currentPage>1}">
							<a href="<%= request.getContextPath()%>/ProductListPage.Pd?currentPage=${currentPage-1}&pageSize=${pageSize}">이전</a>
						</c:if> 
						<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
							<c:choose>
								<c:when test="${currentPage==i}">${i}</c:when>
								<c:otherwise>
									<a
										href="<%= request.getContextPath()%>/ProductListPage.Pd?currentPage=${i}&pageSize=${pageSize}"><b>${i}</b></a>
								</c:otherwise>
							</c:choose>
						</c:forEach> 
						<c:if test="${currentPage<maxPageCount}">
							<a
								href="<%= request.getContextPath()%>/ProductListPage.Pd?currentPage=${currentPage+1}&pageSize=${pageSize}">다음</a>
						</c:if></td>
				</tr>
			</table>
		</div>
	
	</div>
	<jsp:include page="WEB-INF/views/common/Bottom.jsp"></jsp:include>
	

</body>
<script src="resource/javascript/masonry.pkgd.min.js"></script>

</html>