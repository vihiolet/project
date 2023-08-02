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
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>검색점</title>
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
	    		<a href="adminKeywordModi.ke" >검색점 수정</a>     
	 </div>
   	<form action="adminKeywordReg.ke" method="post" name= "regKeyword" onsubmit= "return checkForm()">
      <div>
            <input type="button"  class="add" value="추가" onclick= "addClick()">
            <input type="button" value="수정" id='modify_btn'>
            <input type="submit" value="저장" id='save_btn'>            
            <input type="button" value="삭제" id= "delete_btn">
      </div>
      <table class="keyword_list">
      	<%if(keywordList != null && listCount > 0){%>
           <tr class="keyword_tit">
                <td><input type="checkbox" name="allcheck" style="margin-left: 10px;" onClick='allCheck()'></td>                
                <td>검색점</td>
                <td>등록인</td>
                <!-- <td>사용여부</td> -->
                <td>비고</td>
            </tr>
        	<%for(int i=0; i < keywordList.size(); i++) {%>    
            	
            <tr class="keyword_info">    
		<a href="adminKeywordModiForm.ke?srch_code=<%=keywordList.get(i).getSrch_code() %>" class= "srch_code">
	                <td><input type="checkbox" name="srch_code" id= "srch_code" class= "key" value="<%=keywordList.get(i).getSrch_code() %>" style="margin-left: 10px;"></td>
	                <td>
	                	<input type="hidden" value="<%=keywordList.get(i).getSrch_code() %>">
	                	<input type="text" name="" id= "srch_name" class= "key srch_name" size="15" value="<%=keywordList.get(i).getSrch_name() %>">
	                </td>
	                <td>                	
	                	<input type="text" name="" id= "create_id" class= "key create_id" size="15" value="<%=keywordList.get(i).getCreate_id() %>" readonly>
	                </td>
			<td>                	
	                	<input type="text" name="" id= "remark" class= "key remark" size="15" value="<%=keywordList.get(i).getRemark() %>" size= "40">
	                </td>
   		</a>
            </tr>  
            <%} %>
      </table> 
    </form> 
    </div> 
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
	//=======
	//행 추가
	//=======
	let rowLenght;
  		let rowValue;
  		let fg= true;
  		let srch_name= $('.addInput #srch_name');
  		
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

      		$('.keyword_list .keyword_info').eq(0).clone().appendTo('.keyword_list');            
      		rowLenght= $('.keyword_info').length;  
      		//$(this).find('.create_id').val(id);
      		
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
		
			//===========
  			//필수값 체크
  			//===========
  			if(rowValue == undefined){   				//행추가 하지 않고 저장버튼을 눌렀을 경우
  				alert('검색점을 추가 후 저장하세요.');   				
  				return false;   				
  			}else if(srch_name.val() == ''){			//검색점을 입력하지 않고 저장했을 경우
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
  			const srch_codeArr= [];
  			
  			let srch_code= $('input[type=checkbox][name=srch_code]:checked');
  			
  			$(srch_code).each(function(){
  				srch_codeArr.push($(this).val());
  			})  			
  			
  			if(srch_code != null){
  				$.ajax({
  	   				type: "POST",
  	   				url: "adminKeywordDel.ke",
  	   				data: { "srch_codeArr" : srch_codeArr},
  	   				traditional: true,				//전달 데이터는 배열
  	   				success: function(data){		//data에 아무것도 없음
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
  		
  		//수정
  		$(document).ready(function(){
  			
  			let srch_name= null;
  			let srch_code= null;
  				
  			//변경된 검색점 정보 변수에 담기
  			$('.srch_name').change(function(){
  				srch_name= $(this).val();
  				srch_code= $(this).prev().val();
  				$('input').not(this).css({'background':'#eee', 'border':'none'}).attr('readonly', true);			
  			})
  	  		
  			$('#modify_btn').on("click", function(e){
  				/*alert('srch_code ' + srch_code);;*/
	  			if(srch_code != null){
	  				$.ajax({
	  	   				type: "POST",
	  	   				url: "adminKeywordModi.ke",
	  	   				data: { 
	  	   						"srch_code" : srch_code,
	  	   						"srch_name" : srch_name
	  	   						},
	  	   				success: function(data){		
	  	   					//alert('수정됨');
	  	   					location.reload();			//새로고침
	  	   				},
	  	   				error: function(data) {
	  	   					//alert('오류!@#$%^');
	  	   					location.reload();
	  	   				}
	 	  	   		})
	  			}	 							
  			})
  	  	})	
  </script>
</body>
</html>
