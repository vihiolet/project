<%@ page import= "java.util.HashMap" %>
<%@ page import= "java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "vo.PageInfo" %>
<%@ page import= "vo.AdminEmpBean" %>
<%@ page import= "java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%	String id= (String)session.getAttribute("id"); %>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <title>관리자등록</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" href="style/admin_emp.css">
    <link rel="stylesheet" href="style/head.css">
    <link rel="stylesheet" href="style/common.css">
</head>
<body>
    <jsp:include page="./../include/admin_header.jsp"></jsp:include>	<!--헤더-->
    <jsp:include page="./../include/left_menu.jsp"></jsp:include>	<!--왼쪽 메뉴-->
  <div id= "emp_list">
    <div class="btn">
	   <a href="adminEmp.emp" class= "list">관리자 조회</a>
	   <a href="adminEmpRegForm.emp">관리자 등록</a>     
	</div> 
    <form action="adminEmpReg.emp" method="post" name= "regEmp" onsubmit= "return checkForm()">    	
        <div>
            <p class="notice">*관리자 정보 수정 및 삭제는 본인만 가능합니다.</p>           
        </div>
        <c:set var= "listCount" value="${pageInfo.getListCount()}" ></c:set>
	    <c:if test= "${EmpList != null && EmpList.size() > 0}">
        	<table class="emp_list">         		
	        	<tr class="emp_tit">
	                <td><span style="margin-left: 10px; display:block;">no</span></td>                
	                <td>관리자ID</td>
	                <td>관리자명</td>
	                <td>등록인ID</td>
	                <td>생성일</td>
            	</tr>  
            	<c:forEach var= "empList" items= "${EmpList}" varStatus="status">
            	<c:set var= "i" value="${i+1 }"/>
	            <tr class="emp_info">
		                <td><span style="margin-left: 10px; display:block;">${i }</span></td>
		                <td>${empList.emp_id}</td>
		                <td>${empList.emp_name}</td>		                
		                <td>${empList.create_id}</td>
		                <td>${empList.create_dt}</td>
	                
	            </tr> 
	            </c:forEach>
        	</table>        
        </c:if>
        <div id="pageList">
    	<c:choose>
	    	<c:when test="${pageInfo.page <= 1}">
	    		[이전]&nbsp;
	    	</c:when>
	    	<c:otherwise>
	    		<a href="adminEmp.emp?page=${pageInfo.page - 1}">[이전]</a>&nbsp;
	    	</c:otherwise>
	    </c:choose>
    	<c:forEach var="i" begin="1" end="${pageInfo.maxPage}" >
    		<c:choose>
    			<c:when test="${i == pageInfo.page}">
    				[<c:out value="${i}" />]
    			</c:when>
    			<c:otherwise>
    				<a href="adminEmp.emp?page=${i}">[${i}]</a>
    			</c:otherwise>
    		</c:choose>
    	</c:forEach>
	    <c:choose>
	    	<c:when test="${pageInfo.page >= pageInfo.maxPage}">
	    		[다음]
	    	</c:when>
	    	<c:otherwise>
	    		<a href="adminEmp.emp?page=${pageInfo.page + 1}">[다음]</a>
	    	</c:otherwise>
	    </c:choose>
    	<c:if test= " ${EmpList == null && listCount.size() == 0 }">
        	<p>등록된 관리자가 없습니다</p>
    	</c:if>
    </div>
    </form>
       
  </div>  
</body>
</html>
