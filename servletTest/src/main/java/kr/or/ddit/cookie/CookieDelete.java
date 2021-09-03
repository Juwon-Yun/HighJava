package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieDelete.do")
public class CookieDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 쿠키 정보 삭제하기 -> 예) gender쿠키 삭제하기
		// 저장된 쿠키 중에서 삭제할 쿠키를 찾아서 이 쿠키의 남은 수명을 0으로 설정한 후에 다시 저장하는 방법을 사용한다.
		// => setMaxAge( )메소드 사용
		Cookie [] cookieArr = request.getCookies();
		out.println("<!DOCTYPE html>");
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>Cookie 삭제</title></head>");
		out.println("<body>");
		out.println("<h2>저장된 Cookie 데이터 삭제하기</h2>");
		
		if ( cookieArr == null || cookieArr.length == 0 ) {
			out.println("<h2>저장된 쿠키가 하나도 없습니다</h2>");
		}else {
			// 쿠키배열에서 삭제할 쿠키를 찾는다
			for (Cookie cookie : cookieArr) {
				// 쿠키변수 구하기
				String name = cookie.getName();
				if ( "gender".equals(name) ) {
					// 찾은 쿠키의 수명을 0으로 설정한다.
					cookie.setMaxAge(0);
					
					// 이 쿠키를 다시 저장한다.
					response.addCookie(cookie);
				}
			}
			out.println("쿠키 삭제 작업 완료<br><br>");
		}
		out.println("<a href='"+request.getContextPath()+ "/02/cookieTest.jsp'>시작문서로 이동하기</a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
