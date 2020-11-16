<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자 페이지</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
</head>
<body>
	<input type="text"  id="memberId">
	<input type="button" id="btn" value="조회">
	<div style="height:400px; width:400px;">
	<canvas id="memberChart"></canvas>
	</div>
</body>
<script>
	// 받은 좋아요의 갯수(옷장)
	// 올린 게시글 수 
	// 판매완료 게시글 수
	
	$('#btn').on('click',function(){
		$('#memberChart').empty();
		
		$.ajax({
			url:'admin.minchan',
			type : 'POST',
			dataType : 'JSON',
			data : {
				memberId : $('#memberId').val()
			},
			success:function(data){
				console.log(data)
				var mc = ${'memberChart'};
				var chart = new Chart(mc, {
					type : 'radar',
					data : {
						labels : ["방문", "옷장상품", "판매 상품", "판매완료 상품", "상품 문의"],
						datasets : [
							{
							 data: [data[0],data[1],data[2],data[3],data[4]],
							 backgroundColor : [
								 "rgba(242,166,52,0.5)",
								
							 ],
							 borderColor : [
								 "rgb(242,166,54)",
								
							 ],
							 hoberBackgroundColor : [
								 "rgb(242,166,54)",
								
							 ],
							 borderWidth : 1
							}
						]
					},
					options : {
						maintainAspectRatio : false,
						legend : {
							display : false
						}
					}
				})
				
			}
		});
	});
	
	/*
	//일일 신규 가입자 
	var ctx = $('#member');
	var chart = new Chart(ctx, {
		type : 'line',
		data: {
			labels : ['', '2', '3순위', '4순위', '5순위'],
			datasets : [
				{
					label : 'bar',
					data : [1,2,3,4,5,6,7]
				},
				{
					label : 'B',
					data : [1,3,3,4,4,6,2]
				}
			]
	
		}
	});
	*/
</script>
</html>

