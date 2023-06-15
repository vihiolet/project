<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>메인페이지</title>
</head>
<script src="https://kit.fontawesome.com/3e4d6b2bc7.js" crossorigin="anonymous"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="style/common.css">
<link rel="stylesheet" href="style/main.css">
<link rel="stylesheet" href="style/head.css">
<link rel="stylesheet" href="style/footer.css">
<body>
<!-- <a href="/on/ReviewWriteForm.fr">후기 쓰러 가기</a> -->
    <jsp:include page="header.jsp"></jsp:include>
    <div class="step1">
       <div class="slider">
          <ul>
            <li><img src="img/main1.jpg" alt="메인이미지2"></li>
            <li><img src="img/main01.jpg" alt="메인이미지1"></li>
            <li><img src="img/main01.jpg" alt="메인이미지3"></li>                 
          </ul>  
        </div> 
   </div>
   <div class="step2">
          <div class="banner"></div>
           <ul>
               <li>
                  <a href="">
                      <img src="img/B004985155.jpg" alt="">
                   <p>상품 이름이 들어갑니다</p>
                  </a>                   
                </li>
               <li>
                  <a href="">
                       <img src="img/A004985155.jpg" alt="">
                       <p>상품 이름이 들어갑니다</p>
                   </a>
                </li>
               <li>
                    <a href="">
                       <img src="img/B004985155.jpg" alt="">
                       <p>상품 이름이 들어갑니다</p>
                   </a>
                </li>
               <li>
                    <a href="">
                       <img src="img/A004985155.jpg" alt="">
                       <p>상품 이름이 들어갑니다</p>
                   </a>
                </li>
           </ul>
   </div>
    <div class="step3">
       <div>
          <ul>
            <li>
                <img src="img/main1.jpg" alt="메인이미지2">
                <p class="text">상품 설명이 들어갑니다</p>
            </li>
            <li>
                <img src="img/main01.jpg" alt="메인이미지1">
                <p class="text">업로드 기능을 악용하여 면</p>
            </li>
            <li>
                <img src="img/main01.jpg" alt="메인이미지3">
                <p class="text">사용자 입력 값에 대한 특수문자</p>
            </li>                 
          </ul>  
        </div> 
   </div>
   <div class="step4">

       <div class="keywordEx">
           <p class="tit">검색 맛 보기</p>
           <ul class="keylist">
               <li><span>생분해</span></li>
               <li><span>방문매장</span></li>
               <li><span>장애인일자리제공</span></li>
           </ul>
           <ul class="prolist">
               <li>
                    <a href="">
                       <img src="img/B004985155.jpg" alt="">
                       <p>상품 이름이 들어갑니다</p>
                   </a>
                </li>
               <li>
                    <a href="">
                       <img src="img/A004985155.jpg" alt="">
                       <p>상품 이름이 들어갑니다</p>
                   </a>
                </li>
               <li>
                  <a href="">
                       <img src="img/B004985155.jpg" alt="">
                       <p>상품 이름이 들어갑니다</p>
                   </a>
                </li>
               <li>
                  <a href="">
                       <img src="img/A004985155.jpg" alt="">
                       <p>상품 이름이 들어갑니다</p>
                   </a>
                </li>
                <li>
                    <a href="">
                       <img src="img/A004985155.jpg" alt="">
                       <p>상품 이름이 들어갑니다</p>
                    </a>
                </li>
           </ul>
       </div>
       <div class="keyword">
           <p class="tit">이렇게나 많은 검색점!</p>
           <div>
               <ul class="slide">
                      <li>장애인일자리제공</li>
                      <li>노인일자리제공</li>
                      <li>새활용</li>
                      <li>재활용</li>
                      <li>생분해</li>
                      <li>누구나이용</li>
                      <li>잔인함X</li>
                      <li>채식지향</li>
                      <li>방문매장</li>                      
                      <li>인터넷매장</li>
                      <li>탄소발자국</li>
                      <li>수익기부</li>
                </ul>
                <ul class="slide" aria-hidden="true">
                      <li>장애인일자리제공</li>                      
                      <li>노인일자리제공</li>
                      <li>새활용</li>
                      <li>재활용</li>
                      <li>생분해</li>
                      <li>누구나이용</li>
                      <li>잔인함X</li>
                      <li>채식지향</li>
                      <li>방문매장</li>                      
                      <li>인터넷매장</li>
                      <li>탄소발자국</li>
                      <li>수익기부</li>
                </ul>
           </div>
       </div>
   </div>
   <!--   푸터 시작-->
   <jsp:include page="footer.jsp"></jsp:include>
   <script src="js/slider.js"></script>
</body>
</html>