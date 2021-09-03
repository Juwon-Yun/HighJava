package kr.or.ddit.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionDelete.do")
public class SessionDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Session 삭제하기
		
		// 1. 세셩 생성 또는 현재 세션 가져오기
		HttpSession session = request.getSession();
		
		// 2. 세션에 저장된 개별적인 데이터 삭제하기
		// 		=> 세션 자체는 삭제되지 않고 개별적인 '세션값'만 삭제된다.
		// 형식) session객체.removeAttribute("삭제할 key값");
		session.removeAttribute("testSession");
		
		// 3. 세션 자체를 삭제하기 ('세션값' 전체 삭제)
		// 형식) session객체.invalidate();
//		session.invalidate();
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out= response.getWriter();
	
		out.println("<!DOCTYPE html>");
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>Session 연습</title></head>");
		out.println("<body>");
		out.println("<h2>저장된 Session 데이터가 삭제되었습니다</h2><hr><br>");
		out.println("<a href='"+request.getContextPath()+"/03/sessionTest.jsp'>시작화면으로 돌아가기</a>");
		out.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
