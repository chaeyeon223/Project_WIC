$(function(){
	var uploadFiles=[];
	var $drop=$('#drop');
	
	$('#summernote').summernote( {
		maxheight: 400,
		minHeight: 300,
		lang: "ko-KR",	
		placeholder: '최대 2048자까지 쓸 수 있습니다',
		codemirror: { // codemirror options
    	theme: 'monokai'
  		},
		callbacks:{
			onImageUpload: function(files, editor,weleditable){
					thumbnail(files);
					uploadFile(files);
			}
		}
	});
	
	
	$drop.on("dragenter",function(e){
		$(this).addClass('drag-over');
	}).on("dragleave",function(e){
		$(this).removeClass('drag-over');
	}).on("dragover",function(e){
		e.stopPropagation();
		e.preventDefault();
	}).on("drop",function(e){
		var file= e.originalEvent.dataTransfer.files;
		e.preventDefault();
   		$("input[type='file']")
        .prop("files", file)  // put files into element
        .closest("form")
        .submit();  // autosubmit as well
		$(this).removeClass('drag-over');
		thumbnail(file)
		uploadFile(file)
	});
	
	function uploadFile(file){
		var formData = new FormData();
		formData.append('upload-file', file[0], file.name);
		
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
	}
	
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
    	
    	var file = input.files;
    	thumbnail(file);
        $("#fileProfile").val();
		
		uploadFile(file);
		
		
	}));
	
	
	function readFiles(file){
		let reader = new FileReader();
		reader.onload=function(e){
			var bin = e.target.result;	
			var formData = new FormData(); 
			formData.append("filelist",bin)
			
			}
		reader.readAsDataURL(file);
	};
})