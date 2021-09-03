package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieCountDelServlet.do")
public class CookieResetCount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		Cookie [] cookieArr = request.getCookies();
		out.println("<!DOCTYPE html>");
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>Cookie 초기화</title></head>");
		out.println("<body>");
		out.println("<h2>count가 초기화 되었습니다</h2>");
		if ( cookieArr == null || cookieArr.length == 0 ) {
			out.println("<h2>저장된 쿠키가 하나도 없습니다</h2>");
		}else {
			// 쿠키배열에서 삭제할 쿠키를 찾는다
			for (Cookie cookie : cookieArr) {
				// 쿠키변수 구하기
				String name = cookie.getName();
				if ( "visited".equals(name) ) {
					// 찾은 쿠키의 수명을 0으로 설정한다.
					cookie.setMaxAge(0);
					
					// 이 쿠키를 다시 저장한다.
					response.addCookie(cookie);
				}
			}
			
		}
		out.println("<a href='"+request.getContextPath()+ "/02/cookieTest02.jsp'>시작문서로 이동하기</a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
