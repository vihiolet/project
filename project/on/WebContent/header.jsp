<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import= "java.util.*" %>
<%@ page import= "vo.UserBean" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./style/common.css">
<link rel="stylesheet" href="./style/head.css">
<script>
    $(function(){
    $('.head3').click(function(){
        $('.head2').slideToggle();
    });
});
</script>
</head>
<body>
 <header>
      <div class= "login">
      		<c:if test= "${userInfo == null}">
      			<a href= "adminLoginForm.ur">관리자로그인</a>
      		</c:if>
      		<c:if test= "${userInfo == null}">
	      		<div>
	      			<a href= "login.ur">로그인</a>
	      			<a href= "Join.ur">회원가입</a>
	      		</div>
      		</c:if>      		
      		<c:if test= "${userInfo != null}">
      			<div>
      				<span>${userInfo.id} 님</span>
      				<a href= "mypage.fr">마이페이지</a>
	      			<a href= "logout.ur">로그아웃</a>
      			</div>
      		</c:if>
      </div>
      <div class="container">      	
         <div class="container-small">
         	<c:if test= "${userInfo == null}">
              <a href="index.jsp" class="head1"><img src="images/logo.png"></a> 
           	</c:if>  
           	<c:if test= "${userInfo != null}">
           		<a href="userIndex.fr" class="head1"><img src="images/logo.png"></a> 
           	</c:if>
              <button type="button" class="head3">
             <i class="fa-solid fa-bars"></i>
              </button>
         </div>
          
          <nav class="head2">
              <ul>
                  <li><a href="ProList1.fr?menu_code=1">음식</a></li>
                  <li><a href="ProList2.fr?menu_code=2">옷</a></li>
                  <li><a href="ProList3.fr?menu_code=3">장신구</a></li>
                  <li><a href="ProList4.fr?menu_code=4">미용</a></li>
                  <li><a href="ProList5.fr?menu_code=5">생활</a></li>
              </ul>
          </nav>
      </div>
  </header>
</body>
</html>