package kr.or.ddit.mvc.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

// Implement의 Impl 
public class MemberDaoImpl implements IMemberDao{
	// apache log 객체 생성
	static Logger logger = Logger.getLogger(MemberDaoImpl.class);
	
	// 1번 자기자신이 참조값이될 변수를 pivate static으로 생성
	private static MemberDaoImpl instance;
	// 2번 생성자
	private MemberDaoImpl() {}
	// 3번 자기자신의 참조값을 반환하는 메소드 생성
	public static MemberDaoImpl getInstance() {
		if( instance == null ) instance = new MemberDaoImpl();
		logger.info("MemberDaoImpl 싱글톤 객체 생성 - MemberDaoImpl 파일 읽기 ");
		return instance;
	}
	
	@Override
	public int insertMember(MemberVO memvo) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성");
			String sql = "insert into mymember (mem_id, mem_pass, mem_name, mem_tel, mem_addr)"
					+ "		values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memvo.getMem_id());
			pstmt.setString(2, memvo.getMem_pass());
			pstmt.setString(3, memvo.getMem_name());
			pstmt.setString(4, memvo.getMem_tel());
			pstmt.setString(5, memvo.getMem_addr());
			logger.info("PreparedStatement 객체 생성");
			logger.info("실행 SQL : " + sql);
			logger.info("사용 데이터 : [" + memvo.getMem_id() + ", "
					+ 	memvo.getMem_pass() + ", " + memvo.getMem_name() + ", "
					+  memvo.getMem_tel() + ", " + memvo.getMem_addr() + "]");
			
			cnt = pstmt.executeUpdate();
			logger.info("MemberDaoImpl 클래스의 insert 쿼리 성공");

		} catch (SQLException e) {
			logger.error("MemberDaoImpl 클래스의 insert 쿼리 실패 : "+e.getMessage());
			cnt = 0;
			// 프로그램을 멈춰버리기때문에 프로그램을 계속하기 위해서 주석처리
//			e.printStackTrace();
		} finally {
			if(pstmt != null)
				try { 
				pstmt.close();
				logger.info("PreparedStatement 객체 반납");
				} catch (SQLException e) {}
			if(conn != null)
				try { 
				conn.close(); 
				logger.info("Connection 객체 반납");
				} catch (SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성");

			String sql = "delete from mymember where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			logger.info("PreparedStatement 객체 생성");
			logger.info("실행 SQL : " + sql);
			logger.info("사용 데이터 : [" + memId + "]");
			count = pstmt.executeUpdate();
			logger.info("MemberDaoImpl 클래스의 delete 쿼리 성공");
		} catch (SQLException e) {
			logger.error("MemberDaoImpl 클래스의 delete 쿼리 실패 : " + e.getMessage());
			count = 0;
//			e.printStackTrace();
		} finally {
			if(pstmt != null)
				try {
					pstmt.close();
					logger.info("PreparedStatement 객체 반납");
				} catch (SQLException e) {}
			if(conn != null) 
				try {
					conn.close();
					logger.info("Connection 객체 반납");
				} catch (SQLException e) {}
		}
		return count;
	}

	@Override
	public int updateMember(MemberVO memvo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성");
			String sql = "update mymember set mem_pass = ?, mem_name = ?, "
					+ "		mem_tel = ?, mem_addr = ? where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memvo.getMem_pass());
			pstmt.setString(2, memvo.getMem_name());
			pstmt.setString(3, memvo.getMem_tel());
			pstmt.setString(4, memvo.getMem_addr());
			pstmt.setString(5, memvo.getMem_id());
			logger.info("PreparedStatement 객체 생성");
			logger.info("실행 SQL : " + sql);
			logger.info("사용 데이터 : [" + memvo.getMem_pass()
					+", " + memvo.getMem_name()
					+ ", "+ memvo.getMem_tel()
					+ ", "+ memvo.getMem_addr() 
					+ ", "+ memvo.getMem_id() + "]");
			
			count = pstmt.executeUpdate();
			logger.info("MemberDaoImpl 클래스의 updateMember 쿼리 성공");
		} catch (SQLException e) {
			logger.error("MemberDaoImpl 클래스의 updateMember 쿼리 실패 : " + e.getMessage());
			count = 0;
//			e.printStackTrace();
		} finally {
			if(pstmt != null) 
				try {
					pstmt.close();
					logger.info("PreparedStatement 객체 반납");
				} catch (SQLException e) {}
			if(conn != null) 
				try {
					conn.close();
					logger.info("Connection 객체 반납");
				} catch (SQLException e) {}
		}
		return count;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		List<MemberVO> memList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성");
			String sql = "select * from mymember";
			pstmt = conn.prepareStatement(sql);
			logger.info("PreparedStatement 객체 생성");
			rs = pstmt.executeQuery();			
			logger.info("ResultSet 객체 생성");
			while(rs.next()) {
				MemberVO mvo = new MemberVO();
				mvo.setMem_id( rs.getString("mem_id") );
				mvo.setMem_pass( rs.getString("mem_pass") );
				mvo.setMem_name( rs.getString("mem_name") );
				mvo.setMem_tel( rs.getString("mem_tel") );
				mvo.setMem_addr( rs.getString("mem_addr") );
				memList.add(mvo);
			logger.info("실행 SQL : " + sql);
			logger.info("사용 데이터 : [" + mvo.getMem_id()
					+", " + mvo.getMem_pass()
					+ ", "+ mvo.getMem_name()
					+ ", "+ mvo.getMem_tel() 
					+ ", "+ mvo.getMem_addr() + "]");
			}
			logger.info("MemberDaoImpl 클래스의 getAllMemberList 쿼리 성공");
		} catch (SQLException e) {
			logger.error("MemberDaoImpl 클래스의 getAllMemberList 쿼리 실패 : " + e.getMessage());
			memList = null;
//			e.printStackTrace();
		}finally {
			if(rs != null) 
				try {
					rs.close();
					logger.info("Resultset 객체 반납");
				} catch (SQLException e) {}
			if(pstmt != null)
				try {
					pstmt.close(); 
					logger.info("PreparedStatement 객체 반납");
				} catch (SQLException e) {}
			if(conn != null)
				try {
					conn.close(); 
					logger.info("Connection 객체 반납");
				} catch (SQLException e) {}
		}
		return memList;
	}


	@Override
	public int getMemberCount(String memId) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성");
			String sql = "select count(*) cnt from mymember where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			logger.info("PreparedStatement 객체 생성");
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			logger.info("ResultSet 객체 생성");
			while( rs.next() ) {
				count = rs.getInt("cnt");
				logger.info("실행 SQL : " + sql);
				logger.info("사용 데이터 : [" + memId + "]");
			}
			logger.info("MemberDaoImpl 클래스의 getMemberCount 쿼리 성공");
		} catch (SQLException e) {
			logger.error("MemberDaoImpl 클래스의 getMemberCount 쿼리 실패 : " + e.getMessage());
			count = 0;
//			e.printStackTrace();
		} finally {
			if(rs != null) 
				try {
					rs.close(); 
					logger.info("Resultset 객체 반납");
				} catch (SQLException e) {}
			if(pstmt != null)
				try {
					pstmt.close(); 
					logger.info("PreparedStatement 객체 반납");
				} catch (SQLException e) {}
			if(conn != null) 
				try {
					conn.close(); 
					logger.info("Connection 객체 반납");
				} catch (SQLException e) {}
		}
		return count;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
