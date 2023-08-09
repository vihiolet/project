<%@ page import= "java.util.HashMap" %>
<%@ page import= "java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "vo.PageInfo"%>
<%@ page import= "vo.AdminProBean"%>
<%@ page import= "java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자제품목록페이지</title>
<link rel="stylesheet" href="style/common.css">
<link rel="stylesheet" href="style/admin_pro_list.css">
<link rel="stylesheet" href="style/review_list.css">
<link rel="stylesheet" href="style/footer.css">
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
	<body>
		<jsp:include page="../include/admin_header.jsp"></jsp:include>	<!--헤더-->
		<jsp:include page="../include/left_menu.jsp"></jsp:include>	<!--왼쪽 메뉴-->
		
		<div class= "list">
		  	<div class="btn">
		    	<a href="adminProList.pr">후기 조회</a>
		  	</div> 	
		  	<div class="review_list">  	
			    <table>
		    	<c:if test= "${articleList != null && articleList.size() > 0}">
		        	<tr class="review_tit">
		                <td><input type="checkbox" name="allcheck" class= "review_code" onClick='allCheck()'></td>                
		                <td><button id= "delete_btn">삭제</button></td>
		                <td><p class= "pro_nm">제품이름</p></td>
		                <td><p class= "tit_fg">후기제목</p></td>
		                <td><p class= "sub_fg">후기내용</p></td>
		                <td><p class= "create_dt">등록날짜</p></td>
		                <td><p class= "create_id">등록회원ID</p></td>
		            </tr>               	
		            <c:forEach var= "articleList" items= "${articleList}" varStatus="status"> 
		       	  	<tr class="review_info">          	
			             <td><input type="checkbox" name="review_code" class= "review_code" value="${articleList.review_code}"></td>               
			             <td class="photo">
			             	<a href="Pro_view.fr?pro_code=${articleList.pro_code}" class="img">
			             		<img src="images/${articleList.pro_img}" alt="">
			             	</a>
			             </td>
			             <td><p class= "pro_nm">${articleList.pro_nm}</p></td>
			             <td>
							<c:choose>
				 				<c:when test= "${articleList.tit_fg == 1}">
				                 	<p class="tit_fg">만족해요</p>
								</c:when>
								<c:when test= "${articleList.tit_fg == 2}">
				                 	<p class="tit_fg">보통이요</p>
								</c:when>
								<c:when test= "${articleList.tit_fg == 3}">
				                 	<p class="tit_fg">아쉬워요</p>
								</c:when>
							</c:choose>
						</td>
		             	<td>
		             		<div class= "sub_fg">
								<c:choose>
									<c:when test= "${articleList.sub1_fg == 1}">
		               					<span class="sub">가격이 낮아요</span>
		  							</c:when>
		  							<c:when test= "${articleList.sub1_fg == 2}">
		                 				<span class="sub">가격이 높아요</span>
		  							</c:when>
		  						</c:choose>
		  						<c:choose>
		  							<c:when test= "${articleList.sub2_fg == 1}">
		               					<span class="sub">품질이 좋아요</span>
		  							</c:when>
		  							<c:when test= "${articleList.sub2_fg == 2}">
		                 				<span class="sub">품질이 아쉬워요</span>
		  							</c:when>
		  						</c:choose>
		  						<c:choose>
					  				<c:when test= "${articleList.sub3_fg == 1}">
		               					<span class="sub">대체품 없어요</span>
		  							</c:when>
		  							<c:when test= "${articleList.sub3_fg == 2}">
		                 				<span class="sub">대체품 있어요</span>
		  							</c:when>
								</c:choose>
		             		</div>
		             	</td>
		            	<td><p class= "create_dt">${articleList.create_dt}</p></td>    
		             	<td><p class= "create_id">${articleList.create_id}</p></td>            
		         	</tr>  
		        	</c:forEach>                 
		         </c:if>
			    </table> 
			    <div id="pageList">
			    	<c:choose>
				    	<c:when test="${pageInfo.page <= 1}">
				    		[이전]&nbsp;
				    	</c:when>
				    	<c:otherwise>
				    		<a href="adminReviewList.re?page=${pageInfo.page - 1}">[이전]</a>&nbsp;
				    	</c:otherwise>
				    </c:choose>
			    	<c:forEach var="i" begin="1" end="${pageInfo.maxPage}" >
			    		<c:choose>
			    			<c:when test="${i == pageInfo.page}">
			    				[<c:out value="${i}" />]
			    			</c:when>
			    			<c:otherwise>
			    				<a href="adminReviewList.re?page=${i}">[${i}]</a>
			    			</c:otherwise>
			    		</c:choose>
			    	</c:forEach>
				    <c:choose>
				    	<c:when test="${pageInfo.page >= pageInfo.maxPage}">
				    		[다음]
				    	</c:when>
				    	<c:otherwise>
				    		<a href="adminReviewList.re?page=${pageInfo.page + 1}">[다음]</a>
				    	</c:otherwise>
				    </c:choose>
			    	<c:if test= " ${EmpList == null && listCount.size() == 0 }">
			        	<p>등록된 관리자가 없습니다</p>
			    	</c:if>
    			</div> 
			</div>		   		               
		</div> 
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
			//체크한 srch_code  			
			const menu_codeArr= [];
			
			let menu_code= $('input[type=checkbox][name=menu_code]:checked');
			
			$(menu_code).each(function(){
				menu_codeArr.push($(this).val());
			})  			
			console.log(menu_codeArr);
			
			if(menu_code != null){
				$.ajax({
	   				type: "POST",
	   				url: "adminProDel.pr",
	   				data: { "menu_codeArr" : menu_codeArr},
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
			//location.reload();	//여기에 쓰면 success의 alert나 console.log가 실행 안 된다 success의 마지막에 써야 된다
		})
</script>

</html>