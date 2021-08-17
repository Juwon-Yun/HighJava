package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

public class JDBCTest05_Teacher {
	 public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "COM", "java");
//			
			conn = DBUtil.getConnection();
			// Lprod_id는 현재의 Lprod_id중 제일 큰 값보다 1만큼 크게해서 처리한다.
			String sql = "select nvl(max(lprod_id),0)+1 maxnum from lprod";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int maxNum = 1;
			// Select한 결과가 1개의 레코드 일 경우에는 if문으로 처리가능
			if (rs.next()) {
				maxNum = rs.getInt("maxnum");
			}
			
			//입력받은 Lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다.

			// 상품 분류 코드(LPROD_GU)가 저장될 변수 선언
			String gu;
			// 입력한 상품 분류 코드의 개수가 저장될 변수
			int count = 0;
			
			// alias 생략 가능 (cnt)
			String sql2 = "select count(*) cnt from lprod where lprod_gu = ? ";
			pstmt1 = conn.prepareStatement(sql2);
			
			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 : ");
				gu = sc.nextLine();
				
				pstmt1.setString(1, gu);
				
				rs = pstmt1.executeQuery();
				
				if( rs.next() ) {
					count = rs.getInt("cnt");
				}
				if( count > 0 ) {
					System.out.println("입력한 상품 분류 코드 " + gu + "는 이미 등록된 코드입니다. ");
					System.out.println("다시 입력하세요.");
				}
					
			} while (count>0);
			System.out.print("상품 분류명(LPROD_NM) 입력 : ");
			String nm = sc.nextLine();
			
			String sql3 = "INSERT INTO LPROD (LPROD_ID, LPROD_GU, LPROD_NM)"
					+ "		VALUES(?, ?, ?)";
			pstmt2 = conn.prepareStatement(sql3);
			pstmt2.setInt(1, maxNum);
			pstmt2.setString(2, gu);
			pstmt2.setString(3, nm);
			
			int cnt = pstmt2.executeUpdate();
			
			if ( cnt > 0) {
				System.out.println("등록 성공");
			}else {
				System.out.println("등록 실패");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sc.close();
			if( rs != null) try { rs.close(); } catch (SQLException e) {}
			if( stmt != null ) try { stmt.close(); } catch (SQLException e) {}
			if( pstmt1 != null ) try { pstmt1.close(); } catch (SQLException e) {}
			if( pstmt2 != null ) try { pstmt2.close(); } catch (SQLException e) {}
			if( conn != null ) try { conn.close(); } catch (SQLException e) {}
		}
	 }
}
