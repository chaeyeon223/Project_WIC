/*Sign in&up 이동 버튼 */
const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
	container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
	container.classList.remove("right-panel-active");
});

/*우편번호 검색*/
function findAddress() {
	new daum.Postcode({
		oncomplete: function(data) {
		
			let address = ''; // 주소
			let extraAddr = ''; // 상세주소

			if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				address = data.roadAddress;
			} else { // 사용자가 지번 주소를 선택했을 경우(J)
				address = data.jibunAddress;
			}

			if(data.userSelectedType === 'R'){
				if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
					extraAddr += data.bname;
				}
				// 건물명이 있고, 공동주택일 경우 추가
				if(data.buildingName !== '' && data.apartment === 'Y'){
					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}
				// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열 생성
				if(extraAddr !== ''){
					extraAddr = ' (' + extraAddr + ')';
				}
				document.getElementById("detailAddr").value = extraAddr;
			
			} else {
				document.getElementById("detailAddr").value = '';
			}

			document.getElementById('postcode').value = data.zonecode;
			document.getElementById('addr').value = address;
			document.getElementById('detailAddr').focus();
		}
	}).open();
}

/*정규식 표현*/
	const userEmail = document.querySelector("#id");
	const userPwd = document.querySelector("#pwd");
	const icon1 = document.querySelector(".icon1");
	const icon2 = document.querySelector(".icon2");
	const error = document.querySelector(".error-text");
	const error2 = document.querySelector(".error-text2");
	const signUpBtn = document.querySelector("#signup_btn");
	const emailReg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i //이메일 형식
	const pwdReg = /^[A-za-z0-9]{4,12}$/; //4~12자리 영문+숫자 조합 비밀번호 

	function checkEmail(){
		if(userEmail.value.match(emailReg)){
			userEmail.style.borderColor = "#27ae60";
			userEmail.style.background = "#eafaf1";
			icon1.style.display = "none";
			icon2.style.display = "block";
			error.style.display = "none";
			signUpBtn.style.display = "block";
		  }else{
			userEmail.style.borderColor = "#e74c3c";
			userEmail.style.background = "#fceae9";
			icon1.style.display = "block";
			icon2.style.display = "none";
			error.style.display = "block";
			signUpBtn.style.display = "none";
		  }
		  if(userEmail.value == ""){
			userEmail.style.borderColor = "lightgrey";
			userEmail.style.background = "#fff";
			icon1.style.display = "none";
			icon2.style.display = "none";
			error.style.display = "none";
			signUpBtn.style.display = "none";
		  }
		}

	function checkPwd(){
		if(userPwd.value.match(pwdReg)){
			userPwd.style.borderColor = "#27ae60";
			userPwd.style.background = "#eafaf1";
			icon3.style.display = "none";
			icon4.style.display = "block";
			error2.style.display = "none";
			signUpBtn.style.display = "block";
		}else{
			userPwd.style.borderColor = "#FF4B2B";
			userPwd.style.background = "#fceae9";
			icon3.style.display = "block";
			icon4.style.display = "none";
			error2.style.display = "block";
			signUpBtn.style.display = "none";
			}
		if(userPwd.value == ""){
			userPwd.style.borderColor = "lightgrey";
			userPwd.style.background = "#fff";
			icon3.style.display = "none";
			icon4.style.display = "none";
			error2.style.display = "none";
			signUpBtn.style.display = "none";
			  }
		}
	
/*check id for register */

$(function(){
			   $('#check_btn').click(function(){
				   $.ajax(
						{
							url: "checkId.my",
							data:{id:$('#id').val()},
							type:"get",
							dataType:"html",  
							success:function(responsedata){
								console.log(typeof(responsedata));	
								console.log('>' + responsedata + '<');	
								if(responsedata == "true"){
									alert("사용가능한 이메일입니다.");
								}else if (responsedata == "false"){
									alert("존재하는 이메일입니다.");
								}
							},
							error:function(xhr){
								alert(xhr.status + " : ERROR");
							}
							
						}	   
				   );
			   });
		  });
	

	

	
	