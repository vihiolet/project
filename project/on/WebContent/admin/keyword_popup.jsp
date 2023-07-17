<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "vo.PageInfo" %>
<%@ page import= "vo.KeywordBean" %>
<%@ page import= "java.util.*" %>
<%@ page import= "java.text.SimpleDateFormat" %>

<%
		ArrayList<KeywordBean> keywordList = (ArrayList<KeywordBean>)request.getAttribute("keywordList");
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
<title>Insert title here</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
      	<%if(keywordList != null && listCount > 0){%>
            <span>검색점</span>
            <span>비고</span>
           <div id= "keywordList" >
        	<%for(int i=0; i < keywordList.size(); i++) {%> 
        		<button type="button"  id= "keyword">         		
	        		<input type="hidden"  name="srch_code" id= "keycd" value= "<%=keywordList.get(i).getSrch_code() %>">
	        		<input type="text" name="srch_name" id= "keynm" value= "<%=keywordList.get(i).getSrch_name() %>">
	        		<p id= "remark" class= "remark"><%=keywordList.get(i).getRemark() %></p>
        		</button>
            <%} %> 
            </div>
            <input type="button" value="닫기" onclick="window.close()">
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
  <script>  		
  		$(document).ready(function () {
  		  $(document).on("click", "button", function () {
  			opener.document.getElementById("keycd").value = $(this).find("#keycd").val();
  			opener.document.getElementById("keynm").value = $(this).find("#keynm").val();
  		  });
  		});
  </script>
</body>
</html>
