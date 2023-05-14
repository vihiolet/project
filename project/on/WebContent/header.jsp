<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style/head.css">
<script>
    $(function(){
    $('.head3').click(function(){
        $('.head2').slideToggle();
    });
});
</script>
</head>
<body>
  <header>
      <div class="container">
         <div class="container-small">
              <a href="" class="head1">로고</a>   
              <button type="button" class="head3">
<!--                  <i class="fa-solid fa-bars-staggered"></i>-->
             <i class="fa-solid fa-bars"></i>
              </button>
         </div>
          
          <nav class="head2">
              <ul>
                  <li><a href="">TOP</a></li>
                  <li><a href="">콘텐츠</a></li>
                  <li><a href="">소개</a></li>
                  <li><a href="">문의</a></li>
              </ul>
          </nav>
      </div>
  </header>
</body>
</html>