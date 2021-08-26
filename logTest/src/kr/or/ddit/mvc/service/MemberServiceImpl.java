package kr.or.ddit.mvc.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.dao.IMemberDao;
import kr.or.ddit.mvc.dao.MemberDaoImpl;
import kr.or.ddit.mvc.vo.MemberVO;

//service에서는 dao 를 호출해야한다
public class MemberServiceImpl implements IMemberService{
	private static MemberServiceImpl instance;
	public static MemberServiceImpl getInstance() {
		if( instance == null ) instance = new MemberServiceImpl();
		return instance;
	}
	
	// DAO객체가 저장될 변수 선언
	private IMemberDao dao;
	
	//2번 이미 만들어진 생성자가있어서 public을 private로만 바꿔주면 된다
	private MemberServiceImpl() {
		// DAO객체 생성
		dao = MemberDaoImpl.getInstance();
	}

	@Override
	public int insertMember(MemberVO memvo) {
		return dao.insertMember(memvo);
	}

	@Override
	public int deleteMember(String memId) {
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memvo) {
		return dao.updateMember(memvo);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		return dao.getAllMemberList();
	}

	@Override
	public int getMemberCount(String memId) {
		return dao.getMemberCount(memId);
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		return dao.updateMember2(paramMap);
	}

	@Override
	public int excuteExel(Map<String, String> paramMap) {
		return dao.executeExel(paramMap);
	}

}
