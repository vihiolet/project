<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>제품관리페이지입니다</title>
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
            <div class="bg">
                <div class="pro_tit"> 
                        <p>상품 분류</p>
                        <p>상품 회사</p>
                        <p>상품 이름</p>
                        <p>검색점</p>
                        <p>상품 사진</p>
                  </div>
                  <form action="adminProReg.pr" method="post" enctype="multipart/form-data">
                    <div class="pro_value">
                        <div class="menu_radio">                      
                              <input type="radio" name="menu_code" id="food" value="1"><label for="food">음식</label>
                              <input type="radio" name="menu_code" id="clothes" value="2"><label for="clothes">옷</label>
                              <input type="radio" name="menu_code" id="beauty" value="3"><label for="beauty">미용</label>
                              <input type="radio" name="menu_code" id="life" value="4"><label for="life">생활</label>   
                         </div>
                         <div class="company_text">                     
                             <input type="text" name="pro_company" required= "required">
                         </div>
                         <div class="name_text">                     
                            <input type="text" name="pro_nm" required= "required">
                         </div>
                         <div class="keyword_r">                     
                             <input type="button" value="추가" onclick="keywordOpen()">
                             <input type="text" id= "keynm" class="keyInput" name="srch_nm1" value=""  required= "required">
                             <input type="hidden" id= "keycd" class="keyInput" name="srch_code1" value="" required= "required">
                         </div>
                         <div class="name_img">                     
                             <input type="file" name="pro_img" required= "required">
                         </div>                    
                     </div> 
                    <div class="button">
                        <input type="submit" value="등록">
                        <input type="reset" value="다시 쓰기">
                    </div>
                  </form>
            </div>
          </div>
      </div>
    </div>    
    <script>
        let openWin;
        function keywordOpen(){
        	
            openWin = window.open("adminProPopup.pr", "key_popup", "width=570, height=350, resizeable = no, left= 200, top= 200");
        }
    </script>
</body>
</html>