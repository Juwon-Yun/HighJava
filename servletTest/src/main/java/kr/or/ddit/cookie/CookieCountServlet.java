package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieCountServlet.do")
public class CookieCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>Cookie Count</title></head>");
		out.println("<body>");
		Cookie [] cookies = request.getCookies();
		Cookie visitedCookie = null;
		if( cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if( cookies[i].getName().equals("visited") ) {
					visitedCookie = cookies[i];
					break;
				}
			}
			if( visitedCookie != null ) {
				int count = Integer.parseInt( visitedCookie.getValue()) +1 ;
				out.println("<h2>어서오세요. 당신은 " +count +"번째 방문입니다.</h2>");
				
				visitedCookie.setValue(count +"");
				visitedCookie.setMaxAge(60);
				
				response.addCookie(visitedCookie);
			}
			
			else {
				out.println("<h2>어서오세요. 당신은 1번째 방문입니다.</h2>");
				Cookie newCookie = new Cookie("visited", "1");
				response.addCookie(newCookie);
			}
		}else {
		}
		
		out.println("<a href='"+request.getContextPath()+ "/cookieCountServlet.do'>카운트 증가하기</a>");
		out.println("<a href='"+request.getContextPath()+ "/02/cookieTest02.jsp'>시작문서로 이동하기</a>");
		
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
