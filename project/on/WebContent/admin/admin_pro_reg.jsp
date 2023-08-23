<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String id= (String)session.getAttribute("id"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>제품 등록</title>
    <script src="https://kit.fontawesome.com/3e4d6b2bc7.js" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="style/common.css">
	<link rel="stylesheet" href="style/admin_pro.css">
	<link rel="stylesheet" href="style/head.css">
</head>
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
						<p>내용</p>
                  </div>
                  <form action="adminProReg.pr" method="post" name="proReg" enctype="multipart/form-data" onsubmit= "return checkForm()">
                  	
                    <div class="pro_value">
                    	<input type= "hidden" name= "create_id" value= "<%=id %>">
                        <div class="menu_radio sub">                        	                    
                              <input type="radio" name="menu_code" id="food" value="1"><label for="food">음식</label>
                              <input type="radio" name="menu_code" id="clothes" value="2"><label for="clothes">옷</label>
                              <input type="radio" name="menu_code" id="acce" value="3"><label for="acce">장신구</label>
                              <input type="radio" name="menu_code" id="beauty" value="4"><label for="beauty">미용</label>                              
                              <input type="radio" name="menu_code" id="life" value="5"><label for="life">생활</label>   
                         </div>
                         <div class="company_text">                     
                             <input type="text" name="pro_company" required= "required">
                             
                         </div>
                         <div class="name_text">                     
                            <input type="text" name="pro_nm" required= "required">
                         </div>
                         <div class="keyword_r">                     
                             <input type="button" value="추가"  class="keybtn" onclick="keywordOpen()">
                             <input type="text" id= "keynm" class="keyInput" name="srch_nm1" value="" placeholder="추가 버튼으로 검색점을 추가하세요" size="30" required= "required">
                             <input type="hidden" id= "keycd" class="keyInput" name="srch_code1" value="" required= "required">
                         </div>
                         <div class="name_img">                     
                             <input type="file" name="pro_img" required= "required">
                         </div>    
						<div class="pro_context">                     
                             <input type="text" name="pro_context" size="48"  maxlength="30">
                             <p class="contextMs">띄어쓰기 포함 25자</p>
                        </div>                    
                     </div> 
                    <div class="button">
                        <input type="submit" class="reg" value="등록">
                        <input type="reset" class="reset" value="다시 쓰기">
                    </div>
                  </form>
            </div>
          </div>
      </div>
    </div>    
    <script>
        
        function keywordOpen(){     
        	let openWin;
        	window.name= "parentForm";
            openWin = window.open("adminProPopup.pr", "key_popup", "width=570, height=350, resizeable = no, left= 200, top= 200");
        }
        
        function checkForm(){
    		let menu_cd= document.proReg.menu_code.value;
    		if(menu_cd == ''){
    			alert('상품분류를 선택하세요.');
    			return false;
    		}
    		return true;
    	}   
    </script>
</body>
</html>
