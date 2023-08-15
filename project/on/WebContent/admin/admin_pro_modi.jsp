<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "vo.AdminProBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import= "java.util.HashMap" %>
<%@ page import= "java.util.ArrayList" %>
<% AdminProBean pro= (AdminProBean)request.getAttribute("pro"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>제품수정페이지입니다</title>
</head>
<script src="https://kit.fontawesome.com/3e4d6b2bc7.js" crossorigin="anonymous"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
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
	    		<a href="adminProList.pr" >제품 조회</a>
	    		<a href="productRegForm.pr" class= "reg">제품 등록</a>     
	  		</div>
            <div class="bg">
                <div class="pro_tit"> 
                        <p>상품 분류</p>
                        <p>상품 회사</p>
                        <p>상품 이름</p>
                        <p>검색점</p>
                        <p>상품 사진</p>
                  </div>
                  <form action="proModi.pr" method="post" enctype="multipart/form-data">
                  <c:if test= "${pro != null}">                  	
                    <div class="pro_value">
                    	<input type= "hidden" id ="pro_code" name ="pro_code" value= ${pro.pro_code }>
                    	<input type= "hidden" name= "create_id" value= "<%=(String)session.getAttribute("id") %>">
                        <div class="menu_radio sub">                        	                    
                              <input type="radio" name="menu_code" id="food" value="1"><label for="food">음식</label>
                              <input type="radio" name="menu_code" id="clothes" value="2"><label for="clothes">옷</label>
                              <input type="radio" name="menu_code" id="beauty" value="3"><label for="beauty">미용</label>
                              <input type="radio" name="menu_code" id="acce" value="4"><label for="acce">장신구</label>
                              <input type="radio" name="menu_code" id="life" value="5"><label for="life">생활</label>   
                         </div>
                         <div class="company_text">                     
                             <input type="text" name="pro_company" value="${pro.pro_company }" required>                             
                         </div>
                         <div class="name_text">                     
                            <input type="text" name="pro_nm" value="${pro.pro_nm }" required>
                         </div>
                         <div class="keyword_r">                     
                             <input type="button" value="추가" class="keybtn" onclick="keywordOpen()">
                             <input type="text" id= "keynm" class="keyInput" name="srch_nm1" value="${pro.srch_nm1 }" required>
                             <input type="hidden" id= "keycd" class="keyInput" name="srch_code1" value="${pro.srch_code1 }" required>
                         </div>
                         <div class="name_img">                     
                             <input type="file" name="pro_img" >
                             <div class="img">
                             	<img src="images/${pro.pro_img }">
                             </div>                             
                             <input type= "hidden" id ="pro_img" name ="pro_img" value= "${pro.pro_img }">
                         </div>                    
                     </div> 
                    </c:if>
                    <div class="button">
                        <input type="submit" class="reg" value="수정">
                        <input type="reset" class="reset" value="다시 쓰기">
                    </div>
                  </form>
            </div>
          </div>
      </div>
    </div>    
    <script>
        function keywordOpen(){
        	window.name= "parentForm";
        	let openWin = window.open("adminProPopup.pr", "key_popup", "width=570, height=350, resizeable = no, left= 200, top= 200");
        }

        //menu_code에 따라 radio 버튼 체크
        $(document).ready(function(){
	        let menu_code= ${pro.menu_code };
	        switch(menu_code){
	            case 1 : $('#food').attr('checked', true);
	                break;
	            case 2 : $('#clothes').attr('checked', true);
	                break;
	            case 3 : $('#beauty').attr('checked', true);
	                break;
	            case 4 : $('#acce').attr('checked', true);
	                break;
	            case 5 : $('#life').attr('checked', true);
	                break;
	        }
    })
    </script>
</body>
</html>
