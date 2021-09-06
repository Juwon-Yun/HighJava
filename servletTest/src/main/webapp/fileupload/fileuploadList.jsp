<%@page import="kr.or.ddit.fileupload.UploadFileVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

	String memId = (String)request.getAttribute("memId");
	List<UploadFileVO> fileList = (List<UploadFileVO>)request.getAttribute("uploadFileList");	
	
	

%>

<%if( memId != null ){ %>
	<h2><%=memId %>님이 방금 업로드한 파일 목록</h2>
<%}else{ %>
	<h2>전체 업로드 파일 목록</h2>
<%} %>

<table border="1">
	<tr>
		<td>파일 이름</td>
		<td>파일 크기</td>
		<td>업로드 상태</td>
		<td>비          고</td>
	</tr>
	<%
		if ( fileList !=null && fileList.size() > 0 ){
			for(UploadFileVO fvo : fileList){
%>
	<tr>
		<td><%=fvo.getFileName() %></td>
		<td><%=fvo.getFileSize() %>KB</td>
		<td><%=fvo.getUploadStatus() %></td>
		<td>-</td>
	</tr>
<%
			}
		}
%>
	
	
</table>

<br><hr><br>
<a href="<%=request.getContextPath()%>/fileupload/fileupload.jsp">파일 업로드 문서로 가기</a>



</body>
</html>