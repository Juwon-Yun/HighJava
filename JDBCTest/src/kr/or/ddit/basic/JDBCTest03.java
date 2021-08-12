package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 	문제) Lprod_id값을 2개 입력 받아서 두 값 중 작은 값부터 
 			큰 값 사이의 자료들을 출력하시오.
 */
public class JDBCTest03 {
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
			
			System.out.println("출력할 LPROD_ID 컬럼의 기준값 두개를 입력하세요");
			System.out.print("기준 컬럼값1 >> ");
			int num = sc.nextInt();
			System.out.print("기준 컬럼값2 >> ");
			int num2 = sc.nextInt();
			/*
			// 선생님 풀이
			int max, min;
			if(num > num2) {
				max = num;
				min = num2;
			}else {
				max = num2;
				min = num;
			}
			String sql = "select * from lprod where lprod_id >="+min+" and lprod_id <= " + max;
			String sql = "select * from lprod where lprod_id between " +min+" and "+max;
			
			// 내 풀이
			String sql = "";
			if(num < num2) {
				sql = "select * from lprod where lprod_id >="+num+"and lprod_id <= " + num2;
			}else if(num > num2){
				sql = "select * from lprod where lprod_id >="+num2+"and lprod_id <= " + num;
			}
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			*/
			String sql = "";
			sql = "select * from lprod where lprod_id >= ? and lprod_id <=  ? ";
			pstmt = conn.prepareStatement(sql);
			if(num < num2) {
				pstmt.setInt(1, num);
				pstmt.setInt(2, num2);
			}else if(num > num2){
				pstmt.setInt(1, num2);
				pstmt.setInt(2, num);
			}
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
			if(pstmt != null) try {stmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
 		}
	}
}
