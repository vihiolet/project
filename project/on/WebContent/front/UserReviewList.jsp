<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import= "vo.UserBean" %>
<%@ page import= "vo.ReviewBean" %>
<%@ page import= "vo.AdminProBean" %>
<%@ page import= "java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>내가 투표한 후기</title>
</head>
<script src="https://kit.fontawesome.com/3e4d6b2bc7.js" crossorigin="anonymous"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="style/common.css">
<link rel="stylesheet" href="style/user_review.css">
<link rel="stylesheet" href="style/head.css">
<link rel="stylesheet" href="style/footer.css">
<body>
<jsp:include page="./../header.jsp"></jsp:include>	<!--헤더-->
	<div class="mypageWrap">
      <div class="container"> 
      	<jsp:include page="./../include/left.jsp"></jsp:include>	<!--왼쪽 메뉴-->    
      	<div class="myContent">   
          <h2>내가 투표한 리뷰</h2>
          	<div>
                 <table class="review_list">
	                 <tr class="keyword_tit">
			                <td><input type="checkbox" name="allcheck" class= "review_code" onClick='allCheck()'></td>                
			                <td><button id= "delete_btn">삭제</button></td>
			                <td><p class= "pro_nm">제품이름</p></td>
			                <td><p class= "tit_fg">후기제목</p></td>
			                <td><p class= "sub_fg">후기내용</p></td>
			                <td><p class= "create_dt">등록날짜</p></td>
			          </tr>
			          <c:if test= "${myReview != null && myReview.size() > 0}">
			          <c:forEach var= "myReview" items= "${myReview}" varStatus="status"> 
		          	  <tr class="review_info">          	
		                <td><input type="checkbox" name="review_code" class= "review_code" value="${myReview.review_code}"></td>               
		                <td class="photo">
		                	<a href="Pro_view.fr?pro_code=${myReview.pro_code}" class="pro_img">
		                		<img src="images/${myReview.pro_img}" alt="">
		                	</a>
		                </td>
		                <td><p class= "pro_nm">${myReview.pro_nm}</p></td>
		                <td><p class= "tit_fg">${myReview.tit_fg}</p></td>
		                <td>
		                	<div class= "sub_fg">
		                		<span >${myReview.sub1_fg}</span>
		                		<span >${myReview.sub2_fg}</span>
		                		<span>${myReview.sub3_fg}</span>
		                	</div>
		                </td>
		                <td><p class= "create_dt">${myReview.create_dt}</p></td>                
		            </tr> 
		            </c:forEach>
		            </c:if> 
                 </table>
         	</div>
         </div>
       </div>
	</div>
<jsp:include page="./../footer.jsp"></jsp:include>
</body>
<script>
	//다중 체크
	function allCheck(){
		let ac= document.regKeyword.allcheck;
		let sc= document.regKeyword.srch_code;
		
		if(ac.checked == true){
			for(i= 0; i< sc.length; i++){
				sc[i].checked = true;
			}
		}else{
			for(i= 0; i< sc.length; i++){
				sc[i].checked = false;
			}
		}
	}
		
	//삭제
	$('#delete_btn').on("click", function(e){	
		const review_codeArr= [];
		
		let review_code= $('input[type=checkbox][name=review_code]:checked');
		
		$(review_code).each(function(){
			review_codeArr.push($(this).val());
		})  			
		
		if(review_code != null){
			$.ajax({
   				type: "POST",
   				url: "userReviewDel.ur",
   				data: { "review_codeArr" : review_codeArr},
   				traditional: true,
   				success: function(data){
   					//alert(data);
   					alert('삭제되었습니당');
   					location.reload();			//새로고침
   				},
   				error: function(data) {
   					alert('오류!@#$%^');
   					location.reload();
   				}
   			})
		}else{
			alert('삭제할 검색점을 선택하세요');
		}
	})		
</script>
</html>
