/*print data table*/
$(document).ready(function() {
	    $('#example').DataTable(
    		{
    	        "lengthMenu": [[5, 10, 100], [5, 10, "All"]],
    	        "language": {
    	        	"emptyTable": "조회할 데이터가 없습니다.",
    	        	"lengthMenu": " _MENU_ 개씩 출력",
    	        	"info": "_END_ / _TOTAL_",
    	        	"infoEmpty": "",
    	        	"infoFiltered": "",
    	        	"search": "검색: ",
    	        	"zeroRecords": "일치하는 데이터 없음",
    	        	"loadingRecords": "로딩중..",
    	        	"paginate": {
    	        		"next": "다음",
    	        		"previous": "이전"
    	        	}
    	        	
    	        },
    	        responsive: true
    		}
	    );

	});
	
