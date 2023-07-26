<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>사용자 왼쪽 메뉴</title>
    <link rel="stylesheet" href="style/left.css">
</head>
<body>
    <div class="myInfo">
        <div class="pofile">
           <div class="pImg">
               <img src="" alt="">
           </div>
            <div class="pText">
                <p class="id">${userInfo.id}님</p>
                <p class="logout">로그아웃</p>
            </div>
        </div>
        <div class="myMenu">
            <ul>
                <li><a href="userInfoModiForm.ur">내 정보 수정</a></li>
                <li><a href="userReview.ur">내가 남긴 후기</a></li>
                <li><a href="userQuitForm.ur">회원 탈퇴</a></li>
            </ul>
        </div>
    </div>
</body>
</html>
