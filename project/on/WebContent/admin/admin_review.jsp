<%@ page import= "java.util.HashMap" %>
<%@ page import= "java.util.ArrayList" %>
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
<title>관리자제품목록페이지</title>
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
	  	 
	    <table class="pro_list">
    	<%if(articleList != null && listCount > 0){%>
        	<tr class="keyword_tit">
                <td><input type="checkbox" name="allcheck" style="margin-left: 10px;" onClick='allCheck()'></td>                
                <td><input type="button" value="선택 제품 삭제" id= "delete_btn"></td>
            </tr>
        	<%for(int i=0; i < articleList.size(); i++) {%>                	
            <tr class="keyword_info">            	
            	<!-- 수정 불가 컬럼 : create_dt(등록일), create_id(등록인) -->
                <td><input type="checkbox" name="menu_code" id= "menu_code" value="<%=articleList.get(i).getMenu_code() %>" style="margin-left: 10px;"></td>
                <td class="photo"><img src="images/<%=articleList.get(i).getPro_img() %>" alt=""></td>                
                <td><span><%=articleList.get(i).getCreate_dt() %></span></td>
                <td><input type="text" name="" id= "" class= "" size="15" value="<%=articleList.get(i).getPro_company() %>"></td>
                <td><input type="text" name="" id= "" class= "pro_tit" size="15" value="<%=articleList.get(i).getPro_nm() %>"></td>
                <td><span><%=articleList.get(i).getCreate_id() %></span></td>
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
			const menu_codeArr= [];
			
			let menu_code= $('input[type=checkbox][name=menu_code]:checked');
			
			$(menu_code).each(function(){
				menu_codeArr.push($(this).val());
			})  			
			console.log(menu_codeArr);
			
			if(menu_code != null){
				$.ajax({
	   				type: "POST",
	   				url: "adminProDel.pr",
	   				data: { "menu_codeArr" : menu_codeArr},
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