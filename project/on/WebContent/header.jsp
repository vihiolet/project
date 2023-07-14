<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
      		<a href= "adminJoin.ur">관리자 로그인</a>
      		<!--<a href= "login.jsp">로그인</a>-->
      		<jsp forward page= "/login">로그인</jsp forward>
      		<a href= "join.jsp">회원가입</a>
      </div>
      <div class="container">      	
         <div class="container-small">
              <a href="index.jsp" class="head1">로고</a>   
              <button type="button" class="head3">
             <i class="fa-solid fa-bars"></i>
              </button>
         </div>
          
          <nav class="head2">
              <ul>
                  <li><a href="">음식</a></li>
                  <li><a href="">옷</a></li>
                  <li><a href="">장신구</a></li>
                  <li><a href="">미용</a></li>
                  <li><a href="">생활</a></li>
              </ul>
          </nav>
      </div>
  </header>
</body>
</html>