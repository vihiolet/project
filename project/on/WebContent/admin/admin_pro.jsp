<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>관리자제품관리페이지입니다</title>
</head>
<script src="https://kit.fontawesome.com/3e4d6b2bc7.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="style/common.css">
<link rel="stylesheet" href="style/admin_pro.css">
<link rel="stylesheet" href="style/head.css">
<link rel="stylesheet" href="style/left_menu.css">
<body>
   <div class="admin_pro">
     <header>
      <div class="container">
         <div class="container-small">
              <a href="" class="head1">로고</a>   
              <!--<button type="button" class="head3"><i class="fa-solid fa-bars"></i></button>-->
         </div>
          <nav class="head2">
              <ul>
                  <li class="admin_nm">관리자 : <span>admin</span></li>
                  <li class="admin_cd">관리자코드 : <span>admin</span></li>
              </ul>
          </nav>
        </div>
      </header>
      
      <div class="container">
            <!--jsp로 바꿀 때 left_nemu는 include로 교체하기-->
          <div class="left_nemu">
              <ul>
                  <li>
                      <span><a href=""><i class="fa-solid fa-boxes-packing" style="color: #ffffff;"></i> 최근 등록</a></span>
                  </li>
                  <li>
                      <span><a href=""><i class="fa-solid fa-tags" style="color: #ffffff;"></i> 제품 설정</a></span>
                  </li>
                  <li>
                      <span><a href=""><i class="fa-solid fa-user-gear" style="color: #ffffff;"></i> 관리자 설정</a></span>
                  </li>
                  <li>
                      <span><a href=""><i class="fa-solid fa-magnifying-glass-plus" style="color: #ffffff;"></i> 검색점 설정</a></span>
                  </li>
                  <li>
                      <span><a href=""><i class="fa-solid fa-pencil" style="color: #ffffff;"></i> 후기 관리</a></span>
                  </li>
              </ul>
          </div>
          <div class="content">
            <div class="bg">
                <div class="pro_tit"> 
                        <p>상품 분류</p>
                        <p>상품 회사</p>
                        <p>상품 이름</p>
                        <p>검색점</p>
                        <p>상품 사진</p>
                  </div>
                  <form action="adminPro.on" method="post" enctype="multipart/form-data">
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
                             <input type="text" class="keyInput" name="srch_nm1" value="친환경" required= "required">
                             <input type="hidden" class="keyInput" name="srch_code1" value="01" required= "required">
                             <input type="text" class="keyInput" name="srch_nm2">
                             <input type="hidden" class="keyInput" name="srch_code2">
                             <input type="text" class="keyInput" name="srch_nm3">
                             <input type="hidden" class="keyInput" name="srch_code3">
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
            openWin = window.open("keyword_popup.html", "popup", "width=570, height=350, resizeable = no");
        }
    </script>
</body>
</html>