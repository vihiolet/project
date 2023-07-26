<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>마이페이지 왼쪽 메뉴</title>
    <link rel="stylesheet" href="style/left.css">
</head>
<body>
    <div class="myInfo">
        <div class="pofile">
           <div class="pImg">
               <img src="" alt="">
           </div>
            <div class="pText">
                <p class="id">${userInfo.id }</p>
                <p class="logout">로그아웃<p>
            </div>
        </div>
        <div class="myMenu">
            <ul>
                <li><a href="userInfoModiForm.ur">내 정보 수정</a></li>
                <li><a href="userReview.ur">내가 투표한 리뷰</a></li>
                <li><a href="userQuitForm.ur">회원 탈퇴</a></li>
            </ul>
        </div>
    </div>
</body>
</html>
