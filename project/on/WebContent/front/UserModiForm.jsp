<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import= "vo.UserBean" %>
<%@ page import= "java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>회원 수정 form</title>
</head>
<script src="https://kit.fontawesome.com/3e4d6b2bc7.js" crossorigin="anonymous"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="style/common.css">
<link rel="stylesheet" href="style/user_modi.css">
<body>
<jsp:include page="./../header.jsp"></jsp:include>	<!--헤더-->
	<div class="mypageWrap">
      <div class="container"> 
      	<jsp:include page="./../include/left.jsp"></jsp:include>	<!--왼쪽 메뉴-->       
          <div class="myContent">
		    <h2>내 정보 수정</h2>
		    <div>
                <div class="user_info"> 
                    <p>아이디</p>
                    <!--<p>이메일</p>-->
                    <p style="padding-bottom: 26px;">이름</p>
                    <div class="tit">비밀번호 변경</div>
                    <p>새 비밀번호</p>
                    <p>새 비밀번호 확인</p>
                </div>
                <form action="userModi.ur" method="post" name="userInfo">
                    <div class="key_value">
                        <div class="id_text">                        	                    
                              <input type="text" name="id" value="${userInfo.id }" readonly>
                         </div>
                        <div class="name_text" style="padding-bottom: 22px;">                         	                    
                              <input type="text" name="name" value="${userInfo.name }">
                         </div>                         
                         <div class="tit" style="color: #fff;">비밀번호 변경</div>
                         <div class="pass_text">                     
                             <input type="password" name="newPasswd" placeholder="비밀번호">   
                         </div>
                         <div class="pass_text">                     
                             <input type="password" name="newPasswdChk" placeholder="비밀번호 확인" onchange= "passChk()">   
                         </div>
                         <p id= "passwdCheck">비밀번호 일치 확인</p>	
                    </div>
                    <input type="submit" class= "btn" value="수정하기">                   
                </form>
            </div>
         </div>
       </div>
	</div>
<jsp:include page="./../footer.jsp"></jsp:include>
<script>
	//비밀번호 일치 확인
	function passChk(){
		let newPasswd= document.userInfo.newPasswd.value;
		let newPasswdChk= document.userInfo.newPasswdChk.value;
		if(newPasswd == newPasswdChk){
			document.getElementById("passwdCheck").innerHTML= "일치합니다";
			$("#passwdCheck").css("color", "#222");
		}else{
			document.getElementById("passwdCheck").innerHTML= "일치하지 않습니다";
			$("input[name=newPasswdChk]").val('');	//비밀번호 확인 input값 초기화
			$("#passwdCheck").css("color", "#f73e3e");
		}
	}
</script>	
</body>
</html>
