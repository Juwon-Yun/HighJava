package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

public class BoardDaoImpl implements IBoardDao {
	
//	private static BoardDaoImpl instance;
//	public BoardDaoImpl() {}
//	public static BoardDaoImpl getInstance() {
//		if(instance == null ) new BoardDaoImpl();
//		return instance;
//	}
	
	
	
	@Override
	public int insertBoard(BoardVO boardvo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil3.getConnection();
			String sql = "insert into JDBC_BOARD(BOARD_NO,BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CNT, BOARD_CONTENT)\r\n"
					+ "        values(BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE, 0, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardvo.getBoard_title());
			pstmt.setString(2, boardvo.getBoard_writer());
			pstmt.setString(3, boardvo.getBoard_content());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
			if(conn != null) try { conn.close(); } catch (SQLException e) {}
		}
		return count;
	}
	@Override
	public int deleteBoard(BoardVO boardvo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil3.getConnection();
			String sql = "delete  from jdbc_board where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardvo.getBoard_no());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
			if(conn != null) try { conn.close(); } catch (SQLException e) {}
		}
		return count;
	}
	@Override
	public int updateBoard(BoardVO boardvo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update jdbc_board set board_title = ?, board_date = SYSDATE, board_content= ? where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardvo.getBoard_title());
			pstmt.setString(2, boardvo.getBoard_content());
			pstmt.setString(3, boardvo.getBoard_no());
			
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
			if(conn != null) try { conn.close(); } catch (SQLException e) {}
		}
		return count;
	}
	@Override
	public List<BoardVO> getAllBoardList() {
		List<BoardVO> boardList1 = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();			
			while(rs.next()) {
				BoardVO boardVo = new BoardVO();
		
				boardVo.setBoard_no( rs.getString("board_no") );
				boardVo.setBoard_title( rs.getString("board_title") );
				boardVo.setBoard_writer( rs.getString("board_writer") );
				boardVo.setBoard_date( rs.getString("board_date") );
				boardVo.setBoard_cnt( rs.getString("board_cnt") );
				boardVo.setBoard_content( rs.getString("board_content") );
				boardList1.add(boardVo);
			}
		} catch (SQLException e) {
			boardList1 = null;
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close(); } catch (SQLException e) {}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
			if(conn != null) try { conn.close(); } catch (SQLException e) {}
		}
		return boardList1;
	}
	@Override
	public List<BoardVO> getBoardNum(BoardVO boardvo) {
		List<BoardVO> boardList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardvo.getBoard_no());
			
			
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				boardvo.setBoard_no( rs.getString("board_no") );
				boardvo.setBoard_title( rs.getString("board_title") );
				boardvo.setBoard_writer( rs.getString("board_writer") );
				boardvo.setBoard_date( rs.getString("board_date") );
				boardvo.setBoard_cnt( rs.getString("board_cnt") );
				boardvo.setBoard_content( rs.getString("board_content") );
				boardList.add(boardvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close(); } catch (SQLException e) {}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
			if(conn != null) try { conn.close(); } catch (SQLException e) {}
		}
		return boardList;
	}
	@Override
	public List<BoardVO> getSearchBoard(BoardVO boardvo) {
		List<BoardVO> boardList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil3.getConnection();
			// '%?%'
			String sql = "select * from jdbc_board where board_title like concat(concat(? , ? ), ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%");
			pstmt.setString(2, boardvo.getBoard_title());
			pstmt.setString(3, "%");
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				boardvo.setBoard_no( rs.getString("board_no") );
				boardvo.setBoard_title( rs.getString("board_title") );
				boardvo.setBoard_writer( rs.getString("board_writer") );
				boardvo.setBoard_date( rs.getString("board_date") );
				boardvo.setBoard_cnt( rs.getString("board_cnt") );
				boardvo.setBoard_content( rs.getString("board_content") );
				boardList.add(boardvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close(); } catch (SQLException e) {}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
			if(conn != null) try { conn.close(); } catch (SQLException e) {}
		}
		return boardList;
	}
	@Override
	public int updateCnt(BoardVO boardVo) {
		int count = 0;
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil3.getConnection();
			
		// 조회수 1 증가시켜야함
		String sql2 = "update jdbc_board set board_cnt = nvl(board_cnt, 0) +1 where board_no = ? ";
		pstmt = conn.prepareStatement(sql2);
		pstmt.setString(1, boardVo.getBoard_no());
		pstmt.executeQuery();

		return 0;
		}catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
			if(conn != null) try { conn.close(); } catch (SQLException e) {}
		}
		return count;
	}
	
}
