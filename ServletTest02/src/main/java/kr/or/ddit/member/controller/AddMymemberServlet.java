package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.member.service.MymemberServiceImpl;
import kr.or.ddit.vo.MymemberVO;

@WebServlet("/addMymemberServlet.do")
public class AddMymemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		MymemberServiceImpl service = MymemberServiceImpl.getInstance();
		String id = request.getParameter("id");
		int mid = service.checkMem(id);
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		String jsonData = gson.toJson(mid);
		out.print(jsonData);
		response.flushBuffer();
//		RequestDispatcher rd = request.getRequestDispatcher("/test/addMymember.jsp");
//		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		MymemberVO mvo = new MymemberVO();
		mvo.setMem_id( request.getParameter("id") );
		mvo.setMem_pass( request.getParameter("pass") );
		mvo.setMem_name( request.getParameter("name") );
		mvo.setMem_tel( request.getParameter("tel") );
		mvo.setMem_addr( request.getParameter("addr") );
		MymemberServiceImpl service = MymemberServiceImpl.getInstance();
		int count = service.insertMem(mvo);
		System.out.println("서블릿에서의 count " + count);
		request.setAttribute("count", count);
		
	}

}
