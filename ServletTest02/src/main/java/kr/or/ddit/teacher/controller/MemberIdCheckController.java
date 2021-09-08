package kr.or.ddit.teacher.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.member.service.IMymemberService;
import kr.or.ddit.member.service.MymemberServiceImpl;

@WebServlet("/member/memberIdCheck.do")
public class MemberIdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파마매터가 있을때 
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		String mem_id = request.getParameter("mem_id");
		
		IMymemberService service = MymemberServiceImpl.getInstance();
		int cnt = service.checkMem(mem_id);
		
		Gson gson = new Gson();
		String result = "";
		if( cnt > 0 ) {
			result = gson.toJson("Fail");
		}else {
			result = gson.toJson("Ok");
		}
		PrintWriter out = response.getWriter();
		out.println(result);
		response.flushBuffer();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
