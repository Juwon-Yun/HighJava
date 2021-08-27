package kr.or.ddit.board.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.board.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {
	
	private static BoardDaoImpl instance;
	private SqlMapClient smc; 
	private BoardDaoImpl() {
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("kr/or/ddit/config/sqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static BoardDaoImpl getInstance() {
		if(instance == null ) instance = new BoardDaoImpl();
		return instance;
	}
	
	@Override
	public int insertBoard(BoardVO boardvo) {
		int count = 0;
		try {
			Object obj = smc.insert("board.insertBoard",boardvo);
			if( obj == null ) {
				count = 1;
			}
			
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		}
		return count;
	}
	@Override
	public int deleteBoard(BoardVO boardvo) {
		int count = 0;
		try {
			count = smc.delete("board.deleteBoard",boardvo);
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		}
		return count;
	}
	@Override
	public int updateBoard(BoardVO boardvo) {
		int count = 0;
		try {
			count = smc.update("board.updateBoard",boardvo);
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		}
		return count;
	}
	
	@Override
	public List<BoardVO> getAllBoardList() {
		List<BoardVO> boardList = null;
		try {
			boardList = smc.queryForList("board.getAllBoard");
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		}
		return boardList;
	}
	@Override
	public List<BoardVO> getBoardNum(BoardVO boardvo) {
		List<BoardVO> boardList = null;
		try {
			boardList = smc.queryForList("board.getBoardNum", boardvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardList;
	}
	
	@Override
	public List<BoardVO> getSearchBoard(BoardVO boardvo) {
		List<BoardVO> boardList = null;
		try {
			// '%?%'
			boardList = smc.queryForList("board.getSearchBoard",boardvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardList;
	}
	
	@Override
	public int updateCnt(BoardVO boardvo) {
		int count = 0;
		try {
			count = smc.update("board.updateCnt", boardvo);
		}catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		}
		return count;
	}
	
}
