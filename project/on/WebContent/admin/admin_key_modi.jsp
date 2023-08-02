<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import= "java.util.HashMap" %>
<%@ page import= "java.util.ArrayList" %>
<%String id= (String)session.getAttribute("id"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>검색점 수정</title>
</head>
<script src="https://kit.fontawesome.com/3e4d6b2bc7.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="style/common.css">
<link rel="stylesheet" href="style/admin_pro.css">
<link rel="stylesheet" href="style/head.css">
<body>
   <div class="admin_pro">
      <jsp:include page="./../include/admin_header.jsp"></jsp:include>	<!--헤더-->
      <div class="container">          
          <jsp:include page="./../include/left_menu.jsp"></jsp:include>	<!--왼쪽 메뉴-->
          <div class="content">
          	<div class="btn">
	    		<a href="adminKey.ke" >검색점 조회</a>   
	  		</div> 
            <div class="bg">
                <div class="pro_tit"> 
                        <p>검색점</p>
                        <p>등록인</p>
                        <p>비고</p>
                  </div>
                  <form action="adminKeywordModi.ke" method="post">
                  	
                    <div class="pro_value">
                    	<div class="name_text">                     
                            <input type="text" name="srch_name" value="${keyword.srch_name}">
                         </div> 
                         <div class="id_text">        
                            <p>${keyword.create_id}</p>
                         </div>
                         <div class="pass_text">                     
                             <input type="text" name="remark" value="${keyword.remark}">    
                         </div>                                            
                     </div> 
                    <div class="button">
                        <input type= "submit" class="btn" value="수정">
                        <input type="reset" value="다시 쓰기">
                    </div>
                  </form>
            </div>
          </div>
      </div>
    </div>    
</body>
</html>
