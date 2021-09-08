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
		$('#btn1').on('click', function(){
		$.ajax({
			url : '<%=request.getContextPath()%>/addMymemberServlet.do',
			data : {id : $('#id').val()}, 
			success:function(res){
				let code = "";
				console.log(res);
				if( res == "1" ){
					code += "이미 등록된 회원입니다.";
				}else{
					code += "회원가입 가능한 아이디입니다.";
				}
			$('#table1').append(code);
			},
			error:function(param){
				alert(param.status);
			},
			dataType : 'json' 
		});//ajax
		});
		
<%
	String id = (String)request.getAttribute("id");
	String count = (String)request.getAttribute("count");
	
%>
		$('#btn2').on('click',function(){
			let count = <%=count%>;
			console.log(count);
			let res = "";
			if( !count == "1"){
				res += "회원 정보 입력 실패";
			}else{
				res += "회원 정보 입력 완료";
			}
			$('#table1').append(res);
		});
	});//function
</script>
</head>
<body>
	<form action="<%=request.getContextPath()%>/addMymemberServlet.do" method="post">
		<h2>회원 정보 입력 폼</h2>
		<table border="1" id="table1">
			<tr>
				<td>회원ID</td>
				<td> <input type="text" name="id" id="id"> <input type="button" value="중복확인" id="btn1"> </td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td> <input type="text" name="pass"> </td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td> <input type="text" name="pass2"> </td>
			</tr>
			<tr>
				<td>회원이름</td>
				<td> <input type="text" name="name"> </td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td> <input type="text" name="tel"> </td>
			</tr>
			<tr>
				<td>회원주소</td>
				<td> <input type="text" name="addr"> </td>
			</tr>
			<tr >
				<td colspan="3"> <input type="submit" name="save" value="저장" id="btn2">
				        <input type="button" name="cancel" value="취소">
				        <input type="button" name="back" value="회원목록"> </td>
			</tr>
		</table>
	</form>
</body>
</html>