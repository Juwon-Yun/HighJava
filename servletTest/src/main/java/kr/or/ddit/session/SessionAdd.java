package kr.or.ddit.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionAdd
 */
@WebServlet("/sessionAdd.do")
public class SessionAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Session 저장하는 방법
		
		// 1. Session 객체 생성하거나 현재 Session 정보 가져오기
		// 형식1) request객체.getSession() 또는 request객체.getSession(true);
		//			=> 현재 Session이 존재하면 현재 Session객체를 반환하고
		//				 존재하지 않으면 새로운 Session객체를 생성한다.
		// 형식2) request객체.getSession(false);
		//			=> 현재 Session이 존재하면 현재 Session객체를 반환하고
		//				 존재하지 않으면 null을 반환한다.
		HttpSession session = request.getSession();
		
		// 2. 세션에 데이터 저장하기
		// 형식) session객체변수.setAttribute("key값", 데이터);
		//		   => key값 : String
		//		   => 데이터 : 자바의 모든 종류의 데이터
		session.setAttribute("testSession", "연습용 세션입니다.");
		session.setAttribute("userName", "홍길동");
		// 쿠키에서는 숫자를 문자열로 감싸서( " " ) 저장했지만
		// 세션에서는 숫자를 그대로 써도된다 ( 객체도 저장가능 )
		session.setAttribute("age", 30);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>Session 연습</title></head>");
		out.println("<body>");
		out.println("<a href='"+request.getContextPath()+"/03/sessionTest.jsp'>시작화면으로 돌아가기</a>");
		out.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
