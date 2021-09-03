<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cookie 연습</title>
</head>
<body>
	<h2>Cookie 연습하기</h2><hr>
	<a href="<%=request.getContextPath()%>/cookieAdd.do">Cookie 정보 저장하기</a><br><br>
	<hr>
	<a href="<%=request.getContextPath()%>/cookieRead.do">저장된 Cookie 정보 확인하기</a><br><br>
	<hr>
	<a href="<%=request.getContextPath()%>/cookieDelete.do">저장된 Cookie 정보 삭제하기</a><br><br>
	<hr>
</body>
</html>