package kr.or.ddit.basic.excoding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtil {

//	private JDBCUtil() {
//		
//	}
	
	private static JDBCUtil instance = new JDBCUtil();

	private static JDBCUtil getInstance() {
		if(instance == null) {
			instance = new JDBCUtil();
		}
		return instance;
	}

	String url = "jdbc:oracle:this:@localhost:1521:xe";
	String user = "COM";
	String password = "java";

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	ResultSetMetaData metaData = null;
	
	// 		ex) List에 MemberVO 를 담는다
	public List<Object> selectOne(String sql) {
		List<Object> selectOneList = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();

			metaData = rs.getMetaData();
			int cnt = metaData.getColumnCount();
			
			while(rs.next()) {
				selectOneList = new ArrayList<>();
				for(int i = 1; i <= cnt; i++) {
					selectOneList.add(metaData.getColumnName(i));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if ( rs != null ) try { rs.close(); } catch (SQLException e) {}
			if ( ps != null ) try { rs.close(); } catch (SQLException e) {}
			if ( conn != null ) try { rs.close(); } catch (SQLException e) {}
		}
		return selectOneList;
	}
	
}
