<%@ page import= "java.util.HashMap" %>
<%@ page import= "java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "vo.PageInfo"%>
<%@ page import= "vo.AdminProBean"%>
<%@ page import= "java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	ArrayList<AdminProBean> articleList= (ArrayList<AdminProBean>)request.getAttribute("articleList");
	PageInfo pageInfo= (PageInfo)request.getAttribute("pageInfo");
	int listCount= pageInfo.getListCount();
	int nowPage= pageInfo.getPage();
	int maxPage= pageInfo.getMaxPage();
	int startPage= pageInfo.getStartPage();
	int endPage= pageInfo.getEndPage();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자제품목록페이지</title>
<link rel="stylesheet" href="style/common.css">
<link rel="stylesheet" href="style/list.css">
<link rel="stylesheet" href="style/footer.css">
<script src="https://kit.fontawesome.com/3e4d6b2bc7.js" crossorigin="anonymous"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<jsp:include page="../include/admin_header.jsp"></jsp:include>	<!--헤더-->
	<div class="list a_container">
		<jsp:include page="../include/left_menu.jsp"></jsp:include>	<!--왼쪽 메뉴-->
        <div class="container a_bg">   
        	<div class="btn">
        		<a href="productRegForm.pr">등록</a>
        	</div>  
        	<%
        		if(articleList != null && listCount > 0){
        			for(int i=0; i<articleList.size(); i++){
        	%>    
            <article>
                <a href="#">
                   <div class="photo">
<!--                      style="background-image: url(img/B004985155.jpg);"-->
                       <img src="images/<%=articleList.get(i).getPro_img() %>" alt="">
                   </div>
                    <div class="text">
                        <p><%=articleList.get(i).getPro_company() %></p>
                        <h3><%=articleList.get(i).getPro_nm() %></h3>
                    </div>
                </a>
            </article>
            <%
        			}
            %>
            
            <%
        		}else{
            %>
            <p>등록된 제품이 없습니다d</p>
            <%} %>
            
            
            <c:out value="${articleList}" />
            
            <c:if test= "${articleList.size() == 0 }">
            	<p>등록된 제품이 없습니다</p>
            </c:if>
            
        </div>
   </div>
</body>
</html>