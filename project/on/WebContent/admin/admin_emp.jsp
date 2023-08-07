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
	                <td><input type="checkbox" name="" style="margin-left: 10px;"></td>                
	                <td>관리자ID</td>
	                <td>관리자명</td>
	                <td>등록인ID</td>
	                <td>생성일</td>
            	</tr>  
            	<c:forEach var= "empList" items= "${EmpList}" varStatus="status">
	            <tr class="emp_info">            
	            			
		                <td><input type="checkbox" name="emp_code" id= "emp_code" class= "key" value="${empList.emp_code}" style="margin-left: 10px;"></td>
		                <!-- <td><input type="text" name="emp_pass" id= "emp_pass" class= "key" value="${empList.emp_pass}"></td> -->
		                <td><input type="text" name="" id= "emp_id" class= "key emp_id" size="15" value="${empList.emp_id}"></td>
		                <td><input type="text" name="" id= "emp_name" class= "key emp_name" size="15" value="${empList.emp_name}"></td>		                
		                <td><input type="text" name="" id= "create_id" class= "key create_id" size="15" value="${empList.create_id}" readonly></td>
		                <td><input type="text" name="" id= "create_dt" class= "key create_dt" size="15" value="${empList.create_dt}"></td>
	                
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
  
    <script>
	//삭제
  		$('#delete_btn').on("click", function(e){
  			//체크한 srch_code
  			let emp_code= $('input[type=checkbox][name=emp_code]:checked').val();  
  			
  			if(emp_code != null){
  				$.ajax({
  	   				type: "POST",
  	   				url: "adminEmpDel.emp",
  	   				data: { emp_code : emp_code },
  	   				success: function(data){
  	   					alert('삭제되었습니다');
  	   					location.reload();			//새로고침
  	   				},
  	   				error: function(data) {
  	   					alert('오류');
  	   					location.reload();
  	   				}
  	   			})
  			}else{
  				alert('삭제할 검색점을 선택하세요');
  			}   			
  			//location.reload();	//여기에 쓰면 success의 alert나 console.log가 실행 안 된다 success의 마지막에 써야 된다
  		})
  	</script> 
</body>
</html>