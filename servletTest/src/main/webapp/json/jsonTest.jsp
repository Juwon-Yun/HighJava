<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		// 문자열
		$("#strBtn").on("click", function(){
			$.ajax({
				url : "<%=request.getContextPath() %>/jsonTestServlet.do",
				type : "post",
				/* choice가 파라매터변수, str은 값 */
				data : "choice=str",
				// 안녕하세요 데이터가 문자열로 data 에 담긴다
				success : function(data){
					$("#result").html(data);
				},
				dataType : "json"
			});
		});
		
		//배열
		$("#arrBtn").on("click", function(){
			$.ajax({
				url : "<%=request.getContextPath() %>/jsonTestServlet.do",
				type : "post",
				data : "choice=arr",
				//data => [100,200,300,400,500]
				success : function(data){
					let str = "";
					$.each(data, function(i, v){
						str += i+"번째 자료 " + v +"<br>" ;
					});
					$("#result").html(str);
				},
				dataType : "json"
			});
		});
		
		// 객체
		$("#objBtn").on("click", function(){
			$.ajax({
				url : "<%=request.getContextPath() %>/jsonTestServlet.do",
				type : "post",
				data : "choice=obj",
				// data => {"num":100,"name":"홍길동"}
				success : function(data){
					let str = "번호 : " + data.num + "<br>";					
					    str += "이름 : " + data.name + "<br>";
					    
					$("#result").html(str);
				},
				dataType : "json"
			});
		});

		// 컬렉션 - List
		$("#listBtn").on("click", function(){
			$.ajax({
				url : "<%=request.getContextPath() %>/jsonTestServlet.do",
				type : "post",
				data : "choice=list",
				// data => [ {"num":1,"name":"이순신"}, 
				//				 {"num":2,"name":"강감찬"},
				//	    		 {"num":3,"name":"변학도"},
				//				 {"num":4,"name":"성춘향"} ]
				success : function(data){
					let str = "";
					// data는 배열, i는 인덱스, v가 객체(실데이터)
					$.each(data, function(i, v){
						str += i + "번째 자료<br>";
						str += "번호 : " + v.num + "<br>";
						str += "이름 : " + v.name + "<hr>";
					});
						$("#result").html(str);
				},
				dataType : "json"
			});
		});
	
		$("#mapBtn").on('click', function(){
			$.ajax({
				url : "<%=request.getContextPath() %>/jsonTestServlet.do",
				type : "post",
				data : "choice=map",
				// data => { "name":"이몽룡",
				//				     "tel":"010-1234-5678",
				//	  			  "addr":"대전시 중구 대흥동" }
				success : function(data){
					let str ="";
						str += "이름 : " + data.name + "<br>";
						str += "전화번호 : " + data.tel + "<br>";
						str += "주소 : " + data.addr + "<br>";
						$("#result").html(str);
				},
				dataType : "json"
			});
		});
	});
	
	
</script>
</head>
<body>
<form action="">
	<input type="button" id="strBtn"  value="문자열">
	<input type="button" id="arrBtn"  value="배 열">
	<input type="button" id="objBtn"  value="객 체">
	<input type="button" id="listBtn"  value="리스트">
	<input type="button" id="mapBtn"  value="map객체">
</form>
<hr>
<h3>응답 결과 보기</h3>
<div id="result"></div>
</body>
</html>