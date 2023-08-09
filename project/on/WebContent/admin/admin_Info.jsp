<%@ page import= "java.util.HashMap" %>
<%@ page import= "java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "vo.PageInfo" %>
<%@ page import= "vo.AdminEmpBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%	
	String id= (String)session.getAttribute("id");
%>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <title></title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" href="style/admin_info.css">
    <link rel="stylesheet" href="style/head.css">
    <link rel="stylesheet" href="style/common.css">
</head>
<body>
    <jsp:include page="./../include/admin_header.jsp"></jsp:include>	<!--헤더-->
    <jsp:include page="./../include/left_menu.jsp"></jsp:include>	<!--왼쪽 메뉴-->
    <div class= "content">    	
	    <div id= "info_list">     	
		      <div class="infoTit"> 
		      	  <p class="" style="color: #ccc">내 정보 변경</p>	      	  
		          <p>아이디</p>
		          <p style="padding-bottom: 35px;">이름</p>
		          <div style="padding-bottom: 12px; color: #ccc">비밀번호 변경</div>
		          <p>새 비밀번호</p>
		          <p>새 비밀번호 확인</p>
		      </div>
		      <form action="adminInfo.emp" method="post" name= "userInfo" onsubmit= "return checkForm()">    
		          	<div class="tit" style="padding-bottom: 15px;">내 정보 변경</div>
		            <div class="id_text">                        	                    
		                  <input type="text" name="id" value="${empInfo.emp_id }" readonly>
		            </div>
		            <div class="name_text">                         	                    
		                  <input type="text" name="name" value="${empInfo.emp_name }">
		             </div>                         
		             <div class="tit" style="padding-bottom: 15px;">비밀번호 변경</div>
		             <div class="pass_text">                     
		                 <input type="password" name="newPasswd" placeholder="비밀번호">   
		             </div>
		             <div class="pass_text">                     
		                 <input type="password" name="newPasswdChk" placeholder="비밀번호 확인" onchange= "passChk()">   
		             </div>
		             <p id= "passwdCheck">비밀번호 일치 확인</p>	
		          <input type="submit" class= "save_btn" value="수정하기">
		      </form>	      
	    </div>
	    <div id= "info_del"> 
	    	<div class="infoTit"> 
		      	 <p class="" style="color: #ccc">계정 삭제</p>	      	  
		         <p>현재 비밀번호</p>
		         <p>비밀번호 확인</p>
		    </div>
	    	<form action="adminDel.emp" method="post" name= "userDel" onsubmit= "return checkForm()">
	    		<div class="tit" style="padding-bottom: 15px;">계정 삭제</div>
	    		<div class="pass_text">                     
		        	<input type="password" name="nowPasswd" placeholder="비밀번호">   
		        </div>
		        <div class="pass_text">                     
		        	<input type="password" name="PasswdChk" placeholder="비밀번호 확인" onchange= "passChk2()">   
		        </div>
		        <p id= "passwdCheck2">비밀번호 일치 확인</p>	
		      	<input type="submit" class= "del_btn" value="계정삭제">
		    </form>
	    </div>
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
	
	function passChk2(){
		let nowPasswd= document.userDel.nowPasswd.value;
		let PasswdChk= document.userDel.PasswdChk.value;
		if(nowPasswd == PasswdChk){
			document.getElementById("passwdCheck2").innerHTML= "일치합니다";
			$("#passwdCheck2").css("color", "#222");
		}else{
			document.getElementById("passwdCheck2").innerHTML= "일치하지 않습니다";
			$("input[name=PasswdChk]").val('');	//비밀번호 확인 input값 초기화
			$("#passwdCheck2").css("color", "#f73e3e");
		}
	}
	
</script>	
</html>
