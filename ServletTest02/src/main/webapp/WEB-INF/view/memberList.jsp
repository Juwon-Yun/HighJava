<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.MymemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#addBtn").on("click", function(){
			location.href = "<%=request.getContextPath()%>/member/memberInsert.do"	
		});
	})
</script>
</head>
<%
	// WEB-INF 안에있는 jsp는 URL을 입력해도 직접 접속할수 없다(요청신호가 닿지않는다)
	// forward방식으로 접근할 수 있다.
	List<MymemberVO> memList = (List<MymemberVO>)request.getAttribute("memberList");
%>
<body>
<h2>회원 목록 보기</h2>
<table border="1">
	<thead>
		<tr >
			<td colspan="5" style ="text-align: right; padding-right: 3px"> 
				<input type="button" value="회원추가" id="addBtn">
			 </td>
		</tr>
		<tr>
			<td>ID</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>전화</td>
			<td>주소</td>
		</tr>
	</thead>
	<tbody>
		<%for(MymemberVO mvo : memList){ %>
		<tr>
																															<!-- 중요함 -->
			<td><a href="<%=request.getContextPath()%>/member/memberView.do?mem_id=<%=mvo.getMem_id()%>"><%=mvo.getMem_id() %></a></td>
			<td><%=mvo.getMem_pass() %></td>
			<td><%=mvo.getMem_name() %></td>
			<td><%=mvo.getMem_tel() %></td>
			<td><%=mvo.getMem_addr() %></td>
		</tr>
		
		<%} %>
	</tbody>
</table>
</body>
</html>