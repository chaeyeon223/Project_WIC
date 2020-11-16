<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Product Detail</title>
	
	<!-- Bootstrap cdn -->
	<link rel="stylesheet" href="resource/style/productDetail.css"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
	<style>
	
	</style>
</head>

<body>
<!-- Top -->
<jsp:include page="WEB-INF/views/common/Top.jsp"></jsp:include>


<!-- 변수선언 -->
<c:set var="product" value="${requestScope.product}"></c:set>
<c:set var="price" value="${requestScope.price}"></c:set>
<c:set var="fileList" value="${requestScope.fileList}"></c:set>
<c:set var="member" value="${requestScope.member}"></c:set>
<c:set var="getLike" value="${requestScope.getLike}"></c:set>
<c:set var="checkLike" value="${requestScope.checkLike}"></c:set>
<c:set var="id" value="${sessionScope.id}"></c:set>

<div class="mb-5"></div>
<div class="container">
	<div class="row">
		<!-- Left -->
		<div class="col-md-6 mb-5 leftDiv">
			
			<!-- carousel slide -->
			<div id="imgCarousel" class="carousel slide" data-interval="false">
				
				<!-- data-target -->
				<c:choose>
					<c:when test="${fn:length(fileList) <= 1}"></c:when>
					<c:otherwise>
						<ol class="carousel-indicators">
						    <li data-target="imgCarousel" data-slide-to="0" class="active"></li>
						    <c:forEach var="i" begin="1" end="${fn:length(fileList) - 1}">
		    				    <li data-target="imgCarousel" data-slide-to="${i}"></li>
						    </c:forEach>
						</ol>
					</c:otherwise>
				</c:choose>
				
				<!-- carousel-inner 이미지 -->
				<div class="carousel-inner">
					<c:choose>
						<c:when test="${fn:length(fileList) eq 0}">
							<div class="carousel-item active">
								<img src="upload/xmark.png" alt="">
							</div>
						</c:when>
						<c:when test="${fn:length(fileList) eq 1}">
							<div class="carousel-item active">
								<c:forEach var="file" items="${fileList}">
									<img src="upload/${file.files_name}" alt="">
								</c:forEach>
							</div>
						</c:when>
						<c:otherwise>
							<c:forEach var="file" items="${fileList}" varStatus="status">
								<c:choose>
									<c:when test="${status.first}">
										<div class="carousel-item active">
											<img src="upload/${file.files_name}" alt="">
										</div>
									</c:when>
									<c:otherwise>
										<div class="carousel-item">
											<img src="upload/${file.files_name}" alt="">
										</div>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					
					<!-- 이전/다음페이지 아이콘(이미지가 없거나 1개이면 삭제) -->
					<c:choose>
						<c:when test="${fn:length(fileList) <= 1}"></c:when>
						<c:otherwise>
							<a class="carousel-control-prev" href="#imgCarousel" role="button" data-slide="prev">
								<span class="carousel-control-prev-icon" aria-hidden="true"></span>
								<span class="sr-only">Previous</span>
							</a>
							<a class="carousel-control-next" href="#imgCarousel" role="button" data-slide="next">
								<span class="carousel-control-next-icon" aria-hidden="true"></span>
								<span class="sr-only">Next</span>
							</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>	
		</div>
		
		<!-- Right -->
		<!-- member(profile_pic, name, addr), like_record(count수), product(prd_num, prd_title, prd_price, prd_content, files) -->
		<div class="col-md-6 rightDiv">
			<div class="mb-4">
				<div class="d-flex justify-content-between">
					<!-- 이미지 클릭 시 해당 회원 옷장으로 이동 -->
					<a class="toCloset" href="<%=request.getContextPath()%>/memberClosetPage.my?prd_num=${product.prd_num}">
						<img id="userPic" src="upload/${member.profile_pic}">
					</a>
					<div class="mr-auto">
						${member.name}<br>
						<c:choose>
							<c:when test="${checkLike eq 0}">
								<i id="heart" class="far fa-heart" aria-hidden="true"></i>
							</c:when>
							<c:otherwise>
								<i id="heart" class="fas fa-heart" aria-hidden="true"></i>
							</c:otherwise>
						</c:choose>
						<span id="cnt">${getLike}</span>
					</div>
					<button id="edit" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/ProductEditPage.Pd?prd_num=${product.prd_num}'">글수정</button>
				</div>
			</div>
			<div class="mb-3">
				<h3 id="title">${product.prd_title}</h3>
			</div>
			<div class="mb-2">
				<h5 id="price">${price}<span id="location">${member.addr}</span></h5>
			</div>
			<div class="mb-4 description">
				${product.prd_content}
			</div>
			<div class="mb-2 d-flex justify-content-between">
				<span class="prdReply">상품문의</span>
				<button id="ask" class="btn btn-primary">문의하기</button>
			</div>
			<div>
				<!-- 상품문의 테이블 -->
				<table class="table myTable">
					<thead class="thead-light">
						<tr>
							<th>No.</th>
							<th>글제목</th>
							<th>작성자</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td><a href="#">상품 문의드려요.</a></td>
							<td>문지</td>
						</tr>
						<tr>
							<td>2</td>
							<td><a href="#">에눌 가능한가요?</a></td>
							<td>재형이</td>
						</tr>
					</tbody>
				</table>
				
				<!-- Pagination -->
				<div class="">
					<nav aria-label="Page navigation">
						<ul class="pagination pagination-sm justify-content-center" style="margin-bottom: 0px;">
							<li class="page-item">
								<a class="page-link" href="#" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
							<li class="page-item"><a class="page-link" href="#">1</a></li>
							<li class="page-item"><a class="page-link" href="#">2</a></li>
							<li class="page-item"><a class="page-link" href="#">3</a></li>
							<li class="page-item">
								<a class="page-link" href="#" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="mb-5"></div>
<jsp:include page="WEB-INF/views/common/Bottom.jsp"></jsp:include>
</body>

<script>
$(document).ready(function() {
	if(!('${member.id}' == '${id}' || '${id}' == 'admin@admin.com')) {
		$("#edit").hide();
	}
	
	$("#heart").click(function(e) {
		if('${id}' != '') {	
			if($(this).hasClass('far fa-heart')){
				$.ajax(
					{
						url: "<%=request.getContextPath()%>/sendLike.Ajax",
						data:{send_id:'${id}', get_id:'${member.id}'},
						type:"post",
						dataType:"html",  
						success:function(responsedata, textStatus, xhr){
							$("#heart").attr('class', 'fas fa-heart');
							$("#cnt").html(responsedata);
						},
						error:function(xhr){
							alert(xhr.status + " : ERROR");
						}
					}	   
				);
				$(this).attr('class', 'fas fa-heart');			
			} else {
				$.ajax(
					{
						url: "<%=request.getContextPath()%>/deleteLike.Ajax",
						data:{send_id:'<%=request.getSession().getAttribute("id")%>', get_id:'${member.id}'},
						type:"post",
						dataType:"html",  
						success:function(responsedata, textStatus, xhr){
							$("#heart").attr('class', 'far fa-heart');
							$("#cnt").html(responsedata);
						},
						error:function(xhr){
							alert(xhr.status + " : ERROR");
						}
					}	   
				);			
			}
		}
	});
});
</script>

</html>