//		key값 정보 => 회원ID(memId), 수정할컬럼명(field), 수정할데이터(data)
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성");
			String sql ="update mymember set " + paramMap.get("field") +"= ? "
					+ "											where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramMap.get("data"));
			pstmt.setString(2, paramMap.get("memId"));
			logger.info("PreparedStatement 객체 생성");
			logger.info("실행 SQL : " + sql);
			logger.info("사용 데이터 : [" + paramMap.get("data") 
										   +", "+paramMap.get("memId") +"]");
			cnt = pstmt.executeUpdate();
			logger.info("MemberDaoImpl 클래스의 getMemberCount 쿼리 성공");
		} catch (SQLException e) {
			logger.error("MemberDaoImpl 클래스의 getMemberCount 쿼리 실패 : " + e.getMessage());
			cnt = 0;
//			e.printStackTrace();
		} finally {
			if(pstmt != null) 
				try {
					pstmt.close(); 
					logger.info("PreparedStatement 객체 반납");
				} catch (SQLException e) {}
			if(conn != null) 
				try {
					conn.close(); 
					logger.info("Connection 객체 반납");
				} catch (SQLException e) {}
		}
		return cnt;
	}
	
	@Override
	public int executeExel(Map<String, String> paramMap) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성");
			
			String sql = "select * from mymember where "+paramMap.get("field")+ "= ? "
					+" 			and mem_id = ? ";
//			String sql ="update mymember set " + paramMap.get("field") +"= ? "
//					+ "											where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramMap.get("data"));
			pstmt.setString(2, paramMap.get("memId"));
			rs = pstmt.executeQuery();
			logger.info("MemberDaoImpl 클래스의 executeExel 데이터 콘솔창으로 출력 성공");

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("MYMEMBER");
			XSSFRow row = sheet.createRow(0);

			row.createCell(0).setCellValue("MEM_ID");
			row.createCell(1).setCellValue("MEM_PASS");
			row.createCell(2).setCellValue("MEM_NAME");
			row.createCell(3).setCellValue("MEM_TEL");
			row.createCell(4).setCellValue("MEM_ADDR");
			int r = 1;

			
			logger.info("PreparedStatement 객체 생성");
			logger.info("실행 SQL : " + sql);
			logger.info("사용 데이터 : [" + paramMap.get("data") 
			+", "+paramMap.get("memId") +"]");
			
			while( rs.next() ) {
				cnt = 1;
				String id = rs.getString("MEM_ID");
				String pass = rs.getString("MEM_PASS");
				String name = rs.getString("MEM_NAME");
				String tel = rs.getString("MEM_TEL");
				String addr = rs.getString("MEM_ADDR");
				
				row = sheet.createRow(r++);
				row.createCell(0).setCellValue(id);
				row.createCell(1).setCellValue(pass);
				row.createCell(2).setCellValue(name);
				row.createCell(3).setCellValue(tel);
				row.createCell(4).setCellValue(addr);
			}
			logger.info("MemberDaoImpl 클래스의 executeExel 쿼리 성공");
			FileOutputStream fos = new FileOutputStream("E://D_Other/ddit.xlsx");
			workbook.write(fos);
			
			
			workbook.close();
		} catch (SQLException e) {
			logger.error("MemberDaoImpl 클래스의 executeExel 쿼리 실패 : " + e.getMessage());
			cnt = 0;
//			e.printStackTrace();
		} catch (IOException e) {
			logger.error("MemberDaoImpl 클래스의 executeExel 엑셀 생성 실패 : " + e.getMessage());
			cnt = 0;
//			e.printStackTrace();
		} finally {
			if(pstmt != null) 
				try {
					pstmt.close(); 
					logger.info("PreparedStatement 객체 반납");
				} catch (SQLException e) {}
			if(conn != null) 
				try {
					conn.close(); 
					logger.info("Connection 객체 반납");
				} catch (SQLException e) {}
		}
		return cnt;
	}
}
