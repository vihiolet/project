<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./style/common.css">
<link rel="stylesheet" href="./style/login.css">
<link rel="stylesheet" href="./style/footer.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div>
    	<form action="" name= "login_form">
           <div class="loginWrap">
                <h2>로그인</h2>
                <p>아이디와 비밀번호를 입력해주세요.</p>
                <input type="text" id="id" placeholder="아이디"><br>  
                <input type="password" id="pw" placeholder="비밀번호"><br>
                <a href="javascript:login_form.submit()" class="login_btn">로그인</a><br>
               <a href="" class="join">회원가입</a>
                <a href="" class="find">아이디/비밀번호찾기</a>
            </div>
        </form>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>