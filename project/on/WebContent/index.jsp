<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import= "vo.UserBean" %>
<%@ page import= "java.util.*" %>    
<%@ page import= "vo.AdminProBean" %>
<%@ page import= "java.util.HashMap" %>
<%@ page import= "java.util.ArrayList" %>
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
    <jsp:include page="header.jsp"></jsp:include>
    <div class="step1">
       <div class="slider">
          <ul>
            <li><img src="images/sliderImg1.png" alt="메인이미지1"></li>
            <li><img src="images/sliderImg2.png" alt="메인이미지2"></li>
            <li><img src="images/sliderImg3.png" alt="메인이미지3"></li>                 
          </ul>  
        </div> 
   </div>
   <div class="step2">
          <div class="banner">
          	<p>고르고 또 골라 자신있게 추천하는 제품</p>
          </div>
           <ul>
               <li>
               	<c:if test= "${mainpro1 != null}">
                  <a href="Pro_view.fr?pro_code=${mainpro1.pro_code}">
                      <img src="images/${mainpro1.pro_img }" alt="">
                   <p>${mainpro1.pro_nm }</p>
                  </a>       
                 </c:if>            
                </li>
               <li>
                  <c:if test= "${mainpro3 != null}">
                  <a href="Pro_view.fr?pro_code=${mainpro3.pro_code}">
                      <img src="images/${mainpro3.pro_img }" alt="">
                   <p>${mainpro3.pro_nm }</p>
                  </a>       
                 </c:if> 
                </li>
               <li>
                   <c:if test= "${mainpro4 != null}">
                  <a href="Pro_view.fr?pro_code=${mainpro4.pro_code}">
                      <img src="images/${mainpro4.pro_img }" alt="">
                   <p>${mainpro4.pro_nm }</p>
                  </a>       
                 </c:if> 
                </li>
               <li>
                 <c:if test= "${mainpro5 != null}">
                  <a href="Pro_view.fr?pro_code=${mainpro5.pro_code}">
                      <img src="images/${mainpro5.pro_img }" alt="">
                   <p>${mainpro5.pro_nm }</p>
                  </a>       
                 </c:if> 
                </li>
           </ul>
   </div>
    <div class="step3">
       <div>
          <ul>
            <li>
                <a href="https://vihiolet.github.io/"><img src="images/main1.png" alt="메인이미지1"></a>
                <a href="https://vihiolet.github.io/"><img src="images/main2.png" alt="메인이미지2"></a>
            </li>                
          </ul>  
        </div> 
   </div>
   <div class="step4">
		<!-- >
       <div class="keywordEx">
           <p class="tit">검색 맛 보기</p>
           <ul class="keylist">
               <li><span>생분해</span></li>
           </ul>
           <ul class="prolist">
               <li>
               	<c:if test= "${mainProSrch1 != null}">
                  <a href="Pro_view.fr?pro_code=${mainProSrch1.pro_code}">
                      <img src="images/${mainProSrch1.pro_img }" alt="">
                	   <p>${mainProSrch1.pro_nm }</p>
                  </a>       
                 </c:if> 
                </li>
               <li>
                  <c:if test= "${mainProSrch2 != null}">
                  <a href="Pro_view.fr?pro_code=${mainProSrch2.pro_code}">
                      <img src="images/${mainProSrch2.pro_img }" alt="">
                	   <p>${mainProSrch2.pro_nm }</p>
                  </a>       
                 </c:if> 
                </li>
               <li>
                  <c:if test= "${mainProSrch3 != null}">
                  <a href="Pro_view.fr?pro_code=${mainProSrch3.pro_code}">
                      <img src="images/${mainProSrch3.pro_img }" alt="">
                	   <p>${mainProSrch3.pro_nm }</p>
                  </a>       
                 </c:if> 
                </li>
                <li>
                    <c:if test= "${mainProSrch4 != null}">
                  <a href="Pro_view.fr?pro_code=${mainProSrch4.pro_code}">
                      <img src="images/${mainProSrch4.pro_img }" alt="">
                	   <p>${mainProSrch4.pro_nm }</p>
                  </a>       
                 </c:if> 
                </li>
           </ul>
       </div>
       <-->
       <div class="keyword">
           <p class="tit">이렇게나 많은 검색점! <span>어떤 주제로 윤리적인 제품을 찾고 싶으세요?</span></p>
           <div>
               <ul class="slide"> 
               		<!-- 7개 -->
                      <li>장애인일자리제공</li>
                      <li>어르신일자리제공</li>
                      <li>새활용</li>
                      <li>재활용</li>
                      <li>채식지향</li>
                      <li>탄소발자국절감</li>
                      <li>수익기부</li>
                      <li>친환경</li>
                </ul>
                <ul class="slide" aria-hidden="true">
                      <li>장애인일자리제공</li>                      
                      <li>어르신일자리제공</li>
                      <li>잔인함X</li>
                      <li>채식지향</li>     
                      <li>수익기부</li>
                      <li>채식지향</li>
                      <li>친환경</li>
                      <li>새활용</li>
                </ul>
           </div>
       </div>
   </div>
   <!--   푸터 시작-->
   <jsp:include page="footer.jsp"></jsp:include>
   <script src="js/slider.js"></script>
</body>
</html>