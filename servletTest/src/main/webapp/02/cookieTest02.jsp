<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	div{
		border: 1px solid black;
		padding: 30px;
	}
</style>
<body>
	<div >
	<hr><br>
		<a href="<%=request.getContextPath()%>/cookieCountServlet.do">Cookie Count 증가 하기</a><br><br>
	<hr>
		<a href="<%=request.getContextPath()%>/cookieCountDelServlet.do">Cookie Count 초기화 하기</a><br><br>
	<hr>
	</div>
</body>
</html>