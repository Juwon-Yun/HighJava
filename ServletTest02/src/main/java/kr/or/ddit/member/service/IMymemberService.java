package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.vo.MymemberVO;

public interface IMymemberService {
	/**
	 * Mymember 테이블의 모든 값을 조회하여 전송해준다
	 * @return List
	 */
	public List<MymemberVO> SelectAll();
	
	public int checkMem(String id);
	
	public int insertMem(MymemberVO vo);
	
	/**
	 * 매개변수로 넘어온 회원ID를 갖는 회원 정보 1개를 검색해서 반환하는 메소드
	 * @param memId 검색할 회원 ID
	 * @return 검색한 결과가 저장된 MymemberVO객체
	 */
	public MymemberVO getMember(String memId);

	public int deleteMember(String memId);
	
	public int updateMember(MymemberVO mvo);
}
