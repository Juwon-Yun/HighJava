package kr.or.ddit.mvc.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import kr.or.ddit.mvc.dao.IMemberDao;
import kr.or.ddit.mvc.dao.MemberDaoImpl;
import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.CryptoUtil;

//service에서는 dao 를 호출해야한다
public class MemberServiceImpl implements IMemberService{
	private static MemberServiceImpl instance;
	public static MemberServiceImpl getInstance() {
		if( instance == null ) instance = new MemberServiceImpl();
		return instance;
	}
	String key = "a1b2c3d4e5f6g7h8";
	
	
	// DAO객체가 저장될 변수 선언
	private IMemberDao dao;
	
	//2번 이미 만들어진 생성자가있어서 public을 private로만 바꿔주면 된다
	private MemberServiceImpl() {
		// DAO객체 생성
		dao = MemberDaoImpl.getInstance();
	}

	@Override
	public int insertMember(MemberVO memvo) {
		try {
			memvo.setMem_id( CryptoUtil.encryptAES256(memvo.getMem_id(), key) );
			memvo.setMem_pass( CryptoUtil.sha512(memvo.getMem_pass() ));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return dao.insertMember(memvo);
	}

	@Override
	public int deleteMember(String memId) {
		try {
			memId = CryptoUtil.encryptAES256(memId, key);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memvo) {
		try {
			memvo.setMem_id( CryptoUtil.encryptAES256(memvo.getMem_id(), key) );
			memvo.setMem_pass( CryptoUtil.sha512(memvo.getMem_pass() ));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return dao.updateMember(memvo);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		List<MemberVO> memList = dao.getAllMemberList();
		try {
			for (MemberVO memVo : memList) {
				String id = memVo.getMem_id();
				memVo.setMem_id( CryptoUtil.decryptAES256(id, key ));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		try {
			memId = CryptoUtil.encryptAES256(memId, key);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dao.getMemberCount(memId);
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		try {
			String id = paramMap.get("memId");
			paramMap.put("memId", CryptoUtil.encryptAES256(id, key));
			
			//field key의 value값을 String field에 저장
			String field = paramMap.get("field");
			
			// String field의 값이 mem_pass와 같으면 
			if( field.equals("mem_pass") ) {
				// String data에 data key의 value값을 저장
				String data = paramMap.get("data");
				// data key의 value값에 단방향 암호화시킨 data값 입력
				paramMap.put("data", CryptoUtil.sha512(data));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.updateMember2(paramMap);
	}


}
