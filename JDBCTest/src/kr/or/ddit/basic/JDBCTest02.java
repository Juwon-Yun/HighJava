package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 	문제) 사용자로부터 Lprod_id 값을 입력 받아 입력한 값보다 Lprod_id가 큰 자료들을 출력하시오.
 */
public class JDBCTest02 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "COM";
			String password = "java";
			
			conn = DriverManager.getConnection(url, user, password);
			
			System.out.print("출력할 LPROD_ID 컬럼의 기준값을 입력하세요>>");
			int num = sc.nextInt();
			
			/*
			String sql = "select * from lprod where lprod_id > " + num;
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println("LPROD_ID : " + rs.getInt("LPROD_ID"));
				System.out.println("LPROD_GU : " + rs.getString(2));
				System.out.println("LPROD_NM : " + rs.getString(3));
				System.out.println("--------------------------------------------");
			}
			*/
			String sql = "select * from lprod where lprod_id > ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("LPROD_ID : " + rs.getInt("LPROD_ID"));
				System.out.println("LPROD_GU : " + rs.getString("LPROD_GU"));
				System.out.println("LPROD_NM : " + rs.getString("LPROD_NM"));
				System.out.println("--------------------------------------------");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(sc != null) sc.close();
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(stmt != null) try {stmt.close();} catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
 		}
	}
}
