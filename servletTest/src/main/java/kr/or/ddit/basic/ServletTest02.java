package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 	이 예제는 어노테이션을 이용해서 Servlet을 설정하여 처리하는 예제이다.
 	사용할 어노테이션은 @WebServlet이고 이것은 Servlet버전 3.0이상에서 
 	사용할 수 있다.
 	
 	@WebServlet어노테이션의 속성들
 	1. name : 서블릿의 이름을 설정한다. (기본값 : 빈문자열("") )
 	2. urlPatterns : 서블릿의 URL패턴의 목록을 설정한다.( 기본값 : 빈배열({}) )
 		예) urlPatterns="/url1" 또는 urlPatterns={"/url1"} => 지정할 패턴이 1개일 경우
 		예) urlPatterns={"/url1", "/url2", ...} => 지정할 패턴이 여러개일 경우
 	3. value : urlPatterns와 동일한 기능을 한다.
 	4. description : 주석(설명글)을 설정한다.
 	
 	
 */

@WebServlet( description = "어노테이션을 이용한 서블릿 설정 예제", urlPatterns = {"/servlet02Test.do"} )
public class ServletTest02 extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// 방법2 : Print( ) 메소드 또는 Println()메소드 이용하기
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='utf-8'>");
		out.println("<title>두번째 Servlet 연습</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1 style='text-align: center;'>");
		out.println("안녕하세요, 두번째 Servlet프로그램입니다.<br>");
		out.println("어노테이션을 이용한 서블릿 설정 예제입니다.<br>");
		out.println("로컬이름 : " + request.getLocalName()+"<br>");
		out.println("서버이름 : " + request.getServerName()+"<br>");
		out.println("Servlet 경로 : " + request.getServletPath()+"<br>");
		out.println("Served at : " + request.getContextPath());
		out.println("</h1");
		out.println("</body>");
		out.println("</html>");
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	
	
	
}









