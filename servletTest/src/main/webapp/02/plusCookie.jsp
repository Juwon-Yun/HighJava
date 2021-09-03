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
Cookie[] cookieArr = request.getCookies();
Cookie cookie = null;
int countNum = 0;
String countStr = "";

if( cookieArr != null ){
	for(int i =0; i < cookieArr.length; i++){
		if( cookieArr[i].getName().equals("visited")){
			cookie = cookieArr[i];
			break;
		}
	}
}
if(cookie != null){
	countNum = Integer.parseInt( cookie.getValue() )+1;
	countStr = Integer.toString( countNum );
	cookie.setValue(countStr);
}else{
	cookie = new Cookie("visited", "1");	
}
response.addCookie(cookie);
%>
<h2>어서오세요. 당신은 <%=cookie.getValue() %>번째 방문입니다</h2>
<a href="<%=request.getContextPath()%>/02/plusCookie.jsp">카운트 증가하기</a>
<a href="<%=request.getContextPath()%>/02/cookieTest02.jsp">시작문서로 이동하기</a>
</body>
</html>