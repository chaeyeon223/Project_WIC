<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="resource/style/productEditPage.css">
	<link rel="stylesheet" href="resource/style/bootstrap-grid.css">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="resource/javascript/bootstrap.bundle.js"></script> 
	<script src="resource/javascript/ProductEditPage.js"></script>
</head>

<body>
<!-- Top -->
<jsp:include page="/WEB-INF/views/common/Top.jsp"></jsp:include>
	
<!-- 객체 호출 -->
<c:set var="product" value="${requestScope.product}"></c:set>
<c:set var="fileList" value="${requestScope.fileList}"></c:set>
<c:set var="member" value="${requestScope.member}"></c:set>
	
<!-- Left -->
<div class="container content my-4">
	<div class="row py-4">
		<h1 class="mx-auto py-4 ">Product(EDIT)</h1>
	</div>
	<form action="<%=request.getContextPath()%>/ProductEdit.Pd" method="post" id="upload">
		<div class="row">
			<!-- prd_num을 parameter로 넘기기 위한 input tag(hide) -->
			<input type="text" value="${product.prd_num}" name="prd_num" style="display: none"/>
			
			<!-- 상품 정보 -->
			<div class="col-md-12">
				<div class="container">
					<div class="row ml-4">
						<h3 class="my-0">상품 이름 :</h3>
						&nbsp;<input class="col-sm-7" type="text" id="productName"
							name="productName" value="${product.prd_title}">
					</div>
					<br>
					<div class="row ml-4">
						<h3 class="my-0">지역 :</h3>
						&nbsp;<input class="col-sm-8" type="text" id="location"
							name="location" value="${member.addr}" readonly>
					</div>
					<br>
					<div class="row ml-4">
						<h3 class="my-0">가격 :</h3>
						&nbsp; <input class="col-sm-8" type="text" id="productPrice"
							name="productPrice" value="${product.prd_price}">
					</div>
					<br>
				</div>
			</div>
			
			<!-- 사진 -->
			<div class="col-md-12 ">
				<div class="row mx-auto">
					<div class="col-md-10 mx-auto" id="drop">
					
						<!-- upload area -->
						<div class="col-md-12 mx-auto mt-3" id="file_add">
							<p class="mt-4" style="text-align:center;"> Drag file Or Click to add files</p>
						</div>
						
						<!-- thumbnail area -->
						<div id="thumbnails" class="col-md-12 row mr-0 ml-0 mb-3"></div>
					</div>
				</div>
			</div>
		</div>
		<div class=" mb-4">
			<h3>&nbsp;</h3>
			<textarea class="col-md-10" id="summernote" name="context">${product.prd_content}</textarea>
		</div>
		<div class ="row mx-auto">
			<button class="col-md-3 mx-auto mb-3" type="submit" id="submit" name="submit">EDIT</button>
			<button class="col-md-3 mx-auto mb-3" id="reset" name="reset" onclick="location.href='<%=request.getContextPath()%>/ProductEditCancel.Pd?prd_num=${product.prd_num}'">CANCEL</button>
		</div>
	</form>	
	<input type="file" id="fileProfile" name="fileProfile" accept="image/*" multiple >
</div>

<!-- Bottom -->
<jsp:include page="WEB-INF/views/common/Bottom.jsp"></jsp:include>

</body>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<script	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"	rel="stylesheet">
	<script	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

	<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/codemirror/3.20.0/codemirror.css">
	<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/codemirror/3.20.0/theme/monokai.css">
	<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/codemirror/3.20.0/codemirror.js"></script>
	<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/codemirror/3.20.0/mode/xml/xml.js"></script>
	<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/codemirror/2.36.0/formatting.js"></script>
</html>