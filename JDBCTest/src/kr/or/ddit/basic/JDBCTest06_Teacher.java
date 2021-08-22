package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

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

public class JDBCTest06_Teacher {
	private Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		new JDBCTest06_Teacher().startMember();
	}

	public void startMember() {			
		while(true) {
			int choice = displayMenu();
			
			switch(choice) {
				case 1:	// 자료추가
					insert();
					break;
				case 2:	// 자료삭제
					delete();
					break;
				case 3:	// 자료수정
					update();
					break;
				case 4:	//전체 자료 출력
					displayMember();
					break;
				case 5:	//선택 자료 수정
					update2();
					break;
				case 0:
					System.out.println("작업을 마칩니다.");
					System.exit(0);
				default : System.out.println("번호를 잘못 입력했습니다. 다시입력하세요.");
			}
		}
	}

	private void update2() {
		System.out.println();
		sc.nextLine();
		System.out.println("수정할 회원정보를 입력하세요");
		System.out.print("수정할 회원 ID >>");
		String memId = sc.nextLine();
		
		int count = getMemberCount(memId);
		if( count ==0 ) {
			System.out.println(memId + "은(는) 없는 회원ID 입니다");
			System.out.println("수정 작업을 종료합니다.");
			return;
		}
		
		int num;
		// 선택한 항목의 컬럼명이 저장될 변수
		String updateField = null;
		// 선택한 항목의 값을 입력받을 때 나타낼 메세지가 저장될 변수
		String updateTitle = null;
		
		do {
			System.out.println();
			System.out.println("수정할 항목을 선택하세요");
			System.out.println("1.비밀번호  2.회원이름  3.전화번호  4.회원주소");
			System.out.println("--------------------------------------------------------");
			System.out.print("수정 항목 선택 >> ");
			num = sc.nextInt();
			
			switch (num) {
			case 1: updateField = "mem_pass"; updateTitle="비밀번호";				
				break;
			case 2: updateField = "mem_name"; updateTitle="회원이름";				
				break;
			case 3: updateField = "mem_tel"; updateTitle="전화번호";				
				break;
			case 4: updateField = "mem_addr"; updateTitle="회원주소";				
				break;
			default:
				System.out.println("수정할 항목을 잘못 선택했습니다");
				System.out.println("다시 선택하세요");
			}
		} while (num < 1 || num > 4);
			System.out.println();
			//버퍼지우기
			sc.nextLine();
			// 수정할 값 입력받기
			System.out.println("새로운" + updateTitle + ">>");
			String updateData = sc.nextLine();
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = DBUtil.getConnection();
				
				String sql = "update mymember set " + updateField + "= ? where mem_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, updateData);
				pstmt.setString(2, memId);
				
				int cnt = pstmt.executeUpdate();
				if(cnt > 0) {
					System.out.println("수정 작업 성공");
				}else {
					System.out.println("수정 작업 실패");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
				if(conn != null) try { conn.close(); } catch (SQLException e) {}
			}
	}

	// 회원 정보를 수정하는 메소드 ==> 전체 항목 수정
	private void update() {
		sc.nextLine();
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("수정할 회원아이디 >>");
		String memId = sc.nextLine();
		
		int count = getMemberCount(memId);
		
		// 없는 회원
		if ( count == 0 ) {
			System.out.println(memId + "은(는) 없는 회원ID 입니다.");
			System.out.println("수정 작업을 종료합니다.");
			return;
		}
		
		System.out.println("수정할 내용을 입력하세요.");
		System.out.print("새로운 비밀번호 >>");
		String newPass = sc.nextLine();
		
		System.out.print("새로운 회원이름 >>");
		String newName = sc.nextLine();
		
		System.out.print("새로운 전화번호 >>");
		String newTel = sc.nextLine();
		
		System.out.print("새로운 회원주소 >>");
		String newAddr = sc.nextLine();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update mymember set mem_pass = ?, mem_name = ?, "
					+ "		mem_tel = ?, mem_addr = ? where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPass);
			pstmt.setString(2, newName);
			pstmt.setString(3, newTel);
			pstmt.setString(4, newAddr);
			pstmt.setString(5, memId);
			
			int cnt = pstmt.executeUpdate();
			if( cnt  > 0) {
				System.out.println(memId + " 회원 정보 수정 성공");
			}else {
				System.out.println(memId + " 회원 정보 수정 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
			if(conn != null) try { conn.close(); } catch (SQLException e) {}
		}
	}

	// 자료를 삭제하는 메소드
	private void delete() {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.print("삭제할 회원ID >> ");
		String memId = sc.next();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "delete from mymember where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if( cnt > 0 ) {
				System.out.println(memId + " 회원 정보 삭제 완료했습니다");
			}else {
				System.out.println(memId + " 회원은 없는 회원이거나 삭제에 실패했습니다");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
			if(conn != null) try { conn.close(); } catch (SQLException e) {}
		}
	}

	// 자료를 추가하는 메소드
	private void insert() {
		// 입력 버퍼 비우기
		sc.nextLine();
		
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요.");
		int count = 0;
		String memId = null;
		
		//아이디가 중복될때 다시입력받는 부분
		do {
		System.out.print("회원 아이디 >>");
		memId = sc.nextLine();
		count = getMemberCount(memId);
		if( count > 0) {
			System.out.println(memId + "는(은) 이미 등록된 회원ID 입니다.");
			System.out.println("다른 회원ID를 입력하세요.");
		}
		}while(count > 0);
		
		System.out.print("비밀번호 >> ");
		String memPass = sc.nextLine();
		System.out.print("회원이름 >> ");
		String memName = sc.nextLine();
		System.out.print("전화번호 >> ");
		String memTel = sc.nextLine();
		System.out.print("회원주소 >> ");
		String memAddr = sc.nextLine();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into mymember (mem_id, mem_pass, mem_name, mem_tel, mem_addr)"
					+ "		values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memPass);
			pstmt.setString(3, memName);
			pstmt.setString(4, memTel);
			pstmt.setString(5, memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			if ( cnt > 0 ) {
				System.out.println(memId + " 회원 정보 추가 성공");
			}else {
				System.out.println(memId + " 회원 정보 추가 실패~~");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
			if(conn != null) try { conn.close(); } catch (SQLException e) {}
		}
	}
	
	// 매개변수로 회원ID를 받아서 해당 회원ID의 개수를 반환하는 메소드
	private int getMemberCount(String memId) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) cnt from mymember where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				count = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			//위에서 초기화를 해줬기때문에 굳이 안해도되지만 작성
			count = 0;
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch (SQLException e) {}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
			if(conn != null) try { conn.close(); } catch (SQLException e) {}
		}
		return count;
	}
	
	// 메뉴를 출력하는 메소드
	private int displayMenu() {
		System.out.println("===================");
	 	System.out.println("	-- 작업 선택 --");
	 	System.out.println("	1. 자료 추가  ");
		System.out.println("	2. 자료 삭제  ");
		System.out.println("	3. 자료 수정  ");
		System.out.println("	4. 전체 자료 출력");
		System.out.println("	5. 선택 자료 수정  ");
		System.out.println("	0. 작업 끝.   ");
	 	System.out.println("===================");
	 	System.out.print("원하는 작업 선택 >> ");
	 	int num = sc.nextInt();
	 	return num;
	}
	
	// 전체 회원 정보를 출력하는 메소드
	private void displayMember() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		System.out.println("---------------------------------------------------------------------");
		System.out.println("ID	비밀번호		이름		전화번호			주소");
		System.out.println("---------------------------------------------------------------------");
		
		try {
//			conn = DBUtil.getConnection();
//			conn = DBUtil2.getConnection();
			conn = DBUtil3.getConnection();
			String sql = "select * from mymember";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int cnt = 0;
			while(rs.next()) {
				cnt++;
				String memId = rs.getString("mem_id");
				String memPass = rs.getString("mem_pass");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
			
				System.out.println(memId + "     " + memPass + "     " + memName + "    " + memTel + "    " + memAddr);
			}
			if(cnt==0)System.out.println("    		회원 정보가 하나도 없습니다.");
			System.out.println("---------------------------------------------------------------------");
			System.out.println("			출력 끝");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close(); } catch (SQLException e) {}
			if(stmt != null) try { stmt.close(); } catch (SQLException e) {}
			if(conn != null) try { conn.close(); } catch (SQLException e) {}
		}
	}
}
