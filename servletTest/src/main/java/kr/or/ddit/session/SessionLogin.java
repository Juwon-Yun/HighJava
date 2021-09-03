package kr.or.ddit.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionLogin.do")
public class SessionLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		session.setAttribute("id", request.getParameter("id"));
		session.setAttribute("pass", request.getParameter("pass"));
		String id = (String)session.getAttribute("id");
		String pass = (String)session.getAttribute("pass");
		if ( id!=null ) {
			if( "admin".equals(id) && "1234".equals(pass) ) {
				response.sendRedirect(request.getContextPath()+"/sessionLogout.do");
				return;
			}
			response.sendRedirect(request.getContextPath()+"/03/sessionLogin.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
