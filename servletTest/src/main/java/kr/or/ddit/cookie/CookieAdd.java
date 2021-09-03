package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieAdd.do")
public class CookieAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Cookie 정보 저장하는 방법 ( 쿠키 => 컴퓨터에 저장, 세션 => 서버에 저장)
		// 1. Cookie 객체를 생성한다. => '쿠키변수'와 '쿠키값'을 문자열로 지정한다.
		// 형식) Cookie cookie변수 = new Cookie("쿠키변수", "쿠키값");
		//			=> 쿠키값으로 한글을 사용할 경우에는 URLEncoder.encode( )메소드로 인코딩한 값을 저장한다.
		
		// 하나의 쿠키는 4kB( 4096 byte )까지 저장 가능
		// 하나의 도메인당 20개, 총 300개까지 저장 가능
		
		//		Cookie nameCookie = new Cookie("name", "홍길동");
		Cookie nameCookie = new Cookie("name", URLEncoder.encode( "홍길동", "utf-8" ) );

		//		Cookie ageCookie = new Cookie("name", "26");
		
																// 데이터값에 변수를 넣고 싶을때 String.valueOf( ) 메소드 사용
		Cookie ageCookie = new Cookie("age", String.valueOf(26));
		Cookie genderCookie = new Cookie("gender", "Male");
		
		/*
		   2. Cookie 속성 설정
		   - 쿠키변수.setPath("적용경로"); 
					=> 지정한 경로와 그 하위 경로에서 사용가능하다
		 			=> 생략하면 쿠키를 저장할 당시의 경로가 설정된다.
		
		   - 쿠키변수.setMaxAge(유지시간);
					=> 단위 : 초 ( 값이 -1이면 웹브라우저가 종료될 때 삭제되고, 값이 0이면 즉시 삭제된다. )
			
			- 쿠키변수.setDomain("적용도메인명");
					=> 예) ".ddit.or.kr" -> www.ddit.or.kr, mail.ddit.or.kr (.ddit.or.kr을 도메인 뒤에 붙여준다)
			
			- 쿠키변수.setSecure(보안여부);
					=> ( true : 보안 적용, false : 보안 미적용(기본값) )
		*/
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 3. response 객체를 이용해서 쿠키를 웹브라우저로 보내면 웹브라우저가 이 쿠키를 받아서 저장한다.
		// 형식) response.addCookie(1번에서 만든 쿠키객체);
		response.addCookie(nameCookie);
		response.addCookie(ageCookie);
		response.addCookie(genderCookie);
		
		out.println("<!DOCTYPE html>");
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>Cookie 연습</title></head>");
		out.println("<body>");
		out.println("<h2>Cookie 데이터가 저장되었습니다.</h2><br><br>");
		
		out.println("<a href='"+request.getContextPath()+ "/02/cookieTest.jsp'>시작문서로 이동하기</a>");
		
		out.println("</body></html>");
 	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
