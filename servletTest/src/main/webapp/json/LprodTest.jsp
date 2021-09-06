<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#btn").on("click", function(){
			//비동기방식
			$.ajax({
				url : "<%=request.getContextPath() %>/jsonLprodServlet.do",
				type : "post",
				success : function(data){
					/* let str = "";
					console.log(data);
					// data => [ {"lprod_id":1,"lprod_gu":"P101","lprod_nm":"컴퓨터제품"},
					//	... {"lprod_id" : ... , "lprod_gu" : ... , "lprod_nm " : ...} ] 
					$.each(data, function(i, v){
						str += i + "번째 자료 입니다<br>";
						str += "LPROD_ID : " + v.lprod_id + "<br>";
						str += "LPROD_GU : " + v.lprod_gu + "<br>";
						str += "LRPOD_NM : " + v.lprod_nm + "<br><hr><br>";
					});// .each
					$('#rst').html(str);				 */
					
					//선생님
					let htmlCode = "<table border = '1' style='text-align: right'>";
					htmlCode += "<tr><td>LPROD_ID</td><td>LPROD_GU</td><td>LPROD_NM</td></tr>";
					$.each(data, function(i, v){
						htmlCode += "<tr><td>" + v.lprod_id + "</td>";
						htmlCode += "<td>" + v.lprod_gu + "</td>";
						htmlCode += "<td>" + v.lprod_nm + "</td></tr>";
					});
						htmlCode += "</table>";
						$("#rst").html(htmlCode);
				},
				dataType : "json"
				
			});	//ajax
		});// btn 
		
		
		// 동기방식
		$("#btn2").on("click", function(){
			location.href = "<%=request.getContextPath()%>/lprod/lprodList2.do"
		});//btn2
	});
</script>
</head>
<body>
	<input type="button"  id="btn" value="확인(ajax)">
	<input type="button"  id="btn2" value="확인">
	<hr>
	<div id="rst"></div>
</body>
</html>