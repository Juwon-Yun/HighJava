<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet File Upload 연습</title>
</head>
<body>

<h2>File Upload 연습</h2>
<form action="<%=request.getContextPath()%>/fileUpload.do" method ="post" enctype="multipart/form-data">
	ID : <input type="text" name="memid"><br><br>
	
	Upload File1 : <input type="file" name="fileUpload1" multiple><br><br>
	Upload File2 : <input type="file" name="fileUpload2" ><br><br>
	
	<input type="submit" value="데이터 전송">
		
</form>
<br><hr><br>
<a href="">Upload된 전체 파일 목록 보기</a>
</body>
</html>