package kr.or.ddit.mvc.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.SqlMapClientFactory;

// Implement의 Impl 
public class MemberDaoImpl implements IMemberDao{
	// 1번 자기자신이 참조값이될 변수를 pivate static으로 생성
	private static MemberDaoImpl instance;
	
	//ibatis용 SqlMapClient 객체 변수 선언
	private SqlMapClient smc; 
	
	// 2번 생성자 => ibatis 환경설정 및 SqlMapClient 객체 생성
	private MemberDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClientFactory();
//		try {
//			Charset charset = Charset.forName("UTF-8");
//			Resources.setCharset(charset);
//			Reader rd = Resources.getResourceAsReader("kr/or/ddit/config/sqlMapConfig.xml");
//			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
//			rd.close();
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
	}
	// 3번 자기자신의 참조값을 반환하는 메소드 생성
	public static MemberDaoImpl getInstance() {
		if( instance == null ) instance = new MemberDaoImpl();
		return instance;
	}
	
	@Override
	public int insertMember(MemberVO memvo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("member.insertMember", memvo);
			if ( obj == null ) {
				cnt = 1;
			}
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int count = 0;
		try {
			count = smc.delete("member.deleteMember", memId);
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} finally {
		}
		return count;
	}

	@Override
	public int updateMember(MemberVO memvo) {
		int count = 0;
		try {
			count = smc.update("member.updateMember", memvo);
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} finally {
		}
		return count;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		List<MemberVO> memList = null;
		try {
			memList = smc.queryForList("member.getAllMember");
		} catch (SQLException e) {
			memList = null;
			e.printStackTrace();
		}
		return memList;
	}
	// 응답 결과가 1개가 확실한 경우에는 queryForObject()메소드를 사용한다.
				// 형식) smc.queryForObject("namespace값.id값", 파라미터클래스);
				//			반환값 : 
				//					1. 처리결과가 여러개인 경우 => exception 객체 반환
				//	 				2. 처리결과가 1개인 경우 => 해당 객체 반환
				//					3. 처리결과가 없을 경우 => null값 반환
	@Override
	public int getMemberCount(String memId) {
		int count = 0;
		try {
			count = (int)smc.queryForObject("member.getMemberCount", memId);
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		}
		return count;
	}

	
	@Override
	public int updateMember2(Map<String, String> paramMap) {
//		key값 정보 => 회원ID(memId), 수정할컬럼명(field), 수정할데이터(data)
		int cnt = 0;
		try {
			cnt = smc.update("member.updateMember2", paramMap);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}
}
