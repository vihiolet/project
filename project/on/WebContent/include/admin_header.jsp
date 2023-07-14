<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% session.setAttribute("id", "admin1"); %>		<!-- id 하드코딩(세션 테스트용) -->
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style/head.css">
</head>
<body>
	<header>
      <div class="container">
         <div class="container-small">
              <a href="" class="head1">로고</a>   
              <!--<button type="button" class="head3"><i class="fa-solid fa-bars"></i></button>-->
         </div>
          <nav class="head2">
              <ul>
                  <li class="admin_nm">관리자 : <span>admin</span></li>
                  <li class="admin_cd">관리자코드 : <span><%=(String)session.getAttribute("id") %></span></li>	<!-- 세션 id -->
              </ul>
          </nav>
        </div>
      </header>
</body>
</html>