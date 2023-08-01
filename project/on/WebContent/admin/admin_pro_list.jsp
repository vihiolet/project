<%@ page import= "java.util.HashMap" %>
<%@ page import= "java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "vo.PageInfo"%>
<%@ page import= "vo.AdminProBean"%>
<%@ page import= "java.text.SimpleDateFormat"%>

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
<title>관리자제품목록</title>
<link rel="stylesheet" href="style/common.css">
<link rel="stylesheet" href="style/admin_pro_list.css">
<link rel="stylesheet" href="style/list.css">
<link rel="stylesheet" href="style/footer.css">
<script src="https://kit.fontawesome.com/3e4d6b2bc7.js" crossorigin="anonymous"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
	<body>
		<jsp:include page="../include/admin_header.jsp"></jsp:include>	<!--헤더-->
		<jsp:include page="../include/left_menu.jsp"></jsp:include>	<!--왼쪽 메뉴-->
		<form action="" method="post" name= "" onsubmit= "return checkForm()"> 
	  	<div class="btn">
	    	<a href="productRegForm.pr">제품 조회</a>
	    	<a href="productRegForm.pr">제품 등록</a>     
	  	</div> 
	    <table class="pro_list">
    	<%if(articleList != null && listCount > 0){%>
        	<tr class="keyword_tit">
                <td><input type="checkbox" name="allcheck" class= "pro_code" onClick='allCheck()'></td>                
                <td><button id= "delete_btn">삭제</button></td>
                <td><p class= "create_dt">등록일</p></td>
                <td><p class= "pro_company">제조회사</p></td>
                <td><p class= "pro_nm">제품이름</p></td>
                <td><p class= "create_id">등록인</p></td>
            </tr>
        	<%for(int i=0; i < articleList.size(); i++) {%>           	       	          	
            <tr class="keyword_info">          	
                <td><input type="checkbox" name="pro_code" class= "pro_code" value="<%=articleList.get(i).getPro_code() %>"></td>
                <td class="photo">
                	<a href="proModiForm.pr?pro_code=<%=articleList.get(i).getPro_code() %>&menu_code=<%=articleList.get(i).getMenu_code() %>" class= "pro_img">
                		<div class= "img">
                			<img src="images/<%=articleList.get(i).getPro_img() %>" alt="">
                		</div>
                	</a>
                </td>                
                <td><p class= "create_dt"><%=articleList.get(i).getCreate_dt() %></p></td>
                <td><p class= "pro_company"><%=articleList.get(i).getPro_company() %></p></td>
                <td><p class= "pro_nm"><%=articleList.get(i).getPro_nm() %></p></td>
                <td><p class= "create_id"><%=articleList.get(i).getCreate_id() %></p></td>
                
            </tr>  
            
          	<%} %>
	    </table> 
	    </form>  
	    <div id="pageList">
	    	<%if(nowPage <= 1) { %>
	        	[이전]&nbsp;
	        <% }else {%>		<!-- 이전 페이지가 존재하면 [이전] 텍스트에 직전 페이지 링크 -->
	        	<a href="adminKey.ke?page=<%=nowPage - 1 %>">[이전]</a>&nbsp;
	        <% } %>
	        <%for(int a= startPage; a <= endPage; a++){ 
	        	if(a == nowPage){%>
	          		[<%=a %>]
	          	<%}else{ %>
	          		<a href="adminKey.ke?page=<%= a %>">[<%= a %>]</a>&nbsp;
	          	<%} %>          			
	        <%} %>
	          	<%if(nowPage >= maxPage){ %>
	          		[다음]
	          	<%}else{ %>
	          		<a href="adminKey.ke?page=<%= nowPage + 1 %>">[다음]</a>&nbsp;
	          	<%} %>
	     </div>
	     <%	}else{ %>  
	       	<p>등록된 검색점이 없습니다</p>  
	     <% } %>               
	</body>
	<script>
		//다중 체크
		function allCheck(){
			let ac= document.regKeyword.allcheck;
			let sc= document.regKeyword.srch_code;
			
			if(ac.checked == true){
				for(i= 0; i< sc.length; i++){
					sc[i].checked = true;
				}
			}else{
				for(i= 0; i< sc.length; i++){
					sc[i].checked = false;
				}
			}
		}
		
		//삭제
		$('#delete_btn').on("click", function(e){
			//체크한 srch_code  			
			const pro_codeArr= [];
			
			let pro_code= $('input[type=checkbox][name=pro_code]:checked');
			
			$(pro_code).each(function(){
				pro_codeArr.push($(this).val());
			})  			
			
			if(pro_code != null){
				$.ajax({
	   				type: "POST",
	   				url: "adminProDel.pr",
	   				data: { "pro_codeArr" : pro_codeArr},
	   				traditional: true,
	   				success: function(data){
	   					//alert(data);
	   					alert('삭제되었습니당');
	   					location.reload();			//새로고침
	   				},
	   				error: function(data) {
	   					alert('오류!@#$%^');
	   					location.reload();
	   				}
	   			})
			}else{
				alert('삭제할 검색점을 선택하세요');
			}   			
			//location.reload();	//여기에 쓰면 success의 alert나 console.log가 실행 안 된다 success의 마지막에 써야 된다
		})
</script>

</html>