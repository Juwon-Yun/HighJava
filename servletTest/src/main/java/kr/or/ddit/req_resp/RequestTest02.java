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
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		int firstNum = Integer.parseInt( request.getParameter("first") );
		int secondNum = Integer.parseInt( request.getParameter("second") );
		String operator = request.getParameter("math");
		
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>계산 결과</title></head>");
		out.println("<body>");
		out.println("<h2>계산 결과</h2>");
		out.println("<hr>");
		
		double sum = 0;

		//계산 성공 여부가 저장될 변수
		boolean calcOk = true; 
		
		if( operator.equals("+") ) {
			sum = firstNum + secondNum;
		}else if( operator.equals("-") ) {
			sum = firstNum - secondNum;
		}else if( operator.equals("*") ) {
			sum = firstNum * secondNum;
		}else if( operator.equals("%") ) {
			if( secondNum == 0 ) {
				calcOk = false;
			}
			sum = firstNum % secondNum;
		}else {
			if ( secondNum == 0 ) {
				calcOk = false;
			}
			sum = (double)firstNum / secondNum;
		}
//		out.println(firstNum +"  "+ operator +"  "+ secondNum +" = "+ sum);
//		out.printf("%d %s %d = %.1f<br>", firstNum, operator, secondNum, sum);
		out.printf("%d %s %d =", firstNum, operator, secondNum);
		
		if ( calcOk == true ) {
			out.printf("%.1f <br>", sum);
		}else {
			out.println("계산 불능 (0으로 나누기)");
		}
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
