<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인 페이지</title>
<link rel="stylesheet" href="style/admin_login.css">
<script src="https://kit.fontawesome.com/3e4d6b2bc7.js" crossorigin="anonymous"></script>
</head>
<body>
   <div class="loginWrap">
        <div class="adminIcon">
            <i class="fa-solid fa-user-gear"></i>
            <span>관리자 페이지입니다.</span>
        </div>
	    <form action= "adminLogin.ur" method="post">		
			<div>
				<input type="text" name= "id" class="id" placeholder="ID"><br>
		        <input type="password" name= "passwd" class="password" placeholder="PASSWORD"><br>
		        <input type= "submit" value= "로그인" style="width: 160px; height: 35px;">
	    	</div>
	    </form>
   </div>
</body>
</html>
