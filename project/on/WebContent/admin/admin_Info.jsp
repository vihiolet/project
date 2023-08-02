<%@ page import= "java.util.HashMap" %>
<%@ page import= "java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "vo.PageInfo" %>
<%@ page import= "vo.AdminEmpBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%	String id= (String)session.getAttribute("id"); %>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <title>관리자등록</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" href="style/admin_info.css">
    <link rel="stylesheet" href="style/head.css">
    <link rel="stylesheet" href="style/common.css">
</head>
<body>
    <jsp:include page="./../include/admin_header.jsp"></jsp:include>	<!--헤더-->
    <jsp:include page="./../include/left_menu.jsp"></jsp:include>	<!--왼쪽 메뉴-->
    <div id= "emp_info">
      <div class="btn">
  	     <a href="adminEmp.emp" class= "list">내 정보 조회</a>
  	     <a href="adminEmpRegForm.emp">계정 삭제</a>     
  	  </div> 
      <div class="infoTit"> 
          <p>아이디</p>
          <p style="padding-bottom: 26px;">이름</p>
          <div class="tit">비밀번호 변경</div>
          <p>새 비밀번호</p>
          <p>새 비밀번호 확인</p>
      </div>
      <form action=".emp" method="post" name= "" onsubmit= "return checkForm()">    
          <div class="key_value">
            <div class="id_text">                        	                    
                  <input type="text" name="id" value="${empInfo.id }" readonly>
            </div>
            <div class="name_text" style="padding-bottom: 22px;">                         	                    
                  <input type="text" name="name" value="${empInfo.name }">
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
</body>
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
</html>
