<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 회원가입</title>
</head>
<body>
	<form action= "JoinAction.ur" method= "post" name= "">
		<input type= "text" name= "id" placeholder="아이디">
		<input type= "password" name= "passwd" placeholder="비밀번호">
		<input type= "text" name= "name" placeholder="이름">
		
		<div class="button">
            <input type="submit" value="등록">
            <input type="reset" value="다시 쓰기">
        </div>
	</form>
</body>
</html>