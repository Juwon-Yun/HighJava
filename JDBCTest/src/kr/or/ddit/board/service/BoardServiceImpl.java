package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService{
//	private static BoardServiceImpl instance;
//	public static BoardServiceImpl getInstance() {
//		if ( instance == null ) new BoardServiceImpl();
//		return instance;
//	}
//	
	private IBoardDao dao;
	
	public BoardServiceImpl() {
		dao = new BoardDaoImpl();
//		dao = BoardDaoImpl.getInstance();
	}

	@Override
	public int insertBoard(BoardVO boardvo) {
		return dao.insertBoard(boardvo);
	}

	@Override
	public int deleteBoard(BoardVO boardvo) {
		return dao.deleteBoard(boardvo);
	}

	@Override
	public int updateBoard(BoardVO boardvo) {
		return dao.updateBoard(boardvo);
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		return dao.getAllBoardList();
	}

	@Override
	public List<BoardVO> getBoardNum(BoardVO boardvo) {
		return dao.getBoardNum(boardvo);
	}

	@Override
	public List<BoardVO> getSearchBoard(BoardVO boardvo) {
		return dao.getSearchBoard(boardvo);
	}

	@Override
	public int updateCnt(BoardVO boardVo) {
		return dao.updateCnt(boardVo);
	}
}
