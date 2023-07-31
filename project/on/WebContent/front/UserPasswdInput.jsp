<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "vo.UserBean" %>
<%@ page import= "java.util.*" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>내 정보 페이지 접근 전 비번 입력</title>
    <script src="https://kit.fontawesome.com/3e4d6b2bc7.js" crossorigin="anonymous"></script>
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" href="style/common.css">
    <link rel="stylesheet" href="style/passwdchk.css">
</head>
<body>
<jsp:include page="./../header.jsp"></jsp:include>
	<div class="mypageWrap">
		<div class="container">
		    <jsp:include page="./../include/left.jsp"></jsp:include>    <!--왼쪽 메뉴-->
		    <div class="myContent">
		    	<h2>내 정보 수정</h2>
		    	<div>
			    	<h3>본인 확인</h3>  
			    	<p>회원님의 정보를 보호하기 위해 비밀번호를 확인합니다.</p>	
					<form action="userModiForm.ur" method="post" name="">
						<span>비밀번호</span>
						<input type="password" name= "passwd" id= "passwd">
						<input type= "submit" class="btn" value="확인">
					</form>
				</div>
		    </div>
		</div>
	</div>
<jsp:include page="./../footer.jsp"></jsp:include>
</body>
</html>