package kr.or.ddit.cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieLoginServlet.do")
public class CookieLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
//		PrintWriter out = response.getWriter();
		Cookie cookieId = new Cookie("id", request.getParameter("id"));
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// 체크되면 chk == on, 아니면 null
		String chk = request.getParameter("chk1");
		System.out.println(id);
		System.out.println(pw);
		System.out.println(chk);
		
			if ( chk.equals("on") ) {
				response.addCookie(cookieId);
			}else {
				cookieId.setMaxAge(0);
				response.addCookie(cookieId);
			}
		
		if ( "test".equals(id) && "1234".equals(pw) ) {
			response.sendRedirect(request.getContextPath()+"/02/cookieMain.jsp");
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"/02/cookieLogin.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
