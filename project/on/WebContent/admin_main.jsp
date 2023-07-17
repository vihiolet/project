<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://kit.fontawesome.com/3e4d6b2bc7.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@4.3.0/dist/chart.umd.min.js"></script>
<link rel="stylesheet" href="style/common.css">
<link rel="stylesheet" href="style/admin_pro.css">
<link rel="stylesheet" href="style/head.css">
<body>
	<jsp:include page="./include/admin_header.jsp"></jsp:include>	<!--헤더-->
	<jsp:include page="./include/left_menu.jsp"></jsp:include>	<!--왼쪽 메뉴-->
	
	<canvas id="myChart" style="width:100%; max-width:700px" ></canvas>

	<script>
	// 차트를 그럴 영역을 dom요소로 가져온다.
	var chartArea = document.getElementById('myChart').getContext('2d');
	// 차트를 생성한다. 
	var myChart = new Chart(chartArea, {
	    // ①차트의 종류(String)
	    type: 'bar',
	    // ②차트의 데이터(Object)
	    data: {
	        // ③x축에 들어갈 이름들(Array)
	        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
	        // ④실제 차트에 표시할 데이터들(Array), dataset객체들을 담고 있다.
	        datasets: [{
	            // ⑤dataset의 이름(String)
	            label: '# of Votes',
	            // ⑥dataset값(Array)
	            data: [12, 19, 3, 5, 2, 3],
	            // ⑦dataset의 배경색(rgba값을 String으로 표현)
	            backgroundColor: 'rgba(255, 99, 132, 0.2)',
	            // ⑧dataset의 선 색(rgba값을 String으로 표현)
	            borderColor: 'rgba(255, 99, 132, 1)',
	            // ⑨dataset의 선 두께(Number)
	            borderWidth: 1
	        }]
	    },
	    // ⑩차트의 설정(Object)
	    options: {
	        // ⑪축에 관한 설정(Object)
	        scales: {
	            // ⑫y축에 대한 설정(Object)
	            y: {
	                // ⑬시작을 0부터 하게끔 설정(최소값이 0보다 크더라도)(boolean)
	                beginAtZero: true
	            }
	        }
	    }
	});
	</script>
</body>
</html>