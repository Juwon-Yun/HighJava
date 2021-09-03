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
			$.ajax({
				url : "<%=request.getContextPath() %>/jsonLprodServlet.do",
				type : "post",
				success : function(data){
					let str = "";
					console.log(data);
					// data => [ {"lprod_id":1,"lprod_gu":"P101","lprod_nm":"컴퓨터제품"},
					//	... {"lprod_id" : ... , "lprod_gu" : ... , "lprod_nm " : ...} ] 
					$.each(data, function(i, v){
						str += i + "번째 자료 입니다<br>";
						str += "LPROD_ID : " + v.lprod_id + "<br>";
						str += "LPROD_GU : " + v.lprod_gu + "<br>";
						str += "LRPOD_NM : " + v.lprod_nm + "<br><hr><br>";
					});// .each
					$('#rst').html(str);				
				},
				dataType : "json"
				
			});	//ajax
		});// btn 
		
	});
</script>
</head>
<body>
	<input type="button"  id="btn" value="확인">
	<div id="rst"></div>
</body>
</html>