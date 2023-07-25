<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "vo.PageInfo" %>
<%@ page import= "vo.KeywordBean" %>
<%@ page import= "java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<script src="https://kit.fontawesome.com/3e4d6b2bc7.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="style/common.css">
<link rel="stylesheet" href="style/keyword_modi.css">
<link rel="stylesheet" href="style/head.css">
<body>
   <div class="admin_pro">
      <jsp:include page="./../include/admin_header.jsp"></jsp:include>	<!--헤더-->
      <div class="container">          
          <jsp:include page="./../include/left_menu.jsp"></jsp:include>	<!--왼쪽 메뉴-->
          <div class="content">
            <div class="bg">
                <div class="keyword_tit"> 
                    <p>검색점</p>
                    <p>등록인</p>
                    <p>사용유무</p>
                    <p>비고</p>
                </div>
                <form action="keywordModiAction" method="post">
                    <div class="key_value">
                        <input type= "hidden" name= "create_id" value= "">
                        <div class="keyword_text">                        	                    
                              <input type="text" name="keyword" required>
                         </div>
                         <div class="createId_text">                     
                             <input type="text" name="" readonly>   
                         </div>
                         <div class="use_select">                     
                            <select name="use_yn" id="">
                                <option value="1">사용</option>
                                <option value="2">미사용</option>
                            </select>
                         </div>
                         <div class="remark_text">                     
                             <input type="text" name="remark" size="20" maxlength="20">   
                         </div>
                    </div>
                    <div class="button">
                        <input type="submit" value="수정">
                        <input type="reset" value="다시 쓰기">
                    </div>
                </form>
            </div>
         </div>
       </div>
    </div>
</body>
</html>