<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Closet</title>
	<link rel="stylesheet" href="resource/style/bootstrap-grid.css">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="resource/javascript/bootstrap.bundle.js"></script>
	<link rel="stylesheet" href="resource/style/myPage-style.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ac5b2d3cc708a6fcb27e5b8880d6d626&libraries=services"></script>
</head>




<body>
	<!-- 변수 선언 -->
	<c:set var="member" value="${requestScope.member}"/>
	<c:set var="getLike" value="${requestScope.getLike}"/>
	<c:set var="closet" value="${requestScope.closet}"/>
	<c:set var="productList" value="${requestScope.productList}"/>
	<c:set var="fileList" value="${requestScope.fileList}"/>
	
	<jsp:include page="/WEB-INF/views/common/Top.jsp"></jsp:include>
	<div id="wrapper" class="my-4">
		<div class="container">
			<div class="row">
				<!-- Left -->
				<div class="col-md-4 mx-auto my-auto" >
					<div>
						<div class="profile">
							<!-- 옷장 이름 -->
							<div class="profile-user-setting">
								<h1 class="profile-user-name">${closet.closet_title}</h1>
							</div>
							
							<!-- 회원 이름 -->
							<div class="closet-name">
								<p>${member.name}</p>
							</div>
							
							<!-- 하트 / 좋아요 -->
							<div class="user-state">
								<span id = heart><i class="fa fa-heart-o" aria-hidden="true" ></i> </span>
								<div id="cnt">
									${getLike}
								</div>
							</div>
							
							<!-- Profile 사진 -->
							<div class="profile">
							<div class="profile-image">
								<img src="upload/${member.profile_pic}"  alt="사진 등록 필요">
							</div>

							<!-- Address -->
							<div>
								<p>${member.addr}</p>
								<button id="map" value="${member.addr }" ><i class="fas fa-map-marker-alt"></i>Map</button>
								<div id="mapdiv" style="width:100px;height:100px;"></div>
							</div>
							
							<!-- 옷장 내용 -->
							<div class="profile-bio">
								<p id="closet_content">${closet.closet_content}</p>
								
							</div>
							</div>
							
							<!-- 회원정보, 옷장정보 수정 버튼 -->
							<div id="button">
								<button id="memberEditBtn" onclick="location.href='<%=request.getContextPath()%>/myInfoEditPage.my?id=${member.id}'" type="button">회원정보 수정</button>
								<button id="closetEditBtn" value="${closet.closet_content}" type="button">옷장정보 수정</button>
							</div>
						</div>
					</div>
				</div>
				
				<!-- Right -->
				<div class="col-md-8 mx-auto my-4" id="autoScroll">
					<div class="tabmenu">
						<div class="btnWrapper">
							<input id="tab1" type="radio" name="tabs" checked>
							<label for="tab1">판매목록</label>
							<input id="tab2" type="radio" name="tabs">
							<label class="mrAuto" for="tab2">찜목록</label>
							<button class="btn" onclick="location.href='<%=request.getContextPath()%>/ProductUploadPage.Pd'">상품등록</button>
						</div>
					</div>
					
					
					<!-- 판매 상품 목록 -->
					<div class="outer-grid">
						<c:forEach var="product" items="${productList}">
							<div class="inner-grid">
								<a href="<%=request.getContextPath()%>/ProductDetailPage.Pd?prd_num=${product.prd_num}">
									<img src="upload/${product.files.files_name}">
								</a>
								<div class="overlay">
									<span><i class="fas fa-heart"></i>&nbsp;30</span>
									&nbsp;&nbsp;&nbsp;
									<span><i class="fas fa-comment"></i>&nbsp;5</span>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Bottom -->
	<jsp:include page="WEB-INF/views/common/Bottom.jsp"></jsp:include>
	
	
</body>
<script>
		
		  $("#closetEditBtn").click(function(){
			  //console.log($(this).val());		
			$("#closet_content").remove();
		    $(".profile-bio").append(" <textarea id='contentedit'></textarea>.");
		    $("textarea").append($(this).val());
		    $("#memberEditBtn").remove();
		    $("#closetEditBtn").remove();
		    $("#button").append('<button type="button" id="cancleBtn">cancle</button>');
		    $("#button").append('<button type="button" id="editBtn">edit</button>');
		    
		  });

		$('#editBtn').on("click",function(){
			
			$.ajax({
				url:'myClosetEdit.my',
				type:'GET',
				dataType : 'JSON',
				data : {
					contentedit : $('contentedit').val()
				},
				success : function(data){
					console.log(data);
				}
			})
		});
</script>
<script>
$(document).ready(function(){
	  $("#heart").click(function(){
	    if($("#heart").hasClass("liked")){
	      $("#heart").html('<i class="fa fa-heart-o" aria-hidden="true"></i>');
	      $("#heart").removeClass("liked");
	    }else{
	      $("#heart").html('<i class="fa fa-heart" aria-hidden="true"></i>');
	      $("#heart").addClass("liked");
	    }
	  });
	});
	
	//판매목록, 찜목록
	$("input[name=tabs]").change(function(e) {
		if(e.target.id == 'tab1'){
			$(".outer-grid").html('<c:forEach var="product" items="${productList}"><div class="inner-grid"><a href="<%=request.getContextPath()%>/ProductDetailPage.Pd?prd_num=${product.prd_num}"><img src="upload/${product.files.files_name}"></a><div class="overlay"><span><i class="fas fa-heart"></i>&nbsp;30</span>&nbsp;&nbsp;&nbsp;<span><i class="fas fa-comment"></i>&nbsp;5</span></div></div></c:forEach>');
		} else {
			$(".outer-grid").html('cart 리스트');
		}
			
	});
</script>
<script>
	//map
	$('#map').on('click',function(){
		//console.log($(this).val());
		showMap($(this).val());
		
	});
	
    function showMap(addr){
	//console.log(addr);
        var mapContainer = document.getElementById('mapdiv'), // 지도를 표시할 div 
        mapOption = {
            center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
            level: 5 // 지도의 확대 레벨
        };  
        var userAdd = addr;
    
       // 지도를 생성  
       var map = new kakao.maps.Map(mapContainer, mapOption); 
       
       // 주소-좌표 변환 객체를 생성
       var geocoder = new kakao.maps.services.Geocoder();
       
       // 주소로 좌표를 검색
       geocoder.addressSearch(addr, function(result, status) {
       		//'서울 강남구 가로수길 43'
	       if (status === kakao.maps.services.Status.OK) {
	          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

	          // 결과값으로 받은 위치를 마커로 표시
	          var marker = new kakao.maps.Marker({
	              map: map,
	              position: coords
	          });
	  
	          // 지도의 중심을 결과값으로 받은 위치로 이동
	          map.setCenter(coords);
	       };
       }); 
	};
</script>

</html>