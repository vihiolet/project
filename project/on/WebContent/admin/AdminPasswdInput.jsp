<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "java.util.*" %> 
<%@ page import= "vo.AdminEmpBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="style/passwdchk.css">
<link rel="stylesheet" href="style/head.css">
<link rel="stylesheet" href="style/common.css">
</head>
<body>
	<jsp:include page="./../include/admin_header.jsp"></jsp:include>	<!--헤더-->
    <div class="container">          
        <jsp:include page="./../include/left_menu.jsp"></jsp:include>	<!--왼쪽 메뉴-->
    	<div class= "content">
    		<div>
				<h3>본인 확인</h3>  
		    	<p>관리자 정보를 보호하기 위해 비밀번호를 확인합니다.</p>	
				<form action="adminInfoForm.emp" method="post" name="">
					<span>비밀번호</span>
					<input type="password" name= "passwd" id= "passwd">
					<input type= "submit" class="btn" value="확인">
				</form>
			</div>
		</div>
    </div>
</body>
</html>