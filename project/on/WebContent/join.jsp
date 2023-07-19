<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 회원가입</title>
<script src="https://kit.fontawesome.com/3e4d6b2bc7.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="style/common.css">
<link rel="stylesheet" href="style/join.css">
<link rel="stylesheet" href="style/head.css">
<link rel="stylesheet" href="style/footer.css">
</head>
<body>
	<form action= "JoinAction.ur" method= "post" name= "userInfo" onsubmit= "return chkValue()">
		<div class="joinWrap">
			<input type= "text" name= "id" placeholder="아이디 3~15자의 영문/숫자 조합하여 입력하세요." onkeydown= "inputIdChk()">
			<input type= "button" value= "id중복확인" onclick= "openIdChk()
			<input type= "password" name= "passwd" placeholder="비밀번호">
			<input type= "text" name= "name" placeholder="이름">
			<input type= "hidden" name= "idDuplication" value= "idUnCheck">
		</div>
	<div class="button">
            <a href="javascript:_form.submit()" class="join_btn">회원가입</a><br>
        </div>
	</form>
	<script>
		//input의 빈 값 체크
		function chkValue(){
			let form= document.userInfo
			if(!form.id.value){
				alert('id를 입력하세요');
				return fales;
			}
			if(!form.passwd.value){
				alert('비밀번호를 입력하세요');
				return fales;
			}
			if(!form.name.value){
				alert('이름을 입력하세요');
				return fales;
			}
			if(form.idDuplication.value != "idCheck"){
				alert('id 중복체크를 하세요');
				return fales;
			}
		}
		//id 중복체크 팝업창 생성
		function inputIdChk(){
			window.name= "parentForm"
			window.open("front/idCheckForm.jsp", "checkForm", "width=500, height=300, resizable = no, scrollbars = no");
		}
		//중복 확인 후 사용 가능한 id면 idDuplication의 value 값은 idCheck가 된다
		//그 상태에서 id input에 새 id를 입력할 시 idDuplication의 value 값을 다시 idUnCheck로 바꾸기
		function inputIdChk(){
			document.userInfoidDuplication.value = "idUnCheck";
		}

	<script>	
</body>
</html>
