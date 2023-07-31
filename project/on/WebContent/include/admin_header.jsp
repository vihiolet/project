<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import= "java.util.*" %>
<%@ page import= "vo.AdminEmpBean" %>

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
         	<c:if test= "${empInfo != null}">
              <a href="adminMain.emp" class="head1">로고</a>  
             </c:if> 
              <!--<button type="button" class="head3"><i class="fa-solid fa-bars"></i></button>-->
         </div>
          <nav class="head2">
              <ul>
              	<c:if test= "${empInfo != null}">
                  <li class="admin_nm">관리자 : <span>${empInfo.emp_name } 님</span></li>
                  <li class="admin_cd">관리자 ID: <span>${empInfo.emp_id }</span></li>	<!-- 세션 id -->
                </c:if>
              </ul>
          </nav>
        </div>
      </header>
</body>
</html>