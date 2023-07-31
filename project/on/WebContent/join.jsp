<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 회원가입</title>
<script src="https://kit.fontawesome.com/3e4d6b2bc7.js" crossorigin="anonymous"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="style/common.css">
<link rel="stylesheet" href="style/join.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<div class="joinArea">
		<form action= "JoinAction.ur" method= "post" name= "userInfo" onsubmit= "return chkValue()">
			<div class="joinWrap">	
				<h2>회원가입</h2>
                <p>환영합니다.</p>		
				<input type= "text" name= "id" placeholder="아이디 15자의 영문/숫자 조합하여 입력하세요." maxlength="15" onkeydown= "inputIdChk()">
				<input type= "button" value= "id중복확인" onclick= "openIdChk()">
				<input type= "hidden" name= "idDuplication" value= "idUnCheck">
				<input type= "password" name= "passwd" placeholder="비밀번호">
				<input type= "password" name= "passwdChk" placeholder="비밀번호 확인" onchange= "passChk()">
				<p id= "passwdCheck">비밀번호 일치 확인</p>	
				<input type= "text" name= "name" placeholder="성명">
				<input type= "submit" class="join_btn" value="회원가입">   	
			</div>
		</form>
	</div>
<jsp:include page="footer.jsp"></jsp:include>
<script>
	//input의 빈 값 체크
	function chkValue(){
		let form= document.userInfo
		if(!form.id.value){
			alert('id를 입력하세요');
			return false;
		}
		if(!form.passwd.value){
			alert('비밀번호를 입력하세요');
			return false;
		}
		if(!form.name.value){
			alert('이름을 입력하세요');
			return false;
		}
		if(form.idDuplication.value != "idCheck"){
			alert('id 중복체크를 하세요');
			return false;
		}
	}
	//id 중복체크 팝업창 생성
	function openIdChk(){
		window.name= "parentForm"	//window.name을 지정해주면 IE에서 window.opener이 undefinde되는 걸 방지할 수 있다
		window.open("idCheckForm.ur", "checkForm", "width=460, height=260, top= 220, left= 200, resizable = no, scrollbars = no");
	}
	//중복 확인 후 사용 가능한 id면 idDuplication의 value 값은 idCheck가 된다
	//그 상태에서 id input에 새 id를 입력할 시 idDuplication의 value 값을 다시 idUnCheck로 바꾸기
	function inputIdChk(){
		document.userInfo.idDuplication.value = "idUnCheck";
	}
	//비밀번호 일치 확인
	function passChk(){
		let passwd= document.userInfo.passwd.value;
		let passwdChk= document.userInfo.passwdChk.value;
		if(passwd == passwdChk){
			document.getElementById("passwdCheck").innerHTML= "비밀번호가 일치합니다";
			$("#passwdCheck").css("color", "#222");
		}else{
			document.getElementById("passwdCheck").innerHTML= "비밀번호가 일치하지 않습니다";
			$("input[name=passwdChk]").val('');	//비밀번호 확인 input값 초기화
			$("#passwdCheck").css("color", "#f73e3e");
		}
	}
</script>	
</body>
</html>
