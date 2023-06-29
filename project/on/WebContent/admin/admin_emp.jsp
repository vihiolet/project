<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "vo.PageInfo" %>
<%@ page import= "vo.AdminEmpBean" %>
<%@ page import= "java.util.*" %>
<%@ page import= "java.text.SimpleDateFormat" %>

<%
		ArrayList<AdminEmpBean> empList = (ArrayList<AdminEmpBean>)request.getAttribute("EmpList");
		PageInfo pageInfo= (PageInfo)request.getAttribute("pageInfo");
		int listCount= pageInfo.getListCount();
		int nowPage= pageInfo.getPage();
		int maxPage= pageInfo.getMaxPage();
		int startPage= pageInfo.getStartPage();
		int endPage= pageInfo.getEndPage();
%>

<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <title>사원등록</title>
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" href="style/admin_keywrod.css">
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
        <table class="emp_list">
        <%if(empList != null && listCount > 0){%>
           <tr class="emp_tit">
                <td><input type="checkbox" name="" style="margin-left: 10px;"></td>                
                <td>검색점</td>
                <td>등록인</td>
                <td>비고</td>
            </tr>
        	<%for(int i=0; i < empList.size(); i++) {%>    
            	
            <tr class="emp_info">            	
                <td><input type="checkbox" name="emp_code" id= "emp_code" value="<%=empList.get(i).getEmp_code() %>" style="margin-left: 10px;"></td>
                <td><input type="text" name="" id= "emp_name" class= "key emp_name" size="15" value="<%=empList.get(i).getEmp_name() %>"></td>
                <td><input type="text" name="" id= "create_id" class= "key create_id" size="15" value="<%=empList.get(i).getCreate_id() %>"></td>
                <td><input type="text" size="35" id= "remark" class= "key remark" value="<%=empList.get(i).getRemark() %>"></td>
            </tr>  
            <%} %>
        </table>
        <div id="pageList">
        	<%if(nowPage <= 1) { %>
            	[이전]&nbsp;
          	<% }else {%>		<!-- 이전 페이지가 존재하면 [이전] 텍스트에 직전 페이지 링크 -->
            	<a href="adminEmp.emp?page=<%=nowPage - 1 %>">[이전]</a>&nbsp;
          	<% } %>
          	
          	<%for(int a= startPage; a <= endPage; a++){ 
          		if(a == nowPage){%>
          			[<%=a %>]
          		<%}else{ %>
          			<a href="adminEmp.emp?page=<%= a %>">[<%= a %>]</a>&nbsp;
          		<%} %>          			
          	<%} %>
          	
          	<%if(nowPage >= maxPage){ %>
          		[다음]
          	<%}else{ %>
          		<a href="adminKey.ke?page=<%= nowPage + 1 %>">[다음]</a>&nbsp;
          	<%} %>
        </div>
        <% }else{ %>  
            	<p>등록된 관리자가 없습니다</p>  
        <% } %>  
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