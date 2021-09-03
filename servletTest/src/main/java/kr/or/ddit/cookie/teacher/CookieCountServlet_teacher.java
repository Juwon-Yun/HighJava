package kr.or.ddit.cookie.teacher;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieCountServlet_teacher.do")
public class CookieCountServlet_teacher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//쿠기 변수는 'count'로 한다.
		
		Cookie [] cookieArr = request.getCookies();
		int count = 0;
		
		// 쿠키변수 'count;가 있는지 검사
		if ( cookieArr != null ) {
			for (Cookie cookie : cookieArr) {
				// 쿠키 변수 구하기
				String name = cookie.getName();
				if( "count".equals(name) ) {
					// 쿠키 변수가 있으면 쿠키값을 읽어온다
					String value = cookie.getValue();
					// count 쿠키값을 정수형으로 반환
					count  = Integer.parseInt(value);
					
				} // for문 안쪽의 if문
			} // for문
		} // if문
		
		// count값 증가하기
		count++;
		
		// 증가된 count가 저장된 쿠키를 생성한다.
		Cookie cntCookie = new Cookie("count", String.valueOf(count));
		
		// 쿠키 추가
		response.addCookie(cntCookie);
		
		// 결과 보여주기
		out.println("<!DOCTYPE html>");
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>쿠키 count연습</title></head>");
		out.println("<body>");
		out.println("<h2>어서오세요. 당신은" + count + "번째 방문입니다.</h2>");
		out.println("<a href='"+request.getContextPath()+"/cookieCountServlet_teacher.do'>쿠키 카운트 증가하기</a>");
		out.println("<a href='"+request.getContextPath()+"/02/teacher/CookieTest02.jsp'>처음화면 돌아가기</a>");
		out.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
