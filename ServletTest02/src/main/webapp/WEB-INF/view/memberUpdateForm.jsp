<%@page import="kr.or.ddit.vo.MymemberVO"%>
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
	$("#memListBtn").on("click",function(){
		location.href = "<%=request.getContextPath()%>/member/memberList.do"
	});
});
</script>
</head>
<body>
<%
	MymemberVO memVo = (MymemberVO)request.getAttribute("memberVo");
%>
<h2>회원 정보 입력 폼</h2>
	<form action="<%=request.getContextPath()%>/member/memberUpdate.do"
		method="post" id="memberForm">
		<table border="1">
			<tr>
				<td>회원ID</td>
				<td>
				<input type="hidden" name="mem_id" id="mem_id" value="<%=memVo.getMem_id()%>">
					<%=memVo.getMem_id() %>
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="mem_pass" id="mem_pass"></td>
			</tr>
			<tr>
				<td>회원이름</td>
				<td><input type="text" name="mem_name" id="mem_name" value="<%=memVo.getMem_name()%>"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="mem_tel" id="mem_tel" value="<%=memVo.getMem_tel()%>"></td>
			</tr>
			<tr>
				<td>회원주소</td>
				<td><input type="text" name="mem_addr" id="mem_addr" value="<%=memVo.getMem_addr()%>"></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
				<input type="submit"  value="저장" >
				<input type="reset"  value="취소"  >
				<input type="button"  value="회원목록" id="memListBtn">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>