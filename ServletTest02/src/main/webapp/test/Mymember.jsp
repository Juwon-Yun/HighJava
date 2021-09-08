<%@page import="kr.or.ddit.vo.MymemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록 보기</title>
<script type="text/javascript">
</script>
<style type="text/css">
#tr2{
	font-size: 20px;
}
</style>
</head>
<body>
<%
	List<MymemberVO> list = (List<MymemberVO>)request.getAttribute("list");
%>
<h2>회원 목록 보기</h2>
<form action="<%=request.getContextPath()%>/test/addMymember.jsp" method="get">
	<table border="1" id="table1">
		<tr>
			<td colspan="5" style="text-align: right;">
				<input type="submit" value="회원추가" >
			</td>
		</tr>
		<tr id="tr2">
			<td>ID</td>
			<td>비밀번호</td>
			<td>이 름</td>
			<td>전 화</td>
			<td>주 소</td>
		</tr>
		<%
	for(MymemberVO mvo : list){
	%>
	<tr>
		<td><a href=""><%=mvo.getMem_id() %></a></td>
		<td><%=mvo.getMem_pass() %></td>
		<td><%=mvo.getMem_name() %></td>
		<td><%=mvo.getMem_tel() %></td>
		<td><%=mvo.getMem_addr() %></td>
	</tr>
	<%	
	}
	%>
	</table>
</form>
</body>
</html>