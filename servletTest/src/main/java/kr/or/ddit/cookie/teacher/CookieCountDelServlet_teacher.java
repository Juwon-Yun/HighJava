package kr.or.ddit.cookie.teacher;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieCountDelServlet_teacher.do")
public class CookieCountDelServlet_teacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		Cookie [] cookieArr = request.getCookies();
		if( cookieArr != null ) {
			for(Cookie cookie : cookieArr) {
				String name = cookie.getName();
				if("count".equals(name)) {
					// 유지시간을 0으로 설정한 후 다시 저장
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		out.println("<!DOCTYPE html>");
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>쿠키 count연습</title></head>");
		out.println("<body>");
		out.println("<h2>count가 초기화되었습니다</h2>");
		out.println("<a href='"+request.getContextPath()+"/02/teacher/CookieTest02.jsp'>처음화면 돌아가기</a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
