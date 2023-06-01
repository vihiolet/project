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
	<jsp:include page="../admin_header.jsp"></jsp:include>	<!--헤더-->
	<jsp:include page="../left_menu.jsp"></jsp:include>	<!--왼쪽 메뉴-->
   	<form action="adminKeywordReg.ad">
      <div>
            <input type="button" class="add" value="추가" onclick= "addClick()">
            <input type="submit" value="저장">
            <input type="button" value="삭제">
      </div>
      <table class="keyword_list">
           <tr class="keyword_tit">
                <td><input type="checkbox" name="" style="margin-left: 10px;"></td>
                <td>검색점</td>
                <td>등록인</td>
                <td>사용여부</td>
                <td>비고</td>
            </tr>
            <%
            	if(keywordList != null && listCount > 0){
            		for(int i= 0; i<keywordList.size(); i++){
            %>
            <tr class="keyword_info">
                <td><input type="checkbox" name="" style="margin-left: 10px;"></td>
                <td><input type="text" name="srch_name" class= "key" size="15" value="<%=keywordList.get(i).getSrch_name() %>"></td>
                <td><input type="text" name="create_id" class= "key" size="15" value="<%=keywordList.get(i).getCreate_id() %>"></td>
                <td>
                    <select name="use_yn" id="">
                        <option value="1">사용</option>
                        <option value="2">미사용</option>
                    </select>
                </td>
                <td><input type="text" size="35" value="탄소중립은 탄소중립지향 검색점 이용"></td>
            </tr>  
            <%
            		}
            %> 
            <%
            	}else{
            %>  
            <p>등록된 검색점이 없습니다</p>  
            <%
            	}
            %>
        </table>        
   </form>    
   <script>
 		//=======
   		//ajax 통신
   		//=======
   		let param= {
   			srch_name : $(".srch_name").val() ,
   			create_id : $(".create_id").val()
   		}
   		
   		$.ajax({
   			type: "POST" ,
   			url: "adminKeywordReg.ad" ,
   			data: param ,
   			success: function(res){
   				//alert("hi?");
   			} ,
   			error: function(XMLHttpRequest, textStatus, errorThrown){
   				alert("다시!");
   			}
   		})
   		function addClick(){
   			$('keyword_list').addClass('keyword_info');
   		}
   		
   		//=======
   		//행 추가
   		//=======
   		let rowLenght;
        let rowValue;
        let fg= true;
   	    function addClick(){    
            if(fg){                
                addData();
                removeValue();                
            }else{
                alert('내용을 저장하고 추가하십시오');
            }
   		}
        function addData(){
            $('.keyword_list .keyword_info').eq(0).clone().appendTo('.keyword_list');            
            rowLenght= $('.keyword_info').length;        
        }
       function removeValue(){
           if(rowLenght > 1 && rowValue == undefined){
               $('.keyword_list .keyword_info:last-child').addClass('addInput'); 
               $('.addInput .key').val('');
               rowValue= $('.addInput .key').val();
               fg= false;
            }
       }    
   </script>
</body>
</html>