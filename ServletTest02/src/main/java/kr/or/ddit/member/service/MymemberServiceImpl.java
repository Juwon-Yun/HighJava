package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.MymemberDaoImpl;
import kr.or.ddit.vo.MymemberVO;

public class MymemberServiceImpl implements IMymemberService {
	
	private static MymemberServiceImpl instance;
	public static MymemberServiceImpl getInstance() {
		if( instance == null ) instance = new MymemberServiceImpl();
		return instance;
	}
	private MymemberDaoImpl dao;
	
	//2번 이미 만들어진 생성자가있어서 public을 private로만 바꿔주면 된다
	private MymemberServiceImpl() {
		// DAO객체 생성
		dao = MymemberDaoImpl.getInstance();
	}
	@Override
	public List<MymemberVO> SelectAll() {
		List<MymemberVO> list = dao.SelectAll();
		return list;
	}
	
	@Override
	public int checkMem(String id) {
		return dao.checkMem(id);
	}
	@Override
	public int insertMem(MymemberVO vo) {
		return dao.insertMem(vo);
	}
	
	@Override
	public MymemberVO getMember(String memId) {
		return dao.getMember(memId);
	}
	
	@Override
	public int deleteMember(String memId) {
		return dao.deleteMember(memId);
	}
	@Override
	public int updateMember(MymemberVO mvo) {
		return dao.updateMember(mvo);
	}
	
	
	

}
