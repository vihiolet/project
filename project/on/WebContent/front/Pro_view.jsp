<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import= "vo.AdminProBean" %>
<%@ page import= "java.util.HashMap" %>
<%@ page import= "java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>뷰페이지입니다</title>
</head>
<script src="https://kit.fontawesome.com/3e4d6b2bc7.js" crossorigin="anonymous"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="style/common.css">
<link rel="stylesheet" href="style/view.css">
<link rel="stylesheet" href="style/head.css">
<link rel="stylesheet" href="style/footer.css">
<body>
<jsp:include page="./../header.jsp"></jsp:include>
   <section class="view">
   		<c:if test= "${pro != null}">
   			<input type= "hidden" id ="pro_code" value= ${pro.pro_code }>
	        <div class="pro_img">
	            <img src="images/${pro.pro_img}" alt="">
	        </div>
	        <div class="pro_info">
	           <p class="pro_company">${pro.pro_company}</p>
	           <div class="title">
	               <span class="pro_nm">${pro.pro_nm}</span>
	               <span class="keyword">${pro.srch_nm1}</span>    
	           </div>           
	           <p class="pro_explain">${pro.pro_explain}</p>           
	           <!-- 상품 정보 끝 -->
	           <!-- 후기 영역 시작 -->
	           <div class="review_info">
	                <p class="tit">${reviewCount}개의 후기</p>
	                <div class="review">
	                	<input type= "hidden" id ="tit_fg" value= ${MaxReviCount.tit_fg }>
	                    <ul class="review_tit"> 
	                        <li><i class="fa-solid fa-face-laugh"></i> 만족해요</li>
	                        <li class= "voteCount">${tit1Count }</li>
	                    </ul>
	                    <ul class="review_tit"> 
	                        <li><i class="fa-solid fa-face-meh"></i> 보통이요</li>
	                        <li class= "voteCount">${tit2Count }</li>
	                    </ul>
	                    <ul class="review_tit"> 
	                        <li><i class="fa-solid fa-face-sad-tear"></i> 아쉬워요</li>
	                        <li class= "voteCount">${tit3Count }</li>
	                    </ul>
	               </div>
	               <p class="review_rs">*주황색 박스가 가장 많은 표를 받은 후기입니다.</p>
	            </div>
	            <div class="btn">
	                <input type="button" class="go_review" value="후기 남기기" onclick="reviwPopup()">
	                <a href="" class="go_buy">구매하러 가기</a>
	            </div>
	        </div>
        </c:if>
	
    </section>   
    <div class="container"> 
            <h3>전체 후기</h3>
            <div class="reviewWrap">
                <ul class="reviewList">
                    <li class="icon"><i class="fa-solid fa-face-meh"></i></li>
                    <li class="id">tlsdbswls77</li>
                    <li class="tit">별로예요</li>
                    <li class="sub">가격이 높아요</li>
                    <li class="sub">대체품 있어요</li>                    
                    <li class="sub">품질이 좋아요</li>
                    <li class="create_dt">2023.08.31</li>
                </ul>
                <ul class="reviewList">
                    <li class="icon"><i class="fa-solid fa-face-laugh"></i></li>
                    <li class="id">asdfg8808</li>
                    <li class="tit">만족해요</li>                    
                    <li class="sub">가격이 낮아요</li>
                    <li class="sub">대체품 없어요</li>
                    <li class="sub">언제 끝나요?</li>
                    <li class="create_dt">2023.09.01</li>
                </ul>
            </div>
       </div> 
<jsp:include page="./../footer.jsp"></jsp:include>
<script>
	$(document).ready(function(){
		
		let tit_fg= ${MaxReviCount.reviewCount };
		
		$(".review_tit").each(function(){
			if($(this).children(".voteCount").text() == tit_fg){
				$(this).addClass("best_vote");
			}
		})
		
	})
	
	function reviwPopup(){
		window.name= "parentPage"
		window.open("Pro_reviewReg.fr", "checkForm", "width=800, height=550, resizable = no, scrollbars = no, top=120, left=100");
	}
</script>
</body>
</html>
