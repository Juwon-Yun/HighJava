package kr.or.ddit.req_resp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/responseTest01.do")
public class ResponseTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 	- forward 방식
		 	  특정 서블릿에 대한 요청을 다른 서블릿이나 JSP로 넘겨준다.
		 	  (이 때 처음 요청할 때 가져온 파라미터도 넘긴다.)
		 	  
		 	  사용자(클라이언트)의 웹브라우저의 URL주소가 바뀌지 않고
		 	  처음 요청할 때의 값으로 유지된다.
		 	  
		 	  서버 내부에서만 접근이 가능하다.
		 	  
		 */
		// 이 곳에서 만든 데이터를 이동되는 페이지로 넘길 수 있는데
		// 이 때 사용하는 메소드는 Request객체의 setAttribute() 메소드를 사용한다.
		// 형식) request.setAttribute("key값", 데이터);
		// 				key값은 반드시 문자열이어야 하고, 데이터는 자바에서 사용할 수 있는 모든 데이터가 가능하다.
		
		// 받는 쪽에서는 Request객체의 getAttribute( )메소드를 사용한다.
		// 형식) request.getAttribute("key값");
		request.setAttribute("tel", "010-1234-5678");
		
		// RequestDispatcher 객체 생성하기 => 객체를 생성할 때 이동할 서블릿이나 JSP의 주소를 기술하는데
		//													  전체 URL중에서 ContextPath이후의 내용을 기술하면 된다.
		// http://localhost:8100/servletTest/responseForwardTest.do
		
		// RequestDispatcher re = request.getRequestDispatcher("이동할경로(URL맵핑)");
		RequestDispatcher re = request.getRequestDispatcher("/responseForwardTest.do");
		
		// request객체와 response객체의 파마미터 값을 그대로 넘겨준다) forward => 다른파일이지만 같은 처리를 한다고 봐도 무방 
		re.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
