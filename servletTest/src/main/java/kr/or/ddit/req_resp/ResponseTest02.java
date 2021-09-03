package kr.or.ddit.req_resp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/responseTest02.do")
public class ResponseTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 	- redirect 방식
		 	다른 페이지로 넘어가도록 한다. ( 처음 넘겨받은 파라미터를 넘길 수 없다. )
		 	응답시 브라우저에게 '이동할 URL'을 전송해서 브라우저가 해당 URL로 자동으로 이동하는 방식이다.
		 	이 때 무조건 GET방식으로 요청하기 때문에 URL의 길이에 제한이 있다.
		 	
		 	
		 */
		
//		redirect 에서는 작동이안됨 ( 완전 새로운 response, request객체이기 때문)
//		request.setAttribute("tel", "010-9999-8888");
		
		String userName = request.getParameter("username");
		String tel = "010-9999-8888";
		
		
		// 이동할 URL주소를 지정하여 redirect하기
		// 이 때 이동할 주소는 ContextPath부터 기술하면된다.
		// response.sendRedirect("ContextPath/이동할경로");
		response.sendRedirect(request.getContextPath()+"/responseRedirectTest.do?username="+userName+"&tel="+tel);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
