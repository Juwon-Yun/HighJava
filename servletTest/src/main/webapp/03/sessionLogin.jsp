<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style >
	table {
	width : 25%;
	height: 100px;
}
#tr3{
	text-align: center;
}
</style>
</head>
<body>
<%
	HttpSession getsession = request.getSession();
%>
	<form action="<%=request.getContextPath()%>/sessionLogin.do" method="post" >
	<table border="1">
		<tr>
			<td>ID:  </td>
			<td><input type="text" size="20" value="<%=(String)getsession.getAttribute("id") %>" name="id" class="id"  placeholder="ID를 입력하세요."></td>
		</tr>
		<tr>
			<td>PASS:  </td>
			<td><input type="text" size="20"  name="pass" class="pass" placeholder="PASSWORD를 입력하세요."></td>
		</tr>
		<tr id="tr3">
			<td colspan="2"> <input type="submit" value="Login"></td>
		</tr>
	</table>
	</form>
</body>
</html>