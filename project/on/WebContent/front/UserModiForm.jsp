<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import= "vo.UserBean" %>
<%@ page import= "java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>회원 탈퇴 form</title>
</head>
<script src="https://kit.fontawesome.com/3e4d6b2bc7.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="style/common.css">
<link rel="stylesheet" href="style/userQuit.css">
<link rel="stylesheet" href="style/head.css">
<link rel="stylesheet" href="style/footer.css">
<body>
      <jsp:include page="./../header.jsp"></jsp:include>	<!--헤더-->
      <div class="container"> 
      	<jsp:include page="./../include/left.jsp"></jsp:include>	<!--왼쪽 메뉴-->       
          <div class="content">
            <div class="bg">
                <div class="user_info"> 
                    <p>아이디</p>
                    <!--<p>이메일</p>-->
                    <p>이름</p>
                    <p>새 비밀번호</p>
                    <p>새 비밀번호 확인</p>
                </div>
                <form action="userModi.ur" method="post">
                    <div class="key_value">
                        <p class="tit">개인정보 변경</p>
                        <div class="id_text">                        	                    
                              <input type="text" name="id" value="${userInfo.id }" readonly>
                         </div>
                        <div class="name_text">                        	                    
                              <input type="text" name="name" value="${userInfo.name }">
                         </div>
<!--
                        <div class="email_text">                        	                    
                              <input type="text" name="name" value="${userInfo.email }">
                         </div>
-->                         
                         <p class="tit">비밀번호 변경</p>
                         <div class="pass_text">                     
                             <input type="password" name="newPasswd">   
                         </div>
                         <div class="pass_text">                     
                             <input type="password" name="newPasswdChk">   
                         </div>
                    </div>
                    <input type="submit" class= "btn" value="수정하기">                   
                </form>
            </div>
         </div>
       </div>
    <jsp:include page="./../footer.jsp"></jsp:include>
</body>
</html>
