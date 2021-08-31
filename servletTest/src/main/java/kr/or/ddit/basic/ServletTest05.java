package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 서블릿 초기화 파라미터를 사용하는 예제
public class ServletTest05 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		/*
		 	서블릿 초기화 파라미터는 ServletConfig객체를 이용해서 구할 수 있다.
		 	ServletConfig객체는 Servlet의 getServletConfig( )메소드를 이용해서 구한다
		 */
		
		// ServletConfig객체 구하기
		ServletConfig config = this.getServletConfig();
		
		// 초기화 파마리터는 ServletConfig객체의 getInitParameter( )메소드를 이용해서 구한다.
		// => 이 값은 무조건 String으로 가져온다
		String strStrat = config.getInitParameter("start");
		String strEnd = config.getInitParameter("end");
		
		int start = Integer.parseInt(strStrat);
		int end = Integer.parseInt(strEnd);
		
		int sum = 0;
		for (int i = start; i < end; i++) {
			sum += i;
		}
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>서블릿 초기화 파라미터 연습</title></head>");
		out.println("<body>");
		out.println("<h2>" + start + "부터 " + end + "까지의 합 : " + sum +"<br>");
		out.println("</body></html>");
		
	}	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
