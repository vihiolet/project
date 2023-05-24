<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>검색점</title>
    <link rel="stylesheet" href="style/admin_keywrod.css">
    <link rel="stylesheet" href="style/head.css">
    <link rel="stylesheet" href="style/common.css">
</head>
<body>
	<jsp:include page="../admin_header.jsp"></jsp:include>	<!--헤더-->
	<jsp:include page="../left_menu.jsp"></jsp:include>	<!--왼쪽 메뉴-->
   	<form action="adminKeywordReg.ad">
      <div>
            <input type="button" class="add" value="추가">
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
            <tr class="keyword_info">
                <td><input type="checkbox" name="" style="margin-left: 10px;"></td>
                <td><input type="text" name="srch_name" class= "key" size="15" value="친환경"></td>
                <td><input type="text" name="create_id" class= "key" size="15" value="0001"></td>
                <td>
                    <select name="use_yn" id="">
                        <option value="1">사용</option>
                        <option value="2">미사용</option>
                    </select>
                </td>
                <td><input type="text" size="35" value="탄소중립은 탄소중립지향 검색점 이용"></td>
            </tr>       
        </table>        
   </form>    
   <script>
   		let param= {
   			srch_name= $(".srch_name").val() ,
   			create_id= $(".create_id").val()
   		}
   		//ajax 통신
   		$.ajax({
   			type: "POST" ,
   			url: "adminKeywordReg.ad" ,
   			data: param ,
   			success: function(res){
   				alert("hi");
   			} ,
   			error: function(XMLHttpRequest, textStatus, errorThrown){
   				alert("다시!")
   			}
   		})
   </script>
</body>
</html>