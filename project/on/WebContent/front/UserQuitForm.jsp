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
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="style/common.css">
<link rel="stylesheet" href="style/userQuit.css">
<link rel="stylesheet" href="style/head.css">
<link rel="stylesheet" href="style/footer.css">
<body>
<jsp:include page="./../header.jsp"></jsp:include>	<!--헤더-->
	<div class="mypageWrap">
      <div class="container"> 
      	<jsp:include page="./../include/left.jsp"></jsp:include>	<!--왼쪽 메뉴-->    
      	<div class="myContent">   
          <h2>회원 탈퇴</h2>
          	<div>
                <div class="user_info"> 
                    <p>아이디</p>
                    <p>비밀번호</p>
                </div>
                <form action="userQuit.ur" method="post">
                    <div class="key_value">
                        <div class="id_text">                        	                    
                              <p>${userInfo.id}</p>
                         </div>
                         <div class="pass_text">                     
                             <input type="password" name="passwd" required>   
                         </div>
                    </div>
                    <input type="submit" class= "btn" value="탈퇴하기">                   
                </form>
         	</div>
         </div>
       </div>
	</div>
<jsp:include page="./../footer.jsp"></jsp:include>
</body>
</html>
