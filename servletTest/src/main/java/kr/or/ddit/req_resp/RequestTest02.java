package kr.or.ddit.req_resp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestTest02
 */
@WebServlet("/RequestTest02.do")
public class RequestTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int firstNum = Integer.parseInt( request.getParameter("first") );
		int secondNum = Integer.parseInt( request.getParameter("second") );
		String operator = request.getParameter("math");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>계산 결과</title></head>");
		out.println("<body>");
		out.println("<h2>계산 결과</h2>");
		out.println("<hr>");
		double sum = 0;
		if( operator.equals("+") ) {
			sum = firstNum + secondNum;
		}else if( operator.equals("-") ) {
			sum = firstNum - secondNum;
		}else if( operator.equals("*") ) {
			sum = firstNum * secondNum;
		}else {
			sum = (double)firstNum / secondNum;
		}
		out.println(firstNum +"  "+ operator +"  "+ secondNum +" = "+ sum);
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
