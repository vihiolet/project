<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import= "vo.PageInfo" %>
<%@ page import= "vo.AdminProBean" %>
<%@ page import= "java.util.HashMap" %>
<%@ page import= "java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>생활 제품 조회</title>
<script src="https://kit.fontawesome.com/3e4d6b2bc7.js" crossorigin="anonymous"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="style/common.css">
<link rel="stylesheet" href="style/pro_list.css">
<link rel="stylesheet" href="style/head.css">
<link rel="stylesheet" href="style/footer.css">
</head>
<body>
<jsp:include page="./../header.jsp"></jsp:include>
	 <section class="list a_container">
	     <div class="container a_bg"> 
	     <c:set var= "listCount" value="${pageInfo.getListCount()}"></c:set>
	     <c:if test= "${articleList != null && articleList.size() > 0}">
	     	<c:forEach var= "articleList" items= "${articleList}" varStatus="status">
	     		<c:if test= "${articleList.menu_code == 5}"> 
	     		<!--메뉴코드 5(생활) 상품만 출력  -->				
			         <article>
			         	 <a href="Pro_view.fr?pro_code=${articleList.pro_code}">
			             <input type="hidden" name= "pro_code" value="${articleList.pro_code}">
			                <div class="photo">
			                    <img src="images/${articleList.pro_img}" alt="">
			                </div>
			                 <div class="text">
			                 	<input type="hidden" name= "menu_code" value="${articleList.menu_code}">
			                     <h3>${articleList.pro_nm}</h3>
			                     <p>${articleList.pro_company}</p>
			                 </div>
			             </a>
			         </article>
		        </c:if>
	     	</c:forEach>
	     </c:if> 
	     </div>
	     <div id="pageList">
		   	<c:choose>
		    	<c:when test="${pageInfo.page <= 1}">
		    		[이전]&nbsp;
		    	</c:when>
		    	<c:otherwise>
		    		<a href="ProList1.fr?menu_code=5&page=${pageInfo.page - 1}">[이전]</a>&nbsp;
		    	</c:otherwise>
		    </c:choose>
		   	<c:forEach var="i" begin="1" end="${pageInfo.maxPage}" >
		   		<c:choose>
		   			<c:when test="${i == pageInfo.page}">
		   				[<c:out value="${i}" />]
		   			</c:when>
		   			<c:otherwise>
		   				<a href="ProList1.fr?menu_code=5&page=${i}">[${i}]</a>
		   			</c:otherwise>
		   		</c:choose>
		   	</c:forEach>
		    <c:choose>
		    	<c:when test="${pageInfo.page >= pageInfo.maxPage}">
		    		[다음]
		    	</c:when>
		    	<c:otherwise>
		    		<a href="ProList1.fr?menu_code=5&page=2">[다음]</a>
		    	</c:otherwise>
		    </c:choose>
		   	<c:if test= " ${articleList == null && articleList.size() == 0 }">
		       	<p>등록된 관리자가 없습니다</p>
		   	</c:if>
   		</div>
	</section>	
<jsp:include page="./../footer.jsp"></jsp:include>
</body>
</html>