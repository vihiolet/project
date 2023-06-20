<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>사원등록</title>
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" href="style/admin_emp.css">
    <link rel="stylesheet" href="style/head.css">
    <link rel="stylesheet" href="style/common.css">
</head>
<body>
    <jsp:include page="./../include/admin_header.jsp"></jsp:include>	<!--헤더-->
    <jsp:include page="./../include/left_menu.jsp"></jsp:include>	<!--왼쪽 메뉴-->
    <form action="adminEmpReg.em" method="post" name= "regEmp" onsubmit= "return checkForm()">
        <div>
            <input type="button"  class="add" value="추가" onclick= "addClick()">
            <input type="submit" value="저장" id='save_btn'>            
            <input type="button" value="삭제" id= "delete_btn">
        </div>
        <table class="emp_list">
            
        </table>
    </form>    
</body>
</html>