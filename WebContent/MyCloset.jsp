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
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
	<!-- jQuery Modal -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
	
</head>




<body>
	<!-- 변수 선언 -->
	<c:set var="member" value="${requestScope.member}"/>
	<c:set var="getLike" value="${requestScope.getLike}"/>
	<c:set var="closet" value="${requestScope.closet}"/>
	<c:set var="productList" value="${requestScope.productList}"/>
	<c:set var="fileList" value="${requestScope.fileList}"/>
	<c:set var="checkLike" value="${requestScope.checkLike}"></c:set>
	<c:set var="id" value="${sessionScope.id}"></c:set>
	
	<jsp:include page="/WEB-INF/views/common/Top.jsp"></jsp:include>
	<div id="wrapper" class="my-4">
		<div class="container">
			<div class="row">
				<!-- Left -->
				<div class="col-md-4 mx-auto my-auto" >
					<div>
						<div class="profile">
							<!-- 옷장 이름 -->
							<div class="profile-user-setting my-4">
								<h1 class="profile-user-name">${member.name}의 옷장</h1>
							</div>
							
							<!-- 회원 이름 -->
							<div class="closet-name">
								<p>${member.name}</p>
							</div>
							
							<!-- 하트 / 좋아요 -->
							<div class="user-state">
								<!--  <span id = heart><i class="fa fa-heart-o" aria-hidden="true" ></i> </span> -->		
								<c:choose>
									<c:when test="${checkLike eq 0}">
										<i id="heart" class="far fa-heart" aria-hidden="true"></i>
									</c:when>
									<c:otherwise>
										<i id="heart" class="fas fa-heart" aria-hidden="true"></i>
									</c:otherwise>
								</c:choose>	
								<div id="cnt">
									${getLike}
								</div>
							</div>
							
							<!-- Profile 사진 -->
							<div class="profile">
							<div class="profile-image">
								<img src="upload/${member.profile_pic}"  alt="사진 등록 필요">
							</div>

							<!-- Map -->
							<div>
								<!--<button id="map" value="${member.addr }" ><i class="fas fa-map-marker-alt"></i>Map</button> -->
								<a href="#ex1" rel="modal:open"><button class="fas fa-map-marker-alt" id="map" value="${member.addr }"></button></a>
								<div id="ex1" class="modal">
								  <div id="mapdiv" class="mx-auto" style="width:400px; height:400px;"></div>
								</div>
								<p>${member.addr}</p>
								<!-- <div id="mapdiv" style="width:100px;height:100px;"></div>  -->
							</div>
							
							<!-- 옷장 내용 -->
							<div class="profile-bio col-md-8 mx-auto my-4">
								<p id="closet_content">${closet.closet_content}</p>
								 <textarea id='contentedit' hidden></textarea>
								
							</div>
							</div>
							
							<!-- 회원정보, 옷장정보 수정 버튼 -->
							<div id="button">
								<button id="memberEditBtn" onclick="location.href='<%=request.getContextPath()%>/myInfoEditPage.my?id=${member.id}'" type="button">회원정보 수정</button>
								<button id="closetEditBtn" value="${closet.closet_content}" type="button">옷장정보 수정</button>
								<button id="cancleBtn" value="${closet.closet_content}" type="button" hidden="hidden">취소</button>
								
								<button id="editBtn" value="${closet.closet_content}" type="button" hidden="hidden">수정</button>
								
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
 	//옷장정보 수정
		  $("#closetEditBtn").click(function(){
			  //console.log($(this).val());		
			$("#closet_content").hide();
			$("#closet_content").hide();
		    $("#contentedit").show();
		    $("textarea").append($(this).val());
		    $("#memberEditBtn").hide();
		    $("#closetEditBtn").hide();

		    $("#cancleBtn").show();
		    $("#editBtn").show();
		 /*    $("#button").append('<button type="button" id="cancleBtn">cancle</button>');
		    $("#button").append('<button type="button" id="editBtn">edit</button>'); */
		    
		  });
		$('#editBtn').on("click",function(){
			console.log($('#contentedit').val());
			let content = $('#contentedit').val();
			$.ajax({
				url:'myClosetEdit.my',
				type:'POST',
				data:{
					contentedit : $('#contentedit').val(),
					
				},
				success: function(data){
					console.log(data);
					$('textarea').hide()
					$('#closet_content').text(content);
					$('#closet_content').show();
					$('#editBtn').hide();
					$('#cancleBtn').hide();
					$("#memberEditBtn").show();
				    $("#closetEditBtn").show();
					
					//location.href="myPage.my";
					
				}
			})
		});
		
		$('#cancleBtn').on("click",function(){
			$("#closet_content").show();
			$("#memberEditBtn").show();
		    $("#closetEditBtn").show();
		    $('#editBtn').hide();
			$('#cancleBtn').hide();
			$('textarea').hide()
		    
		});
</script>
<script>
	//좋아요 표시
$(document).ready(function(){
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
	//map (Address)
	$('#map').on('click',function(){
		//console.log($(this).val());
		showMap($(this).val());
		
	});
	
    function showMap(addr){
	console.log(addr);
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
	          map.relayout()
	          // 지도의 중심을 결과값으로 받은 위치로 이동
	          map.setCenter(coords);
	          
	       };
       }); 
	};
	
</script>

</html>