package kr.or.ddit.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/jsonLprodServlet.do")
public class JsonLprodServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LprodVO>list = null;
		try {
		list = new ArrayList<>();
		conn = DBUtil3.getConnection();
		String sql  = "select * from LPROD";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
			while ( rs.next() ) {
				LprodVO lvo = new LprodVO();
				lvo.setLprod_id( Integer.parseInt(rs.getString("lprod_id")) );
				lvo.setLprod_gu( rs.getString("lprod_gu") );
				lvo.setLprod_nm( rs.getString("lprod_nm") );
				list.add(lvo);
			}
		} catch (SQLException e) {
			e.getMessage();
		} finally {
			if( rs != null ) try { rs.close(); } catch ( SQLException e) {}
			if( pstmt != null ) try { pstmt.close(); } catch ( SQLException e) {}
			if( conn != null ) try { conn.close(); } catch ( SQLException e) {}
		}
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("apllication/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		Gson gson = new Gson();
		String jsonData = gson.toJson(list);
		System.out.println( jsonData );
		out.println(jsonData);
		response.flushBuffer();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
