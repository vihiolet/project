<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>후기등록페이지입니다</title>
</head>
<script src="https://kit.fontawesome.com/3e4d6b2bc7.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="style/common.css">
<link rel="stylesheet" href="style/reg.css">
<body>
   <section class="reg">
        <div class="container"> 
        	<p class="review_tit">사용 후기를 등록해주세요.</p>
            <form action="reviewWritePro.on" method="post" name="reviewform">
            	<input type="hidden" name="pro_code" value="3">            	
            	
                <div class="reviewWrap tit">
                    <input type="radio" name="tit_fg" id="tit1" class="btn" value="1">
                    <label for="tit1" class="tit1"><i class="fa-solid fa-face-laugh"></i> 만족해요</label>
                    <input type="radio" name="tit_fg" id="tit2" class="btn" value="2">
                    <label for="tit2" class="tit2"><i class="fa-solid fa-face-meh"></i> 보통이요</label>
                    <input type="radio" name="tit_fg" id="tit3" class="btn" value="3">
                    <label for="tit3" class="tit3"><i class="fa-solid fa-face-sad-tear"></i> 아쉬워요</label>
                </div>
                <div class="reviewWrap sub">
                   <div>
                        <p class="subtitle">가격대</p>
                        <input type="radio" name="sub1_fg" id="sub1_1" class="btn2" value="1">
                        <label for="sub1_1" class="sub1">낮아요</label>
                        <input type="radio" name="sub1_fg" id="sub1_2" class="btn2" value="2">
                        <label for="sub1_2" class="sub1">높아요</label>   
                   </div>
                    <div>
                        <p class="subtitle">품질</p>
                        <input type="radio" name="sub2_fg" id="sub2_1" class="btn2" value="1">
                        <label for="sub2_1" class="sub1">좋아요</label>
                        <input type="radio" name="sub2_fg" id="sub2_2" class="btn2" value="2">
                        <label for="sub2_2" class="sub1">그럭저럭</label>   
                   </div>
                   <div>
                        <p class="subtitle">대체품</p>
                        <input type="radio" name="sub3_fg" id="sub3_1" class="btn2" value="1">
                        <label for="sub3_1" class="sub1">없어요</label>
                        <input type="radio" name="sub3_fg" id="sub3_2" class="btn2" value="2">
                        <label for="sub3_2" class="sub1">있어요</label>   
                   </div>
                </div>
                <div class="button">
                    <input type="submit" value="등록">
                    <input type="reset" value="다시쓰기">
                </div>
            </form>
       </div>
    </section>    
</body>
</html>