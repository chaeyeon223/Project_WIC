<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="resource/style/uploadProductPage-style.css">
	<link rel="stylesheet" href="resource/style/bootstrap-grid.css">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="resource/javascript/bootstrap.bundle.js"></script> 
</head>

<body>
<!-- Top -->
<jsp:include page="/WEB-INF/views/common/Top.jsp"></jsp:include>
	
	<!-- 객체 호출 -->
	<c:set var="product" value="${requestScope.product}"></c:set>
	<c:set var="price" value="${requestScope.price}"></c:set>
	<c:set var="fileList" value="${requestScope.fileList}"></c:set>
	<c:set var="member" value="${requestScope.member}"></c:set>
	
	<!-- Test -->
	<c:forEach var="file" items="${fileList}" varStatus="status">
	</c:forEach>
	
	<!-- Left -->
	<div class="container content my-4">
		<div class="row py-4">
			<h1 class="mx-auto py-4 ">Product(EDIT)</h1>
		</div>
		<form action="<%=request.getContextPath()%>/ProductEdit.Pd" method="post" id="upload">
			<div class="row">
			
				<!-- 상품 정보 -->
				<div class="col-md-6">
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
								name="productPrice" value="${price}">
						</div>
						<br>
						<div class="ml-4 mb-4">
							<h3>상품 설명을 해 주세요</h3>
							<textarea class="col-md-10" id="context" name="context">${product.prd_content}</textarea>
						</div>

					</div>
				</div>
				
				<!-- 사진파일 -->
				<div class="col-md-6">
					<div class =" row">
						<input class="col-md-3 mx-auto mb-3" type="submit" id="submit" name="submit" value="EDIT">
						<input class="col-md-3 mx-auto mb-3" type="reset" id="reset" name="reset" value="CANCLE">
					</div>
					<div class="col-md-10 mx-auto" id="drop">
						<div class="col-md-12 mx-auto mt-3" id="file_add">
							<p class="mt-4" style="text-align:center;"> Drag file Or Click to add files</p>
						</div>
						<div id="thumbnails" class="col-md-12 row mr-0 ml-0 mb-3">
						
							<!--  					
							<c:forEach var="file" items="${fileList}" varStatus="status">
								<div class="thumb col-md-5 px-auto mx-auto">
									<div class="close" data-idx="${status.getIndex()}">x</div>
									<img class="col-md-12 px-0 mx-0" src="upload/${file.files_name}" title="${file.files_name}"/>								
								</div>
							</c:forEach>
							-->
						</div>
					</div>
				</div>
			</div>
		</form>
		<input type="file" id="fileProfile" name="fileProfile" accept="image/*" multiple hidden>
	</div>
	
	<!-- Bottom -->
	<jsp:include page="WEB-INF/views/common/Bottom.jsp"></jsp:include>

</body>
<script type="text/javascript">
	$(function(){
		$('#thumbnails').html('');
		
	});
	$(function(){
		var uploadFiles=[];
		var $drop=$('#drop');
		
		$drop.on("dragenter",function(e){
			$(this).addClass('drag-over');
		}).on("dragleave",function(e){
			$(this).removeClass('drag-over');
		}).on("dragover",function(e){
			e.stopPropagation();
			e.preventDefault();
		}).on("drop",function(e){
			var files= e.originalEvent.dataTransfer.files;
			console.log(files);
			e.preventDefault();
	   		$("input[type='file']")
	        	.prop("files", files)  // put files into element
	        	.closest("form")
	        	.submit();  // autosubmit as well
			$(this).removeClass('drag-over');
			thumbnail(files)
			
			var formData = new FormData();
			formData.append('upload-file', files[0], files.name);
			
			
			$.ajax({
				url: 'fileUpload.Ajax',
				data : formData,
				type : 'post',
				contentType : false,
				processData: false,
				success : function(ret) {
					console.log(ret);
				}
			});
			
		});
		
		function thumbnail(files){
			for(var i=0; i<files.length; i++){
				var file = files[i];
				var size = uploadFiles.push(file);
				preview(file,size-1);
			}
		}
		
		function preview(file,idx){
			let reader = new FileReader();
			reader.onload =(function(f,idx){
					return function(e){
						var div = '<div class="thumb col-md-5 px-auto mx-auto">\
							<div class="close" data-idx="'+idx+'">x</div>\
							<img class="col-md-12 px-0 mx-0" src="'+e.target.result+'"title="'+escape(f.name)+'"/>\
							</div>';
							$('#thumbnails').append(div);
					};
			})(file,idx);
			reader.readAsDataURL(file);
		}
		
		$('#thumbnails').on("click",".close",function(e){
			var $target = $(e.target);
			var idx=$target.attr('data-idx');
			uploadFiles[idx].upload='disable';
			$target.parent().remove();
			
			$.ajax({
				url:'fileDelete.Ajax',
				data: {filename:uploadFiles[idx].name},
				type: 'post'
				
			});
			
		})
		
		
		$('#file_add').click(function() {
		    $("#fileProfile").click();
		   
		});
		
		 //업로드 파일체인지가 됬을경우 실행되는 이벤트  form태그에 fileProfile은 opacity:0으로 넣어줌
		var input = document.querySelector('input[name="fileProfile"]');
	    input.addEventListener('change',(function(e){
	    	
	    	var fileList = input.files;
	    	thumbnail(fileList);
	        $("#fileProfile").val();
			
			var formData = new FormData();
			formData.append('upload-file', fileList[0], fileList.name);
			
			$.ajax({
				url: 'fileUpload.Ajax',
				data : formData,
				type : 'post',
				contentType : false,
				processData: false,
				success : function(ret) {}
			});
			
			
		}))
		
		
		function readFiles(file){
			let reader = new FileReader();
			reader.onload=function(e){
				var bin = e.target.result;	
				console.log(bin);
				var formData = new FormData(); 
				formData.append("filelist",bin)
				
				}
			reader.readAsDataURL(file);
		};
		
		
	});
</script>

</html>