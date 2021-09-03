<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<hr>
	<h2>Request연습 Form(숫자 입력은 정수형으로 입력하세요.)</h2>
	<hr>
	<form name="testForm" action="<%=request.getContextPath()    %>/RequestTest02.do" method="get">
	<input type="text" size="10" name="first" >
	<select name="math">
		<option value="+">+</option>
		<option value="-">-</option>
		<option value="*">*</option>
		<option value="/">/</option>
		<option value="%">%</option>
	</select>
	<input type="text" size="10" name="second" >
	<input type="submit" name ="btn" value="확인">
	</form>
</body>
</html>