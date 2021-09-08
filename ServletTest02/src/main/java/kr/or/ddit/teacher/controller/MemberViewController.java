package kr.or.ddit.teacher.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMymemberService;
import kr.or.ddit.member.service.MymemberServiceImpl;
import kr.or.ddit.vo.MymemberVO;

@WebServlet("/member/memberView.do")
public class MemberViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String mem_id = request.getParameter("mem_id");
		
		IMymemberService service = MymemberServiceImpl.getInstance();
		MymemberVO memVo = service.getMember(mem_id);
		request.setAttribute("memberVo", memVo);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/memberView.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
