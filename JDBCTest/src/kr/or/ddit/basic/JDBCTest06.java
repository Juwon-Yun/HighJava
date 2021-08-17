package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
 	
 	회원을 관리하는 프로그램을 작성하시오.
 	(오라클의 MYMEMBER 테이블 이용)
 	
 	아래 메뉴의 기능을 모두 구현하시오. (CRUD기능 구현하기)
 	
 	메뉴예시)
 	===================
 			-- 작업 선택 --
 			1. 자료 추가
			2. 자료 삭제
			3. 자료 수정
			4. 전체 자료 출력
			0. 작업 끝.
 	===================
	
	처리 조건)
	1) 자료 추가에서 '회원ID'는 중복되지 않는다. (중복되면 다시 입력받는다.)
	2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
	3) 자료 수정에서 '회원ID'는 변경되지 않는다.
 	
 	
 */
public class JDBCTest06 {
	Scanner sc = new Scanner(System.in);
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	
	public static void main(String[] args) {
		DBUtil.getInstance();
		new JDBCTest06().start();
	}
	private void menu() {
		System.out.println("===================");
	 	System.out.println("	-- 작업 선택 --");
	 	System.out.println("	1. 자료 추가  ");
		System.out.println("	2. 자료 삭제  ");
		System.out.println("	3. 자료 수정  ");
		System.out.println("	4. 전체 자료 출력");
		System.out.println("	0. 작업 끝.   ");
	 	System.out.println("===================");
	}
	private int start() {
		while(true) {
		menu();
		System.out.print("메뉴를 입력하세요>>");
		int input = sc.nextInt();
		switch(input){
		case 1:
			createData();
			break;
		case 2:
			deleteData();
			break;
		case 3:
			updateData();
			break;
		case 4:
			readDate();
			break;
		case 0:
			System.out.println("프로그램을 종료합니다");
			System.exit(0);
		default :
			System.out.println("잘못된 입력입니다");
			start();
			}
		}
	}
	//처리 조건)
	//	1) 자료 추가에서 '회원ID'는 중복되지 않는다. (중복되면 다시 입력받는다.)
	private void createData() {
		sc.nextLine();
		try{
			conn = DBUtil.getConnection();
		
			
		String id;
		int isid = 0;
		
		String sql2 = "SELECT COUNT(*) ID FROM MYMEMBER WHERE MEM_ID = ? ";
		pstmt1 = conn.prepareStatement(sql2);
		do {
			System.out.print("회원 ID 입력 : ");
			id = sc.nextLine();
			pstmt1.setString(1, id);
			rs = pstmt1.executeQuery();
			if( rs.next() ) {
				isid = rs.getInt("ID");
			}
			if( isid > 0 ) { 
				System.out.println("입력한 회원 ID " + id + "는 이미 등록된 회원 ID입니다.");
				System.out.println("다시 입력하세요");
			}
		} while (isid > 0);
			System.out.print("회원 Password 입력 : ");
			String ps = sc.nextLine();
			System.out.print("회원 Name 입력 : ");
			String name = sc.nextLine();
			System.out.print("회원 Tel 입력 : ");
			String tel = sc.nextLine();
			System.out.print("회원 Addr 입력 : ");
			String addr = sc.nextLine();
			
			String sql3 = "INSERT INTO MYMEMBER(MEM_ID, MEM_PASS, MEM_NAME, MEM_TEL, MEM_ADDR)"
					+ "	VALUES(?, ?, ?, ?, ?)";
			
			pstmt2 = conn.prepareStatement(sql3);
			pstmt2.setString(1, id);
			pstmt2.setString(2, ps);
			pstmt2.setString(3, name);
			pstmt2.setString(4, tel);
			pstmt2.setString(5, addr);
			
			int cnt2 = pstmt2.executeUpdate();
			if(cnt2 > 0) {
				System.out.println("등록 성공");
			}else {
				System.out.println("등록 실패");
			}
		} catch ( SQLException e ) {
			System.out.println("회원 ID 생성 실패");
			e.printStackTrace();
		}finally {
			if( rs != null) try { rs.close(); } catch (SQLException e) {}
			if( stmt != null ) try { stmt.close(); } catch (SQLException e) {}
			if( pstmt1 != null ) try { pstmt1.close(); } catch (SQLException e) {}
			if( pstmt2 != null ) try { pstmt2.close(); } catch (SQLException e) {}
			if( conn != null ) try { conn.close(); } catch (SQLException e) {}
		}
	}
	//	2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
	private void deleteData() {
		sc.nextLine();
		try{
			conn = DBUtil.getConnection();
			
			System.out.print("삭제할 ID 를 입력 하세요 : ");
			String id = sc.nextLine();
			
			String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ? ";
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setString(1, id);
			
			int cnt = pstmt1.executeUpdate();
			if( cnt > 0) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
		} catch (SQLException e) {
			System.out.println("회원 ID 삭제 실패");
			e.printStackTrace();
		}finally {
			if( rs != null) try { rs.close(); } catch (SQLException e) {}
			if( stmt != null ) try { stmt.close(); } catch (SQLException e) {}
			if( pstmt1 != null ) try { pstmt1.close(); } catch (SQLException e) {}
			if( pstmt2 != null ) try { pstmt2.close(); } catch (SQLException e) {}
			if( conn != null ) try { conn.close(); } catch (SQLException e) {}
		}
	}
	private void updateData() {
		sc.nextLine();
		try{
			conn = DBUtil.getConnection();
			
			String id = "";
//			String isid = "";
			System.out.print("변경할 ID 를 입력 하세요 : ");
			id = sc.nextLine();
			
//			do {
//				
//				String sql2 = "SELECT MEM_ID FROM MYMEMBER WHERE MEM_ID = ? ";
//				pstmt2 = conn.prepareStatement(sql2);
//				pstmt2.setString(1, id);
//				rs = pstmt2.executeQuery();
//				
//				if( rs.next() ) {
//					isid = rs.getString("MEM_ID");
//				}
//				if( isid != null ) { 
//					break;
//				}else {
//				System.out.println("입력한 회원 ID " + id + "는 등록되지않은 회원 ID입니다.");
//				System.out.println("다시 입력하세요");
//				}
//				
//			} while (isid != null);
			
			System.out.print("변경할 Password를 입력하세요 : ");
			String ps = sc.nextLine();
			System.out.print("변경할 Name을 입력하세요 : ");
			String name = sc.nextLine();
			System.out.print("변경할 Tel을 입력하세요 : ");
			String tel = sc.nextLine();
			System.out.print("변경할 Addr을 입력하세요 : ");
			String addr = sc.nextLine();
			
			
			String sql = "UPDATE MYMEMBER SET MEM_PASS = ?,"
					+ "										MEM_NAME = ?,"
					+ "										MEM_TEL = ?,"
					+ "										MEM_ADDR = ?"
					+ "		WHERE MEM_ID = ? ";
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setString(1, ps);
			pstmt1.setString(2, name);
			pstmt1.setString(3, tel);
			pstmt1.setString(4, addr);
			pstmt1.setString(5, id);
			
			int cnt = pstmt1.executeUpdate();
			if( cnt > 0) {
				System.out.println("변경 성공");
			}else {
				System.out.println("변경 실패");
			}
		}catch(SQLException e) {
			System.out.println("회원 정보 수정 실패");
			e.printStackTrace();
		}finally {
			if( rs != null) try { rs.close(); } catch (SQLException e) {}
			if( stmt != null ) try { stmt.close(); } catch (SQLException e) {}
			if( pstmt1 != null ) try { pstmt1.close(); } catch (SQLException e) {}
			if( pstmt2 != null ) try { pstmt2.close(); } catch (SQLException e) {}
			if( conn != null ) try { conn.close(); } catch (SQLException e) {}
		}
	}
	
	private void readDate() {
		sc.nextLine();
		try{
			conn = DBUtil.getConnection();
			
			String sql = "SELECT * FROM MYMEMBER";
			
			pstmt1 = conn.prepareStatement(sql);
			
			rs = pstmt1.executeQuery();
			while( rs.next() ) {
				System.out.println("회원 ID : " + rs.getString(1));
				System.out.println("회원 Password : " + rs.getString(2));
				System.out.println("회원 Name : " + rs.getString(3));
				System.out.println("회원 Tel : " + rs.getString(4));
				System.out.println("회원 Addr : " + rs.getString(5));
				System.out.println("===================");
			}
			
		} catch (SQLException e) {
			System.out.println("전체 자료 출력 실패");
			e.printStackTrace();
		}finally {
			if( rs != null) try { rs.close(); } catch (SQLException e) {}
			if( stmt != null ) try { stmt.close(); } catch (SQLException e) {}
			if( pstmt1 != null ) try { pstmt1.close(); } catch (SQLException e) {}
			if( pstmt2 != null ) try { pstmt2.close(); } catch (SQLException e) {}
			if( conn != null ) try { conn.close(); } catch (SQLException e) {}
		}
	}
	
	
}
