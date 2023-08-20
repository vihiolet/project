<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "vo.AdminProBean"%>
<%@ page import= "java.util.*" %>   

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>관리자 데시보드</title>
</head>
<script src="https://kit.fontawesome.com/3e4d6b2bc7.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@4.3.0/dist/chart.umd.min.js"></script>
<link rel="stylesheet" href="style/common.css">
<link rel="stylesheet" href="style/admin.css">
<link rel="stylesheet" href="style/head.css">
<body>
	<jsp:include page="./include/admin_header.jsp"></jsp:include>	<!--헤더-->
	<jsp:include page="./include/left_menu.jsp"></jsp:include>	<!--왼쪽 메뉴-->	
	<div class="admin_main">
		<div class="board">
			<ul>
				<li>가입자 수</li>
				<li class="myVote">${userCount}</span>개</li>
			</ul>
			<ul>
				<li>후기 개수</li>
				<li class="myVote">${reviewCount}</span>개</li>
			</ul>
			<ul>
				<li>검색점 개수</li>
				<li class="myVote">${keyCount}</span>개</li>
			</ul>
        </div>
		<p class="tit">메뉴별 상품 현황</p>
		<canvas id="myChart" ></canvas>
	</div>	

	<script>
	
	var chartArea = document.getElementById('myChart').getContext('2d');
	//차트 생성 
	var myChart = new Chart(chartArea, {
	    type: 'bar',
	    data: {
	        labels: ['음식', '옷', '장신구', '미용', '생활'],
	        //차트에 표시할 데이터
	        datasets: [{
	            label: '상품 개수',
	            data: [
	            	'${menu1Count}', 
	            	'${menu2Count}', 
	            	'${menu3Count}', 
	            	'${menu4Count}', 
	            	'${menu5Count}'
	            	],
	            backgroundColor: 'rgba(255, 99, 132, 0.2)',
	            borderColor: 'rgba(255, 99, 132, 1)',
	            borderWidth: 1
	        }]
	    },
	    options: {
	        scales: {
	            y: {
	                beginAtZero: true
	            }
	        }
	    }
	});
	</script>
</body>
</html>