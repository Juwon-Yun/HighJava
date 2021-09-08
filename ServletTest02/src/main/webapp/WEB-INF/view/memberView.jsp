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
		$("#memListBtn").on("click", function(){
			location.href = "<%=request.getContextPath()%>/member/memberList.do";
		});		
		
		//수정버튼 클릭
		$("#updateBtn").on("click",function(){
			$("#memberForm").attr("action", "<%=request.getContextPath()%>/member/memberUpdate.do");
			$("#memberForm").attr("method", "get");
			//form데이터 전송
			$("#memberForm").submit(); 
		});
		
		
		//삭제버튼 클릭
		$("#deleteBtn").on("click",function(){
			$("#memberForm").attr("action", "<%=request.getContextPath()%>/member/memberDelete.do");
			$("#memberForm").attr("method", "post");
			//form데이터 전송
			$("#memberForm").submit(); 
		});
		
	});
</script>
</head>
<body>
<%
	MymemberVO memVo = (MymemberVO)request.getAttribute("memberVo");
%>
	<h2>회원 정보 상세 보기</h2>
	<form id="memberForm">
	<input type="hidden" name="mem_id" value="<%=memVo.getMem_id()%>">
		<table border="1">
			<tr>
				<td>회원ID</td>
				<td><%=memVo.getMem_id() %>	</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><%=memVo.getMem_pass() %></td>
			</tr>
			<tr>
				<td>회원이름</td>
				<td><%=memVo.getMem_name() %></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><%=memVo.getMem_tel() %></td>
			</tr>
			<tr>
				<td>회원주소</td>
				<td><%=memVo.getMem_addr() %></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
				<input type="button"  value="수정"  id="updateBtn">
				<input type="button"  value="삭제"  id="deleteBtn">
				<input type="button"  value="회원목록" id="memListBtn">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>