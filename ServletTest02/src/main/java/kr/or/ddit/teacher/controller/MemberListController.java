package kr.or.ddit.teacher.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMymemberService;
import kr.or.ddit.member.service.MymemberServiceImpl;
import kr.or.ddit.vo.MymemberVO;

@WebServlet("/member/memberList.do")
public class MemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		IMymemberService service = MymemberServiceImpl.getInstance();
		List<MymemberVO> memList = service.SelectAll();
		request.setAttribute("memberList", memList);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/memberList.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
