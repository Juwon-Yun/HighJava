package kr.or.ddit.json;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LprodDao {
	
	public List<LprodVO> getLprodList() {
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
			list = null;
			e.getMessage();
		} finally {
			if( rs != null ) try { rs.close(); } catch ( SQLException e) {}
			if( pstmt != null ) try { pstmt.close(); } catch ( SQLException e) {}
			if( conn != null ) try { conn.close(); } catch ( SQLException e) {}
		}
		
		return list;
	}
}
