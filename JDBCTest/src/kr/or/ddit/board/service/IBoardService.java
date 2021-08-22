package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

/**
 * 
 * @author GJW
 *
 */
public interface IBoardService {
	
	/**
	 * 
	 * @param boardvo
	 * @return true 1, false 0
	 */
	public int insertBoard(BoardVO boardvo);
	
	/**
	 * 
	 * @param boardvo
	 * @return true 1, false 0
	 */
	public int deleteBoard(BoardVO boardvo);
	
	/**
	 * 
	 * @param boardvo
	 * @return true 1, false 0
	 */
	public int updateBoard(BoardVO boardvo);
	
	/**
	 * 
	 * @return BoardVO객체를 담고있는 List객체
	 */
	public List<BoardVO> getAllBoardList();
	
	/**
	 * Controller에서 선택된 번호를 Dao에 전달하는 메소드
	 * @return
	 */
	public List<BoardVO> getBoardNum(BoardVO boardvo);

	/**
	 * Controller에서 입력받은 제목을 Dao에 전달하는 메소드
	 * @return
	 */
	public List<BoardVO> getSearchBoard(BoardVO boardvo);

	/**
	 * 조회한 글번호를 매개변수로 넘겨받아 해당 글번호의 조회수를 +1증가시키는 메소드
	 * @param boardVo
	 * @return
	 */
	public int updateCnt(BoardVO boardVo);
}
