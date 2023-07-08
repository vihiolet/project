<%@ page import= "java.util.HashMap" %>
<%@ page import= "java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "vo.PageInfo" %>
<%@ page import= "vo.AdminEmpBean" %>
<%@ page import= "java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <form action="adminEmpReg.emp" method="post" name= "regEmp" onsubmit= "return checkForm()">
        <div>
            <input type="button"  class="add" value="추가" onclick= "addClick()">
            <input type="submit" value="저장" id='save_btn'>            
            <input type="button" value="삭제" id= "delete_btn">
        </div>
        <c:set var= "listCount" value="${pageInfo.getListCount()}" ></c:set>
	    <c:if test= "${EmpList != null && EmpList.size() > 0}">
        	<table class="emp_list">         		
	        	<tr class="emp_tit">
	                <td><input type="checkbox" name="" style="margin-left: 10px;"></td>                
	                <td>관리자ID</td>
	                <td>관리자비밀번호</td>
	                <td>관리자명</td>
	                <td>등록인ID</td>
	                <td>비고</td>
            	</tr>  
	            <tr class="emp_info">            
	            	<c:forEach var= "empList" items= "${EmpList}" varStatus="status">		
		                <td><input type="checkbox" name="emp_code" id= "emp_code" class= "key" value="${empList.emp_code}" style="margin-left: 10px;"></td>
		                <td><input type="text" name="emp_code" id= "emp_code" class= "key" value="${empList.emp_code}"></td>
		                <td><input type="text" name="emp_pass" id= "emp_pass" class= "key" value="${empList.emp_pass}"></td>
		                <td><input type="text" name="" id= "emp_name" class= "key emp_name" size="15" value="${empList.emp_name}"></td>
		                <td><input type="text" name="" id= "create_id" class= "key create_id" size="15" value="${empList.create_id}"></td>
		                <td><input type="text" size="35" id= "remark" class= "key remark" value="${empList.remark}"></td>
	                </c:forEach>
	            </tr> 
	            
        	</table>        
        </c:if>
        <c:if test= " ${EmpList == null && listCount.size() == 0 }">
        	<p>등록된 관리자가 없습니다</p>
        </c:if>
    </form>   
    <script>
	//=======
	//행 추가
	//=======
	let rowLenght;
  		let rowValue;
  		let fg= true;
  		let srch_name= $('.addInput #emp_name');
  		
  		//추가 버튼 이벤트 헨들러
    function addClick(){    
      		if(fg){                
          		addData();
          		removeValue();                
      		}else{
          		alert('내용을 저장하고 추가하십시오');
      		}
      		//location.reload();	
	}
  		
    //이전 행 복제
  		function addData(){
      		$('.emp_list .emp_info').eq(0).clone().appendTo('.emp_list');            
      		rowLenght= $('.emp_info').length;        
  		}
    //복제된 행 데이터 리셋
  		function removeValue(){
     		if(rowLenght > 1 && rowValue == undefined){
         		$('.emp_list .emp_info:last-child').addClass('addInput'); 
         		$('.addInput .key').val('');
         		rowValue= $('.addInput .key').val();
         		fg= false;
      		}
     		$('.addInput #emp_name').attr('name', 'emp_name');
     		$('.addInput #create_id').attr('name', 'create_id');
     		$('.addInput #remark').attr('name', 'remark');
  		} 
  		
  		//===========
  		//submit 체크
  		//===========
		function checkForm(){
  			let srch_name= $('.addInput #emp_name');
			let create_id= $('.addInput #create_id');
		
			//===========
  			//필수값 체크
  			//===========
  			if(rowValue == undefined){   				//행추가 하지 않고 저장버튼을 눌렀을 경우
  				alert('검색점을 추가 후 저장하세요.');   				
  				return false;   				
  			}else if(emp_name.val() == ''){			//검색점을 입력하지 않고 저장했을 경우
  				alert('검색점은 필수값입니다.');
  				return false;
  			}else if(create_id.val() == ''){			//등록인을 입력하지 않고 저장했을 경우
  				alert('등록인은 필수값입니다.');
  				//location.reload();
  				return false;
  			}
  			alert('등록되었습니다.');
  			return true;   				
  		}  		
  		
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