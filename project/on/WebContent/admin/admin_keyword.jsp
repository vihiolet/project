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
		
		String id= (String)session.getAttribute("id");		
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>검색점 조회</title>
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" href="style/admin_keywrod.css">
    <link rel="stylesheet" href="style/head.css">
    <link rel="stylesheet" href="style/common.css">
</head>
<body>
	<jsp:include page="./../include/admin_header.jsp"></jsp:include>	<!--헤더-->
	<jsp:include page="./../include/left_menu.jsp"></jsp:include>	<!--왼쪽 메뉴-->
<div id= "key_list">
	<div class="btn">
	    	<a href=adminKey.ke class= "list">검색점 조회</a>   
	 </div>
   	<form action="adminKeywordReg.ke" method="post" name= "regKeyword" onsubmit= "return checkForm()">
      <div>
            <input type="button"  class="add rebtn" value="추가" onclick= "addClick()">
            <input type="button" class="rebtn" value="수정" id='modify_btn'>
            <input type="submit" class="reg rebtn" value="저장" id='save_btn'>            
            <input type="button" class="rebtn"value="삭제" id= "delete_btn">
      </div>
      <table class="keyword_list">
      	<tr class="keyword_tit">
                <td><input type="checkbox" name="allcheck" style="margin-left: 10px;" onClick='allCheck()'></td>                
                <td>검색점</td>
                <td>등록인</td>
                <!-- <td>사용여부</td> -->
                <td>비고</td>
            </tr>

            <%if(keywordList != null && listCount > 0){%>
           
        	<%for(int i=0; i < keywordList.size(); i++) {%>    
            	
            <tr class="keyword_info">   				
	                <td><input type="checkbox" name="srch_code" id= "srch_code" class= "key srch_code" value="<%=keywordList.get(i).getSrch_code() %>" style="margin-left: 10px;"></td>
	                <td>	           
		                <input type="hidden" value="<%=keywordList.get(i).getSrch_code() %>">
		                <input type="text" name="" id= "srch_name" class= "key srch_name" size="15" value="<%=keywordList.get(i).getSrch_name() %>">                	
	                </td>
	                <td>                	
	                	<input type="text" name="" id= "create_id" class= "key create_id" size="15" value="<%=keywordList.get(i).getCreate_id() %>" readonly>
	                </td>
					<td>   
						<a href="adminKeywordModiForm.ke?srch_code=<%=keywordList.get(i).getSrch_code() %>" class= "mvModiForm">             	
	                		<input type="text" name="" id= "remark" class= "key remark" size="30" value="<%=keywordList.get(i).getRemark() %>">
	                	</a>
	                </td>  		
            </tr>  
            <%} %>
      
	       	<%}else{%>
	        	<tr class="keyword_info">   				
		                <td><input type="checkbox" name="srch_code" id= "srch_code"  style="margin-left: 10px;"></td>
		                <td>	           
			                <input type="hidden" value="">
			                <input type="text" name="srch_name" id= "srch_name" class="firstkey" size="15" >                	
		                </td>
		                <td>                	
		                	<input type="text" name="create_id" id= "create_id" class= "" size="15" value="<%=id %>" readonly>
		                </td>
						<td>                	
	                		<input type="text" name="remark" id= "remark" class= "" size="35" value="" size= "40" placeholder="">
		                </td>  		
	            </tr>
	        <%} %>
         </table>
         <%if(keywordList != null && listCount > 0){%>
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
       <%}else{%>
       		<p class="emptyKey">최초 검색점을 등록하세요.</p>
       <%} %>      
    </form> 
    </div> 
  <script>
	 
	//=======
	//행 추가
	//=======
	let rowLenght;
	let rowValue;
	let fg= true;
	let srch_name= $('.addInput #srch_name');
  		
  	//추가 버튼 이벤트 헨들러
    function addClick(){   
  		let addBtn= '<%= listCount%>';
   		if(fg && addBtn > 0){                
       		addData();
       		removeValue();  
       		document.querySelector('.addInput').querySelector('.srch_code').addEventListener('click', function(e) {
       		     e.preventDefault();                                                                                             
       		 });
       		document.querySelector('.addInput').querySelector('.mvModiForm').addEventListener('click', function(e) {
      		     e.preventDefault();                                                                                             
      		 });
   		}else{
       		alert('내용을 저장하고 추가하십시오');
   		}	
	}
  		
    //이전 행 복제
  		function addData(){
      		$('.keyword_list .keyword_info').eq(0).clone().appendTo('.keyword_list');            
      		rowLenght= $('.keyword_info').length;  
  		}
    //복제된 행 데이터 리셋
  		function removeValue(){   
  			let id= '<%= id%>';
  			
     		if(rowLenght > 1 && rowValue == undefined){
         		$('.keyword_list .keyword_info:last-child').addClass('addInput'); 
         		$('.addInput .key').val('');  
         		$('.addInput').find('#create_id').val(id);
         		rowValue= $('.addInput .key').val();
         		fg= false;
      		}
     		$('.addInput #srch_name').attr('name', 'srch_name');
     		$('.addInput #create_id').attr('name', 'create_id');
     		$('.addInput #remark').attr('name', 'remark');
     	
  		} 
  		
  		//===========
  		//submit 체크
  		//===========
		function checkForm(){
  			let srch_name= $('.addInput #srch_name');
			let create_id= $('.addInput #create_id');
			
			let srch_nameFirst= $('.firstkey');
		
			//===========
  			//필수값 체크
  			//===========
  			if(rowLenght > 1 && rowValue == undefined){  //행추가 하지 않고 저장버튼을 눌렀을 경우
  				alert('검색점을 추가 후 저장하세요.');   				
  				return false;   				
  			}else if(srch_name.val() == '' || srch_nameFirst.val() == ''){			//검색점을 입력하지 않고 저장했을 경우
  				alert('검색점은 필수값입니다.');
  				return false;  			
  			}
  			alert('등록되었습니다.');
  			return true;   				
  		}  		
  		
  		//다중 체크
  		function allCheck(){
  			let ac= document.regKeyword.allcheck;
  			let sc= document.regKeyword.srch_code;
  			
  			//검색점 1개일 때 체크
  			if(ac.checked == true){
  				if(sc.length == undefined){
  					sc.checked = true;
  				}
  			}else{
  				if(sc.length == undefined){
  					sc.checked = false;
  				}
  			}
  			
  			//검색점이 두 개 이상일 때 체크
  			if(ac.checked == true ){
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
  			const srch_codeArr= [];
  			
  			let srch_code= $('input[type=checkbox][name=srch_code]:checked');
  			
  			//체크된 행을 배열에 push
  			$(srch_code).each(function(){
  				srch_codeArr.push($(this).val());
  			})  			
  			
  			let valueCk= $('.firstkey').val();
  			
  			if(srch_codeArr != 0){
  				$.ajax({
  	   				type: "POST",
  	   				url: "adminKeywordDel.ke",
  	   				data: { "srch_codeArr" : srch_codeArr},
  	   				traditional: true,				//전달 데이터는 배열
  	   				success: function(data){	
  	   					if(valueCk != ''){
	  	   					alert('삭제되었습니다');
	  	   					location.reload();			//새로고침
  	   					}  	   					
  	   				},
  	   				error: function(data) {
  	   					alert('삭제 오류');
  	   					location.reload();
  	   				}
  	   			})
  			}else{
  				alert('삭제할 검색점을 선택하세요');
  			}   			
  			//location.reload();	//여기에 쓰면 success의 alert나 console.log가 실행 안 된다 success의 마지막에 써야 된다
  		})
  		
  		//수정
  		$(document).ready(function(){  	
  			let srch_name= null;
  			let srch_code= null;
  				
  			//변경된 검색점 정보 변수에 담기
  			$('.srch_name').change(function(){
  				srch_name= $(this).val();
  				srch_code= $(this).prev().val();
  				$('input').not(this).not('.rebtn').css({'background':'#eee', 'border':'2px solid #eee'}).attr('readonly', true);
  				
  				//저장 버튼 통제
  				document.querySelector('.reg').addEventListener('click', function(e) {
  	      		     e.preventDefault();                                  
  	      		     alert('수정버튼을 누르세요.');
  	      		 });
  			})
  	  		
  			$('#modify_btn').on("click", function(e){
	  			if(srch_code != null){
	  				$.ajax({
	  	   				type: "POST",
	  	   				url: "adminKeywordModi.ke",
	  	   				data: { 
	  	   						"srch_code" : srch_code,
	  	   						"srch_name" : srch_name
	  	   						},
	  	   				success: function(data){		
	  	   					alert('수정되었습니다.');
	  	   					location.reload();			//새로고침
	  	   				},
	  	   				error: function(data) {
	  	   					alert('수정 오류');
	  	   					location.reload();
	  	   				}
	 	  	   		})
	  			}	 							
  			})
  	  	})	
  </script>
</body>
</html>
