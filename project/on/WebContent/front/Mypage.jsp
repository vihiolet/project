<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import= "vo.UserBean" %>
<%@ page import= "vo.ReviewBean" %>
<%@ page import= "java.util.HashMap" %>
<%@ page import= "java.util.ArrayList" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>mypage</title>
    <script src="https://kit.fontawesome.com/3e4d6b2bc7.js" crossorigin="anonymous"></script>
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" href="style/common.css">
    <link rel="stylesheet" href="style/mypage.css">
    <link rel="stylesheet" href="style/head.css">
    <link rel="stylesheet" href="style/footer.css">
    </head>
<body>
<jsp:include page="./../header.jsp"></jsp:include>
    <div class="mypageWrap">
        <div class="container">
            <jsp:include page="./../include/left.jsp"></jsp:include>    <!--왼쪽 메뉴-->  
            <div class="myContent">
               <div>
                    <div class="board">
                        <p>내가 남긴 후기</p>
                        <p class="myVote"><span>${reviewBean.reviewCount}</span>개</p>
                    </div>
                    <div class="vanner">    
                        <div><img src="" alt=""></div>
                        <div><img src="" alt=""></div>            
                    </div>
                    <p class="porList_tit">최근 리뷰 투표한 제품</p>
                    <div class="porList">                        
                        <div></div>
                    </div>
               </div>                
            </div>
        </div>
    </div>
<jsp:include page="./../footer.jsp"></jsp:include>
</body>
</html>
