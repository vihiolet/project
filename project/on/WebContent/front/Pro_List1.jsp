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
<title>제품1 페이지</title>
<script src="https://kit.fontawesome.com/3e4d6b2bc7.js" crossorigin="anonymous"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="style/common.css">
<link rel="stylesheet" href="style/list.css">
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
	     		<!--메뉴코드 1(음식) 상품만 출력  -->
				<c:if test= "${articleList.menu_code == 1}"> 
			         <article>
			         	 <a href="Pro_view.fr?pro_code=${articleList.pro_code}">
			             <input type="text" name= "pro_code" value="${articleList.pro_code}">
			                <div class="photo">
			                    <img src="images/${articleList.pro_img}" alt="">
			                </div>
			                 <div class="text">
			                 	<input type="hidden" name= "menu_code" value="${articleList.menu_code}">
			                     <h3>${articleList.pro_nm}</h3>
			                     <p>${articleList.pro_company}</p>
			                 </div>
			                 <div class="icon">
			                     <i class="fa-regular fa-heart"></i>
			                     <span>123</span>
			                 </div>
			             </a>
			         </article>
		        </c:if>
	     	</c:forEach>
	     </c:if> 
	     </div>
	</section>	
<jsp:include page="./../footer.jsp"></jsp:include>
</body>
</html>