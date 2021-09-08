package kr.or.ddit.teacher.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMymemberService;
import kr.or.ddit.member.service.MymemberServiceImpl;

@WebServlet("/member/memberDelete.do")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request => 받아올때, response => 보낼때
		request.setCharacterEncoding("utf-8");
		String mem_id = request.getParameter("mem_id");
		IMymemberService service = MymemberServiceImpl.getInstance();
		int cnt = service.deleteMember(mem_id);
		response.sendRedirect(request.getContextPath()+"/member/memberList.do");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
