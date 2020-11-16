$(function(){

var $grid = $('.grid').masonry({
		  // options
		  itemSelector:'.grid-item',
		 percentPosition: true,
		  columnWidth: '.grid-sizer'
});

$grid.imagesLoaded().progress(function() {
  $grid.masonry();
});  
		
		
$('.like').on("click",function(){
			if($(this).hasClass('far')){
				$(this).removeClass('far');
				$(this).addClass('fas');
				$.ajax({ 
					url:'myCart.my',
					data : {
						prd_num:$(this).val(),
							},
					type:"get",
					success:function(){
					}
				});
				
			}else if($(this).hasClass('fas')){
				$(this).removeClass('fas');
				$(this).addClass('far');
				$.ajax({ 
					url:'myCart.my',
					data : {
						prd_num:$(this).val(),
							},
					type:"get",
					success:function(){
					}
	
				});
			}
	});
	//무한 스크롤
	/*let isEnd = false;
	$(window).scroll(function(){
            let $window = $(this);
            let scrollTop = $window.scrollTop();
            let windowHeight = $window.height();
            let documentHeight = $(document).height();
            
            console.log("documentHeight:" + documentHeight + " | scrollTop:" + scrollTop + " | windowHeight: " + windowHeight );
       

			if( scrollTop + windowHeight + 30 > documentHeight ){
				
                $.ajax({ 
		            url:"ProductList.Ajax",
		            type: "get",
		            dataType: "json",
		            success: function(result){
		               // $('#grid').append(html);
						console.log(result);
		                // 컨트롤러에서 가져온 방명록 리스트는 result.data에 담겨오도록 했다.
		                // 남은 데이터가 5개 이하일 경우 무한 스크롤 종료
						let length = result[0].length;
						console.log(length);
		               /* if( length < 5 ){
		                    isEnd = true;
		                }
						for(var i=0; i<length; i++){
							
							console.log(result[0]);
							console.log(i);
							console.log(result[0][i]);
							console.log(result[0][i]["prd_num"]);
		                    //renderList(false, vo);
							console.log(result[0][i]["files"]["files_name"]);
							let html = "<div class='grid-item'>"+"<img src='upload/"+result[0][i]["files"]["files_name"]+"'>"+
							"<div class='overlay'>"+
							"<a href='<%=request.getContextPath()%>/ProductDetailPage.Pd?prd_num='"+result[0][i]["prd_num"]+"'>"+
							"<h3>"+result[0][i]["prd_title"]+"</h3>"+
							"</a><p>"+result[0][i]["prd_content"]+"</p>"
							"<c:choose>"+
							"<c:when test=${empty "+ result[1]+"}>"+
							"<button class='far fa-heart like' id='like' value='"+result[0][i]["prd_num"]+"'></button>"+
							"<input type='text' value='off' name='check' id='check' hidden>"+
							"</c:when>"+
							"<c:otherwise>"+
							/*"<c:forEach var='cart' items='${"+result[1]+"}'>"+
						"<c:if test='${"+result[0][i]["prd_num"]+"!="+result[1][i]["prd_num"]+"}'>"+
						"<button class='far fa-heart like' value='"+result[0][i]["prd_num"]+"'></button>"+
						"</c:if>"+
						"<c:if test=${"+result[0][i]["prd_num"]+"=="+result[1][i]["prd_num"]+"}>"+
						"<button class='fas fa-heart like' value='"+result[0][i]["prd_num"]+"'></button>"+
						"</c:if>"+
						"</c:forEach>"+
						"</c:otherwise>"+
						"</c:choose>"+
						"<button onclick='location.href='<%=request.getContextPath() %>/ProductDetailPage.Pd?prd_num=${"+
						result[0][i]["prd_num"]+
						"}'>상세보기</button>"+
						"</div>	"+
						"</div>";
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							$("#grid").append(html);
						}
						
		                }
		            })
		        };
            
        
       // fetchList();
    })
	*/
	
	/*let fetchList = function(){
        if(isEnd == true){
            return;
        }
			$.ajax({ 
		            url:"ProductList.Ajax",
		            type: "get",
		            dataType: "json",
		            success: function(result){
		               // $('#grid').append(html);
						console.log(result);
		                // 컨트롤러에서 가져온 방명록 리스트는 result.data에 담겨오도록 했다.
		                // 남은 데이터가 5개 이하일 경우 무한 스크롤 종료
						let length = result[0].length;
		                if( length < 5 ){
		                    isEnd = true;
		                }
		                $.each(result[0], function(index, vo){
							console.log(vo);
							console.log(vo[index]);
		                    //renderList(false, vo);
							console.log(vo[index]["files"]["files_name"]);
							let html = "<div class='grid-item'>"+"<img src='upload/"+result[index][0]["files"]["files_name"]+"'>"+
							"<div class='overlay'>"+
							"<a href='<%=request.getContextPath()%>/ProductDetailPage.Pd?prd_num='"+result[index]["prd_num"]+"'>"+
							"<h3>"+result[index]["prd_num"]+"</h3>"+
							"</a><p>"+result[index]["prd_title"]+"</p></div>";
							$("#grid").append(html);
							
		                })
		            }
		        });
               // fetchList();
            }*/
        
        //fetchList();

	
	
	
	

	/*let renderList = function(mode, vo){
        // 리스트 html을 정의

       let html = "<div class='grid-item'>"+
					"<img src='upload/'"+vo["files"]["files_name"]+">"+
					"<div class='overlay'>"+ 
						"<a href='<%=request.getContextPath()%>/ProductDetailPage.Pd?prd_num='"+vo["prd_num"]+"'>"+
						"<h3>"+vo[+"prd_num"+"]"+"</h3>"+
						"</a><p>"+vo[0][index][prd_title]+"</p>"+
						"<c:choose>"+
					"<c:when test=${empty"+ vo[1]}+">"+
							"<button class='far fa-heart like' id='like' value="+vo[0][index][prd_num]+"></button>"+
							"<input type='text' value='off' name='check' id='check' hidden>"+
							"</c:when>"+
							"<c:otherwise>"+
							"<c:forEach var='cart' value="+vo[1]+">"+
						"<c:if test=${"+vo[0][index][prd_num]+"!="+vo[1][index][prd_num]+"}>"+
						"<button class='far fa-heart like' value='"+vo[0][index][prd_num]+"'></button>"+
						"</c:if>"+
						"<c:if test=${"+vo[0][index][prd_num]+"=="+vo[1][index][prd_num]+"}>"+
						"<button class='fas fa-heart like' value='"+vo[0][index][prd_num]+"'></button>"+
						"</c:if>"+
						"</c:forEach>"+
						"</c:otherwise>"+
						"</c:choose>"+
						"<button onclick='location.href='<%=request.getContextPath() %>/ProductDetailPage.Pd?prd_num=${"+
						vo[0][index][prd_num]+
						"}'>상세보기</button>"+
						"</div>	"+
						"</div>";
       
        if( mode ){
            $("#list-guestbook").prepend(html);
        }
        else{
            $("#list-guestbook").append(html);
        };

		
		};
	*/
	
	
	
});
	