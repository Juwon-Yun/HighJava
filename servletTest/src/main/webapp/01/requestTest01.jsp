<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	//jsp에서 받은 데이터를 UTF-8로 설정한다
    	request.setCharacterEncoding("UTF-8");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>request 연습</title>
</head>
<body>
	<h2>Request연습 Form</h2>
	<hr>
																												<!-- 개별로 설정할때 -->
	<form name="testForm" action="/servletTest/RequestTest01.do" method="post" accept-charset="utf-8">
		<table border = "1">
			<tr>
				<td>이 름</td>
				<td><input type="text" size="10" name="username" > </td>
			</tr>
			<tr>
				<td>직 업</td>
				<td>
					<select name="job">
						<option value="무직">무직</option>
						<option value="회사원">회사원</option>
						<option value="전문직">전문직</option>
						<option value="학생">학생</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>취 미</td>
				<td>
					<input type="checkbox" name="hobby" value="여행">여행
					<input type="checkbox" name="hobby" value="게임">게임
					<input type="checkbox" name="hobby" value="배드민턴">배드민턴
					<input type="checkbox" name="hobby" value="테니스">테니스
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<input type="submit" value="확인">			
					<input type="reset" value="취소">			
				</td>
			</tr>
		</table>
	</form>
</body>
</html>