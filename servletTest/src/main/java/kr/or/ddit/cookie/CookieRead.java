package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieRead.do")
public class CookieRead extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 저장된 쿠키 읽어오기
		// 1. 전체 쿠키 정보를 request객체를 이용해서 가져온다.
		// 					 => 가져온 쿠키 정보들은 배열형태로 되어 있다.
		// 형식) Cookie [] 쿠키배열변수 = request.getCookies();
		Cookie [] cookieArr = request.getCookies();
		
		out.println("<!DOCTYPE html>");
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>Cookie 읽기</title></head>");
		out.println("<body>");
		out.println("<h2>저장된 Cookie 데이터 읽어오기</h2>");
		if ( cookieArr == null || cookieArr.length == 0 ) {
			out.println("<h2>저장된 쿠키가 하나도 없습니다</h2>");
		}else {
			// 2. 쿠키배열에서 해당 쿠키 정보를 가져온다
			for( Cookie cookie : cookieArr) {
				// '쿠키 변수'를 반환한다.
				String name = cookie.getName();
				
				// 쿠키값이 한글일 경우에는 읽어온 값을 URLDecoder.decode( )메소드로 디코딩해서 사용한다.
				// '쿠키 값'을 반환한다.
				String value = URLDecoder.decode( cookie.getValue(), "utf-8");
				//	String value = cookie.getValue();
				
				out.println("쿠키변수 : " + name + "<br>");
				out.println("쿠키값 : " + value + "<hr>");
			}
			out.println("쿠키 출력 끝<br><br>");
		}
		out.println("<a href='"+request.getContextPath()+ "/02/cookieTest.jsp'>시작문서로 이동하기</a>");
		out.println("</body></html>");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
