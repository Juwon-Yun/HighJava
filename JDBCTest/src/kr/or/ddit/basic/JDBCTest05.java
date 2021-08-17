package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 	문제) LPROD테이블에 새로운 데이터를 추가하기
 			LPROD_GU와 LPROD_NM은 직접 입력받아서 처리하고, 
 			LPROD_ID는 현재의 LPROD_ID중 제일 큰 값보다 1만큼 크게 해서 처리한다.
 			그리고 입력받은 LPROD_GU가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
 			
 */
public class JDBCTest05 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "COM", "java");
			
			String sql = "SELECT MAX(LPROD_ID) +1 FROM LPROD" ;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int temp = 0;
			
			// rs값이 존재할때까지 포인터를 옮김(지금 결과값은 쿼리가 1개)
			while ( rs.next() ) {
			temp = rs.getInt(1);
			}
			
			//자원반납
			stmt.close();
			rs.close();
			
			while(true) {
			System.out.println("LPROD 테이블 INSERT 하기 ");
			System.out.print("LPROD_GU : ");
			String lprodGu=sc.nextLine();
			System.out.print("LPROD_NM : ");
			String lprodNm=sc.nextLine();
			
			sql = "INSERT INTO LPROD (LPROD_ID, LPROD_GU, LPROD_NM)"
					+ "		VALUES('"+temp+"', '"+lprodGu+"', '" + lprodNm + "')";
			
			// ID에 SELECT MAX(LPROD_ID) +1 FROM LPROD;의 결과값을 넣어야한다
			stmt = conn.createStatement();
			
			int cnt = stmt.executeUpdate(sql);
			
			System.out.println("반환값 : " + cnt);
			
			if( cnt < 0 ) {
				System.out.println("이미 중복되는 LPROD_GU값입니다 다시 입력하세요");
			}else {
				break;
			}
			
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			//자원반납 필수!!!
			sc.close();
			if( rs != null ) try { rs.close(); } catch(SQLException e) {}
			if( stmt != null ) try { stmt.close(); } catch(SQLException e) {}
			if( conn != null ) try { conn.close(); } catch(SQLException e) {}
		}
	}
}
