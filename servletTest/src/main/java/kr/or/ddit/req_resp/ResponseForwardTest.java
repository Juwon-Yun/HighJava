package kr.or.ddit.req_resp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/responseForwardTest.do")
public class ResponseForwardTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	// forward가 됬다면 ResponseTest01.java에서 받은 request와 response가 있다. URL에는 responseTest01.do 로 작성됨
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 파라미터로 넘어온 데이터 받기
		String userName = request.getParameter("username");
		
		// setAttribute( )메소드로 넘어온 데이터 받기
		String tel = (String)request.getAttribute("tel");
		
		//Redirect로 받은 데이터 받기
//		String tel = request.getParameter("tel");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>forward 연습</title></head>");
		out.println("<body>");
		out.println("<h2>forward 결과</h2><hr>");
		out.println("<ul>");
		out.println("<li>이      름 : " + userName + "<br>");
		out.println("<li>전화번호 : " + tel + "<br>");
		out.println("</ul>");
		out.println("<hr>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
