<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String id= (String)session.getAttribute("id"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>이게 진짜 관리자등록</title>
</head>
<script src="https://kit.fontawesome.com/3e4d6b2bc7.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="style/common.css">
<link rel="stylesheet" href="style/admin_pro.css">
<link rel="stylesheet" href="style/head.css">
<body>
   <div class="admin_pro">
      <jsp:include page="./../include/admin_header.jsp"></jsp:include>	<!--헤더-->
      <div class="container">          
          <jsp:include page="./../include/left_menu.jsp"></jsp:include>	<!--왼쪽 메뉴-->
          <div class="content">
            <div class="bg">
                <div class="pro_tit"> 
                        <p>관리자명</p>
                        <p>관리자 ID</p>
                        <p>관리자 비밀번호</p>
                  </div>
                  <form action="adminEmpReg.emp" method="post" name= "empInfo" onsubmit= "return chkValue()">
                  	
                    <div class="pro_value">
                    	<input type= "hidden" name= "create_id" value= "<%=id %>">
                    	<div class="name_text">                     
                            <input type="text" name="name" required= "required">
                         </div> 
                         <div class="id_text">                     
                             <input type="text" name="id" required= "required"> 
                             <input type= "button" value= "id중복확인" onclick= "openIdChk()">
							 <input type= "hidden" name= "idDuplication" value= "idUnCheck">                         
                         </div>
                         <div class="pass_text">                     
                             <input type="password" name="passwd" required= "required">    
                             <input type= "password" name= "passwdChk" placeholder="비밀번호 확인" onchange= "passChk()">                      
                         </div>
                         <p id= "passwdCheck"></p>	                                            
                     </div> 
                    <div class="button">
                        <input type= "submit" class="btn" value="등록">
                        <input type="reset" value="다시 쓰기">
                    </div>
                  </form>
            </div>
          </div>
      </div>
    </div>    
    <script>
		//input의 빈 값 체크
		function chkValue(){
			let form= document.empInfo
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
			window.open("idCheckForm.emp", "checkForm", "width=500, height=300, resizable = no, scrollbars = no");
		}
		//중복 확인 후 사용 가능한 id면 idDuplication의 value 값은 idCheck가 된다
		//그 상태에서 id input에 새 id를 입력할 시 idDuplication의 value 값을 다시 idUnCheck로 바꾸기
		function inputIdChk(){
			document.empInfo.idDuplication.value = "idUnCheck";
		}
		//비밀번호 일치 확인
		function passChk(){
			let passwd= document.empInfo.passwd.value;
			let passwdChk= document.empInfo.passwdChk.value;
			if(passwd == passwdChk){
				document.getElementById("passwdCheck").innerHTML= "비밀번호가 일치합니다";
			}else{
				document.getElementById("passwdCheck").innerHTML= "비밀번호가 일치하지 않습니다";
				$("input[name=passwdChk]").val('');	//비밀번호 확인 input값 초기화
			}
		}
	</script>	
</body>
</html>
